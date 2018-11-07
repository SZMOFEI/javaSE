#27.01_反射(类的加载概述和加载时机)
A:类的加载概述:

    当程序要使用某个类时,如果该类还未被加载到内存中,则系统会通过加载,连接,初始化,
	三步来实现对这个类进行初始化:
	
	加载:
		就是指将class文件读入内存,并为之创建一个Class对象,任何类被使用时系统都会建立一个Class对象
		
	连接:三部曲↓
		验证,是否有正确的内部结构,并和其他类协调一致
		准备,负责为类的静态成员分配内存,并设置默认初始化值
		解析,将类的二进制数据中的符号引用替换为直接引用
		
	初始化:就是我们以前讲过的初始化步骤

B:加载时机:

	创建类的实例
	访问类的静态变量,或者为静态变量赋值
	调用类的静态方法
	使用反射方式来强制创建某个类或接口对应的java.lang.Class对象
	初始化某个类的子类
	直接使用java.exe命令来运行某个主类,(主类指含有主方法即main方法的类)

#27.02_反射(类加载器的概述和分类)
A:类加载器的概述:

    负责将.class文件加载到内存中,并为之生成对应的Class对象,虽然我们不需要关心类加载机制,
	但是了解这个机制我们就能更好的理解程序的运行
	
B:类加载器的分类:

	Bootstrap ClassLoader 根类加载器
	Extension ClassLoader 扩展类加载器
	Sysetm ClassLoader 系统类加载器
	
C:类加载器的作用:

	Bootstrap ClassLoader 根类加载器:
	    也被称为引导类加载器,负责Java核心类的加载,
		比如System,String等,在JDK中JRE的lib目录下rt.jar文件中

	Extension ClassLoader 扩展类加载器:
		负责JRE的扩展目录中jar包的加载,
		在JDK中JRE的lib目录下ext目录

	Sysetm ClassLoader 系统类加载器:
		负责在JVM启动时,加载来自java命令的class文件,以及classpath环境变量所指定的jar包和类路径

#27.03_反射(反射概述)
A:反射概述:

    JAVA反射机制是在运行状态中,对于任意一个类,都能够知道这个类的所有属性和方法,
	对于任意一个对象,都能够调用它的任意一个方法和属性,
	这种动态获取的信息,以及动态调用对象的方法的功能,称为java语言的反射机制!!!
	
	要想解剖一个类,必须先要获取到该类的字节码文件对象,
	而解剖使用的就是Class类中的方法,所以先要获取到每一个字节码文件对应的Class类型的对象:

B:三种方式:

	a:Object类的getClass()方法,判断两个对象是否是同一个字节码文件
	b:静态属性class,例如锁对象XXX.class
	c:Class类中静态方法forName(),读取配置文件,这个前期容易淡忘!!!
	
C:案例演示:

    获取class文件对象的三种方式:
总结:三种获取字节码文件对象的方式,但是在实际开发中,更多情况下使用的是第三种,原因是什么?因为更加灵活,看下面知识点:

#27.04_反射(Class.forName()读取配置文件举例)
榨汁机(Juicer)榨汁的案例:

    分别有水果(Fruit)苹果(Apple)香蕉(Banana)桔子(Orange)榨汁(squeeze)
代码演示如下:
```
public class Demo2_Reflect {
	public static void main(String[] args) throws Exception {
	    /*Juicer j = new Juicer();
		//j.run(new Apple());//榨汁机运行,榨出苹果汁
		j.run(new Orange());*/又调用一次方法,如果要榨多种水果,那得调用多次,麻烦,代码复用性太差

        BufferedReader br = new BufferedReader(new FileReader("config.properties"));//文件内容com.heima.reflect.Apple
		Class<?> clazz = Class.forName(br.readLine());//读取配置文件一行内容,获取该类的字节码对象,
		Fruit f = (Fruit) clazz.newInstance();//通过字节码对象创建实例对象,并用多态接收
		Juicer j = new Juicer();
		j.run(f);//调用一次方法,根据读取的配置文件里面每一行都是全路径的类名,你想要榨什么就配置什么
	}
}

interface Fruit {//水果接口,抽象榨汁方法
	public void squeeze();
}

class Apple implements Fruit {//苹果类实现榨汁方法
	public void squeeze() {
		System.out.println("榨出一杯苹果汁儿");
	}
}

class Orange implements Fruit {//橘子类实现榨汁方法
	public void squeeze() {
		System.out.println("榨出一杯桔子汁儿");
	}
}

class Juicer {//榨汁机类提供运行方法,调用的是传入水果接口实现类的榨汁方法,多态
	public void run(Fruit f) {//用接口,可以接收更多实现类对象
		f.squeeze();
	}
}
```
总结:配置文件里面一行内容为带全路径的水果类名,你想要榨什么汁,就配置什么水果类名,通过流读取内容传入forName方法
造出字节码类对象,对象调用方法强转得到水果,传入榨汁机的运行方法中榨出对应果汁

#27.05_反射(通过反射获取带参构造方法并使用)
Constructor类:

    Class类的newInstance()方法是使用该类无参的构造函数创建对象,如果一个类没有无参的构造函数,
	就不能这样创建了,可以调用Class类的getConstructor(String.class,int.class)方法,
	获取一个指定的构造函数然后再调用Constructor类的newInstance("张三",20)方法创建对象:
代码演示如下:
```
public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("com.heima.bean.Person");
    //Person类手动给出有参构造没有给出无参构造,系统就不再给出无参构造,通过无参构造创建对象就会报错:↓
    //Person p = (Person) clazz.newInstance();//字节码类对象的创建实例方法调用的是无参构造,没有报错↑
    //System.out.println(p);
    
    Constructor c = clazz.getConstructor(String.class,int.class);//获取有参构造,
    Person p = (Person) c.newInstance("张三",23);//通过有参构造创建对象
    System.out.println(p);//Person [name=张三, age=23]
}
```
总结:注意,如果构造方法被私有,应该用getDeclaredConstructor方法获取,并且该构造器类对象要调用方法先进行暴力反射,
代码演示如下:
```
public class Work {
	public static void main(String[] args) throws Exception {
	    Class c = Class.forName("com.itheima.有待验证.Student");//Student类中对应有参构造被私有修饰:
		Constructor ct = c.getDeclaredConstructor(String.class,int.class);
		ct.setAccessible(true);//被私有的构造器类对象要调用设置接近方法进行暴力反射后,才能创建类对象s:
		
		Student s = (Student) ct.newInstance("小明",23);
		System.out.println(s);
	}
}
```
下面的字段类,方法类如果被私有,也要调用相应的getDeclaredXxx方法,然后Xxx类对象调用setAccessible(true);暴力反射后,才能进行其他操作↓

#27.06_反射(通过反射获取成员变量并使用)
Field类:

    Class.getField(String)方法可以获取类中的指定字段(可见的),
    如果是私有的可以用getDeclaedField("name")方法获取,
    通过set(obj, "李四")方法可以设置指定对象上该字段的值,
    如果是私有的需要先调用setAccessible(true)去除私有访问权限,
    用获取的指定的字段调用get(obj)可以获取指定对象中该字段的值
代码演示如下:
```
public static void main(String[] args) throws Exception {
	Class clazz = Class.forName("com.heima.bean.Person");
	Constructor c = clazz.getConstructor(String.class,int.class);//获取有参构造
	Person p = (Person) c.newInstance("张三",23);//通过有参构造创建对象
	
	//Field f = clazz.getField("name");//获取姓名字段
    //f.set(p, "李四");//修改姓名的值,输出p对象就会报异常，因为这里的Person类成员变量name被pirate修饰是私有的
	
	//所以要先采用暴力反射获取字段,才能调用对象进行其他操作:
	Field f = clazz.getDeclaredField("name");//所以要采用暴力反射获取字段
	f.setAccessible(true);//但是要记得去除私有权限,才能进行其他操作:
	f.set(p, "李四");
	
	System.out.println(p);//Person [name=李四, age=23]
}
```

#27.07_反射(通过反射获取方法并使用)
Method类:

    Class.getMethod(String, Class...) 和 Class.getDeclaredMethod(String, Class...)方法,
	可以获取类中的指定方法,调用invoke(Object, Object...)可以调用该方法,
	如:Class.getMethod("eat").invoke(obj);
	   Class.getMethod("eat",int.class).invoke(obj,10);
代码演示如下:
```
public static void main(String[] args) throws Exception {
	Class clazz = Class.forName("com.heima.bean.Person");
	Constructor c = clazz.getConstructor(String.class,int.class);//获取有参构造
	Person p = (Person) c.newInstance("张三",23);//通过有参构造创建对象
	
	Method m = clazz.getMethod("eat");//获取eat方法
	m.invoke(p);//今天吃了一顿金钱豹,调用p对象所在类的eat方法
	
	Method m2 = clazz.getMethod("eat", int.class);//获取有参的eat方法
	m2.invoke(p, 10);//今天吃了10顿金钱豹,调用p对象所在类的eat(int num)方法
	
    //另外,如何获取像main方法那样传入字符串数组类型的方法并调用:
	Class<?> class2 = Class.forName("com.heima.bean.Person");
	Method method = class2.getMethod("main", String[].class);//字节码对象得到方法类对象,跟平时一样,

    method.invoke(class2.newInstance(), (Object)new String[]{"1"});//调用时把数组强转为Object类型即可,
    
    method.invoke(class2.newInstance(), new Object[]{new String[]{"1"}});//或者用Object类型数组接收字符串数组也可,
    
	//main方法被调用上面两种方式都可以,推荐第一种,简单点
}
```
总结:由于JDK1.4把传入数组参数的每一个元素当做一个参数,而JDK1.5为了兼容JDK1.4的语法规则,也会这么做,所以:
当调用像main方法这样要传入数组类型的参数方法时,通过字节码得到方法类对象再invoke调用方法时,传入的实参数组,
要强转为Object类对象,这样就是一个参数,不会把数组拆分,或者用一个Object类型的数组接收一个元素,而这个元素恰好是
我们的字符串数组,这样也不会把字符串数组拆分,总而保证是一个参数,而不是要拆分字符串数组的元素为一个参数

#27.08_反射(通过反射越过泛型检查)
A:案例演示:

    ArrayList<Integer>的一个对象,在这个集合中添加一个字符串数据,如何实现呢?
代码演示如下:
```
public static void main(String[] args) throws Exception {
	ArrayList<Integer> list = new ArrayList<>();
	list.add(111);
	list.add(222);
	
	//泛型只在编译期有效,在运行期会被擦除掉,所以用反射获取运行时类即字节码来解决:
	Class clazz = Class.forName("java.util.ArrayList");//获取字节码对象,
    Method m = clazz.getMethod("add", Object.class);//通过对象,获取add方法,方法的参数字节码类型是Object.class,
	m.invoke(list, "abc");//方法对象调用方法,传入方法所在的对象和实参,
	
	System.out.println(list);//[111, 222, abc]	
}
```
总结:除了用反射越过泛型检查,还可以自定义一个没有泛型的引用指向有泛型引用的同一个对象,用无泛型的引用添加元素即可:
代码演示如下:
```
public static void main(String[] args) {
	ArrayList<Integer> list = new ArrayList<>();
	list.add(111);
	list.add(222);
	
	ArrayList al = list;//把有泛型引用赋值给无泛型引用,即让两个引用执行同一个对象,面向对象的知识吧
	al.add("黑马666");//无泛型引用添加任意类型元素,输入泛型引用即原对象的值如下:
	System.out.println(list);//[111, 222, 黑马666]
}
```

#27.09_反射(通过反射写一个通用的设置某个对象的某个属性为指定的值)
案例演示:

    public void setProperty(Object obj, String propertyName, Object value){},
	定义一个类,写一个方法,此方法可将obj对象中名为propertyName的属性的值设置为value,并测试调用:
代码演示如下:
```
public class Tool {//公有说明该类在另一个源文件中
	//此方法可将obj对象中名为propertyName的属性的值设置为value:
	public void setProperty(Object obj, String propertyName, Object value) throws Exception {
		Class clazz = obj.getClass();//通过对象调用方法,获取字节码对象,
		Field f = clazz.getDeclaredField(propertyName);//暴力反射获取字段,
		f.setAccessible(true);//去除私有权限,
		f.set(obj, value);//设置属性的值为value
	}
}

//测试类,创建一个学生类作为对象传入我写的工具类方法里,并设置学生类对象的属性姓名为值李四:
public class Test3 {
	public static void main(String[] args) throws Exception {
		Student s = new Student("张三", 23);
		System.out.println(s);//Student [name=张三, age=23]
		
		Tool t = new Tool();//创建工具类对象,
		t.setProperty(s, "name", "李四");//对象调用设置方法,设置对象的属性姓名值为李四,
		System.out.println(s);//Student [name=李四, age=23]
	}
}

class Student {//定义学生类,用于创建学生类对象传入我写的工具类设置方法里
	private String name;
	private int age;
	public Student() {
		super();
		
	}
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}	
}
```
总结:设置某个类对象的属性为指定值,还可以写一个获取某个类对象的属性值方法,这感觉就像内省一样,
哈哈,关于内省是什么?简单理解一下就是可以通过内省的API设置或获取对象的属性值,用法是得到一个属性对象,而属性对象指定就是属性的setter和getter方法,然后通过属性对象的set和get方法
来设置对象的属性值,其实就是做了一下封装,对吧

#27.10_反射(练习)
已知一个类,定义如下:

     package com.heima.test;
     public class DemoClass {
    	public void run() {
    		System.out.println("welcome to heima!");
    	}
    }
    
    (1) 写一个Properties格式的配置文件,配置类的完整名称,即文件里面内容是com.heima.test.DemoClass
    (2) 写一个程序,读取这个Properties配置文件,获得类的完整名称并加载这个类,用反射的方式运行run方法:
代码演示如下:
```
public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("xxx.properties"));//文件内容com.heima.test.DemoClass
    Class clazz = Class.forName(br.readLine());//读取配置文件中类名,获取字节码对象,
    		
    DemoClass dc = (DemoClass) clazz.newInstance();//通过字节码对象方法通过无参构造创建类对象,
    dc.run();//welcome to heima!类对象调用方法输出
}
```
总结:其实这个跟榨汁机和水果案例是一样一样的,对吧,加多练习的机会而已

#27.11_反射(动态代理的概述和实现)
A:动态代理概述:

    代理:本来应该自己做的事情,请了别人来做,被请的人就是代理对象,或者理解为中介就对了,
	举例:春节回家买票让人代买,这个代买人或者是黄牛或者是你认识的人,就是代理对象

	动态代理:程序运行过程中产生的这个对象,而程序运行过程中产生的对象,其实就是反射讲解的内容,
	所以,动态代理,其实就是通过反射来生成一个代理(对象,中介)!!!
		
	在Java中java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口,
	通过使用这个类和接口就可以生成动态代理对象,JDK提供的代理只能针对接口做代理,(我们有更强大的代理cglib):
	
	Proxy类中的方法创建动态代理类对象:
	public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)

	最终会调用InvocationHandler的方法invoke:
	InvocationHandler Object invoke(Object proxy,Method method,Object[] args)
代码演示如下:
```
public class Test {//4.测试类
	public static void main(String[] args) {
		UserImp ui = new UserImp();

        //动态代理就三步:跟着做就好了,不用多理解://1,创建调用处理器接口实现类对象,构造传入接口实现类对象,
		MyInvocationHandler m = new MyInvocationHandler(ui);

		//2.代理类静态方法创建代理对象,强转为接口引用,
        User u = (User)Proxy.newProxyInstance(ui.getClass().getClassLoader(), ui.getClass().getInterfaces(), m);

		//3,接口引用调用方法,最终调用的是调用处理器接口实现类复写的调用方法:
		u.add();//权限校验,添加功能,日志记录
		u.delete();//权限校验,删除功能,日志记录
	}
}

public class MyInvocationHandler implements InvocationHandler {//3.调用处理器接口实现类,实现调用方法
	private Object target;
	
	public MyInvocationHandler(Object target) {//通过有参构造方法给成员变量赋值,
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {//最终调用方法如下:
		System.out.print("权限校验,");
		method.invoke(target, args);//执行被代理对象target的方法,如这里的add方法和delete方法
		System.out.println("日志记录");
		return null;
	}
}

public interface User {//1定义接口,抽象增删方法
	public void add();
	public void delete();
}

public class UserImp implements User {//2接口实现类,实现增删功能
	@Override
	public void add() {
		//System.out.print("权限校验,");//想实现这样的输出,但是不属于我这个方法,不能自己做,找别人做,
		System.out.print("添加功能,");
		//System.out.print("日志记录");
	}

	@Override
	public void delete() {
		//System.out.print("权限校验,");//那么,这个别人就是动态代理对象,
		System.out.print("删除功能,");
		//System.out.print("日志记录");
	}
}
```
总结:动态代理就三步:
1,创建调用处理器接口实现类对象,构造传入接口实现类对象,
2.代理类静态方法创建代理对象,强转为接口引用,
3,接口引用调用方法,最终调用的是调用处理器接口实现类复写的调用方法

#27.12_设计模式(模版(Template)设计模式概述和使用)
A:模版方法设计模式概述:

    模版方法设计模式,就是定义一个算法的骨架,而将具体的算法延迟到子类中去实现:
    
B:优点和缺点:

    a:优点:
	    使用模版方法设计模式,在定义算法骨架的同时,可以很灵活的实现具体的算法,满足用户灵活多变的需求
	    
	b:缺点:
		如果算法骨架有修改的话,则需要修改抽象类
代码演示如下:
```
public class Demo1_Template {//测试类
	public static void main(String[] args) {
		/*long start = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {//这次是统计循环从开始到结束的代码运行时间,
			System.out.println("x");//那我下次要是要统计其他代码运行时间呢,是不是要再写一遍?
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);*/

		Demo d = new Demo();//创建抽象类子类对象,对象调用最终骨架方法:
		System.out.println(d.getTime());
	}
}

//模板方法设计模式就是:
abstract class GetTime {//抽象类定义最终普通方法不让复写,普通方法调用抽象方法,让抽象类的子类去实现抽象方法
	public final long getTime() {
		long start = System.currentTimeMillis();//算法骨架,有种固定的意思但是骨架变时可以修改
		code();//调用抽象方法,让子类去实现这个未知方法!!!
		long end = System.currentTimeMillis();//算法骨架
		return end - start;//算法骨架
	}

	public abstract void code();//抽象方法,让子类强制执行,具体的算法延迟到子类中去实现,具体实现灵活多变,(这也是一种先调用后定义或者实现的思想,提前消费,对吧?)
}

class Demo extends GetTime {
	@Override
	public void code() {
		int i = 0;
		while(i < 100000) {
			System.out.println("x");
			i++;
		}
	}
}
```
总结:模板方法设计模式,其实就是定义一个抽象类,里面定义一个(最终普通方法不让复写)普通方法调用自己的抽象方法,
让抽象类的子类去实现抽象方法,这么一种设计模式,上了就业班就知道经常用模板方法设计模式写BaseActivity抽象基类等

回顾一下,目前我们学过的设计模式,如下6个:

    1,装饰:
    2,单例:
    3,简单工厂:
    4,工厂方法:
    5,适配器:
    6,模版方法:

#27.13_JDK5新特性(自己实现枚举类)
A:枚举概述:

    是指将变量的值一一列举出来,变量的值只限于列举出来的值的范围内,举例:一周只有7天,一年只有12个月等
    
B:回想单例设计模式:单例类,是一个类只有一个实例,

	那么多例类,就是一个类有多个实例,但不是无限个数的实例,而是有限个数的实例,这才能是枚举类,
	说白了,枚举类,就是有限个数的多例类!
	
C:案例演示:

	自己实现枚举类:类的成员变量是类的实例,实例个数有限:
代码演示如下:
实现格式1:无参构造创建对象为本类成员变量:
```
public class Week {//定义一个类,成员变量是本类对象,被公有静态最终修饰
	
	public static final Week MON = new Week();//之前很多人不理解,成员变量为什么可以是一个对象
	public static final Week TUE = new Week();
	public static final Week WED = new Week();
	
	private Week(){}//私有本类构造,不让其他类,创建本类对象
}

测试调用:
public static void main(String[] args) {
	Week mon = Week.MON;//类名点调用静态成员变量
	Week tue = Week.TUE;
	Week wed = Week.WED;
	
	System.out.println(mon);//输出是一个对象的地址值
}
```

实现格式2:有参构造创建对象为本类成员变量:
```
public class Week2 {
	//成员变量是本类对象,通过有参构造创建,
	public static final Week2 MON = new Week2("星期一");
	public static final Week2 TUE = new Week2("星期二");
	public static final Week2 WED = new Week2("星期三");
	
	private String name;
	private Week2(String name){//有参构造,传入姓名给成员变量姓名赋值,
		this.name = name;
	}//私有本类构造,不让其他类创建本类对象,
	public String getName() {//公有方法得到姓名,返回的是成员变量的姓名值
		return name;
	}
}

测试调用:
public static void main(String[] args) {
	Week2 mon = Week2.MON;//类名点调用静态成员变量,得到本类对象,本类对象调用成员方法输出
	System.out.println(mon.getName());//输出传入有参构造的参数星期一
}
```

实现格式3:有参构造和抽象方法,那就通过匿名内部类创建抽象类子类对象,为本类成员方法:
```
public abstract class Week3 {
	public static final Week3 MON = new Week3("星期一") {
		public void show() {
			System.out.println("星期一");
		}
	};
	public static final Week3 TUE = new Week3("星期二"){
		public void show() {
			System.out.println("星期二");
		}
	};
	public static final Week3 WED = new Week3("星期三"){
		public void show() {
			System.out.println("星期三");
		}
	};
	
	private String name;
	private Week3(String name){//私有本类构造,不让其他类创建本类对象
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public abstract void show();//抽象方法
}

测试调用:
public static void main(String[] args) {
	Week3 mon = Week3.MON;//类名点调用静态成员变量,即得到抽象类的子类对象,对象调用方法输出
	mon.show();//星期一
}
```
总结:自己实现枚举类,要求类的成员变量是公有静态最终实例,且实例是有限个数的,然后分别通过无参构造,有参构造,
有参构造并且有抽象方法三种情况创建实例,然后调用实例方法即可,这里重复一遍,实例就是类的对象,这个要知道吧?

回顾一下,目前学过的JDK5新特性,如下7个:

    1,自动拆装箱:
    2,泛型:
    3,可变参数:
    4,静态导入:
    5,增强for循环:
    6,互斥锁:
    7,枚举:
		
#27.14_JDK5新特性(通过enum关键字实现枚举类)
A:案例演示:

    通过enum关键字实现枚举类:
代码演示如下:
实现格式1:
```
public enum Week {//把enum当成class看待即可,enum为枚举的意思,读作枚举类Week,
	MON,TUE,WED;//通过自己写枚举类,我们就知道,这里的枚举值就是一个类对象,对应自己写的无参构造创建对象,
}

调用测试:
public static void main(String[] args) {
	Week mon = Week.MON;//一样的用类名点调用静态成员变量,
	System.out.println(mon);//输出对象,结果是枚举值对象的名字MON,
}
```

实现格式2:
```
public enum Week2 {
	MON("星期一"),TUE("星期二"),WED("星期三");//每个枚举值就是一个本类对象,对应自己写的有参构造创建对象,
	
	private String name;
	private Week2(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}

测试调用:
public static void main(String[] args) {
	Week2 mon = Week2.MON;
	System.out.println(mon.getName());//对象调用成员方法,输出结果是星期一
}
```

实现格式3:
```
public enum Week3 {//注意:abstract关键字不能用来修饰用enum关键字修饰的枚举类,很奇葩,对吧,下面自我解释一下:↓
	MON("星期一"){//每个枚举值就是一个本类对象,对应自己写的有参构造和抽象方法,用匿名内部类创建子类对象
		public void show() {
			System.out.println("星期一");
		}
	},TUE("星期二"){
		public void show() {
			System.out.println("星期二");
		}
	},WED("星期三"){
		public void show() {
			System.out.println("星期三");
		}
	};
	
	private String name;
	private Week3(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public abstract void show();//从这里可以知道,有抽象方法的除了抽象类,还可以是通过enum关键字定义的枚举类!	
}

测试调用:
public static void main(String[] args) {
	Week3 mon = Week3.MON;
	mon.show();//输出结果也是星期一
}
```
总结:自己写枚举类不是JDK1.5的新特性,谁都可以写,用enum关键字代替class定义枚举类,这才是JDK1.5的新特性,再次强调:↓
有抽象方法的除了抽象类,还可以是通过enum关键字定义的枚举类!简直颠覆了我们以前的认知,哈哈,看下面吧:↓
其实,用enum关键字定义的枚举类终究还是类,并且是抽象类Enum的子类,看抽象类Enum的源码可以解开我们的很多困惑,如下:↓
```
public abstract class Enum<E extends Enum<E>>
    implements Comparable<E>, Serializable {
    
    private final String name;

    public final String name() {
        return name;
    }

    private final int ordinal;

    public final int ordinal() {
        return ordinal;
    }

    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public String toString() {
        return name;
    }

    public final boolean equals(Object other) {
        return this==other;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public final int compareTo(E o) {
        Enum other = (Enum)o;
        Enum self = this;
        if (self.getClass() != other.getClass() && // optimization
            self.getDeclaringClass() != other.getDeclaringClass())
            throw new ClassCastException();
        return self.ordinal - other.ordinal;
    }

    public final Class<E> getDeclaringClass() {
        Class clazz = getClass();
        Class zuper = clazz.getSuperclass();
        return (zuper == Enum.class) ? clazz : zuper;
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                                String name) {
        T result = enumType.enumConstantDirectory().get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException(
            "No enum constant " + enumType.getCanonicalName() + "." + name);
    }

    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("can't deserialize enum");
    }
}
```

#27.15_JDK5新特性(枚举的注意事项)
A:案例演示:

	定义枚举类要用关键字enum
	所有枚举类都是抽象类Enum的子类
	枚举类的第一行上(有效代码不包括注释)必须是枚举项,最后一个枚举项后的分号是可以省略的,
	但是分号后面如果有其他的代码,这个分号自然就不能省略,建议不要省略
	枚举类可以有构造器,但必须是private的,它默认的也是private的
	枚举类也可以有抽象方法,但是枚举项必须重写该方法
	枚举在switch语句中的使用
	
总结:控制变量演示即可,跟用class定义枚举类不同的是,通过enum定义的枚举类的第一行有效代码(不含注释)必须是枚举项,
最后一个枚举项后的分号,如果其后面有代码,自然就不能省略,建议永远不要省略

#27.16_JDK5新特性(枚举类的常见方法)
A:枚举类的常见方法,枚举类的抽象父类是Enum类,下面很多方法来自其源码,其源码在上面,请回头看看:↑,有对应的标记"↓":

    int ordinal()//返回枚举项的编号,从0开始
	int compareTo(E o)//比较的是枚举项的编号,返回的是编号相减的差值
	
	String name()//获取实例的名称
	String toString()//调用枚举类重写之后的toString()方法
	
	<T> T valueOf(Class<T> type,String name)//通过字节码对象和实例名字,获取枚举项如星期一
	values()//通过枚举类调用返回一个枚举类数组,遍历数组元素得到的是每一个枚举项,如星期一,星期二...
	此方法虽然在JDK文档中查找不到,但是每个枚举类都具有该方法,它用来遍历枚举类的所有枚举值,非常方便
	
B:案例演示:

    枚举类的常见方法:
代码演示如下:
```
public class Demo2_Enum {
	public static void main(String[] args) {
		//demo1();
		
        //Week2 mon = Week2.valueOf(Week2.class, "MON");//通过字节码对象获取枚举项,
        //System.out.println(mon);//星期一
		
		Week2[] arr = Week2.values();
		for (Week2 week2 : arr) {
			System.out.println(week2);//输出的是枚举项,如星期一,星期二,等等
		}
	}

	public static void demo1() {
		Week2 mon = Week2.MON;
		Week2 tue = Week2.TUE;
		Week2 wed = Week2.WED;
		
		System.out.println(mon.ordinal());//0,枚举项都是有编号的,默认从0开始,外国人喜欢从0开始
		System.out.println(tue.ordinal());//1,
		System.out.println(wed.ordinal());//2,
		
		System.out.println(mon.compareTo(tue));//0-1 = -1,比较的是编号,返回的是编号之间的差值,
		System.out.println(mon.compareTo(wed));//0-2 = -2;
		
		System.out.println(mon.name());//MON,获取实例名称,
		System.out.println(mon.toString());//星期一,调用手动重写之后的toString方法,如下所示:↓
	}
}

//其他源文件中定义的枚举类:↓
public enum Week2 {
	MON("星期一"),TUE("星期二"),WED("星期三");
	
	private String name;
	private Week2(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {//实例通过有参构造传入名字创建,所以调用mon.toString();输出的是星期一↑
		return name;
	}
}
```

#27.17_JDK7新特性(JDK7的六个新特性回顾和讲解)
    1,二进制字面量:
    2,数字字面量可以出现下划线:
    3,switch语句可以用字符串:
    4,泛型简化,菱形泛型:
    5,异常的多个catch合并,每个异常用或|:
    6,try-with-resources 语句:
    
代码演示如下:
```
public class Demo1_JDK7 {
	public static void main(String[] args) {
		System.out.println(0b110);//6
		System.out.println(100_000);//100000
	}
}
```

try-with-resources 语句:↓

    JDK1.7InputStream和OutputStream实现了AutoCloseable自动关闭接口,会在读写代码执行完之后调用close方法关闭流:
    
    	try(
    		FileInputStream fis = new FileInputStream("aaa.txt");
    		FileOutputStream fos = new FileOutputStream("bbb.txt");
    		MyClose mc = new MyClose();
    	){
    		int b;//读写代码执行完后,上面实现了实现AutoCloseable接口的对象回调方法自动关闭流
    		while((b = fis.read()) != -1) {
    			fos.write(b);
    		}
    	}
    原理:
    	在try()中创建的流对象必须实现了AutoCloseable这个接口,如果实现了,
    	在try后面的{}(读写代码)执行后就会自动调用流对象的close方法,将流关掉:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	//demo1();
	
	try(
	    FileInputStream fis = new FileInputStream("xxx.txt");//实现了AutoCloseable接口
		FileOutputStream fos = new FileOutputStream("yyy.txt");//实现了AutoCloseable接口
        MyClose mc = new MyClose();//自定义类也要实现AutoCloseable接口才能调用关闭方法自动关闭
	){
//下面是执行代码,执行完之后,上面的实现了AutoCloseable接口的类对象就会调用关闭方法关闭流:
		int b;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
        System.out.println("执行代码,执行完之后,上面的实现了AutoCloseable接口的类对象就会调用关闭方法关闭流等");
	}
}

class MyClose implements AutoCloseable {
	public void close() {
		System.out.println("我关了");//执行代码执行完后调用这个方法
	}
}
```
总结:JDK1.7InputStream和OutputStream实现了AutoCloseable自动关闭接口,会在读写代码执行完之后调用close方法关闭流,
这个面试可能会考,要注意

#27.18_JDK8新特性(JDK8的新特性):
1.接口中有了可以定义有方法体的方法:

    如果是非静态的,必须用default修饰(接口实现类可以不复写这个方法,用接口实现类对象调用)	
    如果是静态的就不用了(并且静态方法可以通过接口名.调用)
    
2.局部内部类在访问他所在方法中的局部变量,可以不写final修饰,但不写默认也是final,因为系统会默认加上:
代码演示如下:
```
//测试类1,由于eclipse不支持JDK1.8,所以我们在EditPlus里面配置再演示JDK1.8的新特性
class Test {
	public static void main(String[] args){
		Demo d = new Demo();
		d.print();//print

		Inter.method();//static method,接口名调用接口里的静态普通方法
	}
}

//接口,里面定义非静态普通方法加上default修饰,静态普通方法跟平时一样,这就是JDK1.8的新特性,6吧?
interface Inter {
	public default void print(){
		System.out.println("print");
	}

	public static void method(){
		System.out.println("static method");
	}
}

//接口实现类,由于实现的接口里没有写抽象方法,所以不用复写任何方法,这里不要混淆JDK其他版本接口也可不写抽象方法哦:
class Demo implements Inter {
	
}

//测试类2,局部内部类在访问他所在方法中的局部变量,可以不写final修饰,但不写默认也是final修饰:
class Test2 {
	public void run() {
		final int x = 10;//final可以写可以不写,不写的时候默认为写
		class Inner {
			public void method() {
				//x=20;//测试知这里会报错,所以知道默认不写也是加上final的,因为最终变量是常量,
				System.out.println(x);//常量只能被赋值一次
			}
		}

		Inner i = new Inner();
		i.method();
	}	
}
```
局部内部类在访问他所在方法中的局部变量必须用final修饰,为什么?
因为当调用这个方法时,局部变量如果没有用final修饰,他的生命周期和方法的生命周期是一样的,
当方法弹栈,这个局部变量也会消失,而如果局部内部类对象还没有马上消失想用这个局部变量,就没有了,
如果用final修饰会在类加载的时候进入常量池,即使方法弹栈,常量池的常量还在,也可以继续使用

总结:JDK8的新特性,接口中可以定义普通方法,如果是非静态的普通方法要用default即缺省来修饰,而静态跟普通静态一样

#27.19_day27总结
    把今天的知识点总结一遍:
    反射-模板方法设计模式-JDK5新特性包括枚举-JDK7新特性-JDK8新特性


