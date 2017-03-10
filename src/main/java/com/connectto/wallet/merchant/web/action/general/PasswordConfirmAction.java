package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.util.CaptchaResponse;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Initializer;

/**
 * Created by htdev001 on 9/2/14.
 */
public class PasswordConfirmAction extends BaseAction {

    private ICashierManager cashierManager;

    private String password;
    private String newPassword;

    public String execute() {

        String recaptchaSecretKey = Initializer.getSetupInfo().recaptchaSecretKey;
        String recap = getHttpServletRequest().getParameter("g-recaptcha-response");
        String remoteAddress = getHttpServletRequest().getRemoteAddr();

        CaptchaResponse capRes = HttpURLBaseConnection.googleReCaptchaSiteVerify(recaptchaSecretKey,recap,remoteAddress);
        if(capRes.isSuccess()) {
            // Input by Human
            getHttpServletRequest().setAttribute("verified", "true");
        } else {
            // Input by Robot
            getHttpServletRequest().setAttribute("verified", "false");
            addFieldError("password", getText("errors.invalid.captcha.or.psw"));
            return INPUT;
        }

        Cashier cashier = (Cashier) session.get(SESSION_CASHIER);

        if (cashier == null ) {
            getHttpServletRequest().setAttribute("verified", "false");
            return   INPUT;
        }

        String cashierPassword = cashier.getPassword();

        try {

            if(!Utils.isEmpty(cashierPassword)){
                password = SHAHashEnrypt.get_MD5_SecurePassword(password);
                if (!password.equals(cashier.getPassword())) {
                    addFieldError("password", getText("errors.invalid.captcha.or.psw"));
                    getHttpServletRequest().setAttribute("verified", "false");
                    return  INPUT;
                }
            } else {
                password = SHAHashEnrypt.get_MD5_SecurePassword(password);
                newPassword  = SHAHashEnrypt.get_MD5_SecurePassword(newPassword);
               /* if (!password.equals(user.getPassword())) {
                    addFieldError("password", getText("errors.invalid.captcha.or.psw"));
                    getHttpServletRequest().setAttribute("verified", "false");
                    return  getIsMobile() ? "m-input" : INPUT;
                }*/
                cashier.setPassword(newPassword);
                cashierManager.update(cashier);
            }

        }  catch (Exception e) {
            writeLog(PasswordConfirmAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            getHttpServletRequest().setAttribute("verified", "false");
            return   INPUT;
        }

        session.put("confirmed", true);
        getHttpServletRequest().setAttribute("verified", "true");



        return SUCCESS;
    }

    @Override
    public void validate() {
        if (Utils.isEmpty(password)) {
            addFieldError("password", getText("errors.required", new String[]{getText("pages.registration.password")}));
        }
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCashierManager(ICashierManager cashierManager) {
        this.cashierManager = cashierManager;
    }
}
