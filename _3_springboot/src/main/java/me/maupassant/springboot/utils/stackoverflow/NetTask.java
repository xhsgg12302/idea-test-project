package me.maupassant.springboot.utils.stackoverflow;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class NetTask {

    private String ip;
    private NetworkListener networkListener;

    public NetTask(String ip, NetworkListener networkListener) {
        this.ip = ip;
        this.networkListener = networkListener;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public NetworkListener getNetworkListener() {
        return networkListener;
    }

    public void setNetworkListener(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public String toString() {
        return "NetTask{" +
                "ip='" + ip + '\'' +
                ", networkListener=" + networkListener +
                '}';
    }
}
