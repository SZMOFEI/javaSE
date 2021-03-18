#06.01_面向对象(面向对象思想概述)(了解)
###A:面向过程思想概述:
    第一步
	第二步 
###B:面向对象思想概述:
    找对象(第一步,第二步) 
###C:举例:
	买煎饼果子
	洗衣服 
###D:面向对象思想特点:
    a:是一种更加符合我们思想习惯的思想
	b:可以将复杂的事情简单化
	c:将我们从执行者变成了指挥者,角色发生了转换
###E:面向对象开发:
	就是不断的创建对象,使用对象,指挥对象做事情
###F:面向对象设计:
	其实就是在管理和维护对象之间的关系
###G:面向对象特征:
    封装(encapsulation)
	继承(inheritance)
	多态(polymorphism)
总结:思想很重要,简单介绍一下,要求能够举例子说明即可

#06.02_面向对象(类与对象概述)(掌握)
###A:我们学习编程是为了什么
    为了把我们日常生活中事物用学习语言描述出来
###B:我们如何描述现实世界的事物
	属性,就是该事物的描述信息(事物身上的名词)
	行为,就是该事物能够做什么(事物身上的动词)
###C:Java中最基本的单位是类,Java中用class描述事物也是如此
	成员变量,就是事物的属性
	成员方法,就是事物的行为
###D:定义类其实就是定义类的成员(成员变量和成员方法)
	a:成员变量,和以前定义变量是一样的,只不过位置发生了改变,在类中,方法外
	b:成员方法,和以前定义方法是一样的,只不过把static去掉,后面再详细讲解static的作用
###E:类和对象的概念
	a:类:是一组相关的属性和行为的集合(其实类是一个集合,里面装的是属性和行为)
	b:对象:是该类事物的具体体现
	c:举例:
	    类,对应学生
		对象,对应具体的某个学生就是一个对象
总结:还是要多举例子,把概念搞清楚,下面定义类的时候就没有问题了

#06.03_面向对象(学生类的定义)(掌握)
A:学生事物
B:学生类
C:案例演示

    属性:姓名,年龄,性别
	行为:学习,睡觉
代码演示如下:
```
class Student {
    //成员变量↓
	String name;//姓名属性
	int age;//年龄属性
	String gender;//性别属性

	//成员方法↓
	public void study() {//学习行为
		System.out.println("学生学习");
	}

	public void sleep() {//睡觉行为
		System.out.println("学生睡觉");
	}
}
```

#06.04_面向对象(手机类的定义)(掌握)
模仿学生类,让学生自己完成:

    属性:品牌(brand)价格(price)
	行为:打电话(call),发信息(sendMessage),玩游戏(playGame)
总结:活学活用,当堂完成

#06.05_面向对象(学生类的使用)(掌握)
###A:文件名问题:
    在一个java文件中写两个类:一个基本的类,一个测试类
	建议:文件名称和测试类名称一致
###B:如何使用对象?
	创建对象并使用:
	格式:类名 对象名 = new 类名();
###C:如何使用成员变量呢?
	对象名.变量名
###D:如何使用成员方法呢?
	对象名.方法名(...)
代码演示如下:
```
class Demo1_Student {
	public static void main(String[] args) {
		//创建对象的格式:类名 对象名 = new 类名();
		Student s = new Student();
		
		//如何使用成员变量呢?//对象名.变量名
		s.name = "张三";
		s.age = 23;
		System.out.println(s.name + "..." + s.age);
		
		//如何使用成员方法呢?//对象名.方法名(...)
		s.study();
		s.sleep();
	}
}
```

#06.06_面向对象(手机类的使用)(掌握)
A:学生自己完成

	模仿学生类,让学生自己完成
总结:完成练习
	
#06.07_面向对象(一个对象的内存图)(掌握)
A:画图演示:见截图

    一个对象
代码演示如下:
```
class Demo1_Car {
	public static void main(String[] args) {
		Car c1 = new Car();

		c1.color = "red";
		c1.num = 8;

		c1.run();
	}
}
```
总结:方法与方法是平级的,所以main方法里面创建对象调用了成员方法,成员方法在栈内与main方法属于不同的块,方法运行完之后就会弹栈

#06.08_面向对象(二个对象的内存图)(了解)
A:画图演示:见截图

	二个不同的对象
代码演示如下:
```
class Demo1_Car {
	public static void main(String[] args) {
		Car c1 = new Car();
		c1.color = "red";
		c1.num = 8;
		c1.run();
		
		Car c2 = new Car();
		c2.color = "black";
		c2.num = 4;
		c2.run();

		c2 = null;//手动给引用赋值为null,把原来的地址值覆盖掉了
		c2.run();//空指针异常		
	}
}
```
总结:其实跟一个对象的内存图是一样的,另外也不必手动给引用赋值为null,当main方法运行完毕之后也会弹栈,引用的地址值也就没了,对应的对象也会成为垃圾等待垃圾回收器进行回收

#06.09_面向对象(三个引用两个对象的内存图)(了解)
A:画图演示:见截图

    三个引用,有两个对象的引用指向同一个地址
代码演示如下:
```
class Demo1_Car {
	public static void main(String[] args) {
		Car c1 = new Car();
		c1.color = "red";
		c1.num = 8;
		c1.run();
		
		Car c2 = new Car();
		c2.color = "black";
		c2.num = 4;
		c2.run();

		Car c3 = c2;//引用给引用赋值,赋的是引用的地址值,同一个地址值指向的是同一个对象
		c3.run();		
	}
}
```
总结:这个就当练手,引用给引用赋值,赋的是引用的地址值,同一个地址值指向的是同一个对象

#06.10_面向对象(成员变量和局部变量的区别)(掌握)
###A:在类中的位置不同:
    成员变量：在类中方法外
	局部变量：在方法定义中或者方法声明上
###B:在内存中的位置不同:
	成员变量：在堆内存(成员变量属于对象,对象进堆内存)
	局部变量：在栈内存(局部变量属于方法,方法进栈内存)
###C:生命周期不同:
	成员变量：随着对象的创建而存在,随着对象的消失而消失
	局部变量：随着方法的调用而存在,随着方法的调用完毕而消失
###D:初始化值不同:
	成员变量：有默认初始化值
	局部变量：没有默认初始化值,必须定义,赋值,然后才能使用
	
###注意事项：
    局部变量名称可以和成员变量名称一样,在方法中使用的时候,采用的是就近原则
	基本数据类型变量包括哪些:byte,short,int,long,float,double,boolean,char
	引用数据类型变量包括哪些:数组,类,接口,枚举
代码演示如下:
```
class Demo2_Person {
	public static void main(String[] args) {
		Person p = new Person();
		p.speak(666);
	}
}

class Person {
	String name;//成员变量
	int num;

	public void speak(int x) {
		int num;//x和num都是局部变量
		System.out.println(name);//null
		num = 666;//局部变量使用之前必须赋值,否则报错变量可能尚未被初始化
		System.out.println(num);//666
	}
}
```
总结:要从类中位置,内存中的位置,生命周期,初始化值四个方面来比较成员变量与局部变量的不同
	
#06.11_面向对象(方法的形式参数是类名的时候如何调用)(掌握)
方法的参数是类名,如public void print(Student s){}//print(new Student());
###如果你看到了一个方法的形式参数是一个类类型(引用类型),这里其实需要的是该类的对象
总结:其实很容易理解,例如上面的参数需要的就是一个学生类类型的对象

#06.12_面向对象(匿名对象的概述和应用)(掌握)
###A:什么是匿名对象:
    没有名字的对象 
###B:匿名对象应用场景:
	a:调用方法,仅仅只调用一次的时候
		那么,这种匿名调用有什么好处吗?
			节省代码
		注意:调用多次的时候,不适合,匿名对象调用完毕就是垃圾,可以被垃圾回收器回收
		
	b:匿名对象可以作为实际参数传递,如下:
	public static void method(Car cc) {//调用时传入实参相当于Car cc = new Car();
		cc.color = "red";
		cc.num = 8;
		cc.run();
	}
C:案例演示

	匿名对象应用场景
代码演示如下:
```
class Demo2_Car {
	public static void main(String[] args) {
		Car c1 = new Car();//创建有名字的对象
		c1.run();
		c1.run();

		new Car().run();//匿名对象调用方法
		new Car().run();//匿名对象只适合对方法的一次调用,因为调用多次就会产生多个对象,不如用有名字的对象	

		//匿名对象可以调用属性,但是没有意义,因为调用后对象就变成垃圾,如果需要赋值还是用有名字对象
		new Car().color = "red";
		new Car().num = 8;
		new Car().run();//null...0
	}
}

class Car {
	String color;//颜色
	int num;//轮胎数

	public void run() {
		System.out.println(color + "..." + num);
	}
}
```
总结:匿名对象就是没有名字的对象,最好用于一次性的逻辑,每new一次就是一个新的对象,调用完毕对象就成为垃圾,
另外别忘了还可以当做实参传递

#06.13_面向对象(封装的概述)(掌握)
###A:封装概述:
    是指隐藏对象的属性和实现细节,仅对外提供公共的访问方式
###B:封装好处:
    隐藏实现细节,提供公共的访问方式
	提高了代码的复用性
	提高安全性
###C:封装原则:
	将不需要对外提供的内容都隐藏起来
	把属性隐藏,提供公共方法对其访问
总结:想想送礼物时,礼物的包装封装就懂相关概念好处和原则

#06.14_面向对象(private关键字的概述和特点)(掌握)
A:人类赋值年龄的问题
###B:private关键字特点
    a:是一个权限修饰符
	b:可以修饰成员变量和成员方法
	c:被其修饰的成员只能在本类中被访问
C:案例演示

    封装和private的应用:
	A:把成员变量用private修饰
	B:提供对应的getXxx()和setXxx()方法
总结:private仅仅是封装的一种体现形式,不能说封装就是私有
代码演示如下:
```
class Demo1_Person {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.name = "张三";		//调用姓名属性并赋值
		//p1.age = -17;			//调用年龄属性并赋值
		//p1.speak();			//调用行为

		p1.setAge(-17);
		System.out.println(p1.getAge());
	}
}

class Person {
	String name;				    //姓名
	private int age;			    //年龄
	
	public void setAge(int a) {		//设置年龄
		if (a > 0 && a < 200) {
			age = a;
		}else {
			System.out.println("请回火星吧,地球不适合你");
		}
		
	}

	public int getAge() {			//获取年龄
		return age;
	}

	public void speak() {
		System.out.println(name + "..." + age);
	}
}
```
总结:想想如何防止别人调用我的类并设置类的成员变量如年龄属性为负数

#06.15_面向对象(this关键字的概述和应用)(掌握)
###A:this关键字特点
    代表当前对象的引用//或者说代表当前变量所属方法所属对象的引用
B:案例演示

	this的应用场景
	用来区分成员变量和局部变量重名
代码演示如下:
```
class Demo1_This {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("张三");
		p1.setAge(23);
		System.out.println(p1.getName() + "..." + p1.getAge());

		Person p2 = new Person();
		p2.setName("李四");
		p2.setAge(24);
		System.out.println(p2.getName() + "..." + p2.getAge());
	}
}

class Person {
	private String name;			//姓名
	private int age;			//年龄
	
	public void setAge(int age) {		//设置年龄
		if (age > 0 && age < 200) {
		this.age = age;//因为变量使用遵循就近原则,所以用当前对象的引用this,来区分局部变量和成员变量重名
			//System.out.println(age);
		}else {
			System.out.println("请回火星吧,地球不适合你");
		}
		
	}

	public int getAge() {			//获取年龄
		return age;//就近原则,没有局部的,当然是用的是成员变量的,所以这里不用加this.,其实默认是this.
	}

	public void setName(String name) {	//设置姓名
		this.name = name;
		//System.out.println(name);
	}

	public String getName() {
		return name;
	}
}
```
总结:因为变量使用遵循就近原则,所以用当前对象的引用this,来区分局部变量和成员变量重名

#06.16_面向对象(手机类代码及其测试)(掌握)
A:学生练习

    请把手机类写成一个标准类,然后创建对象测试功能
代码演示如下:
```
class Demo2_Phone {//测试类
	public static void main(String[] args) {
		Phone p1 = new Phone();
		p1.setBrand("三星");
		p1.setPrice(5288);

		System.out.println(p1.getBrand() + "..." + p1.getPrice());
		p1.call();
		p1.sendMessage();
		p1.playGame();
	}
}

/*
手机类
	属性:品牌brand,价格price
	行为:打电话call,发短信sendMessage,玩游戏,playGame
*/
class Phone {					    //java bean
	private String brand;		    //品牌
	private int price;			    //价格

	public void setBrand(String brand) {//设置品牌
		this.brand = brand;
	}

	public String getBrand() {		 //获取品牌
		return this.brand;	//this.可以省略,你不加系统会默认给你加
	}

	public void setPrice(int price) {//设置价格
		this.price = price;
	}

	public int getPrice() {			//获取价格
		return price;
	}

	public void call() {			//打电话
		System.out.println("打电话");
	}

    public void sendMessage() {		//发短信
		System.out.println("发短信");
	}

	public void playGame() {		//玩游戏
		System.out.println("玩游戏");
	}
}
```			

#06.17_day06总结
    把今天的知识点总结一遍:
    面向对象思想-类与对象-类的定义与使用-对象的内存图-成员变量和局部变量-匿名对象的应用-封装与private-this关键字
    

