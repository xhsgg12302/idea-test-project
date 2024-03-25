package site.wtfu.framework.web.glbExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class ExceptionHandlerAdvice {


    //也可以出现   自定义Exception，和其他Exception

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception ex){
        return this.getClass().getSimpleName() + ": " + ex.getMessage();
    }
}
