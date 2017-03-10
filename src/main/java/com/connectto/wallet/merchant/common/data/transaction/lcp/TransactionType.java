package com.connectto.wallet.merchant.common.data.transaction.lcp;

import java.util.ArrayList;
import java.util.List;

public enum TransactionType {

    WALLET(1, "Wallet","wallet_money_logo.png",false),
    ;

    TransactionType(int id, String value, String logo, boolean isCreditCard) {
        this.id = id;
        this.value = value;
        this.logo = logo;
        this.isCreditCard = isCreditCard;
    }

    public static TransactionType getDefault() {
        return null;
    }

    public static synchronized TransactionType valueOf(final int value) {
        for (TransactionType e : TransactionType.values()) {
            if (e.id == value) {
                return e;
            }
        }
        return getDefault();
    }

    public static synchronized List<TransactionType> getCreditCards() {
        List<TransactionType> creditCards = new ArrayList<TransactionType>();
        for (TransactionType e : TransactionType.values()) {
            if (e.isCreditCard) {
                creditCards.add(e);
            }
        }
        return creditCards;
    }

    private final int id;
    private final String value;
    private final String logo;
    private final boolean isCreditCard;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getLogo() {
        return logo;
    }

    public boolean getIsCreditCard() {
        return isCreditCard;
    }
}
