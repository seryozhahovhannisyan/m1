package com.connectto.wallet.merchant.web.action.util;

import com.connectto.wallet.merchant.web.util.Initializer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by htdev01 on 11/26/15.
 */
public class CaptchaUtil {

    public static boolean isValid(HttpServletRequest httpServletRequest){
        String recaptchaSecretKey = Initializer.getSetupInfo().recaptchaSecretKey;
        String recap = httpServletRequest.getParameter("g-recaptcha-response");
        String remoteAddress = httpServletRequest.getRemoteAddr();

        CaptchaResponse capRes = HttpURLBaseConnection.googleReCaptchaSiteVerify(recaptchaSecretKey, recap, remoteAddress);
        if (capRes.isSuccess()) {
            // Input by Human
            httpServletRequest.setAttribute("verified", "true");
            return true;
        } else {
            // Input by Robot
            httpServletRequest.setAttribute("verified", "false");
            return false;
        }
    }
}
