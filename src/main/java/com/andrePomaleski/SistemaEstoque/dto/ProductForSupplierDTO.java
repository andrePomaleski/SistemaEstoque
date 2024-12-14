package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductForSupplierDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;

    public static List<ProductForSupplierDTO> mapProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(product -> new ProductForSupplierDTO(
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

}
