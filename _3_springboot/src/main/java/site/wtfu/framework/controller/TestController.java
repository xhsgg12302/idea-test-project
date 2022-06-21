package site.wtfu.framework.controller;

import site.wtfu.framework.service.ItestAop;
import site.wtfu.framework.utils.stackoverflow.NetTask;
import site.wtfu.framework.utils.stackoverflow.NetworkListener;
import site.wtfu.framework.utils.stackoverflow.NetworkStatusThread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-26
 * @Desc:
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    /**
     * 测试到controller的线程是否每次新建，不是，就是http-nio-***
     */
    ThreadLocal<String> localVar  = new ThreadLocal<>();

    @RequestMapping(value = "test")
    @ResponseBody
    public Object test(){
        localVar.set("HelloWorld");
        return new HashMap(){{put("code",1000);put("desc","SUCCESS");}};
    }


    @Resource
    private ItestAop itestAop;
    @RequestMapping(value = "aop")
    @ResponseBody
    public Object aop(){
        System.out.println(localVar.get());
        Object test = null;
        //Object test = itestAop.test();
        return new HashMap(){{put("code",1000);put("desc",test);}};
    }

    @RequestMapping(value = "start")
    @ResponseBody
    public Object start(){
        try{
            new Thread(NetworkStatusThread.getInstance()).start();

            Thread.sleep(2* 1000L);
            NetworkStatusThread.getInstance().addNetTask(new NetTask("39.107.88.49", new NetworkListener() {
                @Override
                public void sendNetworkStatus(String status) {
                    System.out.println("39.107.88.49 \t" + status);
                }
            }));

            Thread.sleep(4 * 1000L);
            NetworkStatusThread.getInstance().addNetTask(new NetTask("127.0.0.1", new NetworkListener() {
                @Override
                public void sendNetworkStatus(String status) {
                    System.out.println("127.0.0.1 \t" + status);
                }
            }));
        }catch ( Exception e){
            // ignore...
        }
        return new HashMap(){{put("code",1000);put("desc","start");}};
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public Object checkNet(String ip){

        NetworkStatusThread.getInstance().addNetTask(new NetTask(ip, new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println(ip +" \t" + status);
            }
        }));
        return new HashMap(){{put("code",1000);put("desc","addTask");}};
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST ,consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public Object login(@RequestParam(required = false) String username, @RequestParam(required = false) String password, HttpServletRequest request) {
        return new HashMap(){{put("msg",username + "欢迎" );put("status",200);}};
    }

}
