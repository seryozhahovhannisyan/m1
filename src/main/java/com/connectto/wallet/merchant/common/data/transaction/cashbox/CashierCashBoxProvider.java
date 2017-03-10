package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 12/7/2016.
 */
public class CashierCashBoxProvider {

    private Long id;
    private Long cashierCashBoxId;
    private Long branchCashBoxId;

    private Double balanceProvided;
    private CurrencyType currencyType;

    private Date providedAt;

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

    public Long getCashierCashBoxId() {
        return cashierCashBoxId;
    }

    public void setCashierCashBoxId(Long cashierCashBoxId) {
        this.cashierCashBoxId = cashierCashBoxId;
    }

    public Long getBranchCashBoxId() {
        return branchCashBoxId;
    }

    public void setBranchCashBoxId(Long branchCashBoxId) {
        this.branchCashBoxId = branchCashBoxId;
    }

    public Double getBalanceProvided() {
        return balanceProvided;
    }

    public void setBalanceProvided(Double balanceProvided) {
        this.balanceProvided = balanceProvided;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Date getProvidedAt() {
        return providedAt;
    }

    public void setProvidedAt(Date providedAt) {
        this.providedAt = providedAt;
    }
}
