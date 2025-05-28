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
    private PowerRepository repository;

    public List<Power> findAll() {
        return repository.findAll();
    }

    public Optional<Power> findById(int id) {
        return repository.findById(id);
    }

    public Power save(Power power) {
        return repository.save(power);
    }
}

