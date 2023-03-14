package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.UserService;
import com.atguigu.clients.UserClient;
import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

   @Autowired
   private UserClient userClient;
    /**
     * 用户的展示业务方法
     *
     * @param pageParam
     * @return
     */
    @Cacheable(value = "list.user",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {
        log.info("UserServiceImpl.userList业务开始，参数：{}",pageParam);
        R r = userClient.adminListPage(pageParam);
        log.info("UserServiceImpl.userList业务结束，结果：{}",r);
        return r;
    }

    /**
     * 删除用户
     * @param cartListParam
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userRemove(CartListParam cartListParam) {
        R r = userClient.adminRemove(cartListParam);
        log.info("UserServiceImpl.userRemove业务结束,结果:{}",r);
        return r;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userUpdate(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.userUpdate业务结束,结果:{}",r);
        return R.ok("修改用户信息成功！");
    }

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userSave(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.userSave业务结束,结果:{}",r);
        return R.ok("新增用户信息成功！");
    }
}
