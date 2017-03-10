package com.connectto.wallet.merchant.web.action.dto;

/**
 * Created by Serozh on 11/21/2016.
 */
public class WalletDto {

    private String id;
    private String partitionId;
    private String img;
    private String name;
    private String surname;
    private String email;
    private String money;
    private String frozenAmount;
    private String receivingAmount;
    private String currencyType;
    private String isLocked;

    /*
    * #################################################################################################################
    * ########################################        GETTER & SETTER       ###########################################
    * #################################################################################################################
    */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getReceivingAmount() {
        return receivingAmount;
    }

    public void setReceivingAmount(String receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public WalletDto setIsLocked(String locked) {
        isLocked = locked;
        return this;
    }
}
