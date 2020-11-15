package _base.spi.impl;

import _base.spi.ISpiTest;

public class SpiTestImpl2 implements ISpiTest {
    @Override
    public void saySpi() {
        System.out.println("call spi 2");
    }
}
