package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.CategoryService;
import com.atguigu.clients.CategoryClient;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl  implements CategoryService {

   @Autowired
   private CategoryClient categoryClient;
    /**
     * 后台类别分页查询
     *
     * @param pageParam
     * @return
     */
    @Cacheable(value = "list.category",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {
        R r = categoryClient.adminPageList(pageParam);
        log.info("CategoryServiceImpl.pageList业务结束,结果:{}",r);
        return r;
    }

    /**
     * 后台界面新增分类
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R save(Category category) {
        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save业务结束,结果:{}",r);
        return r;
    }

    /**
     * 后台类别删除分类
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R remove(Category category) {
        R r = categoryClient.adminRemove(category);
        log.info("CategoryServiceImpl.remove业务结束,结果:{}",r);
        return r;
    }

    /**
     * 后台类别修改分类
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update业务结束,结果:{}",r);
        return r;
    }
}
