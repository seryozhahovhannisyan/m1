package com.connectto.wallet.merchant.web.interceptor;

import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.web.action.BaseActionConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Map;

public class AuthenticateInterceptor extends AbstractInterceptor implements BaseActionConstants {

    private long INTERVAL = 120000000; // 2 minutes in milliseconds

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext context = actionInvocation.getInvocationContext();

        Map<String, Object> session = context.getSession();

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);
        if (cashier == null) {
            return GLOBAL_RESULT_LOGIN;
        }

        if (session.get(COOKIE_MODERATOR_CASHIER_DATA) != null) {

            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            if (cookies != null && cookies.length > 0) {

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase(COOKIE_MODERATOR_LAST_ACTIVITY)) {
                        long lastActivity = Long.parseLong(cookie.getValue());
                        if (System.currentTimeMillis() - lastActivity > INTERVAL) {

                            // removes cookie
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            // removes session data
                            session.clear();
                        } else {
                            cookie.setMaxAge(DEFAULT_TIMEOUT_MODERATOR);
                            cookie.setValue(String.valueOf(System.currentTimeMillis()));

                            // executes required action
                            return actionInvocation.invoke();
                        }
                    }
                }
            }
        }

        //return "login"; // goes to login page
        return actionInvocation.invoke();
    }
}
