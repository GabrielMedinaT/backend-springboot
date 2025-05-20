package com.ieselrincon.controller;

import com.ieselrincon.model.Measurement;
import com.ieselrincon.model.MeasurementDocument;
import com.ieselrincon.repository.jpa.MeasurementRepository;
import com.ieselrincon.repository.mongo.MeasurementMongoRepository;  // Nuevo repositorio para MongoDB
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

    @Autowired
    private MeasurementMongoRepository measurementMongoRepository;  // Repositorio de MongoDB

    // GET: Obtener todas las mediciones (desde MySQL)
    @GetMapping
    public List<Measurement> getAllMeasurements(@RequestParam(value = "idSensor", required = false) Integer idSensor, @RequestParam(value = "date", required = false) String date) {
        if (date !=  null) {
            try {
                if (idSensor != null) {return measurementRepository.findByIdAndDate(idSensor, LocalDate.parse(date));}
                return measurementRepository.findByDate(LocalDate.parse(date));
            } catch (DateTimeParseException e) {
                System.out.println("Error al procesar la fecha.");
                return List.of();
            }
        }
        else if (idSensor != null) {
            // Filtrar por groupId si se pasa como parámetro
            return measurementRepository.findByIdSensor(idSensor);
        }
        return measurementRepository.findAll();
    }

    // POST: Crear una nueva medición (guardar en MySQL y MongoDB)
    @PostMapping
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        // Si no se envía fecha, asignar la fecha actual
        if (measurement.getFecha() == null) {
            measurement.setFecha(LocalDateTime.now());
        }

        // Guardar en MySQL
        Measurement savedMeasurement = measurementRepository.save(measurement);

        // Convertir la entidad JPA a un documento MongoDB
        MeasurementDocument measurementDocument = new MeasurementDocument();
        measurementDocument.setIdSensor(measurement.getIdSensor());
        measurementDocument.setConsumo(measurement.getConsumo());
        measurementDocument.setFecha(measurement.getFecha());

        // Guardar en MongoDB
        measurementMongoRepository.save(measurementDocument);

        return new ResponseEntity<>(savedMeasurement, HttpStatus.CREATED);
    }
}