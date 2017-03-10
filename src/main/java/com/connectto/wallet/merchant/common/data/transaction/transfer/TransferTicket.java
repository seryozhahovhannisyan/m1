package com.connectto.wallet.merchant.common.data.transaction.transfer;


/**
 * Created by Serozh on 10/19/2016.
 */
public class TransferTicket {

    private Long id;
    private Long itemId;
    private Long walletId;
    private String name;
    private String description;
    //
    private String secretKey;
    private String clientKey;
    private Long tsmCompanyId;


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

    public Long getWalletId() {
        return walletId;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public Long getTsmCompanyId() {
        return tsmCompanyId;
    }

    public void setTsmCompanyId(Long tsmCompanyId) {
        this.tsmCompanyId = tsmCompanyId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}