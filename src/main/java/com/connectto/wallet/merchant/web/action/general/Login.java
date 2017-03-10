package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.business.merchant.ITransactionManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.data.transaction.cashbox.CashierCashBox;
import com.connectto.wallet.merchant.common.data.transaction.lcp.TransactionState;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 7/4/2016.
 */
public class Login extends BaseAction {


    private ICashierManager cashierManager;

    private ITransactionManager transactionManager;

    private static Logger logger = Logger.getLogger(Login.class);

    private String username;

    private String password;

    @SkipValidation
    public String view() {
        return SUCCESS;
    }

    public String authenticate() {
        try {
            Cashier cashier = cashierManager.login(username, password);
            if (cashier.getStatus() == Status.ACTIVE) {
                // stores last activity time via cookie
                Cookie cookie = new Cookie(COOKIE_MODERATOR_LAST_ACTIVITY, String.valueOf(System.currentTimeMillis()));
                cookie.setMaxAge(DEFAULT_TIMEOUT_MODERATOR);
                ServletActionContext.getResponse().addCookie(cookie);


                logger.info(String.format("User Logged In :[ %s, %s, %s ]", cashier.getName(), cashier.getEmail(), cashier.getPrivilege().toString()));
            } else {
                String message = String.format("User %s account is disabled", username);
                addActionError(message);
                logger.warn(message);
                return INPUT;
            }

            CashierCashBox cashBox = cashier.getCurrentCashBox();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cashBoxId", cashBox.getId());
            params.put("states", DataConverter.convertIdesToString(Arrays.asList(new Integer[]{TransactionState.PENDING.getId()})));

            int pendingCount = transactionManager.getWithdrawsCountByParams(params);
            //pendingCount += transactionManager.getDepositsCountByParams(params);

            cashier.setPendingCount(pendingCount);
            session.put(SESSION_CASHIER, cashier);
        } catch (Exception e) {
            logger.error(e);
            session.put(MESSAGE, "Internal Server Exception");
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (Utils.isEmpty(username)) {
            addFieldError("username", "username can not be empty");
        }

        if (Utils.isEmpty(password)) {
            addFieldError("password", "password can not be empty");
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }

    public void setTransactionManager(ITransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}