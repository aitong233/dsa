package com.qpyy.libcommon.utils;

import java.util.Calendar;

public class TimeUtils {

    /**
     * 获取年
     *
     * @return
     */
    public static int getYear() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @return
     */
    public static int getMonth() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MONTH) + 1;
    }

    public static String getMonths() {
        Calendar cd = Calendar.getInstance();
        int month = cd.get(Calendar.MONTH) + 1;
        if (month <= 9) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    /**
     * 获取日
     *
     * @return
     */
    public static int getDay() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.DATE);
    }

    public static String getDays() {
        Calendar cd = Calendar.getInstance();
        int day = cd.get(Calendar.DATE);
        if (day <= 9) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }

    /**
     * 获取时
     *
     * @return
     */
    public static int getHour() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR);
    }

    /**
     * 获取分
     *
     * @return
     */
    public static int getMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);
    }

    /**
     * 根据某年份、某月份获取对应的月份天数
     *
     * @param year  年份
     * @param month 月份
     * @return 月份的天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int days = calendar.get(Calendar.DATE);
        return days;
    }


}
