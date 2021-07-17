package site.wtfu.framework.services.impl;

import site.wtfu.framework.dao.UserDao;
import site.wtfu.framework.entity.User;
import site.wtfu.framework.services.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List getList(Map map) {
        return userDao.getListFromUser(map);
    }
}
