package com.nnk.springboot.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO javadoc for this class
@Configuration
public class SecurityConfig {

    class LoginPageFilter extends GenericFilterBean {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            if (SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && ((HttpServletRequest) request).getRequestURI().equals("/login")) {
                ((HttpServletResponse) response).sendRedirect("/");
            }
            chain.doFilter(request, response);
        }
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        final RequestMatcher[] resourceMatcher = new RequestMatcher[] { 
            antMatcher("/css/**")
        };
        http.addFilterBefore(new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(resourceMatcher)
                .permitAll()
                .anyRequest()
                .authenticated())
        .formLogin(login -> login
                .permitAll()
                .defaultSuccessUrl("/"))
        .logout(logout -> logout
                .logoutRequestMatcher(antMatcher("/logout"))
                .permitAll());
        // @formatter:on
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
