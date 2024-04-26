package com.example.controller;

import com.example.model.Event;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Получение всех мероприятий
    @CrossOrigin
    @GetMapping("/get_all")
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    // Получение мероприятия по ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Создание нового мероприятия с изображением
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("dateTime") LocalDateTime dateTime,
                                             @RequestParam("location") String location,
                                             @RequestParam("images") List<MultipartFile> images) {
        try {
            List<String> imagePaths = new ArrayList<>();
            for (MultipartFile image : images) {
                String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(image.getOriginalFilename());
                Path imagePath = Paths.get("images").resolve(fileName);
                Files.copy(image.getInputStream(), imagePath);
                imagePaths.add(fileName);
            }

            String concatenatedPaths = String.join(",", imagePaths); // Объединяем пути в одну строку через запятую

            Event newEvent = new Event(name, description, dateTime, location, concatenatedPaths);
            Event createdEvent = eventService.saveOrUpdateEvent(newEvent);


            return ResponseEntity.ok(createdEvent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    // Обновление существующего мероприятия
    @CrossOrigin
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.findById(id)
                .map(existingEvent -> {
                    event.setId(id);
                    return ResponseEntity.ok(eventService.saveOrUpdateEvent(event));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Удаление мероприятия
    @CrossOrigin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        return eventService.findById(id)
                .map(event -> {
                    eventService.deleteEventById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Поиск мероприятий по имени
    @CrossOrigin
    @GetMapping("/search")
    public List<Event> searchEventsByName(@RequestParam String name) {
        return eventService.findEventsByNameContaining(name);
    }

}
