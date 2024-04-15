package chris.ilg.dierenwinkel.config;

import chris.ilg.dierenwinkel.Security.CustomSessionAuthentication;
import chris.ilg.dierenwinkel.Security.CustomUserDetails;
import chris.ilg.dierenwinkel.Security.CustomUserDetailsService;
import chris.ilg.dierenwinkel.repository.UserRepo;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {


    @Autowired
    private UserRepo userRepo;
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("http://localhost:5173/**").allowedMethods("*");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                        .ignoringRequestMatchers("/register")
                        //.ignoringRequestMatchers("/api/user/add")
                        .ignoringRequestMatchers("/login")
                        .ignoringRequestMatchers("/api/product/all")
                        .ignoringRequestMatchers("/api/product/filter/{category}")
                        .ignoringRequestMatchers("/api/product/{id}")

                )
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:5173/")); // Replace with your frontend origin
                    config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "PATCH", "DELETE"));
                    config.setAllowedHeaders(Arrays.asList("Content-Type","User-Id","Set-Cookie", "POST")); // Adjust as needed
                    config.setAllowCredentials(true);
                    return config;}))
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                        .ignoringRequestMatchers("http://localhost:8080/register")
                )
        ;

        return http.build();
    }
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("http://localhost:*").permitAll()
                                .requestMatchers("http://localhost:5713/*").permitAll()
                                .requestMatchers("http://localhost:5713/validate").authenticated()
                                .requestMatchers("/admin").hasRole("ADMIN")

                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("http://localhost:5713/catalog")
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

}

