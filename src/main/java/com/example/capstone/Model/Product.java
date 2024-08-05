package com.example.capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "ID cannot be empty")
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name size must be between 3 - 20")
    private String name;
    @NotNull(message = "Price cannot be empty")
@Positive
    private double price;
    @NotNull(message = "Category ID cannot be empty")
    private int categoryId;

    private List<Comment> comments = new ArrayList<>();
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }





    // Copy constructor
    public Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
    }



}
