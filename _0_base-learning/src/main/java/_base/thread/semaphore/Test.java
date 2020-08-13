package _base.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class Test {

    public static void main(String[] args) {
        ZeroEvenOdd zeo = new ZeroEvenOdd(2);

        new Thread(()-> {

            try {
                zeo.even((i)->{System.out.print(i);});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {

            try {
                zeo.zero((i)->{System.out.print(i);});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {

            try {
                zeo.odd((i)->{System.out.print(i);});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    Semaphore z = new Semaphore(1);
    Semaphore e = new Semaphore(0);
    Semaphore o = new Semaphore(0);

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=0; i<n;i++) {
            z.acquire();
            printNumber.accept(0);
            if((i&1)==0) {
                o.release();
            }else {
                e.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2) {
            e.acquire();
            printNumber.accept(i);
            z.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2) {
            o.acquire();
            printNumber.accept(i);
            z.release();
        }
    }
}