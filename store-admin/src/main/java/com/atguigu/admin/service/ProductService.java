package com.atguigu.admin.service;

import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;

public interface ProductService {
    /**
     * 全部商品查询和搜索查询的方法
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 进行商品数据保存
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam);

    /**
     * 进行商品数据更新
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 进行商品数据删除
     * @param productId
     * @return
     */
    R remove(Integer productId);
}
