package com.atguigu.admin.controller;

import com.atguigu.admin.service.UserService;
import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 普通用户调用的controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public R userList(PageParam pageParam) {
        return userService.userList(pageParam);
    }

    @PostMapping("/remove")
    public R userRemove(CartListParam cartListParam) {
        return userService.userRemove(cartListParam);
    }

    @PostMapping("/update")
    public R userUpdate(User user) {
        return userService.userUpdate(user);
    }

    @PostMapping("/save")
    public R userSave(User user) {
        return userService.userSave(user);
    }


}
