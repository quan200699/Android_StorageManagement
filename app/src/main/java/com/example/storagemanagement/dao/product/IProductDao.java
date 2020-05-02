package com.example.storagemanagement.dao.product;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.Product;

public interface IProductDao extends IGeneralDao<Product> {
    Product findByName(String name);
}
