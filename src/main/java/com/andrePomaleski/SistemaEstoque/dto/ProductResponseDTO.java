package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Category;
import com.andrePomaleski.SistemaEstoque.model.Product;
import com.andrePomaleski.SistemaEstoque.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private List<Long> categoryIds;
    private Long supplierId;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.categoryIds = product.getCategories().stream().map(Category::getId).toList();
        this.supplierId = product.getSupplier().getId();
    }
}
