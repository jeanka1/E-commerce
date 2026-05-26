package com.E_commerce.product.service;

import com.E_commerce.product.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    public Product save(Product product);
    public Optional<Product> get(UUID id);
    public void update(Product product);
    public void delete(UUID id);
}
