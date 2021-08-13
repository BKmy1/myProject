package com.my.frame;


import com.my.bean.Patient;
import com.my.dao.PatientDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * 创建一个桌面面板，用来存放内部窗体
 */

public class UserManager extends JFrame {
    private JDesktopPane desktopPane;
    private InteralFrame userManagerFrame;
    private JTabbedPane mainPane;
    private JPanel addUserPane,userMaintain;
    private JLabel nameLabel,idLabel,ageLabel,genderLabel,workspaceLabel,sectionLabel,telephoneLabel,dateWork,passwordLabel;
    private JTextField nameText,idText,ageText,telephoneText,dateWorkText;
    private JPasswordField passwordText;
    private JButton btn_submit,btn_reset,update,flash,delete,select,selectAll;
    private String sex,workspace,section,tid;
    private JLabel selectId;
    private JTextField selectIdText;
    private DefaultTableModel tableModel;
    private JTable table;
    private JRadioButton male,female;
    private JComboBox jcb,jcb1;

    public UserManager(){
        super();
        this.setTitle("用户管理");
        this.setBounds(100,100,700,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
    }

    /**
     * 桌面面板
     */
    public void init(){
        desktopPane=new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);    //设置窗体内部拖动模式
        desktopPane.add(getUserManagerFrame());
        getContentPane().add(desktopPane,BorderLayout.CENTER);
        setContentPane(desktopPane);
    }

    /**
     * 内部面板，用户管理
     * @return
     */
    public InteralFrame getUserManagerFrame(){
        if(userManagerFrame==null){
            userManagerFrame=new InteralFrame("用户管理");
            userManagerFrame.setBounds(20,20,500,600);
            userManagerFrame.add(getMainPane());

            userManagerFrame.setVisible(true);  //不能忘记！！！！
        }
        return userManagerFrame;
    }

    /**
     * 选项卡面板：添加用户面板，用户管理面板
     */

    public JTabbedPane getMainPane(){
        if(mainPane==null){
            mainPane=new JTabbedPane();
            mainPane.add("添加用户",getAddUserPanel());
            mainPane.add("用户维护",getUserMaintain());
        }
        return mainPane;
    }
    /**
     * 面板一：添加用户
     * @return
     */

    public JPanel getAddUserPanel(){
        if(addUserPane==null){
            addUserPane=new JPanel();
            addUserPane.setLayout( null);

            idLabel=new JLabel("医生编号：");
            idLabel.setBounds(40,20,100,40);
            idText=new JTextField(20);
            idText.setBounds(110,30,80,20);

            nameLabel=new JLabel("医生姓名：");
            nameLabel.setBounds(240,20,100,40);
            nameText=new JTextField(20);
            nameText.setBounds(310,30,80,20);

            ageLabel=new JLabel("年龄：");
            ageLabel.setBounds(40,80,100,40);
            ageText=new JTextField(20);
            ageText.setBounds(110,90,80,20);

            JPanel p=new JPanel();
            genderLabel=new JLabel("性别：");
            genderLabel.setBounds(240,80,100,40);
            male=new JRadioButton("男");
            male.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(male.isSelected()){
                        sex=male.getText();
                    }
                }
            });
            female=new JRadioButton("女");
            female.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(female.isSelected()){
                        sex=female.getText();
                    }
                }
            });

            ButtonGroup bg=new ButtonGroup();
            bg.add(male);
            bg.add(female);
            p.add(male);
            p.add(female);
            p.setBounds(290,85,100,40);

            workspaceLabel=new JLabel("职称：");
            workspaceLabel.setBounds(40,140,100,40);

            JPanel p1=new JPanel();
            jcb=new JComboBox(new String[ ]{"医士","医师","住院医师","主治医师","副主任医师","主任医师"});
            jcb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    workspace= (String) jcb.getSelectedItem();
                }
            });
            p1.add(jcb);
            p1.setBounds(100,145,100,40);

            sectionLabel=new JLabel("科室：");
            sectionLabel.setBounds(240,140,100,40);

            JPanel p2=new JPanel();
            jcb1=new JComboBox();
            jcb1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    section=(String)jcb1.getSelectedItem(); //返回Object对象
                }
            });
            jcb1.addItem("内科");
            jcb1.addItem("外科");
            jcb1.addItem("妇产科");
            jcb1.addItem("儿科");
            jcb1.addItem("眼科");
            jcb1.addItem("耳鼻喉科");
            jcb1.addItem("口腔科");
            jcb1.addItem("皮肤科");
            jcb1.addItem("精神科");
            jcb1.addItem("传染科");
            p2.add(jcb1);
            p2.setBounds(290,145,100,40);

            dateWork=new JLabel("入职日期：");
            dateWork.setBounds(40,200,100,40);

//            String[] year={"2017","2018","2019","2020","2021"};
//            String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
//            List<String> day=new ArrayList<String>();
//            for(int i=1;i<=31;i++){
//                day.add(i+"");
//                day.toString().charAt(i);
//            }
//
//            private void getDateWorkText(ItemEvent item,JComboBox<String> cbox){
//
//            }

            Calendar c=Calendar.getInstance();  //使用默认时区和语言环境获得一个日历。
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH)+1;
            int date=c.get(Calendar.DATE);
            dateWorkText=new JTextField(20);
            dateWorkText.setBounds(110,210,110,20);
            dateWorkText.setText(year+"年"+month+"月"+date+"日");
            dateWorkText.setEnabled(false);

            telephoneLabel=new JLabel("电话：");
            telephoneLabel.setBounds(240,200,100,40);
            telephoneText=new JTextField(20);
            telephoneText.setBounds(290,210,90,20);

            passwordLabel=new JLabel("登录密码：");
            passwordLabel.setBounds(40,260,100,40);
            passwordText=new JPasswordField(20);
            passwordText.setBounds(110,270,80,20);

            addUserPane.add(idLabel);
            addUserPane.add(idText);
            addUserPane.add(nameLabel);
            addUserPane.add(nameText);
            addUserPane.add(ageLabel);
            addUserPane.add(ageText);
            addUserPane.add(genderLabel);
            addUserPane.add(p);
            addUserPane.add(workspaceLabel);
            addUserPane.add(p1);
            addUserPane.add(sectionLabel);
            addUserPane.add(p2);
            addUserPane.add(dateWork);
            addUserPane.add(dateWorkText);
            addUserPane.add(telephoneLabel);
            addUserPane.add(telephoneText);
            addUserPane.add(passwordLabel);
            addUserPane.add(passwordText);
            addUserPane.add(getButtonSubmit());
            addUserPane.add(getButtonReset());
        }
        return addUserPane;
    }

    /**
     * 面板二：用户管理面板
     * 运用表格模型创建表格
     * 1.new DefaultTableModel();
     * 2.new Table(defaultTableModel);
     * 3.new JScrollPane(table)
     * @return
     */

    public JPanel getUserMaintain(){
        if(userMaintain==null){
            userMaintain=new JPanel();
            JPanel p1=new JPanel();
            selectId=new JLabel("医生编号：");
            selectIdText=new JTextField(20);
            select=new JButton("查询");
            select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id=selectIdText.getText();
                    PatientDao.selectDoctorData(tableModel,id);
                }
            });
            selectAll=new JButton("显示所有");
            selectAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.selectAllDoctorData(tableModel);
                }
            });
            p1.add(selectId);
            p1.add(selectIdText);
            p1.add(select);
            p1.add(selectAll);
            userMaintain.add(p1);

            tableModel=new DefaultTableModel();       //创建表格模型      //动态改变JTable的值
            table=new JTable(tableModel);        //创建表格
            table.setRowHeight(30);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    //自动调整模式
            JScrollPane scrollPane=new JScrollPane(table);

            userMaintain.add(scrollPane,BorderLayout.CENTER);
            //设置好表格的title
            PatientDao.setDoctorTitle(tableModel);
            JPanel p2=new JPanel();
            update=new JButton("修改用户");
            update.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.updateDoctorData(table);
                }
            });
            flash=new JButton("刷新操作");
            flash.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.falshDoctorData(tableModel,tid);
                }
            });
            delete=new JButton("删除操作");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(PatientDao.deleteDoctorData(table)){
                        JOptionPane.showMessageDialog(null,"删除成功！！！");
                    }
                }
            });
            p2.add(update);
            p2.add(flash);
            p2.add(delete);
            userMaintain.add(p2);
        }
        return userMaintain;
    }

    /**
     *提交
     * @return
     */
    public JButton getButtonSubmit() {
        if(btn_submit==null) {
            btn_submit=new JButton("提交");
            btn_submit.setBounds(140,360,60,40);
            btn_submit.setForeground(Color.RED);
            btn_submit.setContentAreaFilled(false);     //设置按钮透明
            btn_submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id=idText.getText();
                    tid=id;
                    String name=nameText.getText();
                    int age=Integer.parseInt(ageText.getText());
                    //String sex
                    String telephone=telephoneText.getText();
                    String password=new String(passwordText.getPassword());
                   //String workspace
                    //String section
                    Patient patient=new Patient();
                    patient.setId(id);
                    patient.setName(name);
                    patient.setAge(age);
                    patient.setWorkspace(workspace);
                    patient.setGender(sex);
                    patient.setSection(section);
                    patient.setDateWork(dateWorkText.getText());
                    patient.setTelephone(telephone);
                    patient.setPassword(password);
                    if(PatientDao.addDoctor(patient)){
                        JOptionPane.showMessageDialog(null,"添加成功！！");
//                        dispose();
                        clearData();
                    }
                }
            });
        }
        return btn_submit;
    }
    public JButton getButtonReset() {
        if(btn_reset==null) {
            btn_reset=new JButton("重置");
            btn_reset.setBounds(new Rectangle(260,360,60,40));
            btn_reset.setForeground(Color.BLUE);
            btn_reset.setContentAreaFilled(false);
            btn_reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clearData();
                }
            });
        }
        return btn_reset;
    }

    /**
     * 清除文本框中数据
     */
    public void clearData(){
        nameText.setText("");
        idText.setText("");
        ageText.setText("");
        telephoneText.setText("");
        passwordText.setText("");
    }

    /**
     * 设置内部窗体
     * 自定义内部类
     */

    private class InteralFrame extends JInternalFrame{
        public InteralFrame(String title){
            super();
            this.setTitle(title);
            this.setResizable(true);     //设置允许自动调整大小
//            this.setClosable(true);     //设置关闭按钮
            this.setMaximizable(true);      //设置最大化按钮
            this.setIconifiable(true);      //设置提供图标化按钮，最小化在桌面
        }
    }

    public static void main(String args[]){
        UserManager um=new UserManager();
        um.setVisible(true);
    }
}

