package com.swmu.common.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author seven.mu
 * @version 1.0
 * @description 时间格式工具
 * @date Create in 2018/12/13 18:01
 */
public class DateFormatUtils {
    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYMMDDHHMMSSHS = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 时间格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    /**
     * 时间格式 yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_ALL = "yyyyMMddHHmmss";
    /**
     * 时间格式 yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_HHMM = "HH:mm";

    /**
     * 将时间字符串转化为Date类型
     *
     * @param dateTimeStr
     * @return Date
     * @method stringToDate
     */
    public static Date stringToDate (String dateTimeStr) throws Exception {
        return stringToDate(dateTimeStr, DATE_FORMAT_YYYMMDDHHMMSSHS);
    }

    /**
     * 字符串转换成日期 按照默认formatStr的格式，转化dateTimeStr为Date类型
     *
     * @param dateTimeStr
     * @param formatStr   :yyyy/MM/dd
     * @return Date
     * @method stringToDate
     */
    public static Date stringToDate(String dateTimeStr, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.parse(dateTimeStr);
    }


    /**
     * 字符串转换成日期 按照默认formatStr的格式，转化dateTimeStr为Date类型
     *
     * @param dateTimeStr
     * @param formatStr   :yyyy/MM/dd
     * @return Date
     * @method stringToDate
     */
    public static Date stringToDefaultDate(String dateTimeStr, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(dateTimeStr);
    }

    /**
     * 将Date类型按formatStr格式转化为String类型
     *
     * @param date
     * @param formatStr 如yyyy-MM-dd
     * @return String
     * @method dateToString
     */
    public static String dateToString(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(date);
    }

    /**
     * 获取当前时间   时间格式 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getDateFormatYyyymmddhhmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ALL);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间   时间格式 yyyyMMdd HHmmss
     *
     * @return
     */
    public static String getDateFormatType(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(date);
    }

    /**
     * 获取当前时间   时间格式 yyyyMMdd HHmmss
     *
     * @return
     */
    public static String getDateUTC(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    /**
     * 获取当前时间   时间格式 yyyy-MM-dds
     *
     * @return
     */
    public static String getDateFormatYyyymmdd() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(new Date());
    }

    /**
     * 时间计算
     *
     * @param date  时间
     * @param field field取1加count年,取2加count半年,取3加count季度,取4加count周 取5加count天
     * @param count 数量， 正数表示加，负数表示减
     * @return Date
     * @method dateCalculate
     */
    public static Date dateCalculate(Date date, Integer field, Integer count) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, count);
        return gc.getTime();
    }

    /**
     * 获取今日开始时间
     *
     * @return
     */
    public static Date getTodayStartDateTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取本月开始日期和时间
     *
     * @return
     */
    public static Date getThisMonthStartDateTime() {
        Calendar monthStart = Calendar.getInstance();
        monthStart.set(Calendar.DAY_OF_MONTH, 1);
        monthStart.set(Calendar.HOUR_OF_DAY, 0);
        monthStart.set(Calendar.MINUTE, 0);
        monthStart.set(Calendar.SECOND, 0);
        monthStart.set(Calendar.MILLISECOND, 0);
        return monthStart.getTime();
    }

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        final int number = 10;

        StringBuffer sb = new StringBuffer();
        if (day != 0) {
            sb.append(day);
            sb.append("天");
        }

        if (hour != 0) {
            if (hour < number) {
                sb.append("0");
            }
            sb.append(hour);
            sb.append(":");
        } else {
            if (day != 0) {
                sb.append("00");
                sb.append(":");
            }
        }

        if (min != 0) {
            if (min < number) {
                sb.append("0");
            }
            sb.append(min);
            sb.append(":");
        } else {
            sb.append("00");
            sb.append(":");

        }

        if (sec != 0) {
            if (sec < number) {
                sb.append("0");
            }
            sb.append(sec);
        } else {
            sb.append("00");
        }
        return sb.toString();
    }

    public static int comparedDate(String startTime, String endTime, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        try {
            String nowtime = sdf.format(new Date());
            Date startTimeDate = sdf.parse(startTime);
            Date endTimeDate = sdf.parse(endTime);
            Date nowtimeTimeDate = sdf.parse(nowtime);
            //1 进行中 0 未开始 2 已结束
            if ((nowtimeTimeDate.before(endTimeDate)) && ((nowtimeTimeDate.after(startTimeDate)))) {
                return 1;
            } else if ((nowtimeTimeDate.before(startTimeDate))) {
                return 0;
            } else if ((nowtimeTimeDate.after(endTimeDate))) {
                return 2;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static int comparedDate(Date date1, Date date2) {
        try {
            if (date1.before(date2)) {
                return -1;
            } else if (date1.getTime() == date2.getTime()) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static String getDuration(Integer second) {
        int hours = second / (60 * 60);
        int minutes = (second % (60 * 60)) / (60);
        StringBuffer sb = new StringBuffer();
        if (hours > 0) {
            sb.append(hours + "小时");
        }

        if (minutes > 0) {
            sb.append(minutes + "分");
        }
        return sb.toString();
    }
}