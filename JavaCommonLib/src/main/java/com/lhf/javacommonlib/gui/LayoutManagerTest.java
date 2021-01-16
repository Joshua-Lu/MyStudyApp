package com.lhf.javacommonlib.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Joshua on 2021/1/16 16:18
 */
public class LayoutManagerTest {
    public static void main(String[] args) {
//        testBorderLayout();
//        testFlowLayout();
        testBoxLayout();
    }

    private static void testBorderLayout() {
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Container contentPane = frame.getContentPane();

        JButton north = new JButton("North");
        JButton south = new JButton("South");
        JButton west = new JButton("West");
        JButton east = new JButton("East");
        JButton center = new JButton("Center");

        // JFrame的默认布局管理器是BorderLayout
        contentPane.add(BorderLayout.NORTH, north);
        contentPane.add(BorderLayout.SOUTH, south);
        contentPane.add(BorderLayout.WEST, west);
        contentPane.add(BorderLayout.EAST, east);
        contentPane.add(BorderLayout.CENTER, center);
        // BorderLayout默认add在 BorderLayout.CENTER
//        contentPane.add(center);
    }

    private static void testFlowLayout() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Container contentPane = frame.getContentPane();

        // JPanel的默认布局管理器是FlowLayout
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.add(new JButton("FlowLayout default"));
        panel.add(new JButton("FlowLayout default1"));
        panel.add(new JButton("FlowLayout default2"));

        contentPane.add(panel);
    }

    private static void testBoxLayout() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Container contentPane = frame.getContentPane();

        // JPanel的默认布局管理器是FlowLayout
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        // 设置布局管理器为 BoxLayout ，方向为 BoxLayout.Y_AXIS
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JButton("BoxLayout default"));
        panel.add(new JButton("BoxLayout default1"));
        panel.add(new JButton("BoxLayout default2"));

        contentPane.add(panel);
    }
}
