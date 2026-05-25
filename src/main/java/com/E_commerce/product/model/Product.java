package com.E_commerce.product.model;

import com.E_commerce.category.model.Category;
import com.E_commerce.user.model.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products", schema = "main")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String imagen;
    private double price;
    private int quantity;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(UUID id, String name, String description, String imagen, double price, int quantity, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagen = imagen;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagen='" + imagen + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
