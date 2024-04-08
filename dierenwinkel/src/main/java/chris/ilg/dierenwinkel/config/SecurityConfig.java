package chris.ilg.dierenwinkel.config;

import chris.ilg.dierenwinkel.Security.CustomUserDetails;
import chris.ilg.dierenwinkel.Security.CustomUserDetailsService;
import chris.ilg.dierenwinkel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**").allowedMethods("*");
////    }
////
////    @Bean
////    @Order(0)
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .cors(cors -> cors.disable())
////                .csrf((csrf) -> csrf.disable())
////                 ;
////
////        return http.build();
////    }
////    @Bean
////    @Order(1)
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .cors(cors -> cors.disable());
////        return http.build();
////    }
////
////
////    CorsConfigurationSource apiConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
////        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
////        configuration.setAllowCredentials(true);
////        configuration.setAllowedHeaders(List.of("*"));
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
////
////    CorsConfigurationSource myWebsiteConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
////        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//
////    private CustomUserDetailsService customUserDetailsService;
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .requestMatchers("http://localhost:5173/*").permitAll()
////                        .anyRequest().authenticated()
////                );
////
////        return http.build();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(
////            UserDetailsService userDetailsService,
////            PasswordEncoder passwordEncoder) {
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(userDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder);
////
////        return new ProviderManager(authenticationProvider);
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails userDetails = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("password")
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(userDetails);
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
////
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////
////    private BCryptPasswordEncoder passwordEncoder;
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeRequests((authorize) -> authorize
////                .requestMatchers("http://localhost:5173/*").permitAll()
//////                .requestMatchers("http://localhost:5173/").permitAll()
//////                .anyRequest("/welcome").authenticated()
//////                .anyRequest("/admin").hasAuthority("Admin")
//////                .anyRequest("/mgr").hasAuthority("Manager")
//////                .anyRequest("/emp").hasAuthority("Employee")
//////                .anyRequest("/hr").hasAuthority("HR")
//////                .anyRequest("/common").hasAnyAuthority("Employee", "Manager", "Admin")
////                .anyRequest().authenticated()
//////                .and()
//////                .formLogin().defaultSuccessUrl("http://localhost:5173/", true)
//////                .and()
//////                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")
////                ).httpBasic(Customizer.withDefaults())
////                .formLogin(Customizer.withDefaults());
////;
////
////        return http.build();
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(userDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder);
////        return authenticationProvider;
////    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
////    @Bean
////    public AuthenticationManager authenticationManager(){
////
////        authenticationManagerBean() throws Exception {
////                return defaultSecurityFilterChain(null).getAuthenticationManager();
////            }
////    }
//
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf((csrf) -> csrf.disable())
//                .cors(cors -> cors.disable())
//
//                .authorizeHttpRequests((authorize) ->
//                        authorize.requestMatchers("/register/**").permitAll()
//                                .requestMatchers("/index").permitAll()
//                                .requestMatchers("/users").hasRole("ADMIN")
//                ).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/catalog")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                                .logoutSuccessUrl("/login?logout") // Specify the URL after logout
//                                .invalidateHttpSession(true) // Invalidate the HTTP session on logout
//                                .deleteCookies("JSESSIONID")
//                );
//        return http.build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
////    @Bean
////    public AuthenticationManager authenticationManagerBean()
////            throws Exception {
////        //HttpSecurity http = new HttpSecurity()
////        return super.authenticationManagerBean();
////                //HttpSecurity.
////                //filterChain(null).getSharedObject(AuthenticationManager.class);
////    }
//
//

    @Autowired
    private UserRepo userRepo;

    @Bean
    @Order(0)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf((csrf) -> csrf.disable())
                 ;

        return http.build();
    }
    @Order(1)
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
              .cors(cors -> cors.disable())

                /*.cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    // Customize CORS configuration here
                    // (e.g., allowed origins, methods, headers)
                    return config;
                }) // Enable CORS with specific configuration (uncomment if needed)
                */
                /*
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Enable CSRF with protection (uncomment if needed)
                */
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("http://localhost:*").permitAll()
                                .requestMatchers("http://localhost:5713/*").permitAll()
                                .requestMatchers("http://localhost:5713/validate").authenticated()
                                .requestMatchers("/admin").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/catalog")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                                .logoutSuccessUrl("/login?logout") // Specify the URL after logout
                                .invalidateHttpSession(true) // Invalidate the HTTP session on logout
                                .deleteCookies("JSESSIONID")
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

