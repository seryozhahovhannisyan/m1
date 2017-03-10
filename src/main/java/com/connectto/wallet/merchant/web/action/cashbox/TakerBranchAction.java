package com.connectto.wallet.merchant.web.action.cashbox;

import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxTakerManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serozh on 6/26/2016.
 */
public class TakerBranchAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(TakerBranchAction.class.getSimpleName());
    private IBranchCashBoxTakerManager cashBoxTakerManager;

    private ResponseDto dto;
    //
    private String branchIdes;
    private String amount;
    private String currencyType;

    private Double takeAmount;
    private CurrencyType takeCurrencyType;
    private Set<Long> takeIdes;

    public String takeBack() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long cashierId = cashier.getId();
        Long companyId = cashier.getCompanyId();

        try {
            convert();
            cashBoxTakerManager.add(cashierId, companyId, takeAmount, takeCurrencyType, takeIdes);
            session.put(INFO, "Ok");
        } catch (InternalErrorException e) {
            session.put(ERROR, e.getMessage());
            writeLog(TakerBranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            return ERROR;
        } catch (PermissionDeniedException e) {
            String msg = getText(e.getMessage());
            session.put(ERROR, msg);
            writeLog(TakerBranchAction.class.getSimpleName(), null, LogLevel.ERROR, LogAction.READ, msg);
            return ERROR;
        } catch (IllegalArgumentException e) {
            session.put(ERROR, e.getMessage());
            writeLog(TakerBranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            return ERROR;
        }
        return SUCCESS;
    }

    private synchronized void convert() throws IllegalArgumentException {

        if (Utils.isEmpty(amount)) {
            String msg = getText("wallet.back.end.message.empty.amount");
            throw new IllegalArgumentException(msg);
        }

        if (Utils.isEmpty(currencyType)) {
            String msg = getText("wallet.back.end.message.empty.currencyType");
            throw new IllegalArgumentException(msg);
        }

        if (Utils.isEmpty(branchIdes)) {
            String msg = getText("provider.empty.branchIdes");
            throw new IllegalArgumentException(msg);
        }

        try {
            takeAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.currencyType") + " ," + getText("wallet.payment.label.Amount") + "=" + amount;
            throw new IllegalArgumentException(msg);
        }

        try {
            int type = Integer.parseInt(currencyType);
            takeCurrencyType = CurrencyType.valueOf(type);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.incorrect.currencyType") + " ," + getText("wallet.back.end.message.currencyType") + "=" + currencyType;
            throw new IllegalArgumentException(msg);
        }

        String[] ides = branchIdes.split(",");
        takeIdes = new HashSet<Long>();
        for (String id : ides) {
            try {
                takeIdes.add(Long.parseLong(id));
            } catch (NumberFormatException e) {
                String msg = getText("provider.empty.branchIdes");
                throw new IllegalArgumentException(msg);
            }

        }
    }

    /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public ResponseDto getDto() {
        return dto;
    }

    public void setBranchIdes(String branchIdes) {
        this.branchIdes = branchIdes;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setCashBoxTakerManager(IBranchCashBoxTakerManager cashBoxTakerManager) {
        this.cashBoxTakerManager = cashBoxTakerManager;
    }
}