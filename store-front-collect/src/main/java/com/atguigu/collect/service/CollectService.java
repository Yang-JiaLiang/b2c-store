package com.atguigu.collect.service;

import com.atguigu.pojo.Collect;
import com.atguigu.utils.R;

public interface CollectService {
    /**
     * s收藏添加的方法
     * @param collect
     * @return 001（未收藏返回code） 004（已收藏返回code）
     */
    R save(Collect collect);

    /**
     * 根据用户id 查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 删除收藏接口
     * @param collect
     * @return
     */
    R remove(Collect collect);

    /**
     * 后台根据商品id，删除收藏（删除商品，连带删除收藏）
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
