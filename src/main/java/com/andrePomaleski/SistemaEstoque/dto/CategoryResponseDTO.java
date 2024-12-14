package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Category;
import com.andrePomaleski.SistemaEstoque.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<Product> productList;

    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.productList = category.getProducts();
    }
}
