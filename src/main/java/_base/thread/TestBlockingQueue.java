package _base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TestBlockingQueue {
    public static void main(String[] args)  throws Exception{
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(2);
        blockingQueue.take();
    }
}
