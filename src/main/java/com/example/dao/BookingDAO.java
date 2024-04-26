package com.example.dao;

import com.example.model.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {

    // Примеры пользовательских методов запроса

    // Найти все бронирования по идентификатору пользователя
    List<Booking> findByUserId(Long userId);

    // Найти все бронирования для конкретного события
    List<Booking> findByEventId(Long eventId);


}
