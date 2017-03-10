package com.connectto.wallet.merchant.web.interceptor;

import com.connectto.wallet.merchant.common.data.merchant.lcp.Language;
import com.connectto.wallet.merchant.common.util.Utils;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.util.ScopeKeys;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Map;


public class LanguageInterceptor extends AbstractInterceptor {

    private final String REQUEST_LANGUAGE        = "mlang";
    private final String SESSION_LANGUAGE        = "WW_TRANS_I18N_LOCALE";

    private final String REQUEST_LOCALE          = "request_locale";
    private final String REQUEST_ONLY_LOCALE     = "request_only_locale";

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext actionContext = actionInvocation.getInvocationContext();

        Map<String, Object> session = actionContext.getSession();
        Map<String, Object> parameters = actionContext.getParameters();

        Cookie[] cookies = ServletActionContext.getRequest().getCookies();

        boolean isLangDefined = false;

        // if any parameters bellow mentioned exist, it means user is changing the language
        if (parameters.containsKey(REQUEST_LOCALE) ||
            parameters.containsKey(REQUEST_ONLY_LOCALE) ||
            parameters.containsKey(REQUEST_LANGUAGE)) {

            isLangDefined = true;

            String language;
            if (parameters.containsKey(REQUEST_LOCALE)) {
                language = ((String[]) parameters.get(REQUEST_LOCALE))[0];
            } else if (parameters.containsKey(REQUEST_ONLY_LOCALE)) {
                language = ((String[]) parameters.get(REQUEST_ONLY_LOCALE))[0];
            } else {
                language = ((String[]) parameters.get(REQUEST_LANGUAGE))[0];
            }

            // retrives language
            Language lang = Language.languageOf(language);
            setLanguage(session, lang, true);
        }

        // retrieves user preferred language if it's defined
        if (!isLangDefined && cookies != null && cookies.length > 0) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(ScopeKeys.LANGUAGE)) {

                    isLangDefined = true;

                    String language = cookie.getValue();
                    // gets 'Language' via language
                    Language lang = Language.languageOf(language);
                    setLanguage(session, lang, false);
                    break;
                }
            }
        }

        // stores default language in session and cookie if it doesn't exist
        if (!isLangDefined && session.isEmpty() || !session.containsKey(ScopeKeys.LANGUAGE)) {

            // gets default language
            Language lang = Language.getDefault();
            setLanguage(session, lang, true);
        }

        return actionInvocation.invoke();
    }

    /** Stores chosen language into session and cookie,
      * if language exists in any storage it will be replaced.
      * @param session (session mapped data)
      * @param lang (chosen language)
      * @param storeInCookies
      */
    private void setLanguage(Map<String, Object> session, Language lang, boolean storeInCookies) {

        Language sesLang = (Language) session.get(ScopeKeys.LANGUAGE);

        // if language not stored in session
        // or it is not matched with chosen language
        if (sesLang == null || sesLang != lang) {
            // sets language for user usage
            session.put(ScopeKeys.LANGUAGE, lang);
            // sets language for guest usage
            session.put(BaseAction.LANGUAGE, lang);
            // sets locale for application localization (i18n)
            session.put(SESSION_LANGUAGE, lang.getLocale());
        }

        if (storeInCookies) {
            // add language cookie
            Cookie cookie = new Cookie(ScopeKeys.LANGUAGE, lang.getLocale().getLanguage());
            cookie.setMaxAge(Utils.dayToSeconds(365));
            ServletActionContext.getResponse().addCookie(cookie);
        }
    }
}
