package com.connectto.wallet.merchant.common.data.transaction.withdraw;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;

import java.util.Date;

/**
 * Created by Serozh on 11/9/2016.
 */
public class TransactionWithdrawProcess {

    private Long id;
    //PENDING, TIME_OUTED, CANCELED, RELEASED
    private TransactionState state;
    private Date actionDate;
    //from
    private Long walletId;
    //to
    private Long cashierCashBoxId;
    //100 usd
    private Double withdrawAmount;
    private CurrencyType withdrawAmountCurrencyType;

    //100 USD - pass to withdraw
    private Double walletWithdrawPrice;
    //102 USD - total wallet paid money for withdraw transaction
    private Double walletTotalPrice;
    private CurrencyType walletTotalPriceCurrencyType;

    //100 usd
    private Double cashierWithdrawPrice;
    //99 usd cashier total including tax
    private Double cashierTotalPrice;
    private CurrencyType cashierTotalPriceCurrencyType;
    //1 USD
    private TransactionWithdrawProcessTax processTax;

    //0 USD
    private TransactionWithdrawExchange exchange;

    private Long processTaxId;
    private Long exchangeId;

    public TransactionWithdrawProcess(TransactionState state, Date actionDate, Long walletId, Long cashierCashBoxId, Double withdrawAmount, CurrencyType withdrawAmountCurrencyType,
                                     TransactionWithdrawProcessTax processTax  ) {
        this.state = state;
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;

        this.withdrawAmount = withdrawAmount;
        this.withdrawAmountCurrencyType = withdrawAmountCurrencyType;

        this.walletWithdrawPrice = withdrawAmount;
        this.walletTotalPrice = withdrawAmount - processTax.getProcessTaxPrice()   ;
        this.walletTotalPriceCurrencyType = withdrawAmountCurrencyType;

        this.cashierWithdrawPrice = withdrawAmount;
        this.cashierTotalPrice = processTax.getProcessTaxPrice()   ;
        this.cashierTotalPriceCurrencyType = withdrawAmountCurrencyType;

        this.processTax = processTax;
    }

    public TransactionWithdrawProcess(TransactionState state, Date actionDate, Long walletId, Long cashierCashBoxId,
                                     Double withdrawAmount, CurrencyType withdrawAmountCurrencyType,
                                     Double walletWithdrawPrice, Double walletTotalPrice, CurrencyType walletTotalPriceCurrencyType,
                                     Double cashierWithdrawPrice, Double cashierTotalPrice, CurrencyType cashierTotalPriceCurrencyType,
                                     TransactionWithdrawProcessTax processTax,
                                     TransactionWithdrawExchange exchange) {
        this.state = state;
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;

        this.withdrawAmount = withdrawAmount;
        this.withdrawAmountCurrencyType = withdrawAmountCurrencyType;

        this.walletWithdrawPrice = walletWithdrawPrice;
        this.walletTotalPrice = walletTotalPrice;
        this.walletTotalPriceCurrencyType = walletTotalPriceCurrencyType;

        this.cashierWithdrawPrice = cashierWithdrawPrice;
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

    public Double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public CurrencyType getWithdrawAmountCurrencyType() {
        return withdrawAmountCurrencyType;
    }

    public void setWithdrawAmountCurrencyType(CurrencyType withdrawAmountCurrencyType) {
        this.withdrawAmountCurrencyType = withdrawAmountCurrencyType;
    }

    public Double getWalletWithdrawPrice() {
        return walletWithdrawPrice;
    }

    public void setWalletWithdrawPrice(Double walletWithdrawPrice) {
        this.walletWithdrawPrice = walletWithdrawPrice;
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

    public Double getCashierWithdrawPrice() {
        return cashierWithdrawPrice;
    }

    public void setCashierWithdrawPrice(Double cashierWithdrawPrice) {
        this.cashierWithdrawPrice = cashierWithdrawPrice;
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

    public TransactionWithdrawProcessTax getProcessTax() {
        return processTax;
    }

    public void setProcessTax(TransactionWithdrawProcessTax processTax) {
        this.processTax = processTax;
    }

    public TransactionWithdrawExchange getExchange() {
        return exchange;
    }

    public void setExchange(TransactionWithdrawExchange exchange) {
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
