package com.ieselrincon.service;

import com.ieselrincon.model.Schedule;
import com.ieselrincon.repository.jpa.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;// Repositorio JPA para la entidad Schedule

    public Schedule saveSchedule(Schedule schedule) {// Método para guardar un horario
        return scheduleRepository.save(schedule);
    }
}
