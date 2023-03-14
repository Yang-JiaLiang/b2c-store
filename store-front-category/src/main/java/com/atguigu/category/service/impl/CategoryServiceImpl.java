package com.atguigu.category.service.impl;

import com.atguigu.category.mapper.CategoryMapper;
import com.atguigu.category.service.CategoryService;
import com.atguigu.param.PageParam;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据类别名称，查询类别对象
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        if(category==null) {
            log.info("CategoryServiceImpl.byName业务结束，结果:类别查询失败");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果:{}","类别查询成功");
        return R.ok("类别查询成功",category);
    }

    /**
     * 根据热门类别名称集合，返回类别id集合
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",  productHotParam.getCategoryName());
        queryWrapper.select("category_id");

        List<Object> ids = categoryMapper.selectObjs(queryWrapper);
        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}",ok);
        return ok;
    }

    /**
     * 查询类别数据，进行返回
     * @return  类别数据集合
     */
    @Override
    public R list() {
        //查询全部，没有参数，所以传参为null
        List<Category> categoryList = categoryMapper.selectList(null);
        R ok = R.ok("类别全部数据查询成功!", categoryList);
        log.info("CategoryServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }

    /**
     * 后台界面的分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {

        Page<Category>  page= new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        page = categoryMapper.selectPage(page,null);
        return R.ok("类别分页数据查询成功！",page.getRecords(),page.getTotal());

    }

    /**
     * 后台界面新增类别
     *
     * @param category
     * @return
     */
    @Override
    public R adminSave(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);
        if (count>0) {
            log.info("CategoryServiceImpl.adminSave业务结束,结果:{}","该分类已存在，添加失败！");
            return R.fail("该分类已存在，添加失败！");
        }
        int insert = categoryMapper.insert(category);
        log.info("CategoryServiceImpl.adminSave业务结束,结果:{}",insert);
        return R.ok("分类添加成功！");
    }

    /**
     * 后台界面删除类别
     *
     * @param category
     * @return
     */
    @Override
    public R adminRemove(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", category.getCategoryId());
        int delete = categoryMapper.delete(queryWrapper);
        if (delete==0) {
            log.info("CategoryServiceImpl.adminRemove业务结束,结果:{}","不存在该类别，删除失败！");
            return R.fail("不存在该类别，删除失败！");
        }
        log.info("CategoryServiceImpl.adminRemove业务结束,结果:{}",delete);
        return R.ok("分类删除成功！");
    }

    /**
     * 后台界面修改类别
     *
     * @param category
     * @return
     */
    @Override
    public R adminUpdate(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);
        if (count>0) {
            return R.fail("该类别已存在，修改失败！");
        }
        int update = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminRemove业务结束,结果:{}",update);
        return R.ok("分类删除成功！");
    }
}
