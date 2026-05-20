package com.company.aviation.security;

import io.jmix.core.JmixSecurityFilterChainOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AviationSecurityConfiguration {

    @Bean
    @Order(JmixSecurityFilterChainOrder.CUSTOM)
    SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/public/**")
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().permitAll()
                );

        return http.build();
    }
}
