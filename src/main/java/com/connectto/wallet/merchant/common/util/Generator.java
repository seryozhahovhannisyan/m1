package com.connectto.wallet.merchant.common.util;

import java.util.Random;

public class Generator {

    private static Random rand = new Random();

    public enum Type {

        DIGITS              ("0123456789"),
        ALPHABETIC_LOWER    ("abcdefghijklmnopqrstuvwxyz"),
        ALPHABETIC_UPPER    ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHABETIC          ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHABETIC_DIGIT    ("0123456789abcdefghijklmnopqrstuvwxyz");

        Type(final String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }
    }

    public static synchronized String get(int count, Type type) {

        if (type == null) {
            throw new RuntimeException("Type for 'Value Generator' is NULL");
        }
        if (count <= 0) {
            return null;
        }

        StringBuilder buff = new StringBuilder();
        while (count > 0) {
            buff.append(type.value.charAt(rand.nextInt(type.value.length())));
            count--;
        }
        return buff.toString();
    }
}
