package com.ieselrincon.controller;

import com.ieselrincon.model.Schedule;
import com.ieselrincon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")// Controlador REST para la entidad Schedule
public class ScheduleController {// Todas las rutas de este controlador comienzan con /api/schedules

    @Autowired
    private ScheduleService scheduleService;// Inyección del servicio que maneja la lógica de negocio

    @PostMapping // Ruta para crear un nuevo horario
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {// Recibe un objeto Schedule en el cuerpo de la solicitud
        Schedule saved = scheduleService.saveSchedule(schedule);// Llama al servicio para guardar el horario
        return new ResponseEntity<>(saved, HttpStatus.CREATED);// Devuelve el horario guardado con estado 201 Created
    }
}
