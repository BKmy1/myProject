package com.my.frame;

import javax.swing.*;

import org.apache.ibatis.session.SqlSession;

import com.my.Mapper.PatientMapper;
import com.my.bean.Patient;

import cn.com.sise.util.MyBatisUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 创建一个桌面面板，用来存放内部窗体
 */

public class FeedBackFrame extends JFrame {
    private JDesktopPane desktopPane;
    private InteralFrame feedBackFrame;
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel mobilePhone;
    private JTextField mpText;
    private JButton btn_submit,btn_reset;
    private JLabel area;
    private JTextArea submitInfo;
    private SqlSession sqlSession;

    public FeedBackFrame(){
        super();
        this.setTitle("帮助");
        this.setBounds(100,100,500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        init();
        desktopPane.add(getinteralFrame());
    }

    public InteralFrame getinteralFrame(){
        if(feedBackFrame==null){
            feedBackFrame=new InteralFrame("信息反馈");
            feedBackFrame.setBounds(20,20,380,300);
            feedBackFrame.setVisible(true);

            JPanel p=new JPanel();
            p.setLayout(null);
            nameLabel=new JLabel("姓名：");
            nameLabel.setBounds(new Rectangle(20,20,50,50));

            nameText=new JTextField(10);
            nameText.setBounds(new Rectangle(60,35,100,20));

            mobilePhone=new JLabel("电话：");
            mobilePhone.setBounds(new Rectangle(200,20,50,50));

            mpText=new JTextField(10);
            mpText.setBounds(new Rectangle(240,35,100,20));

            area=new JLabel("意见：");
            area.setBounds(20,100,50,50);

            submitInfo=new JTextArea();
            submitInfo.setLineWrap(true);   //自动换行
            submitInfo.setWrapStyleWord(true);      // 设置单词在一行不足容纳时换行
            submitInfo.setBorder(BorderFactory.createLineBorder(Color.green,1));
//            submitInfo.setBounds(60,100,260,60);


            JScrollPane jsb=new JScrollPane(submitInfo);  //滚动条无法与多行文本共同使用
            jsb.setBounds(60,100,260,60);

            p.add(nameLabel);
            p.add(nameText);
            p.add(mobilePhone);
            p.add(mpText);
            p.add(area);
            p.add(jsb);
            p.add(getButtonSubmit());
            p.add(getButtonReset());
            feedBackFrame.add(p);
        }
        return feedBackFrame;
    }

    public void init(){
        desktopPane=new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);    //设置窗体内部拖动模式
        getContentPane().add(desktopPane,BorderLayout.CENTER);
    }

    public JButton getButtonSubmit() {
        if(btn_submit==null) {
            btn_submit=new JButton("提交");
            btn_submit.setBounds(80,200,60,40);
            btn_submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    Connection conn=null;
                    try {
//                        conn= Dao.getConnect();
//                        PreparedStatement ps=conn.prepareStatement("insert into tbl_suggestion (name,mobile,content) values(?,?,?)");
                        String name=nameText.getText();
                        String tele=mpText.getText();
                        String areaInfo=submitInfo.getText();
                        if(name.equals("")) {
                            JOptionPane.showMessageDialog(null, "请添加姓名！！！");
                        }else if(tele.equals("")) {
                            JOptionPane.showMessageDialog(null, "请添加电话！！！");
                        }else {
//                            ps.setString(1, name);
//                            ps.setString(2, tele);
//                            ps.setString(3, areaInfo);
//                            int flag=ps.executeUpdate();
                        	Patient patient=new Patient();
                        	patient.setName(name);
                        	patient.setTelephone(tele);
                        	patient.setContent(areaInfo);
                        	SqlSession sqlSession=MyBatisUtil.getSession();
                        	PatientMapper patientMapper=sqlSession.getMapper(PatientMapper.class);
                        	int flag=patientMapper.addFeedBack(patient);
                            if(flag>0) {
                                JOptionPane.showMessageDialog(null, "添加成功！！！");
                                dispose();
                            }else {
                                JOptionPane.showMessageDialog(null, "添加失败!!!");
                            }
                        }
                    }catch(Exception e1) {
                        e1.printStackTrace();
                    }finally {
                        if(sqlSession!=null) {
                            try {
                                sqlSession.close();
                            }catch(Exception ee) {
                                JOptionPane.showMessageDialog(null,ee.getMessage());
                            }
                        }
                    }
                }
            });
        }
        return btn_submit;
    }
    public JButton getButtonReset() {
        if(btn_reset==null) {
            btn_reset=new JButton("重置");
            btn_reset.setBounds(new Rectangle(220,200,60,40));
            btn_reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    nameText.setText("");
                    mpText.setText("");
                    submitInfo.setText("");
                }
            });
        }
        return btn_reset;
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
        FeedBackFrame fbf=new FeedBackFrame();
        fbf.setVisible(true);
    }
}
