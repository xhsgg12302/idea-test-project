package _base.io.nio.Scalable_IO_in_Java.baisc_reactor_design;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/1
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Reactor {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    // Reactor 1: Setup
    Reactor(int port) throws IOException {

        /*
            Alternatively, use explicit SPI provider:
            SelectorProvider p = SelectorProvider.provider();
            selector = p.openSelector();
            serverSocket = p.openServerSocketChannel();
        */
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();

        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);

        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    // Reactor 2: Dispatch Loop
    public void run() { // normally in a new Thread
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext())
                    dispatch((SelectionKey)(it.next()));
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }
    void dispatch(SelectionKey k) {
        Runnable r = (Runnable)(k.attachment());
        if (r != null)
            r.run();
    }

    // Reactor 3: Acceptor
    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null)
                    new Handler(selector, c);
            }
            catch(IOException ex) { /* ... */ }
        }
    }

    final class Handler implements Runnable {

        // Reactor 4: Handler setup
        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        Handler(Selector sel, SocketChannel c)
                throws IOException {
            socket = c;
            c.configureBlocking(false);
            // Optionally try first read now
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        boolean inputIsComplete() { /* ... */ return true; }
        boolean outputIsComplete() { /* ... */ return true; }
        void process() { /* ... */}

        // Reactor 5: Request Handling
        @Override
        public void run() {
            try {
                if (state == READING) read();
                else if (state == SENDING) send();
            } catch (IOException ex) { /* ... */ }
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // Normally also do first write now
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }
        void send() throws IOException {
            input.flip();
            socket.write(input);
            if (outputIsComplete()) sk.cancel();
        }

        // Per-State Handlers (A simple use of GoF State-Object pattern)
        // Rebind appropriate handler as attachment
        public void run2() throws IOException { // initial state is reader
            socket.read(input);
            if (inputIsComplete()) {
                process();
                sk.attach(new Sender());
                sk.interestOps(SelectionKey.OP_WRITE);
                sk.selector().wakeup();
            }
        }
        class Sender implements Runnable {
            @SneakyThrows
            public void run(){ // ...
                socket.write(output);
                if (outputIsComplete()) sk.cancel();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Reactor(12302).run();
    }
}
