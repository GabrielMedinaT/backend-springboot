package com.ieselrincon.controller;// 

import com.ieselrincon.model.Power;
import com.ieselrincon.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/powers")// Controlador REST para la entidad Power 

public class PowerController {// Todas las rutas de este controlador comienzan con /api/powers

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
