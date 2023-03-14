package com.atguigu.admin.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录保护拦截器：检查session中是否有数据，有则放行，没有则跳转到登录
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo != null) {
            //放行
            return true;
        }else{
            //否则重定向到登录页面（index.html会跳转到login）
            response.sendRedirect(request.getContextPath()+"/index.html");

            return false;
        }

    }
}
