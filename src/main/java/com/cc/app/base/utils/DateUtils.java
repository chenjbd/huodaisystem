package com.cc.app.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String formatDate(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            logger.error("日期格式化异常", e);
        }
        return null;
    }

    public static Date parseDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            logger.error("日期格式化异常", e);
        }
        return null;
    }

    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    public static Date addMinute(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }

    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    public static Date getShortDate(Date date) {
        Date shortDate = null;
        try {
            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
            shortDate = yyyyMMdd.parse(yyyyMMdd.format(date));
        } catch (Exception e) {
            logger.error("日期格式化异常", e);
        }
        return shortDate;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Date shortDate = DateUtils.getShortDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(shortDate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Date shortDate = DateUtils.getShortDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(shortDate);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    public static  String currDate(){
        return  formatDate(new Date(),"yyyyMMddHHmmss");
    }

    public static void main(String[] args) {
        String dateStr = "2018-10-13 13:00:00";
        Date currDate = DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        Date date = DateUtils.getFirstDayOfMonth(currDate);
        System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));

        Date lastDate = DateUtils.getLastDayOfMonth(currDate);
        System.out.println(DateUtils.formatDate(lastDate, "yyyy-MM-dd HH:mm:ss"));
    }
}
