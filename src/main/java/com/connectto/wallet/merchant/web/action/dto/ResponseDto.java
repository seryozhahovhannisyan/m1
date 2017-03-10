package com.connectto.wallet.merchant.web.action.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 12/6/2014.
 */
public class ResponseDto {

    private ResponseStatus responseStatus;

    private String actionerror;
    private String actionmessage;

    private Map<String,String> fielderrors;

    private Map<String,Object> response;

    public void addResponse(String key, Object value) {
        if(response == null || response.size() == 0 ){
            response = new HashMap<String, Object>();
        }
        response.put(key,value);
    }

    public void addFieldError(String name, String error) {
        if(fielderrors == null || fielderrors.size() == 0 ){
            fielderrors = new HashMap<String, String>();
        }
        fielderrors.put(name,error);
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getActionerror() {
        return actionerror;
    }

    public void setActionerror(String actionerror) {
        this.actionerror = actionerror;
    }

    public String getActionmessage() {
        return actionmessage;
    }

    public void setActionmessage(String actionmessage) {
        this.actionmessage = actionmessage;
    }

    public Map<String, String> getFielderrors() {
        return fielderrors;
    }

    public void setFielderrors(Map<String, String> fielderrors) {
        this.fielderrors = fielderrors;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }
}
