package com.connectto.wallet.merchant.common.data.transaction.withdraw;

import java.util.Date;

/**
 * Created by Serozh on 11/2/2016.
 */
public class TransactionWithdrawTax {

    private Long id;
    private Date actionDate;

    private Long walletId;
    private Long cashierCashBoxId;
    //6 USD (6 + 4 ExT)
    private Double totalTax;
    //6 USD (6 + 4 ExT) USD * 480 AMD = 48000 MAD
    private Double totalTaxPrice;
    //process 1 USD
    private TransactionWithdrawProcessTax processTax;
    //exchange tax  0 or 4 USD
    private TransactionWithdrawExchangeTax exchangeTax;
    //2 USD
    private WalletWithdrawTax walletWithdrawTax;

    private Long processTaxId;
    private Long exchangeTaxId;
    private Long walletWithdrawTaxId;

    private boolean isPaid;

    public TransactionWithdrawTax() {
    }

    public TransactionWithdrawTax(Date actionDate, Long walletId, Long cashierCashBoxId,
                                 TransactionWithdrawProcessTax processTax,
                                 WalletWithdrawTax walletWithdrawTax) {
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.processTax = processTax;
        this.walletWithdrawTax = walletWithdrawTax;

        this.calculateTotalTax();
    }

    public TransactionWithdrawTax(Date actionDate, Long walletId, Long cashierCashBoxId,
                                 TransactionWithdrawProcessTax processTax,
                                 WalletWithdrawTax walletWithdrawTax,
                                 TransactionWithdrawExchangeTax exchangeTax) {
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.processTax = processTax;
        this.walletWithdrawTax = walletWithdrawTax;
        this.exchangeTax = exchangeTax;
        this.calculateTotalTax();
    }

    private void calculateTotalTax() {

        this.totalTax = this.processTax.getProcessTax() ;
        this.totalTaxPrice = this.processTax.getProcessTaxPrice() ;

        if (this.exchangeTax != null) {
            this.totalTax += this.exchangeTax.getExchangeTax();
            this.totalTaxPrice += this.exchangeTax.getExchangeTaxPrice();
        }

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

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalTaxPrice() {
        return totalTaxPrice;
    }

    public void setTotalTaxPrice(Double totalTaxPrice) {
        this.totalTaxPrice = totalTaxPrice;
    }

    public TransactionWithdrawProcessTax getProcessTax() {
        return processTax;
    }

    public void setProcessTax(TransactionWithdrawProcessTax processTax) {
        this.processTax = processTax;
    }

    public TransactionWithdrawExchangeTax getExchangeTax() {
        return exchangeTax;
    }

    public void setExchangeTax(TransactionWithdrawExchangeTax exchangeTax) {
        this.exchangeTax = exchangeTax;
    }

    public WalletWithdrawTax getWalletWithdrawTax() {
        return walletWithdrawTax;
    }

    public void setWalletWithdrawTax(WalletWithdrawTax walletWithdrawTax) {
        this.walletWithdrawTax = walletWithdrawTax;
    }

    public Long getProcessTaxId() {
        return processTaxId;
    }

    public void setProcessTaxId(Long processTaxId) {
        this.processTaxId = processTaxId;
    }

    public Long getExchangeTaxId() {
        return exchangeTaxId;
    }

    public void setExchangeTaxId(Long exchangeTaxId) {
        this.exchangeTaxId = exchangeTaxId;
    }

    public Long getWalletWithdrawTaxId() {
        return walletWithdrawTaxId;
    }

    public void setWalletWithdrawTaxId(Long walletWithdrawTaxId) {
        this.walletWithdrawTaxId = walletWithdrawTaxId;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }
}
