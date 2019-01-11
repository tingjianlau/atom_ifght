package me.ifight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 网站信息
 * @author: tjliu
 * @create: 2019-01-10 21:50
 **/
@ApiModel(description = "网站信息")
public class WebSiteInfoVo {
    @ApiModelProperty("网站域名")
    private String domain;
    @ApiModelProperty("网站简介")
    private String intro;
    @ApiModelProperty("网站名称")
    private String name;
    @ApiModelProperty("网址标题")
    private String title;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
