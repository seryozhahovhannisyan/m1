package com.connectto.wallet.merchant.common.util;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Utils {

    //TODO remove after typeHandler bug fixing
    public static final String DE_STRING = "String",
            DE_LARGE_STRING = "Large String",
            DE_INT = "int",
            DE_DATE = "Date",
            DE_DATETIME = "DateTime";

    private static DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private static Pattern expDigit = Pattern.compile("[0-9]");

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isValidBankCode(String code) {
        return true;
    }

    public static String toString(Date date) {

        return date == null
                ? null
                : dateFormat.format(date);
    }

    public static Date toDate(String strDate) {
        try {
            return Utils.isEmpty(strDate)
                    ? null
                    : dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date toSimpleDate(String strDate) {
        try {
            return Utils.isEmpty(strDate)
                    ? null
                    : simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Date getAfterSecunds(Date date, Long seconds) throws InvalidParameterException {
        if (date == null || seconds == null) {
            throw new InvalidParameterException("Empty Parameter date or second");
        }

        return new Date(date.getTime() + (seconds * 1000));
    }

    /**
     * Calculates seconds for given days
     *
     * @param day
     * @return <code>java.lang.Integer</code>
     */
    public static synchronized int dayToSeconds(int day) {
        return 60 * 60 * 24 * day;
    }

    public static boolean isPositiveNumeric(Integer data) {
        return data != null && data > 0;
    }

    public static boolean isDigit(String str) {
        return !Utils.isEmpty(str)
                ? expDigit.matcher(str).matches()
                : false;
    }

    public static synchronized int diffMonth(Date date1, Date date2) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int months1 = cal1.get(Calendar.YEAR) * 12 + cal1.get(Calendar.MONTH);
        int months2 = cal2.get(Calendar.YEAR) * 12 + cal2.get(Calendar.MONTH);

        return months2 - months1;
    }

    public static synchronized long diffSeconds(Date date1, Date date2) {
        long diffInMillies = date1.getTime() - date2.getTime();
        return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static synchronized Date agoSeconds(Date date, long ago) {
        return new Date(date.getTime() - ago * 1000);
    }

    public static synchronized Date getStartToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static synchronized Date getEndToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static synchronized Date getAfter(int days) {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static synchronized Date getStartCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static synchronized Date getEndCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static void main(String args[]) {
        Date date1 = DataConverter.toMerchantDate("2017-06-06 22:22:22");
        Date date2 = DataConverter.toMerchantDate("2017-06-06 22:22:22");
        System.out.println(diffSeconds(date1, date2));

        date2 = DataConverter.toMerchantDate("2017-06-06 22:22:25");
        System.out.println(diffSeconds(date1, date2));

        date2 = DataConverter.toMerchantDate("2017-06-06 22:22:20");
        System.out.println(diffSeconds(date1, date2));

        date1 = DataConverter.toMerchantDate("2017-01-18 06:40:52");
        System.out.println(diffSeconds(date1, new Date(System.currentTimeMillis())));

    }

    /**
     * Iterates through a Collection to create a single string list separated by a delimiter.
     *
     * @param coll    The Collection object to
     * @param delimit delimiter character is appended after each object in the collection save the last one
     * @return a delimited String
     */
    public static String join(Collection coll, String delimit) {
        if (coll == null) return "";

        StringBuilder val = new StringBuilder();
        Iterator i = coll.iterator();
        int cnt = 0;
        while (i.hasNext()) {
            if (cnt > 0) {
                val.append(delimit);
            }

            Object obj = i.next();
            if (obj == null) {
                obj = "null";
            }
            val.append(obj.toString().trim());
            cnt++;
        }
        return val.toString();
    }

    public static String join(Map map, String delimit) {
        if (map == null) return "";

        StringBuilder val = new StringBuilder();
        Iterator i = map.entrySet().iterator();
        int cnt = 0;
        while (i.hasNext()) {
            if (cnt > 0) {
                val.append(delimit);
            }

            Map.Entry obj = (Map.Entry) i.next();
            val.append(obj.getKey().toString().trim());
            val.append("=");
            val.append(obj.getValue().toString().trim());
            cnt++;
        }
        return val.toString();
    }

    /**
     * A faster split method that doesn't use regex.  It's about 10 times faster than the normal String.split().
     */
    public static List<String> split(String val, String delimiter) {
        return split(val, delimiter, 0);
    }

    public static List<String> split(String val, String delimiter, int maxSplits) {
        List<String> list = new ArrayList<String>();
        if (val == null) return list;

        int start = 0;
        int count = 1;

        int index;
        while ((index = val.indexOf(delimiter, start)) >= 0 && (maxSplits == 0 || count < maxSplits)) {
            list.add(val.substring(start, index));
            start = index + delimiter.length();
            count++;
        }

        list.add(val.substring(start));

        return list;
    }

    /**
     * An efficient method for replacing strings within strings (just the first match).
     */
    public static String replaceFirst(String val, String match, String replacement) {
        if (val == null || match == null || replacement == null) return val;
        StringBuilder result = new StringBuilder(val.length() + 128);

        int index = val.indexOf(match);
        result.append(val, 0, index);
        result.append(replacement);
        result.append(val, index + match.length(), val.length());

        return result.toString();
    }

    /**
     * An efficient method for replacing strings within strings
     */
    public static String replaceAll(String val, String match, String replacement) {
        if (val == null || match == null || replacement == null) return val;
        StringBuilder result = new StringBuilder(val.length() + 128);

        int oldIndex = 0;
        int index = val.indexOf(match);
        while (index != -1) {
            result.append(val, oldIndex, index);
            result.append(replacement);
            oldIndex = index + match.length();
            index = val.indexOf(match, oldIndex);
        }

        result.append(val, oldIndex, val.length());

        return result.toString();
    }

    /**
     * A faster split method that doesn't use regex.  It's about 10 times faster than the normal String.split().
     */
    public static List<String> split(String val, char delimiter) {
        return split(val, delimiter, 0);
    }

    public static List<String> split(String val, char delimiter, int maxSplits) {
        List<String> list = new ArrayList<String>();
        if (val == null) return list;

        int start = 0;
        int count = 1;

        int index;
        while ((index = val.indexOf(delimiter, start)) >= 0 && (maxSplits == 0 || count < maxSplits)) {
            list.add(val.substring(start, index));
            start = index + 1;
            count++;
        }

        list.add(val.substring(start));

        return list;
    }

    /**
     * A faster split method that doesn't use regex and always returns at most two parts.
     * <p>
     * This method is about twice as fast in my benchmarks as the general StringUtils.split() above.
     * It's about 20 times faster than the normal String.split().
     */
    public static String[] splitFast(String text, char delimiter) {
        int index = text.indexOf(delimiter);
        if (index == -1) {
            return new String[]{text};
        } else {
            return new String[]{
                    text.substring(0, index),
                    text.substring(index + 1)
            };
        }
    }

    /**
     * A faster split method that doesn't use regex and always returns only the first part.
     * <p>
     * This method is about twice as fast in my benchmarks as the StringUtils.splitFast() above.
     * This method is about 4 times as fast in my benchmarks as the general StringUtils.split() above.
     * It's about 40 times faster than the normal String.split().
     */
    public static String splitFastFirst(String text, char delimiter) {
        int index = text.indexOf(delimiter);
        if (index == -1) {
            return text;
        } else {
            return text.substring(0, index);
        }
    }

    /**
     * A faster split method that doesn't use regex and always returns only the second part.
     * <p>
     * This method is about twice as fast in my benchmarks as the StringUtils.splitFast() above.
     * This method is about 4 times as fast in my benchmarks as the general StringUtils.split() above.
     * It's about 40 times faster than the normal String.split().
     */
    public static String splitFastSecond(String text, char delimiter) {
        int index = text.indexOf(delimiter);
        if (index == -1) {
            return null;
        } else {
            return text.substring(index + 1);
        }
    }

    /**
     * A faster split method that doesn't use regex and always returns only the last part.
     * <p>
     * This method is about twice as fast in my benchmarks as the StringUtils.splitFast() above.
     * This method is about 4 times as fast in my benchmarks as the general StringUtils.split() above.
     * It's about 40 times faster than the normal String.split().
     */
    public static String splitFastLast(String text, char delimiter) {
        int index = text.lastIndexOf(delimiter);
        if (index == -1) {
            return null;
        } else {
            return text.substring(index + 1);
        }
    }

    /**
     * A faster split method that doesn't use regex and always returns the start to the last match the delimiter.
     */
    public static String splitFastStartToLast(String text, char delimiter) {
        int index = text.lastIndexOf(delimiter);
        if (index == -1) {
            return text;
        } else {
            return text.substring(0, index);
        }
    }

    public static <T> T getFirst(Set<T> set) throws NullPointerException {
        if (isEmpty(set)) {
            throw new NullPointerException();
        }
        Iterator<T> iter = set.iterator();
        if (iter.hasNext()) {
            return iter.next();
        }
        throw new NullPointerException();
    }

}