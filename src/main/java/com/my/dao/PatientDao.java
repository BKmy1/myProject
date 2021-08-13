package com.my.dao;

import com.my.Mapper.PatientMapper;
import com.my.bean.Patient;

import cn.com.sise.util.MyBatisUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class PatientDao {
    private static int colCount;        //colCount = rsmd.getColumnCount();     //返回此 ResultSet 对象中的列数。
    private static Connection conn;
    private static Vector<String> title;
    private static String[] doctorData = {"id", "name", "age", "gender", "workspace", "section", "telephone", "dateWork", "password"};
    private static String[] patientData = {"idCard", "name", "guardian", "age", "gender", "birthday", "nation", "section", "address", "telephone"};

    private static SqlSession sqlSession;
    /**
     * 医生开始
     */

    /**
     * 添加用户，static 公共方法
     *
     * @param patient
     * @return
     */
    public static boolean addDoctor(Patient patient) {
        try {
            String id = patient.getId();
            String name = patient.getName();
            int age = patient.getAge();
            String sex = patient.getGender();
            String workspace = patient.getWorkspace();
            String section = patient.getSection();
            String datework = patient.getDateWork();
            String telephone = patient.getTelephone();
            String password = patient.getPassword();
            if (id.equals("") || name.equals("") || (age + "").equals("") || telephone.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "内容不能为空！！！");
                return false;
            } else if (age < 18 || age > 120) {
                JOptionPane.showMessageDialog(null, "年龄不正确，数字不合法！！");
                return false;
            } else {
//                conn = Dao.getConnect();
//                PreparedStatement ps = conn.prepareStatement("insert into tbl_doctor (id,name,age,gender,workspace,section,telephone,dateWork,password) values(?,?,?,?,?,?,?,?,?)");
//                ps.setString(1, id);
//                ps.setString(2, name);
//                ps.setString(3, (age + ""));
//                ps.setString(4, sex);
//                ps.setString(5, workspace);
//                ps.setString(6, section);
//                ps.setString(7, telephone);
//                ps.setString(8, datework);
//                ps.setString(9, password);
//            	 int flag = ps.executeUpdate();
            	
            	SqlSession sqlSession=MyBatisUtil.getSession();
            	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
            	int flag=patientMapper.addDoctor(patient);
            	sqlSession.commit();
                if (flag > 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败！！");
                    return false;
                }
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null,e.getMessage());
//            JOptionPane.showMessageDialog(null, "医生编号已存在！！");
            return false;
//            e.printStackTrace();
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

    /**
     * 显示医生表格标题
     *
     * @param tableModel
     */
    public static void setDoctorTitle(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
        //创建一个 PreparedStatement 对象来将参数化的 SQL 语句发送到数据库。
        try {
//        	PreparedStatement ps = conn.prepareStatement("select * from tbl_doctor");
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
//            ResultSetMetaData rsmd = rs.getMetaData();    //接口用于获取结果集中的属性对象
//            colCount = rsmd.getColumnCount();     //返回此 ResultSet 对象中的列数。
//            title = new Vector<String>();
//            for (int i = 1; i <= colCount; i++) {
//                title.add(rsmd.getColumnLabel(i));      //获取用于打印输出和显示的指定列的建议标题。
//            }
//            tableModel.setDataVector(null, title);   //没有数据，存放标题
//        	SqlSession sqlSession=MyBatisUtil.getSession();
//        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
//        	List<Patient> list=patientMapper.setDoctorTitle();
//        	Vector<String> title=new Vector<Patient>(list);
        	
        	title=new Vector<String>();
        	String[] t= {"id","name","age","gender","workspace","section","telephone","dateWork","password"};
        	for(int i=0;i<t.length;i++) {
        		title.add(t[i]);
        	}
        	tableModel.setDataVector(null, title);   //没有数据，存放标题
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户管理，查看所有数据
     * 返回包含预编译 SQL 语句的新的默认 PreparedStatement 对象
     * 查找所有数据
     *
     * @param tableModel
     */
    public static void selectAllDoctorData(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
        try {
            //存放表格数据
            Vector<Vector<String>> value = new Vector<Vector<String>>();
//            int rowCount = 0;     //行号
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_doctor");
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
            SqlSession sqlSession=MyBatisUtil.getSession();
            PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
            List<Patient> list=patientMapper.selectAllDoctorData();
//            Vector<String> v=new Vector<String>();
//            while (rs.next()) {       //next()将光标移向下一行，返回true or false
////                rowCount++;
//                Vector<String> rowData = new Vector<String>();
//                for (int i = 1; i <= colCount; i++) {
//                    rowData.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//                value.add(rowData);     //将第一行中的所有列数据储存到集合中，以此类推
//            }
            for(Patient p:list) {
            	Vector<String> rowData = new Vector<String>();
            	rowData.add(p.getId());
            	rowData.add(p.getName());
            	rowData.add(p.getAge()+"");
            	rowData.add(p.getGender());
            	rowData.add(p.getWorkspace());
            	rowData.add(p.getSection());
            	rowData.add(p.getTelephone());
            	rowData.add(p.getDateWork());
            	rowData.add(p.getPassword());
            	value.add(rowData);
            }
            tableModel.setDataVector(value, title);
//            if (rowCount == 0) {
//                tableModel.setDataVector(null, title);   //没有数据，存放标题
//            } else {
//                tableModel.setDataVector(value, title);      //有数据，存放数据
//            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * 刷新添加的数据
     */

    public static void falshDoctorData(DefaultTableModel tableModel, String id) {
//        conn = Dao.getConnect();
        try {
            Vector<Vector<String>> value = new Vector<Vector<String>>();
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_doctor where id=?");
//            ps.setString(1, id);
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
//            while (rs.next()) {       //next()将光标移向下一行，返回true or false
//                Vector<String> rowData = new Vector<String>();
//                for (int i = 1; i <= colCount; i++) {
//                    rowData.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//                value.add(rowData);     //将第一行中的所有列数据储存到集合中，以此类推
//            }
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.falshDoctorData(id);
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
               	v.add(p.getId());
            	v.add(p.getName());
            	v.add(p.getAge()+"");
            	v.add(p.getGender());
            	v.add(p.getWorkspace());
            	v.add(p.getSection());
            	v.add(p.getTelephone());
            	v.add(p.getDateWork());
            	v.add(p.getPassword());
            	value.add(v);
        	}
            tableModel.setDataVector(value, title);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "异常"+e.getMessage());
//            e.printStackTrace();
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

    /**
     * 查找操作
     */

    public static void selectDoctorData(DefaultTableModel tableModel, String id) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_doctor where id=?");
//            ps.setString(1, id);
//            ResultSet rs = ps.executeQuery();
//            Vector<Vector<String>> value = new Vector<Vector<String>>();    //行
//            Vector<String> rowData = new Vector<String>();      //列数据
//            if (!rs.isBeforeFirst()) {    //isBeforeFirst()  判断rs返回的第一条记录是否为空
//                JOptionPane.showMessageDialog(null, "医生编号为空！！！");
//            } else {
//                while (rs.next()) {
//                    for (int i = 1; i <= colCount; i++) {
//                        rowData.add(rs.getString(i));
//                    }
//                }
//                value.add(rowData);
//                tableModel.setDataVector(value, title);
//            }
        	Vector<Vector<String>> value = new Vector<Vector<String>>();    //行
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectDoctorData(id);
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
               	v.add(p.getId());
            	v.add(p.getName());
            	v.add(p.getAge()+"");
            	v.add(p.getGender());
            	v.add(p.getWorkspace());
            	v.add(p.getSection());
            	v.add(p.getTelephone());
            	v.add(p.getDateWork());
            	v.add(p.getPassword());
            	value.add(v);
        	}
        	tableModel.setDataVector(value, title);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库异常！！！" + e.getMessage());
        } finally {
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 修改操作
     */
    public static void updateDoctorData(JTable table) {
//        conn = Dao.getConnect();
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "请选择需要修改的数据", "提示", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                int selectedRow = table.getSelectedRow();     //从0开始
                int selectedColumn = table.getSelectedColumn();
                String id = table.getValueAt(index, 0).toString();
                CellEditor ce = table.getCellEditor(selectedRow, selectedColumn);
                ce.stopCellEditing();
                Object cha = table.getValueAt(selectedRow, selectedColumn);    //返回Object类型
                table.setValueAt(cha, selectedRow, selectedColumn);
//                PreparedStatement ps = conn.prepareStatement("update tbl_doctor set  " + doctorData[selectedColumn] + "=?  where id=?");
//                ps.setString(1, cha.toString());
//                ps.setString(2, id);
//                int flag = ps.executeUpdate();
                SqlSession sqlSession=MyBatisUtil.getSession();
                PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                int flag=patientMapper.updateDoctorData(cha.toString(), id);
                sqlSession.commit();
                if (flag == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功！！");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！！");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "数据库异常" + e.getMessage());
            } finally {
                if (sqlSession != null) {
                    try {
                        sqlSession.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 删除操作
     * 删除选定的行
     *
     * @param table
     * @return
     */
    public static boolean deleteDoctorData(JTable table) {
//        conn = null;
        int[] index = table.getSelectedRows();  //返回所有选定行的索引。
        if (index.length == 0) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录", "提示", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            int choose = JOptionPane.showConfirmDialog(null, "你确定要删除数据吗？", "删除", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choose == JOptionPane.YES_OPTION) {
//                conn = Dao.getConnect();
                String id = table.getValueAt(index[0], 0).toString();      //获取选取行的第一列数据并返回字符串(从0开始)
                int flag = 0;
                try {
//                    PreparedStatement ps = conn.prepareStatement("delete from tbl_doctor where id=?");
//                    ps.setString(1, id);
//                    flag = ps.executeUpdate();
                	SqlSession sqlSession=MyBatisUtil.getSession();
                	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                	flag=patientMapper.deleteDoctorData(id);
                	sqlSession.commit();
                    if (flag == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "删除失败！！");
                    return false;
                } finally {
                    if (sqlSession != null) {
                        try {
                            sqlSession.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                return false;
            }
        }
    }
    /**
     * 医生结束
     */

    /**
     * 病人信息开始，添加病人
     */
    public static boolean addPatient(Patient patient) {
        try {
            String name = patient.getName();
            String guardian = patient.getGuardian();
            int age = patient.getAge();
            String sex = patient.getGender();
            String birthday = patient.getBirthday();
            String nation = patient.getNation();
            String section = patient.getSection();
            String address = patient.getAddress();
            String telephone = patient.getTelephone();
            String idCard = patient.getIdCard();
            if (guardian.equals("") || name.equals("") || (age + "").equals("") || telephone.equals("") || idCard.equals("") || address.equals("") || birthday.equals("")) {
                JOptionPane.showMessageDialog(null, "内容不能为空！！！");
                return false;
            } else if (age < 0 || age > 120) {
                JOptionPane.showMessageDialog(null, "年龄不正确，数字不合法！！");
                return false;
            } else {
//                conn = Dao.getConnect();
//                PreparedStatement ps = conn.prepareStatement("insert into tbl_patient (name,guardian,age,gender,birthday,nation,section,address,telephone,idCard) values(?,?,?,?,?,?,?,?,?,?)");
//                ps.setString(1, name);
//                ps.setString(2, guardian);
//                ps.setString(3, (age + ""));
//                ps.setString(4, sex);
//                ps.setString(5, birthday);
//                ps.setString(6, nation);
//                ps.setString(7, section);
//                ps.setString(8, address);
//                ps.setString(9, telephone);
//                ps.setString(10, idCard);
//                int flag = ps.executeUpdate();
            	
            	SqlSession sqlSession=MyBatisUtil.getSession();
            	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
            	int flag=patientMapper.addPatient(patient);
                if (flag > 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败！！");
                    return false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "医生编号已存在！！"+e.getMessage());
            return false;
//            e.printStackTrace();
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

    /**
     * 病人信息维护
     */

    /**
     * 病人查找操作
     */

    public static void selectPatientData(DefaultTableModel tableModel, String idCard) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_patient where idCard=?");
//            ps.setString(1, idCard);
//            ResultSet rs = ps.executeQuery();
//            Vector<Vector<String>> value = new Vector<Vector<String>>();    //行
//            Vector<String> rowData = new Vector<String>();      //列数据
//            if (!rs.isBeforeFirst()) {    //isBeforeFirst()  判断rs返回的第一条记录是否为空
//                JOptionPane.showMessageDialog(null, "医保卡号不存在！！！");
//            } else {
//                while (rs.next()) {
//                    for (int i = 1; i <= colCount; i++) {
//                        rowData.add(rs.getString(i));
//                    }
//                }
//                value.add(rowData);
//                tableModel.setDataVector(value, title);
//            }
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectPatientData(idCard);
        	Vector<Vector<String>> value=new Vector<Vector<String>>();
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
        		v.add(p.getIdCard());
        		v.add(p.getName());
        		v.add(p.getGuardian());
        		v.add(p.getAge()+"");
        		v.add(p.getGender());
        		v.add(p.getBirthday());
        		v.add(p.getNation());
        		v.add(p.getSection());
        		v.add(p.getAddress());
        		v.add(p.getTelephone());
        		value.add(v);
        	}
        	tableModel.setDataVector(value, title);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库异常！！！" + e.getMessage());
        } finally {
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查找操作，返回数据
     */
    public static void selectPatientInfo(String idCard, Patient patient) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_patient where idCard=?");
//            ps.setString(1, idCard);
//            ResultSet rs = ps.executeQuery();
//            Vector<String> data = new Vector<String>();
//            while (rs.next()) {
//                for (int i = 1; i <= colCount; i++) {
//                    data.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//            }
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectPatientInfo(idCard);
        	Vector<String> v=new Vector<String>();
        	for(Patient p:list) {
//        		v.add(p.getIdCard());
        		v.add(p.getName());
//        		v.add(p.getGuardian());
        		v.add(p.getAge()+"");
        		v.add(p.getGender());
//        		v.add(p.getBirthday());
//        		v.add(p.getNation());
//        		v.add(p.getSection());
//        		v.add(p.getAddress());
//        		v.add(p.getTelephone());
        	}
            String name = v.get(0);
            int age = Integer.parseInt(v.get(1));
            String gender = v.get(2);
            patient.setName(name);
            patient.setAge(age);
            patient.setGender(gender);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "医保卡号不存在！！");
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

    /**
     * 病人修改操作
     * 只能修改年龄
     * @param table
     */
    public static void updatePatientData(JTable table) {
//        conn = Dao.getConnect();
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "请选择需要修改的数据", "提示", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                int selectedRow = table.getSelectedRow();     //从0开始
                int selectedColumn = table.getSelectedColumn();
                String id = table.getValueAt(index, 0).toString();
                CellEditor ce = table.getCellEditor(selectedRow, selectedColumn);
                ce.stopCellEditing();
                Object cha = table.getValueAt(selectedRow, selectedColumn);    //返回Object类型
                table.setValueAt(cha, selectedRow, selectedColumn);
//                PreparedStatement ps = conn.prepareStatement("update tbl_patient set  " + patientData[selectedColumn] + "=?  where idCard=?");
//                ps.setString(1, cha.toString());
//                ps.setString(2, id);
//                int flag = ps.executeUpdate();
                SqlSession sqlSession=MyBatisUtil.getSession();
                PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                int flag=patientMapper.updatePatientData(cha.toString(), id);
                sqlSession.commit();
                if (flag == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功！！");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！！");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "数据库异常" + e.getMessage());
            } finally {
                if (sqlSession != null) {
                    try {
                        sqlSession.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 病人信息刷新操作
     */
    public static void flashPatientData(DefaultTableModel tableModel, String idCard) {
//        conn = Dao.getConnect();
        try {
            //存放表格数据
//            Vector<Vector<String>> value = new Vector<Vector<String>>();
//            PreparedStatement ps = null;
//            ps = conn.prepareStatement("select * from tbl_patient where idCard=?");
//            ps.setString(1, idCard);
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
//            while (rs.next()) {       //next()将光标移向下一行，返回true or false
//                Vector<String> rowData = new Vector<String>();
//                for (int i = 1; i <= colCount; i++) {
//                    rowData.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//                value.add(rowData);     //将第一行中的所有列数据储存到集合中，以此类推
//            }
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.flashPatientData(idCard);
        	Vector<Vector<String>> value = new Vector<Vector<String>>();
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
        		v.add(p.getIdCard());
        		v.add(p.getName());
        		v.add(p.getGuardian());
        		v.add(p.getAge()+"");
        		v.add(p.getGender());
        		v.add(p.getBirthday());
        		v.add(p.getNation());
        		v.add(p.getSection());
        		v.add(p.getAddress());
        		v.add(p.getTelephone());
        		value.add(v);
        	}
            tableModel.setDataVector(value, title);
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * 查看病人所有数据
     *
     * @param tableModel
     */
    public static void selectAllPatientData(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
        try {
            //存放表格数据
//            Vector<Vector<String>> value = new Vector<Vector<String>>();
//            PreparedStatement ps = null;
//            ps = conn.prepareStatement("select * from tbl_patient");
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
//            while (rs.next()) {       //next()将光标移向下一行，返回true or false
//                Vector<String> rowData = new Vector<String>();
//                for (int i = 1; i <= colCount; i++) {
//                    rowData.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//                value.add(rowData);     //将第一行中的所有列数据储存到集合中，以此类推
//            }
        	Vector<Vector<String>> value = new Vector<Vector<String>>();
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectAllPatientData();
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
        		v.add(p.getIdCard());
        		v.add(p.getName());
        		v.add(p.getGuardian());
        		v.add(p.getAge()+"");
        		v.add(p.getGender());
        		v.add(p.getBirthday());
        		v.add(p.getNation());
        		v.add(p.getSection());
        		v.add(p.getAddress());
        		v.add(p.getTelephone());
        		value.add(v);
        	}
            tableModel.setDataVector(value, title);
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * 病人删除操作
     */
    public static boolean deletePatientData(JTable table) {
//        conn = null;
        int[] index = table.getSelectedRows();  //返回所有选定行的索引。
        if (index.length == 0) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录", "提示", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            int choose = JOptionPane.showConfirmDialog(null, "你确定要删除数据吗？", "删除", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choose == JOptionPane.YES_OPTION) {
//                conn = Dao.getConnect();
                String idCard = table.getValueAt(index[0], 0).toString();      //获取选取行的第一列数据并返回字符串(从0开始)
                int flag = 0;
                try {
//                    PreparedStatement ps = conn.prepareStatement("delete from tbl_patient where idCard=?");
//                    ps.setString(1, idCard);
//                    flag = ps.executeUpdate();
                	SqlSession sqlSession=MyBatisUtil.getSession();
                	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                	flag=patientMapper.deletePatientData(idCard);
                	sqlSession.commit();
                    if (flag == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "删除失败！！");
                    return false;
                } finally {
                    if (sqlSession != null) {
                        try {
                        	sqlSession.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 设置病人信息标题
     */
    public static void setPatientTitle(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
//        PreparedStatement ps = null;   //创建一个 PreparedStatement 对象来将参数化的 SQL 语句发送到数据库。
        try {
//            ps = conn.prepareStatement("select * from tbl_patient");
//            ResultSet rs = ps.executeQuery();         //包含该查询生成的数据的 ResultSet 对象；不会返回 null，开始光标指向第一行
//            ResultSetMetaData rsmd = rs.getMetaData();    //接口用于获取结果集中的属性对象
//            colCount = rsmd.getColumnCount();     //返回此 ResultSet 对象中的列数。
//            title = new Vector<String>();
//            for (int i = 1; i <= colCount; i++) {
//                title.add(rsmd.getColumnLabel(i));      //获取用于打印输出和显示的指定列的建议标题。
//            }
        	String[] t= {"idCard","name","guardian","age","gender","birthday","nation","section","address","telephone"};
        	title=new Vector<String>();
        	for(int i=0;i<t.length;i++) {
        		title.add(t[i]);
        	}
            tableModel.setDataVector(null, title);   //没有数据，存放标题
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加药品
     */
//    public static void addMedical(){
//
//    }

    /**
     * 药品标题
     */
    public static void medicalTitle(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_medicine");
//            ResultSet rs = ps.executeQuery();
//            ResultSetMetaData rsmd = rs.getMetaData();    //接口用于获取结果集中的属性对象
//            colCount = rsmd.getColumnCount();     //返回此 ResultSet 对象中的列数。
        	String[] t= {"id","name","birthday","end","address","price","number"};
            title = new Vector<String>();
            for(int i=0;i<t.length;i++) {
            	title.add(t[i]);
            }
//            for (int i = 1; i <= colCount; i++) {
//                title.add(rsmd.getColumnLabel(i));
//            }
            tableModel.setDataVector(null, title);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库异常" + e.getMessage());
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

    /**
     * 显示所有药品
     */
    public static void selectAllMedical(DefaultTableModel tableModel) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_medicine");
//            ResultSet rs = ps.executeQuery();
//            Vector<Vector<String>> value = new Vector<Vector<String>>();
//            while (rs.next()) {       //next()将光标移向下一行，返回true or false
//                Vector<String> rowData = new Vector<String>();
//                for (int i = 1; i <= colCount; i++) {
//                    rowData.add(rs.getString(i));       //获取当前行中的指定列数据
//                }
//                value.add(rowData);     //将第一行中的所有列数据储存到集合中，以此类推
//            }
        	Vector<Vector<String>> value = new Vector<Vector<String>>();
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectAllMedical();
        	for(Patient p:list) {
        		Vector<String> v=new Vector<String>();
        		v.add(p.getId());
        		v.add(p.getName());
        		v.add(p.getBirthday());
        		v.add(p.getEnd());
        		v.add(p.getAddress());
        		v.add(p.getPrice()+"");
        		v.add(p.getNumber()+"");
        		value.add(v);
        	}
            tableModel.setDataVector(value, title);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查找药品
     */
    public static void selectMedical(DefaultTableModel tableModel, String id) {
//        conn = Dao.getConnect();
        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tbl_medicine where id=?");
//            ps.setString(1, id);
//            ResultSet rs = ps.executeQuery();
//            Vector<Vector<String>> value = new Vector<Vector<String>>();    //行
//            Vector<String> rowData = new Vector<String>();      //列数据
        	SqlSession sqlSession=MyBatisUtil.getSession();
        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
        	List<Patient> list=patientMapper.selectMedical(id);
          Vector<Vector<String>> value = new Vector<Vector<String>>();    //行
          for(Patient p:list) {
        	  Vector<String> v=new Vector<String>();
        	  v.add(p.getId());
        	  v.add(p.getName());
        	  v.add(p.getBirthday());
        	  v.add(p.getEnd());
        	  v.add(p.getAddress());
        	  v.add(p.getPrice()+"");
        	  v.add(p.getNumber()+"");
        	  value.add(v);
          }
//            if (!rs.isBeforeFirst()) {    //isBeforeFirst()  判断rs返回的第一条记录是否为空
//                JOptionPane.showMessageDialog(null, "药品不存在！！！");
//            } else {
//                while (rs.next()) {
//                    for (int i = 1; i <= colCount; i++) {
//                        rowData.add(rs.getString(i));
//                    }
//                }
//                value.add(rowData);
//                tableModel.setDataVector(value, title);
//            }
          tableModel.setDataVector(value, title);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库异常" + e.getMessage());
        } finally {
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除药品
     */
    public static boolean deleteMedical(JTable table) {
//        conn = Dao.getConnect();
        int[] index = table.getSelectedRows();  //返回所有选定行的索引。
        if (index.length == 0) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录", "提示", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            int choose = JOptionPane.showConfirmDialog(null, "你确定要删除数据吗？", "删除", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choose == JOptionPane.YES_OPTION) {
//                conn = Dao.getConnect();
                String id = table.getValueAt(index[0], 0).toString();      //获取选取行的第一列数据并返回字符串(从0开始)
                int flag= 0;
                try {
//                    PreparedStatement ps = conn.prepareStatement("delete from tbl_medicine where id=?");
//                    ps.setString(1, id);
//                    flag = ps.executeUpdate();
                	SqlSession sqlSession=MyBatisUtil.getSession();
                	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                	flag=patientMapper.deleteMedical(id);
                	sqlSession.commit();
                    if (flag == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "删除失败！！");
                    return false;
                } finally {
                    if (sqlSession != null) {
                        try {
                            sqlSession.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                return false;
            }
        }
    }
}
