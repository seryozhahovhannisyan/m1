package com.connectto.wallet.merchant.common.data.transaction.transfer;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 11/2/2016.
 */
public class TransferTransaction {

    private Long id;
    //
    private Long transferTicketId;
    private TransferTicket transferTicket;

    private Long walletResultId;
    private TransferTransactionWalletResult walletResult;

    //from
    private Long companyCashBoxId;
    //to
    private Long walletId;

    private Date actionDate;

    //1000USD transfer amount
    private Double transferAmount;
    private CurrencyType transferAmountCurrencyType;

    private boolean isEncoded;
    //not entity field
    private Long companyId;
    private int partitionId;


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

    public Long getTransferTicketId() {
        return transferTicketId;
    }

    public void setTransferTicketId(Long transferTicketId) {
        this.transferTicketId = transferTicketId;
    }

    public TransferTicket getTransferTicket() {
        return transferTicket;
    }

    public void setTransferTicket(TransferTicket transferTicket) {
        this.transferTicket = transferTicket;
    }

    public Long getWalletResultId() {
        return walletResultId;
    }

    public void setWalletResultId(Long walletResultId) {
        this.walletResultId = walletResultId;
    }

    public TransferTransactionWalletResult getWalletResult() {
        return walletResult;
    }

    public void setWalletResult(TransferTransactionWalletResult walletResult) {
        this.walletResult = walletResult;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyCashBoxId() {
        return companyCashBoxId;
    }

    public void setCompanyCashBoxId(Long companyCashBoxId) {
        this.companyCashBoxId = companyCashBoxId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public CurrencyType getTransferAmountCurrencyType() {
        return transferAmountCurrencyType;
    }

    public void setTransferAmountCurrencyType(CurrencyType transferAmountCurrencyType) {
        this.transferAmountCurrencyType = transferAmountCurrencyType;
    }

    public boolean getIsEncoded() {
        return isEncoded;
    }

    public void setIsEncoded(boolean encoded) {
        isEncoded = encoded;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }
}
