package com.ieselrincon.controller;

import com.ieselrincon.model.Power;
import com.ieselrincon.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/powers")
public class PowerController {

    @Autowired
    private PowerService service;

    @GetMapping
    public List<Power> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Power> getById(@PathVariable int id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Power update(@PathVariable int id, @RequestBody Power updatedPower) {
        updatedPower.setId(id); // aseguramos que el ID del path se use
        return service.save(updatedPower);
    }
}
