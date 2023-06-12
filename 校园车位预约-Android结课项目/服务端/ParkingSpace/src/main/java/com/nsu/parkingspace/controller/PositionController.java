package com.nsu.parkingspace.controller;

import com.nsu.parkingspace.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    // 查看空闲车位
    @GetMapping("/queryAllFreePosition")
    public List<String> queryAllFreePosition(){
        List<String> list = positionService.queryAllFreePosition();
        return list;
    }

    // 更新车位状态信息
    @GetMapping("/updatePosition")
    public int updatePosition(String place, String status){
        System.out.println(place + "," + status);
        int r = positionService.updatePosition(place, status);
        return r;
    }
}
