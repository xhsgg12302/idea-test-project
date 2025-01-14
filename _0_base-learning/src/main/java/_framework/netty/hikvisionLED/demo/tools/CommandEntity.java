package _framework.netty.hikvisionLED.demo.tools;

import _framework.netty.hikvisionLED.demo.protocol.LedRequestMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-26
 * @Desc:
 */
public class CommandEntity {

    private int index;

    private List<Object> objs = new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public synchronized void setIndex(int index) {
        this.index = index;
    }

    public List<Object> getObjs() {
        return objs;
    }

    public synchronized void setObjs(LedRequestMessage[] ledRequestMessages) {
        for (LedRequestMessage ledRequestMessage : ledRequestMessages) {
            this.objs.add(ledRequestMessage);
        }
        this.objs.add(new String("stop"));
    }
}
