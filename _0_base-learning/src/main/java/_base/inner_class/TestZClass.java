package _base.inner_class;

/**
 * 
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/9/2
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class TestZClass {

    public static void main(String[] args) {


        /* _12302_2021/9/2_< 静态内部类外部访问 > */

        TestStaticClass out1 = new TestStaticClass();
        TestStaticClass.StaticInner out11 = new TestStaticClass.StaticInner();
        out11.test();



        /* _12302_2021/9/2_< 成员内部类外部访问 > */
        TestMemberClass out2 = new TestMemberClass();
        TestMemberClass.MemberInner out22 = new TestMemberClass().new MemberInner();
        out22.test();


        /* _12302_2021/9/2_< 局部内部类外部访问 > */
        TestLocalClass out3 = new TestLocalClass();
        out3.test();

    }

}
