package _base.thread._volatile;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) throws Exception{
        final Test test = new Test();


        for (int z = 0; z < 10; z++) {

            for(int i=0;i<30 ;i++){
                Thread temp = new Thread(){
                    public void run() {
                        for(int j=0;j<10000;j++)
                            test.increase();
                    };
                };
                temp.start();
                temp.join();
            }
            System.out.println(test.inc);
            test.inc = 0;
        }

        //while(Thread.activeCount()>1)  //保证前面的线程都执行完
         //   Thread.yield();

    }
}
