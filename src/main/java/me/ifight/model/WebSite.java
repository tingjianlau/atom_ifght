package me.ifight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 站点信息，爬自站长之家
 * @author: tjliu
 * @create: 2019-01-08 22:33
 **/

@ApiModel(description = "网站信息")
public class WebSite {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("域名")
    private String domain;
    @ApiModelProperty("网站名称")
    private String name;
    @ApiModelProperty("Alexa排名")
    private int alexa;
    @ApiModelProperty("网站简介")
    private String intro;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlexa() {
        return alexa;
    }

    public void setAlexa(int alexa) {
        this.alexa = alexa;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
