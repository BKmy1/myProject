package com.my.frame;

import com.my.tool.Background;
import com.my.tool.Internationalization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainFrame extends JFrame implements ActionListener {
    private JLabel timeLabel,nameLabel;
    private JMenuBar jMenuBar;
    private JMenu jMenuBaseInfo, jMenuPatientInfo, jMenuSystemManager, jMenuUserManager, jMenuHelp;
    private JMenuItem jMenuItemShowMedicalResource, jMenuItemShowDoctor,
            jMenuItemPatientAdd, jMenuItemPatientMaintain, jMenuItemPatient,
            jMenuItemUserAdd, jMenuItemUserMaintain,
            jMenuItemVersion, jMenuItemFeedback, jMenuItemExit, jMenuItemRestartLogin;
    private JPanel leftjPanel;
    private JButton jb,item0,item1,item2,item3,item4,item5;
    private JToolBar jToolBar;
    private String imn = "../hospital/src/main/resource/images/";
    private String toolImageName[] = {imn + "医生.png", imn + "资源.png", imn + "添加病人.png", imn+"病人信息.png",imn+"信息维护.png",imn+"添加用户.png",imn+"用户维护.png",imn + "退出系统.png", imn + "重新登录.png",imn+"当前版本.png",imn+"信息反馈.png"};  //工具栏图片
    private String tipImageName[]={"在职医生","医疗物资","添加病人","病人信息","信息维护","添加用户","用户维护","退出系统","重新登录","当前版本","信息反馈"};   //提示信息
    private String commandString[]={"doctor","medicalResource","addPatient","patientInfo","infoMaintain","addUser","userMaintain","exit","reloadLogin","version","feedback"};     //触发事件的别名
    private String name;
    private static ResourceBundle resources;
    
    
    /**
     * 国际化的初始化
     */

    static {
        try {
         Locale loc=Internationalization.getInternationalization();
         String prourl=Internationalization.getUrl();
            resources = ResourceBundle.getBundle(prourl, loc);//第二个参数用Locale.JAPAN、Locale.US等替代可显示不同国家文字
        } catch (Exception e) {
         System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    public MainFrame(String name) {
        super("医院管理系统");
        this.name=name;
        this.setBounds(350, 50, 900, 700);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        init();
        this.SystemTrayInitial();   //初始化系统栏
        this.setVisible(true);
    }

    public void init() {
        Background bg = new Background("背景1.jpg",0,10);
        bg.setLayout(null);
        bg.setBackground(Color.darkGray);
        getContentPane().add(bg);
        this.setJMenuBar(getjMenuBar());
        getContentPane().add(getjToolBar(),BorderLayout.NORTH);
        this.add(mainLeftCommand(),BorderLayout.WEST);
        bg.add(getTimeLabel());
        bg.add(getNameLabel());
    }

    /**
     * 系统托盘
     * 1.获取系统默认托盘    SystemTray st=SystemTray.getSystemTray();
     * 2.创建系统栏图标  Image im=Toolkit.getDefaultToolkit().getImage(getClass().getResource());
     * 3.创建系统栏图标对象  TrayIcon t=new TrayIcon(...);//可以添加监听事件
     * 4.设置自动大小   t.setImageAutoSize(true);
     * 5.添加系统栏图标到系统托盘   st.add(t);
     * 6.显示系统栏图标提示文本  t.displayMessage(...);
     */

    private void SystemTrayInitial() {
        if (!SystemTray.isSupported()) {
            return;
        }
        try {
            String title = getResourceString("traytitle");
            String company = getResourceString("traycontent");
            SystemTray sysTray = SystemTray.getSystemTray();//获取系统默认托盘
            Image ima = Toolkit.getDefaultToolkit().getImage("../hospital/src/main/resource/icons/12.jpg");//创建系统栏图标
            TrayIcon trayicon = new TrayIcon(ima, title + "\n" + company, createMenu());//创建系统栏图标对象
            trayicon.setImageAutoSize(true);//设置自动大小
            trayicon.addActionListener(new SysTrayActionListener());
            sysTray.add(trayicon);//添加系统栏图标到系统托盘
            trayicon.displayMessage(title, company, TrayIcon.MessageType.INFO);//显示系统栏图标提示文本
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();
        MenuItem exitItem = new MenuItem("exit");     //退出
        exitItem.setFont(new Font("微软雅黑",1,25));
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MenuItem openItem = new MenuItem("open");         //打开
        openItem.setFont(new Font("微软雅黑",1,25));
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isVisible()) {
                    setVisible(true);
                    toFront();   //使主窗体显示在最上层
                } else {
                    toFront();
                }
            }
        });
        menu.add(openItem);//添加打开菜单项
        menu.addSeparator();//添加菜单项分隔符
        menu.add(exitItem);//添加退出菜单项
        return menu;
    }

    class SysTrayActionListener implements ActionListener {//系统栏双击事件

        public void actionPerformed(ActionEvent e) {
            setVisible(true);
            toFront();
        }
    }

    /**
     * 时间显示  JLabel
     * @return
     */
    public JLabel getTimeLabel(){
        if(timeLabel==null){
            timeLabel=new JLabel();
            timeLabel.setFont(new Font("微软雅黑",1,40));
            timeLabel.setBounds(550,20,180,40);
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
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
        }
        return timeLabel;
    }

    /**
     * 获得登录用户名
     * @return
     */

    public JLabel getNameLabel(){
        if(nameLabel==null){
            nameLabel=new JLabel();
            nameLabel.setText(getResourceString("welcome")+"："+name);
            nameLabel.setFont(new Font("宋体",1,30));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setBounds(50,20,250,40);
        }
        return nameLabel;
    }

    /*
    菜单栏
     */
    public JMenuBar getjMenuBar() {
        if (jMenuBar == null) {
            jMenuBar = new JMenuBar();
            jMenuBar.add(getjMenuBaseInfoInfo());
            jMenuBar.add(getjMenuPatientInfo());
            jMenuBar.add(getjMenuUserManager());
            jMenuBar.add(getjMenuSystemManager());
            jMenuBar.add(getjMenuHelp());
        }
        return jMenuBar;
    }

/*
菜单
 */

    public JMenu getjMenuBaseInfoInfo() {
        if (jMenuBaseInfo == null) {
            jMenuBaseInfo = new JMenu(getResourceString("baseinfo"));
            jMenuBaseInfo.add(getjMenuItemShowDoctor());
            jMenuBaseInfo.add(getjMenuItemShowMedicalResource());
        }
        return jMenuBaseInfo;
    }

    public JMenu getjMenuPatientInfo() {
        if (jMenuPatientInfo == null) {
            jMenuPatientInfo = new JMenu(getResourceString("patientinfo"));
            jMenuPatientInfo.add(getjMenuItemPatientAdd());
            jMenuPatientInfo.add(getjMenuItemPatientMaintain());
            jMenuPatientInfo.add(getjMenuItemPatient());
        }
        return jMenuPatientInfo;
    }

    public JMenu getjMenuSystemManager() {
        if (jMenuSystemManager == null) {
            jMenuSystemManager = new JMenu(getResourceString("systemmanager"));
            jMenuSystemManager.add(getjMenuItemExit());
            jMenuSystemManager.add(getjMenuItemRestartLogin());
        }
        return jMenuSystemManager;
    }

    public JMenu getjMenuUserManager() {
        if (jMenuUserManager == null) {
            jMenuUserManager = new JMenu(getResourceString("usermanager"));
            jMenuUserManager.add(getjMenuItemUserAdd());
            jMenuUserManager.add(getjMenuItemUserMaintain());
        }
        return jMenuUserManager;
    }

    public JMenu getjMenuHelp() {
        if (jMenuHelp == null) {
            jMenuHelp = new JMenu(getResourceString("help"));
            jMenuHelp.add(getjMenuItemVersion());
            jMenuHelp.add(getjMenuItemFeedback());
        }
        return jMenuHelp;
    }

    /*
    菜单项
     */
    public JMenuItem getjMenuItemShowMedicalResource() {
        if (jMenuItemShowMedicalResource == null) {
            jMenuItemShowMedicalResource = new JMenuItem();
            jMenuItemShowMedicalResource.setText(getResourceString("medicalsupplies"));
            jMenuItemShowMedicalResource.setActionCommand(commandString[1]);
            jMenuItemShowMedicalResource.addActionListener(this);
        }
        return jMenuItemShowMedicalResource;
    }

    public JMenuItem getjMenuItemShowDoctor() {
        if (jMenuItemShowDoctor == null) {
            jMenuItemShowDoctor = new JMenuItem(getResourceString("On-the-job"));
            jMenuItemShowDoctor.setActionCommand(commandString[0]);
            jMenuItemShowDoctor.addActionListener(this);
        }
        return jMenuItemShowDoctor;
    }

    public JMenuItem getjMenuItemUserAdd() {
        if (jMenuItemUserAdd == null) {
            jMenuItemUserAdd = new JMenuItem(getResourceString("adduser"));
            jMenuItemUserAdd.setActionCommand(commandString[5]);
            jMenuItemUserAdd.addActionListener(this);
        }
        return jMenuItemUserAdd;
    }

    public JMenuItem getjMenuItemUserMaintain() {
        if (jMenuItemUserMaintain == null) {
            jMenuItemUserMaintain = new JMenuItem(getResourceString("usermaintain"));
            jMenuItemUserMaintain.setActionCommand(commandString[6]);
            jMenuItemUserMaintain.addActionListener(this);
        }
        return jMenuItemUserMaintain;
    }

    public JMenuItem getjMenuItemPatient() {
        if (jMenuItemPatient == null) {
            jMenuItemPatient = new JMenuItem(getResourceString("patientinfo"));
            jMenuItemPatient.setActionCommand(commandString[3]);
            jMenuItemPatient.addActionListener(this);
        }
        return jMenuItemPatient;
    }

    public JMenuItem getjMenuItemPatientAdd() {
        if (jMenuItemPatientAdd == null) {
            jMenuItemPatientAdd = new JMenuItem(getResourceString("addpatient"));
            jMenuItemPatientAdd.setActionCommand(commandString[2]);
            jMenuItemPatientAdd.addActionListener(this);
        }
        return jMenuItemPatientAdd;
    }

    public JMenuItem getjMenuItemPatientMaintain() {
        if (jMenuItemPatientMaintain == null) {
            jMenuItemPatientMaintain = new JMenuItem(getResourceString("infomaintain"));
            jMenuItemPatientMaintain.setActionCommand(commandString[4]);
            jMenuItemPatientMaintain.addActionListener(this);
        }
        return jMenuItemPatientMaintain;
    }

    public JMenuItem getjMenuItemVersion() {
        if (jMenuItemVersion == null) {
            jMenuItemVersion = new JMenuItem(getResourceString("nowversion"));
            jMenuItemVersion.setActionCommand(commandString[9]);
//            jMenuItemVersion.setFont(new Font("微软雅黑",1,15));
            jMenuItemVersion.addActionListener(this);
        }
        return jMenuItemVersion;
    }

    public JMenuItem getjMenuItemFeedback() {
        if (jMenuItemFeedback == null) {
            jMenuItemFeedback = new JMenuItem(getResourceString("feedback"));
            jMenuItemFeedback.setActionCommand(commandString[10]);
            jMenuItemFeedback.addActionListener(this);
        }
        return jMenuItemFeedback;
    }

    public JMenuItem getjMenuItemExit() {
        if (jMenuItemExit == null) {
            jMenuItemExit = new JMenuItem(getResourceString("exit"));
//            jMenuItemExit.setFont(new Font("宋体",1,15));
            jMenuItemExit.setActionCommand(commandString[7]);
            jMenuItemExit.addActionListener(this);
        }
        return jMenuItemExit;
    }

    public JMenuItem getjMenuItemRestartLogin() {
        if (jMenuItemRestartLogin == null) {
            jMenuItemRestartLogin = new JMenuItem(getResourceString("login"));
            jMenuItemRestartLogin.setActionCommand(commandString[8]);
            jMenuItemRestartLogin.addActionListener(this);
        }
        return jMenuItemRestartLogin;
    }

    /**
     * 工具栏
     */
    public JToolBar getjToolBar() {
        if (jToolBar == null) {
            jToolBar = new JToolBar();
            jToolBar.setFloatable(false);//不允许拖动
            for(int i=0;i<commandString.length;i++){
                jb=new JButton();
                ImageIcon ig=new ImageIcon(toolImageName[i]);
                jb.setIcon(ig);
                jb.setToolTipText(tipImageName[i]);
                jb.setActionCommand(commandString[i]);
                jb.addActionListener(this);
                jToolBar.add(jb);
            }
        }
        return jToolBar;
    }

    /**
     * 左边的面板
     */
    public JPanel mainLeftCommand(){
        if(leftjPanel==null){
            leftjPanel=new JPanel();
            leftjPanel.setLayout(null);
            leftjPanel.setLayout(new GridLayout(5,1));
//            item0=new JButton("首页");
//            item0.setPreferredSize(new Dimension(150,47));
//            item0.setContentAreaFilled(false);      //设置透明

            item1=new JButton(getResourceString("baseinfo"));
            item1.setPreferredSize(new Dimension(150,47));
            item1.setContentAreaFilled(false);      //设置透明
            item1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new BaseInfoFrame().setVisible(true);
                }
            });

            item2=new JButton(getResourceString("patientinfo"));
            item2.setPreferredSize(new Dimension(150,47));
            item2.setContentAreaFilled(false);      //设置透明
            item2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new PatientManager().setVisible(true);
                }
            });

            item3=new JButton(getResourceString("usermanager"));
            item3.setPreferredSize(new Dimension(150,47));
            item3.setContentAreaFilled(false);      //设置透明
            item3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new UserManager().setVisible(true);
                }
            });

            item4=new JButton(getResourceString("systemmanager"));
            item4.setPreferredSize(new Dimension(150,47));
            item4.setContentAreaFilled(false);      //设置透明

            item5=new JButton(getResourceString("help"));
            item5.setPreferredSize(new Dimension(150,47));
            item5.setContentAreaFilled(false);      //设置透明

//            leftjPanel.add(item0);
            leftjPanel.add(item1);
            leftjPanel.add(item2);
            leftjPanel.add(item3);
            leftjPanel.add(item4);
            leftjPanel.add(item5);
        }
        return leftjPanel;
    }

    /**
     * 事件处理
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(commandString[0])){
            //在职医生
            BaseInfoFrame bf=new BaseInfoFrame();
            bf.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[1])){
            //医疗物资
            BaseInfoFrame bf=new BaseInfoFrame();
            bf.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[2])){
            //添加病人
            PatientManager pm=new PatientManager();
            pm.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[3])){
            //病人信息
            PatientManager pm=new PatientManager();
            pm.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[4])){
            //信息维护
            PatientManager pm=new PatientManager();
            pm.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[5]) || e.getActionCommand().equals(commandString[6])){
            //添加用户      //用户维护
            UserManager um=new UserManager();
            um.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[7])){
            //系统退出
            System.exit(0);
        }else if(e.getActionCommand().equals(commandString[8])){
            //重新登录
            dispose();
            loginFrame lf=new loginFrame();
            lf.setVisible(true);
        }else if(e.getActionCommand().equals(commandString[9])){
            //当前版本
            JOptionPane.showMessageDialog(null,getResourceString("versionuser")+"user");
        }else if(e.getActionCommand().equals(commandString[10])){
            //信息反馈
            FeedBackFrame ff=new FeedBackFrame();
            ff.setVisible(true);
        }
    }

    /**
     * 国际化代码
     * @param res
     * @return
     */
    protected String getResourceString(String res) {
        String str;
        try {
            str = resources.getString(res);
        } catch (MissingResourceException mre) {
            str = null;
        }
        return str;
    }


    public static void main(String args[]){
        MainFrame mf=new MainFrame("李华");
        mf.setVisible(true);
    }
}
