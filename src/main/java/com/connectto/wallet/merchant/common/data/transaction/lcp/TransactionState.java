package com.connectto.wallet.merchant.common.data.transaction.lcp;

public enum TransactionState {

    PREPARE(1, "prepare transaction", "enum.TransactionState.PREPARE"),
    PENDING(2, "pending transaction", "enum.TransactionState.PENDING"),
    TIME_OUTED(3, "time outed transaction", "enum.TransactionState.TIME_OUTED"),
    CANCELED(4, "canceled transaction", "enum.TransactionState.CANCELED"),
    RELEASED(5, "released transaction", "enum.TransactionState.RELEASED");

    TransactionState(int id, String state, String description) {
        this.id = id;
        this.state = state;
        this.description = description;
    }

    public static TransactionState getDefault() {
        return null;
    }

    public static synchronized TransactionState valueOf(final int value) {
        for (TransactionState e : TransactionState.values()) {
            if (e.id == value) {
                return e;
            }
        }
        return getDefault();
    }

    private final int id;
    private final String state;
    private final String description;

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }
}
