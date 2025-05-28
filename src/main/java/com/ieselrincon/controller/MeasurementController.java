package com.ieselrincon.controller;

import com.ieselrincon.model.Measurement;
import com.ieselrincon.repository.jpa.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/medidas")
public class MeasurementController {

    @Autowired
    private MeasurementRepository measurementRepository;  // Repositorio de MySQL

    // GET: Obtener todas las mediciones (desde MySQL)
    @GetMapping
    public List<Measurement> getAllMeasurements(
            @RequestParam(value = "idSensor", required = false) Integer idSensor,
            @RequestParam(value = "date", required = false) String date) {

        if (date != null) {
            try {
                LocalDate parsedDate = LocalDate.parse(date);
                if (idSensor != null) {
                    return measurementRepository.findByIdAndDate(idSensor, parsedDate);
                }
                return measurementRepository.findByDate(parsedDate);
            } catch (DateTimeParseException e) {
                System.out.println("Error al procesar la fecha.");
                return List.of();
            }
        } else if (idSensor != null) {
            return measurementRepository.findByIdSensor(idSensor);
        }

        return measurementRepository.findAll();
    }

    // POST: Crear una nueva medición (guardar en MySQL)
    @PostMapping
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        if (measurement.getFecha() == null) {
            measurement.setFecha(LocalDateTime.now());
        }

        Measurement savedMeasurement = measurementRepository.save(measurement);
        return new ResponseEntity<>(savedMeasurement, HttpStatus.CREATED);
    }

    // GET: Obtener todas las mediciones por idSensor
    @GetMapping("/sensor/{idSensor}")
    public List<Measurement> getMeasurementsBySensor(@PathVariable int idSensor) {
        return measurementRepository.findByIdSensor(idSensor);
    }
}
