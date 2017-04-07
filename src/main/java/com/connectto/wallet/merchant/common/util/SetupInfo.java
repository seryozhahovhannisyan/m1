package com.connectto.wallet.merchant.common.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Serozh on 6/21/2016.
 */
public class SetupInfo implements Serializable {

    private Properties props;

    //pages
    public String setup = "dev";
    public String version = "1.0";
    public String staticDir = ".";
    public String staticDirProd = "/opt/tomcat/merchantapps";

    public String socialFb = "ConnectTo-Communications-176878392348752";
    public String socialGmail = "u/1/103581122530817965828/posts";
    public String socialTwitter = "ConnectToCom";

    public String notificationSmsUsername = "aramter";
    public String notificationSmsPassword = "Art22Arax";
    public String notificationEmailUsername = "merchant@connectto.com";
    public String notificationEmailPassword = "XwN$!sf8+P";

    public String recaptchaSecretKey = "6Ldf0hETAAAAAP94esg5vk5ch2K19_0khG6HFw8v";
    public String recaptchaClientKey = "6Ldf0hETAAAAAKRPDJYZ-rnVHGI8bQyWPeyN7eiM";

    public SetupInfo() {
    }

    public SetupInfo(Properties properties) {
        try {
            this.props = properties;
            setup = properties.getProperty("setup");
            version = properties.getProperty("version");
            staticDir = properties.getProperty("static.dir");
            staticDirProd = properties.getProperty("static.dir.prod");

            socialFb = properties.getProperty("social.link.fb");
            socialGmail = properties.getProperty("social.link.gmail");
            socialTwitter = properties.getProperty("social.link.tw");

            notificationSmsUsername = properties.getProperty("notification.sms.bulk.username");
            notificationSmsPassword = properties.getProperty("notification.sms.bulk.password");
            notificationEmailUsername = properties.getProperty("notification.email.username");
            notificationEmailPassword = properties.getProperty("notification.email.password");

            recaptchaSecretKey = properties.getProperty("recaptcha.secret.key");
            recaptchaClientKey = properties.getProperty("recaptcha.client.key");


        } catch (Exception e) {

        }

    }

    private String fName(int width, int height) {
        return width + "x" + height;
    }

    public final Properties getProperties() {
        return props;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getSetup() {
        return setup;
    }

    public String getVersion() {
        return version;
    }

    public String getStaticDir() {
        return staticDir;
    }

    public String getStaticDirProd() {
        return staticDirProd;
    }

}
