package com.connectto.wallet.merchant.web.interceptor;

import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.web.action.BaseActionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class JSONAuthenticationInterceptor extends AbstractInterceptor implements BaseActionConstants {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);

        if (cashier == null) {
            ActionSupport action = (ActionSupport) invocation.getAction();
            Map<String, Object> params = context.getParameters();
            params.put("status", Action.LOGIN);
            params.put("message", action.getText("err.msg.not.authenticated"));
            return Action.LOGIN;
        }

        return invocation.invoke();
    }
}
