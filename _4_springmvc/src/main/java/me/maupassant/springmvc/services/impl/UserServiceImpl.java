package me.maupassant.springmvc.services.impl;

import me.maupassant.springmvc.dao.UserDao;
import me.maupassant.springmvc.entity.User;
import me.maupassant.springmvc.services.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-27
 * @Desc:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer insert(User user) {
        return userDao.insert(user);
    }
}
