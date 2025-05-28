package com.ieselrincon.service;

import com.ieselrincon.model.Measurement;
import com.ieselrincon.model.MeasurementDocument;
import com.ieselrincon.repository.jpa.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;  // Repositorio de MySQL

    
    // Guardar la medición en ambas bases de datos
    public void saveMeasurement(Measurement measurement) {
        // Guardar en MySQL
        measurementRepository.save(measurement);

        // Convertir la entidad JPA a un documento MongoDB
        MeasurementDocument measurementDocument = new MeasurementDocument();
        measurementDocument.setIdSensor(measurement.getIdSensor());
        measurementDocument.setMedidas(measurement.getMedidas());
        measurementDocument.setFecha(measurement.getFecha());

        // Guardar en MongoDB
    }
}