package com.connectto.wallet.merchant.web.action.util;

/**
 * User: Garik
 * Date: 6/4/16
 * Time: 2:04 AM
 */
public class AjaxResponse {

    public AjaxResponse(){}

    public AjaxResponse(String status, String error){
        this.status = status;
        this.error = error;
    }

    protected String status;

    protected String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
