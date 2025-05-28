package com.ieselrincon.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timePowered;
    private LocalDateTime timeShutdown;
    private String idDevice;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimePowered() {
        return timePowered;
    }

    public void setTimePowered(LocalDateTime timePowered) {
        this.timePowered = timePowered;
    }

    public LocalDateTime getTimeShutdown() {
        return timeShutdown;
    }

    public void setTimeShutdown(LocalDateTime timeShutdown) {
        this.timeShutdown = timeShutdown;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }
}
