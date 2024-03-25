/**
 *
 * 1,注解ExceptionHandler(参考 @TestExceptionController)
 *
 *      作用对象为方法，可以在baseController中定义
 *
 *      public class BaseController{
 *
 *             @ExceptionHandler(Exception.class)
 *             @ResponseBody
 *             public String exception(Exception ex){
 *                 return this.getClass.getSimpleName() + ": " + ex.getMessage();
 *             }
 *      }
 *
 *
 *  2,注解ControllerAdvice 等级低于第一个。而且第一个只处理当前Controller。顺序为 @ExceptionHandler,@ControllerAdvice,HandlerExceptionResolver
 *
 *
 *
 *  3,实现HandlerExceptionResolver接口
 *      400异常不会处理，500 可以
 *      出现的原因；被默认的处理器处理了，如图： https://segmentfault.com/img/bVbnJqq?w=866&h=497
 *      解决办法，实现ordered 接口排序第一位。就可以实现所以异常处理。
 *
 *
 *
 *
 *
 *
 *
 */

package site.wtfu.framework.web.glbExceptionHandler;