package com.ieselrincon.controller;

// Importa la clase del modelo que representa a los usuarios
import com.ieselrincon.model.UserLogin;

// Importa el servicio que maneja la lógica de acceso a los datos de usuario
import com.ieselrincon.service.UserLoginService;

// Anotaciones e infraestructura de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Librería para comparar y cifrar contraseñas de forma segura
import org.mindrot.jbcrypt.BCrypt;

// Define que esta clase es un controlador REST
@RestController

// Define que todas las rutas de este controlador empiezan con /api/login
@RequestMapping("/api/login")
public class LoginController {

    // Inyección automática del servicio que maneja los usuarios
    @Autowired
    private UserLoginService userLoginService;

    // Método que maneja el login. Recibe un JSON con el usuario y la contraseña
    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLogin loginRequest) {
        // Busca el usuario por nombre
        UserLogin user = userLoginService.findByUser(loginRequest.getUser());

        // Si existe y la contraseña es correcta, devuelve 200 OK
        if (user != null && BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            // Si no coincide, devuelve 401 Unauthorized
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Método para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLogin newUser) {
        // Comprueba si ya existe el usuario
        if (userLoginService.findByUser(newUser.getUser()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Cifra la contraseña antes de guardarla
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);

        // Guarda el nuevo usuario en la base de datos
        userLoginService.save(newUser);

        // Devuelve éxito
        return ResponseEntity.ok("User registered successfully");
    }
}
