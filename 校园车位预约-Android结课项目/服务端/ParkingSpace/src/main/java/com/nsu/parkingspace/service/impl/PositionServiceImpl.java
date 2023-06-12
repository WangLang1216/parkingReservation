package com.nsu.parkingspace.service.impl;

import com.nsu.parkingspace.mapper.PositionMapper;
import com.nsu.parkingspace.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<String> queryAllFreePosition() {
        return positionMapper.queryAllFreePosition();
    }

    //更新车位状态
    @Override
    public int updatePosition(String place, String status) {
        return positionMapper.updatePosition(place, status);
    }
}
