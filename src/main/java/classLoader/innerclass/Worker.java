package classLoader.innerclass;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-06
 * @Desc:
 */
public class Worker implements Person{
    @Override
    public void say(String words) {
        System.out.println(words);
    }
}
