package com.atguigu.param;

import lombok.Data;

/**
 * 分页的属性
 */

@Data
public class PageParam {
    private int currentPage =1;
    private int pageSize =15;

    /**
     * 运算分页起始值
     * @return
     */
    public int getFrom(){
        return (currentPage-1)*pageSize;
    }

    /**
     * 返回查询值
     * @return
     */
    public int getSize(){
        return pageSize;
    }
}
