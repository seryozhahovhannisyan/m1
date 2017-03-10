package com.connectto.wallet.merchant.web.action.wallet;

import com.connectto.wallet.merchant.business.merchant.ITransactionManager;
import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 7/8/2016.
 */
public class SearchWalletAction extends BaseAction {

    private ResponseDto dto;
    private IWalletManager walletManager;

    private String requestJson;
    private int dataCount;

    public String listView() {
        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        try {
            dataCount = walletManager.getCountByParams(params);
        } catch (InternalErrorException e) {
            writeLog(SearchWalletAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
        }
        return SUCCESS;
    }

    public String list() {

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        Long companyId = cashier.getCompanyId();

        try {

            Map<String, Object> params = DataConverter.convertWalletRequestToParams(requestJson);
            params.put("companyId", companyId);
            dataCount = walletManager.getCountByParams(params);//old 1359 new with availableRateValues is 1340

            long page = (Long) params.get("page");
            long count = (Long) params.get("count");
            params.put("page", (page - 1) * count);

            List<Wallet> wallets = walletManager.getByParams(params);
            dto.addResponse("data", wallets);
            dto.addResponse("dataCount", dataCount);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(SearchWalletAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        } catch (DataParseException e) {
            writeLog(SearchWalletAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.READ, null);
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
        }
        return SUCCESS;
    }

    public String format(Double dub) {
        return String.format("%.2f", dub);
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setWalletManager(IWalletManager walletManager) {
        this.walletManager = walletManager;
    }
}
