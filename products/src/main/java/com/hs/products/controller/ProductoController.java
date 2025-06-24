package com.hs.products.controller;

import com.hs.products.dto.ProductoRequestDTO;
import com.hs.products.entity.Producto;
import com.hs.products.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // 🔍 Obtener todos los productos
    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.obtenerTodos();
    }

    // 🔍 Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 💾 Crear producto con DTO
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoRequestDTO dto) {
        Producto nuevo = productoService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(nuevo);
    }

    // ✏️ Actualizar producto con DTO
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO dto) {
        try {
            Producto actualizado = productoService.actualizarDesdeDTO(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🗑️ Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(p -> {
                    productoService.eliminarPorId(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔍 Buscar por categoría
    @GetMapping("/categoria/{idCategoria}")
    public List<Producto> listarPorCategoria(@PathVariable Long idCategoria) {
        return productoService.buscarPorCategoria(idCategoria);
    }

    // 🔍 Buscar productos con descuento
    @GetMapping("/con-descuento")
    public List<Producto> listarConDescuento() {
        return productoService.buscarConDescuento();
    }
}
