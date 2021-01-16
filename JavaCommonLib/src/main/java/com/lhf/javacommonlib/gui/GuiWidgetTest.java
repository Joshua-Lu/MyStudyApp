package com.lhf.javacommonlib.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Created by Joshua on 2021/1/16 20:39
 */
public class GuiWidgetTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();

        JTextArea textArea = new JTextArea(10, 20);
        textArea.setText("text area");
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton button = new JButton("click me");
        button.addActionListener(e -> textArea.append("button clicked! \n"));


        contentPane.add(BorderLayout.NORTH, new JTextField("text field"));
        contentPane.add(BorderLayout.WEST, new JLabel("label"));
        contentPane.add(BorderLayout.CENTER, scrollPane);
        contentPane.add(BorderLayout.SOUTH, button);

        frame.setVisible(true);

    }
}
