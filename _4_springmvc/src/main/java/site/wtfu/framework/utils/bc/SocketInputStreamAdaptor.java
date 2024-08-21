package site.wtfu.framework.utils.bc;

import site.wtfu.framework.utils.WSSClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/6
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class SocketInputStreamAdaptor extends InputStream {

    private final WSSClient wssClient;

    private final AtomicBoolean first = new AtomicBoolean(true);

    public SocketInputStreamAdaptor(WSSClient wssClient){
        this.wssClient = wssClient;
    }

    @Override
    public int read() throws IOException {
        byte[] bytes = new byte[1];
        return wssClient.getData(bytes, 0, 1);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if(first.get()){
            byte[] vlessHeader = new byte[2];
            wssClient.getData(vlessHeader, 0, 2);
            first.set(false);
        }
        return wssClient.getData(b, off, len);
    }
}
