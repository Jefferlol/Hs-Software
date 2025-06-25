package com.hs.employees.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    private String nombre_completo;
    private String username;
    private String direccion;
    private String telefono;
    private String cargo;
    private String fecha_de_contrato;
}
