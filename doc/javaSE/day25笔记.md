#25.01_多线程(单例设计模式)(掌握)
单例设计模式:保证类在内存中只有一个对象,
如何保证类在内存中只有一个对象呢?

    (1)控制类的创建,不让其他类来创建本类的对象,即private私有构造方法
	(2)在本类中定义一个本类的对象,Singleton s;
	(3)提供公共的访问方式,public static Singleton getInstance(){return s}
	
单例写法两种:

	(1)饿汉式,开发用这种方式:
代码演示如下:
```
//饿汉式
class Singleton {
	//1,私有构造函数
	private Singleton(){}
	//2,创建本类对象
	private static Singleton s = new Singleton();//私有不让通过类名调用成员变量修改
	//3,对外提供公共的访问方法
	public static Singleton getInstance() {
		return s;
	}
	
	public static void print() {
		System.out.println("11111111111");
	}
}
```

    (2)懒汉式,面试写这种方式,但是要加同步代码块或者加同步关键字synchronized来解决多线程的安全性问题,优化如下:
代码演示如下:
```
//懒汉式,也叫单例的延迟加载模式:
class Singleton {
	//1,私有构造函数
	private Singleton(){}
	//2,声明一个本类的引用
	private static Singleton s;//下面调用方法是静态,所以被调用的成员也要是静态,静态调用静态
	//3,对外提供公共的访问方法
	public static synchronized Singleton getInstance() {//因为构造私有所以要加静态类名调用
		if(s == null)
			//线程1,线程2
			s = new Singleton();
			
		return s;
	}
	
	public static void print() {
		System.out.println("11111111111");
	}
}//由于空间所限,懒汉式即延迟加载的单利设计模式不加同步会出现多线程的安全性问题代码演示放到最后!
```

    (3)第三种格式,其实就是后面学的用自定义枚举类的成员变量是本类实例,并且实例个数为1的单实例情况,而多实例是枚举类:
代码演示如下:
```
class Singleton {
	private Singleton() {}
	//final是最终的意思,被final修饰的变量不可以被更改
	public static final Singleton s = new Singleton();//把私有改公有,加上final不可修改,
	
    public static void print() {//改成公有是可以让其他类通过类名调用,私有其他类看不到成员变量s
    		System.out.println("11111111111");
    }
}
```
总结:饿汉式和懒汉式的区别:
1,饿汉式是牺牲空间换取时间,懒汉式是牺牲时间换取空间
2,在多线程访问时,饿汉式不会创建多个对象,而懒汉式有可能会创建多个对象

#25.02_多线程(Runtime类)
Runtime类是一个单例类:
代码演示如下:
```
public static void main(String[] args) {
	Runtime r = Runtime.getRuntime();//看源码知道是用的单例设计模式的饿汉式,直接创建对象
	//r.exec("shutdown -s -t 300");//300秒后关机
	r.exec("shutdown -a");//取消关机
}

Runtime类源码如下:
public class Runtime {
    private static Runtime currentRuntime = new Runtime();//单例设计模式的饿汉式,直接创建对象
    public static Runtime getRuntime() {
        return currentRuntime;//提供公有方法得到对象引用
    }
    private Runtime() {}//私有构造,不让其他类创建本类对象
}
```

#25.03_多线程(Timer)(掌握)
Timer类:计时器:
代码演示如下:
```
public class Demo3_Timer {
	public static void main(String[] args) throws InterruptedException {
	    Timer t = new Timer();
		//在指定时间安排指定任务,
		//第一个参数,是安排的任务,第二个参数是执行的时间,第三个参数是过多长时间再重复执行,
		t.schedule(new MyTimerTask(), new Date(116, 4, 30, 17, 41, 00),3000);	
		//年份为当前减去1900年,月份从0开始的,传入4代表当前是5月↑
		while(true) {
			Thread.sleep(1000);
			System.out.println(new Date());//每隔一秒输出当前时间,以便于查看是否到达指定时间
		}
	}
}

class MyTimerTask extends TimerTask {
	@Override
	public void run() {
		System.out.println("起床背英语单词");
	}
}
```

#25.04_多线程(两个线程间的通信)(掌握)
1.什么时候需要通信:

    多个线程并发执行时,在默认情况下CPU是随机切换线程的,
    如果我们希望他们有规律的执行,就可以使用通信,例如让每个线程执行一次打印
    
2.怎么通信:

    如果希望线程等待,就调用wait(),释放锁,
	如果希望等待的线程唤醒,就调用notify();
	注意这两个方法必须在同步代码中执行,并且使用同步锁对象来调用,这两个前提条件要特别注意,否则运行报异常!
代码演示如下:
```
public class Demo1_Notify {
	//等待唤醒机制,让两个线程交替输出黑马程序员和传智播客
	public static void main(String[] args) {
		final Printer p = new Printer();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print1();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print2();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

//等待唤醒机制,让两个线程交替输出黑马程序员和传智播客
class Printer {
	private int flag = 1;//起始标记为1,让其他不等于1的线程等待,然后改变标记,最后唤醒等待的线程来实现交替!
	public void print1() throws InterruptedException {		
		synchronized(this) {//这里this可以是同一把锁,因为final Printer p = new Printer();只创建一次!
			if(flag != 1) {
				this.wait();//当前线程等待,释放锁,方法必须在同步代码中执行,用同步锁对象来调用
			}
			System.out.print("黑");
			System.out.print("马");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.print("\r\n");
			flag = 2;//标记控制,让黑马程序员和传智播客交替打印,做完事后标记控制,来达到释放锁条件
			this.notify();//随机唤醒单个等待的线程,方法必须在同步代码中执行,用同步锁对象来调用
		}
	}
	
	public void print2() throws InterruptedException {
		synchronized(this) {//这里this可以是同一把锁,因为final Printer p = new Printer();只创建一次!
			if(flag != 2) {
				this.wait();//当前线程等待,释放锁,方法必须在同步代码中执行,用同步锁对象来调用
			}
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
			flag = 1;//成本变量标记改变,让其他线程等待!
			this.notify();//随机唤醒单个等待的线程,方法必须在同步代码中执行,用同步锁对象来调用
		}
	}
}
```

#25.05_多线程(三个或三个以上间的线程通信)
多个线程通信的问题:

    notify()方法是随机唤醒一个线程,
	notifyAll( )方法是唤醒所有线程,
	JDK5之前无法唤醒指定的一个线程,(JDK5之后有新特性即下个知识点的互斥锁的条件调用方法来解决唤醒指定线程)
	如果多个线程之间通信,需要使用notifyAll()通知所有线程,用while来反复判断标记条件,而不再用if
代码演示如下:
```
public class Demo2_NotifyAll {
	//三个或三个以上间的线程通信,让三个线程交替打印输出语句内容:
	public static void main(String[] args) {
		final Printer2 p = new Printer2();
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print1();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print2();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print3();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

//三个或三个以上间的线程通信,让三个线程交替打印输出语句内容:
class Printer2 {
	private int flag = 1;
	public void print1() throws InterruptedException {			
		synchronized(this) {
			while(flag != 1) {
				this.wait();//当前线程等待
			}
			System.out.print("黑");
			System.out.print("马");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.print("\r\n");
			flag = 2;
			//this.notify();//随机唤醒单个等待的线程,配套if语句判断标记
			this.notifyAll();//唤醒所有线程
		}
	}
	
	public void print2() throws InterruptedException {
		synchronized(this) {
			while(flag != 2) {
				this.wait();//线程2在此等待
			}
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
			flag = 3;
			//this.notify();
			this.notifyAll();
		}
	}
	
	public void print3() throws InterruptedException {
		synchronized(this) {
			while(flag != 3) {
				this.wait();//线程3在此等待,用if语句是在哪里等待,就在哪里起来,交替输出有问题
					   //while循环是循环判断,每次都会判断标记,所以改为while循环
			}
			System.out.print("i");
			System.out.print("t");
			System.out.print("h");
			System.out.print("e");
			System.out.print("i");
			System.out.print("m");
			System.out.print("a");
			System.out.print("\r\n");
			flag = 1;
			//this.notify();//随机唤醒单个等待的线程,有可能唤醒线程2,或者线程1,造成没有交替输出,
			this.notifyAll();//所以改为唤醒所有线程,并重新判断标记改为while语句
		}
	}
}
```
总结:跟两个线程间的通信相比,无非改了标记判断语句为while,还有唤醒用的是锁对象调用notifyAll()方法而不再是notify

    另外,线程间的通信注意的问题如下:
    1,在同步代码块中,用哪个锁对象,就用哪个锁对象调用wait方法
    
    2,为什么wait方法和notify方法定义在Object这类中?
    答:因为锁对象可以是任意对象,Object是所有的类的基类,所以wait方法和notify方法需要定义在Object这个类中
    
    3,sleep方法和wait方法的区别?
    答:第一,sleep方法必须传入参数,参数就是时间,时间到了自动醒来,
    而wait方法可以传入参数也可以不传入参数,传入非0参数就是在参数时间结束后结束等待,不传入参数或者0就是直接等待;
    其实,wait()方法的源码调用的就是wait(0)直接等待: public final void wait() throws InterruptedException {wait(0);}
    
    第二,sleep方法在同步函数或同步代码块中,不释放锁,睡着了也抱着锁睡,
    而wait方法在同步函数或者同步代码块中,释放锁

#25.06_多线程(JDK1.5的新特性互斥锁)(掌握)
1.同步:

    使用ReentrantLock类的lock()和unlock()方法进行同步,(两个方法的调用代码之间相当于同步代码块的作用)
    
2.通信:

    使用ReentrantLock类的newCondition()方法可以获取Condition对象,
	需要等待的时候使用Condition的await()方法,唤醒的时候用signal()方法,
	不同的线程使用不同的Condition,这样就能区分唤醒的时候找哪个线程了,就不用特意用while来判断标记用if也行:
代码演示如下:
```
public class Demo3_ReentrantLock {
    //线程间的通信,用JDK1.5的新特性互斥锁来解决同步与等待唤醒机制:
	public static void main(String[] args) {
		final Printer3 p = new Printer3();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print1();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print2();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						p.print3();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

//线程间的通信,用JDK1.5的新特性互斥锁来解决同步与等待唤醒机制:
class Printer3 {
	//成员位置,一个互斥锁对象创建三个不同条件:
	private ReentrantLock r = new ReentrantLock();
	private Condition c1 = r.newCondition();
	private Condition c2 = r.newCondition();
	private Condition c3 = r.newCondition();
	
	private int flag = 1;//成员其实标记依然为1,让不为1的线程等待!
	public void print1() throws InterruptedException {							
		r.lock();//获取锁与释放锁之间相当于同步代码块:
			if(flag != 1) {
				c1.await();//条件1等待方法,让线程等待
			}
			
			System.out.print("黑");
			System.out.print("马");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.print("\r\n");
			
			flag = 2;
			//this.notify();//随机唤醒单个等待的线程
			c2.signal();//条件2信号方法,唤醒指定线程,所以用if语句就可以了
		r.unlock();//释放锁
	}
	
	public void print2() throws InterruptedException {
		r.lock();
			if(flag != 2) {
				c2.await();//条件2等待方法,让线程等待
			}
			
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
			
			flag = 3;
			//this.notify();
			c3.signal();//条件3信号方法,唤醒指定线程,所以用if语句就可以了
		r.unlock();
	}
	
	public void print3() throws InterruptedException {
		r.lock();
			if(flag != 3) {
				c3.await();//条件3等待方法,让线程等待
			}
			
			System.out.print("i");
			System.out.print("t");
			System.out.print("h");
			System.out.print("e");
			System.out.print("i");
			System.out.print("m");
			System.out.print("a");
			System.out.print("\r\n");
			
			flag = 1;
			c1.signal();//条件1信号方法,唤醒指定线程,所以用if语句就可以了
		r.unlock();
	}
}
```
    总结:无非改了用一个互斥锁对象创建三个不同条件,然后用条件方法等待唤醒,用互斥锁调用lock方法和unlock方法之间代码
    来替代同步代码块,并且标记判断又改回了用if语句,跟两个线程间的通信一样了,而不再是三个线程间通信的while语句,
    换言之,用while语句判断标记时用notifyAll方法,if语句判断标记时用notify方法时多个线程时其他线程有可能没有唤醒,
    所以推荐用while语句判断标记时用notifyAll方法或者用if语句和JDK1.5的新特性互斥锁创建条件对象c2等调用signal()方法,
    不同条件对象调用signal方法,唤醒指定用同一条件等待的线程,谁调用我,我就唤醒谁,所以用if语句就可以了

#25.07_多线程(线程组的概述和使用)(了解)
A:线程组概述:

    Java中使用ThreadGroup来表示线程组,它可以对一批线程进行分类管理,Java允许程序直接对线程组进行控制:
	默认情况下,所有的线程都属于主线程组:
	    public final ThreadGroup getThreadGroup()//通过线程对象,获取他所属于的组,
		public final String getName()//然后通过线程组对象,获取组的名字
	我们也可以给线程设置分组:
		1,ThreadGroup(String name)//创建线程组对象,并给其赋值名字
		2,创建线程对象,
		3,Thread(ThreadGroup group, Runnable target, String name)//线程构造,传入所属的线程组
		4,设置整组的优先级或者守护线程
		
B:案例演示:

    线程组的使用,默认是主线程组:↓
代码演示如下:
```
public static void main(String[] args) {
	MyRunnable mr = new MyRunnable();
	Thread t1 = new Thread(mr, "张三");
	Thread t2 = new Thread(mr, "李四");
	
	//获取线程组,用线程类里面的方法:public final ThreadGroup getThreadGroup():
	ThreadGroup tg1 = t1.getThreadGroup();//线程对象调用方法,获取其所在的线程组
	ThreadGroup tg2 = t2.getThreadGroup();

	//获取线程组的名字,用线程组里面的方法:public final String getName():
	String name1 = tg1.getName();//线程组对象调用方法,获取组的名字
	String name2 = tg2.getName();
	System.out.println(name1);//main
	System.out.println(name2);//main
		
	//通过下面的测试,你应该能够看到,默认情况下,所有的线程都属于同一个组,线程默认情况下属于main线程组
		System.out.println(Thread.currentThread().getThreadGroup().getName());//main
}
```

    自己设定线程组:↓
代码演示如下:
```
public static void main(String[] args) {
	//ThreadGroup(String name):
	ThreadGroup tg = new ThreadGroup("这是一个新的组");//1线程组构造传入名字,设定线程组

	MyRunnable mr = new MyRunnable();
	
	//Thread(ThreadGroup group, Runnable target, String name):
	Thread t1 = new Thread(tg, mr, "张三");//2线程构造方法,传入线程组
	Thread t2 = new Thread(tg, mr, "李四");
	
    System.out.println(t1.getThreadGroup().getName());//3线程得到线程组,线程组得到组的名字:这是一个新的组
	System.out.println(t2.getThreadGroup().getName());//这是一个新的组
	
	//通过组名称设置后台线程,表示该组的线程都是后台线程
	tg.setDaemon(true);//但是这个方法还需要配合tg.stop()方法才能演示作用,否则不起作用,有点鸡肋!!!
}
```

#25.08_多线程(线程的五种状态)(掌握)
新建,就绪,运行,阻塞,死亡:

    A.线程的五种状态分别是新建,就绪,运行,阻塞,死亡
    B.新建状态,创建了线程对象
    C.就绪状态,线程对象启动,但是没有获得CPU的执行权
    D.运行状态获取到了CPU的执行权
    E.阻塞状态,没有CPU的执行权,会回到就绪状态
    F.死亡状态,代码运行完毕,线程消亡

线程的生命周期的执行流程是:新建状态创建线程对象,调用start方法来到就绪状态有执行资格没有CPU的执行权,
如果此时抢到了CPU的执行权就会到达运行状态有执行资格和执行权,
在运行状态有三个如果,
如果CPU的执行权被其他线程抢走了就会回到就绪状态;
如果调用了sleep或者wait方法就会到达阻塞状态没有执行资格也没有执行权会回到就绪状态;
如果调用了stop方法或者run方法结束就会到达死亡状态,线程对象变成垃圾,详情见截图!

#25.09_多线程(线程池的概述和使用)(了解)
A:线程池概述:

    程序启动一个新线程成本是比较高的,因为它涉及到要与操作系统进行交互,而使用线程池可以很好的提高性能,
	尤其是当程序中要创建大量生存期很短的线程时,更应该考虑使用线程池,线程池里的每一个线程代码结束后,
	并不会死亡,而是再次回到线程池中成为空闲状态,等待下一个对象来使用,另外,
	在JDK5之前,我们必须手动实现自己的线程池,从JDK5开始,Java内置支持线程池,如下↓
	
B:内置线程池的使用概述:

    JDK5新增了一个Executors工厂类来产生线程池,有如下几个方法:
	    public static ExecutorService newFixedThreadPool(int nThreads)
		public static ExecutorService newSingleThreadExecutor()
		这些方法的返回值是ExecutorService对象,该对象表示一个线程池,
		可以执行Runnable对象或者Callable对象代表的线程,它提供了如下方法:
		Future<?> submit(Runnable task)
		<T> Future<T> submit(Callable<T> task)
		
	使用步骤:
		创建线程池对象
		创建Runnable实例
		提交Runnable实例
		关闭线程池
		
C:案例演示:

    提交的是Runnable:
代码演示如下:
```
public static void main(String[] args) {
	//public static ExecutorService newFixedThreadPool(int nThreads):
	ExecutorService pool = Executors.newFixedThreadPool(2);

	//可以执行Runnable对象或者Callable对象代表的线程:
	pool.submit(new MyRunnable());//线程池提交任务后,记得关闭:↓
	pool.submit(new MyRunnable());

	//最后记得关闭线程池:↓
	pool.shutdown();
}
```

#25.10_多线程(多线程程序实现的方式3)(了解)
    提交的是Callable:
代码演示如下:
```
public static void main(String[] args) {
	//创建线程池对象:
	ExecutorService pool = Executors.newFixedThreadPool(2);

	//可以执行Runnable对象或者Callable对象代表的线程:
	Future<Integer> f1 = pool.submit(new MyCallable(100));
	Future<Integer> f2 = pool.submit(new MyCallable(50));//线程池提交线程,返回未来类对象,

	//V get():
	Integer i1 = f1.get();//未来类对象调用得到方法,得到线程执行方法call的返回值
	Integer i2 = f2.get();

	System.out.println(i1);//5050
	System.out.println(i2);//1275

	//最后记得关闭线程池
	pool.shutdown();
}

public class MyCallable implements Callable<Integer> {//公有表示在其他文件中,可去掉放同一文件

	private int number;

	public MyCallable(int number) {//1通过构造方法给成员变量number赋值,传入什么,值就是什么
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		//统计1到number的和:
		int sum = 0;
		for (int x = 1; x <= number; x++) {//2来保证成员变量的值可以发生变化而不是0
			sum += x;
		}
		
		return sum;
	}
}
```

多线程程序实现的方式3的好处和弊端:

	好处:
		可以有返回值
		可以抛出异常
		
	弊端:
		代码相对复杂,所以一般不用

总结:多线程程序实现的方式3,线程池提交Callable子类对象,首先要创建线程池,然后线程池提交,最后线程池关闭

#25.11_设计模式(简单工厂模式概述和使用)(了解)
A:简单工厂模式概述:

	又叫静态工厂方法模式,它定义一个具体的工厂类负责创建一些类的实例
	
B:优点:

    其他类客户端不需要再负责对象的创建,从而明确了各个类的职责
    
C:缺点:

	这个静态工厂类负责所有对象的创建,如果有新的对象增加,或者某些对象的创建方式不同,
	就需要不断的修改工厂类,不利于后期的维护
	
D:案例演示:

	动物抽象类:public abstract Animal { public abstract void eat(); }
	具体狗类:public class Dog extends Animal {...}
	具体猫类:public class Cat extends Animal {...}
	开始,在测试类中每个具体的内容自己创建对象,但是,创建对象的工作如果比较麻烦,就需要有人专门做这个事情,
	所以就制造了一个专门的类来创建对象,如下面的工厂类
代码演示如下:
```
//动物抽象类
public abstract class Animal {//看到下面不同的类都有public修饰就知道不在同一个源文件中,下面就不解释了
	public abstract void eat();
}

//猫狗实现类
public class Cat extends Animal {//客户端不需要再负责对象的创建,从而明确了各个类的职责
	@Override
	public void eat() {
		System.out.println("猫吃鱼");
	}
}
public class Dog extends Animal {//客户端不需要再负责对象的创建,从而明确了各个类的职责:
	@Override
	public void eat() {
		System.out.println("狗吃肉");
	}
}

//具体工厂类,即简单工厂模式,又叫静态工厂方法模式,它定义一个具体的工厂类负责创建一些类(如猫狗类)的实例:
public class AnimalFactory {
	//下面这样写,方法会定义很多,如果还有其他动物类如猪等,同样的代码写很多次,复用性太差,改进如下:
	/*public static Dog createDog() {
		return new Dog();
	}
	
	public static Cat createCat() {
		return new Cat();
	}*/
	
	//这个静态工厂类负责所有对象的创建,如果有新的对象增加,就需要不断的修改工厂类,不利于后期的维护:
	public static Animal createAnimal(String name) {//静态工厂方法,用来生产对象
		if("dog".equals(name)) {
			return new Dog();
		}else if("cat".equals(name)) {
			return new Cat();
		}else {
			return null;
		}
	}
}

//测试类
public class Test {
	public static void main(String[] args) {
		//Dog d = AnimalFactory.createDog();
		
		Dog d = (Dog) AnimalFactory.createAnimal("dog");
		d.eat();//狗吃肉
		
		Cat c = (Cat) AnimalFactory.createAnimal("cat");
		c.eat();//猫吃鱼
	}
}
```
总结:简单工厂其实就是我们平时写的工具类,简单吧,这里的工具类只不过是定义一个方法来创建不同名字的动物对象而已,如传入猫就创建猫,传入狗则创建狗对象,等等

#25.12_设计模式(工厂方法模式的概述和使用)(了解)
A:工厂方法模式概述:

    工厂方法模式中,抽象工厂类负责定义创建对象的接口,具体对象的创建工作由继承抽象工厂的具体类实现:
    
B:优点:

	客户端不需要再负责对象的创建,从而明确了各个类的职责,如果有新的对象增加,
	只需要增加一个具体的类和具体的工厂类即可,不影响已有的代码,后期维护容易,增强了系统的扩展性
	
C:缺点:

	需要额外的编写代码,增加了工作量
	
D:案例演示:
代码演示如下:

	动物抽象类:public abstract Animal { public abstract void eat(); }
	工厂接口：public interface Factory {public abstract Animal createAnimal();}
	具体狗类：public class Dog extends Animal {...}
	具体猫类：public class Cat extends Animal {...}
	
	开始,在测试类中每个具体的内容自己创建对象,但是,创建对象的工作如果比较麻烦,
	就需要有人专门做这个事情,所以就制造了一个专门的类来创建对象,发现每次修改代码太麻烦,
	用工厂方法改进,针对每一个具体的实现提供一个具体工厂:
	狗工厂：public class DogFactory implements Factory {
		public Animal createAnimal() {…}
		}
	猫工厂：public class CatFactory implements Factory {
		public Animal createAnimal() {…}
		}
代码演示如下:
```
//动物抽象类
public abstract class Animal {//看到下面不同的类都有public修饰就知道不在同一个源文件中,下面就不解释了
	public abstract void eat();
}

//猫狗实现类
public class Cat extends Animal {
	@Override
	public void eat() {
		System.out.println("猫吃鱼");
	}
}
public class Dog extends Animal {
	@Override
	public void eat() {
		System.out.println("狗吃肉");
	}
}

//工厂接口
public interface Factory {
	public Animal createAnimal();//抽取工厂接口,用来产生动物
}

//猫狗工厂实现类
public class CatFactory implements Factory {
	@Override
	public Animal createAnimal() {
		return new Cat();
	}
}
public class DogFactory implements Factory {
	@Override
	public Animal createAnimal() {
		return new Dog();
	}
}

//测试类
public class Test {
	public static void main(String[] args) {
		DogFactory df = new DogFactory();
		Dog d = (Dog) df.createAnimal();
		d.eat();//狗吃肉
	}
}
```
总结:前提存在动物抽象类,猫狗实现类,简单工厂模式又叫静态工厂方法模式,其实就是定义一个工具类调用其静态方法生产对象,
而工厂方法模式其实是把生产对象的方法抽取到一个工厂接口中,让工厂接口的实现类去生产对象,如猫工厂实现类生产猫...

#25.13_GUI(如何创建一个窗口并显示)
Graphical User Interface(图形用户接口):
代码演示如下:
```
public static void main(String[] args) {
	Frame  f = new Frame(“my window”);
	f.setLayout(new FlowLayout());//设置布局管理器,传入下面知识点的管理器即可
	f.setSize(500,400);//设置窗体大小
	f.setLocation(300,200);//设置窗体出现在屏幕的位置
	f.setIconImage(Toolkit.getDefaultToolkit().createImage("qq.png"));//设置窗体图标
	f.setVisible(true);//设置窗体可见,这个不要忘,否则看不到没用哦
}
```

#25.14_GUI(布局管理器)
    FlowLayout（流式布局管理器）
    	从左到右的顺序排列
    	Panel默认的布局管理器
    	
    BorderLayout（边界布局管理器）
    	东，南，西，北，中
    	Frame默认的布局管理器
    	
    GridLayout（网格布局管理器）
    	规则的矩阵
    	
    CardLayout（卡片布局管理器）
    	选项卡
    	
    GridBagLayout（网格包布局管理器）
    	非规则的矩阵

#25.15_GUI(窗体监听)
代码演示如下:
```
public static void main(String[] args) {
	Frame f = new Frame("我的窗体");
	//事件源是窗体,把监听器注册到事件源上
	//事件对象传递给监听器
	f.addWindowListener(new WindowAdapter() {//传入适配器对象
	@Override
        public void windowClosing(WindowEvent e) {
            //关闭窗口调用系统退出虚拟机方法
			System.exit(0);
		}
	});
}
```

#25.16_GUI(鼠标监听)
代码演示如下:
```
public static void main(String[] args) {
    b1.addMouseListener(new MouseAdapter() {//传入适配器对象
		/*@Override
		public void mouseClicked(MouseEvent e) {//单击回调次方法
			System.exit(0);
		}*/
		
		@Override
		public void mouseReleased(MouseEvent e) {//释放鼠标回调这:
			System.exit(0);
		}
	});
}
```

#25.17_GUI(键盘监听和键盘事件)
代码演示如下:
```
public static void main(String[] args) {
    b1.addKeyListener(new KeyAdapter() {//传入适配器对象
		@Override
		public void keyReleased(KeyEvent e) {
			//System.exit(0);
			//System.out.println(e.getKeyCode());
			//if(e.getKeyCode() == 32) {//对应空格键
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				System.exit(0);
			}
		}
	});
}
```

#25.18_GUI(动作监听)
代码演示如下:
```
public static void main(String[] args) {
    b2.addActionListener(new ActionListener() {//传入动作监听对象,应用场景就是暂停视频和播放视频
			
	@Override
    public void actionPerformed(ActionEvent e) {//默认对应鼠标左键或者空格键,应用场景就是暂停视频和播放视频
			System.exit(0);
		}
	});
}
```

#25.19_设计模式(适配器设计模式)(掌握)
a.什么是适配器:

    在使用监听器的时候,需要定义一个类事件监听器接口
	通常接口中有多个方法,而程序中不一定所有的都用到,但又必须重写,这很繁琐,
	适配器简化了这些操作,我们定义监听器时只要继承适配器,然后重写需要的方法即可

b.适配器原理:

    适配器就是一个抽象类,实现了监听器接口,所有抽象方法都重写了,但是方法全是空的:
	适配器类需要定义成抽象的,因为创建该类对象,调用空方法是没有意义的,
	目的就是为了简化程序员的操作,定义监听器时继承适配器,只重写需要的方法就可以了:
代码演示如下:
```
public class Demo1_Adapter {
    //适配器设计模式,利用抽象类可以没有抽象方法,然后让抽象类空实现接口方法,最后让抽象类的子类复写父类方法去做事情:
	public static void main(String[] args) {
        System.out.println("适配器设计模式,适配器就是一个复写接口抽象方法但什么也没做的抽象类,通过继承适配器复写实现");
		new 鲁智深().习武();
		new 鲁智深().念经();//继承过来的方法,但是父类什么也没做,所以没有输出
	}
}

interface 和尚 {//1.一个接口定义多个抽象方法:
	public void 打坐();
	public void 念经();
	public void 撞钟();
	public void 习武();
}

//声明成抽象的原因是,不想让其他类创建本类对象,因为创建也没有意义,方法都是空的:
abstract class 天罡星 implements 和尚 {//2.抽象类空实现接口方法:

	@Override
	public void 打坐() {//实现接口方法,但是什么也没做,让继承本抽象类的子类去复写完成
	}

	@Override
	public void 念经() {//空实现接口方法,什么也没做
	}

	@Override
	public void 撞钟() {//空实现接口方法,什么也没做
	}

	@Override
	public void 习武() {//实现接口方法,但是什么也没做,让继承本抽象类的子类去复写完成
	}	
}

class 鲁智深 extends 天罡星 {//3.抽象类子类去实现想要的接口方法:
	public void 习武() {//实现接口方法,但是什么也没做,让继承本抽象类的子类去复写完成
		System.out.println("倒拔垂杨柳");
		System.out.println("拳打镇关西");
		System.out.println("大闹野猪林");
		System.out.println("......");
	}
}
```

#25.20_GUI(需要知道的)
事件处理:

    事件:用户的一个操作
	事件源:被操作的组件
	监听器:一个自定义类的对象,实现了监听器接口,包含事件处理方法,把监听器添加在事件源上,
	当事件发生的时候,虚拟机就会自动调用监听器中的事件处理方法

GUI代码总结如下:
```
public class Demo1_Frame {
	public static void main(String[] args) {
		Frame f = new Frame("我的第一个窗口");
		f.setSize(400, 600);//设置窗体大小
		f.setLocation(500, 50);//设置窗体位置
		f.setIconImage(Toolkit.getDefaultToolkit().createImage("qq.png"));//设置窗体图片
		Button b1 = new Button("按钮一");//创建按钮对象
		Button b2 = new Button("按钮二");
		f.add(b1);//窗体添加按钮
		f.add(b2);
		f.setLayout(new FlowLayout());//设置布局管理器
		
		//f.addWindowListener(new MyWindowAdapter());
		f.addWindowListener(new WindowAdapter() {//窗体增加窗体监听,传入适配器对象
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		b1.addMouseListener(new MouseAdapter() {//按钮增加鼠标监听,传入适配器对象
			/*@Override
			public void mouseClicked(MouseEvent e) {//单击
				System.exit(0);
			}*/
			@Override
			public void mouseReleased(MouseEvent e) {//释放
				System.exit(0);
			}
		});
		
    	b1.addKeyListener(new KeyAdapter() {//按钮增加键盘监听,传入适配器对象
			@Override
			public void keyReleased(KeyEvent e) {
				//System.exit(0);
				//System.out.println(e.getKeyCode());
				//if(e.getKeyCode() == 32) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					System.exit(0);
				}
			}
		});
    		
    	b2.addActionListener(new ActionListener() {//按钮添加动作监听,传入监听器接口实现类对象,
    			
			@Override//应用场景就是暂停视频和播放视频,
			public void actionPerformed(ActionEvent e) {//因为只有一个接口方法,就不需要适配器了
				System.exit(0);//适配器其实就是实现了接口方法的抽象类,复写方法什么也不做
			}
		});
    		
    		f.setVisible(true);//设置窗体可见
    }
}

//实现接口要复写接口所有方法,麻烦,而继承适配器只需要复写你想要的方法即可,方便灵活
/*class MyWindowListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}	
}
*/

//继承适配器只需要复写你想要的方法即可,方便灵活,适配器其实就是实现了接口方法的抽象类,复写接口方法什么也不做,让抽象类子类去实现:
/*class MyWindowAdapter extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}*/
```

#25.21_day25总结
    把今天的知识点总结一遍:
    单例设计模式三种写法以及系统运行时类举例-定时器类-线程间的通信等待唤醒三种写法-线程组-线程的五种状态-
    线程池以及线程池提交Callable接口实现类对象,实现多线程程序的实现方式3-简单工厂又名静态工厂方法模式-
    工厂方法模式其实就是抽取工厂接口-GUI显示布局监听-适配器设计模式,适配器其实就是实现了接口方法的抽象类,
    复写接口方法里面什么也不做,空实现

//懒汉式即延迟加载的单利设计模式不加同步会出现多线程的安全性问题,
代码演示如下:↓
```
public class Work {
	public static void main(String[] args) {
//		System.out.println(O.getInstance()==O.getInstance());
		for (int i = 0; i < 10; i++) {
			new Thread(new MyThread2()).start();
		}
	}
}

class MyThread2 implements Runnable{
	@Override
	public void run() {
		System.out.println(O.getInstance()==O.getInstance());
	}
}

class O {
	private O(){}
	private static O o;
	public static O getInstance(){
		if (o==null) {
			o = new O();
		}
		
		return o;
	}
}
```

