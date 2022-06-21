package _utils.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 */
public class ThreadUtil {

    private static class Instance {

        private static ThreadPoolExecutor singleton = new ThreadPoolExecutor(
                3,
                Runtime.getRuntime().availableProcessors() + 1,
                200,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactory() {

                    private final ThreadGroup group;
                    private final AtomicInteger threadNumber = new AtomicInteger(1);
                    private final String namePrefix;

                    {
                        SecurityManager s = System.getSecurityManager();
                        group = (s != null) ? s.getThreadGroup() :
                                Thread.currentThread().getThreadGroup();
                        namePrefix = "pool-demo-thread-";
                    }

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(group, r,
                                namePrefix + threadNumber.getAndIncrement(),
                                0);
                        return t;
                    }
                });
    }

    public static ThreadPoolExecutor getInstance() {
        return Instance.singleton;
    }

    public static void keepRunning(){
        Thread thread = Thread.currentThread();
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

}
