package _base.io.bio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFile {
    private static final String url = "src/main/resources/test-files/raf.txt";
    private static final String [] model = {"r","rw","rws","rwd"};

    public static RandomAccessFile getRAFWithModelR() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(new File(url), model[0]);
        return raf;
    }

    public static RandomAccessFile getRAFWithModelRW() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(new File(url), model[1]);
        return raf;
    }

    public static RandomAccessFile getRAFWithModelRWS() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(new File(url), model[2]);
        return raf;
    }

    public static RandomAccessFile getRAFWithModelRWD() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(new File(url), model[3]);
        return raf;
    }


    /**
     * 将指定的字节数写入原来的内容，不会覆盖其他的
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        RandomAccessFile raf = TestRandomAccessFile.getRAFWithModelRW();
        String word = "hello";
        raf.write(word.getBytes());
        raf.close();
    }

}
