/**
 * Copyright_c 2018 qmhd.con.cn Inc. All Rights Reserved.
 * @Title LoginServiceImpl.java
 * @Package: site.wtfu.framework.services.impl
 * @Email:  xhsgg12302@outlook.com   
 * @date: 2019年3月11日 上午12:05:40 
 * @version 1.0.0
 * @since JDK1.8
 */
package site.wtfu.framework.services.impl;

import javax.annotation.Resource;

import site.wtfu.framework.dao.UserDao;
import site.wtfu.framework.entity.User;
import site.wtfu.framework.services.LoginService;

import org.springframework.stereotype.Service;

/**
 * @className LoginServiceImpl
 * @description
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Resource
  private UserDao userDao;
	
	
  @Override
  public User getUser() {
	  // TODO Auto-generated method stub
	  return userDao.getUser(null);
  }

}
