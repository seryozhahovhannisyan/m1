package com.connectto.wallet.merchant.common.data.transaction.deposit;


import java.util.Date;

/**
 * Created by Serozh on 10/19/2016.
 * WalletApi-> com.connectto.wallet.model.transaction.merchant.deposit.MerchantDeposit
 */
public class WalletDeposit {

    private Long id;

    private Long itemId;
    private String name;
    private String description;

    private Date startAt;
    private Date endAt;

    private WalletDepositTax walletDepositTax;
    private Long walletDepositTaxId;

    /*
     * #################################################################################################################
     * ########################################        GETTER & SETTER       ###########################################
     * #################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public WalletDepositTax getWalletDepositTax() {
        return walletDepositTax;
    }

    public void setWalletDepositTax(WalletDepositTax walletDepositTax) {
        this.walletDepositTax = walletDepositTax;
    }

    public Long getWalletDepositTaxId() {
        return walletDepositTaxId;
    }

    public void setWalletDepositTaxId(Long walletDepositTaxId) {
        this.walletDepositTaxId = walletDepositTaxId;
    }
}

