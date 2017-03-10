package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.util.notification.MailContent;
import com.connectto.wallet.merchant.web.action.util.notification.MailException;
import com.connectto.wallet.merchant.web.action.util.notification.MailSender;
import com.connectto.wallet.merchant.web.util.Initializer;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;

/**
 * Created by htdev01 on 11/9/15.
 */
public class MailAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(MailAction.class.getSimpleName());
    //private MailTaskManager mailTaskManager;
    private ResponseDto responseDto;

    private String subject;
    private String email;

    private String name;
    private String surname;

    private String message;
    private String title;

    public String execute(){

        String fromEmail = Initializer.getSetupInfo().notificationEmailUsername;
        String fromEmailPassword = Initializer.getSetupInfo().notificationEmailPassword;

        StringWriter writer = new StringWriter();
        Template template = null;
        try {
            template = Velocity.getTemplate("mail.vm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        VelocityContext context = new VelocityContext();

        context.put("Greeting","" );
        //context.put("Name", String.format("%s %s", user.getName(), user.getSurname()));
        context.put("Name", String.format("%s %s", name, surname));
        context.put("title", title );
        context.put("ignore", "");
        context.put("message", message );
        context.put("Action", ""  );
        context.put("goto",  "");
        template.merge(context, writer);

        MailContent mailContent = new MailContent();
        mailContent.setEmailsTo(new String[]{email});
        mailContent.setEmailsCC(new String[]{fromEmail});
        //mailContent.setEmailsBCC(new String[]{fromEmail});
        mailContent.setSubject(subject);
        mailContent.setContent(writer.toString());
        mailContent.setRecipientTypeTo();

        String contentPath =  Initializer.context.getRealPath("");
        StringBuilder logo = new StringBuilder(contentPath);
        logo.append(File.separator).append("img").
                append(File.separator).append("general").
                append(File.separator).append("logo.png");


        mailContent.addDataSource("logo",logo.toString());

        MailSender mailNotification = new MailSender();
        try {
            mailNotification.sendEmailFromConnectTo(mailContent, fromEmail, fromEmailPassword);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }
}
