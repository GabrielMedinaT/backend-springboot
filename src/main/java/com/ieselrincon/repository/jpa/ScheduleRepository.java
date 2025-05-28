package com.ieselrincon.repository.jpa;

import com.ieselrincon.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {// Repositorio JPA para la entidad Schedule
    // Aquí puedes agregar métodos personalizados si es necesario
}
