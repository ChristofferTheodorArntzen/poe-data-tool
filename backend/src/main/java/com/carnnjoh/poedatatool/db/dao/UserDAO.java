package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.model.User;

public interface UserDAO extends DAO<User> {

    public User getLastCreatedUser();

}
