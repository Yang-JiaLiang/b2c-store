package com.atguigu.collect.service.impl;

import com.atguigu.clients.ProductClient;
import com.atguigu.collect.mapper.CollectMapper;
import com.atguigu.param.ProductCollectParam;
import com.atguigu.pojo.Collect;
import com.atguigu.collect.service.CollectService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;
    /**
     * s收藏添加的方法
     *
     * @param collect
     * @return 001（未收藏返回code） 004（已收藏返回code）
     */
    @Override
    public R save(Collect collect) {
        //1.先查询是否存在
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());
        Long count = collectMapper.selectCount(queryWrapper);
        if(count>0) {
            return R.fail("收藏已经添加，无需二次添加！");
        }

        //2.不存在则添加,需要补充下时间
        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束，结果:{}",rows);

        return R.ok("收藏添加成功");
    }



    /**
     * 根据用户id 查询商品信息集合
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.select("product_id");

        List<Object> idsObject = collectMapper.selectObjs(queryWrapper);
        ProductCollectParam productCollectParam = new ProductCollectParam();

        ArrayList<Integer> ids = new ArrayList<>();
        for (Object o : idsObject) {
            ids.add((Integer)o);
        }
        productCollectParam.setProductIds(ids);
        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束，结果:{}",r);
        return r;
    }

    /**
     * 删除收藏接口
     *
     * @param collect
     * @return
     */
    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());

        int count = collectMapper.delete(queryWrapper);
            R r = R.ok("删除收藏成功");
            log.info("CollectServiceImpl.remove业务结束，结果:{}",count);
            return r;
    }

    /**
     * 后台根据商品id，删除收藏（删除商品，连带删除收藏）
     *
     * @param productId
     * @return
     */
    @Override
    public R removeByPid(Integer productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        int delete = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.removeByPid业务结束,结果:{}",delete);
        return R.ok("删除收藏商品成功！");
    }
}
