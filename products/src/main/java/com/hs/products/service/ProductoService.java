package com.hs.products.service;

import com.hs.products.dto.ProductoRequestDTO;
import com.hs.products.entity.Categoria;
import com.hs.products.entity.Producto;
import com.hs.products.repository.CategoriaRepository;
import com.hs.products.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // 🔍 Listar todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // 🔍 Buscar por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // 💾 Crear producto desde DTO
    public Producto guardarDesdeDTO(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setUnidad(dto.getUnidad());
        producto.setPrecio_docena(dto.getPrecioDocena());
        producto.setDescuentoDocena(dto.getDescuentoDocena());
        producto.setImagen1(dto.getImagen1());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    // ✏️ Actualizar producto
    public Producto actualizarDesdeDTO(Long id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setUnidad(dto.getUnidad());
        producto.setPrecio_docena(dto.getPrecioDocena());
        producto.setDescuentoDocena(dto.getDescuentoDocena());
        producto.setImagen1(dto.getImagen1());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    // 🗑️ Eliminar
    public void eliminarPorId(Long id) {
        productoRepository.deleteById(id);
    }

    // 🔎 Buscar productos por categoría
    public List<Producto> buscarPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    // 🎁 Buscar productos con descuento
    public List<Producto> buscarConDescuento() {
        return productoRepository.findByDescuentoDocenaGreaterThan(0.0);
    }
}
