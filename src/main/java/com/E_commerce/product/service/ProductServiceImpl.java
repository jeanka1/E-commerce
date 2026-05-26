package com.E_commerce.product.service;

import com.E_commerce.product.model.Product;
import com.E_commerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);

    }
}
