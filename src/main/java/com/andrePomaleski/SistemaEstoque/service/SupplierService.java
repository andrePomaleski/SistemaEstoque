package com.andrePomaleski.SistemaEstoque.service;
import com.andrePomaleski.SistemaEstoque.dto.SupplierRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.SupplierExceptionHandler.SupplierNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Supplier;
import com.andrePomaleski.SistemaEstoque.repository.ProductRepository;
import com.andrePomaleski.SistemaEstoque.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) throws SupplierNotFoundException {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado"));
    }

    @Transactional
    public Supplier createSupplier(SupplierRequestDTO supplierRequestDTO) {
        if (supplierRequestDTO == null) {
            throw new IllegalArgumentException("SupplierRequestDTO cannot be null");
        }

        Supplier supplier = new Supplier();
        if (supplierRequestDTO.getName() == null || supplierRequestDTO.getContactInfo() == null) {
            throw new IllegalArgumentException("Name and contact info cannot be null");
        }

        supplier.setName(supplierRequestDTO.getName());
        supplier.setContactInfo(supplierRequestDTO.getContactInfo());

        try {
            return supplierRepository.save(supplier);
        } catch (DataAccessException e) {
            // Log and handle the database exception
            throw new RuntimeException("Failed to save supplier to the database", e);
        }
    }



    @Transactional
    public Supplier updateSupplier(Long id, SupplierRequestDTO supplierRequestDTO)
            throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado"));

        if (supplierRequestDTO.getName() != null) {
            supplier.setName(supplierRequestDTO.getName());
        }
        if (supplierRequestDTO.getContactInfo() != null) {
            supplier.setContactInfo(supplierRequestDTO.getContactInfo());
        }

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado"));
        supplierRepository.delete(supplier);
    }
}