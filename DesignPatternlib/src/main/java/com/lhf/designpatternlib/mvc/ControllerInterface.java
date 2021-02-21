package com.lhf.designpatternlib.mvc;

public interface ControllerInterface {
	void start();

	void stop();

	void increaseBPM();

	void decreaseBPM();

	void setBPM(int bpm);
}
