package com.connectto.wallet.merchant.web.action.cashbox;

import com.connectto.wallet.merchant.business.merchant.IBranchCashBoxProviderManager;
import com.connectto.wallet.merchant.business.merchant.IBranchManager;
import com.connectto.wallet.merchant.business.merchant.ICashierCashBoxProviderManager;
import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Branch;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.exception.PermissionDeniedException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Serozh on 6/26/2016.
 */
public class ProviderCashierAction extends BaseAction {

    private ICashierManager cashierManager;
    private ICashierCashBoxProviderManager cashierCashBoxProviderManager;

    private ResponseDto dto;

    private String requestJson;
    private int dataCount;
    //
    private String cashierIdes;
    private String amount;
    private String currencyType;

    private Double provideAmount;
    private CurrencyType provideCurrencyType;
    private Set<Long> provideIdes;


    public String list() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Privilege privilege = cashier.getPrivilege();

        if (privilege.getId() != Privilege.CRUD_BRANCH.getId()) {
            dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
            return SUCCESS;
        }

        Long companyId = cashier.getCompanyId();
        Long branchId = cashier.getBranchId();

        try {

            Map<String, Object> params = DataConverter.convertRequestToParams(requestJson);
            params.put("companyId", companyId);
            params.put("branchId", branchId);
            params.put("except", cashier.getId());
            params.put("privileges", new Integer[]{Privilege.CRUD_CASHIER.getId(), Privilege.CASHIER.getId()});
            dataCount = cashierManager.getCountByParams(params);

            long page = (Long) params.get("page");
            long count = (Long) params.get("count");

            params.put("page", (page - 1) * count);
            List<Cashier> cashiers = cashierManager.getByParamsFull(params);

            dto.addResponse("data", cashiers);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(ProviderCashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(ProviderCashierAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String provide() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);

        Privilege privilege = cashier.getPrivilege();

        if (privilege.getId() != Privilege.CRUD_BRANCH.getId()) {
            dto.setResponseStatus(ResponseStatus.PERMISSION_DENIED);
            return SUCCESS;
        }

        Long cashierId = cashier.getId();
        Long branchId = cashier.getBranchId();

        try {
            convert();
            cashierCashBoxProviderManager.add(cashierId, branchId, provideAmount, provideCurrencyType, provideIdes);
            session.put(INFO, "Ok");
        } catch (InternalErrorException e) {
            session.put(ERROR, e.getMessage());
            writeLog(ProviderBranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            return ERROR;
        } catch (PermissionDeniedException e) {
            String msg = getText(e.getMessage());
            session.put(ERROR, msg);
            writeLog(ProviderBranchAction.class.getSimpleName(), null, LogLevel.ERROR, LogAction.READ, msg);
            return ERROR;
        } catch (IllegalArgumentException e) {
            session.put(ERROR, e.getMessage());
            writeLog(ProviderBranchAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
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

        if (Utils.isEmpty(cashierIdes)) {
            String msg = getText("provider.empty.cashierIdes");
            throw new IllegalArgumentException(msg);
        }

        try {
            provideAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.currencyType") + " ," + getText("wallet.payment.label.Amount") + "=" + amount;
            throw new IllegalArgumentException(msg);
        }

        try {
            int type = Integer.parseInt(currencyType);
            provideCurrencyType = CurrencyType.valueOf(type);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.incorrect.currencyType") + " ," + getText("wallet.back.end.message.currencyType") + "=" + currencyType;
            throw new IllegalArgumentException(msg);
        }

        String[] ides = cashierIdes.split(",");
        provideIdes = new HashSet<Long>();
        for (String id : ides) {
            try {
                provideIdes.add(Long.parseLong(id));
            } catch (NumberFormatException e) {
                String msg = getText("provider.empty.cashierIdes");
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

    public int getDataCount() {
        return dataCount;
    }

    public void setCashierIdes(String cashierIdes) {
        this.cashierIdes = cashierIdes;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }


    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }

    public void setCashierCashBoxProviderManager(ICashierCashBoxProviderManager cashierCashBoxProviderManager) {
        this.cashierCashBoxProviderManager = cashierCashBoxProviderManager;
    }
}