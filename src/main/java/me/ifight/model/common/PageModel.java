package me.ifight.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页查询对象")
public class PageModel {
    @ApiModelProperty(value = "当前页号")
    private int pageNum;
    @ApiModelProperty(value = "每页的数量")
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int offset(){
        return (this.pageNum - 1) * this.pageSize;
    }
}
