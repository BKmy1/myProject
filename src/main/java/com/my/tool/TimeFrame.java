package com.my.tool;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFrame extends JFrame{
    private JLabel timeLabel;
    private int width,height;
    public TimeFrame(int width,int height){
        super();
        this.width=width;
        this.height=height;
        getContentPane().add(getTimeLabel());
        Time t=new Time();
        t.start();
    }

    public JLabel getTimeLabel(){
        if(timeLabel==null){
            timeLabel=new JLabel();
            timeLabel.setLayout(null);
            timeLabel.setFont(new Font("微软雅黑",1,4));
            timeLabel.setBounds(20,20,width,height);
        }
        return timeLabel;
    }

    class Time extends Thread{
        TimeFrame tf;
        @Override
        public void run() {
            while(true){
                SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
                String time=dateFormat.format(new Date( ));
                tf.timeLabel.setText(time);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
