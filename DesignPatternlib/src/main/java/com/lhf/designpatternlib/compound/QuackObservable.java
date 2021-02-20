package com.lhf.designpatternlib.compound;

/**
 * Created by Joshua on 2021/2/21 1:16
 */
interface QuackObservable {
    void registerObserver(QuackObserver observer);

    void notifyObservers();
}
