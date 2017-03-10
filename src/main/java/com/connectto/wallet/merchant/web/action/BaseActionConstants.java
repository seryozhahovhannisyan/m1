package com.connectto.wallet.merchant.web.action;

/**
 * Created by Serozh on 6/21/2016.
 */
public interface BaseActionConstants {

    public static final String MESSAGE = "message";
    public static final String INFO = "info";

    public static final int DEFAULT_TIMEOUT_CRM = 36000;
    public static final int DEFAULT_TIMEOUT_MODERATOR = 36000;

    public static final String COOKIE_MODERATOR_CASHIER_DATA = "cashier";
    public static final String COOKIE_MODERATOR_LAST_ACTIVITY = "cookie-last_activity";

    public static final String DEFAULT_CASHIER_REF = "cashier-home";

    public static final String SESSION_CASHIER = "cashier";

    public static final String GLOBAL_RESULT_LOGIN = "login";
    public static final String GLOBAL_RESULT_PENDING_TRANSACTION = "pending";

    public static final String LANGUAGE            = "request_locale";
}
