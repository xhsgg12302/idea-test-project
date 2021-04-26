/**
 * Copyright_c 2018 qmhd.con.cn Inc. All Rights Reserved.
 * @Title UserDao.java
 * @Package: me.maupassant.springmvc.dao
 * @Email:  xhsgg12302@outlook.com   
 * @date: 2019年3月11日 上午12:08:14 
 * @version 1.0.0
 * @since JDK1.8
 */
package me.maupassant.springmvc.dao;

import java.util.List;
import java.util.Map;

import me.maupassant.springmvc.entity.User;

/**
 * @className UserDao
 * @description
 *
 */
public interface UserDao {

	User getUser(Map<String, Object> condition);

	Integer insert(User user);

	List getListFromUser(Map<String,Object> condition);
}
