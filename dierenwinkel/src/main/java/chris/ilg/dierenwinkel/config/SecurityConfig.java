package chris.ilg.dierenwinkel.config;

import chris.ilg.dierenwinkel.Security.*;
import chris.ilg.dierenwinkel.controller.OrderController;
import chris.ilg.dierenwinkel.repository.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private UserRepo userRepo;
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("http://localhost:5173/**").allowedMethods("*");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:5173/")); // Replace with your frontend origin
                    config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "PATCH", "DELETE"));
                    config.setAllowedHeaders(Arrays.asList("Content-Type", "POST","Authorization")); // Adjust as needed
                    config.addExposedHeader("Set-Cookie");
                    config.setExposedHeaders(Arrays.asList("Set-Cookie", "Session-ID"));
                    config.setAllowCredentials(true);
                    return config;}))

                .csrf((csrf) -> csrf.disable());

        return http.build();
    }
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("http://localhost:*").permitAll()
                                .requestMatchers("http://localhost:5713/*").permitAll()
                                .requestMatchers("http://localhost:5713/validate").authenticated()
                                //.requestMatchers("/admin").hasRole("ADMIN")

                ).formLogin(
                        form -> form
                                .loginPage("/login").permitAll()
                                .loginProcessingUrl("/login").permitAll()
                                //.defaultSuccessUrl("http://localhost:5713/catalog")
                                .successHandler(successfulLoginHandler())
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/login?logout"))
                                .permitAll()
                                .logoutSuccessUrl("http://localhost:5713/") // Specify the URL after logout
                                .invalidateHttpSession(true) // Invalidate the HTTP session on logout
                                .deleteCookies("JSESSIONID")
                )

                .sessionManagement( sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // Match application.properties setting
                        .sessionAuthenticationStrategy(new CustomSessionAuthentication())
                        .invalidSessionUrl("/login") // Redirect to login if the session is invalid
                        .maximumSessions(1) // Allow only one session per user
                        .expiredUrl("/login?expired") // Redirect to login if the session expires
                );

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService(userRepo);
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(Arrays.asList(provider));
    }

    @Bean
    public AuthenticationSuccessHandler successfulLoginHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // Ensure a session is created (if not already present)
                HttpSession session = request.getSession(true);

                // Set the session ID in the response header
                response.setHeader("Session-ID", session.getId());

                // Log for debugging
                logger.info("Session ID on successful login: " + session.getId());

                // You can optionally add a redirect or additional logic here
                response.setStatus(HttpServletResponse.SC_OK);
                // response.sendRedirect("/home"); // Modify as necessary if you want a redirect after login
            }
        };
    }

}

