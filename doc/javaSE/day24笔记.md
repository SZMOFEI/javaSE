#24.01_多线程(多线程的引入)(了解)
1.什么是线程:进程:进行的程序

    线程是程序执行的一条路径,一个进程中可以包含多条线程
    多线程并发执行可以提高程序的效率,可以同时完成多项工作

2.多线程的应用场景:

	红蜘蛛同时共享屏幕给多个电脑
	迅雷开启多条线程一起下载
	QQ同时和多个人一起视频
	服务器同时处理多个客户端请求

#24.02_多线程(多线程并行和并发的区别)(了解)
并行就是两个任务同时运行,就是甲任务进行的同时,乙任务也在进行(需要多核CPU);
并发是指两个任务都请求运行,而处理器只能接受一个任务,就把这两个任务安排轮流进行,由于时间间隔较短,使人感觉两个任务都在运行:

    举例:我跟两个网友聊天,左手操作一个电脑跟甲聊,同时右手用另一台电脑跟乙聊天,这就叫并行:
    如果用一台电脑我先给甲发个消息,然后立刻再给乙发消息,然后再跟甲聊,再跟乙聊,这就叫并发

#24.03_多线程(Java程序运行原理和JVM的启动是多线程的吗)(了解)
A:Java程序运行原理:

    Java命令会启动java虚拟机,启动JVM等于启动了一个应用程序,也就是启动了一个进程,
    该进程会自动启动一个"主线程",然后主线程去调用某个类的main方法

B:JVM的启动是多线程的吗?

    JVM启动至少启动了垃圾回收线程和主线程,所以是多线程的:
代码演示如下:
```
public class Demo1_Thread {
	//证明jvm是多线程的
	public static void main(String[] args) {
		for(int i = 0; i < 100000; i++) {
			new Demo();//产生很多垃圾,每产生一个匿名对象调用完毕就是垃圾,垃圾回收器调用gc方法,
		}
		
		for(int i = 0; i < 10000; i++) {
			System.out.println("我是主线程的执行代码");//主线程
		}
	}
}

class Demo {
	@Override
	public void finalize() {//gc方法调用后会回调finalize方法,
		System.out.println("垃圾被清扫了");//说明JVM启动至少启动了垃圾回收线程和主线程
	}	
}
```

#24.04_多线程(多线程程序实现的方式1)(掌握)
###1.继承Thread类:
	定义类继承Thread
	重写run方法
	把新线程要做的事写在run方法中
	创建线程对象
	开启新线程,内部会自动执行run方法
代码演示如下:
```
public class Demo2_Thread {
	public static void main(String[] args) {
		MyThread mt = new MyThread();//4,创建自定义类的对象
		mt.start();//5,开启线程
		
		for(int i = 0; i < 3000; i++) {
		System.out.println("主线程bbb");//开启子线程的时间CPU切换很快,一般主线程先输出内容,
		}
	}
}

class MyThread extends Thread {//1,定义类继承Thread
	public void run() {											//2,重写run方法
		for(int i = 0; i < 3000; i++) {//3,将要执行的代码,写在run方法中
		    System.out.println("子线程aa");//子线程要开启完毕后,才调用run方法执行输出内容
		}
	}
}
```
总结:在子线程开启还没有完成的一定时间里,主线程就把内容输出一部分,说明CPU切换很快,
然后子线程开启后,两个线程抢占资源输出

#24.05_多线程(多线程程序实现的方式2)(掌握)
###2.实现Runnable接口:
    定义类实现Runnable接口
    实现run方法
    把新线程要做的事写在run方法中
    创建自定义的Runnable的子类对象
    创建Thread对象,传入Runnable
    调用start()开启新线程,内部会自动调用Runnable的run()方法
代码演示如下:
```
public class Demo3_Runnable {
	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();//4,创建自定义类对象
		Thread t = new Thread(mr);//5,将其当作参数传递给Thread的构造函数
		t.start();//6,开启线程
		
		for(int i = 0; i < 3000; i++) {
			System.out.println("主线程bb");
		}
	}
}

class MyRunnable implements Runnable {//1,自定义类实现Runnable接口
	@Override
	public void run() {//2,重写run方法
		for(int i = 0; i < 3000; i++) {//3,将要执行的代码,写在run方法中
			System.out.println("子线程aa");
		}
	}		
}
```

#24.06_多线程(实现Runnable的原理)(了解)
查看源码:

    1,看Thread类的构造函数,传递了Runnable接口的引用
    2,通过init()方法找到传递的target给成员变量的target赋值
    3,查看run方法,发现run方法中有判断,如果target不为null,就会调用Runnable接口子类对象的run方法

#24.07_多线程(两种方式的区别)(掌握)
查看源码的区别:

	a.继承Thread:由于子类重写了Thread类的run(),当调用start()时,直接找子类的run()方法;

	b.实现Runnable:构造函数中传入了Runnable的引用,成员变量记住了它,start()调用run()方法时,
	  内部判断成员变量Runnable的引用是否为空,不为空编译时看的是Runnable接口的run()方法,
	  运行时执行的是接口实现类的run()方法

继承Thread:

    好处是:可以直接使用Thread类中的方法,代码简单
    弊端是:如果已经有了父类,就不能用这种方法

实现Runnable接口:

	好处是:即使自己定义的线程类有了父类也没关系,因为有了父类也可以实现接口,而且接口是可以多实现的
	弊端是:不能直接使用Thread中的方法需要先获取到线程对象后,才能得到Thread的方法,代码相对复杂

#24.08_多线程(匿名内部类实现线程的两种方式)(掌握)
继承Thread类:
代码演示如下: 
```
public static void main(String[] args) {
	new Thread() {//1,new 类(){}继承这个类
		public void run() {//2,重写run方法
			for(int i = 0; i < 3000; i++) {//3,将要执行的代码,写在run方法中
				System.out.println("子线程aa");
			}
		}
	}.start();
}
```

实现Runnable接口:
代码演示如下:
```
public static void main(String[] args) {
	new Thread(new Runnable(){//1,new 接口(){}实现这个接口
		public void run() {//2,重写run方法
			for(int i = 0; i < 3000; i++) {//3,将要执行的代码,写在run方法中
				System.out.println("子线程bb");
			}
		}
	}).start();
}
```

#24.09_多线程(获取名字和设置名字)(掌握)
1.获取名字:

    通过getName()方法获取线程对象的名字//默认从Thread-0开始命名,下一个为Thread-1

2.设置名字:

    通过构造函数可以传入String类型的名字,或者通过setName(String)方法可以设置线程对象的名字:
代码演示如下:
```
public static void main(String[] args) {
	new Thread("xxx") {
		public void run() {
			for(int i = 0; i < 1000; i++) {
				System.out.println(this.getName() + "...aa");//xxx...aa
			}
		}
	}.start();
	
	new Thread("yyy") {
		public void run() {
			for(int i = 0; i < 1000; i++) {
				System.out.println(this.getName() + "...bb");//yyy...bb
			}
		}
	}.start(); 
}
```

    通过setName(String)方法可以设置线程对象的名字:
代码演示如下:
```
public static void main(String[] args) {
    Thread t1 = new Thread() {
		public void run() {
			for(int i = 0; i < 1000; i++) {
				System.out.println(this.getName() + "....aaaaaaaaaaaaaaaaaaaaaaa");
			}
		}
	};
	
	Thread t2 = new Thread() {
		public void run() {
			for(int i = 0; i < 1000; i++) {
				System.out.println(this.getName() + "....bb");
			}
		}
	};
	t1.setName("芙蓉姐姐");
	t2.setName("凤姐");
	
	t1.start();
	t2.start();
}
```
总结:通过构造传入名字或者通过对象调用setName方法设置名字,封装的跟我们平时写的Javabean一样,对吧?

#24.10_多线程(获取当前线程的对象)(掌握)
###Thread.currentThread(),主线程也可以获取:
代码演示如下:
```
public static void main(String[] args) {
	new Thread(new Runnable() {
		public void run() {
			for(int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName() + "...a");//Thread-0....aaaaaa
			}
		}
	}).start();
	
	new Thread(new Runnable() {
		public void run() {
			for(int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName() + "...bb");//Thread-1...bb
			}
		}
	}).start();
	Thread.currentThread().setName("我是主线程");//获取主函数线程的引用,并改名字
	System.out.println(Thread.currentThread().getName());//我是主线程,获取主函数线程的引用,并获取名字
}
```
    总结:线程的默认名字Thread-0也是从构造调用设置的,看构造源码就知道了,如下所示:
    public Thread() {
            init(null, null, "Thread-" + nextThreadNum(), 0);//nextThreadNum方法返回值threadInitNumber默认从0开始
    }

#24.11_多线程(休眠线程)(掌握)
Thread.sleep(毫秒,纳秒),控制当前线程休眠若干毫秒1秒= 1000毫秒 1秒 = 1000 * 1000 * 1000纳秒 1000000000
代码演示如下:
```
public static void main(String[] args) {
	new Thread() {
		public void run() {
			for(int i = 0; i < 10; i++) {
				System.out.println(getName() + "...aa");//Thread-0...aa
				try {
					Thread.sleep(1000);//让当前线程睡眠1秒
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}.start();
	
	new Thread() {
		public void run() {
			for(int i = 0; i < 10; i++) {
				System.out.println(getName() + "...bb");//Thread-1...bb
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}.start();
}
```
总结:这个不一定是交替输出的,多次快速运行程序就知道了,只不过这里的时间控制的非常接近,有时候会有交替输出的假象

#24.12_多线程(守护线程)(掌握)
###setDaemon(),设置一个线程为守护线程,该线程不会单独执行,当其他非守护线程都执行结束后,自动退出:
代码演示如下:
```
public static void main(String[] args) {
	Thread t1 = new Thread() {
		public void run() {
			for(int i = 0; i < 50; i++) {
				System.out.println(getName() + "...aaaaaaaaaaaaaaaaaaaaaa");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	Thread t2 = new Thread() {
		public void run() {
			for(int i = 0; i < 5; i++) {
				System.out.println(getName() + "...bb");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	t1.setDaemon(true);//将t1设置为守护线程,这里注意要先设置为守护线程,才能启动线程,顺序反了报错!!!
	
	t1.start();//开启的时间里,一般但不绝对,先开启的优先输出一点点,不管你是守护线程还是非守护线程
	t2.start();
}
```
总结:所有线程都抢占资源,非守护线程都执行结束后,守护线程自动退出,但是由于CPU切换太快了,
在守护线程退出的一定时间内还有输出一点点,守护线程是指多个附属线程来保护被指定的线程,如果被指定的线程挂了,
附属的线程也要灭亡,有点皇帝死亡与妃子陪葬的关系

#24.13_多线程(加入线程)(掌握)
###join(),当前线程暂停,等待指定的线程执行结束后,当前线程再继续
###join(int),可以等待指定的毫秒之后继续
代码演示如下:
```
public static void main(String[] args) {
	final Thread t1 = new Thread() {
		public void run() {
			for(int i = 0; i < 50; i++) {
				System.out.println(getName() + "...aaaaaaaaaaaaaaaaaaaaaa");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	Thread t2 = new Thread() {
		public void run() {
			for(int i = 0; i < 50; i++) {
				if(i == 2) {
					try {
						//t1.join();//插队,加入线程执行完,当前线程再继续
						t1.join(30);//加入,有固定的时间,过了固定时间,当前线程再继续
						Thread.sleep(10);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				System.out.println(getName() + "...bb");
			}
		}
	};
	
	t1.start();
	t2.start();
}
```
总结:所有线程都会抢占资源的,其他地方也是,当符合一定条件后,加入的线程会执行完毕,再执行其他线程

#24.14_多线程(礼让线程)(了解)
Thread.yield();让出cpu
代码演示如下:
```
public class Demo6_Yield {
	public static void main(String[] args) {
		new MyThread().start();
		new MyThread().start();
	}
}

class MyThread extends Thread {
	public void run() {
		for(int i = 1; i <= 1000; i++) {
			if(i % 10 == 0) {
				Thread.yield();//让出CPU,鸡肋方法,效果不太明显,了解就好
			}
			System.out.println(getName() + "..." + i);
		}
	}
}
```
总结:鸡肋方法,效果不太明显,了解就好

#24.15_多线程(设置线程的优先级)(了解)
###setPriority()设置线程的优先级,范围1-10,默认为5:
    这个方法在一些时候会有一些鸡肋,什么意思呢,原因是线程在运行过程中除非加了同步,否则是不受控制的,

    另一方面,程序的优先级是不可能被绝对执行的,因为有一个程序被绝对优先执行,相当于其他所有的程序都要停下来,
    难道QQ的执行权被提高了,就要360挂掉么,不可能的,
    我们的电脑一般属于并发运行,如果有一个程序可以阻止所有别的程序运行,那这个并发的过程就要出问题了,
    
    所以其实优先级,更准确的来说只是提高了某个线程执行几率,就好像有个硬币抛起来落地后得到正面的几率为90%,
    我们扔上十次,也可能十次都是反面,
    所以优先级的使用,更注重于一些执行次数多,数据量大的多线程执行环境,简单的线程使用优先级是看不出来效果的:
代码演示如下:
```
public static void main(String[] args) {
	Thread t1 = new Thread(){
		public void run() {
			for(int i = 0; i < 100; i++) {
				System.out.println(getName() + "...aaaaaaaaa" );//2还是会抢占资源
			}
		}
	};
	
	Thread t2 = new Thread(){
		public void run() {
			for(int i = 0; i < 100; i++) {
				System.out.println(getName() + "...bb" );//1优先一点点,但不是绝对的
			}
		}
	};
	
	//t1.setPriority(10);//设置最大优先级
	//t2.setPriority(1);//范围1-10，默认为5
	
	t1.setPriority(Thread.MIN_PRIORITY);//设置最小的线程优先级
	t2.setPriority(Thread.MAX_PRIORITY);//设置最大的线程优先级
	
	t1.start();
	t2.start();
}
```
总结:设置优先级大的线程输出的时候,有时候会优先一点点,但不是绝对的,而且后面大家还是会抢占资源,一般交替输出

#24.16_多线程(同步代码块)(掌握)
1.什么情况下需要同步:

    当多线程并发,有多段代码同时执行时,我们希望某一段代码执行的过程中,CPU不要切换到其他线程工作,
    这时就需要同步,
    如果两段代码是同步的,那么同一时间只能执行一段,在一段代码没执行结束之前,不会执行另外一段代码

2.同步代码块:

    使用synchronized关键字加上一个锁对象来定义一段代码,这就叫同步代码块,
    多个同步代码块,如果使用相同的锁对象,那么他们就是同步的,这个要特别注意,想想火车上厕所的例子就明白了:
代码演示如下:
```
public class Demo1_Synchronized {
	public static void main(String[] args) {
		final Printer p = new Printer();
		
		new Thread() {//匿名内部类开启多个线程,分别调用自定义类的不同方法,要求实现同步:
			public void run() {
				while(true) {
					p.print1();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					p.print2();
				}
			}
		}.start();
	}
}

class Printer {
	Demo d = new Demo();
	public void print1() {
//synchronized(new Demo()) {//同步代码块,锁机制,锁对象可以是任意的,但是多个同步代码块要同步,要保证是同一把锁,
		synchronized(d) {//可以注释掉同步代码块来演示区别
			System.out.print("黑");
			System.out.print("马");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.print("\r\n");
		}
	}
	
	public void print2() {
		//synchronized(new Demo()) {//所以,锁对象不能用匿名对象,因为它不是同一个对象,即不是同一把锁!
		synchronized(d) {		
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
		}
	}
}
class Demo{}
```
总结:不加同步代码块两个线程抢占资源输出就会乱序如黑马传智程等,但是加了同步代码块要保证是同一把锁,如果你用了
匿名对象做各自的锁,那就不是同一把锁,还是会乱序,所以保证一个线程一个线程输出完毕,要加同步代码块且是同一把锁!

#24.17_多线程(同步方法)(掌握)
###使用synchronized关键字修饰一个方法,该方法中所有的代码都是同步的:
###非静态的同步方法的锁对象是this,静态的同步方法的锁对象是该类的字节码对象:
代码演示如下:
```
public class Demo2_Synchronized {
	public static void main(String[] args) {
		final Printer2 p = new Printer2();
		
		new Thread() {
			public void run() {
				while(true) {
					p.print();
//					p.print1();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				while(true) {
					p.print0();
//					p.print2();
				}
			}
		}.start();
	}

}

class Printer2 {
	//1非静态同步函数的锁是:this,演示时用一个同步方法,一个同步代码块但是锁对象不是this,
	public synchronized void print() {//同步方法只需要在方法上加synchronized关键字即可
		System.out.print("黑");
		System.out.print("马");
		System.out.print("程");
		System.out.print("序");
		System.out.print("员");
		System.out.print("\r\n");
	}
	public void print0() {
		//synchronized(new Demo()) {//锁对象不能用匿名对象,因为匿名对象不是同一个对象
//		synchronized(Printer2.class) {//其他对象即使是字节码对象也还是乱码,改为this就不会		
		synchronized(this) {//只有改为this就不会乱序,说明和非静态同步方法是同一把锁		
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
		}
	}
	
	//2静态的同步函数的锁是:字节码对象,这个也同理控制锁对象不同演示即可:
	public static synchronized void print1() {//同步方法只需要在方法上加synchronized关键字即可
		System.out.print("黑");
		System.out.print("马");
		System.out.print("程");
		System.out.print("序");
		System.out.print("员");
		System.out.print("\r\n");
	}
	
	public static void print2() {
		//synchronized(new Demo()) {//锁对象不能用匿名对象,因为匿名对象不是同一个对象
		synchronized(Printer2.class) {//只有改为字节码对象才不会乱序,说明和静态方法是同一个锁		
			System.out.print("传");
			System.out.print("智");
			System.out.print("播");
			System.out.print("客");
			System.out.print("\r\n");
		}
	}
}
```
总结:非静态同步函数的锁是:this,演示时用一个同步方法,一个同步代码块但是锁对象不是this,如其他类对象d或者字节码
对象作为锁都会乱序,而用this作为锁就不会乱序,说明同步代码块用this作为锁时跟非静态同步方法的锁,是同一把锁;
同理,静态的同步函数的锁是:本类字节码对象,只有同步方法用同一个字节码对象时,才不会乱序,他们的锁是同一把锁!

#24.18_多线程(线程安全问题)(掌握)
###多线程并发操作同一数据时,就有可能出现线程安全问题,如出现票数为负数,为0的情况:
###使用同步技术可以解决这种问题,把操作数据的代码进行同步,不要多个线程一起操作:
代码演示如下:
```
public class Demo2_Synchronized {
	//需求:铁路售票,一共100张,通过四个窗口卖完
	public static void main(String[] args) {
		TicketsSeller t1 = new TicketsSeller();
		TicketsSeller t2 = new TicketsSeller();
		TicketsSeller t3 = new TicketsSeller();
		TicketsSeller t4 = new TicketsSeller();
		
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t4.setName("窗口4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

class TicketsSeller extends Thread {
	private static int tickets = 100;//共享数据定义成静态!!!
	static Object obj = new Object();//要保证是同一把锁,引用数据类型成员变量当作锁对象,必须静态!!!
	public TicketsSeller() {
		super();
		
	}
	public TicketsSeller(String name) {
		super(name);
	}
	
	public void run() {
		while(true) {//锁对象还可以用TicketsSeller.class,必须保证是同一把锁即可
			synchronized(obj) {//并发操作同一个共享数据,用同步代码块解决线程的安全问题!!!
				if(tickets <= 0) 
					break;
				try {//让线程睡眠,让线程安全问题更加突出,更容易出现票数为负数等情况
					Thread.sleep(10);//线程1睡,线程2睡,线程3睡,线程4睡
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				System.out.println(getName() + "...这是第" + tickets-- + "号票");
			}
		}
	}
}
```
总结:统一用同步代码块所在类的字节码对象做同步锁对象即可,原因解释在下个知识点

#24.19_多线程(火车站卖票的例子用实现Runnable接口)(掌握)
代码演示如下:
```
public class Demo4_Ticket {
	//火车站卖100张票的例子用实现Runnable接口方式实现
	public static void main(String[] args) {
		MyTicket mt = new MyTicket();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
		
		/*Thread t1 = new Thread(mt);//多次启动同一个线程是非法的,会报运行时异常
		t1.start();
		t1.start();
		t1.start();
		t1.start();*/
	}
}

class MyTicket implements Runnable {
	private int tickets = 100;//这里不用定义成静态,因为只创建一个对象,跟下面可以用this作为锁是一样的,
	@Override//当然定义成静态也没问题,为了养成共享数据定义成静态的习惯,建议定义成静态
	public void run() {
		while(true) {
			synchronized(this) {//并发操作同一个共享数据,用同步代码块解决线程的安全问题!!!
				if(tickets <= 0) {
					break;
				}
				try {//让线程睡眠,让线程安全问题更加突出,更容易出现票数为负数等情况
					Thread.sleep(10);//线程1睡,线程2睡,线程3睡,线程4睡
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
		        System.out.println(Thread.currentThread().getName() + "...这是第" + tickets-- + "号票");
			}
		}
	}
}
```
总结:用同步代码块时要保证是同一把锁,如果用继承线程类创建四个线程时this对象都不同,而用实现Runnable接口时,虽然
也创建了四个线程类对象,但是传入构造的都是同一个Runnable对象引用即this,所以为了避免这种麻烦,以后用同步代码块时,
锁对象最好用同步代码块所在类的字节码对象,这样可以保证是同一把锁,或者在同步代码块所在类定义一个静态成员变量,如
private static Object obj = new Object();用obj作为锁,但是麻烦,所以,统一用同步代码块所在类的字节码对象做同步锁
但是,如果用的是同步方法就要分情况了,非静态同步方法的锁对象是this,而静态同步方法的锁对象是该类的字节码对象!!!

#24.20_多线程(死锁)(了解)
多线程同步的时候,如果同步代码嵌套,使用相同锁,就有可能出现死锁:

    所以,尽量不要嵌套使用同步代码块!!!
代码演示如下:
```
private static String s1 = "筷子左";
private static String s2 = "筷子右";
public static void main(String[] args) {
	new Thread() {
		public void run() {
			while(true) {
				synchronized(s1) {
					System.out.println(getName() + "...拿到" + s1 + "等待" + s2);
					synchronized(s2) {
						System.out.println(getName() + "...拿到" + s2 + "开吃");
					}
				}
			}
		}
	}.start();//1多次快速运行程序你就知道,下面的结论:↓
	
	new Thread() {
		public void run() {
			while(true) {
				synchronized(s2) {
					System.out.println(getName() + "...拿到" + s2 + "等待" + s1);
					synchronized(s1) {
						System.out.println(getName() + "...拿到" + s1 + "开吃");
					}
				}
			}
		}
	}.start();//2这个不是一开始就死锁的,在线程的开启时间内,如果第二个线程还没有启动完成,那么就有输出
}
```
总结:两个线程强抢资源,但我拿不到你的锁,你拿不到我的锁,而且我们两个都没有释放锁的情况下就会出现僵持的死锁状态,
谁都动弹不得,不能输出,所以为了避免这种情况,尽量不要嵌套使用同步代码块!!!

#24.21_多线程(以前的线程安全的类回顾)(掌握)
A:回顾以前说过的线程安全问题:

    看源码:Vector,StringBuffer,Hashtable,Collections.synchroinzed(xxx)
    Vector是线程安全的,ArrayList是线程不安全的
    StringBuffer是线程安全的,StringBuilder是线程不安全的
    Hashtable是线程安全的,HashMap是线程不安全的
总结:线程同步,线程安全,但是效率较低

#24.22_day24总结
    把今天的知识点总结一遍:
    进程和线程-多线程并发和并行的区别-Java虚拟机进程至少启动了主线程和垃圾回收线程-多线程程序的两种实现方式-
    获取当前线程对象和设置获取线程名字-线程休眠,线程守护,线程加入,线程礼让,线程设置优先级-同步代码块-同步方法-
    -解决线程的安全性问题-多线程死锁

