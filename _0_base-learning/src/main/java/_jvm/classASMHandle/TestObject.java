package _jvm.classASMHandle;

import java.io.*;
import java.net.URL;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestObject implements Serializable {

    private static final long serialVersionUID = 3804108859751123561L;

    private final char[] value;

    /** Cache the hash code for the string */
    private int hash;

    public TestObject() { value = new char[0];}

    public TestObject(char[] value) { this.value = value;}

    public int getIntHash(){ return hash;}

    public String getValue(int a,String b){
        int c = ++a;
        int d = c + 3;
        return hash + d + b;
    }

    public void otherTest(){
        Object mutex = new Object();
        URL resource = this.getClass().getClassLoader().getResource("note.txt");
        try (FileInputStream fileInputStream = new FileInputStream(resource.getPath())){
            byte[] bytes = new byte[50];
            int read = fileInputStream.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
