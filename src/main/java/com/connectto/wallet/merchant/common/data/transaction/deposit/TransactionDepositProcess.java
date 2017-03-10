package com.connectto.wallet.merchant.common.data.transaction.deposit;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;

import java.util.Date;

/**
 * Created by Serozh on 11/9/2016.
 */
public class TransactionDepositProcess {

    private Long id;
    //PENDING, TIME_OUTED, CANCELED, RELEASED
    private TransactionState state;
    private Date actionDate;
    //from
    private Long walletId;
    //to
    private Long cashierCashBoxId;
    //100 usd
    private Double depositAmount;
    private CurrencyType depositAmountCurrencyType;

    //
    private Double walletDepositPrice;
    //
    private Double walletTotalPrice;
    private CurrencyType walletTotalPriceCurrencyType;

    //
    private Double cashierDepositPrice;
    //
    private Double cashierTotalPrice;
    private CurrencyType cashierTotalPriceCurrencyType;
    //
    private TransactionDepositProcessTax processTax;

    //
    private TransactionDepositExchange exchange;

    private Long processTaxId;
    private Long exchangeId;

    public TransactionDepositProcess(TransactionState state, Date actionDate, Long walletId, Long cashierCashBoxId, Double depositAmount, CurrencyType depositAmountCurrencyType,
                                      TransactionDepositProcessTax processTax  ) {
        this.state = state;
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;

        this.depositAmount = depositAmount;
        this.depositAmountCurrencyType = depositAmountCurrencyType;

        this.walletDepositPrice = depositAmount;
        this.walletTotalPrice = depositAmount - processTax.getProcessTaxPrice()   ;
        this.walletTotalPriceCurrencyType = depositAmountCurrencyType;

        this.cashierDepositPrice = depositAmount;
        this.cashierTotalPrice = processTax.getProcessTaxPrice()   ;
        this.cashierTotalPriceCurrencyType = depositAmountCurrencyType;

        this.processTax = processTax;
    }

    public TransactionDepositProcess(TransactionState state, Date actionDate, Long walletId, Long cashierCashBoxId,
                                      Double depositAmount, CurrencyType depositAmountCurrencyType,
                                      Double walletDepositPrice, Double walletTotalPrice, CurrencyType walletTotalPriceCurrencyType,
                                      Double cashierDepositPrice, Double cashierTotalPrice, CurrencyType cashierTotalPriceCurrencyType,
                                      TransactionDepositProcessTax processTax,
                                      TransactionDepositExchange exchange) {
        this.state = state;
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;

        this.depositAmount = depositAmount;
        this.depositAmountCurrencyType = depositAmountCurrencyType;

        this.walletDepositPrice = walletDepositPrice;
        this.walletTotalPrice = walletTotalPrice;
        this.walletTotalPriceCurrencyType = walletTotalPriceCurrencyType;

        this.cashierDepositPrice = cashierDepositPrice;
        this.cashierTotalPrice = cashierTotalPrice;
        this.cashierTotalPriceCurrencyType = cashierTotalPriceCurrencyType;

        this.processTax = processTax;
        this.exchange = exchange;
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

    public TransactionState getState() {
        return state;
    }

    public void setState(TransactionState state) {
        this.state = state;
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

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public CurrencyType getDepositAmountCurrencyType() {
        return depositAmountCurrencyType;
    }

    public void setDepositAmountCurrencyType(CurrencyType depositAmountCurrencyType) {
        this.depositAmountCurrencyType = depositAmountCurrencyType;
    }

    public Double getWalletDepositPrice() {
        return walletDepositPrice;
    }

    public void setWalletDepositPrice(Double walletDepositPrice) {
        this.walletDepositPrice = walletDepositPrice;
    }

    public Double getWalletTotalPrice() {
        return walletTotalPrice;
    }

    public void setWalletTotalPrice(Double walletTotalPrice) {
        this.walletTotalPrice = walletTotalPrice;
    }

    public CurrencyType getWalletTotalPriceCurrencyType() {
        return walletTotalPriceCurrencyType;
    }

    public void setWalletTotalPriceCurrencyType(CurrencyType walletTotalPriceCurrencyType) {
        this.walletTotalPriceCurrencyType = walletTotalPriceCurrencyType;
    }

    public Double getCashierDepositPrice() {
        return cashierDepositPrice;
    }

    public void setCashierDepositPrice(Double cashierDepositPrice) {
        this.cashierDepositPrice = cashierDepositPrice;
    }

    public Double getCashierTotalPrice() {
        return cashierTotalPrice;
    }

    public void setCashierTotalPrice(Double cashierTotalPrice) {
        this.cashierTotalPrice = cashierTotalPrice;
    }

    public CurrencyType getCashierTotalPriceCurrencyType() {
        return cashierTotalPriceCurrencyType;
    }

    public void setCashierTotalPriceCurrencyType(CurrencyType cashierTotalPriceCurrencyType) {
        this.cashierTotalPriceCurrencyType = cashierTotalPriceCurrencyType;
    }

    public TransactionDepositProcessTax getProcessTax() {
        return processTax;
    }

    public void setProcessTax(TransactionDepositProcessTax processTax) {
        this.processTax = processTax;
    }

    public TransactionDepositExchange getExchange() {
        return exchange;
    }

    public void setExchange(TransactionDepositExchange exchange) {
        this.exchange = exchange;
    }

    public Long getProcessTaxId() {
        return processTaxId;
    }

    public void setProcessTaxId(Long processTaxId) {
        this.processTaxId = processTaxId;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }
}
