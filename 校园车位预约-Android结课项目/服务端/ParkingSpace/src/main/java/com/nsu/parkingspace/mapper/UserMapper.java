package com.nsu.parkingspace.mapper;

import com.nsu.parkingspace.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {
    //查询是否存在账号
    UserModel queryUser(UserModel userModel);
}
