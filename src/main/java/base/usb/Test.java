package base.usb;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-03-04
 * @Desc:
 */
public class Test {
    public static void main(String[] args) throws Exception{
        String []cmdarray = new String[5];
        cmdarray[0] = "cmd";
        cmdarray[1] = "/K";
        cmdarray[2] = "start";
        cmdarray[3] = "chrome.exe";
        cmdarray[4] = "\"https://www.baidu.com\"";
        Runtime.getRuntime().exec(cmdarray);
    }
}
