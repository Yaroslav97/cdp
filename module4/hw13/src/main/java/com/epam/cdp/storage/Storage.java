package com.epam.cdp.storage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Storage<T, ID extends Serializable> {

    private StorageUtil storageUtil;

    private Map<ID, T> map = new ConcurrentHashMap<>();

    public T update(ID id, T entity) {
        return map.put(id, entity);
    }

    public T findOne(Long id) {
        return map.get(id);
    }

    public void delete(Long id) {
        map.remove(id);
    }

    public void patchUpdate(Map<ID, T> patch) {
        map.putAll(patch);
    }

    public Iterable<T> findAll() {
        return new ArrayList<>(map.values());
    }

}
