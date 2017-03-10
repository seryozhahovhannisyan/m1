package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.util.Date;

/**
 * Created by Serozh on 12/7/2016.
 */
public class CashierCashBoxTaker {

    private Long id;
    private Long cashierCashBoxId;
    private Long branchCashBoxId;

    private Double balanceTaken;
    private CurrencyType currencyType;

    private Date tookAt;

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

    public Date getTookAt() {
        return tookAt;
    }

    public void setTookAt(Date tookAt) {
        this.tookAt = tookAt;
    }
}
