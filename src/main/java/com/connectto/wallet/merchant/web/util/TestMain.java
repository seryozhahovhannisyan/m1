package com.connectto.wallet.merchant.web.util;

/**
 * Created by Serozh on 6/21/2016.
 */
public class TestMain {

    public static boolean isHotel(String idPat){
        return idPat.split("/")[0].equals("7");
    }

    public static void main(String[] args) {
        System.out.println(isHotel("10/14"));
        System.out.println(isHotel("7"));
        System.out.println(isHotel("7/10/50"));
    }
}
