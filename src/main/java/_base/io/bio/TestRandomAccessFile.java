package _base.io.bio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFile {
    private static final String url = "raf.txt";
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


    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = TestRandomAccessFile.getRAFWithModelRW();
        String word = "raf";
        raf.write(word.getBytes());
    }

}
