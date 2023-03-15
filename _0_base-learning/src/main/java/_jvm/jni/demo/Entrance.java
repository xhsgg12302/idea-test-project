package _jvm.jni.demo;

public class Entrance {

    static{
        // libexample.so is here: /usr/lib/java;x
        //System.out.println();

        //System.setProperty("java.library.path",System.getProperty("user.dir") + "/_0_base-learning/src/main/resources");

        //System.getProperties().entrySet().stream().forEach(item -> System.out.println(item.getKey() + ":" + item.getValue()));
        //System.loadLibrary("example");


        // 这个.so链接 不好用
        System.load("/Users/mac/Documents/idea-projects/idea-test-project/_0_base-learning/src/main/resources/libmylib.dylib");


    }

    public static native void myFunction();

    public static void main(String[] args) {
        myFunction();
    }
}
