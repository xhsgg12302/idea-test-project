package me.maupassant.springmvc.services;

import me.maupassant.springmvc.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-27
 * @Desc:
 */
public interface IUserService {

    Integer insert(User user);

    List getList(Map map);

}
