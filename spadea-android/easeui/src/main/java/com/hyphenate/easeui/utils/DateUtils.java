package com.hyphenate.easeui.utils;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 项目名称 qipao-android
 * 包名：com.hyphenate.easeui.utils
 * 创建人 王欧
 * 创建时间 2020/7/13 1:41 PM
 * 描述 describe
 */
public class DateUtils {
    public static String getTimestampString(Date date) {
        String var1 = null;
        long time = date.getTime();
        if (isCurrentYear(time)) {
            if (TimeUtils.isToday(time)) {
                var1 = "HH:mm";
            } else if (isYesterday(time)) {
                var1 = "昨天 HH:mm";
            } else if (isCurrentWeek(time)) {
                var1 = "E HH:mm";
            } else {
                var1 = "MM-dd HH:mm";
            }
        } else {
            var1 = "yyyy-MM-dd HH:mm";
        }

        return new SimpleDateFormat(var1, Locale.CHINESE).format(date);
    }

    private static boolean isYesterday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return time < calendar.getTimeInMillis() && time >= calendar.getTimeInMillis() - TimeConstants.DAY;
    }

    private static boolean isCurrentYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        return year == Calendar.getInstance().get(Calendar.YEAR);
    }

    private static boolean isCurrentWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }
}
