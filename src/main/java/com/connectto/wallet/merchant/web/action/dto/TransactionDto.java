package com.connectto.wallet.merchant.web.action.dto;

/**
 * Created by Serozh on 1/10/2017.
 */
public class TransactionDto {
    //PENDING, TIME_OUTED, CANCELED, RELEASED
    private String withdrawId;
    private String depositId;

    private String orderId;
    private String account;
    private String finalState;
    //todo mark dateformat
    private String openedAt;
    private String closedAt;
    private String duration;

    //100 USD
    private String withdrawAmount;
    private String withdrawAmountCurrencyType;
    //100 USD + 2 USD WalletWithdrawTax.paidTaxToWalletSetup from Wallet Api = 102 USD
    private String walletTotalPrice;
    private String walletTotalPriceCurrencyType;
    //1 USD (1%)
    private String withdrawCashierCashBoxTotalTax;
    private String withdrawCashierCashBoxTotalTaxCurrencyType;
    //100 USD - 1 USD (1%) = 99 USD
    private String cashierTotalAmount;
    private String cashierTotalAmountCurrencyType;

    private String orderCode;

    private WalletDto walletDto;

    /*
    * #################################################################################################################
    * ########################################        GETTER & SETTER       ###########################################
    * #################################################################################################################
    */

    public String getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getWithdrawAmountCurrencyType() {
        return withdrawAmountCurrencyType;
    }

    public void setWithdrawAmountCurrencyType(String withdrawAmountCurrencyType) {
        this.withdrawAmountCurrencyType = withdrawAmountCurrencyType;
    }

    public String getWalletTotalPrice() {
        return walletTotalPrice;
    }

    public void setWalletTotalPrice(String walletTotalPrice) {
        this.walletTotalPrice = walletTotalPrice;
    }

    public String getWalletTotalPriceCurrencyType() {
        return walletTotalPriceCurrencyType;
    }

    public void setWalletTotalPriceCurrencyType(String walletTotalPriceCurrencyType) {
        this.walletTotalPriceCurrencyType = walletTotalPriceCurrencyType;
    }

    public String getWithdrawCashierCashBoxTotalTax() {
        return withdrawCashierCashBoxTotalTax;
    }

    public void setWithdrawCashierCashBoxTotalTax(String withdrawCashierCashBoxTotalTax) {
        this.withdrawCashierCashBoxTotalTax = withdrawCashierCashBoxTotalTax;
    }

    public String getWithdrawCashierCashBoxTotalTaxCurrencyType() {
        return withdrawCashierCashBoxTotalTaxCurrencyType;
    }

    public void setWithdrawCashierCashBoxTotalTaxCurrencyType(String withdrawCashierCashBoxTotalTaxCurrencyType) {
        this.withdrawCashierCashBoxTotalTaxCurrencyType = withdrawCashierCashBoxTotalTaxCurrencyType;
    }

    public String getCashierTotalAmount() {
        return cashierTotalAmount;
    }

    public void setCashierTotalAmount(String cashierTotalAmount) {
        this.cashierTotalAmount = cashierTotalAmount;
    }

    public String getCashierTotalAmountCurrencyType() {
        return cashierTotalAmountCurrencyType;
    }

    public void setCashierTotalAmountCurrencyType(String cashierTotalAmountCurrencyType) {
        this.cashierTotalAmountCurrencyType = cashierTotalAmountCurrencyType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public WalletDto getWalletDto() {
        return walletDto;
    }

    public void setWalletDto(WalletDto walletDto) {
        this.walletDto = walletDto;
    }
}
