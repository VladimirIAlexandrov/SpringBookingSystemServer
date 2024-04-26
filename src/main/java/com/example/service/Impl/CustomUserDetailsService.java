package com.example.service.Impl;

import com.example.dao.ClientDAO;
import com.example.model.Client;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientDAO clientDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    private void init() {
        this.passwordEncoder = context.getBean(PasswordEncoder.class);
    }

    @Autowired
    public CustomUserDetailsService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        Collection<GrantedAuthority> authorities = client.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return User.builder()
                .username(client.getUsername())
                .password(client.getPassword()) // Убедитесь, что пароль зашифрован
                .authorities(authorities) // Исправлено
                .build();
    }
}
