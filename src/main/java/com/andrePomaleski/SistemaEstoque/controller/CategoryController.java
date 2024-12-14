package com.andrePomaleski.SistemaEstoque.controller;

import com.andrePomaleski.SistemaEstoque.dto.CategoryRequestDTO;
import com.andrePomaleski.SistemaEstoque.dto.CategoryResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.CategoryExceptionHandler.CategoryNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Category;
import com.andrePomaleski.SistemaEstoque.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponseDTO> response = categories.stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        Category category = categoryService.getCategoryById(id);
        CategoryResponseDTO response = new CategoryResponseDTO(category);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryService.createCategory(categoryRequestDTO);
        CategoryResponseDTO response = new CategoryResponseDTO(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,
                                                              @RequestBody CategoryRequestDTO categoryRequestDTO) throws CategoryNotFoundException {
        Category category = categoryService.updateCategory(id, categoryRequestDTO);
        CategoryResponseDTO response = new CategoryResponseDTO(category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
