package com.connectto.wallet.merchant.web.action.json;

import com.connectto.wallet.merchant.common.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class JsonErrorAction extends ActionSupport {

    private String status;

    private String message;

    public String execute() {
        return SUCCESS;
    }

    public String getStatus() {
        if (Utils.isEmpty(status)) {
            status = ERROR;
            message = getText("err.msg.internal");
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
