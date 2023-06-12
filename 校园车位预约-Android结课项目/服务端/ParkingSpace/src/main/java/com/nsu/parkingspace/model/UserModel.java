package com.nsu.parkingspace.model;

import lombok.Data;

@Data
public class UserModel {
    private int id;
    private String account;
    private String name;
    private String password;
}
