package com.example.service;

import com.example.dao.ClientDAO;
import com.example.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientDAO ClientDAO;

    @Autowired
    public ClientService(ClientDAO ClientDAO) {
        this.ClientDAO = ClientDAO;

    }

    public Client saveOrUpdateUser(Client client) {

        return ClientDAO.save(client);
    }

    // Поиск пользователя по ID
    public Optional<Client> findById(Long id) {
        return ClientDAO.findById(id);
    }

    // Поиск всех пользователей
    public List<Client> findAll() {
        return ClientDAO.findAll();
    }

    // Удаление пользователя по ID
    public void deleteUser(Long id) {
        ClientDAO.deleteById(id);
    }

    // Поиск пользователя по email
    public Optional<Client> findByEmail(String email) {
        return ClientDAO.findByEmail(email);
    }

    // Поиск пользователя по имени пользователя
    public Optional<Client> findByUsername(String username) {
        return ClientDAO.findByUsername(username);
    }

    // Другие методы, связанные с бизнес-логикой пользователей
    // Например, методы для изменения пароля, проверки прав доступа и т.д.
}
