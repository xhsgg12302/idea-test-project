package _draft.test;

import java.io.IOException;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/8 10:09
 * @Description:
 */
public abstract class TestStructure<K,V> implements java.io.Closeable,java.io.Serializable,Cloneable{

    private Long id;


    String defautl;


    private String name = "elizabeth";

    /**
     * _12302_2019-07-08
     * @desc [final 的时候右上角一个钉子]
     */
    final protected char sex ='n';


    public String account;

    /**
     * _12302_2019-07-08
     * @desc [右上角的钉子final和右下角的方块static]
     */
    public static final Long serilizeId = 0x1006L;

    Object obj = new Object();



    /**
     * _12302_2019-07-08
     * @desc [为method的时候，返回值不是boolean 首字母不是is],为properties是，is+boolean ,或者getter,setter,
     * @param
     * @return boolean
     */
    public boolean isMax(){
        return false;
    }



    public static int calacMinNumPub(int max,int min){
        return max-min;
    }
    public  int calacMinNumPubNoStatic(int max,int min){
        return max-min;
    }

    static int calaMinNumDefault(int max,int min){
        return max-min;
    }

    int calcMinNumDefaultNoStatic(int max,int min){
        return max - min;
    }

    protected static int clacMinNumPro(int max,int min){
        return max-min;
    }
    protected  int clacMinNumProNoStatic(int max,int min){
        return max-min;
    }

    private static int clacMinNumPri(int max,int min){
        return max-min;
    }

    private int calcMinNumPriNoStatic(int max,int min){
        return max-min;
    }



    abstract void putAll(Map<? extends K, ? extends V> m);

    public boolean isColse(){
        return false;
    }


    /**
     * _12302_2019-07-08
     * @desc [name properties 上面的红绿点应该表示（访问和修改）]
     */
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String toString() {
        return "TestStructure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                '}';
    }

}
