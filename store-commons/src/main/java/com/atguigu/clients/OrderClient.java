package com.atguigu.clients;

import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("order/admin/list")
    R list(@RequestBody PageParam pageParam);
}
