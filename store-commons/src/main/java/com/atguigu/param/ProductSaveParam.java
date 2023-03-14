package com.atguigu.param;

import com.atguigu.pojo.Product;
import lombok.Data;

/**
 * 商品数据保存param
 */
@Data
public class ProductSaveParam extends Product {

    //保存商品详情图片的地址，图片之间用+号拼接
    private  String pictures;
}
