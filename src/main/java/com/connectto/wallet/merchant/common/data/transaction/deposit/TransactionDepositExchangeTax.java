package com.connectto.wallet.merchant.common.data.transaction.deposit;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionTaxType;

import java.util.Date;

/**
 * Created by Serozh on 11/9/2016.
 */
public class TransactionDepositExchangeTax {


    private Long id;
    private Date actionDate;
    //Many to one
    private Long walletId;
    private Long cashierCashBoxId;
    //
    private Double exchangeTax;
    private CurrencyType exchangeTaxCurrencyType;

    //
    private Double exchangeTaxPrice;
    private CurrencyType exchangeTaxPriceCurrencyType;

    private TransactionTaxType exchangeTaxType;

    public TransactionDepositExchangeTax() {
    }

    // Tax belongs for general exchange TransactionDeposit.depositAmount
    public TransactionDepositExchangeTax(Date actionDate, Long walletId, Long cashierCashBoxId,
                                          Double exchangeTax, CurrencyType exchangeTaxCurrencyType,
                                          Double exchangeTaxPrice, CurrencyType exchangeTaxPriceCurrencyType,
                                          TransactionTaxType exchangeTaxType) {
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.exchangeTax = exchangeTax;
        this.exchangeTaxCurrencyType = exchangeTaxCurrencyType;
        this.exchangeTaxPrice = exchangeTaxPrice;
        this.exchangeTaxPriceCurrencyType = exchangeTaxPriceCurrencyType;
        this.exchangeTaxType = exchangeTaxType;
    }

    /*
     * #################################################################################################################
     * ########################################        GETTER & SETTER       ###########################################
     * #################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getCashierCashBoxId() {
        return cashierCashBoxId;
    }

    public void setCashierCashBoxId(Long cashierCashBoxId) {
        this.cashierCashBoxId = cashierCashBoxId;
    }

    public Double getExchangeTax() {
        return exchangeTax;
    }

    public void setExchangeTax(Double exchangeTax) {
        this.exchangeTax = exchangeTax;
    }

    public CurrencyType getExchangeTaxCurrencyType() {
        return exchangeTaxCurrencyType;
    }

    public void setExchangeTaxCurrencyType(CurrencyType exchangeTaxCurrencyType) {
        this.exchangeTaxCurrencyType = exchangeTaxCurrencyType;
    }

    public Double getExchangeTaxPrice() {
        return exchangeTaxPrice;
    }

    public void setExchangeTaxPrice(Double exchangeTaxPrice) {
        this.exchangeTaxPrice = exchangeTaxPrice;
    }

    public CurrencyType getExchangeTaxPriceCurrencyType() {
        return exchangeTaxPriceCurrencyType;
    }

    public void setExchangeTaxPriceCurrencyType(CurrencyType exchangeTaxPriceCurrencyType) {
        this.exchangeTaxPriceCurrencyType = exchangeTaxPriceCurrencyType;
    }

    public TransactionTaxType getExchangeTaxType() {
        return exchangeTaxType;
    }

    public void setExchangeTaxType(TransactionTaxType exchangeTaxType) {
        this.exchangeTaxType = exchangeTaxType;
    }
}
