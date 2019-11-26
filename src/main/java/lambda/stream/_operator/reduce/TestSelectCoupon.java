package lambda.stream._operator.reduce;

import lambda.stream.model.CouponRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-26
 * @Desc:
 */
public class TestSelectCoupon {

    private static List<CouponRecord> selectCoupon(List<CouponRecord> list) {
        list.stream()
                .filter(couponRecord -> "1".equals(couponRecord.getHasCover()))
                .sorted(Comparator.comparing(CouponRecord::getEndTime))
                .reduce((cp1,cp2)->{cp1.setFaceValue(cp1.getFaceValue().add(cp2.getFaceValue()));return cp1;})
                .orElseGet(()->{CouponRecord c = new CouponRecord(); c.setHasCover("1"); return c;})
                .setHasCover("2");

        return list.stream()
                .filter(couponRecord -> !"1".equals(couponRecord.getHasCover()))
                .sorted(Comparator.comparing(CouponRecord::getFaceValue).thenComparing(CouponRecord::getEndTime))
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {

        Date date = new Date();
        List<CouponRecord> list = new ArrayList<>();
        list.add(new CouponRecord(1l,new BigDecimal(2.4),"0",date));
        list.add(new CouponRecord(2l,new BigDecimal(1.5),"0",date));
        //list.add(new CouponRecord(3l,new BigDecimal(9.2),"1",date));
        //list.add(new CouponRecord(4l,new BigDecimal(3.4),"1",date));

        List<CouponRecord> couponRecords = selectCoupon(list);

        System.out.println();

    }

}
