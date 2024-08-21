package site.wtfu.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/5
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class HttpVlessProxyUtil {

    /**
     * - { name: cf-vless-tls, type: vless, server: ws.12302.site, port: 8443, uuid: 5f968565-cfe2-49c5-b13f-e540f8e002dc,
     * udp: true, tls: true, network: ws,  ws-opts: { path: /, headers: { Host: ws.12302.site }}}
     *
     * @return
     */
    public static byte[] buildByte(){

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
        buffer.putShort(port);

        byte addrType = 1; // 1:ipv4, 2:domain, 3:ipv6
        buffer.put(addrType);

        byte[] addr = new byte[]{ (byte)139, (byte)155, (byte)77, (byte)112};
        //addr = new byte[]{ 127, 0, 0, 1};
        buffer.put(addr);


        buffer.put(buildReq());

        buffer.flip();
        byte[] rst = new byte[buffer.limit()];
        buffer.get(rst);
        return rst;
    }

    private static byte[] buildReq(){
        String requestUrl = "http://localhost:8080/test/hello";
        requestUrl = "/cc";

        // 构建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "wtfu.site");
        headers.put("Connection","keep-alive");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.2623.112 Safari/537.36");
        headers.put("Accept", "text/html:Charset=UTF-8,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        //headers.put("Accept-Encoding","gzip, deflate, br, zstd");
        headers.put("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");

        // 模拟构造GET请求的HTTP报文头部
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("GET ").append(requestUrl).append(" HTTP/1.1\r\n");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }
        requestBuilder.append("\r\n"); // 空行，表示头部结束

        // 转换为字节数组
        byte[] requestBytes = requestBuilder.toString().getBytes(StandardCharsets.UTF_8);

        return requestBytes;
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = buildReq();

        // 139, (byte)155, (byte)77, (byte)112
        Socket socket = new Socket("139.155.77.112", 80);
        socket.getOutputStream().write(bytes);

        //Creates a BufferedReader that contains the server response
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String outStr;

        //Prints each line of the response
        while((outStr = bufRead.readLine()) != null){
            System.out.println(outStr);
        }
        System.out.println(bytes.length);
    }
}
