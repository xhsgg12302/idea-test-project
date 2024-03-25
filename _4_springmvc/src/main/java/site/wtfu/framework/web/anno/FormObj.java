package site.wtfu.framework.web.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/25
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormObj {
    //参数别名
    String value() default "";
    //是否展示, 默认展示
    boolean show() default true;
}
