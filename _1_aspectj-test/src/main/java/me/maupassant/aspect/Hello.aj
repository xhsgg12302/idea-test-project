package me.maupassant.aspect;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-09
 * @Desc:
 */
public aspect Hello {
    before(): execution(* me.maupassant.service.*.*(..))
            {
                System.out.println("执行模拟权限检查");
            }
}
