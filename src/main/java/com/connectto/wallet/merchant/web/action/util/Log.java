package com.connectto.wallet.merchant.web.action.util;

import org.apache.log4j.Logger;

public final class Log {

    private Log() {
    }


    public static synchronized Logger inform() {
        return Logger.getLogger("Informer");
    }

    public static synchronized Logger track() {
        return Logger.getLogger("Tracker");
    }


}
