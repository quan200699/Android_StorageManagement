package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private int id;
    private String supplierId;
    private String name;
    private String address;
    private String email;

    public Supplier ( String supplierId, String name, String address, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}

