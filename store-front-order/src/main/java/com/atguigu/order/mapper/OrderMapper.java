package com.atguigu.order.mapper;

import com.atguigu.pojo.Order;
import com.atguigu.vo.AdminOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询后台的订单数据
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}
