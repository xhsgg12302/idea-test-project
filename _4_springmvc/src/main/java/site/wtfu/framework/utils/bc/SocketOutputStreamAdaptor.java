package site.wtfu.framework.utils.bc;

import site.wtfu.framework.utils.HexUtil;
import site.wtfu.framework.utils.WSSClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
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
public class SocketOutputStreamAdaptor extends OutputStream {

    private final WSSClient wssClient;

    private final URI uri;

    private final AtomicBoolean first = new AtomicBoolean(true);

    public SocketOutputStreamAdaptor(WSSClient wssClient, URI uri){
        this.wssClient = wssClient;
        this.uri = uri;
    }

    @Override
    public void write(int b) throws IOException {
        wssClient.send(new byte[]{(byte)b});
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] data = new byte[len];
        System.arraycopy(b, off, data, 0, len);

        if(first.get()){
            data = buildVlessHeader(data, uri);
            first.set(false);
        }
        wssClient.send(data);
    }

    public static byte[] buildVlessHeader(byte[] data, URI uri){

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        byte version = 1;
        buffer.put(version);

        UUID uuid = UUID.fromString("5f968565-cfe2-49c5-b13f-e540f8e002dc");
        //十六进制字符串uuid转换成字节
        byte[] uuidBytes = HexUtil.toBytes(uuid);
        buffer.put(uuidBytes);

        byte optLength = 0;
        buffer.put(optLength);

        byte command = 1;
        buffer.put(command);

        short port = 443;
        int uriPort = uri.getPort();
        if(uriPort != -1)
            port = (short) uriPort;
        buffer.putShort(port);

        byte addrType = 2; // 1:ipv4, 2:domain, 3:ipv6
        buffer.put(addrType);

        if(addrType == 1){
            byte[] addr = new byte[]{ (byte)139, (byte)155, (byte)77, (byte)112};
            //addr = new byte[]{ 127, 0, 0, 1};
            buffer.put(addr);
        }else if(addrType == 2){
            String host = uri.getHost();
            byte length = (byte) host.length();
            buffer.put(length);
            buffer.put(host.getBytes(StandardCharsets.UTF_8));
        }

        buffer.put(data);
        buffer.flip();
        byte[] rst = new byte[buffer.limit()];
        buffer.get(rst);
        return rst;
    }
}
