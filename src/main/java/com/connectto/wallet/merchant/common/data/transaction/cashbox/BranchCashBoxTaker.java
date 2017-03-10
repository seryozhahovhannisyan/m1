package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 12/7/2016.
 */
public class BranchCashBoxTaker {

    private Long id;
    private Long branchCashBoxId;
    private Long companyCashBoxId;

    private Double balanceTaken;
    private CurrencyType currencyType;
    //company admin id
    private Long tookBy;
    private Date tookAt;
    private Long providerId;

    /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchCashBoxId() {
        return branchCashBoxId;
    }

    public void setBranchCashBoxId(Long branchCashBoxId) {
        this.branchCashBoxId = branchCashBoxId;
    }

    public Long getCompanyCashBoxId() {
        return companyCashBoxId;
    }

    public void setCompanyCashBoxId(Long companyCashBoxId) {
        this.companyCashBoxId = companyCashBoxId;
    }

    public Double getBalanceTaken() {
        return balanceTaken;
    }

    public void setBalanceTaken(Double balanceTaken) {
        this.balanceTaken = balanceTaken;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Long getTookBy() {
        return tookBy;
    }

    public void setTookBy(Long tookBy) {
        this.tookBy = tookBy;
    }

    public Date getTookAt() {
        return tookAt;
    }

    public void setTookAt(Date tookAt) {
        this.tookAt = tookAt;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
}