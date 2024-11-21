package com.project.testing1.firstjob.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password encoder for securing passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Updated Security configuration for Spring Security 6.1
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/", "/register", "/signin", "/login", "/home", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()  // All other URLs should be secured
                .and()
                .formLogin(form -> form
                        .loginPage("/signin") // Custom login page URL
                        .loginProcessingUrl("/signin") // Handle form login at /signin
                        .permitAll()  // Allow anyone to access the login page
                        .defaultSuccessUrl("/home", true) // Redirect to home page on successful login
                        .failureUrl("/signin?error=true") // Redirect to login page with error message on failure
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Specify the URL for logout
                        .permitAll()  // Allow anyone to access the logout page
                )
                .csrf(csrf -> csrf.disable());  // Disable CSRF protection (only if needed for APIs)

        return http.build();
    }
}
