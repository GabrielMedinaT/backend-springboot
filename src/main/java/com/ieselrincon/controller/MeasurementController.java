package com.ieselrincon.controller;

// Importa el modelo que representa una medición
import com.ieselrincon.model.Measurement;

// Importa el repositorio JPA que se comunica con la base de datos
import com.ieselrincon.repository.jpa.MeasurementRepository;

// Importa las anotaciones necesarias para inyección, REST y manejo HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Importa clases para manejo de fechas y listas
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

// Indica que esta clase es un controlador REST
@RestController

// Todas las rutas de este controlador comienzan con /api/medidas
@RequestMapping("/api/medidas")
public class MeasurementController {

    // Inyección del repositorio que permite acceder a la base de datos
    @Autowired
    private MeasurementRepository measurementRepository;

    // GET /api/medidas: devuelve todas las mediciones o las filtradas por sensor y/o fecha
    @GetMapping
    public List<Measurement> getAllMeasurements(
            @RequestParam(value = "idSensor", required = false) Integer idSensor,  // opcional
            @RequestParam(value = "date", required = false) String date) {         // opcional

        if (date != null) {
            try {
                // Parsea la fecha recibida como string
                LocalDate parsedDate = LocalDate.parse(date);

                // Si se especifica sensor y fecha, busca por ambos
                if (idSensor != null) {
                    return measurementRepository.findByIdAndDate(idSensor, parsedDate);
                }

                // Si solo hay fecha, busca por fecha
                return measurementRepository.findByDate(parsedDate);
            } catch (DateTimeParseException e) {
                // Si la fecha no es válida, muestra error y devuelve lista vacía
                System.out.println("Error al procesar la fecha.");
                return List.of();
            }
        } else if (idSensor != null) {
            // Si solo hay idSensor, busca por sensor
            return measurementRepository.findByIdSensor(idSensor);
        }

        // Si no hay parámetros, devuelve todas las mediciones
        return measurementRepository.findAll();
    }

    // POST /api/medidas: guarda una nueva medición en la base de datos
    @PostMapping
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        // Si no se especifica fecha, se usa la fecha y hora actual
        if (measurement.getFecha() == null) {
            measurement.setFecha(LocalDateTime.now());
        }

        // Guarda la medición y la devuelve con estado 201 CREATED
        Measurement savedMeasurement = measurementRepository.save(measurement);
        return new ResponseEntity<>(savedMeasurement, HttpStatus.CREATED);
    }

    // GET /api/medidas/sensor/{idSensor}: devuelve todas las mediciones para un sensor específico
    @GetMapping("/sensor/{idSensor}")
    public List<Measurement> getMeasurementsBySensor(@PathVariable int idSensor) {
        return measurementRepository.findByIdSensor(idSensor);
    }
}
