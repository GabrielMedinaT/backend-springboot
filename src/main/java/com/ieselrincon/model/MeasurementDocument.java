package com.ieselrincon.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "measurements")
public class MeasurementDocument {

    @Id
    private String id;  // MongoDB usa String para los IDs

    private int idSensor;
    private Double  medidas;
    private LocalDateTime fecha;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public Double getMedidas() {
        return medidas;
    }

    public void setMedidas(Double medidas) {
        this.medidas = medidas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}