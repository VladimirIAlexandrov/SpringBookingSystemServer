package com.example.controller;

import com.example.model.Booking;
import com.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Получение всех бронирований
    @CrossOrigin
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    // Получение бронирования по ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Создание нового бронирования
    @CrossOrigin
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveOrUpdateBooking(booking);
    }

    // Обновление существующего бронирования
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.findById(id)
                .map(existingBooking -> {
                    booking.setId(id);
                    return ResponseEntity.ok(bookingService.saveOrUpdateBooking(booking));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Удаление бронирования
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        return bookingService.findById(id)
                .map(booking -> {
                    bookingService.deleteBooking(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Поиск бронирований по идентификатору пользователя
    @CrossOrigin
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.findByUserId(userId);
    }

    // Поиск бронирований по идентификатору мероприятия
    @CrossOrigin
    @GetMapping("/event/{eventId}")
    public List<Booking> getBookingsByEventId(@PathVariable Long eventId) {
        return bookingService.findByEventId(eventId);
    }

}
