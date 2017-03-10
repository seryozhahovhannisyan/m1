package com.connectto.wallet.merchant.web.action.general;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Language;
import com.connectto.wallet.merchant.web.action.BaseAction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by htdev001 on 10/23/14.
 */
public class LocaleAction extends BaseAction {
    private String url;
    private String mlang;

    public String execute() {

        HttpServletRequest req = getHttpServletRequest();

        String referer = req.getHeader("referer");
        int indexOfAction = referer.lastIndexOf("/");
        url = referer.substring(indexOfAction + 1);

        Language selectedLanguage = Language.keyOf(mlang);
        //if url's header already contained lang param, replace it current lang
        if (url.contains("?lang=")) {
            int uLangIndex = url.indexOf("?mlang=");
            int langLength = "?mlang=".length();

            String uLang =  url.substring(uLangIndex + langLength, uLangIndex + langLength + 2);
            if(!uLang.equals(mlang)){
                url = url.replace(url.substring(0, uLangIndex + langLength ) + uLang, url.substring(0, uLangIndex + langLength ) + mlang);
            }
        } else if (url.contains("&mlang=")) {
            int uLangIndex = url.indexOf("&mlang=");
            int langLength = "&mlang=".length();

            String uLang =  url.substring(uLangIndex + langLength, uLangIndex + langLength + 2);
            if(!uLang.equals(mlang)){
                url = url.replace(url.substring(0, uLangIndex + langLength ) + uLang, url.substring(0, uLangIndex + langLength ) + mlang);
            }
        }
        return SUCCESS;
    }

    public String getUrl() {
        return url;
    }

    public void setMlang(String mlang) {
        this.mlang = mlang;
    }
}
