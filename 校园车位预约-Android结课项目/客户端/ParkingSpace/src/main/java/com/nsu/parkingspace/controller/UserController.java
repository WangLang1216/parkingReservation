package com.nsu.parkingspace.controller;

import com.nsu.parkingspace.model.UserModel;
import com.nsu.parkingspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 用户登录
    @PostMapping("/queryUser")
    public R<UserModel> queryUser(String account, String password){
        UserModel userModel = new UserModel();
        userModel.setAccount(account);
        userModel.setPassword(password);
        UserModel userModel1 = userService.queryUser(userModel);
        return R.success(userModel1);
    }
}
