package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Serozh on 12/7/2016.
 */
public class BranchCashBoxProvider {

    private Long id;
    private Long branchCashBoxId;
    private Long companyCashBoxId;

    private Double balanceProvided;
    private Double balanceTaken;
    private CurrencyType currencyType;
    //company admin id
    private Long providedBy;
    private Date providedAt;
    //
    private Boolean isTaken;
    //
    private Set<BranchCashBoxTaker> cashBoxTakers;

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

    public Double getBalanceProvided() {
        return balanceProvided;
    }

    public void setBalanceProvided(Double balanceProvided) {
        this.balanceProvided = balanceProvided;
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

    public Long getProvidedBy() {
        return providedBy;
    }

    public void setProvidedBy(Long providedBy) {
        this.providedBy = providedBy;
    }

    public Date getProvidedAt() {
        return providedAt;
    }

    public void setProvidedAt(Date providedAt) {
        this.providedAt = providedAt;
    }

    public Boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Set<BranchCashBoxTaker> getCashBoxTakers() {
        return cashBoxTakers;
    }

    public void setCashBoxTakers(Set<BranchCashBoxTaker> cashBoxTakers) {
        this.cashBoxTakers = cashBoxTakers;
    }
}
