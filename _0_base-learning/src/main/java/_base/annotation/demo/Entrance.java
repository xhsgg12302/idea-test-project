package _base.annotation.demo;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/21
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Entrance {

    /**
     * see: https://cloud.tencent.com/developer/article/1522675
     *      https://www.cnblogs.com/ajianbeyourself/p/14560581.html#/
     * @param args
     */
    public static void main(String[] args) {
        //System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}
