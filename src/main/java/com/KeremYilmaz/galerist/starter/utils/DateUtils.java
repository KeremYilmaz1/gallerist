package com.KeremYilmaz.galerist.starter.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
