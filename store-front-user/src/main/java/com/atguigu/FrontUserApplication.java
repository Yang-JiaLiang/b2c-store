package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.user.mapper")
public class FrontUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontUserApplication.class,args);
    }
}
