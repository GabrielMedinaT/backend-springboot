package com.ieselrincon.repository.jpa;

import com.ieselrincon.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {// Repositorio JPA para la entidad Measurement
    List<Measurement> findByIdSensor(int idSensor);// Método para encontrar mediciones por ID de sensor

    @Query("SELECT m FROM Measurement m WHERE DATE(m.fecha) = :date")// Método para encontrar mediciones por fecha
    List<Measurement> findByDate(LocalDate date);

    @Query("SELECT m FROM Measurement m WHERE DATE(m.fecha) = :date AND m.idSensor = :idSensor")// Método para encontrar mediciones por ID de sensor y fecha
    List<Measurement> findByIdAndDate(int idSensor, LocalDate date);
}