package com.epam.cdp.dao;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> {

    <S extends T> S save(S var1);

    T findOne(ID var1);

    void delete(ID var1);

    void delete(T var1);

    Iterable<T> findAll();
}
