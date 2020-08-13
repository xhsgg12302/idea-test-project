package _base.io.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * https://www.zhihu.com/question/31274481
 * I/O四种方式 1，循环IO测试。2，程序中断IO。3，DMA方式，4，通道方式
 *
 * DMA 和 channel 的区别
 * 1，DMA 只能实现固定的数据传送控制，并且有cpu 参与，而通道有自己的指令和程序，具有更强的独立处理数据输入和输出的能力
 * 2，DMA 只能控制一台或少数同类设备，通道可以控制多台同类或者不同的设备
 *
 * 通道：用于原节点和目标节点的连接，需要配合缓冲区进行数据的传输
 *
 * 主要实现类
 *  java.nio.channels.Channel 接口：
 *      |-- FileChannel
 *      |-- SocketChannel
 *      |-- ServerSocketChannel
 *      |-- DatagramChannel
 *
 * 获取的三种方式
 *     1，Java 对 支持通道的类提供getChannel()方法
 *     2，jdk 1.7 针对各个通道提供open()方法
 *     3，jdk 1.7 Files工具类的  newByteChannel()
 *
 * 分散读取和聚集写入，好处根据Java NIO 说 更加高效，可以减少或避免缓冲区的拷贝和系统调用。
 *
 * 字符集：编解码
 */
public class Test {

    public static void main(String[] args) throws Exception{
        //test1();
        //test2();

        //test3();
        //test4();

        test5();
    }

    //普通方式获取通道
    public static void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try{
            fis = new FileInputStream("4.png");
            fos = new FileOutputStream("2.png");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(inChannel.read(buffer)!= -1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(fis !=null){
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(fos !=null){
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    //open 方式 + 映射内存文件
    public static void test2() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/stevenobelia/Downloads/temporary-download/2.rmvb"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/stevenobelia/Downloads/temporary-download/3.rmvb"), StandardOpenOption.READ,StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] buffer = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(buffer);
        outMappedByteBuffer.put(buffer);

        outChannel.close();
        inChannel.close();
    }

    // transferTo(),transferFrom()
    public static void test3() throws Exception{
        FileChannel inFileChannel = FileChannel.open(Paths.get("2.png"),StandardOpenOption.READ);
        FileChannel outFileChannel = FileChannel.open(Paths.get("5.png"),StandardOpenOption.READ, StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        inFileChannel.transferTo(0,inFileChannel.size(),outFileChannel);

        inFileChannel.close();
        outFileChannel.close();
    }

    //scatter gather
    public static void test4() throws Exception{
        RandomAccessFile raf = new RandomAccessFile("_draft.test.txt","rw");

        FileChannel rafChannel = raf.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(12);
        ByteBuffer buffer2 = ByteBuffer.allocate(21);

        ByteBuffer[] buffers = {buffer1,buffer2};

        rafChannel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
            System.out.println(new String(buffer.array(),0,buffer.array().length));
            System.out.println("==========================");
        }

        RandomAccessFile raf2 = new RandomAccessFile("test2.txt","rw");
        FileChannel raf2Channel = raf2.getChannel();

        raf2Channel.write(buffers);

        raf2Channel.close();
        rafChannel.close();
        raf.close();
        raf2.close();
    }

    //charset
    public static void test5() throws Exception{
        //System.out.println(Charset.availableCharsets().entrySet().size());
        //Charset.availableCharsets().entrySet().forEach((entry-> System.out.println(entry.getKey() + "\t" + entry.getValue())));

        Charset gbk = Charset.forName("GBK");

        CharsetEncoder charsetEncoder = gbk.newEncoder();

        ByteBuffer encode = charsetEncoder.encode(CharBuffer.wrap("钓鱼岛是中国的"));

        //encode.flip(); 不能flip();会导致limit为position 0；

        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer decode = charsetDecoder.decode(encode);

        System.out.println(decode.toString());
    }
}
