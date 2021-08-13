package com.my.dao;

import com.my.Mapper.UserMapper;
import com.my.bean.User;

import cn.com.sise.util.MyBatisUtil;

import javax.swing.*;

import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
	private static SqlSession sqlSession;
    /*
    static   公共类，可以通过类名直接调用
     */
    public static boolean userLogin(User user) {
//        Connection conn = null;
        try {
            String name = user.getName();
            String password = user.getPassword();
            if (name.equals("") && password.equals("")) {
                JOptionPane.showMessageDialog(null, "用户名和密码不能为空！");
                return false;
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空！");
                return false;
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空！");
                return false;
            } else {
//                conn = Dao.getConnect();
//                PreparedStatement ps = conn.prepareStatement("select password from tbl_user where name=?");
//                ps.setString(1, name);
//                ResultSet rs = ps.executeQuery();
            	SqlSession sqlSession=MyBatisUtil.getSession();
            	UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            	List<User> rs=userMapper.userLogin(name);
            	if(!rs.isEmpty()) {
            		User use=rs.get(0);
//                if (rs.next() && rs.getRow() > 0) {
//                    String pwd = rs.getString(1);
                    if (use.getPassword().equals(password)) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "密码错误！");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "用户名不存在！");
                    return false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
//            e.printStackTrace();
            return false;
        } finally {
            if (sqlSession != null) {
                try {
                	sqlSession.close();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
}
