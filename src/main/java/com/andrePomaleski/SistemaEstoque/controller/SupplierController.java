package com.andrePomaleski.SistemaEstoque.controller;

import com.andrePomaleski.SistemaEstoque.dto.SupplierRequestDTO;
import com.andrePomaleski.SistemaEstoque.dto.SupplierResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.ProductExceptionHandler.ProductNotFoundException;
import com.andrePomaleski.SistemaEstoque.exception.SupplierExceptionHandler.SupplierNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Supplier;
import com.andrePomaleski.SistemaEstoque.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierResponseDTO> supplierResponseDTOS = suppliers.stream().map(SupplierResponseDTO::new).toList();
        return ResponseEntity.ok(supplierResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) throws SupplierNotFoundException {
        Supplier supplier = supplierService.getSupplierById(id);
        SupplierResponseDTO response = new SupplierResponseDTO(supplier);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO) throws SupplierNotFoundException {
        Supplier supplier = supplierService.createSupplier(supplierRequestDTO);
        SupplierResponseDTO response = new SupplierResponseDTO();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setContactInfo(supplier.getContactInfo());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id,
                                                              @RequestBody SupplierRequestDTO supplierRequestDTO) throws SupplierNotFoundException, ProductNotFoundException {
        Supplier supplier = supplierService.updateSupplier(id, supplierRequestDTO);
        SupplierResponseDTO response = new SupplierResponseDTO(supplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) throws SupplierNotFoundException {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

