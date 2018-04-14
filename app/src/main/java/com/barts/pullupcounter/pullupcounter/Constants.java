package com.barts.pullupcounter.pullupcounter;

import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "pullupCounterDB";
    public static final String TABLE_NAME = "dailyEntry";

    //Table columns
    public static final String KEY_ID = "id";
    public static final String KEY_DATE = "date";
    public static final String KEY_P_UPS = "pullups";
    public static final String KEY_ASSISTED_P_UPS = "assisted_pullups";
    public static final String KEY_CH_UPS = "chinups";
    public static final String KEY_ASSISTED_CH_UPS = "assisted_chinups";

    public static final String[] ALL_COLUMNS_NAMES = {
            KEY_ID,
            KEY_DATE,
            KEY_P_UPS,
            KEY_ASSISTED_P_UPS,
            KEY_CH_UPS,
            KEY_ASSISTED_CH_UPS
    };

    public static String convertDateToString(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("E, dd.MM.yyyy");
        return ft.format(date).toString();
    }

    public static boolean isTheDateToday(String dateS){
        String today = convertDateToString(new Date());

        if(today.equals(dateS))
            return true;
        else return false;
    }


}
