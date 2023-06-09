package com.atguigu.carousel.service.impl;

import com.atguigu.carousel.service.CarouselService;
import com.atguigu.carousel.mapper.CarouselMapper;
import com.atguigu.pojo.Carousel;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询优先级最高的6条轮播图数据
     *按照优先级查询数据库数据
     * 我们使用stream流，进行内存数据切割，保留6条数据
     * @return
     */
    @Cacheable(value = "list.carousel",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> carouselQueryWapper = new QueryWrapper<>();
        carouselQueryWapper.orderByDesc("priority");
        List<Carousel> list = carouselMapper.selectList(carouselQueryWapper);
        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());
        R ok = R.ok(collect);
        log.info("CarouselServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }
}
