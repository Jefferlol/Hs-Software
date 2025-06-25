package com.hs.employees.service;

import com.hs.employees.dto.UserRegisterRequest;
import com.hs.employees.dto.UserUpdateRequest;
import com.hs.employees.entity.Empleado;
import com.hs.employees.entity.UserActivo;
import com.hs.employees.repository.EmpleadoRepository;
import com.hs.employees.repository.UserActivoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserActivoService {

    private final UserActivoRepository userRepo;
    private final EmpleadoRepository empleadoRepo;
    private final PasswordEncoder passwordEncoder;

    public UserActivoService(UserActivoRepository userRepo, EmpleadoRepository empleadoRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.empleadoRepo = empleadoRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserActivo registrarUsuario(UserRegisterRequest request) {
        Empleado empleado = empleadoRepo.findById(request.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        UserActivo user = new UserActivo();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmpleado(empleado);

        return userRepo.save(user);
    }

    public List<UserActivo> listarUsuarios() {
        return userRepo.findAll();
    }

    public Optional<UserActivo> obtenerPorId(Long id) {
        return userRepo.findById(id);
    }

    public UserActivo actualizarUsuario(Long id, UserUpdateRequest request) {
        UserActivo user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setUsername(request.getUsername());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getEmpleadoId() != null) {
            Empleado empleado = empleadoRepo.findById(request.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            user.setEmpleado(empleado);
        }

        return userRepo.save(user);
    }
}
