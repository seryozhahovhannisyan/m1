package com.connectto.wallet.merchant.web.action.util;

import java.util.List;

/**
 * User: Garik
 * Date: 6/16/16
 * Time: 11:14 PM
 */
public class ListAjaxResponse extends AjaxResponse {

    private List<?> data;
    private long dataCount;

    public ListAjaxResponse(String status, String error) {
        super(status, error);
    }
    public ListAjaxResponse(String status, List<?> data) {
        this.status = status;
        this.data = data;
        this.dataCount = -1;
    }
    public ListAjaxResponse(String status, List<?> data, long dataCount) {
        this.status = status;
        this.data = data;
        this.dataCount = dataCount;
    }

    public List<?> getData() {
        return data;
    }

    public long getDataCount() {
        return dataCount;
    }
}
