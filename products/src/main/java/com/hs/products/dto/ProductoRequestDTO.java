package com.hs.products.dto;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private Double precioDocena;
    private String unidad;
    private Double descuentoDocena;
    private String imagen1;
    private Long categoriaId; // 🔥 solo el ID
}
