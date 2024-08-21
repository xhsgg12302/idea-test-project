package site.wtfu.framework.utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import site.wtfu.framework.utils.bc.CacheByte;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/5
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class WSSClient extends WebSocketClient {

    public WSSClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to server.");
    }

    @Override
    public void onMessage(String message) {
        System.err.println("Received message: " + message);
    }


    private CacheByte cb = new CacheByte();
    @Override
    public void onMessage(ByteBuffer bytes) {
        cb.put(bytes);
    }

    public int getData(byte[] b, int off, int length)  {
        cb.get(b, off, length);
        return length;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed, code: " + code + ", reason: " + reason);
        close();
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("Error: " + ex);
        close();
    }

}
