package com.connectto.wallet.merchant.web.action.cashier;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.admin.CompanyFormRequestAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;

/**
 * Created by Serozh on 8/10/2016.
 */
public class ProfileAction extends BaseAction{

    private ResponseDto dto;
    private ICashierManager cashierManager;

    private Long cashierId;
    private Cashier cashier;

    public String viewProfile(){
        try {
            cashier = cashierManager.getProfile(cashierId);
        } catch (InternalErrorException e) {
            writeLog(CompanyFormRequestAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        } catch (EntityNotFoundException e) {
            writeLog(CompanyFormRequestAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        try {
            this.cashierId = Long.parseLong(cashierId);
        } catch (Exception e) {
            writeLog(CompanyFormRequestAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }
}
