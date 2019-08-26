package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestBigdecimal {

    public static void main(String [] args){


        BigDecimal bd = new BigDecimal(80);

        BigDecimal bd1 = new BigDecimal(12.3);
        BigDecimal bd2 = new BigDecimal(5.235);
        BigDecimal bd3 = new BigDecimal(0.01);

        DecimalFormat df1 = new DecimalFormat("0.00");
        String str = df1.format(bd);
        new BigDecimal("2.00");

       System.out.println(new BigDecimal("2.00"));


        System.out.println(bd+"\t"+bd1+"\t"+bd2+"\t"+bd3);


        //System.out.println(bd.setScale(2)+"\t"+bd1.setScale(2)+"\t"+bd2.setScale(2)+"\t"+bd3.setScale(2));


        System.out.println(df1.format(bd)+"\t"+df1.format(bd1)+"\t"+df1.format(bd2)+"\t"+df1.format(bd3));


    }
}
