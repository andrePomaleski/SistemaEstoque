package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDTO {
    private Long id;
    private String name;
    private String contactInfo;
    private List<ProductForSupplierDTO> productList;

    public SupplierResponseDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.contactInfo = supplier.getContactInfo();
        this.productList = ProductForSupplierDTO.mapProductsToDTOs(supplier.getProducts());
    }

}
