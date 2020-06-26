package com.ahmad.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/auth/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);


    }
}
