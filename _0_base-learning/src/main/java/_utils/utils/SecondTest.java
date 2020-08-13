package _utils.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/2/24 0:28
 * @Description:
 */
public class SecondTest {
    public static long getMin(Long min,Date begin,Date end,String time11,String time22) throws Exception{
        int i = 1;
        Date [] dates = Utils.getDataUnit(begin,1,time11,time22);
        while(end.after(dates[0])  || end.after(dates[1])){
            long temp;
            if(end.after(dates[0]) && end.before(dates[1])){
                temp = Utils.sub(dates[0],end);
                min += temp;break;
            }
            if(end.after(dates[1])){
                long abc = Utils.sub(dates[0],dates[1]);
                min += abc;
                dates = Utils.getDataUnit(begin,++i,time11,time22);
            }
        }
        return min;
    }
    public static long calc(Date begin,Date end,String time11,String time22) throws Exception{
        Date time1 = Utils.transferDate(begin,time11);
        Date time2 = Utils.transferDate(begin,time22);
        long min = 0L;
        //前
        if(begin.before(time1) && time1.before(end) && end.before(time2)){
            min += Utils.sub(time1,end);
        }
        if(begin.before(time1) && time2.before(end)){
            min += Utils.sub(time1,time2);
            //开始循环往后追加
            min = getMin(min,begin,end,time11,time22);
        }
        //后
        if(time1.before(begin) && end.before(time2)){
            min += Utils.sub(begin,end);
        }
        if(time1.before(begin) && time2.before(end)){
            min += Utils.sub(begin,time2);
            min = getMin(min,begin,end,time11,time22);
        }
        return min;
    }
    public static long getStart(Date begin,Date end,String time11,String time22) throws Exception{
        //判断是否倒序
        if(Integer.parseInt(time11.substring(0,2)) > Integer.parseInt(time22.substring(0,2))){
            int gap = Integer.parseInt(time22.substring(0,2)) + 1; //
            return calc(Utils.backDate(begin,-gap),Utils.backDate(end,-gap)
                        ,Utils.backTime(time11,-gap)
                            ,Utils.backTime(time22,-gap));
        }
        return calc(begin,end,time11,time22);
    }

    public static void main(String [] args) throws Exception{
        String a = "2019-02-08 09:25:07";
        String b = "2019-02-10 23:50:14";

        String head = "23:32";
        String tail = "01:24";

        Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a);
        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(b);

        long minute = getStart(begin,end,head,tail);
        System.out.println(minute);
    }

}


final class Utils{
    public static long sub(Date dateStart, Date dateEnd) {
        long gap = dateEnd.getTime() - dateStart.getTime();
        return (gap / 60000);
    }
    public static Date getDateByIndex(Date dataBase, int increments) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBase);
        cal.add(Calendar.DATE, increments);
        return cal.getTime();
    }
    public static String toTwoNumber(int i){
        String result;
        String str = String.valueOf(i);
        int leng = str.length();
        if(leng==1){
            result = "0"+ str;
        }else{
            result = str;
        }
        return result;
    }
    public static Date[] getDataUnit(Date begin,int increments,String time1,String time2) throws Exception{
        Date[] da = new Date[2];
        String b = "yyyy-MM-dd HH:mm:ss";
        String pre = new SimpleDateFormat(b).format(begin.getTime()).substring(0,10) + " "+ time1 + ":00";
        String suf = new SimpleDateFormat(b).format(begin.getTime()).substring(0,10) + " "+ time2 + ":00";
        Date date1 = new SimpleDateFormat(b).parse(pre);
        Date date2 = new SimpleDateFormat(b).parse(suf);
        da[0] = getDateByIndex(date1,increments);
        da[1] = getDateByIndex(date2,increments);
        return da;
    }
    public static Date transferDate(Date beginDate, String time) throws Exception {
        String prefix = new SimpleDateFormat("yyyy-MM-dd").format(beginDate.getTime());
        Date transferDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(prefix + " " + time + ":00");
        return transferDate;
    }
    public static Date backDate(Date date, int hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }
    public static String backTime (String time, int hour){
        String pre = time.substring(0,2);
        String suf = time.substring(2);
        int a = Integer.parseInt(pre);
        if(a>-hour){
            return toTwoNumber(a+hour) + suf;
        }else{
            return toTwoNumber(24+hour+a) + suf;
        }
    }
}
