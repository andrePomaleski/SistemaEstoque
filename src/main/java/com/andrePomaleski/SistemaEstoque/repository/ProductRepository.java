package com.andrePomaleski.SistemaEstoque.repository;

import com.andrePomaleski.SistemaEstoque.model.Product;
import com.andrePomaleski.SistemaEstoque.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
