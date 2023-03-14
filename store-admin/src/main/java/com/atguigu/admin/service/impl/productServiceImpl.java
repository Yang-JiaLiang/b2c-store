package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.ProductService;
import com.atguigu.clients.ProductClient;
import com.atguigu.clients.SearchClient;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class productServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;
    /**
     * 全部商品查询和搜索查询的方法
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R search = searchClient.search(productSearchParam);
        log.info("productServiceImpl.search业务结束,结果:{}",search);
        return search;
    }

    /**
     * 进行商品数据保存
     * @param productSaveParam
     * @return
     */
    @Override
    public R save(ProductSaveParam productSaveParam) {
        R r = productClient.adminSave(productSaveParam);
        log.info("productServiceImpl.save业务结束,结果:{}",r);
        return r;
    }

    /**
     * 进行商品数据更新
     *
     * @param product
     * @return
     */
    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("productServiceImpl.update业务结束,结果:{}",r);
        return r;
    }

    /**
     * 进行商品数据删除
     *
     * @param productId
     * @return
     */
    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("productServiceImpl.remove业务结束,结果:{}",r);
        return r;
    }
}
