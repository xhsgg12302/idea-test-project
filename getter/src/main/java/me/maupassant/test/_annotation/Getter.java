package me.maupassant.test._annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-25
 * @Desc:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Getter {
    //getter注解
}
