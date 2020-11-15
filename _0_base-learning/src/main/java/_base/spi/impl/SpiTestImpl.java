package _base.spi.impl;

import _base.spi.ISpiTest;

public class SpiTestImpl implements ISpiTest {

    @Override
    public void saySpi() {
        System.out.println("call saySpi");
    }
}
