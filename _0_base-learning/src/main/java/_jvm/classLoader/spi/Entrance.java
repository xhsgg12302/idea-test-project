package _jvm.classLoader.spi;

import _jvm.classLoader.spi.service.ISpiTest;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Entrance {

    public static void main(String[] args) {
        ServiceLoader<ISpiTest> serviceLoader = ServiceLoader.load(ISpiTest.class);
        Iterator<ISpiTest> iterator = serviceLoader.iterator();

        while(iterator.hasNext()){
            ISpiTest next = iterator.next();
            next.saySpi();
        }
    }
}
