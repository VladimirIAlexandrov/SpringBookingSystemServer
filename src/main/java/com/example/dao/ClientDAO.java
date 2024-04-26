package com.example.dao;

import com.example.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDAO extends JpaRepository<Client , Long> {

    // Пример: поиск пользователя по его email
    Optional<Client> findByEmail(String email);

    // Пример: поиск пользователя по его имени пользователя
    Optional<Client> findByUsername(String username);

}
