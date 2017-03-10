package com.connectto.wallet.merchant.web.action.dto;


public enum ResponseStatus {

    SUCCESS             (1, "success"),
    INTERNAL_ERROR      (2, "error"),
    DATA_NOT_FOUND      (3, "the data not found"),
    INVALID_PARAMETER   (4, "not allowed like parameter"),
    RESOURCE_NOT_FOUND  (5, "the resource not found"),
    PERMISSION_DENIED   (6, "not allowed the action"),
    WALLET_DENIED       (7, "wallet api response denied");



    private ResponseStatus(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public static ResponseStatus valueOf(final int value){

        for(ResponseStatus status :ResponseStatus.values()){
            if(status.getValue() == value){
                return status;
            }
        }

        return null;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    private int value;
    private String title;

}
