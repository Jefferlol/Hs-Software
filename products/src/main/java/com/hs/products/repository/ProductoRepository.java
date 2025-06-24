package com.hs.products.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hs.products.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	
    List<Producto> findByCategoria_IdCategoria(Long id_categoria); 
    
    List<Producto> findByDescuentoDocenaGreaterThan(Double descuento);  
}
