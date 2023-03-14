package com.atguigu.product.controller;

import com.atguigu.pojo.Product;
import com.atguigu.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 搜索服务调用的controller（搜索服务在SpringbootListenrer调用过allList（）方法）
 */

@RestController
@RequestMapping("/product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> allList() {

        return productService.allList();
    }
}
