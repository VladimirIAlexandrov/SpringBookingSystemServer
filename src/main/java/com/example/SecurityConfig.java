package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Настройка авторизации
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/clients/**").hasAuthority("ROLE_ROLE_ADMIN")
                        .requestMatchers("/api/images/**").permitAll()
                        .requestMatchers("/api/events/get_all").permitAll()
                        .requestMatchers("/api/events/search").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/events/create").hasAuthority("ROLE_ROLE_ADMIN")

                        .anyRequest().authenticated())
                .httpBasic(withDefaults());

        // Отключение CSRF защиты
        http
                .csrf((csrf) -> csrf.disable())
        ;
        // Добавляем кастомный JWT фильтр перед UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // для отключения шифрования паролей
        return NoOpPasswordEncoder.getInstance();
    }
}
