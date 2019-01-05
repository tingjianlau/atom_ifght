package me.ifight.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private final static String dateFmt1 = "yyyy-MM-dd HH:mm:ss";

    public static String now(){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFmt1);

        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        System.out.printf(DateUtils.now());
    }
}
