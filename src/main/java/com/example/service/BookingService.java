package com.example.service;

import com.example.dao.BookingDAO;
import com.example.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingDAO bookingDAO;

    @Autowired
    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    // Создание или обновление бронирования
    public Booking saveOrUpdateBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    // Поиск бронирования по ID
    public Optional<Booking> findById(Long id) {
        return bookingDAO.findById(id);
    }

    // Поиск всех бронирований
    public List<Booking> findAll() {
        return bookingDAO.findAll();
    }

    // Удаление бронирования по ID
    public void deleteBooking(Long id) {
        bookingDAO.deleteById(id);
    }

    // Поиск бронирований по идентификатору пользователя
    public List<Booking> findByUserId(Long userId) {
        return bookingDAO.findByUserId(userId);
    }

    // Поиск бронирований по идентификатору события
    public List<Booking> findByEventId(Long eventId) {
        return bookingDAO.findByEventId(eventId);
    }


}
