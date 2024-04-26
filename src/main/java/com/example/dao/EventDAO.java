package com.example.dao;

import com.example.model.Event;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventDAO {

    private final EventRepository eventRepository;

    @Autowired
    public EventDAO(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Поиск событий по имени
    public List<Event> findByNameContaining(String name) {
        return eventRepository.findByNameContaining(name);
    }

    // Получение всех событий
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    // Получение события по ID
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    // Сохранение события
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    // Удаление события по ID
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    // Другие методы доступа к данным, если необходимо
}
