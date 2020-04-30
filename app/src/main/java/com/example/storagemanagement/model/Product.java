package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String productId;
    private String name;
    private String description;
    private int guarantee;

    public Product(String productId, String name, String description, int guarantee) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.guarantee = guarantee;
    }
}
