package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.model.User;
import com.epam.cdp.storage.Storage;
import com.epam.cdp.storage.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserDAO implements CrudRepository<User, Long> {

    private Storage<User, Long> storage;
    private StorageUtil<User, Long> storageUtil;

    public void initStorage() {
        storage.patchUpdate(storageUtil.readCSV(StorageUtil.Model.USER));
    }

    @Autowired
    public void setStorageUtil(StorageUtil<User, Long> storageUtil) {
        this.storageUtil = storageUtil;
    }

    @Autowired
    public void setStorage(Storage<User, Long> storage) {
        this.storage = storage;
    }

    @Override
    public User save(User user) {
        return storage.update(user.getId(), user);
    }

    @Override
    public User update(User user) {
        return storage.update(user.getId(), user);
    }

    @Override
    public User findOne(Long id) {
        return storage.findOne(id);
    }

    @Override
    public void delete(Long id) {
        storage.delete(id);
    }

    @Override
    public Iterable<User> findAll() {
        return storage.findAll();
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> userList = (List<User>) storage.findAll();
        return userList.stream().filter(user -> user.getName().equals(name))
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        List<User> userList = (List<User>) storage.findAll();
        return userList.stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList()).get(0);
    }


}
