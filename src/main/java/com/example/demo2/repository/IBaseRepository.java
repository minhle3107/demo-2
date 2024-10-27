package com.example.demo2.repository;

import java.util.List;

public interface IBaseRepository <T>{
    List<T> getAll();

    long create(T t);

    T update(long id, T t);

    long delete(long id);

    T getById(long id);
}
