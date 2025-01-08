package site.wtfu.framework.utils;

import org.bouncycastle.tls.TlsClient;
import org.bouncycastle.tls.TlsClientProtocol;
import org.bouncycastle.tls.TlsNoCloseNotifyException;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;
import site.wtfu.framework.utils.bc.SocketInputStreamAdaptor;
import site.wtfu.framework.utils.bc.SocketOutputStreamAdaptor;
import site.wtfu.framework.utils.bc.tls.CusDefaultTlsClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/6
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TLSTest {
    public static void main(String[] args) throws IOException {

        plainSocketTLSTest();
        //plainWssTLSTest();
    }

    public static void plainWssTLSTest() throws IOException {
        WSSClient wssclient = null;
        TlsClientProtocol protocol = null;
        try {
            URI
                    //target = new URI("https://www.baidu.com/");
                    //target = new URI("https://www.sina.com.cn/");
                    target = new URI("https://www.qq.com/");
                    //target = new URI("https://www.google.com/");
                    //target = new URI("https://beian.mps.gov.cn/#/query/webSearch/");
                    //target = new URI("https://test.wtfu.site/robots.txt");
            //target = new URI("https://wtfu.site/");
            target = new URI("https://login.yfjc.xyz/api/v1/client/subscribe?token=xxxxx");


            //wssclient = new WSSClient(new URI("ws://localhost:8787"));
            wssclient = new WSSClient(new URI("wss://ws.12302.site:8443"));
            wssclient.addHeader("Host", "ws.12302.site");
            wssclient.connectBlocking();
            byte[] request = buildReq(target);


            // ref : https://downloads.bouncycastle.org/fips-java/docs/BC-FJA-(D)TLSUserGuide-1.0.13.pdf (4.2.1)
            // TlsCrypto to support client functionality
            TlsCrypto crypto = new BcTlsCrypto(new SecureRandom());

            TlsClient client = new CusDefaultTlsClient(crypto) {};
            protocol = new TlsClientProtocol(
                    new SocketInputStreamAdaptor(wssclient),
                    new SocketOutputStreamAdaptor(wssclient, target)
            );
            // Performs a TLS handshake
            protocol.connect(client);
            // Read/write to protocol.getInputStream(), protocol.getOutputStream()

            protocol.getOutputStream().write(request);
            //Creates a BufferedReader that contains the server response
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(protocol.getInputStream()));
            String outStr;

            //Prints each line of the response
            while((outStr = bufRead.readLine()) != null){
                System.out.println(outStr);
            }
        } catch (TlsNoCloseNotifyException tncne){ System.err.println(tncne.getMessage());
        } catch (Exception e) { e.printStackTrace();} finally {
            if(protocol != null) protocol.close();
            if(wssclient != null) wssclient.close();
        }
    }

    public static void plainSocketTLSTest() throws IOException {
        Socket socket = null;
        TlsClientProtocol protocol = null;
        try {
            URI
                    //target = new URI("https://www.baidu.com/"); // ✓
                    //target = new URI("https://www.sina.com.cn/"); // ✓
                    //target = new URI("https://www.qq.com/");

                    //target = new URI("https://mp.weixin.qq.com/s/JRsbK1Un2av9GKmJ8DK7IQ");
                    target = new URI("https://www.google.com/");
                    //target = new URI("https://beian.mps.gov.cn/#/query/webSearch/");
                    //target = new URI("https://test.wtfu.site/robots.txt");
                    target = new URI("https://wtfu.site/");
                    //target = new URI("https://music.163.com");
                    target = new URI("https://yifen.996.buzz/gengxin");

            socket = new Socket();
            socket.connect(InetSocketAddress.createUnresolved(target.getHost(), 443));

            byte[] request = buildReq(target);


            // ref : https://downloads.bouncycastle.org/fips-java/docs/BC-FJA-(D)TLSUserGuide-1.0.13.pdf (4.2.1)
            // TlsCrypto to support client functionality
            TlsCrypto crypto = new BcTlsCrypto(new SecureRandom());

            TlsClient client = new CusDefaultTlsClient(crypto) {};
            protocol = new TlsClientProtocol(
                    socket.getInputStream(),
                    socket.getOutputStream()
            );
            // Performs a TLS handshake
            protocol.connect(client);
            // Read/write to protocol.getInputStream(), protocol.getOutputStream()

            protocol.getOutputStream().write(request);
            //Creates a BufferedReader that contains the server response
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(protocol.getInputStream()));
            String outStr;

            //Prints each line of the response
            while((outStr = bufRead.readLine()) != null){
                System.out.println(outStr);
            }
        } catch (TlsNoCloseNotifyException tncne){ System.err.println(tncne.getMessage());
        } catch (Exception e) { e.printStackTrace();} finally {
            if(protocol != null) protocol.close();
            if(socket != null) socket.close();
        }
    }

    private static byte[] buildReq(URI target){

        String requestUrl = "";
        if(!StringUtils.isEmpty(target.getPath())){
            requestUrl += target.getPath();
            if(target.getQuery() != null){ requestUrl += "?" + target.getQuery();}
            requestUrl += " ";
        }

        // 构建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", target.getHost());
        //headers.put("Connection", "close");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.2623.112 Safari/537.36");
        headers.put("Accept", "*/*");
        headers.put("Cache-Control", "no-cache");
        headers.put("Pragma", "no-cache");
        //headers.put("Range","bytes=0-8");

        // 模拟构造GET请求的HTTP报文头部
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("GET ").append(requestUrl).append("HTTP/1.1\r\n");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }
        requestBuilder.append("\r\n"); // 空行，表示头部结束

        // 转换为字节数组
        byte[] requestBytes = requestBuilder.toString().getBytes(StandardCharsets.UTF_8);

        return requestBytes;
    }


    @Test
    public void test(){
        byte[] bytes = new byte[]{(byte) 0x01,(byte)0x02,(byte) 0x03};
        System.out.println(bytesToHex(bytes));

    }

    public static String bytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x ", b);
        }
        return formatter.toString();
    }
}
