package com.connectto.wallet.merchant.common.data.transaction.lcp;

public enum TransactionRationalDuration {

    MINUTES_5       (1, 300,   "page.wallet.duration.minute.5"),
    MINUTES_15      (2, 900,   "page.wallet.duration.minute.15"),
    HALF_HOUR       (3, 1800,  "page.wallet.duration.minute.hour.half"),
    HOUR            (4, 3600,  "page.wallet.duration.minute.hour"),
    DAY             (5, 86400, "page.wallet.duration.minute.day");

    TransactionRationalDuration(int id, long duration, String description) {
        this.id = id;
        this.duration = duration;
        this.description = description;
    }

    public static TransactionRationalDuration getDefault() {
        return null;
    }

    public static synchronized TransactionRationalDuration valueOf(final int value) {
        for (TransactionRationalDuration e : TransactionRationalDuration.values()) {
            if (e.id == value) {
                return e;
            }
        }
        return getDefault();
    }

    private final int id;
    private final long duration;
    private final String description;

    public int getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
