package me.ifight.model.weather;

public class WeatherResponse {
    private static final long serialVersionUID = 1L;

    private WeatherVO data; // 消息数据

    private String status; // 消息状态

    private String desc; // 消息描述

    public WeatherVO getData() {
        return data;
    }

    public void setData(WeatherVO data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
