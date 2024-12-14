package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
    @NotBlank(message = "Supplier name cannot be blank")
    private String name;
    @NotBlank(message = "Contact info cannot be blank")
    private String contactInfo;
    @NotEmpty(message = "Product list cannot be empty")
    private List<Long> productIds;
}
