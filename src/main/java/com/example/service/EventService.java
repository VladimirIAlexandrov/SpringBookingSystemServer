package com.example.service;

import com.example.dao.EventDAO;
import com.example.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventDAO eventDAO;

    @Autowired
    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    // Создание или обновление мероприятия
    public Event saveOrUpdateEvent(Event event) {
        return eventDAO.save(event);
    }

    // Поиск мероприятия по ID
    public Optional<Event> findById(Long id) {
        return eventDAO.findById(id);
    }

    // Поиск всех мероприятий
    public List<Event> findAll() {
        return eventDAO.findAll();
    }

    // Удаление мероприятия по ID
    public void deleteEvent(Long id) {
        eventDAO.deleteById(id);
    }

    // Поиск мероприятий по имени
    public List<Event> findByNameContaining(String name) {
        return eventDAO.findByNameContaining(name);
    }

    // Другие методы, связанные с бизнес-логикой мероприятий
    // Например, методы для управления расписанием, проверки доступности мест и т.д.
}
