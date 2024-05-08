package site.wtfu.framework.utils.bc;

import java.nio.ByteBuffer;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/6
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class CacheByte {

    private volatile ByteBuffer data = ByteBuffer.allocate(0);

    private final Object mutex = new Object();
    public void put(ByteBuffer bb){
        synchronized (mutex){
            int totalSize = data.remaining() + bb.remaining();
            ByteBuffer merged = ByteBuffer.allocate(totalSize);
            merged.put(data);
            merged.put(bb);
            merged.flip();
            data = merged;
            mutex.notifyAll();
        }
    }

    public int get(byte[] b, int off, int length){
        try{
            synchronized (mutex){
                while(data.remaining() < length){
                    mutex.wait();
                }
                data.get(b, off, length);
                return length;
            }
        }catch (Exception e){ throw new RuntimeException(e);}
    }

    public static void main(String[] args) {
        CacheByte cb = new CacheByte();

        cb.put(ByteBuffer.wrap("123".getBytes()));
        byte[] bytes = new byte[2];
        cb.get(bytes, 0, 2);
        cb.put(ByteBuffer.wrap("456".getBytes()));
    }
}
