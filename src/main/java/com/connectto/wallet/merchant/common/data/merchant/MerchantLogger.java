package com.connectto.wallet.merchant.common.data.merchant;

import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;

import java.util.Date;

/**
 * Created by htdev001 on 11/14/14.
 */
public class MerchantLogger {

    private Long id;
    private LogLevel logLevel;
    private String className;
    private String methodName;
    private LogAction logAction;
    private String message;
    private Date date;

    public MerchantLogger() {
    }

    public MerchantLogger(LogLevel logLevel, LogAction logAction, String message, Date date) {
        this.logLevel = logLevel;
        this.logAction = logAction;
        this.message = message;
        this.date = date;
    }

    public MerchantLogger(String className, LogLevel logLevel, LogAction logAction, String message, Date date) {
        this.className = className;
        this.logLevel = logLevel;
        this.logAction = logAction;
        this.message = message;
        this.date = date;
    }

    public MerchantLogger(Long walletId, String className, LogLevel logLevel, LogAction logAction, String message, Date date) {
        this.className = className;
        this.logLevel = logLevel;
        this.logAction = logAction;
        this.message = message;
        this.date = date;
    }

    /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public LogAction getLogAction() {
        return logAction;
    }

    public void setLogAction(LogAction logAction) {
        this.logAction = logAction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
