package com.connectto.wallet.merchant.common.data.transaction.withdraw;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionRationalDuration;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;

import java.util.Date;

/**
 * Created by Serozh on 11/2/2016.
 */
public class TransactionWithdraw {

    private Long id;
    //PENDING, TIME_OUTED, CANCELED, RELEASED
    private TransactionState finalState;
    //from
    private Wallet wallet;
    private Long walletId;
    //to
    private CashierCashBox cashierCashBox;
    private Long cashierCashBoxId;

    private Date openedAt;
    private Date closedAt;

    //100 USD transaction amount & currency type by selected currency type
    private WalletWithdraw walletWithdraw;
    //100 USD
    private Double withdrawAmount;
    private CurrencyType withdrawAmountCurrencyType;
    //100 USD + 2 USD WalletWithdrawTax.paidTaxToWalletSetup from Wallet Api = 102 USD
    private Double walletTotalPrice;
    private CurrencyType walletTotalPriceCurrencyType;
    //1 USD (1%)
    private Double withdrawCashierCashBoxTotalTax;
    private CurrencyType withdrawCashierCashBoxTotalTaxCurrencyType;
    //100 USD - 1 USD (1%) = 99 USD
    private Double cashierTotalAmount;
    private CurrencyType cashierTotalAmountCurrencyType;

    //TransactionWithdrawProcess
    private TransactionWithdrawProcess processStart;
    private TransactionWithdrawProcess processEnd;
    //
    private TransactionWithdrawTax tax;
    //
    private Long walletWithdrawId;
    private Long processStartId;
    private Long processEndId;
    private Long taxId;

    private boolean isEncoded;
    private String orderCode;
    private TransactionRationalDuration rationalDuration;

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

    public TransactionState getFinalState() {
        return finalState;
    }

    public void setFinalState(TransactionState finalState) {
        this.finalState = finalState;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public CashierCashBox getCashierCashBox() {
        return cashierCashBox;
    }

    public void setCashierCashBox(CashierCashBox cashierCashBox) {
        this.cashierCashBox = cashierCashBox;
    }

    public void setCashierCashBoxId(Long cashierCashBoxId) {
        this.cashierCashBoxId = cashierCashBoxId;
    }

    public Date getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(Date openedAt) {
        this.openedAt = openedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public WalletWithdraw getWalletWithdraw() {
        return walletWithdraw;
    }

    public void setWalletWithdraw(WalletWithdraw walletWithdraw) {
        this.walletWithdraw = walletWithdraw;
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

    public Double getWithdrawCashierCashBoxTotalTax() {
        return withdrawCashierCashBoxTotalTax;
    }

    public void setWithdrawCashierCashBoxTotalTax(Double withdrawCashierCashBoxTotalTax) {
        this.withdrawCashierCashBoxTotalTax = withdrawCashierCashBoxTotalTax;
    }

    public CurrencyType getWithdrawCashierCashBoxTotalTaxCurrencyType() {
        return withdrawCashierCashBoxTotalTaxCurrencyType;
    }

    public void setWithdrawCashierCashBoxTotalTaxCurrencyType(CurrencyType withdrawCashierCashBoxTotalTaxCurrencyType) {
        this.withdrawCashierCashBoxTotalTaxCurrencyType = withdrawCashierCashBoxTotalTaxCurrencyType;
    }

    public Double getCashierTotalAmount() {
        return cashierTotalAmount;
    }

    public void setCashierTotalAmount(Double cashierTotalAmount) {
        this.cashierTotalAmount = cashierTotalAmount;
    }

    public CurrencyType getCashierTotalAmountCurrencyType() {
        return cashierTotalAmountCurrencyType;
    }

    public void setCashierTotalAmountCurrencyType(CurrencyType cashierTotalAmountCurrencyType) {
        this.cashierTotalAmountCurrencyType = cashierTotalAmountCurrencyType;
    }

    public TransactionWithdrawProcess getProcessStart() {
        return processStart;
    }

    public void setProcessStart(TransactionWithdrawProcess processStart) {
        this.processStart = processStart;
    }

    public TransactionWithdrawProcess getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(TransactionWithdrawProcess processEnd) {
        this.processEnd = processEnd;
    }

    public TransactionWithdrawTax getTax() {
        return tax;
    }

    public void setTax(TransactionWithdrawTax tax) {
        this.tax = tax;
    }

    public Long getWalletWithdrawId() {
        return walletWithdrawId;
    }

    public void setWalletWithdrawId(Long walletWithdrawId) {
        this.walletWithdrawId = walletWithdrawId;
    }

    public Long getProcessStartId() {
        return processStartId;
    }

    public void setProcessStartId(Long processStartId) {
        this.processStartId = processStartId;
    }

    public Long getProcessEndId() {
        return processEndId;
    }

    public void setProcessEndId(Long processEndId) {
        this.processEndId = processEndId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public boolean getIsEncoded() {
        return isEncoded;
    }

    public void setIsEncoded(boolean encoded) {
        isEncoded = encoded;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public TransactionRationalDuration getRationalDuration() {
        return rationalDuration;
    }

    public void setRationalDuration(TransactionRationalDuration rationalDuration) {
        this.rationalDuration = rationalDuration;
    }
}