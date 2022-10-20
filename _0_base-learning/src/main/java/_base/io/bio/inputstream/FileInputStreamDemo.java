package _base.io.bio.inputstream;

import org.junit.Test;

import java.io.*;
import java.net.URL;

public class FileInputStreamDemo {

    @Test
    public void demoWithoutTryWithResource(){
        // URL resource = this.getClass().getClassLoader().getResource("../../yqgz.cer");
        // File file = new File(resource.toURI());
        URL resource = this.getClass().getResource("../../res/yqgz.cer");
        File file = new File(resource.getFile());
        InputStream ins = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            System.out.println(file.isFile() ? file.length() : 0);
            ins = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            while((len = ins.read(data)) != -1){
                byteArrayOutputStream.write(data, 0, len);
            }
            System.out.println(byteArrayOutputStream.toByteArray().length);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Test
    public void demoWithTryWithResource(){
        URL resource = this.getClass().getResource("../../res/yqgz.cer");
        File file = new File(resource.getFile());
        try (InputStream ins = new FileInputStream(file)){
            // file.length() 与后面读取到的字节数一致。都是1583
            System.out.println(file.isFile() ? file.length() : 0);
            byte[] data = new byte[1024 * 2];
            int count = ins.read(data);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
