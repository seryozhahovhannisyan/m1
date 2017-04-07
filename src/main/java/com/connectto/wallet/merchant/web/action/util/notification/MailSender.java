package com.connectto.wallet.merchant.web.action.util.notification;

import com.connectto.wallet.merchant.common.util.Utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;
import java.util.Properties;

/**
 * @author : Serozh
 *         Date: 24.08.13
 *         Time: 1:55
 */
public class MailSender {

    //private JavaMailSenderImpl javaMailSender;

    public void sendEmailFromConnectTo(MailContent mailContent, final String fromEmail, final String password) throws MailException {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "mail.connectto.com");
            props.put("mail.smtp.socketFactory.port", "465");//465
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");//"465";//SMTP server- mail.connecttomail.com 587
            //Properties properties = System.getProperties();
            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(fromEmail, password);
                        }
                    });
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromEmail));
            // send to
            String[] emailsTo = mailContent.getEmailsTo();
            InternetAddress[] emailsToAddresses = new InternetAddress[emailsTo.length];
            for (int i = 0; i < emailsTo.length ; i++) {
                emailsToAddresses[i] = new InternetAddress(emailsTo[i]);
            }
            message.setRecipients(Message.RecipientType.TO, emailsToAddresses);
            // send cc
            if (!Utils.isEmpty(mailContent.getEmailsCC())) {
                String[] emailsCC = mailContent.getEmailsCC();
                InternetAddress[] emailsCCAddresses = new InternetAddress[emailsCC.length];
                for (int i = 0; i < emailsCC.length ; i++) {
                    emailsCCAddresses[i] = new InternetAddress(emailsCC[i]);
                }
                message.setRecipients(Message.RecipientType.CC, emailsCCAddresses);
            }
            //Send bcc
            if (!Utils.isEmpty(mailContent.getEmailsBCC())) {
                String[] emailsBCC = mailContent.getEmailsBCC();
                InternetAddress[] emailsBCCAddresses = new InternetAddress[emailsBCC.length];
                for (int i = 0; i < emailsBCC.length; i++) {
                    emailsBCCAddresses[i] = new InternetAddress(emailsBCC[i]);
                }
                message.setRecipients(Message.RecipientType.BCC, emailsBCCAddresses);
            }

            // Set Subject: header field
            message.setSubject(mailContent.getSubject());
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");
            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = mailContent.getContent();
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            Map<String, String > dataSources = mailContent.getDataSources();
            if(!Utils.isEmpty(dataSources)){
                // second part (the image)
                messageBodyPart = new MimeBodyPart();
                for(String data : dataSources.keySet()){
                    FileDataSource fds = new FileDataSource(dataSources.get(data)){
                        @Override
                        public String getContentType(){
                            return "application/octet-stream";
                        }
                    };
                    messageBodyPart.setDataHandler(new DataHandler(fds));
                    messageBodyPart.setHeader("Content-ID", "<"+data+">");
                }
            }



            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);
            Transport.send(message);
            // Send message
           /* String mailHost =javaMailSender.getHost();// "mail.connectto.com";
            String mailProtocol = javaMailSender.getProtocol();
            Transport transport = session.getTransport(mailProtocol);
            try {
                transport.connect(mailHost, fromEmail, password);
                transport.sendMessage(message, message.getAllRecipients());
            }  finally {
                transport.close();
            }*/
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

/*

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }*/

}
