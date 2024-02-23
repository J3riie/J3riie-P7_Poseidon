package com.nnk.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService users() {
        final UserDetails user = User.builder().username("user")
                .password("{bcrypt}$2a$10$NO9SvxeyObq.6sb9o4wdOewqOfgjzGJsti8RZaYjulSYzeTij7S5y") // user
                .roles("USER").build();
        final UserDetails admin = User.builder().username("admin")
                .password("{bcrypt}$2a$10$1AkPsgCps7CSIdclFIZfr.q3AwYnd4jOfF7XxGNINaP.KJ6EYjjSW") // admin
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
