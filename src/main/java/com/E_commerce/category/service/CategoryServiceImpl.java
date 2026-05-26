package com.E_commerce.category.service;

import com.E_commerce.category.model.Category;
import com.E_commerce.category.repository.CategoryRepository;
import com.E_commerce.product.model.Product;
import com.E_commerce.product.repository.ProductRepository;
import com.E_commerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> get(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void update(Category category) {
       categoryRepository.save(category);

    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);

    }
}
