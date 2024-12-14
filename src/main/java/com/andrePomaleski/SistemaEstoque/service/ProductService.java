package com.andrePomaleski.SistemaEstoque.service;

import com.andrePomaleski.SistemaEstoque.dto.ProductRequestDTO;
import com.andrePomaleski.SistemaEstoque.dto.ProductResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.CategoryExceptionHandler.CategoryNotFoundException;
import com.andrePomaleski.SistemaEstoque.exception.ProductExceptionHandler.ProductNotFoundException;
import com.andrePomaleski.SistemaEstoque.exception.SupplierExceptionHandler.SupplierNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Category;
import com.andrePomaleski.SistemaEstoque.model.Product;
import com.andrePomaleski.SistemaEstoque.model.Supplier;
import com.andrePomaleski.SistemaEstoque.repository.CategoryRepository;
import com.andrePomaleski.SistemaEstoque.repository.ProductRepository;
import com.andrePomaleski.SistemaEstoque.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // Map each Product entity to a ProductDTO
        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getCategories().stream().map(Category::getId).toList(),
                        product.getSupplier().getId()
                )).toList();
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
    }

    public Product createProduct(ProductRequestDTO productRequestDTO) throws CategoryNotFoundException, SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(productRequestDTO.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado"));

        List<Category> categories = categoryRepository.findAllById(productRequestDTO.getCategoryIds());
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("Nenhuma categoria encontrada");
        }

        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setPrice(productRequestDTO.getPrice());
        product.setSupplier(supplier);
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) throws ProductNotFoundException, SupplierNotFoundException, CategoryNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        Supplier supplier = supplierRepository.findById(productRequestDTO.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException("Fornecedor não encontrado"));

        List<Category> categories = categoryRepository.findAllById(productRequestDTO.getCategoryIds());
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("Nenhuma categoria encontrada");
        }
        if (productRequestDTO.getName() != null) {
            product.setName(productRequestDTO.getName());
        }
        if (productRequestDTO.getQuantity() != null) {
            product.setQuantity(productRequestDTO.getQuantity());
        }
        if (productRequestDTO.getPrice() != null) {
            product.setPrice(productRequestDTO.getPrice());
        }

        product.setSupplier(supplier);
        product.setCategories(categories);

        return productRepository.save(product);
    }


    public void deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        productRepository.delete(product);
    }
}
