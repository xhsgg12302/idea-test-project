/**
 * Copyright_c 2018 qmhd.con.cn Inc. All Rights Reserved.
 * @Title User.java
 * @Package: site.wtfu.framework._draft.entity
 * @Email:  xhsgg12302@outlook.com   
 * @date: 2019年3月11日 上午12:03:32 
 * @version 1.0.0
 * @since JDK1.8
 */
package site.wtfu.framework.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className User
 * @description
 *
 */
@Data
public class User {

	private String name;

	private Integer age;

	private String account;

	private String password;

	private LocalDateTime time;

	private LocalDateTime now;

	public User() {}

	public User(String name, String account, String password) {
		this.name = name;
		this.account = account;
		this.password = password;
	}
}
