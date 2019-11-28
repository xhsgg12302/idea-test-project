package me.maupassant.springmvc.services.impl;

import me.maupassant.springmvc.entity.User;
import me.maupassant.springmvc.services.ITest;
import me.maupassant.springmvc.services.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-27
 * @Desc:
 */
@Service
public class TestImpl implements ITest {

    @Resource
    private IUserService iUserService;

    @Override
    @Transactional
    public Integer testTransaction(User user) {
        Integer temp;
        try {
            temp = iUserService.insert(user);
            //int i = 10/0;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 9;
        }
        return temp;
    }
}
