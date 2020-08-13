package _draft.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/2/22 18:03
 * @Description:
 */
public class TTest {

    /**
     * 日期差（分钟）
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static long sub(Date dateStart, Date dateEnd) {
        long gap = dateEnd.getTime() - dateStart.getTime();
        return (gap / 60000);
    }

    /**
     * 获取下index个日期
     *
     * @param dataBase
     * @param increments
     * @return
     */
    public static Date getDateByIndex(Date dataBase, int increments) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBase);
        cal.add(Calendar.DATE, increments);
        return cal.getTime();
    }

    public static String getStringByIndex(String stringBase, int increments) throws Exception {
        Date stb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringBase);
        Calendar cal = Calendar.getInstance();
        cal.setTime(stb);
        cal.add(Calendar.DATE, increments);
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cal.getTime());
    }


    public static Date transferDate(Date beginDate, String time) throws Exception {
        String prefix = new SimpleDateFormat("yyyy-MM-dd").format(beginDate.getTime());
        Date transferDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(prefix + " " + time + ":00");
        return transferDate;
    }

    public static long calcHT(String beginDate, String endDate, String timeHead, String timeTail,boolean isTrue) throws Exception {
        long timeSum = 0L;
        Date begin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(beginDate);
        Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endDate);
        Date time1 = transferDate(begin, timeHead);
        Date time2 = transferDate(begin, timeTail);

        //前部分
        if(begin.after(time1)){
            //如果是同一天，有三种取值
            if(beginDate.substring(0,10).equals(endDate.substring(0,10))){
                if(begin.before(time2) && end.after(time2)){
                    timeSum += sub(begin,time2);
                }
                if(end.before(time2)){
                    timeSum += sub(begin,end);
                }
            }

        }else{
            if(beginDate.substring(0,10).equals(endDate.substring(0,10))){
                if(end.after(time1) && end.before(time2)){
                    timeSum += sub(time1,end);
                }
                if(end.before(time2)){
                    timeSum += sub(time1,time2);
                }
            }
        }


        if ((begin.before(time1)||begin.equals(time1))
                && (end.after(time2) || end.equals(time2))) {
            timeSum += sub(time1, time2);
        }
        if(isTrue){
            timeSum += sub(begin, time2);
        }

        return timeSum;
    }

    /**
     * 计算日期在前后整点，用数组保存
     *
     * @return
     */
    public static String[] allDay(String beginDate, String endDate) throws Exception {
        String a = " 00:00:00";
        String b = "yyyy-MM-dd hh:mm:ss";
        String[] dataArray = new String[4];
        String sPrefix = beginDate.substring(0, 10);
        String ePrefix = endDate.substring(0, 10);
        Date sbegin = new SimpleDateFormat(b).parse(sPrefix + a);
        Date ebegin = new SimpleDateFormat(b).parse(ePrefix + a);
        Date send = getDateByIndex(sbegin, 1);
        Date eend = getDateByIndex(ebegin, 1);
        //开始日期的开始和结束
        dataArray[0] = (new SimpleDateFormat(b).format(sbegin.getTime())).substring(0,10)+a;
        dataArray[1] = (new SimpleDateFormat(b).format(send.getTime())).substring(0,10)+a;
        //结束日期的开始和结束
        dataArray[2] = (new SimpleDateFormat(b).format(ebegin.getTime())).substring(0,10)+a;
        dataArray[3] = (new SimpleDateFormat(b).format(eend.getTime())).substring(0,10)+a;
        return dataArray;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }
    public static boolean getSequ(String a,String b){
        return Integer.parseInt(a.substring(0,2)) > Integer.parseInt(b.substring(0,2));
    }

    public static long getMinute(String beginDate, String endDate, String timeHead, String timeTail) throws Exception {
        //1,获取日期
        String[] dayArray = allDay(beginDate, endDate);
        Date head = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dayArray[0]);
        Date tail = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dayArray[2]);
        int dayCount = differentDaysByMillisecond(head, tail);
        boolean isTrue = getSequ(timeHead,timeTail);

        long minute = 0L;
        for (int i = 0; i < dayCount+1; i++) {
            if (i == 0) {
                minute += calcHT(beginDate, endDate, timeHead, timeTail,isTrue);
            } else if (i == dayCount) {
                minute += calcHT(dayArray[2], endDate, timeHead, timeTail,isTrue);
            } else {
                minute += calcHT(getStringByIndex(dayArray[0], i), getStringByIndex(dayArray[0], i+1), timeHead, timeTail,isTrue);
            }
        }
        return minute;
    }

    public static void main(String[] args) throws Exception {
        //时间段，  时间段， 2019-02-08 15:32:07   --  2019-02-21 05:12:49
        //System.out.println(a.substring(0,10));
//        Date d1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-02-08 15:32:07");
//        Date d2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-02-08 16:33:07");
//System.out.println(getSequ("21:00","14:00"));

        //System.out.println(sub(d1,d2));
        String a = "2019-02-08 09:25:07";
        String b = "2019-02-10 04:56:14";

        String head = "15:32";
        String tail = "01:18";

        long min = getMinute(a,b,head,tail);
        System.out.println(min);


    }
}
