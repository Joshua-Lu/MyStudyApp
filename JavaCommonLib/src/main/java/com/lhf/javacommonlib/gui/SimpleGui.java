package com.lhf.javacommonlib.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * Created by Joshua on 2021/1/14 21:45
 */
public class SimpleGui {
    public static void main(String[] args) {
        // 创建Frame和Button
        JFrame frame = new JFrame("my first JFrame");
        JButton button = new JButton("click me");
        JRadioButton radioButton = new JRadioButton("choose me");

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("SimpleGui.mouseClicked() called");
                button.setText("mouseClicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("SimpleGui.mousePressed() called");
                button.setText("mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("SimpleGui.mouseReleased() called");
                button.setText("mouseReleased");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("SimpleGui.mouseEntered() called");
                button.setText("mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("SimpleGui.mouseExited() called");
                button.setText("mouseExited");
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mousePressed 之后就会回调该方法
                System.out.println("SimpleGui.actionPerformed() called");
                button.setText("actionPerformed");
            }
        });

        radioButton.setBackground(Color.GREEN);
        button.setSize(100, 100);
        button.setLocation(20, 10);

        radioButton.setBackground(Color.RED);
        radioButton.setSize(100, 100);
        radioButton.setLocation(200, 200);

        // 先获取pane，才能add
        frame.getContentPane().add(button);
        frame.getContentPane().add(radioButton);

        // 设置关闭window时，退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300, 300);
        frame.setLayout(null);// 不设Layout为null，默认控件会撑满全屏
        frame.setVisible(true);

    }
}
