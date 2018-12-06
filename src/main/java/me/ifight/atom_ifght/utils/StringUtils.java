package me.ifight.atom_ifght.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        return (str == null) || (str.equals(""));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
