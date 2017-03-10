package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.business.merchant.ICashierManager;
import com.connectto.wallet.merchant.common.data.merchant.Cashier;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.util.CaptchaResponse;
import com.connectto.wallet.merchant.web.action.util.GeneralUtil;
import com.connectto.wallet.merchant.web.action.util.HttpURLBaseConnection;
import com.connectto.wallet.merchant.web.action.util.encryption.EncryptException;
import com.connectto.wallet.merchant.web.action.util.encryption.SHAHashEnrypt;
import com.connectto.wallet.merchant.web.util.Initializer;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 11.11.13
 * Time: 0:51
 * To change this template use File | Settings | File Templates.
 */
public class PasswordRememberAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(PasswordRememberAction.class.getSimpleName());

    private ICashierManager cashierManager;
    //private MailTaskManager mailTaskManager;

    private String user;
    private String token;
    private String password;

    private ResponseDto actionDto;

    @SkipValidation
    public String redirectResetPassword() {
        logger.info("redirectResetPassword user " + user + " token " + token);
        session.put("u", user);
        session.put("t", token);
        return SUCCESS;
    }

    @SkipValidation
    public String resetPassword() {

        String u = (String)session.get("u");
        String t = (String)session.get("t");
        session.remove("u");
        session.remove("t");
        logger.info("header user " + u + " token " + t);

        if (Utils.isEmpty(u) || Utils.isEmpty(t)) {
            addActionError(getText("page.reset.expired.url"));
            return ERROR;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("u",u);
        params.put("t",t);

        try {

           List<Cashier> cashiers =  cashierManager.getByParams(params);
            session.put(ScopeKeys.PASSWORD_CONFIRMATION, cashiers);
        } catch (InternalErrorException e1) {
            logger.error(e1);

            addActionError(getText("page.reset.expired.url"));
            return ERROR;
        }

        return  SUCCESS;
    }

    public String execute() {

        //actionDto.cleanMessages();

        List<Cashier> cashiers = (List<Cashier>)session.get(ScopeKeys.PASSWORD_CONFIRMATION);

        try {

            String token = GeneralUtil.generatePasswordToken();
            Cashier updateWallet= new Cashier();
            //updateWallet.setUserId(wallet.getUserId());
            updateWallet.setResetPasswordToken(token);
            //walletManager.resetPassword(updateWallet);

             /*StringBuilder gotoBuilder = new StringBuilder(partitionHost);
            gotoBuilder.append("/wallet");
            gotoBuilder.append("/redirect-wallet-reset-password").append(".htm?").
                    append("user=").append(user.getId()).
                    append("&token=").append(token);

           Partition partition = partitionSetupManager.getPartitionById(partitionId);

            String fromEmail = partition.getPartitionEmail();
            String fromEmailPassword = partition.getPartitionEmailPassword();
            if (Utils.isEmpty(fromEmail) || Utils.isEmpty(fromEmailPassword)) {
                actionDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                return SUCCESS;
            }

            String partitionName = partition.getName();*/

            /*String subject = getText("mail.subject.reset.password", new String[]{partitionName});

            StringWriter writer = new StringWriter();
            Template template = Velocity.getTemplate("mail" + partitionDns + ".vm","UTF-8");
            VelocityContext context = new VelocityContext();

            context.put("Greeting", getText("mail.greeting"));
            context.put("Name", String.format("%s %s", user.getName(), user.getSurname()));
            context.put("title", getText("mail.title.reset.password"));
            context.put("ignore", getText("mail.ignore"));
            context.put("Action", getText("mail.action.reset.password"));
            context.put("goto", gotoBuilder.toString());
            template.merge(context, writer);

            String contentPath = getHttpServletRequest().getServletContext().getRealPath("");

            StringBuilder logo = new StringBuilder(contentPath);
            logo.append(File.separator).append("img").
                    append(File.separator).append("general").
                    append(File.separator).append(partitionDns).
                    append(File.separator).append("logo.png");

            MailContent mailContent = new MailContent();
            mailContent.setEmailsTo(new String[]{"seryozha.hovhannisyan@gmail.com", user.getEmail(), fromEmail});
            mailContent.setSubject(subject);
            mailContent.setContent(writer.toString());
            mailContent.setRecipientTypeTo();
            mailContent.addDataSource("logo", logo.toString());

            Notification passwordNotification = new MailNotification(mailContent, fromEmail, fromEmailPassword);
            mailTaskManager.addNotification(passwordNotification);

            responseDto.setStatus(ResponseStatus.SUCCESS);*/
        } catch (Exception e) {
            logger.error(e);
            actionDto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }
        return SUCCESS;
    }

    public String reset() {

        Cashier sesCashier = null; //(Cashier) session.remove(ConstantGeneral.RESET_SESSION_WALLET);
        if (sesCashier == null) {
            logger.error("Incorrect incoming data, incoming browser user was null");
            String message = getText("page.reset.expired.url");
            addActionError(message);
            return   ERROR;
        }

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
            return    ERROR;
        }

        if (Utils.isEmpty(password)) {
            logger.info("Validation not passed empty password");
            addFieldError("password", getText("errors.required", new String[]{getText("pages.registration.password")}));
            addActionError(getText("errors.required", new String[]{getText("pages.registration.password")}));
            return  ERROR;
        } else if (!GeneralUtil.isValidPassword(password)) {
            logger.info("Validation not passed empty password");
            addFieldError("password", getText("errors.required", new String[]{getText("pages.registration.password.lengt.6")}));
            addActionError(getText("errors.required", new String[]{getText("pages.registration.password.lengt.6")}));
            return   ERROR;
        }

        try {
            password = SHAHashEnrypt.get_MD5_SecurePassword(password);
        } catch (EncryptException e) {
            logger.warn(e);
        }

        Cashier cashier = new Cashier();
        cashier.setId(sesCashier.getId());
        cashier.setPassword(password);
        cashier.setResetPasswordToken("");
        try {
            cashierManager.update(cashier);
            addActionMessage(getText("pages.reset.success"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(e);
            addActionError(getText("error.internal"));
            return ERROR;
        } catch (EntityNotFoundException e) {
            logger.error(e);
            addActionError(getText("error.internal"));
            return   ERROR;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseDto getActionDto() {
        return actionDto;
    }

    public void setUser(String user) {
        this.user = user;
    }



    public void setActionDto(ResponseDto actionDto) {
        this.actionDto = actionDto;
    }

}
