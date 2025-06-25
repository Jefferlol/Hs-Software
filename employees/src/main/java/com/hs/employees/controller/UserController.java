package com.hs.employees.controller;

import com.hs.employees.dto.UserRegisterRequest;
import com.hs.employees.dto.UserUpdateRequest;
import com.hs.employees.entity.UserActivo;
import com.hs.employees.service.UserActivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserActivoService userService;

    public UserController(UserActivoService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserActivo> registrarUsuario(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.registrarUsuario(request));
    }

    @GetMapping
    public List<UserActivo> listarUsuarios() {
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserActivo> obtenerPorId(@PathVariable Long id) {
        return userService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserActivo> actualizarUsuario(@PathVariable Long id,
                                                        @RequestBody UserUpdateRequest request) {
        try {
            return ResponseEntity.ok(userService.actualizarUsuario(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
