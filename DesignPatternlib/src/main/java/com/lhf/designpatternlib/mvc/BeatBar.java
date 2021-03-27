package com.lhf.designpatternlib.mvc;

import com.lhf.javacommonlib.utils.CommonUtils;

import javax.swing.JProgressBar;

public class BeatBar extends JProgressBar implements Runnable {
	private static final long serialVersionUID = 2L;
	JProgressBar progressBar;
	Thread thread;

	public BeatBar() {
		thread = new Thread(this);
		setMaximum(100);
		thread.start();
	}

	public void run() {
		for (; ; ) {
			int value = getValue();
			value = (int) (value * .75);
			setValue(value);
			repaint();
			CommonUtils.threadSleep(50);
		}
	}
}
