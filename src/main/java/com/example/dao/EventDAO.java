package com.example.dao;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventDAO extends JpaRepository<Event, Long> {

    // Пример: Поиск событий по имени
    List<Event> findByNameContaining(String name);

}
