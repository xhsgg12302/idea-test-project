package network.stackoverflow;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class NetworkUtil {
    /**
     * _12302_2019-12-13
     * <p>
     *     global lock
     * </p>
     */
    private final Object lock = new Object();

    private static final Thread single = new Thread();

    public void detect(String ip ,NetworkListener networkListener){
        //new Thread(new NetworkStatusThread(ip,networkListener,lock)).start();
        //single.
    }
}
