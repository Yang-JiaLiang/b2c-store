package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

public interface CategoryService {
    /**
     * 后台类别分页查询
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 后台类别新增分类
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 后台类别删除分类
     * @param category
     * @return
     */
    R remove(Category category);

    /**
     * 后台类别修改分类
     * @param category
     * @return
     */
    R update(Category category);
}
