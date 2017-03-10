package com.connectto.wallet.merchant.common.data.transaction.withdraw;


import java.util.Date;

/**
 * Created by Serozh on 10/19/2016.
 * WalletApi-> com.connectto.wallet.model.transaction.merchant.withdraw.MerchantWithdraw
 */
public class WalletWithdraw {

    private Long id;

    private Long itemId;
    private String name;
    private String description;

    private Date startAt;
    private Date endAt;

    private WalletWithdrawTax walletWithdrawTax;
    private Long walletWithdrawTaxId;

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

    public WalletWithdrawTax getWalletWithdrawTax() {
        return walletWithdrawTax;
    }

    public void setWalletWithdrawTax(WalletWithdrawTax walletWithdrawTax) {
        this.walletWithdrawTax = walletWithdrawTax;
    }

    public Long getWalletWithdrawTaxId() {
        return walletWithdrawTaxId;
    }

    public void setWalletWithdrawTaxId(Long walletWithdrawTaxId) {
        this.walletWithdrawTaxId = walletWithdrawTaxId;
    }
}

