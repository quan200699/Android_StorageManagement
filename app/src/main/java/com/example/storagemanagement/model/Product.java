package com.example.storagemanagement.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String productId;
    private String name;
    private String description;
    private int guarantee;
}
