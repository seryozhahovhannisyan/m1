package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class BranchCashBox implements Serializable {

    private Long id;
    private Long branchId;
    //10.000 USD Company provides balance amount to branches and takes back any time. CompanyCashBox.****
    private Double balanceProvidedByCompany;
    //2.000 USD balance Current still not provided to cashiers
    private Double balanceCurrent;
    //8.000 USD Its daily total balance. At start it is 0. totally 8.000 - 1.000 for each 8 cashierBoxes
    private Double balanceTotalProvidedForCashierCashBox;
    //balance Currently Provided For Cashier's CashBox.
    //First 8.000 USD //At day end  tail is 2000 USD
    private Double balanceCurrentProvidedForCashierCashBox;
    //balance Returned From Cashier's CashBox.
    //First 0 USD//At day end Returned 6.200 USD  + tax
    private Double balanceReturnedFromCashierCashBox;
    //information row about branch's tax incomes daily. 200 USD is Gathered Tax
    private Double balanceGatheredTax;
    //Defined from company administrator
    private CurrencyType currencyType;
    //
    private Date openedAt;
    private Date closedAt;
    private Status status;

    //balanceProvidedByCompany
    private BranchCashBoxProvider currentBranchCashBoxProvider;
    private Set<BranchCashBoxProvider> allBranchCashBoxProviders;
    //balanceTotalProvidedForCashierCashBox //First 8.000 USD
    private Set<CashierCashBoxProvider> allCashierCashBoxProviders;
    //balanceCurrentProvidedForCashBox //At day end  tail is 2000 USD
    private Set<CashierCashBoxProvider> currentCashierCashBoxProviders;
    //balanceReturnedFromCashBox
    private Set<CashierCashBoxTaker> cashBoxTakers;

    private Set<CashierCashBox> providedAllForCashBoxes;
    private Set<CashierCashBox> providedCurrentForCashBoxes;
    private Set<CashierCashBox> returnedFromCashBoxes;



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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Double getBalanceProvidedByCompany() {
        return balanceProvidedByCompany;
    }

    public void setBalanceProvidedByCompany(Double balanceProvidedByCompany) {
        this.balanceProvidedByCompany = balanceProvidedByCompany;
    }

    public Double getBalanceCurrent() {
        return balanceCurrent;
    }

    public void setBalanceCurrent(Double balanceCurrent) {
        this.balanceCurrent = balanceCurrent;
    }

    public Double getBalanceTotalProvidedForCashierCashBox() {
        return balanceTotalProvidedForCashierCashBox;
    }

    public void setBalanceTotalProvidedForCashierCashBox(Double balanceTotalProvidedForCashierCashBox) {
        this.balanceTotalProvidedForCashierCashBox = balanceTotalProvidedForCashierCashBox;
    }

    public Double getBalanceCurrentProvidedForCashierCashBox() {
        return balanceCurrentProvidedForCashierCashBox;
    }

    public void setBalanceCurrentProvidedForCashierCashBox(Double balanceCurrentProvidedForCashierCashBox) {
        this.balanceCurrentProvidedForCashierCashBox = balanceCurrentProvidedForCashierCashBox;
    }

    public Double getBalanceReturnedFromCashierCashBox() {
        return balanceReturnedFromCashierCashBox;
    }

    public void setBalanceReturnedFromCashierCashBox(Double balanceReturnedFromCashierCashBox) {
        this.balanceReturnedFromCashierCashBox = balanceReturnedFromCashierCashBox;
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

    public BranchCashBoxProvider getCurrentBranchCashBoxProvider() {
        return currentBranchCashBoxProvider;
    }

    public void setCurrentBranchCashBoxProvider(BranchCashBoxProvider currentBranchCashBoxProvider) {
        this.currentBranchCashBoxProvider = currentBranchCashBoxProvider;
    }

    public Set<BranchCashBoxProvider> getAllBranchCashBoxProviders() {
        return allBranchCashBoxProviders;
    }

    public void setAllBranchCashBoxProviders(Set<BranchCashBoxProvider> allBranchCashBoxProviders) {
        this.allBranchCashBoxProviders = allBranchCashBoxProviders;
    }

    public Set<CashierCashBoxProvider> getAllCashierCashBoxProviders() {
        return allCashierCashBoxProviders;
    }

    public void setAllCashierCashBoxProviders(Set<CashierCashBoxProvider> allCashierCashBoxProviders) {
        this.allCashierCashBoxProviders = allCashierCashBoxProviders;
    }

    public Set<CashierCashBoxProvider> getCurrentCashierCashBoxProviders() {
        return currentCashierCashBoxProviders;
    }

    public void setCurrentCashierCashBoxProviders(Set<CashierCashBoxProvider> currentCashierCashBoxProviders) {
        this.currentCashierCashBoxProviders = currentCashierCashBoxProviders;
    }

    public Set<CashierCashBoxTaker> getCashBoxTakers() {
        return cashBoxTakers;
    }

    public void setCashBoxTakers(Set<CashierCashBoxTaker> cashBoxTakers) {
        this.cashBoxTakers = cashBoxTakers;
    }

    public Set<CashierCashBox> getProvidedAllForCashBoxes() {
        return providedAllForCashBoxes;
    }

    public void setProvidedAllForCashBoxes(Set<CashierCashBox> providedAllForCashBoxes) {
        this.providedAllForCashBoxes = providedAllForCashBoxes;
    }

    public Set<CashierCashBox> getProvidedCurrentForCashBoxes() {
        return providedCurrentForCashBoxes;
    }

    public void setProvidedCurrentForCashBoxes(Set<CashierCashBox> providedCurrentForCashBoxes) {
        this.providedCurrentForCashBoxes = providedCurrentForCashBoxes;
    }

    public Set<CashierCashBox> getReturnedFromCashBoxes() {
        return returnedFromCashBoxes;
    }

    public void setReturnedFromCashBoxes(Set<CashierCashBox> returnedFromCashBoxes) {
        this.returnedFromCashBoxes = returnedFromCashBoxes;
    }
}