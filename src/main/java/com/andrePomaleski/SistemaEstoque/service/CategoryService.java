package com.andrePomaleski.SistemaEstoque.service;

import com.andrePomaleski.SistemaEstoque.dto.CategoryRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.CategoryExceptionHandler.CategoryNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Category;
import com.andrePomaleski.SistemaEstoque.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Nenhuma categoria encontrada"));
    }

    public Category createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.name());
        category.setDescription(categoryRequestDTO.description());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Nenhuma categoria encontrada"));

        category.setName(categoryRequestDTO.name());
        category.setDescription(categoryRequestDTO.description());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Nenhuma categoria encontrada"));
        categoryRepository.delete(category);
    }
}
