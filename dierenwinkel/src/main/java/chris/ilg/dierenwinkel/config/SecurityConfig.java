package chris.ilg.dierenwinkel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
@Configuration
    public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return CookieCsrfTokenRepository.withHttpOnlyFalse();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .cors(cors -> cors // Enable CORS
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of("http://localhost:5173"));
                            config.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(List.of("*"));
                            return config;
                        })
                )
                .csrf(csrf -> csrf // Enable CSRF
                        .csrfTokenRepository(csrfTokenRepository())

                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers( "http://localhost:5173/**").permitAll() // Permit OPTIONS requests
                         .requestMatchers(HttpMethod.OPTIONS, "http://localhost:5173/**").permitAll() // Permit OPTIONS requests
                        .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin // Configure form-based authentication
                        .loginPage("/login").permitAll() // Customize login page
                        .defaultSuccessUrl("/home").permitAll() // Redirect after successful login
                )
                .logout(logout -> logout // Configure logout
                        .logoutUrl("/logout").permitAll() // Customize logout URL
                        .logoutSuccessUrl("/login?logout").permitAll() // Redirect after logout
                );
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/confirm-order").authenticated() // Confirm order page accessible only to authenticated users
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin pages accessible only to users with role ADMIN
                        .anyRequest().permitAll()                )
                .httpBasic(withDefaults());
        return http.build();
    }
/*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }
    */



}


