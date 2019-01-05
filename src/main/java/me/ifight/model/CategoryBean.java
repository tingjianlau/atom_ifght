package me.ifight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "收藏类别")
public class CategoryBean {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("主键")
    private int id;
    @ApiModelProperty("类别名称")
    private String categoryName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
