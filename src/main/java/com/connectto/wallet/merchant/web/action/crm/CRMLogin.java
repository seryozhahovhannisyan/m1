package com.connectto.wallet.merchant.web.action.crm;


import com.connectto.wallet.merchant.web.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by Serozh on 6/21/2016.
 */
public class CRMLogin extends BaseAction {

    //private IUserManager userManager;

    private static Logger logger = Logger.getLogger(CRMLogin.class);

    private String username;

    private String password;

    @SkipValidation
    public String view() {
        return SUCCESS;
    }

    /**public String authenticate() {

        if (!Initializer.getCRMLogin().equals(username) || !Initializer.getCRMPassword().equals(password)) {
            String message = String.format("User not found for [%s]", username);
            addActionError(message);
            logger.warn(message);
            return INPUT;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.ADMIN);

        // stores last activity time via cookie
        Cookie cookie = new Cookie(COOKIE_CRM_LAST_ACTIVITY, String.valueOf(System.currentTimeMillis()));
        cookie.setMaxAge(DEFAULT_TIMEOUT_CRM);
        ServletActionContext.getResponse().addCookie(cookie);

        session.put(COOKIE_CRM_USER_DATA, user);

        logger.info(String.format("User Logged In :[ %s, %s, %s ]", user.getUsername(), user.getEmail(), user.getRole().toString()));

        return SUCCESS;
    }*/

    @Override
    public void validate() {
        /*if (CommonValidator.isEmpty(username)) {
            addFieldError("username", "username can not be empty");
        }

        if (CommonValidator.isEmpty(password)) {
            addFieldError("password", "password can not be empty");
        }*/
    }

    /*public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }*/

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
}
