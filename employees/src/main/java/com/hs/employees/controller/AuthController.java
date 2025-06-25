package com.hs.employees.controller;

import com.hs.employees.dto.AuthRequest;
import com.hs.employees.dto.AuthResponse;
import com.hs.employees.repository.UserActivoRepository;
import com.hs.employees.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserActivoRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserActivoRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return userRepo.findByUsername(request.getUsername())
            .map(user -> {
                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getEmpleado().getCargo());
                    return ResponseEntity.ok(new AuthResponse(token));
                } else {
                    return ResponseEntity.status(401).body("Credenciales inválidas");
                }
            })
            .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }



}
