package _base.socket.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * see:
 *
 * @date 2024/4/14
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class JavaTLSClientExample {

    private static final String[] protocols = new String[]{"TLSv1.2"};
    private static final String[] cipher_suites = new String[]{"TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384","TLS_RSA_WITH_AES_256_CBC_SHA256"};//,"TLS_AES_128_GCM_SHA256"};

    /**
     * https://snyk.io/blog/implementing-tls-in-java/
     * https://www.cloudflare.com/zh-cn/learning/ssl/what-happens-in-a-tls-handshake/
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SSLSocket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {

            // Step : 1
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Step : 2
            socket = (SSLSocket) factory.createSocket("wtfu.site", 443);

            // Step : 3
            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);

            // Step : 4 {optional}
            socket.startHandshake();

            // Step : 5
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError()) {
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");
            }

            // Step : 6
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
