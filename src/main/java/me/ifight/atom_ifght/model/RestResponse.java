package me.ifight.atom_ifght.model;

import me.ifight.atom_ifght.utils.StringUtils;

import java.util.List;

public class RestResponse {
    private int resultCode;
    private String resultMessage;
    private Object result;
    private static final String SUCCESS = "Request is successful";
    private static final String FAIL = "Request is failed";

    public static RestResponse succuess(Object result){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(0);
        restResponse.setResultMessage(SUCCESS);

        if(result instanceof List){
            RestResult restResult = new RestResult();
            List responseList = (List) result;

            restResult.setTotal(responseList.size());
            restResult.setData(responseList);

            restResponse.setResult(restResult);
        }else {
            restResponse.setResult(result);
        }

        return restResponse;
    }

    public static RestResponse fail(String errorMsg) {
        RestResponse restResponse = new RestResponse();

        restResponse.setResultCode(1);
        restResponse.setResultMessage(FAIL);

        if(StringUtils.isNotEmpty(errorMsg)){
            restResponse.setResultMessage(errorMsg);
        }

        return restResponse;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
