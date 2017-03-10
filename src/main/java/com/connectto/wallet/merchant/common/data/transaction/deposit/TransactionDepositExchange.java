package com.connectto.wallet.merchant.common.data.transaction.deposit;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 11/9/2016.
 */
public class TransactionDepositExchange {

    private Long id;
    //who
    private Long walletId;
    private Long cashierCashBoxId;
    //buy or sell rate
    private Long rateId;
    //when
    private Date exchangeDate;

    //
    private Double cashierAmount;
    private CurrencyType cashierCurrencyType;
    //
    private Double rate;
    private CurrencyType rateCurrencyType;
    //
    private Double walletBoughtAmount;
    private CurrencyType walletBoughtAmountCurrencyType;
    //
    private Double walletPaidAmount;
    private CurrencyType walletPaidCurrencyType;
    //Can be null, when exchange just show corresponding convert
    private TransactionDepositExchangeTax exchangeTax;
    private Long exchangeTaxId;

    /**
     * For converting
     */
    public TransactionDepositExchange(Long walletId, Long cashierCashBoxId, Long rateId,
                                       Date exchangeDate,
                                       Double cashierAmount, CurrencyType cashierCurrencyType,
                                       Double rate, CurrencyType rateCurrencyType,
                                       Double walletPaidAmount, CurrencyType walletPaidCurrencyType) {
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.rateId = rateId;
        this.exchangeDate = exchangeDate;
        this.cashierAmount = cashierAmount;
        this.cashierCurrencyType = cashierCurrencyType;
        this.rate = rate;
        this.rateCurrencyType = rateCurrencyType;

        this.walletBoughtAmount = cashierAmount;
        this.walletBoughtAmountCurrencyType = cashierCurrencyType;

        this.walletPaidAmount = walletPaidAmount;
        this.walletPaidCurrencyType = walletPaidCurrencyType;

    }

    /**
     * For general  counting from depositAmount
     */
    public TransactionDepositExchange(Long walletId, Long cashierCashBoxId, Long rateId,
                                       Date exchangeDate,
                                       Double cashierAmount, CurrencyType cashierCurrencyType,
                                       Double rate, CurrencyType rateCurrencyType,
                                       Double walletPaidAmount, CurrencyType walletPaidCurrencyType,
                                       TransactionDepositExchangeTax exchangeTax) {
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.rateId = rateId;
        this.exchangeDate = exchangeDate;
        this.cashierAmount = cashierAmount;
        this.cashierCurrencyType = cashierCurrencyType;
        this.rate = rate;
        this.rateCurrencyType = rateCurrencyType;

        this.walletBoughtAmount = cashierAmount;
        this.walletBoughtAmountCurrencyType = cashierCurrencyType;

        this.walletPaidAmount = walletPaidAmount;
        this.walletPaidCurrencyType = walletPaidCurrencyType;
        this.exchangeTax = exchangeTax;

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

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Double getCashierAmount() {
        return cashierAmount;
    }

    public void setCashierAmount(Double cashierAmount) {
        this.cashierAmount = cashierAmount;
    }

    public CurrencyType getCashierCurrencyType() {
        return cashierCurrencyType;
    }

    public void setCashierCurrencyType(CurrencyType cashierCurrencyType) {
        this.cashierCurrencyType = cashierCurrencyType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public CurrencyType getRateCurrencyType() {
        return rateCurrencyType;
    }

    public void setRateCurrencyType(CurrencyType rateCurrencyType) {
        this.rateCurrencyType = rateCurrencyType;
    }

    public Double getWalletBoughtAmount() {
        return walletBoughtAmount;
    }

    public void setWalletBoughtAmount(Double walletBoughtAmount) {
        this.walletBoughtAmount = walletBoughtAmount;
    }

    public CurrencyType getWalletBoughtAmountCurrencyType() {
        return walletBoughtAmountCurrencyType;
    }

    public void setWalletBoughtAmountCurrencyType(CurrencyType walletBoughtAmountCurrencyType) {
        this.walletBoughtAmountCurrencyType = walletBoughtAmountCurrencyType;
    }

    public Double getWalletPaidAmount() {
        return walletPaidAmount;
    }

    public void setWalletPaidAmount(Double walletPaidAmount) {
        this.walletPaidAmount = walletPaidAmount;
    }

    public CurrencyType getWalletPaidCurrencyType() {
        return walletPaidCurrencyType;
    }

    public void setWalletPaidCurrencyType(CurrencyType walletPaidCurrencyType) {
        this.walletPaidCurrencyType = walletPaidCurrencyType;
    }

    public TransactionDepositExchangeTax getExchangeTax() {
        return exchangeTax;
    }

    public void setExchangeTax(TransactionDepositExchangeTax exchangeTax) {
        this.exchangeTax = exchangeTax;
    }

    public Long getExchangeTaxId() {
        return exchangeTaxId;
    }

    public void setExchangeTaxId(Long exchangeTaxId) {
        this.exchangeTaxId = exchangeTaxId;
    }
}
