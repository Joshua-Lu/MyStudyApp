package com.lhf.designpatternlib.proxy.virtualexample;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.JFrame;

/**
 * Created by Joshua on 2021/2/6 20:52
 */
public class ImageProxyTest {
    public static void main(String[] args) throws MalformedURLException {
        Icon icon = new ImageProxy(new URL("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2873813226,4113011731&fm=26&gp=0.jpg"));
        JFrame frame = new JFrame();
        ImageComponent imageComponent = new ImageComponent(icon);
        frame.getContentPane().add(imageComponent);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
