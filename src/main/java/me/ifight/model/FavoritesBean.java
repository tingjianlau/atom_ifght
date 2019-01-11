package me.ifight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "收藏夹")
public class FavoritesBean {
    @ApiModelProperty("Id")
    private int id;
    @ApiModelProperty("收藏网站")
    private String siteUrl;
    @ApiModelProperty("网址标题")
    private String siteTitle;
    @ApiModelProperty("收藏时间")
    private String addTime;
    @ApiModelProperty("类型")
    private int catId;
    @ApiModelProperty("类型")
    private String category;
    @ApiModelProperty("是否已读")
    private boolean colsed;
    @ApiModelProperty("点击次数")
    private int clickCount;
    @ApiModelProperty("上次点击时间")
    private String updateTime;
    @ApiModelProperty("优先级")
    private int level;
    @ApiModelProperty("用户")
    private String userName;
    @ApiModelProperty("置顶")
    private String topping;
    @ApiModelProperty("webSite信息")
    private WebSite webSite;

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isColsed() {
        return colsed;
    }

    public void setColsed(boolean colsed) {
        this.colsed = colsed;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }
}
