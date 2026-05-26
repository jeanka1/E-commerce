package com.E_commerce.order.model;

import com.E_commerce.orderItems.model.OrderItems;
import com.E_commerce.user.model.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="orders", schema = "main")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private Date creationDate;
    private Date receivedDate;

    private double total;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "order")
    private OrderItems orderItems;

    public Order() {
    }

    public Order(Integer id, String number, Date creationDate, Date receivedDate, double total) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.receivedDate = receivedDate;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUse() {
        return user;
    }

    public void setUse(User use) {
        this.user = use;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", receivedDate=" + receivedDate +
                ", total=" + total +
                '}';
    }
}
