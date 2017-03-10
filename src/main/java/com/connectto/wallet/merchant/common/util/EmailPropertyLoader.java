package com.connectto.wallet.merchant.common.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Serozh on 6/21/2016.
 */
public class EmailPropertyLoader implements Serializable {


    private Properties props;

    //Emails to , cc, bcc
    private String to;
    private String cc;
    private String bcc;
    //
    private String[] emailsTo;
    private String[] emailsCc;
    private String[] emailsBcc;

    public EmailPropertyLoader() {
    }

    public EmailPropertyLoader(Properties properties) {
        try {
            this.props = properties;
            to = properties.getProperty("mail.sender.emails.to");
            cc = properties.getProperty("mail.sender.emails.cc");
            bcc = properties.getProperty("mail.sender.emails.bcc");

        } catch (Exception e) {

        } finally {
            createEmails();
        }
    }

    private void createEmails() {
        if(!Utils.isEmpty(to)){
           emailsTo = to.split(",");
        }
        if(!Utils.isEmpty(cc)){
            emailsCc = to.split(",");
        }
        if(!Utils.isEmpty(bcc)){
            emailsBcc = to.split(",");
        }
    }

    public final Properties getProperties() {
        return props;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String[] getEmailsTo() {
        return emailsTo;
    }

    public String[] getEmailsCc() {
        return emailsCc;
    }

    public String[] getEmailsBcc() {
        return emailsBcc;
    }
}
