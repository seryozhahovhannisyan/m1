package com.connectto.wallet.merchant.common.data.transaction.transfer;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 12/23/2016.
 * WalletApi ->com.connectto.wallet.model.transaction.merchant.withdraw.MerchantWithdrawTax
 */
public class TransferTransactionWalletResult {

    private Long id;
    private Long transactionId;
    private Date actionDate;
    //Many to one
    private Long walletId;
    private CurrencyType walletCurrencyType;

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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public CurrencyType getWalletCurrencyType() {
        return walletCurrencyType;
    }

    public void setWalletCurrencyType(CurrencyType walletCurrencyType) {
        this.walletCurrencyType = walletCurrencyType;
    }
}
