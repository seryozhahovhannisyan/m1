package com.connectto.wallet.merchant.web.action;

import org.apache.log4j.Logger;

public class ExceptionHandlerAction extends BaseAction {

    /**
     * General logger
     */
    private Logger logger = Logger.getLogger(ExceptionHandlerAction.class);

    /**
     * thrown exception
     */
    private Exception exception;

    /**
     * Makes a log for thrown exception.
     * Returns status of execution (error)
     * @return <code>java.lang.String</code>
     */
    public String execute() {

        if (exception != null) {
            logger.error("Exception occurred", exception);
        }


        return SUCCESS;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
