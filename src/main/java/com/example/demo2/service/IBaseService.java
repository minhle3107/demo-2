package com.example.demo2.service;

import java.util.List;

public interface IBaseService<T, U> {

    List<T> getAll();

    long create(U u);

    T update(long id, U u);

    long delete(long id);

    T getById(long id);

}
