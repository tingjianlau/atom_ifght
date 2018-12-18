package me.ifight.utils;

import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String str){
        return (str == null) || (str.equals(""));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isDecimal(CharSequence cs){
        if(cs != null && cs.length() != 0){
            String decimalPattern = "([0-9]*)\\.([0-9]*)";
            return Pattern.matches(decimalPattern, cs);
        }

        return false;
    }
}
