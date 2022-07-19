package _jvm.classLoader.spi.impl;

import _jvm.classLoader.spi.ISpiTest;

public class SpiTestImpl2 implements ISpiTest {
    @Override
    public void saySpi() {
        System.out.println("call spi 2");
    }
}
