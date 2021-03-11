1.大纲

### 总结
JMM的内存模型

- volatile关键字的作用,怎么用
    - 可见性
    - 不保证原子性
    解决就是使用原子引用类
    - 禁止重排序
- volatile的底层原理是CAS
compareAndSwap 比较并交换
- 是否可以手写一个自旋锁?
CAS的底层原理是?
    1.计算机原语
    2.unsafe类
    3.compareAndGet
    
- 单例模式下的线程安全问题以及解决
    - 使用的双重检索机制. synchronize进行同步. 
Cas的缺点
CAS问题  
    ABA问题  ,也就是自旋锁的过程, 线程B一直等待A ,但是A修改了两次值, 刚好B线程又修改成功了. 
所以解决ABA的问题就是使用了带版本号原子引用类.

- 第2 部分是将锁的部分
- 公平锁, 非公平锁 ,可重入锁,自旋锁,平衡锁等
- reentrylock的原理

线程通信工具类
-   countdownlatch 就是递减的一个过程 . 每个任务执行到的时候countdown一下 . 最后集中的时候await
-   cyclicBarier 就是递增的一个过程 . 每个任务等待await  . 最后够数的时候一起执行. 
- semaphore 就是信号量的例子, 可以替换synchronize 和 其中有acquire  and realese方法

synchronize和reentrylock的区别
    - 1  A是jvm层面   B是JUC的API层面
    - 2. A是不可以中断  B可以是interrupt
    - 3. A是自动释放锁     B是手动释放锁的
    - 4. 通知所有的线程   B可以精确通知其他线程

- 使用lock的方式实现生产消费者模式的代码
    - 传统的方式实现生产者消费模式
    - 使用了Blockqueue的方式实现生产消费模式

- 枚举类的使用 ,能够编写枚举类的使用

- 用代码实现一个需求:
    A 线程生产一个消息  B 消费一个消息  5秒  , main线程暂停. 
- 阻塞队列的架构体系. 除了用List 还有 了哪些
- 不安全的集合类有哪些
    - ArrayList  CopyOnWriteList
    - HashMap   ConcurrentHashMap
    - HashSet   
    
### 阻塞队列原理
- 集成体系是什么
- 为什么有blockqueue
- 有几个queue 常用的
    - ArrayBlockQuere
    - LinkBlockQueue  
    - AsynBlockQueue  (一个只有一个,定制版)
- block操作的企业使用
    插入使用的是带过期时间的offer(key,1,timeunit)
    获取使用的是poll(1,timeunit)
    
- 生产者消费模式 , MQ的底层原理

### 线程池
实现多线程的多种方式
- CallAble为什么没有提供Thread的构造参数   futureTask 实现了Runable也是实现了Callable . 带返回值的多线程
- CallAble的使用方式  . 装饰者模式的设计, 所以不需要提供一个Thread的构造函数 . 通过get可以获取返回值和异常

线程池的种类
    - fix   固定
    - single 一池 1 
    - cache  一池N
    
线程池的使用
线程池的7大参数
    - corePoolSize
    - maxPoolSize
    - keepAlieve
    - timeunit
    - blockqueue
    - threadFactory
    - rejectExececptionHandle  拒绝策略
线程池的底层工作原理是什么?



### 阻塞 BlockingQueue 好处就是不用管阻塞和唤醒,实际上就是自动挡和手动挡的区别
- countdownlatch用法
    班长关门的例子，保证最后一个人走
    
- java基础练习部分
https://www.runoob.com/java/java-basic-syntax.html
    - base  菜鸟编程系列
    - 序列化和transient的使用 
- Callable的使用方式  2021/3/7
- 手写一个阻塞队列,写一个生产者和消费者, 这个版本.MQ的底层原理  2021/3/6

