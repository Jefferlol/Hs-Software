package com.hs.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")  // nombre real de columna
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private Double precio_docena;
    private String unidad;

    @Column(name = "descuento_docena")
    private Double descuentoDocena;

    private String imagen1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    @JsonIgnore
    private Categoria categoria;
}
