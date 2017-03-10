package com.connectto.wallet.merchant.web.interceptor;

import com.connectto.wallet.merchant.web.action.BaseActionConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Map;

public class LoggedInInterceptor extends AbstractInterceptor implements BaseActionConstants {

    private long INTERVAL = 120000000; // 2 minutes in milliseconds

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext context = actionInvocation.getInvocationContext();

        Map<String, Object> session = context.getSession();
        if (session.get(COOKIE_MODERATOR_CASHIER_DATA) != null) {

            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            if (cookies != null && cookies.length > 0) {

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase(COOKIE_MODERATOR_LAST_ACTIVITY)) {
                        long lastActivity = Long.parseLong(cookie.getValue());
                        if (System.currentTimeMillis() - lastActivity < INTERVAL) {

                            // executes required action
                            return DEFAULT_CASHIER_REF;
                        }
                    }
                }
            }
        }

        return actionInvocation.invoke(); // goes to login page
    }
}
