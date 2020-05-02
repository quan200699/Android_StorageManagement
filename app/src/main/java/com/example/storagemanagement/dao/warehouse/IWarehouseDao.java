package com.example.storagemanagement.dao.warehouse;

import com.example.storagemanagement.dao.IGeneralDao;
import com.example.storagemanagement.model.Warehouse;

public interface IWarehouseDao extends IGeneralDao<Warehouse> {
    Warehouse findByName(String name);
}
