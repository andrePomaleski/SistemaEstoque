package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Product;

import java.util.List;

public record CategoryRequestDTO(String name, String description, List<Product> productList){
}
