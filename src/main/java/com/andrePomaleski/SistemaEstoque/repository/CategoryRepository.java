package com.andrePomaleski.SistemaEstoque.repository;

import com.andrePomaleski.SistemaEstoque.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
