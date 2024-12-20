package com.andrePomaleski.SistemaEstoque.infra.security;

import com.andrePomaleski.SistemaEstoque.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService; // Inject CustomUserDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Authentication
                        .requestMatchers("/auth/login").permitAll()

                        // Categories
                        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll() // Public access
                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                        // Orders
                        .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("MERCHANT", "ADMIN")
                        .requestMatchers("/orders/**").hasRole("ADMIN") // ADMIN has full access to orders

                        // Products
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll() // Public access
                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("MERCHANT")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("MERCHANT")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("MERCHANT")
                        .requestMatchers("/products/**").hasRole("ADMIN") // ADMIN has full access to products

                        // Suppliers
                        .requestMatchers(HttpMethod.GET, "/suppliers/**").permitAll() // Public access
                        .requestMatchers(HttpMethod.POST, "/suppliers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/suppliers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/suppliers/**").hasRole("ADMIN")

                        // Users
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll() // Public access
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        // Admin full access
                        .requestMatchers("/**").hasRole("ADMIN") // ADMIN can access anything

                        // Default
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService); // Use custom UserDetailsService
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
