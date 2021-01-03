package com.lhf.designpatternlib.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
 * Created by Joshua on 2020/4/6 20:46.
 */
public class Observable {
    String data;
    protected List<IObserver> observers = new ArrayList<IObserver>();

    //增加观察者方法
    public void add(IObserver observer) {
        System.out.println("Observable.add() called with: observer = [" + observer + "]");
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(IObserver observer) {
        System.out.println("Observable.remove() called with: observer = [" + observer + "]");
        observers.remove(observer);
    }

    //通知观察者方法
    public void notifyObserver() {
        System.out.println("Observable.notifyObserver() called");
        for (IObserver observer :
                observers) {
            observer.response(data);
        }
    }

    public void setData(String data) {
        this.data = data;
        notifyObserver();
    }
}
