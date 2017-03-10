package com.connectto.wallet.merchant.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public abstract class AbstractInterceptor implements Interceptor {

    @Override
    public void init() {}

    @Override
    public abstract String intercept(ActionInvocation actionInvocation) throws Exception;

    @Override
    public void destroy() {}
}
