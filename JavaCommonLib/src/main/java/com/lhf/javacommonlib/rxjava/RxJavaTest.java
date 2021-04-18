package com.lhf.javacommonlib.rxjava;

import com.lhf.javacommonlib.utils.CommonUtils;

import org.junit.Test;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Joshua
 * @date 2021/4/18 0:06
 */
public class RxJavaTest {
    /**
     * 一般不会分开写，都是链式调用，这也是RxJava的特点
     */
    @Test
    public void test() {
        // 创建Observable对象
        // create 方式创建
        @NonNull Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) {
                emitter.onNext("Hello");
                emitter.onNext("RxJava");
                emitter.onComplete();
            }
        });
        // just 方式创建
        observable = Observable.just("create by just", "just 1", "just 2");
        //
        observable = Observable.fromIterable(Arrays.asList("create fromIterable", "11", "22"));

        // 创建Observer对象
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("RxJavaTest.onSubscribe() called with: d = [" + d + "]");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("RxJavaTest.onNext() called with: s = [" + s + "]");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("RxJavaTest.onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                System.out.println("RxJavaTest.onComplete() called");
            }
        };

        // observer订阅observable
        observable.subscribe(observer);
    }

    @Test
    public void test1() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext("Hello");
                    emitter.onNext("RxJava");
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.newThread())
                // map用来完成类型转换
                .map(s -> {
                    System.out.println("RxJavaTest.map() called");
                    CommonUtils.printThreadName();
                    CommonUtils.threadSleep(1000);
                    return s.length();
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("RxJavaTest.onSubscribe() called with: d = [" + d + "]");
                        CommonUtils.printThreadName();
                    }

                    @Override
                    public void onNext(@NonNull Integer s) {
                        System.out.println("RxJavaTest.onNext() called with: s = [" + s + "]");
                        CommonUtils.printThreadName();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("RxJavaTest.onError() called with: e = [" + e + "]");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("RxJavaTest.onComplete() called");
                        CommonUtils.printThreadName();
                    }
                });
        CommonUtils.threadSleep(10000);
    }

}
