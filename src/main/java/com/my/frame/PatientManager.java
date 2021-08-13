package com.my.frame;

import com.my.bean.Patient;
import com.my.dao.PatientDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientManager extends JFrame {
    private JDesktopPane desktopPane;
    private  InteralFrame patientManagerFrame;
    private JTabbedPane mainPane;
    private JPanel addPatientPane,patientInfoPane,patientMaintainPane;
    private JLabel selectIdCard,nameLabel,guardianLabel,ageLabel,genderLabel,birthdayLabel,nationLabel,workLabel,idCardLabel,
            telephoneLabel,addressLabel,timeLabel;
    private JTextField nameText,guardianText,ageText,birthdayText,idCardText,telephoneText,selectIdCardText,genderText;
    private JTextArea addressText;
    private JButton btn_submit,btn_reset,select,update,flash,delete,selectAll;
    private String sex,nation,section,idCard,midCard;
    private JTable table;
    private DefaultTableModel tableModel=null;
    private JMenuBar menuBar;
    private JRadioButton female,male;
    private JComboBox jcb,jcb1;
    private JTextField idCardText1,nameText1,ageText1,genderText1;

    public PatientManager(){
        super("病人管理");
        this.setBounds(100,100,700,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    /**
     * 桌面面板
     *
     */
    public void init(){
        desktopPane=new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);    //设置窗体内部拖动模式
        desktopPane.add(getPatientManagerFrame());
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        setContentPane(desktopPane);
    }

    /**
     * 内部面板
     * @return
     */
    public InteralFrame getPatientManagerFrame(){
        if(patientManagerFrame==null){
            patientManagerFrame=new InteralFrame("病人信息");
            patientManagerFrame.setBounds(20,20,500,600);
            patientManagerFrame.add(getMainPane());
            patientManagerFrame.setJMenuBar(menuBar);   //添加菜单条
            patientManagerFrame.setVisible(true);
        }
        return patientManagerFrame;
    }

    /**
     * 选项卡面板
     * @return
     */
    public JTabbedPane getMainPane(){
        if(mainPane==null){
            mainPane=new JTabbedPane();
            mainPane.add("添加病人",getAddPatientPanel());
            mainPane.add("病人信息",getPatientInfoPane());
            mainPane.add("信息维护",getPatientMaintainPane());
        }
        return mainPane;
    }

    /**
     * 添加病人
     * @return
     */
    public JPanel getAddPatientPanel(){
        if(addPatientPane==null){
            addPatientPane=new JPanel();
            addPatientPane.setLayout(null);

            nameLabel=new JLabel("姓名：");
            nameLabel.setBounds(40,20,100,40);
            nameText=new JTextField(20);
            nameText.setBounds(110,30,80,20);

            guardianLabel=new JLabel("监护人姓名：");
            guardianLabel.setBounds(240,20,100,40);
            guardianText=new JTextField(20);
            guardianText.setBounds(320,30,80,20);

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

            birthdayLabel=new JLabel("出生日期：");
            birthdayLabel.setBounds(40,140,100,40);
            birthdayText=new JTextField(20);
            birthdayText.setBounds(110,150,80,20);

            nationLabel=new JLabel("民族：");
            nationLabel.setBounds(40,200,100,40);
            JPanel p1=new JPanel();
            jcb=new JComboBox(new String[ ]{"汉族","回族","满族","维吾尔族","苗族","彝族","土家族","藏族","蒙古族","侗族","布依族",
            "瑶族","白族","朝鲜族","哈尼族","黎族","哈萨克族","傣族"});
            jcb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    nation= (String) jcb.getSelectedItem();
                }
            });
            p1.add(jcb);
            p1.setBounds(100,205,100,40);

            workLabel=new JLabel("职业：");
            workLabel.setBounds(240,140,100,40);

            JPanel p2=new JPanel();
            jcb1=new JComboBox();
            jcb1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    jcb1.setSelectedIndex(0);       //JComboBox 默认选择,设置显示第一个元素
                    section=(String)jcb1.getSelectedItem(); //返回Object对象
                }
            });
            jcb1.addItem("学生");
            jcb1.addItem("老师");
            jcb1.addItem("军人");
            jcb1.addItem("程序员");
            jcb1.addItem("警察");
            jcb1.addItem("医生");
            jcb1.addItem("企业家");
            jcb1.addItem("工人");
            jcb1.addItem("厨师");
            jcb1.addItem("司机");
            jcb1.addItem("农民");
            jcb1.addItem("艺术家");
            jcb1.addItem("其它");
            p2.add(jcb1);
            p2.setBounds(290,145,100,40);

            addressLabel=new JLabel("居住地址：");
            addressLabel.setBounds(240,200,100,40);
            addressText=new JTextArea();
            addressText.setLineWrap(true);
            addressText.setBorder(BorderFactory.createLineBorder(Color.gray));
            addressText.setBounds(310,200,100,50);

            telephoneLabel=new JLabel("电话：");
            telephoneLabel.setBounds(240,260,100,40);
            telephoneText=new JTextField(20);
            telephoneText.setBounds(290,270,90,20);

            idCardLabel=new JLabel("医保卡号：");
            idCardLabel.setBounds(40,260,100,40);
            idCardText=new JTextField(20);
            idCardText.setBounds(110,270,80,20);

            addPatientPane.add(nameLabel);
            addPatientPane.add(nameText);
            addPatientPane.add(guardianLabel);
            addPatientPane.add(guardianText);
            addPatientPane.add(ageLabel);
            addPatientPane.add(ageText);
            addPatientPane.add(genderLabel);
            addPatientPane.add(p);
            addPatientPane.add(birthdayLabel);
            addPatientPane.add(birthdayText);
            addPatientPane.add(nationLabel);
            addPatientPane.add(p1);
            addPatientPane.add(workLabel);
            addPatientPane.add(p2);
            addPatientPane.add(addressLabel);
            addPatientPane.add(addressText);
            addPatientPane.add(telephoneLabel);
            addPatientPane.add(telephoneText);
            addPatientPane.add(idCardLabel);
            addPatientPane.add(idCardText);
            addPatientPane.add(getButtonSubmit());
            addPatientPane.add(getButtonReset());
        }
        return addPatientPane;
    }

    public JButton getButtonSubmit() {
        if(btn_submit==null) {
            btn_submit=new JButton("提交");
            btn_submit.setBounds(140,360,60,40);
            btn_submit.setForeground(Color.RED);
            btn_submit.setContentAreaFilled(false);     //设置按钮透明
            btn_submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name=nameText.getText();
                    String guardian=guardianText.getText();
                    int age=Integer.parseInt(ageText.getText());
                    //String sex
                    String birthday=birthdayText.getText();
                    //String nation
                    //String section
                    String address=addressText.getText();
                    String telephone=telephoneText.getText();
                    idCard=idCardText.getText();
                    midCard=idCard;

                    Patient patient=new Patient();
                    patient.setName(name);
                    patient.setGuardian(guardian);
                    patient.setAge(age);
                    patient.setGender(sex);
                    patient.setBirthday(birthday);
                    patient.setNation(nation);
                    patient.setSection(section);
                    patient.setAddress(address);
                    patient.setTelephone(telephone);
                    patient.setIdCard(midCard);
                    if(PatientDao.addPatient(patient)){
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
     * 清除数据
     */
    public void clearData(){
        nameText.setText("");
        guardianText.setText("");
        ageText.setText("");
        birthdayText.setText("");
        addressText.setText("");
        telephoneText.setText("");
        idCardText.setText("");
    }


    /**
     * 病人信息，打印功能
     * @return
     */
    public JPanel getPatientInfoPane(){
        if(patientInfoPane==null){
            patientInfoPane=new JPanel();
            patientInfoPane.setLayout(null);
            menuBar=new JMenuBar();
            JMenu editMenu=new JMenu("编辑");
            JMenuItem printMenuItem=new JMenuItem("打印");
            printMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //创建并返回初始化时与默认打印机关联的 PrinterJob。
                        PrinterJob job = PrinterJob.getPrinterJob();//应用程序调用此类中的方法设置作业、（可选地）调用与用户的打印对话框，然后打印作业的页面。
                        if (!job.printDialog())//向用户呈现一个对话框，用来更改打印作业的属性。(打印框)
                            return;
                        job.setPrintable(new Printable() {//  调用 painter 以呈现页面。
                            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
                                //将指定索引处的页面用指定格式打印到指定的 Graphics 上下文。
                                //graphics  绘制打印页面图形的上下文
                                //pageformat 将绘制打印页面的格式属性，如大小和方向
                                //pageindex 当前打印页的索引
                                if (pageIndex > 0) {//pageIndex 指定请求页面从 0 开始的索引。
                                    return Printable.NO_SUCH_PAGE;
                                }
                                int x = (int) pageFormat.getImageableX();
                                int y = (int) pageFormat.getImageableY();
                                Graphics2D g2 = (Graphics2D) graphics;
                                g2.drawString("第一页", x, y);
                                return Printable.PAGE_EXISTS;
                            }
                        });
                        job.setJobName("打印快递单");//设置要打印的文档名称。
                        job.print();//实现打印功能（public abstract void print()）
                    }catch(Exception ee) {
                        ee.printStackTrace();
                        JOptionPane.showMessageDialog(null, ee.getMessage());
                    }
                }
            });
            editMenu.add(printMenuItem);
            menuBar.add(editMenu);

            JLabel idCardLabel=new JLabel("医保卡号：");
            idCardLabel.setBounds(100,15,100,40);

          
            idCardText1=new JTextField(20);
            idCardText1.setBounds(160,25,160,25);

            select=new JButton("查询");
            select.setBounds(350,25,65,27);
            select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String idCard=idCardText1.getText();
                    Patient patient=new Patient();
                    PatientDao.selectPatientInfo(idCard,patient);
                    nameText1.setText(patient.getName());
                    ageText1.setText(patient.getAge()+"");
                    genderText1.setText(patient.getGender());
                }
            });

            JLabel time=new JLabel("日期：");
            time.setBounds(100,80,100,40);

            timeLabel=new JLabel();
            timeLabel.setFont(new Font("微软雅黑",1,15));
            timeLabel.setBounds(160,80,180,40);
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
                        String time=dateFormat.format(new Date());
                        timeLabel.setText(time);
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException ee){
                            ee.printStackTrace();
                        }
                    }
                }
            }.start();

            JLabel nameLabel=new JLabel("姓名：");
            nameLabel.setBounds(100,140,100,40);

            nameText1=new JTextField(20);
            nameText1.setBounds(140,150,80,20);

            JLabel ageLabel=new JLabel("年龄：");
            ageLabel.setBounds(260,140,100,40);

            ageText1=new JTextField(20);
            ageText1.setBounds(300,150,80,20);

            JLabel genderLabel=new JLabel("性别：");
            genderLabel.setBounds(100,200,100,40);
            
            genderText1=new JTextField(20);
            genderText1.setBounds(140,210,80,20);

            JLabel illnessLabel=new JLabel("诊断：");
            illnessLabel.setBounds(260,200,100,40);
            JTextField illnessText=new JTextField(20);
            illnessText.setBounds(300,210,80,20);

            JLabel medicineLabel=new JLabel("药品名称：");
            medicineLabel.setBounds(100,260,100,40);
            JTextField medicineText=new JTextField(20);
            medicineText.setBounds(160,270,80,20);

            JLabel specificationLabel=new JLabel("规格：");
            specificationLabel.setBounds(260,260,100,40);
            JTextField specificationText=new JTextField(20);
            specificationText.setBounds(300,270,80,20);

            JLabel numberLabel=new JLabel("数量：");
            numberLabel.setBounds(100,320,100,40);
            JTextField numberText=new JTextField(20);
            numberText.setBounds(140,330,80,20);

            JLabel makeLabel=new JLabel("用法：");
            makeLabel.setBounds(260,320,100,40);
            JTextField makeText=new JTextField(20);
            makeText.setBounds(300,330,80,20);

            JLabel makeNumberLabel=new JLabel("用量：");
            makeNumberLabel.setBounds(100,380,100,40);
            JTextField makeNumberText=new JTextField(20);
            makeNumberText.setBounds(140,390,80,20);

            JTextArea matterArea=new JTextArea();
            matterArea.setBorder(BorderFactory.createLineBorder(Color.red,3));
            matterArea.setText("  提示：\n"+"      1、请遵照医生指引用药。将药品放妥，以免小孩误服。\n"+"      2、为确保患者用药安全，药品一经发出，不得退换。");
            matterArea.setBounds(100,440,350,60);

            patientInfoPane.add(idCardLabel);
            patientInfoPane.add(idCardText1);
            patientInfoPane.add(select);
            patientInfoPane.add(time);
            patientInfoPane.add(timeLabel);
            patientInfoPane.add(nameLabel);
            patientInfoPane.add(nameText1);
            patientInfoPane.add(ageLabel);
            patientInfoPane.add(ageText1);
            patientInfoPane.add(genderLabel);
            patientInfoPane.add(genderText1);
            patientInfoPane.add(illnessLabel);
            patientInfoPane.add(illnessText);
            patientInfoPane.add(medicineLabel);
            patientInfoPane.add(medicineText);
            patientInfoPane.add(specificationLabel);
            patientInfoPane.add(specificationText);
            patientInfoPane.add(numberLabel);
            patientInfoPane.add(numberText);
            patientInfoPane.add(makeLabel);
            patientInfoPane.add(makeText);
            patientInfoPane.add(makeNumberLabel);
            patientInfoPane.add(makeNumberText);
            patientInfoPane.add(matterArea);
        }
        return patientInfoPane;
    }

    /**
     * 病人信息维护
     * @return
     */
    public JPanel getPatientMaintainPane(){
        if(patientMaintainPane==null){
            patientMaintainPane=new JPanel();

            tableModel=new DefaultTableModel();       //创建表格模型      //动态改变JTable的值
            table=new JTable(tableModel);        //创建表格
            table.setRowHeight(30);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    //自动调整模式
            JScrollPane scrollPane=new JScrollPane(table);

            JPanel p1=new JPanel();
            selectIdCard=new JLabel("医保卡号：");
            selectIdCardText=new JTextField(20);
            select=new JButton("查询");
            select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String idCard=selectIdCardText.getText();
                    PatientDao.selectPatientData(tableModel,idCard);
                }
            });
            selectAll=new JButton("显示所有");
            selectAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.selectAllPatientData(tableModel);
                }
            });
            p1.add(selectIdCard);
            p1.add(selectIdCardText);
            p1.add(select);
            p1.add(selectAll);
            patientMaintainPane.add(p1);

            patientMaintainPane.add(scrollPane,BorderLayout.CENTER);
            //设置好表格的title
            PatientDao.setPatientTitle(tableModel);
            JPanel p2=new JPanel();
            update=new JButton("修改信息");
            update.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.updatePatientData(table);
                }
            });
            flash=new JButton("刷新操作");
            flash.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.flashPatientData(tableModel,midCard);
                }
            });
            delete=new JButton("删除操作");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(PatientDao.deletePatientData(table)){
                        JOptionPane.showMessageDialog(null,"删除成功！！！");
                    }
                }
            });
            p2.add(update);
            p2.add(flash);
            p2.add(delete);
            patientMaintainPane.add(p2);
        }
        return patientMaintainPane;
    }

    class InteralFrame extends JInternalFrame{
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
        PatientManager pm=new PatientManager();
        pm.setVisible(true);
    }
}
