package me.ifight.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("分页对象")
public class PageVO<T> {
    @ApiModelProperty(value = "当前页号")
    private int pageNum;
    @ApiModelProperty(value = "每页的数量")
    private int pageSize;
    @ApiModelProperty(value = "总记录数")
    private long total;
    @ApiModelProperty(value = "总页数")
    private long pages;
    @ApiModelProperty(value = "结果集")
    private List<T> list;

    public PageVO(int pageNum, int pageSize, long total, long pages, List<T> list){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
