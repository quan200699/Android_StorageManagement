package com.example.storagemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String employeeId;
    private String name;
    private String birthday;
    private String sex;
    private String address;

    public Employee(String employeeId, String name, String birthday, String sex, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }
}
