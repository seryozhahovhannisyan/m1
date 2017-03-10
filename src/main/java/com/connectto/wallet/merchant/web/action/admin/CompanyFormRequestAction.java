package com.connectto.wallet.merchant.web.action.admin;

import com.connectto.wallet.merchant.business.merchant.ICompanyFormRequestManager;
import com.connectto.wallet.merchant.common.data.merchant.CompanyFormRequest;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogAction;
import com.connectto.wallet.merchant.common.data.merchant.lcp.LogLevel;
import com.connectto.wallet.merchant.common.data.merchant.lcp.Status;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;
import com.connectto.wallet.merchant.web.action.util.CaptchaUtil;
import com.connectto.wallet.merchant.web.action.util.notification.MailContent;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.Date;

/**
 * Created by Serozh on 6/26/2016.
 */
public class CompanyFormRequestAction extends BaseAction {

    private static Logger logger = Logger.getLogger(CompanyFormRequestAction.class);

    private ICompanyFormRequestManager companyFormRequestManager;
    private ResponseDto dto;
    //tsmCompanyId
    private String companyId;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private String companyPhoneCode;
    private String companyPhone;
    private String countOfBranches;
    private String countOfWorkers;
    private String contactName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhoneCode;
    private String contactPhone;
    private String message;

    public String clientSendRequest() {

        if (!CaptchaUtil.isValid(getHttpServletRequest())) {
            addFieldError("password", getText("errors.invalid.captcha.or.psw"));
            return ERROR;
        }

        CompanyFormRequest formRequest = new CompanyFormRequest();
        formRequest.setCompanyName(companyName);
        formRequest.setCompanyAddress(companyAddress);
        formRequest.setCompanyEmail(companyEmail);
        formRequest.setCompanyPhoneCode(companyPhoneCode);
        formRequest.setCompanyPhone(companyPhone);
        formRequest.setCountOfBranches(countOfBranches);
        formRequest.setCountOfWorkers(countOfWorkers);
        formRequest.setContactName(contactName);
        formRequest.setContactLastName(contactLastName);
        formRequest.setContactEmail(contactEmail);
        formRequest.setContactPhoneCode(contactPhoneCode);
        formRequest.setContactPhone(contactPhone);
        formRequest.setMessage(message);
        formRequest.setStatus(Status.UNVERIFIED);
        formRequest.setRequestedAt(new Date(System.currentTimeMillis()));

        StringWriter writer = new StringWriter();
        Template template = null;
        try {
            template = Velocity.getTemplate("requestform.vm");
        } catch (Exception e) {
            logger.error(e);
        }

        String fromEmail = Initializer.getSetupInfo().notificationEmailUsername;
        VelocityContext context = new VelocityContext();

        context.put("Greeting", "");
        context.put("Name", "Administrator");
        context.put("title", "New Request Form");
        context.put("ignore", "");
        context.put("message", DataConverter.convertCompanyFormRequestToMessage(formRequest));
        context.put("Action", "");
        context.put("goto", "");
        template.merge(context, writer);

        MailContent mailContent = new MailContent();
        mailContent.setEmailsTo(new String[]{fromEmail, "seryozha.hovhannisyan@gmail.com"});
        //mailContent.setEmailsBCC(new String[]{fromEmail});
        mailContent.setSubject("New Request Form");
        mailContent.setContent(writer.toString());
        mailContent.setRecipientTypeTo();

        String contentPath = Initializer.context.getRealPath("");
        StringBuilder logo = new StringBuilder(contentPath);
        logo.append(File.separator).append("img").
                append(File.separator).append("general").
                append(File.separator).append("logo.png");


        mailContent.addDataSource("logo", logo.toString());

        try {
            companyFormRequestManager.add(mailContent, formRequest);
        } catch (InternalErrorException e) {
            writeLog(CompanyFormRequestAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
        }

        return SUCCESS;
    }

    public String convertSendRequest() {

        if (!validateConvertSendRequest()) {
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            return SUCCESS;
        }
        Long tsmCompanyID = null;
        try {
            if (Utils.isEmpty(companyId)) {
                dto.addFieldError("tsmCompanyId", "is empty");
                dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
                return SUCCESS;
            }
            tsmCompanyID = DataConverter.convertToLong(companyId);
        } catch (DataParseException e) {
            dto.addFieldError("tsmCompanyId", "is empty");
            dto.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            return SUCCESS;
        }

        CompanyFormRequest formRequest = new CompanyFormRequest();
        formRequest.setTsmCompanyId(tsmCompanyID);
        formRequest.setCompanyName(companyName);
        formRequest.setCompanyAddress(companyAddress);
        formRequest.setCompanyEmail(companyEmail);
        formRequest.setCompanyPhoneCode(companyPhoneCode);
        formRequest.setCompanyPhone(companyPhone);
        formRequest.setCountOfBranches(countOfBranches);
        formRequest.setCountOfWorkers(countOfWorkers);
        formRequest.setContactName(contactName);
        formRequest.setContactLastName(contactLastName);
        formRequest.setContactEmail(contactEmail);
        formRequest.setContactPhoneCode(contactPhoneCode);
        formRequest.setContactPhone(contactPhone);
        formRequest.setMessage(message);
        formRequest.setStatus(Status.UNCONVERTED);
        formRequest.setRequestedAt(new Date(System.currentTimeMillis()));

        StringBuilder context = new StringBuilder();
        context.append("Dear Administrator <br/>");
        context.append("New Request Form <br/>");
        context.append(DataConverter.convertCompanyFormRequestToMessage(formRequest));

        String fromEmail = Initializer.getSetupInfo().notificationEmailUsername;

        MailContent mailContent = new MailContent();
        mailContent.setEmailsTo(new String[]{fromEmail, "seryozha.hovhannisyan@gmail.com"});
        mailContent.setSubject("New Request Form");
        mailContent.setContent(context.toString());
        mailContent.setRecipientTypeTo();

        try {
            companyFormRequestManager.add(mailContent, formRequest);
            dto.setActionmessage("Data added successfully");
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            writeLog(CompanyFormRequestAction.class.getSimpleName(), e, LogLevel.ERROR, LogAction.CREATE, null);
            dto.setActionmessage(e.getMessage());
            dto.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
        }

        return SUCCESS;
    }

    private boolean validateConvertSendRequest() {
        if (Utils.isEmpty(companyName)) {
            addFieldError("companyName", "is empty");
            dto.addFieldError("companyName", "is empty");
            return false;
        }
        if (Utils.isEmpty(companyAddress)) {
            addFieldError("companyAddress", "is empty");
            dto.addFieldError("companyAddress", "is empty");
            return false;
        }
        if (Utils.isEmpty(companyEmail)) {
            addFieldError("companyEmail", "is empty");
            dto.addFieldError("companyEmail", "is empty");
            return false;
        }
        if (Utils.isEmpty(companyPhoneCode)) {
            addFieldError("companyPhoneCode", "is empty");
            dto.addFieldError("companyPhoneCode", "is empty");
            return false;
        }
        if (Utils.isEmpty(companyPhone)) {
            addFieldError("companyPhone", "is empty");
            dto.addFieldError("companyPhone", "is empty");
            return false;
        }
        if (Utils.isEmpty(countOfBranches)) {
            addFieldError("countOfBranches", "is empty");
            dto.addFieldError("countOfBranches", "is empty");
            return false;
        }
        if (Utils.isEmpty(countOfWorkers)) {
            addFieldError("countOfWorkers", "is empty");
            dto.addFieldError("countOfWorkers", "is empty");
            return false;
        }
        if (Utils.isEmpty(contactName)) {
            addFieldError("contactName", "is empty");
            dto.addFieldError("contactName", "is empty");
            return false;
        }
        if (Utils.isEmpty(contactLastName)) {
            addFieldError("contactLastName", "is empty");
            dto.addFieldError("contactLastName", "is empty");
            return false;
        }
        if (Utils.isEmpty(companyEmail)) {
            addFieldError("companyEmail", "is empty");
            dto.addFieldError("companyEmail", "is empty");
            return false;
        }
        if (Utils.isEmpty(contactPhoneCode)) {
            addFieldError("contactPhoneCode", "is empty");
            dto.addFieldError("contactPhoneCode", "is empty");
            return false;
        }
        if (Utils.isEmpty(contactPhone)) {
            addFieldError("contactPhone", "is empty");
            dto.addFieldError("contactPhone", "is empty");
            return false;
        }
        if (Utils.isEmpty(message)) {
            addFieldError("message", "is empty");
            dto.addFieldError("message", "is empty");
            return false;
        }


        return true;
    }

     /*##################################################################################################################
     *                                  GETTERS & SETTERS
     *##################################################################################################################
     */

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhoneCode() {
        return companyPhoneCode;
    }

    public void setCompanyPhoneCode(String companyPhoneCode) {
        this.companyPhoneCode = companyPhoneCode;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCountOfBranches() {
        return countOfBranches;
    }

    public void setCountOfBranches(String countOfBranches) {
        this.countOfBranches = countOfBranches;
    }

    public String getCountOfWorkers() {
        return countOfWorkers;
    }

    public void setCountOfWorkers(String countOfWorkers) {
        this.countOfWorkers = countOfWorkers;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneCode() {
        return contactPhoneCode;
    }

    public void setContactPhoneCode(String contactPhoneCode) {
        this.contactPhoneCode = contactPhoneCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseDto getDto() {
        return dto;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setCompanyFormRequestManager(ICompanyFormRequestManager companyFormRequestManager) {
        this.companyFormRequestManager = companyFormRequestManager;
    }
}