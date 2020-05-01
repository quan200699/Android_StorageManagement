package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private int id;
    private String warehouseId;
    private String name;
    private String address;

    public Warehouse(String warehouseId, String name, String address) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
    }
}
