package com.connectto.wallet.merchant.common.data.wallet;

import com.connectto.wallet.merchant.common.data.merchant.lcp.CurrencyType;

import java.io.Serializable;

/**
 * Created by htdev001 on 8/25/14.
 */
public class Wallet implements Serializable{

    private Long id;

    private Long userId;
    private int partitionId;

    private String name;
    private String surname;
    private String email;

    private Double money;
    private Double frozenAmount;
    private Double receivingAmount;
    private CurrencyType currencyType;
    private boolean isLocked;

    /**
     * ##################################################################################################################
     * Getters Setter
     * ##################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Double getReceivingAmount() {
        return receivingAmount;
    }

    public void setReceivingAmount(Double receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }
}