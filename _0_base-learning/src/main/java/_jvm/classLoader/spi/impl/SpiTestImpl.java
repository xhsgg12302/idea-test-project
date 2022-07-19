package _jvm.classLoader.spi.impl;

import _jvm.classLoader.spi.ISpiTest;

public class SpiTestImpl implements ISpiTest {

    @Override
    public void saySpi() {
        System.out.println("call saySpi");
    }
}
