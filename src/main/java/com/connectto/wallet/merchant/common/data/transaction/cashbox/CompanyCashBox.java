package com.connectto.wallet.merchant.common.data.transaction.cashbox;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.util.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serozh on 6/22/2016.
 */
public class CompanyCashBox implements Serializable {

    private Long id;
    private Long companyId;

    //100.000 USD Merchant API provides balance amount to companies and takes back any time.
    private Double balanceProvidedByMerchant;
    private Double balanceCurrent;
    //1.000
    private Double acceptedOverpayment;
    private Double overpayment;
    //80.000 USD Its total balance. At start it is 0. totally 80.000 - 10.000 for each 8 branchBoxes
    private Double balanceTotalProvidedForBranchCashBox;
    //balance Currently Provided For Branch's CashBox.
    //First 80.000 USD //At end  tail is 20.000 USD
    private Double balanceCurrentProvidedForBranchCashBox;
    //balance Returned From Branch's CashBox.
    //First 0 USD//At end Returned 62.000 USD  + tax
    private Double balanceReturnedFromBranchCashBox;
    //information row about company's tax incomes. 2.000 USD is Gathered Tax
    private Double balanceGatheredTax;
    //Defined from merchant administrator
    private CurrencyType currencyType;

    private Date openedAt;
    private Date closedAt;
    private Status status;

    //balanceProvidedByMerchant
    private CompanyCashBoxProvider cashBoxProvider;
    private CompanyCashBoxTaker cashBoxTaker;
    //balanceTotalProvidedForBranchCashBox //First 80.000 USD
    private Set<BranchCashBoxProvider> allCashBoxProviders;
    //balanceCurrentProvidedForCashBox //At end  tail is 20.000 USD
    private Set<BranchCashBoxProvider> currentCashBoxProviders;
    //balanceReturnedFromBranchCashBox
    private Set<BranchCashBoxTaker> cashBoxTakers;

    private Set<BranchCashBox> providedAllForCashBoxes;
    private Set<BranchCashBox> providedCurrentForCashBoxes;
    private Set<BranchCashBox> returnedFromCashBoxes;

    private Long cashBoxProviderId;
    private Long cashBoxTakerId;

    private Double maximumLimitOfTransaction;

    private Double depositFeePercent;
    private Double depositMinFee;
    private Double depositMaxFee;

    private Double withdrawFeePercent;
    private Double withdrawMinFee;
    private Double withdrawMaxFee;

    private Double exchangeDepositFeePercent;
    private Double exchangeDepositMinFee;
    private Double exchangeDepositMaxFee;

    private Double exchangeWithdrawFeePercent;
    private Double exchangeWithdrawMinFee;
    private Double exchangeWithdrawMaxFee;

    private String availableRateValues;
    private Set<CurrencyType> availableRates;

    public boolean isCurrencyTypeSupported(int currencyTypeId) throws DataParseException {
        if (!Utils.isEmpty(availableRateValues)) {
            parseAvailableRates();
            if (Utils.isEmpty(availableRates)) {
                return false;
            }
            for (CurrencyType ct : availableRates) {
                if (ct.getId() == currencyTypeId) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<CurrencyType> parseAvailableRates() throws DataParseException {
        if (!Utils.isEmpty(this.availableRateValues)) {
            this.availableRates = new HashSet<CurrencyType>();

            String[] ars = this.availableRateValues.split(",");

            try {
                for (String s : ars) {
                    CurrencyType type = CurrencyType.valueOf(Integer.parseInt(s.trim()));
                    if (type != null) {
                        this.availableRates.add(type);
                    }
                }
            } catch (Exception e) {
                throw new DataParseException(e);
            }


            return this.availableRates;
        }

        return null;
    }

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Double getBalanceProvidedByMerchant() {
        return balanceProvidedByMerchant;
    }

    public void setBalanceProvidedByMerchant(Double balanceProvidedByMerchant) {
        this.balanceProvidedByMerchant = balanceProvidedByMerchant;
    }

    public Double getBalanceCurrent() {
        return balanceCurrent;
    }

    public void setBalanceCurrent(Double balanceCurrent) {
        this.balanceCurrent = balanceCurrent;
    }

    public Double getAcceptedOverpayment() {
        return acceptedOverpayment;
    }

    public void setAcceptedOverpayment(Double acceptedOverpayment) {
        this.acceptedOverpayment = acceptedOverpayment;
    }

    public Double getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(Double overpayment) {
        this.overpayment = overpayment;
    }

    public Double getBalanceTotalProvidedForBranchCashBox() {
        return balanceTotalProvidedForBranchCashBox;
    }

    public void setBalanceTotalProvidedForBranchCashBox(Double balanceTotalProvidedForBranchCashBox) {
        this.balanceTotalProvidedForBranchCashBox = balanceTotalProvidedForBranchCashBox;
    }

    public Double getBalanceCurrentProvidedForBranchCashBox() {
        return balanceCurrentProvidedForBranchCashBox;
    }

    public void setBalanceCurrentProvidedForBranchCashBox(Double balanceCurrentProvidedForBranchCashBox) {
        this.balanceCurrentProvidedForBranchCashBox = balanceCurrentProvidedForBranchCashBox;
    }

    public Double getBalanceReturnedFromBranchCashBox() {
        return balanceReturnedFromBranchCashBox;
    }

    public void setBalanceReturnedFromBranchCashBox(Double balanceReturnedFromBranchCashBox) {
        this.balanceReturnedFromBranchCashBox = balanceReturnedFromBranchCashBox;
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

    public CompanyCashBoxProvider getCashBoxProvider() {
        return cashBoxProvider;
    }

    public void setCashBoxProvider(CompanyCashBoxProvider cashBoxProvider) {
        this.cashBoxProvider = cashBoxProvider;
    }

    public CompanyCashBoxTaker getCashBoxTaker() {
        return cashBoxTaker;
    }

    public void setCashBoxTaker(CompanyCashBoxTaker cashBoxTaker) {
        this.cashBoxTaker = cashBoxTaker;
    }

    public Set<BranchCashBoxProvider> getAllCashBoxProviders() {
        return allCashBoxProviders;
    }

    public void setAllCashBoxProviders(Set<BranchCashBoxProvider> allCashBoxProviders) {
        this.allCashBoxProviders = allCashBoxProviders;
    }

    public Set<BranchCashBoxProvider> getCurrentCashBoxProviders() {
        return currentCashBoxProviders;
    }

    public void setCurrentCashBoxProviders(Set<BranchCashBoxProvider> currentCashBoxProviders) {
        this.currentCashBoxProviders = currentCashBoxProviders;
    }

    public Set<BranchCashBoxTaker> getCashBoxTakers() {
        return cashBoxTakers;
    }

    public void setCashBoxTakers(Set<BranchCashBoxTaker> cashBoxTakers) {
        this.cashBoxTakers = cashBoxTakers;
    }

    public Set<BranchCashBox> getProvidedAllForCashBoxes() {
        return providedAllForCashBoxes;
    }

    public void setProvidedAllForCashBoxes(Set<BranchCashBox> providedAllForCashBoxes) {
        this.providedAllForCashBoxes = providedAllForCashBoxes;
    }

    public Set<BranchCashBox> getProvidedCurrentForCashBoxes() {
        return providedCurrentForCashBoxes;
    }

    public void setProvidedCurrentForCashBoxes(Set<BranchCashBox> providedCurrentForCashBoxes) {
        this.providedCurrentForCashBoxes = providedCurrentForCashBoxes;
    }

    public Set<BranchCashBox> getReturnedFromCashBoxes() {
        return returnedFromCashBoxes;
    }

    public void setReturnedFromCashBoxes(Set<BranchCashBox> returnedFromCashBoxes) {
        this.returnedFromCashBoxes = returnedFromCashBoxes;
    }

    public Long getCashBoxProviderId() {
        return cashBoxProviderId;
    }

    public void setCashBoxProviderId(Long cashBoxProviderId) {
        this.cashBoxProviderId = cashBoxProviderId;
    }

    public Long getCashBoxTakerId() {
        return cashBoxTakerId;
    }

    public void setCashBoxTakerId(Long cashBoxTakerId) {
        this.cashBoxTakerId = cashBoxTakerId;
    }

    public Double getMaximumLimitOfTransaction() {
        return maximumLimitOfTransaction;
    }

    public void setMaximumLimitOfTransaction(Double maximumLimitOfTransaction) {
        this.maximumLimitOfTransaction = maximumLimitOfTransaction;
    }

    public Double getDepositFeePercent() {
        return depositFeePercent;
    }

    public void setDepositFeePercent(Double depositFeePercent) {
        this.depositFeePercent = depositFeePercent;
    }

    public Double getDepositMinFee() {
        return depositMinFee;
    }

    public void setDepositMinFee(Double depositMinFee) {
        this.depositMinFee = depositMinFee;
    }

    public Double getDepositMaxFee() {
        return depositMaxFee;
    }

    public void setDepositMaxFee(Double depositMaxFee) {
        this.depositMaxFee = depositMaxFee;
    }

    public Double getWithdrawFeePercent() {
        return withdrawFeePercent;
    }

    public void setWithdrawFeePercent(Double withdrawFeePercent) {
        this.withdrawFeePercent = withdrawFeePercent;
    }

    public Double getWithdrawMinFee() {
        return withdrawMinFee;
    }

    public void setWithdrawMinFee(Double withdrawMinFee) {
        this.withdrawMinFee = withdrawMinFee;
    }

    public Double getWithdrawMaxFee() {
        return withdrawMaxFee;
    }

    public void setWithdrawMaxFee(Double withdrawMaxFee) {
        this.withdrawMaxFee = withdrawMaxFee;
    }

    public Double getExchangeDepositFeePercent() {
        return exchangeDepositFeePercent;
    }

    public void setExchangeDepositFeePercent(Double exchangeDepositFeePercent) {
        this.exchangeDepositFeePercent = exchangeDepositFeePercent;
    }

    public Double getExchangeDepositMinFee() {
        return exchangeDepositMinFee;
    }

    public void setExchangeDepositMinFee(Double exchangeDepositMinFee) {
        this.exchangeDepositMinFee = exchangeDepositMinFee;
    }

    public Double getExchangeDepositMaxFee() {
        return exchangeDepositMaxFee;
    }

    public void setExchangeDepositMaxFee(Double exchangeDepositMaxFee) {
        this.exchangeDepositMaxFee = exchangeDepositMaxFee;
    }

    public Double getExchangeWithdrawFeePercent() {
        return exchangeWithdrawFeePercent;
    }

    public void setExchangeWithdrawFeePercent(Double exchangeWithdrawFeePercent) {
        this.exchangeWithdrawFeePercent = exchangeWithdrawFeePercent;
    }

    public Double getExchangeWithdrawMinFee() {
        return exchangeWithdrawMinFee;
    }

    public void setExchangeWithdrawMinFee(Double exchangeWithdrawMinFee) {
        this.exchangeWithdrawMinFee = exchangeWithdrawMinFee;
    }

    public Double getExchangeWithdrawMaxFee() {
        return exchangeWithdrawMaxFee;
    }

    public void setExchangeWithdrawMaxFee(Double exchangeWithdrawMaxFee) {
        this.exchangeWithdrawMaxFee = exchangeWithdrawMaxFee;
    }

    public String getAvailableRateValues() {
        return availableRateValues;
    }

    public void setAvailableRateValues(String availableRateValues) {
        this.availableRateValues = availableRateValues;
    }

    public Set<CurrencyType> getAvailableRates() {
        return availableRates;
    }

    public void setAvailableRates(Set<CurrencyType> availableRates) {
        this.availableRates = availableRates;
    }
}
