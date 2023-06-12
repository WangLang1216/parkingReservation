package com.nsu.parkingspace.service;

import java.util.List;

public interface PositionService {
    public List<String> queryAllFreePosition();

    //更新车位状态
    public int updatePosition(String place ,String status);
}
