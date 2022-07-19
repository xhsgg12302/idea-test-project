package _base.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2022/6/30
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class DemoWeakReference {

    public static void main(String[] args) {
        //demo();
        //


        withRqDemo();
    }

    public static void demo() {
        Object obj = new Object();
        System.out.println(obj);
        WeakReference<Object> weak = new WeakReference<>(obj);
        obj = null;
        // 此处的 obj = null；是为了让GC回收的时候，当前obj引用不影响回收。 或者可以直接使用 new WeakReference<>(new Object());
        System.out.println(weak.get());
        System.gc();
        System.out.println(weak.get());
    }

    public static void noRqDemo(){
        // 下面跟上面的方式基本一直，只不过使用构造器包装了一下。
        String str = new String("Hello World");
        Cleaner<String> strRf = new Cleaner<>(str);
        str = null;
        System.out.println(strRf.get());
        System.gc();
        System.out.println(strRf.get());
    }

    public static void withRqDemo(){
        ReferenceQueue<String> rqStr = new ReferenceQueue<>();
        String str = new String("Hello World");
        Cleaner<String> strRf = new Cleaner<>(str,rqStr);
        str = null;
        System.out.println(strRf.get());
        System.out.println(strRf);
        System.out.println(rqStr.poll());
        System.gc();
        System.out.println(strRf.get());
        System.out.println(rqStr.poll());
    }


    public static class Cleaner<T> extends WeakReference<T> {

        public Cleaner(T obj) { super(obj); }
        public Cleaner(T obj, ReferenceQueue<T> rq) { super(obj, rq);}
    }
}
