/**
 * Copyright_c 2018 qmhd.con.cn Inc. All Rights Reserved.
 * @Title LoginController.java
 * @Package: me.maupassant.springmvc.controller
 * @Email:  xhsgg12302@outlook.com   
 * @date: 2019年3月10日 下午11:57:34 
 * @version 1.0.0
 * @since JDK1.8
 */
package me.maupassant.springmvc.controller;

import me.maupassant.springmvc.entity.User;
import me.maupassant.springmvc.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @className LoginController
 * @description
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	private LoginService loginService;

	@RequestMapping("/login")
	public String toIndex(HttpServletRequest request, Model model) {
		User user = loginService.getUser();
		model.addAttribute("user", user);
		return "content";
	}

	/*@RequestMapping("/content")
	public String getContextBean(HttpServletRequest request, Model model) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		System.out.println("helloworld");
		ApplicationContext son = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
		//String[] str = context.getBeanDefinitionNames();
		*//*for(String ss :str){
			System.out.println(ss);
		}*//*
		
		String[] strSon  = son.getBeanDefinitionNames();
		for(String ss:strSon){
			System.out.println(ss);
		}
		//model.addAttribute("content", str);
		return "content";
	}*/
}
