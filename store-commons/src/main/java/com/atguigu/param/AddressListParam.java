package com.atguigu.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressListParam {
    @NotNull
    @JsonProperty("user_id") //使用这个注解后,在postman测试时，键用user_id，不能用userId
    private Integer userId;
}
