package _base.design_pattern.chain_of_responsibility;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/1/27
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public abstract class AbstractLogger {
    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int ERROR = 3;

    protected int level;

    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        if(this.level >= level){
            write(message);
        }
        if(nextLogger != null){
            nextLogger.logMessage(level, message);
        }else{ System.out.println();}
    }

    abstract protected void write(String message);
}