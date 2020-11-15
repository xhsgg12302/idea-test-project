package _base.spi.entry;

/**
 * 调用静态方法的时候会初始化
 */
public class DemoStaticInitialization {
    static {
        System.out.println("123");
    }

    public static void test(){}
}

