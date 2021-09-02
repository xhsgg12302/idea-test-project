package _base.inner_class;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2021/9/2
 * @since
 * @author 12302
 */
public class TestMemberClass {

    private static int num1 = 1;

    public static int num2 = 2;

    public int num3 = 3;

    private int num4 = 4;

    public class MemberInner{
        public void test(){
            System.out.println("member:" + (num1 + num2 + num3 + num4));
        }
    }

    public static void main(String[] args) {
        TestMemberClass.MemberInner inner = new TestMemberClass().new MemberInner();
        inner.test();
    }
}
