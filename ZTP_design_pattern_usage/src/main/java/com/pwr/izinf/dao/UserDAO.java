package com.pwr.izinf.dao;

import com.pwr.izinf.domain.User;

import java.util.List;

public interface UserDAO {

    User save(User user);
    User read(String id);
    User update(User user);
    void delete(String id);
    List<User> getList();

}
