package draft;

import java.util.concurrent.Semaphore;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-09
 * @Desc:
 */
public class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    Semaphore head = new Semaphore(1);
    Semaphore tail = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            head.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            tail.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            tail.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            head.release();
        }
    }
}
