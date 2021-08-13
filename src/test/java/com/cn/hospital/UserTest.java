package com.cn.hospital;

import org.junit.Test;

import com.my.bean.User;
import com.my.dao.UserDao;

public class UserTest {
	private User user;
	@Test
	public void testInsertExpress() {
		user=new User();
		user.setName("admin");
		user.setPassword("admin");
		UserDao userd=new UserDao();
		userd.userLogin(user);
	}
}
