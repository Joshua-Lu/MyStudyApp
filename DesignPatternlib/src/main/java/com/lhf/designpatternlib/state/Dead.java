package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Dead extends ThreadState {
    public Dead() {
        stateName = "Dead";
        System.out.println("Dead.Dead: stateName = [" + stateName + "]");
    }
}
