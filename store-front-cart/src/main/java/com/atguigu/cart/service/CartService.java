package com.atguigu.cart.service;
import com.atguigu.param.CartSaveParam;
import com.atguigu.pojo.Cart;
import com.atguigu.utils.R;

import java.util.List;

public interface CartService  {

    /**
     * 添加商品到购物车
     * @param cartSaveParam
     * @return
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 根据用户id，返回购物车数据
     * @param userId
     * @return  确保要返回一个数组，要不然前端报错
     */
    R list(Integer userId);

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应用户的购物车项
     * @param cardIds
     */
    void clearIds(List<Integer> cardIds);

    /**
     * 后台查询购物车项
     * @param productId
     * @return
     */
    R check(Integer productId);
}
