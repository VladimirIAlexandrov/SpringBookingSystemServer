package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // Пользовательский запрос
    @Query("SELECT c FROM Client c WHERE c.email = ?1")
    Client findByEmail(String email);

    // В Spring Data JPA также можно использовать именованные параметры
    @Query("SELECT c FROM Client c WHERE c.username = :username")
    Client findByUsername(String username);
}

