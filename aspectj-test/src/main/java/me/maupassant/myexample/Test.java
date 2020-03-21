package me.maupassant.myexample;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-10
 * @Desc:
 */
public class Test implements BaseDao {

    public int insert(){
        System.out.println("doing insert ...");
        return 0;
    }
    public int update(){
        System.out.println("doing update ...");
        return 1;
    }
}
