package com.hs.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hs.products.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
