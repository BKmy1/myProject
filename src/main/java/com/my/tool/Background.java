package com.my.tool;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private ImageIcon bg;   //背景图片对象
    private int xwidth,yheight;

    public Background(String img,int xwidth,int yheigtht) {
        super();
        this.xwidth=xwidth;
        this.yheight=yheigtht;
        bg = new ImageIcon("../hospital/src/main/resource/images/"+img);
        setSize(bg.getIconWidth(), bg.getIconHeight());
    }

    /*
    重写绘制方法
     */

    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();    //创建Graphics2D对象
        super.paintComponent(graphics2D);   //调用父类的方法
        if (bg != null) {
            graphics2D.drawImage(bg.getImage(), xwidth, yheight, this);
        }
    }
}
