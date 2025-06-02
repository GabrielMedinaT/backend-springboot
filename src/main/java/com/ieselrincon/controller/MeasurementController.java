package com.ieselrincon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ieselrincon.model.Measurement;
import com.ieselrincon.repository.jpa.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private MeasurementRepository measurementRepository;

    // ✅ GET /api/medidas (opcional: filtro por idSensor y/o fecha)
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
                System.out.println("❌ Error al procesar la fecha: " + e.getMessage());
                return List.of();
            }
        } else if (idSensor != null) {
            return measurementRepository.findByIdSensor(idSensor);
        }

        return measurementRepository.findAll();
    }

    // ✅ POST para JSON (Postman, Thunder Client, frontend, etc.)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Measurement> createMeasurementFromJson(@RequestBody Measurement measurement) {
        if (measurement.getFecha() == null) {
            measurement.setFecha(LocalDateTime.now());
        }
        Measurement saved = measurementRepository.save(measurement);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ POST para FORM-URLENCODED (por ejemplo, Waspmote si no se usa JSON explícitamente)
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Measurement> createMeasurementFromForm(
            @RequestParam("idSensor") int idSensor,
            @RequestParam("medidas") float medidas) {

        Measurement measurement = new Measurement();
        measurement.setIdSensor(idSensor);
        measurement.setMedidas(Double.valueOf(medidas)); // conversión segura de float a Double
        measurement.setFecha(LocalDateTime.now());

        Measurement saved = measurementRepository.save(measurement);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ POST para JSON sin Content-Type declarado correctamente (Waspmote usando raw JSON)
    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Measurement> createMeasurementFromRawJson(@RequestBody String rawJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Measurement measurement = objectMapper.readValue(rawJson, Measurement.class);

            if (measurement.getFecha() == null) {
                measurement.setFecha(LocalDateTime.now());
            }

            Measurement saved = measurementRepository.save(measurement);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ GET por ID de sensor
    @GetMapping("/sensor/{idSensor}")
    public List<Measurement> getMeasurementsBySensor(@PathVariable int idSensor) {
        return measurementRepository.findByIdSensor(idSensor);
    }
}
