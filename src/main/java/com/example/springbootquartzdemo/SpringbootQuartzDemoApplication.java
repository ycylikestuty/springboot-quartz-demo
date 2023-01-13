package com.example.springbootquartzdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.springbootquartzdemo.mapper")
public class SpringbootQuartzDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuartzDemoApplication.class, args);
    }

}
