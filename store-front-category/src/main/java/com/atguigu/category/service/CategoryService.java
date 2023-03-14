package com.atguigu.category.service;

import com.atguigu.param.PageParam;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

public interface CategoryService {

    /**
     * 根据类别名称，查询类别对象
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据热门类别名称集合，返回类别id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据，进行返回
     * @return  类别数据集合
     */
    R list();

    /**
     * 后台界面的分页查询
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 后台界面新增类别
     * @param category
     * @return
     */
    R adminSave(Category category);

    /**
     *后台界面删除类别
     * @param category
     * @return
     */
    R adminRemove(Category category);

    /**
     * 后台界面修改类别
     * @param category
     * @return
     */
    R adminUpdate(Category category);
}
