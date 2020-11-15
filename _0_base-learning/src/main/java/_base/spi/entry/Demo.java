package _base.spi.entry;

import _base.spi.ISpiTest;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Demo {

    @Test
    public void test(){
        DemoStaticInitialization.test();
        ServiceLoader<ISpiTest> serviceLoader = ServiceLoader.load(ISpiTest.class);
        Iterator<ISpiTest> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            iterator.next().saySpi();
        }
    }
}
