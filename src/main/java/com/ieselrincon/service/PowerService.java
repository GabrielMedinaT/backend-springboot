package com.ieselrincon.service;

import com.ieselrincon.model.Power;
import com.ieselrincon.repository.jpa.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PowerService {

    @Autowired
    private PowerRepository repository;// Repositorio JPA para la entidad Power

    public List<Power> findAll() {// Método para obtener todas los horarios de encendido y apagado
        return repository.findAll();// Devuelve una lista de todos los objetos Power
    }

    public Optional<Power> findById(int id) {// Método para encontrar un horario de encendido y apagado por ID
        return repository.findById(id);// Devuelve un Optional que contiene el objeto Power si se encuentra, o vacío si no
    }

    public Power save(Power power) {// Método para guardar o actualizar un horario de encendido y apagado
        return repository.save(power);// Guarda el objeto Power en la base de datos y devuelve el objeto guardado
    }
}

