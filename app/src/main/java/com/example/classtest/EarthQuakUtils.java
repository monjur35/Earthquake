package com.example.classtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthQuakUtils {
    public static String getFormattedDateorTime(long date,String format){
        return new SimpleDateFormat(format).format(new Date(date*1000));
    }

}
