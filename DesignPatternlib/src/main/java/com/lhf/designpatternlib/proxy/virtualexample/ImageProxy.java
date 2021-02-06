package com.lhf.designpatternlib.proxy.virtualexample;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 虚拟代理，图像代理
 * <p>
 * Created by Joshua on 2021/2/6 20:40
 */
public class ImageProxy implements Icon {

    ImageIcon imageIcon;// 被代理的对象
    URL imageUrl;
    Thread retrievalThread;
    boolean retrieving;

    public ImageProxy(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("Loading image, please wait...", x + 20, y + 200);
            if (!retrieving) {
                retrievalThread = new Thread(() -> {
                    // TODO:@lhf paintIcon: Java中可以在子线程处理UI控件？？
                    imageIcon = new ImageIcon(imageUrl);
                    c.repaint();
                });
                retrievalThread.start();
            }
        }
    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null) {
            return imageIcon.getIconWidth();
        } else {
            return 400;
        }
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        } else {
            return 400;
        }
    }
}
