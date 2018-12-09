package me.ifight.model.common;


/**
 * API 统一返回状态码
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "Request is successful"),
    FAIL(1, "Request is failed");

    private Integer code;
    private String message;
    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }
}
