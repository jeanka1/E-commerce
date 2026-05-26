package com.E_commerce.category.service;

import com.E_commerce.category.model.Category;
import com.E_commerce.product.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    public Category save(Category category);
    public Optional<Category> get(Integer id);
    public void update(Category category);
    public void delete(Integer id);
}
