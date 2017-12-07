package com.studylbn.www.loopviewpager.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.y;

/**
 * Created by LiuBin on 2017/12/7 17:11.
 */

public class DataStyle {
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String format(Date date) {
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat md = new SimpleDateFormat("MM月dd日");
        long delta = new Date().getTime() - date.getTime();
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        if (delta > 0 && delta < ONE_MINUTE) {
            return "刚刚";
        }
        if (delta > ONE_MINUTE && delta < ONE_HOUR) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta > ONE_HOUR && delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta > ONE_DAY && delta < 7L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_HOUR_AGO;
        }
        if ((delta > 7L * ONE_DAY) && (date.getYear() == nowYear)) {
            return md.format(date);
        }
        if ((delta > 7L * ONE_DAY) && (date.getYear() != nowYear)) {
            return ymd.format(date);
        } else {
            return ymd.format(date);
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
