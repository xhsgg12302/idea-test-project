package me.maupassant;

import me.maupassant.myexample.Test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Test test = new Test();

        System.out.println("duixiang:"+test);
        test.insert();
        test.update();
    }
}
