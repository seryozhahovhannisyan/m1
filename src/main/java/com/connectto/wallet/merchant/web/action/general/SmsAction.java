package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.common.exception.HttpConnectionDeniedException;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.util.notification.SMSNotification;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.log4j.Logger;

/**
 * Created by htdev01 on 11/9/15.
 */
public class SmsAction  extends BaseAction {

    private static final Logger logger = Logger.getLogger(SmsAction.class.getSimpleName());
    private ResponseDto responseDto;

    private String message;
    private String phone;

    public String execute(){
        try{
            String smsResponse = SMSNotification.sendCustomSMS("Merchant",
                    Initializer.getSetupInfo().notificationSmsUsername, Initializer.getSetupInfo().notificationSmsPassword,
                    message, phone);
            logger.info(smsResponse);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setActionmessage("sms.success");
        }catch (HttpConnectionDeniedException e){
            logger.error(e);
            responseDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.setActionmessage(getText("error.internal"));
        }

        return SUCCESS;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }



}
