package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;
import com.connectto.wallet.merchant.common.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Serozh on 8/8/2016.
 */
public class Role implements Serializable {

    private Long id;
    private Long companyId;

    private String name;
    private String description;

    private Double transactionMin;
    private Double transactionMax;

    private Boolean isExchangeAllowed;

    private Set<CurrencyType> availableRates;
    private String availableRateValues;

    public Set<CurrencyType> parseAvailableRates() {
        if(!Utils.isEmpty(availableRateValues)){
            this.availableRates = new HashSet<CurrencyType>();
            String[] ars = availableRateValues.split(",");

            for(String s : ars){
                CurrencyType type = CurrencyType.valueOf(Integer.parseInt(s));
                if(type !=  null){
                    availableRates.add(type);
                }
            }

            return availableRates;
        }

        return null;
    }

    public boolean isCurrencyTypeSupported(int currencyTypeId) {
        if(!Utils.isEmpty(availableRateValues)){
            parseAvailableRates();
            if(Utils.isEmpty(availableRates)){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTransactionMin() {
        return transactionMin;
    }

    public void setTransactionMin(Double transactionMin) {
        this.transactionMin = transactionMin;
    }

    public Double getTransactionMax() {
        return transactionMax;
    }

    public void setTransactionMax(Double transactionMax) {
        this.transactionMax = transactionMax;
    }

    public Boolean getIsExchangeAllowed() {
        return isExchangeAllowed;
    }

    public void setIsExchangeAllowed(Boolean exchangeAllowed) {
        isExchangeAllowed = exchangeAllowed;
    }

    public Set<CurrencyType> getAvailableRates() {
        return availableRates;
    }

    public void setAvailableRates(Set<CurrencyType> availableRates) {
        this.availableRates = availableRates;
    }

    public String getAvailableRateValues() {
        return availableRateValues;
    }

    public void setAvailableRateValues(String availableRateValues) {
        this.availableRateValues = availableRateValues;
    }
}
