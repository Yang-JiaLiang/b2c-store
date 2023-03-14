package com.atguigu.param;

import com.atguigu.vo.CartVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;
import java.util.List;

/**
 * 订单接收参数的param
 */
@Data
public class OrderParam implements Serializable {
    public static final Long  SerialVersionUID =1L;

    @JsonProperty("user_id")
    private Integer userId;

    private List<CartVo> products;

}
