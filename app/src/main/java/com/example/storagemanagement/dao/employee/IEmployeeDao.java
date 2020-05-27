package com.example.storagemanagement.dao.employee;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.Employee;

public interface IEmployeeDao extends IGeneralDao<Employee> {
    Employee findByName(String name);
}
