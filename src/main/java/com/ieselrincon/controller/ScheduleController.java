package com.ieselrincon.controller;

import com.ieselrincon.model.Schedule;
import com.ieselrincon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule saved = scheduleService.saveSchedule(schedule);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
