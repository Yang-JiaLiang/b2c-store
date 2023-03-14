package com.atguigu.user.service;

import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;

public interface UserService {
    /**
     * 检查账号是否可用业务
     * @param userCheckParam 账号参数，已经校验完毕
     * @return 返回结果 001  004
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务：参数已经校验，但是密码是明文
     * @param user
     * @return
     */
    R register(User user);

    /**
     * 登录业务：账号和密码已经校验，但是密码是明文
     * @param userLoginParam
     * @return
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用，查询全部数据
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);


    /**
     * 根据用户id删除数据
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 新增用户数据
     * @param user
     * @return
     */
    R save(User user);
}
