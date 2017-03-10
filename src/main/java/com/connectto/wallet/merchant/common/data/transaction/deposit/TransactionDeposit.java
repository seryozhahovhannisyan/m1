package com.connectto.wallet.merchant.common.data.transaction.deposit;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionRationalDuration;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;

import java.util.Date;

/**
 * Created by Serozh on 11/2/2016.
 */
public class TransactionDeposit {

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
    private WalletDeposit walletDeposit;
    //100 USD
    private Double depositAmount;
    private CurrencyType depositAmountCurrencyType;
    //
    private Double walletTotalPrice;
    private CurrencyType walletTotalPriceCurrencyType;
    //
    private Double depositCashierCashBoxTotalTax;
    private CurrencyType depositCashierCashBoxTotalTaxCurrencyType;
    //
    private Double cashierTotalAmount;
    private CurrencyType cashierTotalAmountCurrencyType;

    //TransactionDepositProcess
    private TransactionDepositProcess processStart;
    private TransactionDepositProcess processEnd;
    //
    private TransactionDepositTax tax;
    //
    private Long walletDepositId;
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

    public WalletDeposit getWalletDeposit() {
        return walletDeposit;
    }

    public void setWalletDeposit(WalletDeposit walletDeposit) {
        this.walletDeposit = walletDeposit;
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

    public Double getDepositCashierCashBoxTotalTax() {
        return depositCashierCashBoxTotalTax;
    }

    public void setDepositCashierCashBoxTotalTax(Double depositCashierCashBoxTotalTax) {
        this.depositCashierCashBoxTotalTax = depositCashierCashBoxTotalTax;
    }

    public CurrencyType getDepositCashierCashBoxTotalTaxCurrencyType() {
        return depositCashierCashBoxTotalTaxCurrencyType;
    }

    public void setDepositCashierCashBoxTotalTaxCurrencyType(CurrencyType depositCashierCashBoxTotalTaxCurrencyType) {
        this.depositCashierCashBoxTotalTaxCurrencyType = depositCashierCashBoxTotalTaxCurrencyType;
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

    public TransactionDepositProcess getProcessStart() {
        return processStart;
    }

    public void setProcessStart(TransactionDepositProcess processStart) {
        this.processStart = processStart;
    }

    public TransactionDepositProcess getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(TransactionDepositProcess processEnd) {
        this.processEnd = processEnd;
    }

    public TransactionDepositTax getTax() {
        return tax;
    }

    public void setTax(TransactionDepositTax tax) {
        this.tax = tax;
    }

    public Long getWalletDepositId() {
        return walletDepositId;
    }

    public void setWalletDepositId(Long walletDepositId) {
        this.walletDepositId = walletDepositId;
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