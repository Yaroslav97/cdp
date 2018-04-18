package com.epam.cdp.dao;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> {

    /**
     * Save entry
     * @param var1 object
     * @return object
     */
    <S extends T> S save(S var1);

    /**
     * Update entry
     * @param var1 object
     * @return object
     */
    <S extends T> S update(S var1);

    /**
     * Find by id
     * @param var1 id
     * @return object
     */
    T findOne(ID var1);

    /**
     * Delete entry by id
     * @param var1 id
     */
    void delete(ID var1);

    /**
     * Find all entries
     * @return entries
     */
    Iterable<T> findAll();
}
