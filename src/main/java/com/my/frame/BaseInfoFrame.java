package com.my.frame;

import com.my.dao.PatientDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseInfoFrame extends JFrame {
    private JDesktopPane desktopPane;
    private DefaultTableModel tableModel,tableModel1;
    private JTable table,table1;
    private InteralFrame baseInfoFrame;
    private JTabbedPane mainPane;
    private JPanel showDoctorPane, medicalResourcePane;
    private JTextField idText;

    public BaseInfoFrame() {
        super("基本信息");
        this.setBounds(100, 100, 700, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    /**
     * 桌面面板
     */
    public void init() {
        desktopPane = new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);    //设置窗体内部拖动模式
        desktopPane.add(getBaseInfoFrame());
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        setContentPane(desktopPane);
    }

    /**
     * 内部面板
     *
     * @return
     */
    public InteralFrame getBaseInfoFrame() {
        if (baseInfoFrame == null) {
            baseInfoFrame = new InteralFrame("基本信息");
            baseInfoFrame.setBounds(20, 20, 500, 600);
            baseInfoFrame.add(getMainPane());
            baseInfoFrame.setVisible(true);
        }
        return baseInfoFrame;
    }

    /**
     * 选项卡面板
     *
     * @return
     */
    public JTabbedPane getMainPane() {
        if (mainPane == null) {
            mainPane = new JTabbedPane();
            mainPane.add("在职医生", getShowDoctorPane());
            mainPane.add("医疗物资", getMedicalResourcePane());
        }
        return mainPane;
    }

    /**
     * 在职医生信息
     *
     * @return
     */
    public JPanel getShowDoctorPane() {
        if (showDoctorPane == null) {
            showDoctorPane = new JPanel();

            tableModel=new DefaultTableModel();
            table=new JTable(tableModel);
            table.setRowHeight(30);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    //自动调整模式
            JScrollPane scrollPane=new JScrollPane(table);
            PatientDao.setDoctorTitle(tableModel);
            PatientDao.selectAllDoctorData(tableModel);

            showDoctorPane.add(scrollPane);
        }
        return showDoctorPane;
    }


    /**
     * 医疗物资
     *添加药品，删除药品，查询药品
     * @return
     */
    public JPanel getMedicalResourcePane() {
        if (medicalResourcePane == null) {
            medicalResourcePane = new JPanel();

            JPanel p=new JPanel();
            JLabel idLabel=new JLabel("药品编号：");
            idText=new JTextField(20);
            JButton select=new JButton("查找");
            select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id=idText.getText();
                    PatientDao.selectMedical(tableModel1,id);
                }
            });
            p.add(idLabel);
            p.add(idText);
            p.add(select);

            tableModel1=new DefaultTableModel();
            table1=new JTable(tableModel1);
            table1.setRowHeight(30);
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    //自动调整模式
            JScrollPane scrollPane=new JScrollPane(table1);
            PatientDao.medicalTitle(tableModel1);

            JPanel p1=new JPanel();
            JButton selectAll=new JButton("显示所有");
            selectAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PatientDao.selectAllMedical(tableModel1);
                }
            });
            JButton delete=new JButton("删除操作");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(PatientDao.deleteMedical(table1)){
                        JOptionPane.showMessageDialog(null,"删除成功！！");
                    }else{
                        JOptionPane.showMessageDialog(null,"删除失败！！");
                    }
                }
            });
            p1.add(selectAll);
            p1.add(delete);

            medicalResourcePane.add(p);
            medicalResourcePane.add(scrollPane);
            medicalResourcePane.add(p1);
        }
        return medicalResourcePane;
    }

    class InteralFrame extends JInternalFrame {
        public InteralFrame(String title) {
            super();
            this.setTitle(title);
            this.setResizable(true);     //设置允许自动调整大小
//            this.setClosable(true);     //设置关闭按钮
            this.setMaximizable(true);      //设置最大化按钮
            this.setIconifiable(true);      //设置提供图标化按钮，最小化在桌面
        }
    }
    public static void main (String args[]){
        BaseInfoFrame bif = new BaseInfoFrame();
        bif.setVisible(true);
    }
}
