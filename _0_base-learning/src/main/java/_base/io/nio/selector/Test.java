package _base.io.nio.selector;


import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *      OPS ，SelectionKey的四个常量表示：
 *          SelectionKey.OP_READ
 *          SelectionKey.OP_WRITE
 *          SelectionKey.OP_CONNECT
 *          SelectionKey.OP_ACCEPT
 *          若监听不止一个事件，则可以使用"位或"操作符连接
 *
 *
 *      SelectionKey：表示SelectableChanel 和 Selector之间的注册关系。
 *          对象有以下方法：
 *              int interestOps()           //获取感兴趣事件的集合
 *              int readyOps()              //获取通道已经准备就绪的操作的集合
 *              SelectableChannel channel() //获取注册通道
 *              Selector selector()         //返回选择器
 *              boolean isReadable()        //检测Channel 中读事件是否就绪
 *              boolean isWritable()        //检测Channel 中写事件是否就绪
 *              boolean isConnectable()     //检测Channel 中连接是否就绪
 *              boolean isAcceptable()      //检测Channel 中接收是否就绪
 *
 *       Selector 常用方法
 *              Set<SelectionKey> keys()    //所有SelectionKey集合，代表注册在该Selector上的Channel
 *              selectKeys()                //被选择的SelectionKey 集合。返回此Selector的已选择键集
 *              int select()                //监控所有注册的Channel。当他们中间有需要处理的IO操作时，该方法返回。
 *                                              并将对应的SelectKey加入被选择的SelectKey集合中，该方法返回这些Channel的数量
 *              int select(long timeout)    //可以设置超市时长的select（）操作
 *              int selectNow()             //执行一个立即返回的select（）操作，该方法不会阻塞线程
 *              Selector wakeup()           //使一个还未返回的select（）方法立即返回
 *              void close()                //关闭该选择器
 *
 *
 *
 *
 */

public class Test {

    public static void main1(String[] args) {
        Test test = new Test();
        try {
            test.client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void client() throws Exception{
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("wtfu.site",14307));
        //切换非阻塞模式
        socketChannel.configureBlocking(false);
        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //从标准输入读取数据
        Scanner scanner = new Scanner(System.in);
        //循环读取并且发送
        while(scanner.hasNext()){
            String str = scanner.next();
            byteBuffer.put(((new Date()).toString() + "\n" + str).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //关闭通道
        socketChannel.close();
    }

    public static void main(String[] args) {
        try {
            demo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo() throws Exception{
        SocketChannel client = SocketChannel.open();

        client.configureBlocking(false);

        client.connect(new InetSocketAddress("wtfu.site",14307));


        while(!client.isConnected()){ TimeUnit.SECONDS.sleep(2);
            System.out.println("sleep");}

        System.out.println(client.finishConnect());

        try{
            for(;;){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = client.read(byteBuffer);
                if(read > -1){
                    byteBuffer.flip();
                    byte[] dst = new byte[byteBuffer.limit()];
                    byteBuffer.get(dst,0,dst.length);
                    System.out.println(new String(dst));
                }
                System.out.println("do else something");
                TimeUnit.SECONDS.sleep(2);
            }
        }catch (Exception e){ e.printStackTrace();}
        finally {
            client.close();
        }
    }

    @org.junit.Test
    public void server() throws Exception{
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //获取一个选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上，并且指定监听接受事件
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        //轮询获取选择器上已经就绪的事件
        while(selector.select() > 0){
            //获取所有就绪所有选择键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while(iterator.hasNext()){
                //获取一个就绪选择键
                SelectionKey next = iterator.next();
                //判断类型 ，处理之，
                if(next.isAcceptable()){
                    //若接受就绪，获取客户端连接
                    SocketChannel tempSocket = serverSocketChannel.accept();
                    //设置非阻塞
                    tempSocket.configureBlocking(false);
                    //将该通道注册到选择器上面，监听读事件
                    tempSocket.register(selector,SelectionKey.OP_READ);

                }else if(next.isReadable()){
                    //若是读事件，则根据选择键获取通道
                    SocketChannel channel = (SocketChannel) next.channel();
                    //分配指定大小缓冲区
                    ByteBuffer allocate = ByteBuffer.allocate(1024);


                    // 客户端关闭后也会出现可读数据
                    if(channel.finishConnect()){
                        channel.close();
                        continue;
                    }
                    //处理发送过来的数据
                    int len = 0;
                    while((len = channel.read(allocate)) != -1){
                        allocate.flip();
                        System.out.println(new String(allocate.array(),0,len));
                        allocate.clear();
                    }
                }
                //移除选择键
                iterator.remove();
            }
        }
    }


    public void testApi() throws Exception{
        Socket socket = new Socket();
        SocketChannel sc = socket.getChannel();
        SelectionKey selectionKey = null;
        selectionKey.interestOps();
        Selector open = Selector.open();
        Scanner scanner = new Scanner(System.in);
        scanner.next();

    }




}
