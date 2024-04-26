package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    //  поиск мероприятий по имени, используя именованный параметр
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:name%")
    List<Event> findByNameContaining(String name);

    //  поиск мероприятия по описанию
    @Query("SELECT e FROM Event e WHERE e.description LIKE %:description%")
    List<Event> findByDescriptionContaining(String description);


}
