package com.hs.employees.service;

import com.hs.employees.entity.Empleado;
import com.hs.employees.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado crear(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Long id, Empleado datosActualizados) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre_completo(datosActualizados.getNombre_completo());
            empleado.setUsername(datosActualizados.getUsername());
            empleado.setDireccion(datosActualizados.getDireccion());
            empleado.setTelefono(datosActualizados.getTelefono());
            empleado.setCargo(datosActualizados.getCargo());
            empleado.setFecha_de_contrato(datosActualizados.getFecha_de_contrato());
            return empleadoRepository.save(empleado);
        }).orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    }
}
