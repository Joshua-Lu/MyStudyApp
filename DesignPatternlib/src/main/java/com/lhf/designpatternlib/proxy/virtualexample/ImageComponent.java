package com.lhf.designpatternlib.proxy.virtualexample;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

class ImageComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private Icon icon;

	public ImageComponent(Icon icon) {
		this.icon = icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		int x = 0;
		int y = 0;
		icon.paintIcon(this, g, x, y);
	}
}
