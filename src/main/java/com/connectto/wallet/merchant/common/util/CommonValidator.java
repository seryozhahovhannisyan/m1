package com.connectto.wallet.merchant.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonValidator {

    private static final DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private static final String regexEmailAddress = "((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";

    private static final String regexMobileNumber = "(0){0,1}[0-9]{2}(([\\ ]{0,1}|(-){0,1})[0-9]{2}){3}";

    public static synchronized boolean isValidEmail(String email) {

        if (Utils.isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexEmailAddress);
        return pattern.matcher(email).matches();
    }

    public static synchronized boolean isValidMobileNumber(String mobileNumber) {

        if (Utils.isEmpty(mobileNumber)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexMobileNumber);
        return pattern.matcher(mobileNumber).matches();
    }

    public static boolean isValidDate(String strDate) {
        try {
            simpleDateFormat.parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDateBefore(String strDate) {
        try {
            Date date = simpleDateFormat.parse(strDate);
            return date.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

     public static boolean isValidDateBeforeNow(String strDate) {
        try {
            Date date = dateFormat.parse(strDate);
            return date.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDateBefore(long dateTime) {
        return dateTime < System.currentTimeMillis();
    }

}
