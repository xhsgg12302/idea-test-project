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
public class Main {
    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
        AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);

        debugLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(errorLogger);

        return debugLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.INFO, "This is an info level information.");

        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error level information.");
    }
}
