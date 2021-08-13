package com.cn.hospital;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.my.bean.Patient;
import com.my.dao.PatientDao;

import cn.com.sise.util.MyBatisUtil;

public class PatientTest {
	private Patient p;
	@Test
	public void testInsertExpress() {
		p=new Patient();
		p.setId("123345");
		p.setAge(23);
		p.setGender("男");
		p.setName("玩家");
		p.setWorkspace("医师");
		p.setSection("口腔科");
		p.setTelephone("123123123123");
		p.setDateWork("2001年5月4日");
		p.setPassword("123123");
		PatientDao pd=new PatientDao();
		pd.addDoctor(p);
	}
}
