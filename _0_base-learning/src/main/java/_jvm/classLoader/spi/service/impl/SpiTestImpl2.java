package _jvm.classLoader.spi.service.impl;

import _jvm.classLoader.spi.service.ISpiTest;

public class SpiTestImpl2 implements ISpiTest {
    @Override
    public void saySpi() {
        System.out.println("call spi 2");
    }
}
