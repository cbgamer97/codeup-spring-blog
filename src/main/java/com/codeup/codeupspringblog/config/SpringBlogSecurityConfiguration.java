package com.codeup.codeupspringblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import static org.springframework.transaction.TransactionDefinition.withDefaults;

@Configuration
public class SpringBlogSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/posts/create", "/posts/*/edit", "/profile").authenticated()
                .requestMatchers("/posts", "/create", "/register", "/login", "/index").permitAll()
                .requestMatchers("/css/**", "/js/**").permitAll()
        );

        http.formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/profile"));
        http.logout((form) -> form.logoutSuccessUrl("/login"));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
