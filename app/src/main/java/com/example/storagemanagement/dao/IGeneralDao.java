package com.example.storagemanagement.dao;

import java.util.List;

public interface IGeneralDao<T> {
    T insert(T t);

    T findById(int id);

    List<T> findAll();

    boolean removeById(int id);

    boolean updateById(int id, T t);
}
