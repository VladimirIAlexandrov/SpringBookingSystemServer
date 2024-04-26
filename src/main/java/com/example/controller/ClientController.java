package com.example.controller;

import com.example.model.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService userService;

    @Autowired
    public ClientController(ClientService userService) {
        this.userService = userService;
    }

    // Получение всех пользователей
    @CrossOrigin
    @GetMapping

    public List<Client> getAllUsers() {
        return userService.findAll();
    }

    // Получение пользователя по ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Создание нового пользователя
    @CrossOrigin
    @PostMapping
    public Client createUser(@RequestBody Client user) {

        return userService.saveOrUpdateUser(user);

    }

    // Обновление существующего пользователя
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateUser(@PathVariable Long id, @RequestBody Client client) {
        return userService.findById(id)
                .map(existingUser -> {
                    client.setId(id);
                    return ResponseEntity.ok(userService.saveOrUpdateUser(client));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Удаление пользователя
    @CrossOrigin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Поиск пользователя по имени или почте (пример)
    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        if (username != null) {
            return ResponseEntity.ok(List.of(userService.findByUsername(username).orElse(null)));
        } else if (email != null) {
            return ResponseEntity.ok(List.of(userService.findByEmail(email).orElse(null)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
