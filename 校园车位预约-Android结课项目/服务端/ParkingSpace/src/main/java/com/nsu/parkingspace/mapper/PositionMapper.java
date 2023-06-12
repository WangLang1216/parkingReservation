package com.nsu.parkingspace.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PositionMapper {
    //查询所有空闲车位
    public List<String> queryAllFreePosition();

    //修改车位信息
    public int updatePosition(String place, String status);
}
