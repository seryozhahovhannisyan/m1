package com.connectto.wallet.merchant.web.action.util;

import com.connectto.wallet.merchant.common.util.Generator;
import com.connectto.wallet.merchant.common.util.Utils;

/**
 * Created by Serozh on 7/4/2016.
 */
public class GeneralUtil {

    public static String generateVerificationCode (){
        return Generator.get(6, Generator.Type.DIGITS);
    }

    public static String generatePassword (){
        return "1";//Generator.get(6, Generator.Type.DIGITS);
    }

    public static String generateOrderCode (){
        return Generator.get(15, Generator.Type.DIGITS);
    }

    public static String generatePasswordToken (){
        return Generator.get(128, Generator.Type.ALPHABETIC_DIGIT);
    }

    public static synchronized boolean isValidPassword(String password) {
        if(Utils.isEmpty(password)){
            return false;
        }
        return password.length() >= 6;
    }
}
