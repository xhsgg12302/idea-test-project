package _base.io.nio.selector.reactor;

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
 * source : http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf
 *
 */
public class Reactor implements Runnable{

    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException{
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(
                new InetSocketAddress(port)
        );
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }


    @Override
    public void run() {
        //normally in a new Thread
        try{
            while(!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    dispatch(iterator.next());
                    selectionKeys.clear();
                }
            }
        } catch(IOException ex){}
    }

    void dispatch(SelectionKey k){
        Runnable  r = (Runnable)(k.attachment());
        if(r != null)
            r.run();
    }

    class Acceptor implements Runnable{

        @Override
        public void run() {
            try{
                SocketChannel accept = serverSocket.accept();
                if(accept != null)
                    new Handler(selector,accept);
            }catch (IOException ex){ /* ... */}
        }
    }
}

final class Handler implements Runnable{
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING =1;
    int state = READING;


    Handler(Selector sel, SocketChannel accept) throws IOException{
        socket = accept;
        accept.configureBlocking(false);
        //Optionally try first reade now;
        sk =  socket.register(sel,0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete(){ return false;}
    boolean outputIsComplete(){return false;}
    void process(){}

    @Override
    public void run() {
        try{
            if(state == READING) read();
            else if (state == SENDING) send();
        }catch (IOException ex){}
    }

    void read() throws IOException{
        socket.read(input);
        if(inputIsComplete()){
            process();
            state = SENDING;
            // Normal also do first write now;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }
    void send() throws IOException{
        socket.write(output);
        if(outputIsComplete())
            sk.cancel();
    }
}



