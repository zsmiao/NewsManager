package xyz.itclay.newsmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {
    public static String  nowSystemTime() {
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sbf.format(d);
        return time;
    }
}
