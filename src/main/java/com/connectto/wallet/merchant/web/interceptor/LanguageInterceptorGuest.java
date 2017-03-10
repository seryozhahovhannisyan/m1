package com.connectto.wallet.merchant.web.interceptor;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Language;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Map;

public class LanguageInterceptorGuest extends AbstractInterceptor {

    private final String REQUEST_LANGUAGE        = "mlang";
    private final String SESSION_LANGUAGE        = "WW_TRANS_I18N_LOCALE";

    private final String REQUEST_LOCALE          = "request_locale";
    private final String REQUEST_ONLY_LOCALE     = "request_only_locale";

    //private final String LANGUAGE                = "request_locale";
    
    
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext actionContext = actionInvocation.getInvocationContext();

        Map<String, Object> session = actionContext.getSession();
        Map<String, Object> parameters = actionContext.getParameters();

        Cookie[] cookies = ServletActionContext.getRequest().getCookies();

        // if any parameters bellow mentioned exist, it means employer is changing the resource
        if (parameters.containsKey(REQUEST_LOCALE) ||
                parameters.containsKey(REQUEST_ONLY_LOCALE) ||
                parameters.containsKey(REQUEST_LANGUAGE)) {

            String language;
            if (parameters.containsKey(REQUEST_LOCALE)) {
                language = ((String[]) parameters.get(REQUEST_LOCALE))[0];
            } else if (parameters.containsKey(REQUEST_ONLY_LOCALE)) {
                language = ((String[]) parameters.get(REQUEST_ONLY_LOCALE))[0];
            } else {
                language = ((String[]) parameters.get(REQUEST_LANGUAGE))[0];
            } 

            // retrieves resource
            Language lang = Language.languageOf(language);
            setLanguage(session, lang, true);
            return actionInvocation.invoke();
        }

        // retrieves guest's  preferred resource if it's defined on session
        if(session.containsKey(BaseAction.LANGUAGE)){

            Language lang = (Language)session.get(BaseAction.LANGUAGE);
            setLanguage(session, lang, false);
            return actionInvocation.invoke();
        }

        // retrieves employer preferred resource if it's defined
        if (cookies != null && cookies.length > 0) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(BaseAction.LANGUAGE)) {

                    String language = cookie.getValue();
                    // gets 'Language' via resource
                    Language lang = Language.languageOf(language);
                    setLanguage(session, lang, false);
                    return actionInvocation.invoke();
                }
            }
        }

        // stores default resource in session and cookie if it doesn't exist
        if (session.isEmpty() || !session.containsKey(BaseAction.LANGUAGE)) {

            // gets default resource
            Language lang = Language.getDefault();
            setLanguage(session, lang, true);
        }

        return actionInvocation.invoke();
    }

    /**
     * Stores chosen resource into session and cookie,
     * if resource exists in any storage it will be replaced.
     *
     * @param session        (session mapped data)
     * @param lang           (chosen resource)
     * @param storeInCookies
     */
    private void setLanguage(Map<String, Object> session, Language lang, boolean storeInCookies) {

        Language sesLang = (Language) session.get(BaseAction.LANGUAGE);

        // if resource not stored in session
        // or it is not matched with chosen resource
        if (sesLang == null || sesLang != lang) {
            // sets resource for employer usage
            session.put(BaseAction.LANGUAGE, lang);
            // sets locale for application localization (i18n)
            session.put(SESSION_LANGUAGE, lang.getLocale());
            ActionContext.getContext().setLocale(lang.getLocale());
        }

        if (storeInCookies) {
            // add resource cookie
            Cookie cookie = new Cookie(BaseAction.LANGUAGE, lang.getLocale().getLanguage());
            cookie.setMaxAge(365 * 24 * 60 * 60);
            ServletActionContext.getResponse().addCookie(cookie);
        }
    }
}
