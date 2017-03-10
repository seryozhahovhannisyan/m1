package com.connectto.wallet.merchant.common.util;

import org.apache.log4j.Logger;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

/**
 * Created by Serozh on 6/21/2016.
 */
public class VelocityLogger extends Logger implements LogChute {

    public VelocityLogger(Class clazz) {
        super(clazz.getName());
    }

    @Override
    public void init(RuntimeServices runtimeServices) throws Exception {
        //super.init();
    }

    @Override
    public void log(int i, String s) {
        switch (i) {
            case TRACE_ID:
                super.trace(s);
                break;
            case DEBUG_ID:
                super.debug(s);
                break;
            case INFO_ID:
                super.info(s);
                break;
            case WARN_ID:
                super.warn(s);
                break;
            case ERROR_ID:
                super.error(s);
        }
    }

    @Override
    public void log(int i, String s, Throwable throwable) {
        switch (i) {
            case TRACE_ID:
                super.trace(s, throwable);
                break;
            case DEBUG_ID:
                super.debug(s, throwable);
                break;
            case INFO_ID:
                super.info(s, throwable);
                break;
            case WARN_ID:
                super.warn(s, throwable);
                break;
            case ERROR_ID:
                super.error(s, throwable);
        }
    }

    @Override
    public boolean isLevelEnabled(int i) {
        switch (i) {
            case TRACE_ID:
                return super.isTraceEnabled();
            case DEBUG_ID:
                return super.isDebugEnabled();
            case INFO_ID:
                return super.isInfoEnabled();
            case WARN_ID:
                return true;
            case ERROR_ID:
                return true;
        }

        return false;
    }
}
