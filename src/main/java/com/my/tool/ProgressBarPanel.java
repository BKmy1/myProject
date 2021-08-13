package com.my.tool;

import javax.swing.*;

public class ProgressBarPanel extends JFrame {
//    private final int[] progressValue = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    private JProgressBar progressBar;//进度条对象
    private JPanel progressPanel;

    public ProgressBarPanel() {
        super();
        this.setBounds(20,20,150,20);
        this.setLocationRelativeTo(null);//窗体居中
        this.setResizable(false);//取消最大化
        this.setUndecorated(true);//取消窗体修饰
        progressPanel = new JPanel();

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); //设置采用不确定进度条
        progressBar.setStringPainted(false);//设置不提示信息
//        progressBar.setValue(20);
        progressPanel.add(progressBar);

//        MyThread mt = new MyThread();
//        mt.start();
        this.setContentPane(progressPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    class MyThread extends Thread {
//        public void run() {
//            //通过循环完成百分比
//            for (int i = 20; i < 30; i++) {
//                progressBar.setValue(i);//设置任务完成百分比
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            progressBar.setString("登录成功！");
//        }
//    }
}
