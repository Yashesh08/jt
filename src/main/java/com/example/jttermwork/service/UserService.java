package com.example.jttermwork.service;

import com.example.jttermwork.dto.UserRequest;
import com.example.jttermwork.entity.User;

import java.util.List;

public interface UserService {
    User create(UserRequest request);
    List<User> getAll();
    User getById(Long id);
    User update(Long id, UserRequest request);
    void delete(Long id);
}
