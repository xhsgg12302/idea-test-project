package _base.base.bigdecimal;

import _utils.utils.DateTimeUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-17
 * @Desc:
 */
public class Test {

    public static void main1(String[] args) {
        BigDecimal bd =new BigDecimal("10").divide(new BigDecimal("3"),10,RoundingMode.CEILING);

        System.out.println(bd);
        long l  = bd.setScale( 0, BigDecimal.ROUND_UP ).longValue(); // 向上取整
        long m  = bd.setScale( 0, BigDecimal.ROUND_DOWN ).longValue(); // 向下取整

        float y  = bd.setScale( 2, BigDecimal.ROUND_HALF_UP ).floatValue(); // 向下取整


        System.out.println(l);
        System.out.println(m);
        System.out.println(y);
    }
    public static void main2(String[] args) throws ParseException {
        String str = "20151231";
        Date beginTime = new SimpleDateFormat("yyyyMMdd").parse(str);
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.add(c.DATE, 1);
        Date date = c.getTime();
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(date));
    }

    public static void main3(String[] args) {
        String strDate = "2020-02-19 17:05:36";
        //Date current = DateTimeUtil.toDate(strDate,DateTimeUtil.LONGTIME_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);//设置毫秒数
        Date current = calendar.getTime();
        System.out.println(DateTimeUtil.formatDate(current,"yyyy-MM-dd HH:mm:ss SSS"));
        Date zero = DateTimeUtil.getDateOfZero(current);
        Date gip = DateTimeUtil.getDateOfZeroGip(DateTimeUtil.dateAddDay(current,1));
        System.out.println(DateTimeUtil.formatDate(zero,DateTimeUtil.LONGTIME_PATTERN));
        System.out.println(DateTimeUtil.formatDate(gip,DateTimeUtil.LONGTIME_PATTERN));
        System.out.println("-------------------------------------------------------");
    }

    public static void main4(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.10");
        bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println();
    }

    public static void main(String[] args) {
        String faceid = "hu0001_u80e1u6b4c_35.5";
        String[] temp = faceid.split("_");
        for (String s : temp) {
            System.out.println(s);
        }
    }
}
