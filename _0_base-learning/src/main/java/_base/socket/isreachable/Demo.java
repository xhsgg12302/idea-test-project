package _base.socket.isreachable;

import org.junit.Test;

import java.net.InetAddress;

/**
 * 如果不可达，用root权限运行，可代替ping
 */
public class Demo {

    @Test
    public void tes() throws Exception{
        InetAddress byName = InetAddress.getByName("wtfo.site");
        if(byName.isReachable(2000)){
            System.out.println(byName.getHostName()  + ": is reachable");
        }else{
            System.out.println("not reachable");
        }
    }
}
