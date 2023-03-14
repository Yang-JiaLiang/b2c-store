package com.atguigu.admin.controller;


import com.atguigu.admin.param.AdminUserParam;
import com.atguigu.admin.pojo.AdminUser;
import com.atguigu.admin.service.AdminUserService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return R.fail("核心参数为null，登录失败");
        }
        //验证码校验
        String captcha = (String) session.getAttribute("captcha");
        if(!adminUserParam.getVerCode().equalsIgnoreCase(captcha)) {
            return R.fail("验证码错误");
        }
     AdminUser user =adminUserService.login(adminUserParam);
        if (user==null) {
            return R.fail("登录失败！账号或密码错误！");
        }
        //为什么存session，因为前端页面要取。并且key是固定的 userInfo,因为前端写死了
        session.setAttribute("userInfo",user);
        return R.ok("登录成功！");
    }

    /**
     * 实现退出登录（前端已实现页面跳转）
     * @param session
     * @return
     */
    @GetMapping("/user/logout")
    public R logout(HttpSession session) {
        //清空session中全部数据
        session.invalidate();

        return R.ok("退出登录成功！");
    }

}
