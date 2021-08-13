package com.my.Mapper;

import java.sql.ResultSet;
import java.util.List;

import com.my.bean.User;

public interface UserMapper {
	public List<User> userLogin(String name);
}
