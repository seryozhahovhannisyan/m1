package com.connectto.wallet.merchant.common.data.transaction.deposit;

import java.util.Date;

/**
 * Created by Serozh on 11/2/2016.
 */
public class TransactionDepositTax {

    private Long id;
    private Date actionDate;

    private Long walletId;
    private Long cashierCashBoxId;
    //
    private Double totalTax;
    //
    private Double totalTaxPrice;
    //
    private TransactionDepositProcessTax processTax;
    //
    private TransactionDepositExchangeTax exchangeTax;
    //
    private WalletDepositTax walletDepositTax;

    private Long processTaxId;
    private Long exchangeTaxId;
    private Long walletDepositTaxId;

    private boolean isPaid;

    public TransactionDepositTax() {
    }

    public TransactionDepositTax(Date actionDate, Long walletId, Long cashierCashBoxId,
                                  TransactionDepositProcessTax processTax,
                                  WalletDepositTax walletDepositTax) {
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.processTax = processTax;
        this.walletDepositTax = walletDepositTax;

        this.calculateTotalTax();
    }

    public TransactionDepositTax(Date actionDate, Long walletId, Long cashierCashBoxId,
                                  TransactionDepositProcessTax processTax,
                                  WalletDepositTax walletDepositTax,
                                  TransactionDepositExchangeTax exchangeTax) {
        this.actionDate = actionDate;
        this.walletId = walletId;
        this.cashierCashBoxId = cashierCashBoxId;
        this.processTax = processTax;
        this.walletDepositTax = walletDepositTax;
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

    public TransactionDepositProcessTax getProcessTax() {
        return processTax;
    }

    public void setProcessTax(TransactionDepositProcessTax processTax) {
        this.processTax = processTax;
    }

    public TransactionDepositExchangeTax getExchangeTax() {
        return exchangeTax;
    }

    public void setExchangeTax(TransactionDepositExchangeTax exchangeTax) {
        this.exchangeTax = exchangeTax;
    }

    public WalletDepositTax getWalletDepositTax() {
        return walletDepositTax;
    }

    public void setWalletDepositTax(WalletDepositTax walletDepositTax) {
        this.walletDepositTax = walletDepositTax;
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

    public Long getWalletDepositTaxId() {
        return walletDepositTaxId;
    }

    public void setWalletDepositTaxId(Long walletDepositTaxId) {
        this.walletDepositTaxId = walletDepositTaxId;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }
}
