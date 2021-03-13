# Android Note  

1. Intent可以传递的数据类型：基本数据、String、CharSequence、序列化对象，以及他们的**数组**和**Array List**。

3. SparseArray：

4. Dialog的创建必须使用**Activity**的Context不能使用Application的，因为Dialog必须在Activity上创建，属于Activity的一部分，这也是弹Dialog，**不会走**Activity的**onPause**方法的原因。

5. 默认情况下，一个应用所有的组件都运行在同一个进程的同一个线程（main线程）里。

6. 进程的5级优先级：前台进程、可见进程、服务进程、后台进程、空进程。

7. 装箱、拆箱：

8. ThreadLocal：

9. Handler原理：
   - Looper.prepare()
  - new了一个Looper对象，并在Looper的构造方法里，创建了MessageQueue对象
     - 将创建的Looper对象通过ThreadLocal与当前线程绑定
- new Handler()
     - 通过Looper类中的ThreadLocal获取到prepare()中绑定的Looper对象
  - 然后通过Looper对象，获取到MessageQueue的引用
   - handler.sendMessage(msg)
     - 设置msg.target = this(即调用sendMessage方法的handler对象)
     - 把msg添加到MessageQueue中
   - Looper.loop()
     - 开启死循环，不断的从MessageQueue中获取Message
     - 获取到msg对象后，通过msg.target拿到发送该msg的Handler对象
     - 然后调用handler的dispatchMessage，在该方法里会回调handleMessage方法
   
10. 子线程**不能更新UI**的原因：更新的时候会走View.requestLayout方法，最后调到ViewRootImpl.checkThread，在该方法中会判断当前线程是否是主线程，若不是则抛出异常CalledFromWrongThreadException。因此在某些情况下（更新不会导致requestLayout调用、ViewRootImpl还没创建等），**checkThread**没有走到，更新UI就不会抛异常，如ProgressBar。当然开发过程中还是要注意在主线程去更新UI。
11. 子线程**不能弹Toast**的原因：在Toast的构造方法里，会去调用Looper.myLooper()，获取到loop对象，若**loop为null**则抛出异常。因此，若在子线程主动调用Looper.prepare()之后，弹toast，并在最后调Looper.loop()，也是能正常弹出toast，但一般不建议这么做。至于loop的用处，是用来创建handler对象，toast显示其实也是通过handler发消息。
12. 子线程**不能创建Handler**的原因：这个原因其实与不能弹Toast一样，也是因为没有调用Looper.preapre()，**loop对象为空**，抛出异常。