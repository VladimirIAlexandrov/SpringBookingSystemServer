package com.example.model;

import jakarta.persistence.*;


import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Client user;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private LocalDateTime bookingTimeStart;

    @Column(nullable = false)
    private LocalDateTime bookingTimeEnd;

    @Column(nullable = false)
    private String status;  // Например, "CONFIRMED", "CANCELLED"

    // Конструкторы
    public Booking() {
    }

    public Booking(Client user, Event event, LocalDateTime bookingTime, LocalDateTime bookingTimeStart, LocalDateTime bookingTimeEnd, String status) {
        this.user = user;
        this.event = event;
        this.bookingTime = bookingTime;
        this.bookingTimeStart = bookingTimeStart;
        this.bookingTimeEnd = bookingTimeEnd;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getBookingTimeStart() {
        return bookingTimeStart;
    }
    public void setBookingTimeStart(LocalDateTime bookingTimeStart) {
        this.bookingTimeStart = bookingTimeStart;
    }

    public LocalDateTime getBookingTimeEnd() {
        return bookingTimeEnd;
    }
    public void setBookingTimeEnd(LocalDateTime bookingTimeEnd) {
        this.bookingTimeEnd = bookingTimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString, hashCode, equals методы (при необходимости)
}
