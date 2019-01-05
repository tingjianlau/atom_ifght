package me.ifight.model;

import java.util.List;

public class BookBean {
    private String id;
    private String name;
    private String link;
    private String imageUrl;
    private String introduction;
    private String author;
    private String category;
    private double score;
    private int count;
    private String baiduLink;
    private String baiduPwd;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getBaiduLink() {
        return baiduLink;
    }

    public void setBaiduLink(String baiduLink) {
        this.baiduLink = baiduLink;
    }

    public String getBaiduPwd() {
        return baiduPwd;
    }

    public void setBaiduPwd(String baiduPwd) {
        this.baiduPwd = baiduPwd;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
