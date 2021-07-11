/**
 * Copyright_c 2018 qmhd.con.cn Inc. All Rights Reserved.
 * @Title User.java
 * @Package: me.maupassant.springmvc._draft.entity
 * @Email:  xhsgg12302@outlook.com   
 * @date: 2019年3月11日 上午12:03:32 
 * @version 1.0.0
 * @since JDK1.8
 */
package site.wtfu.framework.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * @className User
 * @description
 *
 */
@XmlRootElement
public class User {
	private String name;
	private Integer age;
	private String account;
	private String password;
	private LocalDateTime time;

	public User() {
	}

	public User(String name, String account, String password) {
		this.name = name;
		this.account = account;
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", time=" + time +
				'}';
	}
}
