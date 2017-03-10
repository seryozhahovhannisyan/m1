package com.connectto.wallet.merchant.web.action.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by htdev001 on 5/27/14.
 */
public class SHAHashEnrypt {

    public static String get_MD5_SecurePassword(String passwordToHash) throws EncryptException {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(passwordToHash.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }


    }

    //Add salt
    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }


    public static void main(String[] args) {
        try {
            String md5 = get_MD5_SecurePassword("1");
            ;
            System.out.println(getSalt());
        } catch (EncryptException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
