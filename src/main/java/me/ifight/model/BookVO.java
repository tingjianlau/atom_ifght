package me.ifight.model;

import java.util.List;

public class BookVO {
    private String id;
    private String name;
    private String link;
    private String imageUrl;
    private String introduction;
    private String author;
    private String category;
    private double douBanScore;
    private int douBanCount;
    private String baiduNetDiskLink;
    private String baiduNetDiskPwd;
    private List<String> tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDouBanScore() {
        return douBanScore;
    }

    public void setDouBanScore(double douBanScore) {
        this.douBanScore = douBanScore;
    }

    public String getBaiduNetDiskLink() {
        return baiduNetDiskLink;
    }

    public void setBaiduNetDiskLink(String baiduNetDiskLink) {
        this.baiduNetDiskLink = baiduNetDiskLink;
    }

    public String getBaiduNetDiskPwd() {
        return baiduNetDiskPwd;
    }

    public void setBaiduNetDiskPwd(String baiduNetDiskPwd) {
        this.baiduNetDiskPwd = baiduNetDiskPwd;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public int getDouBanCount() {
        return douBanCount;
    }

    public void setDouBanCount(int douBanCount) {
        this.douBanCount = douBanCount;
    }
}
