package com.lhf.javacommonlib.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Joshua on 2021/1/14 23:45
 */
public class CustomGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyDrawPanel myDrawPanel = new MyDrawPanel();
        frame.add(myDrawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    static class MyDrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);

            g.fillOval(100, 100, 50, 70);

            g.drawLine(0, 100, 300, 100);
            g.drawLine(0, 200, 300, 200);
            g.drawLine(0, 300, 300, 300);
            g.drawLine(100, 0, 100, 300);
            g.drawLine(200, 0, 200, 300);
            g.drawLine(300, 0, 300, 300);

            Image image = new ImageIcon("D:\\GitHub\\MyStudyApp\\JavaCommonLib\\src\\main\\java\\com\\lhf\\javacommonlib\\file\\test\\test3.png").getImage();
            g.drawImage(image, 0, 0, 100, 100, this);

            Graphics2D g2D = (Graphics2D) g;
            GradientPaint gradientPaint = new GradientPaint(200, 100, Color.BLUE,
                    250, 200, Color.GREEN);
            g2D.setPaint(gradientPaint);
            g2D.fillOval(200, 100, 50, 70);

        }
    }
}
