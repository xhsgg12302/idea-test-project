package _draft.draft;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-10
 * @Desc:
 */
public class IsAssignableFrom {


    public static void main(String[] args) {

        if(Exception.class.isAssignableFrom(IllegalAccessException.class))
            System.out.println(1);
        if(IllegalAccessException.class.isAssignableFrom(IllegalAccessException.class))
            System.out.println(2);

    }


}
