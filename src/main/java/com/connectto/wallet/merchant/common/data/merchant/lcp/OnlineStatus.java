package com.connectto.wallet.merchant.common.data.merchant.lcp;


/**
 * Created by htdev001 on 2/24/14.
 */
public enum OnlineStatus {

    ONLINE(1, "Online"),
    AWAY(2, "Away"),
    DO_NOT_DISTURB(3, "Do not Disturb"),
    INVISIBLE(4, "Invisible"),
    OFFLINE(5, "Offline");

    OnlineStatus(int key, String status) {
        this.key = key;
        this.status = status;
    }

    public static OnlineStatus valueOf(int key) {

        for (OnlineStatus onlineStatus : OnlineStatus.values()) {
            if (onlineStatus.getKey() == key) {
                return onlineStatus;
            }
        }

        return null;
    }

    public static OnlineStatus[] getValues(Language language) {
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    private int key;
    private String status;
}
