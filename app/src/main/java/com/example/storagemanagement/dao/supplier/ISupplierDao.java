package com.example.storagemanagement.dao.supplier;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.Supplier;

public interface ISupplierDao extends IGeneralDao<Supplier> {
    Supplier findByName(String name);

}
