package com.nsu.parkingspace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Client {
    public static void main(String[] args) {
        SpringApplication.run(Client.class,args);
        log.info("这是parkingSpace启动页面");
    }
}
