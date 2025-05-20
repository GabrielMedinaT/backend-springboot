package com.ieselrincon.repository.jpa;

import com.ieselrincon.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByIdSensor(int idSensor);

    @Query("SELECT m FROM Measurement m WHERE DATE(m.fecha) = :date")
    List<Measurement> findByDate(LocalDate date);

    @Query("SELECT m FROM Measurement m WHERE DATE(m.fecha) = :date AND m.idSensor = :idSensor")
    List<Measurement> findByIdAndDate(int idSensor, LocalDate date);
}