package com.connectto.wallet.merchant.web.action.wallet;

import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.encryption.WalletEncription;
import com.connectto.wallet.merchant.business.wallet.IExchangeRateManager;
import com.connectto.wallet.merchant.common.data.merchant.Role;
import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CompanyCashBox;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionRationalDuration;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionTaxType;
import com.connectto.wallet.merchant.common.data.wallet.ExchangeRate;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 11/17/2016.
 */
public class TransactionBaseAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(TransactionBaseAction.class.getSimpleName());

    public static final String WITHDRAW_NAME = "WalletWithdraw name";
    public static final String WITHDRAW_DESC = "WalletWithdraw description";

    public static final String M_TRANSFER_NAME = "Transfer name";
    public static final String M_TRANSFER_DESC = "Transfer description";



    protected final String TAX_KEY = "tax";
    protected final String TAX_TYPE_KEY = "type";

    protected ResponseDto dto;
    private IExchangeRateManager exchangeRateManager;

    protected String sessionId;
    //
    protected String amount;
    protected String currencyType;
    protected Double productAmount;
    protected CurrencyType productCurrencyType;

    protected String rationalDuration;
    protected TransactionRationalDuration productRationalDuration;

    protected synchronized ExchangeRate getDefaultExchangeRate(CurrencyType toCurrency, CurrencyType oneCurrency) throws InternalErrorException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("isDefault", 1);
        params.put("toCurrency", toCurrency.getId());
        params.put("oneCurrency", oneCurrency.getId());
        params.put("lastOne", 1);

        List<ExchangeRate> exchangeRates = exchangeRateManager.getByParams(params);
        if (Utils.isEmpty(exchangeRates)) {
            String msg = "Could not found exchangeRates, with params, params = " + params;
            throw new InternalErrorException(msg);
        }

        return exchangeRates.get(0);
    }

    protected synchronized boolean convertAmountAndCurrency(boolean decripted) {

        boolean valid = true;

        if (Utils.isEmpty(amount)) {
            valid = false;
            String msg = getText("wallet.back.end.message.empty.amount");
            dto.addFieldError("amount", msg);
        }

        if (Utils.isEmpty(currencyType)) {
            valid = false;
            String msg = getText("wallet.back.end.message.empty.currencyType");
            dto.addFieldError("currencyType", msg);
        }

        if (!valid) {
            return valid;
        }

        try {
            if (decripted) {
                amount = WalletEncription.decrypt(amount);
            }
            productAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.currencyType") + " ," + getText("wallet.payment.label.Amount") + "=" + amount;
            amount = null;
            logger.error(e);
            valid = false;
            dto.addFieldError("amount", msg);
        } catch (EncryptException e) {
            String msg = getText("wallet.back.end.message.empty.currencyType") + " ," + getText("wallet.payment.label.Amount") + "=" + amount;
            amount = null;
            logger.error(e);
            valid = false;
            dto.addFieldError("amount", msg);
        }

        try {

            if (decripted) {
                currencyType = WalletEncription.decrypt(currencyType);
            }

            int type = Integer.parseInt(currencyType);
            productCurrencyType = CurrencyType.valueOf(type);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.incorrect.currencyType") + " ," + getText("wallet.back.end.message.currencyType") + "=" + currencyType;
            currencyType = null;
            logger.error(e);
            valid = false;
            dto.addFieldError("currencyType", msg);
        } catch (EncryptException e) {
            String msg = getText("wallet.back.end.message.empty.incorrect.currencyType") + " ," + getText("wallet.back.end.message.currencyType") + "=" + currencyType;
            currencyType = null;
            logger.error(e);
            valid = false;
            dto.addFieldError("currencyType", msg);
        }

        return valid;
    }

    protected synchronized boolean convertRationalDuration() {

        boolean valid = true;

        if (Utils.isEmpty(rationalDuration)) {
            valid = false;
            String msg = getText("wallet.back.end.message.empty.rationalDuration");
            dto.addFieldError("rationalDuration", msg);
        }

        if (!valid) {
            return valid;
        }

        try {

            int type = Integer.parseInt(rationalDuration);
            productRationalDuration = TransactionRationalDuration.valueOf(type);
        } catch (NumberFormatException e) {
            String msg = getText("wallet.back.end.message.empty.incorrect.rationalDuration") + " ," + getText("wallet.back.end.message.currencyType") + "=" + currencyType;
            currencyType = null;
            logger.error(e);
            valid = false;
            dto.addFieldError("currencyType", msg);
        }

        return valid;
    }

    protected synchronized boolean isAmountAllowed(Role role) {
        if (productAmount > role.getTransactionMax()) {
            String msg = getText("wallet.back.end.message.role.not.allowed.great");
            dto.addFieldError("amount", msg);
            return false;
        } else if (productAmount < role.getTransactionMin()) {
            String msg = getText("wallet.back.end.message.role.not.allowed.less");
            dto.addFieldError("amount", msg);
            return false;
        } else {
            return true;
        }
    }

    protected synchronized boolean isCurrencyTypeAllowed(Role role, CashierCashBox cashBox) {
        if (!role.getIsExchangeAllowed()) {
            return cashBox.getCurrencyType().getId() == productCurrencyType.getId();
        } else {
            return role.isCurrencyTypeSupported(productCurrencyType.getId());
        }

    }


    //Withdraw
    protected Map<String, Object> calculateWithdrawTax(CompanyCashBox companyCashBox, Double amount) {

        Map<String, Object> purchaseTaxTypeMap = new HashMap<String, Object>();
        Double tax = 0d;
        Double taxPercentAmount = companyCashBox.getWithdrawFeePercent() * amount / 100;

        if (taxPercentAmount < companyCashBox.getWithdrawMinFee()) {
            tax = companyCashBox.getWithdrawMinFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (taxPercentAmount > companyCashBox.getWithdrawMaxFee()) {
            tax = companyCashBox.getWithdrawMaxFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            tax = taxPercentAmount;
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        purchaseTaxTypeMap.put(TAX_KEY, tax);
        return purchaseTaxTypeMap;
    }

    protected Map<String, Object> calculateWithdrawExchangeTax(CompanyCashBox companyCashBox, Double amount) {

        Map<String, Object> purchaseTaxTypeMap = new HashMap<String, Object>();
        Double tax = 0d;
        Double taxPercentAmount = companyCashBox.getExchangeWithdrawFeePercent() * amount / 100;

        if (taxPercentAmount < companyCashBox.getExchangeWithdrawMinFee()) {
            tax = companyCashBox.getExchangeWithdrawMinFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (taxPercentAmount > companyCashBox.getExchangeWithdrawMaxFee()) {
            tax = companyCashBox.getExchangeWithdrawMaxFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            tax = taxPercentAmount;
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        purchaseTaxTypeMap.put(TAX_KEY, tax);
        return purchaseTaxTypeMap;
    }

    //Deposit
    protected Map<String, Object> calculateDepositTax(CompanyCashBox companyCashBox, Double amount) {

        Map<String, Object> purchaseTaxTypeMap = new HashMap<String, Object>();
        Double tax = 0d;
        Double taxPercentAmount = companyCashBox.getDepositFeePercent() * amount / 100;

        if (taxPercentAmount < companyCashBox.getDepositMinFee()) {
            tax = companyCashBox.getDepositMinFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (taxPercentAmount > companyCashBox.getDepositMaxFee()) {
            tax = companyCashBox.getDepositMaxFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            tax = taxPercentAmount;
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        purchaseTaxTypeMap.put(TAX_KEY, tax);
        return purchaseTaxTypeMap;
    }

    protected Map<String, Object> calculateDepositExchangeTax(CompanyCashBox companyCashBox, Double amount) {

        Map<String, Object> purchaseTaxTypeMap = new HashMap<String, Object>();
        Double tax = 0d;
        Double taxPercentAmount = companyCashBox.getExchangeDepositFeePercent() * amount / 100;

        if (taxPercentAmount < companyCashBox.getExchangeDepositMinFee()) {
            tax = companyCashBox.getExchangeDepositMinFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MIN);
        } else if (taxPercentAmount > companyCashBox.getExchangeDepositMaxFee()) {
            tax = companyCashBox.getExchangeDepositMaxFee();
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.MAX);
        } else {
            tax = taxPercentAmount;
            purchaseTaxTypeMap.put(TAX_TYPE_KEY, TransactionTaxType.PERCENT);
        }
        purchaseTaxTypeMap.put(TAX_KEY, tax);
        return purchaseTaxTypeMap;
    }



    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setRationalDuration(String rationalDuration) {
        this.rationalDuration = rationalDuration;
    }

    public void setExchangeRateManager(IExchangeRateManager exchangeRateManager) {
        this.exchangeRateManager = exchangeRateManager;
    }

}
