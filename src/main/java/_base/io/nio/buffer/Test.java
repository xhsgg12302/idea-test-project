package _base.io.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-02
 * @Desc:
 */
public class Test {

    /**
     * _12302_2020-02-02
     * <p>
     *     buffer 类型：
     *     ByteBuffer
     *     CharBuffer
     *     ShortBuffer
     *     IntBuffer
     *     LongBuffer
     *     FloatBuffer
     *     DoubleBuffer
     *
     *     四个重要属性
     *     mark : 标记position 位置
     *     position : 当前位置
     *     limit : 限制
     *     capacity ： 容量
     *
     * </p>
     */


    public static void main(String [] args){
        test1();
    }

    public static void test1(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        print(byteBuffer);

        String str = "hello";
        byteBuffer.put(str.getBytes());
        print(byteBuffer);

        /*<切换成读模式>_12302_2020-02-02_*/
        byteBuffer.flip();
        print(byteBuffer);

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst,0,dst.length);
        System.out.println(new String(dst));
        print(byteBuffer);

        /*<rewind 可以将position 的位置倒回>_12302_2020-02-02_*/
        byteBuffer.rewind();
        print(byteBuffer);

        /*<mark, reset _draft.test>_12302_2020-02-02_*/
        byteBuffer.get(dst,0,2);
        System.out.println(new String(dst,0,2));
        byteBuffer.mark();
        byteBuffer.get(dst,0,2);
        System.out.println(new String(dst,0,2));
        print(byteBuffer);
        byteBuffer.reset();
        print(byteBuffer);

        byteBuffer.rewind();
        print(byteBuffer);

        byteBuffer.clear();
        print(byteBuffer);
        System.out.println((char)byteBuffer.get());


    }

    public static void print(Buffer buffer){
        System.out.println(
                "position:" + buffer.position() + "\t" +
                "limit:" + buffer.limit() + "\t" +
                "capacity:" + buffer.capacity()
        );
    }
}
