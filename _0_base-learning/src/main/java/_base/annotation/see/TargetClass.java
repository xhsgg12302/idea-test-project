package _base.annotation.see;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-11
 * @Desc:
 */
public class TargetClass extends SecondAClass{

    /**
     * @see SecondAClass#setDomain(boolean)
     */
    public void testSeeAnnotation(){

    }

    public static void main(String[] args) {
        TargetClass t = new TargetClass();
        t.testSeeAnnotation();
    }
}
