package me.ifight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "网站")
public class SiteBean {
    @ApiModelProperty("主键")
    private int id;
    @ApiModelProperty("网站主页地址")
    private String siteUrl;
    @ApiModelProperty("网站名称")
    private String siteName;
    @ApiModelProperty("添加该网站的用户")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
