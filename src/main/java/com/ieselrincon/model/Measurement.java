package com.ieselrincon.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_measure")
    private int idMeasure;

    @Column(name = "id_sensor")
    private int idSensor;

    @Column(name = "medidas")
    private Double medidas;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "incremento")
    private Double incremento;

    // Getters y Setters con convención correcta
    public int getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(int idMeasure) {
        this.idMeasure = idMeasure;
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

    public Double getIncremento() {
        return incremento;
    }

    public void setIncremento(Double incremento) {
        this.incremento = incremento;
    }
}
