package com.ieselrincon.repository.jpa;

import com.ieselrincon.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {// Repositorio JPA para la entidad UserLogin
    UserLogin findByUser(String user);
}
