package com.example.storagemanagement.dao.customer;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.Customer;

public interface ICustomerDao extends IGeneralDao<Customer> {
    Customer findByName(String name);
}
