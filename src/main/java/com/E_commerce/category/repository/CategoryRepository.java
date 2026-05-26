package com.E_commerce.category.repository;

import com.E_commerce.category.model.Category;
import com.E_commerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
