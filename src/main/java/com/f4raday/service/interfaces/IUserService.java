package com.f4raday.service.interfaces;

import com.f4raday.model.User;

public interface IUserService {
    void save(User user);

    User findByUsername(String username);
}
