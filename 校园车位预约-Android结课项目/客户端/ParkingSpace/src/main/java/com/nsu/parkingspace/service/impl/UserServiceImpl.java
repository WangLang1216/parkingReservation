package com.nsu.parkingspace.service.impl;

import com.nsu.parkingspace.mapper.UserMapper;
import com.nsu.parkingspace.model.UserModel;
import com.nsu.parkingspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel queryUser(UserModel userModel) {
        return userMapper.queryUser(userModel);
    }
}
