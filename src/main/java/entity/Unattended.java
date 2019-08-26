package entity;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/5/8 14:44
 * @Description:
 */
public class Unattended {
    private String gateName;
    private String channelName;
    private String cameraIp;
    private String unique_identify;
    private String scanTime;

    public Unattended() {
    }

    public Unattended(String gateName, String channelName, String cameraIp, String unique_identify, String scanTime) {
        this.gateName = gateName;
        this.channelName = channelName;
        this.cameraIp = cameraIp;
        this.unique_identify = unique_identify;
        this.scanTime = scanTime;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }

    public String getUnique_identify() {
        return unique_identify;
    }

    public void setUnique_identify(String unique_identify) {
        this.unique_identify = unique_identify;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }
}
