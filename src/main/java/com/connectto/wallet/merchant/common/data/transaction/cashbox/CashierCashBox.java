package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxProvider;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBoxTaker;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class CashierCashBox implements Serializable {

    private Long id;
    private Long cashierId;
    //1000 USD Branch daily(during login) provides cashiers balance amount and take back day end(during logout or before login) from BranchCashBox.balanceProvidedForCashiers
    private Double balanceProvidedByBranch;
    //0-1200 USD It transaction total balance. At start it takes balanceProvided, can increase and decrease
    private Double balanceCurrent;
    //information row about daily cashier's tax incomes. At start it is 0 and can just increase. It increasing after pending tax
    private Double balanceGatheredTax;
    //Defined from branch administrator
    private CurrencyType currencyType;
    //Deposit/Withdraw/Tax-Pendings - transaction process helper.
    //During transaction shows current payments.
    // After transaction their value will increase/decrease current balance and gathered tax information and their value will be  0
    private Double pendingBalanceDeposit;
    private Double pendingBalanceWithdraw;
    private Double pendingTaxAmount;

    private Date openedAt;
    private Date closedAt;
    private Status status;

    private CashierCashBoxProvider currentCashierCashBoxProvider;
    private Set<CashierCashBoxProvider> allCashierCashBoxProviders;

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

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Double getBalanceProvidedByBranch() {
        return balanceProvidedByBranch;
    }

    public void setBalanceProvidedByBranch(Double balanceProvidedByBranch) {
        this.balanceProvidedByBranch = balanceProvidedByBranch;
    }

    public Double getBalanceCurrent() {
        return balanceCurrent;
    }

    public void setBalanceCurrent(Double balanceCurrent) {
        this.balanceCurrent = balanceCurrent;
    }

    public Double getBalanceGatheredTax() {
        return balanceGatheredTax;
    }

    public void setBalanceGatheredTax(Double balanceGatheredTax) {
        this.balanceGatheredTax = balanceGatheredTax;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Double getPendingBalanceDeposit() {
        return pendingBalanceDeposit;
    }

    public void setPendingBalanceDeposit(Double pendingBalanceDeposit) {
        this.pendingBalanceDeposit = pendingBalanceDeposit;
    }

    public Double getPendingBalanceWithdraw() {
        return pendingBalanceWithdraw;
    }

    public void setPendingBalanceWithdraw(Double pendingBalanceWithdraw) {
        this.pendingBalanceWithdraw = pendingBalanceWithdraw;
    }

    public Double getPendingTaxAmount() {
        return pendingTaxAmount;
    }

    public void setPendingTaxAmount(Double pendingTaxAmount) {
        this.pendingTaxAmount = pendingTaxAmount;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CashierCashBoxProvider getCurrentCashierCashBoxProvider() {
        return currentCashierCashBoxProvider;
    }

    public void setCurrentCashierCashBoxProvider(CashierCashBoxProvider currentCashierCashBoxProvider) {
        this.currentCashierCashBoxProvider = currentCashierCashBoxProvider;
    }

    public Set<CashierCashBoxProvider> getAllCashierCashBoxProviders() {
        return allCashierCashBoxProviders;
    }

    public void setAllCashierCashBoxProviders(Set<CashierCashBoxProvider> allCashierCashBoxProviders) {
        this.allCashierCashBoxProviders = allCashierCashBoxProviders;
    }
}
