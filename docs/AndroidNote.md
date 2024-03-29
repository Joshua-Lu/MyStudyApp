# Android Note  

## 常见问题  

### 1. Intent可以传递的数据类型

   - 基本数据、String、CharSequence、序列化对象，以及他们的**数组**和**Array List**。

### 2. SparseArray

   - **android特有**，在数据量少（源码里写的几百个，实际测试小于2.5万左右SparseArray快，否则HashMap快）、key为int类型时，代替HashMap可以减少**内存**占用，
   - 内部维护**两个数组**，一个int数组保存key值（**有序**的，二分查找的前提），一个Object数组保存value值。
   - **remove()**:不会真正删除，只是做了**标记**，值设为 DELETED，只有执行gc()时才会**回收**，整理数组。
   - **put()**:根据key值，通过**二分查找**找到对应位置，如果该位置上已经有值或被标记为 DELETED，就更新，否则gc()整理数组，重新找位置，再插入。
   - **get()**:根据key值，通过**二分查找**找到对应位置，再根据位置取得对应的值。
   - **扩容**：默认**初始容量10**，超出当前大小，扩容为**2倍**。

### 3. ArrayMap

- 基本跟SparseArray类似，主要区别在于：
     - key值可以是**任意类型**，而SparseArray的key值只能是**int类型**，但也因此SparseArray没有装箱、计算hashCode等操作，效率更高。
     - 内部也维护了**两个数组**，但其中的int数组不是直接保存key值，而是对应的**hashCode**。
     - **扩容**：默认**初始容量0**，超出当前大小，扩容为**1.5倍**。

### 4. HashMap
   - **put**节点，JDK1.8之前头插，JDK1.8开始，尾插，改成尾插的原因：头插法在**多线程**扩容的时候会造成**链表环**。1.8还加入了**红黑树**，在链表个数**大于8**时使用，提高get的效率。
   - **hash函数**：(n - 1) & hash，在n为**2的n次方**时，产生hash**冲突的概率最低**（这也是HashMap初始默认容量为16，**扩容为2倍的原因**）。这种情况概率低的原因是：此时（n - 1）的二进制表示每位都是1，1&0 = 0,1&1 = 1，而如果二进制位上是0的话，0&0 = 0，0&1 = 0，就冲突了。
   - **线程不安全**：HashTable和ConcurrentHashMap是线程安全的，HashTable是将put和get方法都加synchronized，ConcurrentHashMap是在这两个方法里对当前操作的链表加synchronized，因此只有put和get操作的链表相同时，锁才会生效，这样效率会更高。
   - **扩容**：默认**初始容量16**，超出**当前大小*加载因子**（默认0.75），扩容为**2倍**。

### 5. Dialog创建的Context

- Dialog的创建必须使用**Activity**的Context不能使用Application的，会报BadTokenException，只有使用activity的context，获取到的token不为null才可以。
- Dialog弹出后，activity会走onPause，如果Dialog是全屏不透明的，还会走onStop。不同于PopWindow，不会走任何生命周期。

### 6. 组件运行在同一线程

- 默认情况下，一个应用所有的组件都运行在同一个进程的同一个线程（main线程）里。

### 7. 进程的5级优先级  

- 前台进程、可见进程、服务进程、后台进程、空进程。

### 8. 装箱、拆箱

### 9. 强软弱虚引用
   - 强引用：只用引用断开（**设为null**），对象才能被回收
   - 软引用：**内存不足**时，gc会回收，通常用于**缓存**
   - 弱引用：只要**gc**，对象就会被回收，防止**内存泄漏**，JDK中的应用**ThreadLocal**
   - 虚引用：管理**堆外内存**，gc回收时会将该对象的一些信息，放到一个queue里，gc线程会遍历这个queue，拿到信息后就会删除对应的堆外内存

### 10. ThreadLocal 
- set(T value)：
  - 首先获取到**当前线程**的ThreadLocal.ThreadLocalMap成员变量，然后将当前**ThreadLocal对象作为key**，传进来的value作为value，保存到线程的map对象里。
  - 所以value其实是set到了当前线程的map对象里，因此与线程**绑定**在一起了，不会影响其他线程。
  - 这个map对象，里面其实是一个**数组**，存放的是ThreadLocalMap.Entry对象，Entry对象继承WeakReference<ThreadLocal<?>>，**弱引用**指向的是ThreadLocal对象，还有一个成员变量，保存value。
- get()：
  - 首先也是获取到当前线程的map对象，然后以当前ThreadLocal对象作为key，获取到对应的value。
- remove()：
  - 从map中移除当前ThreadLocal对象作为key的Entry。
  - ThreadLocal对象不再使用时，一定要调用remove方法，否则会造成内存泄漏。（虽然key是弱引用，内存不足时会回收，但是不remove的话，entry会一直在map里，对应的value对象内存泄漏）

### 11. Handler原理

   - Looper.prepare()
     * new了一个Looper对象，并在Looper的构造方法里，创建了MessageQueue对象
     * 将创建的Looper对象通过ThreadLocal与当前线程绑定
   - new Handler()
     * 通过Looper类中的ThreadLocal获取到prepare()中绑定的Looper对象
     * 然后通过Looper对象，获取到MessageQueue的引用
   - handler.sendMessage(msg)
     * 设置**msg.target = this**(即调用sendMessage方法的handler对象)
     * 把msg添加到MessageQueue中
   - Looper.loop()
     * 开启死循环，不断的从MessageQueue中获取Message
     * 获取到msg对象后，通过msg.target拿到发送该msg的Handler对象
     * 然后调用handler的dispatchMessage，在该方法里会回调handleMessage方法

- 子线程**不能弹Toast**的原因：在Toast的构造方法里，会去调用Looper.myLooper()，获取到loop对象，若**loop为null**则抛出异常。因此，若在子线程主动调用Looper.prepare()之后，弹toast，并在最后调Looper.loop()，也是能正常弹出toast，但一般不建议这么做。至于loop的用处，是用来创建handler对象，toast显示其实也是通过handler发消息。

- 子线程**不能创建Handler**的原因：这个原因其实与不能弹Toast一样，也是因为没有调用Looper.prepare()，**loop对象为空**，抛出异常。

### 12. 子线程**不能更新UI**的原因

- 更新的时候会走View.requestLayout方法，最后调到ViewRootImpl.checkThread，在该方法中会判断当前线程是否是主线程，若不是则抛出异常CalledFromWrongThreadException。因此在某些情况下（更新不会导致requestLayout调用、ViewRootImpl还没创建等），**checkThread**没有走到，更新UI就不会抛异常，如ProgressBar。当然开发过程中还是要注意在主线程去更新UI。

### 13. 动态代理：

   - 在调用`Proxy.newProxyInstance()`之前，设置一个属性`System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");`之后就能看到生成的代理类的class文件了，该类**继承Proxy**，并**实现代理的接口**。
   - 从该文件中可以看到，它还自动添加了Object类的`equals()`、`toString()`、`hashCode()`3个方法，以及代理的接口中的所有方法。并且调用这些方法，都是转交给**InvocationHandler的`invoke()`方法**处理。

### 14. 统计：友盟
### 15. 启动流程
### 16. 打包

### 17. 加载大图

- 获取原图尺寸

  ```java
  BitmapFactory.Options options = new BitmapFactory.Options();
  options.inJustDecodeBounds = true;// 禁止
  BitmapFactory.decodeResource(getResources(), R.id.myimage, options);
  int imageHeight = options.outHeight;
  int imageWidth = options.outWidth;
  String imageType = options.outMimeType;
  ```

- 根据ImageView尺寸和原图尺寸，计算压缩比例

  ```java
  public static int calculateInSampleSize(BitmapFactory.Options options,
  		int reqWidth, int reqHeight) {
  	// 源图片的高度和宽度
  	final int height = options.outHeight;
  	final int width = options.outWidth;
  	int inSampleSize = 1;
  	if (height > reqHeight || width > reqWidth) {
  		// 计算出实际宽高和目标宽高的比率
  		final int heightRatio = Math.round((float) height / (float) reqHeight);
  		final int widthRatio = Math.round((float) width / (float) reqWidth);
  		// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
  		// 一定都会大于等于目标的宽和高。
  		inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
  	}
  	return inSampleSize;
  }
  ```

- 使用计算得到的压缩比例，解析图片

  ```java
  public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
          int reqWidth, int reqHeight) {
  	// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
      final BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeResource(res, resId, options);
      // 调用上面定义的方法计算inSampleSize值，使用获取到的inSampleSize值再次解析图片
      options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
      // 将inJustDecodeBounds设回false
      options.inJustDecodeBounds = false;
      return BitmapFactory.decodeResource(res, resId, options);
  }
  ```

### 18. SharePreference的commit和apply区别  

- 



## 多线程  

### 1. 线程的基本方法

- start()：启动线程，新起一个线程执行run方法，不能直接调run()，否则还是在当前线程里。
- sleep(long millis)：休眠一定时间，该期间不会获得CPU。
- yield()：放弃CPU，回到ready状态，其他线程可竞争CPU。
- join()：在一个线程里调用另一个线程的join()，会先去执行另一个线程，执行完了，再返回当前线程。

### 2. 线程的状态

![线程状态图](AndroidNote.assets/线程状态图.png)

### 3. synchronized

- 是可重入的
- 抛异常会自动释放锁
- 锁升级过程：
  - 无锁：刚**new**出来，还**没有**线程访问
  - 偏向锁：有**一个**线程访问，升级为偏向锁，在被锁对象的对象头的markword，记录这个线程id。
  - 自旋锁：有**多个**线程争用，升级为自旋锁，开启一个循环，等待锁被释放。适合**线程数少**，锁住的代码执行**时间短**的情况。
  - 重量级锁：自旋**超过10次**，升级为重量级锁，由操作系统来处理，进入等待队列。适合**线程数多**，锁住的代码执行**时间长**的情况。

### 4. volatile  

- 保证线程可见性
  - 默认情况，每个线程在使用变量时，都会从**堆内存**里，**拷贝一份副本**到自己线程的**专属内存**，因此一个线程里改变变量的值，另一个线程并**不会**获取到最新的值。
  - 加上volatile之后，一个线程里的修改，会被立刻同步给另一个线程
- 禁止指令重排序
  - 默认情况，为了提高执行效率，CPU会在不影响执行结果的情况下，对一些指令的顺序进行重新排列。
  - 加上volatile之后，就可以禁止这种重排序。
- 典型应用：双重校验单例模式（DCL，Double Check Lock）
  - 不加volatile时，虽然也能保证单例，但是在**初始化对象**和**对象赋值给引用**这两条**指令重排序**后，会出现引用不为null，但是初始化还未完成的情况，导致获取到**半初始化对象**的情况。

### 5. AtomicInteger  

- 使用的原理CAS（Compare And Swap）：是一种**乐观锁**，里面会有一个循环，判断当前变量是否是期望的值，即是否有被其他线程修改过，若**是期望值**，则直接执行操作**修改**，否则，替换期望值为最新的值，再进到循环里判断。
- ABA问题
  - 出现的原理：如int变量被另一个线程从0修改为1再修改回0，对于基本数据类型，ABA问题并不会产生什么问题，但对于引用类型就可能产生问题。
  - 解决办法：添加版本号或时间戳，同时比较值和版本号（时间戳）。

- **LongAdder**
  - AtomicInteger内部只保存一个变量value，对其进行CAS操作。
  - LongAdder内部有一个变量base，还有一个`Cell[] cells`变量，当对base进行CAS操作失败时，则会对cells里的变量进行CAS操作。最后返回结果时，返回的是**cells里的所有变量**以及**base**的和。**效率更高**。

### 6. ReentrantLock  

- 等待队列结构图

  ![ReentrantLock等待队列结构图](AndroidNote.assets/ReentrantLock等待队列结构图.png)

- Condition 本质是不同的等待队列

### 7. CountDownLatch  

- 调用await()，如果count为**0**，则直接**往下执行**，否则等待
- 调用countDown()会将里面存的值减一
- countDown()到0，await()之后的代码才会执行
- 应用：需要几个前置任务都完成后，才能往下执行的情况

### 8. ReentrantReadWriteLock  

### 9. Semaphore  

- 调用acquire()会判断里面存的值，如果值**大于0**，则将里面存的值减一，直接**往下执行**，否则等待
- 调用release()会将值加一
- 跟CountDownLatch 有点相反，这是要大于0，acquire()之后的代码才会执行
- 应用：限流

### 10. CyclicBarrier  

- 跟CountDownLatch类似，等待一定数量的线程执行完，再继续执行

### 11. LockSupport  

- LockSupport.park()：暂停当前线程
- LockSupport.unpark(Thread t)：指定线程继续执行

### 12. 生产者消费者问题

### 13. AQS  

- 概述：AbstractQuenedSynchronizer抽象的队列式同步器。常用的锁ReentrantLock、Semaphore、CountDownLatch等都是基于AQS实现的。AQS主要维护了一个int变量state和一个双向队列，如下图。

  ![AQS框架图](AndroidNote.assets/AQS框架图.png)

- acquire流程

  ![AQS_acquire时序图](AndroidNote.assets/AQS_acquire时序图.png)

- release流程

  ![AQS_release时序图](AndroidNote.assets/AQS_release时序图.png)

- AQS实现

  - 互斥锁子类主要实现tryAcquire和tryRelease方法，对state变量的修改。
  - 若是共享锁，则需实现tryAcquireShared和tryReleaseShared。

- 设计模式

  - 模板方法：acquire、release等核心方法都定义成**final类型的模板**，里面调用的tryAcquire、tryRelease方法**空实现**，留给**子类实现**。

### 14. 并发的三大特性  

- 可见性
- 有序性
- 原子性

### 15. 线程池

- ThreadPoolExecutor类图

  ![ThreadPoolExecutor类图](AndroidNote.assets/ThreadPoolExecutor类图.png)

- 系统的几种线程池

  - newCachedThreadPool：占CPU高，创建的线程是无限的
  - newFixedThreadPool：占内存高，等待队列是无限的
  - newSingleThreadExecutor：占内存高，等待队列是无限的

- `AtomicInteger ctl`：保存线程池运行状态与工作线程数

  - 工作线程数：用**低29位**表示，可通过`workerCountOf(int c)`从ctl中获取

  - 运行状态：用**高3位**表示，可通过`runStateOf(int c)`从ctl中获取

    ```java
    // COUNT_BITS == 29
    RUNNING    = -1 << COUNT_BITS // 可以接收新任务，并且可以处理队列任务
    SHUTDOWN   =  0 << COUNT_BITS // 不可以接收新任务，但可以处理队列任务
    STOP       =  1 << COUNT_BITS // 不可以接收新任务，也不可以处理队列任务
    TIDYING    =  2 << COUNT_BITS // 全部任务已经结束，工作线程数为0，会执行terminated()
    TERMINATED =  3 << COUNT_BITS // terminated()执行完毕
    ```

- 线程池中线程处理executor流程

  - 提交优先级：核心线程->队列->非核心线程

    ![线程池任务提交流程](AndroidNote.assets/线程池任务提交流程图.png)

    ![线程池execute流程](AndroidNote.assets/线程池execute流程图.png)

  - 执行优先级：核心线程->非核心线程->队列

- `addWorker(Runnable firstTask, boolean core)`

  1. **自旋**检查**运行状态**run status，并**自旋**通过**CAS**增加**工作线程数**work count，直到增加成功
  2. 新建Worker，将Task传进去，Worker里面会使用ThreadFactory**创建线程**，**启动线程**
  3. 线程启动后，会在run方法里调用`runWorker(Worker w)`执行任务

- `runWorker(Worker w)`

  - 会开启一个循环，先执行当前任务（这也是Worker的变量叫firstTask的原因），执行完后，会不断的调用`getTask()`从队列中获取任务执行（这是**线程复用**的关键）

- `getTask()`

  - 不断的从任务队列里获取任务
  - 根据`boolean timed = allowCoreThreadTimeOut || wc > corePoolSize`这个变量（允许核心线程超时销毁或当前线程数大于核心线程数），决定从队列获取任务是调用`workQueue.poll()`还是`workQueue.take()`。调用`workQueue.take()`时，若获取不到任务，则线程会等待，这是默认**核心线程不会被销毁的原因**；若是调用`workQueue.poll()`没获取到任务，返回后，`runWorker(Worker w)`里的循环就会结束，然后调用`processWorkerExit()`移除一个Worker。
  
- execute与submit的区别：

  - 没有返回值与有返回值
  - **同步与异步**？？

### 16. AsyncTask

- 封装了**线程池**以及**Handler**，方便处理耗时任务，以及回调更新UI。
- 相关方法
  - `onPreExecute()`：执行后台任务前调用到，可以做一些UI的初始化
  - `doInBackground()`：执行后台任务，该方法是在**子线程**调用。
  - `publishProgress()`：在`doInBackground()`方法中调用该方法，会回调到`onProgressUpdate()`
  - `onProgressUpdate()`：后台任务执行过程中，更新UI进度等。
  - `onPostExecute()`：后台任务执行完毕之后回调。

### 17. HandlerThread

- 继承Thread，并且封装了一个Handler，该handler不是主线程的，它使用的**Looper是HandlerThread的**，因此该handler的**handleMessage()**是在子线程，**可以处理耗时任务**。

- 使用时，必须先创建HandlerThread，并调用`start()`后，才能创建Handler，绑定Looper，因为Looper是在`run()`里创建的。

  ```java
      private void initHandlerThread() {
      	// 这三步顺序不能换
          mHandlerThread = new HandlerThread("线程名");
          mHandlerThread.start();
          mThreadHandler = new Handler(mHandlerThread.getLooper()) {
              @Override
              public void handleMessage(Message msg) {
                  checkForUpdate();
                  if (isUpdate) {
                      mThreadHandler.sendEmptyMessage(MSG_UPDATE_I NFO);
                  }
              }
          };
      }
  ```

- 使用完成后，需要调用`quit()`或`quitSafely()`方法，否则，线程中的`Looper.loop()`会一直运行，导致线程一直运行。

### 18. IntentService

- 在`onCreate()`里会创建一个HandlerThread，并创建对应的**子线程Handler**。
- 调用`startService()`后，会将传进来的Intent封装到Message里，通过handler发送。`startService()`可以**多次调用**。
- 然后在`onHandleIntent（）`里拿到Intent，进行相应的处理，该方法是在**子线程**里，可以做**耗时操作**。
- 所有任务执行完毕后，自动结束service。
- 对比
  - 相比于Service，它是在子线程，可以**执行耗时任务**。并且任务结束后，会**自动结束服务**。
  - 相比于Thread，它是服务，**优先级更高**，不容易被系统杀死。



## RecyclerView  

### 1. 主要的六个类  

- Adapter：创建ViewHolder以及绑定数据。
- ViewHolder：承载ItemView，控制item的显示，也用于回收复用。
- LayoutMananger：负责Item的布局管理，RecyclerView在onLayout里，调用了该类里的`onLayoutChildren()`。
- ItemDecoration：Item添加装饰，如子类DividerItemDecoration实现分隔线，子类ItemTouchHelper可以实现滑动删除等效果。
- ItemAnimator：添加删除Item时的动画。
- Recycler：管理ViewHolder的回收复用。

### 2. 回收复用原理

> 参考资料：[RecyclerView回收和复用机制分析](https://www.jianshu.com/p/467ae8a7ca6e) 

- 四级缓存
  - 屏幕内缓存：屏幕内的View，有数据改变的缓存在**mChangedScrap**、没有改变的缓存在**mAttachedScrap**。
  - 屏幕外缓存：滑动到屏幕外的View，缓存在**mCachedViews**，**默认大小为2**，View的所有属性都保存着（包括position），复用时可直接使用不用调用`onBindViewHolder()`，但是这里的View只有position也是对应的才能复用，其他位置不能复用。
  - 自定义缓存：通过继承ViewCacheExtension实现`getViewForPositionAndType`，自定义缓存。作用在系统缓存池之前。
  - 缓存池缓存：当**mCachedViews**放不下之后，会将它里面最老的放到**RecycledViewPool**里，新的缓存在mCachedViews。RecycledViewPool里维护了一个SparseArray，key为**ViewType**，value为一个ArrayList，**默认每个list大小为5**。即根据类型缓存，默认每种类型缓存5个。可以调用`setMaxRecycledViews(int viewType, int max)`分别设置每种类型的缓存大小。
- 复用
  - `Recycler.getViewForPosition()`：Recycler提供的获取**复用**View的方法，在LayoutManager的`fill()`方法里调用。
  - **`tryGetViewHolderForPositionByDeadline()`**：View复用的**核心方法**。
    1. `getChangedScrapViewForPosition()`：从**mChangedScrap**里获取，一般在adapter调用**`notifyItemChanged()`**等方法时会用到该缓存。
    2. `getScrapOrHiddenOrCachedHolderForPosition()`
       1. 先从**mAttachedScrap**里获取，这个缓存里的view没有数据改变，不会重走`onBindViewHolder()`
       2. `mChildHelper.findHiddenNonRemovedView()`：从mHiddenViews里获取，这是没有被回收，但是看不见的View
       3. 从**mCachedViews**里获取，这是**真正被回收了**的View的**第一级**缓存
    3. `getScrapOrCachedViewForId()`：跟`getScrapOrHiddenOrCachedHolderForPosition()`方法基本相同，只是这个是根据**ItemId**从**mAttachedScrap**和**mCachedViews**里面获取，调用这个方法有一个**前提**是，要设置mHasStableIds为true，并且adapter里要重写`getItemId()`.
    4. `getRecycledViewPool().getRecycledView(type)`：从**RecycledViewPool**里获取，先根基ViewType找到对应的缓存，再从中获取最后一个。
    5. RecycledViewPool也没获取到，就会调用`Adapter.onCreateViewHolder()`**创建新的ViewHolder**。
    6. 在一定条件`(!holder.isBound() || holder.needsUpdate() || holder.isInvalid())`下，去调用adapter的`onBindViewHolder()`。如：mChangedScrap里获取的、RecycledViewPool里获取的、onCreateViewHolder新创建的。
- 回收

  - `Recycler.recycleView()`：Recycler提供的获取**回收**View的方法，在LayoutManager的`fill()`方法里调用。
  - **`recycleViewHolderInternal()`**:View回收的**核心方法**。
    1. 将ViewHolder放到**mCachedViews**里：如果这里已满，要先将里面最老的一个移除，放到**RecycledViewPool**里，再将新的放到**mCachedViews**里。
    2. 如果**mViewCacheMax小于0**，则直接将ViewHolder放到**RecycledViewPool**里。

  - `addViewHolderToRecycledViewPool()`：将ViewHolder放到**RecycledViewPool**里的方法。
    1. `dispatchViewRecycled(holder)`：这里会触发`onViewRecycled()`方法。
    2. `getRecycledViewPool().putRecycledView(holder)`：获取ViewType，找到对应的缓存，若缓存已满，直接return，否则，将holder的一些状态等清除，再放到该缓存里。

### 3. 用到的设计模式

- 适配器模式
  - 适配的是不同类型的数据（被适配者）。
  - Adapter中统一接口，对不同类型都通过`bindViewHolder()`绑定到holder上。
  - 这其实**不是**一个**标准**的适配器模式，它没有对接口进行转换，但其思想还是同适配器模式的。
- 观察者模式
  - Adapter内部有一个AdapterDataObservable **mObservable**变量，继承自**Observable\<AdapterDataObserver\>**，作为**被观察者**。
  - 调用`setAdapter()`时，会为mObservable注册**观察者**RecyclerViewDataObserver **mObserver**，继承自**AdapterDataObserver**。
  - 在调用adapter的`notifyDataSetChanged()`、`notifyItemXXX()`这些方法时就会调用**mObservable**的对应notify方法，来通知**mObserver**。
  - 这个观察者模式还使用了**泛型**，Observer类型可以通过泛型指定，相比普通的观察者模式更加灵活。

### 4. 优化  

- `RecyclerView.setHasFixedSize(true)`
  - **适用条件**：RecyclerView的**大小固定**，不会随adapter的数据改变而改变时，可以设置该变量为true。
  - **原理**：当调用`notifyItemXXX()`这些方法（调`notifyDataSetChanged()`无效），会去判断mHasFixedSize等变量，满足条件则，不会去调用`requestLayout()`，实现优化。

- 复用RecycledViewPool
  - 适用条件：多个RecyclerView有相同的ViewHolder时，可设置共享的RecycledViewPool
  - 原理：手动开启这个特性：**layout.setRecycleChildrenOnDetach(true)**后，当Item从RecyclerView上detach时，就会立刻被回收到RecycledViewPool里，这样另外一个RecyclerView就可以直接复用了。

- `LinearLayoutManager.getExtraLayoutSpace()`预加载额外的空间
  - 适用条件：根据实际情况，需要提前加载的情况。
  - 原理：重写getExtraLayoutSpace方法，返回需要加载的额外空间，就可以提前加载，在屏幕外不可见的View。但也不能设置太大，否则会**占用大量内存**，也会**降低性能**。

- `setItemViewCacheSize()`：设置**mCachedViews**的大小
  - 适用条件：设置多大合适？设置**一行的大小**，这样来回滚动的时候，效率会比较高。设太大，内存占用会过大。
  - 原理：增加**mCachedViews**缓存的大小，该缓存会保存holder的所有信息，复用时可直接使用，不用再'bindViewHolder()'。

- `onBindViewHolder ()`里尽量减少对象创建、减少不必要的代码，因为该方法会经常调用到。
- 滑动时，停止图片的加载，滑动停止后再加载。



## ViewPager+Fragment  

- `populate(mCurItem)`



## Binder机制  

### 1. 基本概念  

- 解决的问题：不同进程之间，拥有**不同**的**用户空间**，无法直接进行通信。
- 解决的方法：不同进程之间，拥有**共同**的**内核空间**，可以将一个进程的请求打包，通过内核空间，传递到另一个进程，在另一个进程获取到请求的内容，通过调用进程本地的方法，获取结果，将结果打包，通过内核空间返回给请求的进程。

### 2. 绑定服务原理  

- 绑定服务的整体流程图

  ![bindService流程图](AndroidNote.assets/bindService流程图.png)

- 主要组成部分

  - Client：客户端，请求服务，想要调用服务端的方法。
  - Service：服务端，提供服务，给客户端返回数据。
  - ServiceManager：提供注册、获取系统服务的服务。
  - ActivityManagerService（AMS）：管理Activity、Service等组件的运行状态。

- 绑定服务的流程分析

  ![bindService时序图](AndroidNote.assets/bindService时序图.png)

  - `ContextImpl.bindService()`：通过ServiceManager获取到AMS服务，然后调用`AMS.bindService()`。
  - `AMS.bindService()`：调用到`ActivityThread.ApplicationThread.scheduleBindService()`，在该方法里调用ActivityThread**内部Handler（H类）**对象发送消息`H.BIND_SERVICE`，最后在`handleBindService()`里，真正创建IBinder对象。
  - `ActivityThread.handleBindService()`：首先从**mServices**（service创建后，会保存在该集合里）里获取到对应的service，然后调用**`service.onBind()`方法**获取到Service里返回的**IBinder对象**，最后调用`AMS.publishService()`方法。
  - `AMS.publishService()`：会调用到`LoadedApk.ServiceDispatcher.InnerConnection.connected()`方法，这里回调到**`ServiceConnection.onServiceConnected()`方法**，返回**IBinder对象service**。

### 3.Binder调用流程  

- bindService成功后，会在**`ServiceConnection.onServiceConnected()`方法**里返回**IBinder对象service**。
- 首先调用对应接口的`Stub.asInterface(service)`，就可以返回的IBinder对象转换成服务的**Proxy对象**，通过该对象就可以像请求本地方法一样请求远程方法了。
- 调用Proxy对象的方法时，会将对应方法的一个code，参数等，通过调用**`mRemote.transact()`传到服务端**，服务端在**`onTransact()`**解析传过来的数据，然后调用对应的方法，并将返回值写到reply对象里返回。



## 图片加载框架  

![图片加载框架对比](AndroidNote.assets/四大图片框架对比.png)

### 1. Fresco  

- 特点：
  - 图片不显示时，占用的内存自动释放
  - 渐进式呈现图片
  - 支持gif、webp
  - 本地缓存
  - 加载完成回调

- 缓存

### 2. Glide  

- 特点

  - 绑定生命周期：调用`Glide.with(this)`方法时，会创建一个**无UI的Fragment**，该Fragment持有LifeCycle，通过LifeCycle通知生命周期的变化。
  - 自动调整图片大小：根据ImageView的大小，自动压缩图片像素，再给到ImageView显示，可以有效的**降低内存占用**。不想自动调整，也可通过`override(width, height)`方法固定图片的大小。

- Glide流程

  > 参考资料：[Android图片加载框架解析（二），从源码的角度理解Glide的执行流程（上）](https://juejin.cn/post/6913799833841041422)
  >
  > [Android图片加载框架解析（二），从源码的角度理解Glide的执行流程（中）](https://juejin.cn/post/6913806249667035144)
  >
  > [Android图片加载框架解析（二），从源码的角度理解Glide的执行流程（下）](https://juejin.cn/post/6913813341790011405)

  1. `Glide.with(context)`流程

     - 根据传进来的context，主要分为两种情况，context为**Application对象**（主动传的Application或者是在**非主线**程调用的）和**非Application对象**，结果都是**返回RequestManager**。

       - context为**Application对象**：调用`getApplicationManager()`，直接通过RequestManagerFactory返回RequestManager对象，该对象是通过**双重检查单例模式**返回的**单例对象**。

       - context为**非Application对象**：创建一个**无UI的Fragment**，并绑定生命周期，然后返回RequestManager。

  2. `RequestManager.load()`流程

     - 先调用`asDrawable()`返回RequestBuilder\<Drawable\>对象，再调用该对象的`loadGeneric()`方法。

     - `loadGeneric()`只是设置model为传进来的url参数，并返回RequestBuilder对象。

  3. `RequestBuilder.into()`流程（Glide的主要流程）

     ![Glide_into时序图](AndroidNote.assets/Glide_into时序图.png)

     - `RequestBuilder.buildRequest()`：创建请求，然后调用runRequest，**执行请求**。
      - `Engine.load()`：从缓存或网络中获取图片。
      - `HttpUrlFetcher.loadData()`：真正请求网络下载图片，通过**HttpURLConnection**。
      - `Downsampler.decode()`：解析请求回来的图片流，对图片的**压缩**，甚至还有**旋转**、**圆角**等逻辑处理都在这里。
      - `ImageViewTarget.onResourceReady()`： 图片处理完成后，会回调到该方法，然后再这里调用setResource()，真正**将资源设给ImageView**。

- 缓存机制

  > 参考资料：[Android图片加载框架最全解析（三），深入探究Glide的缓存机制](https://juejin.cn/post/6914213320182464526)

  1. 缓存Key

     - 主要包含，请求图片的地址、宽高、类型等8个参数。

  2. 内存缓存

     - 默认开启，可以通过`skipMemoryCache(true)`禁用该缓存，一般不要设。

     - LruResourceCache实现缓存

     - 获取内存缓存的流程

       ![Engine_load内存缓存流程](AndroidNote.assets/Engine_load内存缓存流程.png)

  3. 硬盘缓存

     - 四种缓存类型
       - DiskCacheStrategy.NONE： 表示不缓存任何内容。
       - DiskCacheStrategy.SOURCE： 表示只缓存原始图片。
       - DiskCacheStrategy.RESULT： 表示只缓存转换过后的图片（**默认选项**）。
       - DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。

     - 获取硬盘缓存的流程

       ![Engine_load硬盘缓存流程](AndroidNote.assets/Engine_load硬盘缓存流程.png)



## 网络框架  

### 1. OkHttp  

- OkHttpClient：客户端，用来创建Call，以及维护了dispatcher、connectionPool、interceptors、cache等重要模块。

  - 保持单例：OkHttpClient对象应该保持**单例**，因为该对象有**连接池**connectionPool、dispatcher（里面有**线程池**executorService）等应该重复利用的资源，保持单例可以**减少内存占用**，提高性能。
  - 创建方式：直接new或通过OkHttpClient.Builder对象创建，通过builder对象创建，可以自定义一些配置，如拦截器、缓存等。也可通过`client.newBuilder().build()`方法创建，这样虽然**不是单例**，但是连接池、线程池等配置，都是**复用**的.

- Dispatcher：分发器，主要在**异步请求**过程中，对请求进行队列以及线程池操作。

- Interceptor ：拦截器，**责任链模式**

  > 参考资料：[你该知道的okhttp3拦截器的那些事](https://blog.csdn.net/c10wtiybq1ye3/article/details/101398117/)

  1. RetryAndFollowUpInterceptor：开启一个**循环**，负责**失败重试和重定向**。
  2. BridgeInterceptor：主要处理**请求头**和**响应头**。
  3. CacheInterceptor：
     - Cache：缓存实现，`client.cache`会传到这里，里面具体实现是一个**DiskLruCache**。
     - CacheStrategy：缓存策略，通过`CacheStrategy.Factory(now, request, cacheResponse).compute()`获取到具体的策略，在该方法中，主要通过request和cacheResponse对象，以及服务器的HTTP缓存策略，来最终决定使用哪种策略。

  ![缓存策略](AndroidNote.assets/okhttp缓存策略.png)

  4. ConnectInterceptor：建立连接，初始化了一个Exchange，传给下一个Interceptor。
     - Exchange：里面持有ExchangeFinder对象，而ExchangeFinder对象持有RealConnectionPool对象，因此Exchange主要负责管理**连接池**，以及发送请求获取结果等。
     - RealConnection：连接实现，该对象是被RealCall持有，连接的真正实现，实现了Connection接口，内部利用**Socket**建立连接。
  5. CallServerInterceptor：真正请求数据，在ConnectInterceptor中已经建立了连接，这里只要将请求信息通过exchange发送给服务器，然后获取到返回结果。

- `enqueue()`流程

  - `RealCall.enqueue(Callback)`：调用后会用传进来的Callback对象，创建一个AsyncCall，转给`Dispatcher.enqueue(AsyncCall)`处理。
  - `Dispatcher.enqueue(AsyncCall)`：首先将AsyncCall**放到readyAsyncCalls队列**里，然后调用`promoteAndExecute()`从队列里获取请求并执行。
  - `Dispatcher.promoteAndExecute()`：**遍历readyAsyncCalls**，根据**两个条件判断**是否将遍历到的AsyncCall从readyAsyncCalls里拿出来，放到runningAsyncCalls里，并调用`asyncCall.executeOn(executorService)`给到线程池executorService去执行。
    - 两个判断条件
      1. `(runningAsyncCalls.size >= this.maxRequests)`：当前执行的请求已经大于等于maxRequests（默认64），直接**结束遍历**。
      2. `(asyncCall.callsPerHost.get() >= this.maxRequestsPerHost)`：当前执行的请求相同host的个数已经大于等于maxRequestsPerHost（默认5），**跳过当前遍历**，遍历下一个请求。

  - `asyncCall.executeOn(executorService)`：放到线程池后，就会执行asyncCall的**`run()`方法**，在该方法里，会调用`getResponseWithInterceptorChain()`去获取到请求结果。
  - `getResponseWithInterceptorChain()`：在该方法里，经过拦截器的处理后，**返回请求结果**。
  - `dispatcher.finished(AsyncCall)`：最后执行完请求后，**将该请求移除**，并调用`promoteAndExecute()`去**取下一个请求**。

- `execute()`流程
  - `dispatcher.executed(RealCall)`：将请求放到runningSyncCalls里。
  - `getResponseWithInterceptorChain()`：获取请求结果。
  - `dispatcher.finished(RealCall)`：最后执行完请求后，**将该请求移除**，并调用`promoteAndExecute()`去**取下一个请求**。

- `enqueue()`和`execute()`对比
  - `execute()`调用后，会直接运行`getResponseWithInterceptorChain()`获取请求结果。
  - `enqueue()`调用后，要通过Dispatcher先放到**等待队列**里，然后从等待队列里找到符合条件的请求，放到**运行队列**里，并交给**线程池执行**，才能获取到请求结果。

### 2. Retrofit  

- Retrofit是对OkHttp的封装
  - 请求的封装：写一个接口，接口里的方法加上注解，来添加请求的地址、参数等。
  - 结果的封装：通过addConverterFactory，可以添加一个factory（如GsonConverterFactory）来实现，请求结果的转换。
- Retrofit对象主要变量
  - `callFactory = new OkHttpClient()`：是一个OkHttpClient对象，用于创建RealCall
  - `callbackExecutor = platform.defaultCallbackExecutor()`：在Android平台下内部是一个Handler，用于将回调发送到**主线程**。
  - `callAdapterFactories`：默认会添加一个DefaultCallAdapterFactory，**解析注解**的时候，会根据returnType，调用`DefaultCallAdapterFactory.get()`，获取到一个CallAdapter。调用**invoke方法**时，会调用CallAdapter的**adapt方法**，返回一个封装过的Call对象。
  - `converterFactories`：使用较多的是**GsonConverterFactory**，可以将Bean对象转换成请求RequestBody，也可将响应ResponseBody转换成Bean对象。
- 主要流程
  - `retrofit.create(Interface.class)`：将接口通过**动态代理**，转换成一个该接口的代理类对象返回。
  - 调用代理对象的接口方法：会调用到代理对象里的InvocationHandler对象的**`invok()`**方法，在该方法里会**解析**对应接口方法上的**注解**等内容，并返回一个经过CallAdapter**封装过的Call对象**。
  - 获得Call对象后，就可以调用`execute()`或`enqueue()`方法执行请求了。
  - 请求的响应对象，会经过ConverterFactory转换封装后再返回，一般是转成Bean对象。

- 用到的设计模式
  - Builder模式：Retrofit.Builder、Request.Builder。
  - 动态代理模式：请求接口动态转换成代理类。
  - 工厂模式：`CallAdapter.Factory.get()`根据返回值类型等获取对应的CallAdapter、`Converter.Factory.requestBodyConverter()/responseBodyConverter()`根据要转换的数据类型获取对应的Converter。
  - 适配器模式：`CallAdapter.adapt()`将Call对象，适配成其他类型的对象，如ExecutorCallbackCall，在Android中会将请求结果通过Handler发送到主线程。
  - 策略模式：`Retrofit.Builder().addConverterFactory()/addCallAdapterFactory()`设置不同的ConverterFactory/CallAdapterFactory，相当于设置不同的获取Converter/CallAdapter的策略。



## RxJava  

- 特点：响应式编程、方便线程切换
- 设计模式：观察者、装饰者

## EventBus  

- 特点：在不同组件之间方便的发送接收**任意类型**的消息
- 原理：通过**注解**将接收消息的方法都存在一个**map里**，发送消息后，根据参数的类型，查找对应的方法，进行**反射**调用。

## 组件化  

## 插件化

## 热修复

## MVVM  

## 性能优化  

- 布局优化
  - 过渡渲染：去除不必要的背景、降低嵌套层级、自定义View中绘制时裁剪掉不可见区域
  - 使用include、merge、ViewStub：实现布局复用、减少层级、懒加载
  - GPU耗时：减少onMeasure、onDraw等方法耗时
- 内存优化
  - 内存抖动：大量对象的创建回收，gc频繁。
    - 如onDraw、onMeasure等**频繁调用**的方法中创建对象---------将方法内部对象改成成员变量，在其他地方创建
  - 内存泄漏：长生命周期对象，持有短生命周期对象的引用，导致短生命周期对象无法释放。
    - 集合类：全局性的集合变量，只添加不删除，就会导致所占内存越来越大。
    - 匿名内部类、非静态内部类：如handler发送延时消息，匿名内部类会默认持有外部类的引用------使用静态内部类、使用弱引用，退出时取消消息
    - 单例、静态变量：如果有用到Context，要使用Application的，不要使用Activity的
    - 资源型对象没有关闭：如IO流、数据库Cursor等
  - 减少内存占用：SparseArray代替HashMap等、尽量减少static成员变量的使用
  
- 启动优化
  - 冷启动
  - 启动白屏：设置带背景的主题、添加启动页

## IPC方式

1. Intent
2. 文件共享
3. Messenger
4. AIDL
5. ContentProvider
6. Socket

## 问题  

滑动冲突

service生命周期、如何结束

ANR 实例

TouchEvent、onclick

fragment的使用

帧动画，内存问题

- 使用SurfaceView，并且逐帧解析

  > 参考资料：[帧动画内存OOM？不存在的！—— SurfaceView逐帧解析](https://segmentfault.com/a/1190000019140030?utm_source=tag-newest)

Glide 圆角、gif

- 继承BitmapTransformation，在transform方法里对Bitmap处理，实现圆角

  > 参考资料：[Android Glide自定义圆角处理](https://blog.csdn.net/shuai497331206/article/details/105449897/)

mvp 生命周期回调

mvp的缺点

性能优化，具体哪些方法耗时

activity回收后，恢复List数据

onCreateViewHolder，耗时的点

滑动到一半，Fragment是否是onResume状态

弹Dialog时，系统如何知道Dialog是否全屏

http

surface flinger

广播：除了BroadcastReceiver，还有啥？

onCreate方法里能否取得wrap_content的View的确定尺寸

okhttp的优缺点、动态代理解析结果缓存的key和Value分别是什么

RecyclerView的自定义缓存为什么没人用

Glide的缓存有几级？