package com.my.frame;

import com.my.bean.User;
import com.my.dao.UserDao;
import com.my.tool.Background;
import com.my.tool.ProgressBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class loginFrame extends JFrame {
    private Container container;
    private JButton submit, reset;
    private JLabel userLabel, passwordLabel;
    private JTextField userText;
    private JPasswordField passwordText;
    private Background panel;
    private ProgressBarPanel pbp;
    private String name;
//    private JPanel panel,panel1;
    private int userLen,passwordLen; //账号，密码长度
//    private JLabel bg;

    public loginFrame() {
        super("医疗管理系统");
        init();
        this.setBounds(400, 200, 610, 350);
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);      //调用任意已注册 WindowListener 的对象后自动隐藏并释放该窗体。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //使用 System exit 方法退出应用程序。仅在应用程序中使用。
        this.setResizable(false);
    }

    public void init(){
        container = this.getContentPane();
        container.add(getPanel());
    }

//    public JPanel setImagePanel(){
//        if(panel1==null){
//            panel1=new JPanel();
//            panel1.setLayout(null);
//            panel1.add(setBackgroundImage());
//        }
//        return panel1;
//    }

//    public JLabel setBackgroundImage(){
//        if(bg==null){
//            bg=new JLabel(new ImageIcon("D:/eclipse/医院管理系统/images/images/1.jpeg"));
//            bg.setBounds(0,0,JFrame.WIDTH,JFrame.HEIGHT);
//        }
//        return bg;
//    }

    public JPanel getPanel() {
        if (panel == null) {
            panel = new Background("1.jpeg",0,0);
            panel.setLayout(null);
            panel.add(getUserLabel());
            panel.add(getUserText());
            panel.add(getPasswordLabel());
            panel.add(getPasswordText());
            panel.add(getSubmit());
            panel.add(getReset());
        }
        return panel;
    }

    public JLabel getUserLabel() {
        if (userLabel == null) {
            userLabel = new JLabel();
            userLabel.setFont(new Font("",1,20));    //String 字体，int 风格，int 字号
            userLabel.setText("账号：");
            userLabel.setBounds(130, 80, 100, 20);
        }
        return userLabel;
    }

    public JLabel getPasswordLabel() {
        if (passwordLabel == null) {
            passwordLabel = new JLabel();
            passwordLabel.setFont(new Font("",1,20));
            passwordLabel.setText("密码：");
            passwordLabel.setBounds(130, 150, 100, 20);
        }
        return passwordLabel;
    }

    public JTextField getUserText() {
        if (userText == null) {
            userText = new JTextField("  账号为3~16个字符",20);
            userText.setForeground(Color.GRAY);
            userText.setBounds(230, 80,150,30);
            userText.addKeyListener(new KeyListener(){
                public void keyPressed(KeyEvent e) {
                    userLen=userText.getText().length();
                    if(userLen >= 16){
                        userText.setEnabled(false);
                        JOptionPane.showMessageDialog(null,"账号长度过长！");
                    }
                }
                public void keyTyped(KeyEvent e){
                }
                public void keyReleased(KeyEvent e){
                    userText.setEnabled(true);
                }
            });
        }
        return userText;
    }

    public JPasswordField getPasswordText() {
        if (passwordText == null) {
            passwordText = new JPasswordField(20);
            passwordText.setBounds(230, 150, 150, 30);
            passwordText.addKeyListener(new KeyListener(){
                public void keyTyped(KeyEvent e){

                }
                public void keyPressed(KeyEvent e){
                    passwordLen=passwordText.getPassword().length;
                    if(passwordLen>=16){
                        passwordText.setEnabled(false);
                        JOptionPane.showMessageDialog(null,"密码过长");
                    }
                }
                public void keyReleased(KeyEvent e){
                    passwordText.setEnabled(true);
                }
            });
        }
        return passwordText;
    }

    public JButton getSubmit() {
        if (submit == null) {
            submit = new JButton();
            submit.setPreferredSize(new Dimension(54,23));      //实际大小
            submit.setBounds(150, 200, 54, 23);
            submit.setIcon(new ImageIcon("../hospital/src/main/resource/images/dl.jpg"));
//            submit.setPressedIcon();          //鼠标按压后的图标
            submit.setRolloverIcon(new ImageIcon("../hospital/src/main/resource/images/dl01.jpg"));
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    name=userText.getText();
                    String password=new String(passwordText.getPassword());
                    User user=new User();
                    user.setName(name);
                    user.setPassword(password);
                    if(UserDao.userLogin(user)){
                        dispose();
                        pbp=new ProgressBarPanel();
                        pbp.setVisible(true);
                        new Thread(){
                            public void run() {
                                try{
                                    Thread.sleep(3000);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                pbp.setVisible(false);
                                new MainFrame(name);
                            }
                        }.start();
                    }
                }
            });
        }
        return submit;
    }

    public JButton getReset() {
        if (reset == null) {
            reset = new JButton();
            reset.setPreferredSize(new Dimension(54,23));   //实际大小
            reset.setBounds(300, 200, 54, 23);
            reset.setIcon(new ImageIcon("../hospital/src/main/resource/images/cz.jpg"));     //getClass().getResource("../resources/icons/cz.jpg"
            reset.setRolloverIcon(new ImageIcon("../hospital/src/main/resource/images/cz01.jpg"));
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    userText.setText("");
                    passwordText.setText("");
                    userText.setEnabled(true);
                    passwordText.setEnabled(true);
                }
            });
        }
        return reset;
    }

    public static void main(String args[]) {
        loginFrame lf = new loginFrame();
        lf.setVisible(true);
    }
}
