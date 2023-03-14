package com.atguigu.product.service;

import com.atguigu.param.*;
import com.atguigu.pojo.Product;
import com.atguigu.to.OrderToProduct;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProductService extends IService<Product> {

    /**
     * 单类别名称 查询热门商品 至多7条数据
     * @param categoryName 类别名称
     * @return r
     */
    R promo(String categoryName);


    /**
     * 根据类别名称集合，多类别热门商品查询，至多查询7条
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     * @return
     */
    R clist();

    /**
     * 通用性的业务：
     * 如果传入了类别id，根据id查询并分页
     * 如果没有传入类别id，则查询全部
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);

    /**
     * 根据productID，进行商品详情查询
     * @param productID
     * @return
     */
    R getDetails(Integer  productID);

    /**
     * 根据productID，进行商品图片查询
     * @param productID
     * @return
     */
    R getPictures(Integer  productID);

    /**
     * 搜索服务调用，获取全部商品数据
     * 进行同步
     * @return  商品数据集合
     */
    List<Product> allList();

    /**
     * 搜索业务，需要调用搜索服务
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);


    /**
     * 根据productIds集合，查询商品信息
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);

    /**
     * 根据商品id集合，查询商品集合
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     *修改库存和增加销售量
     * @param orderToProducts
     */
    void subNumber(List<OrderToProduct> orderToProducts);

    /**
     * 后台商品保存业务
     * @param productSaveParam
     * @return
     */
    R adminSave(ProductSaveParam productSaveParam);

    /**
     * 后台商品更新业务
     * @param product
     * @return
     */
    R adminUpdate(Product product);

    /**
     * 后台商品删除业务
     * @param productId
     * @return
     */
    R adminRemove(Integer productId);
}
