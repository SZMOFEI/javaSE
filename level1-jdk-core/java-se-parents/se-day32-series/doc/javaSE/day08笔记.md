#08.01_面向对象(代码块的概述和分类)(了解)
###A:代码块概述:
    在Java中,使用{}括起来的代码被称为代码块
    
###B:代码块分类:
	根据其位置和声明的不同,可以分为局部代码块,构造代码块,静态代码块,同步代码块(多线程讲解)
	
C:常见代码块的应用如下:
###a:局部代码块:
    在方法中出现:限定变量生命周期,及早释放,提高内存利用率
    
###b:构造代码块(也可加初始化块):
	在类中方法外出现:多个构造方法方法中相同的代码存放到一起,每次调用构造都执行,并且在构造方法前执行
	
###c:静态代码块:
	在类中方法外出现,并加上static修饰:用于给类进行初始化,在加载的时候就执行,并且只执行一次
	一般用于加载驱动
	另外,主方法所在的类的静态代码块优先于主方法执行,为什么?因为主方法所在的类先加载进来,所以其先执行
	
总结:代码块的知识,面试的时候会问,开发不用或者很少用,但也要重视

###08.02_面向对象(代码块的面试题)(掌握)
A:看程序写结果

    class Student {
    	static {
    		System.out.println("Student 静态代码块");
    	}
    	
    	{
    		System.out.println("Student 构造代码块");
    	}
    	
    	public Student() {
    		System.out.println("Student 构造方法");
    	}
    }
    
    class Demo2_Student {
    	static {
    		System.out.println("Demo2_Student静态代码块");
    	}
    	
    	public static void main(String[] args) {
    		System.out.println("我是main方法");
    		
    		Student s1 = new Student();
    		Student s2 = new Student();
    	}
    }
    
总结:执行顺序:主方法所在的类的静态代码块-主方法-静态代码块-构造代码块-构造方法

#08.03_面向对象(继承案例演示)(掌握)
###A:继承(extends):
    让类与类之间产生关系,子父类关系 
    
B:继承案例演示：

	动物类,猫类,狗类
	定义两个属性(颜色,腿的个数),两个功能(吃饭,睡觉)
	
C:案例演示:

	使用继承前:猫狗类里面都写一样的代码,复用性太差,如下:
```
class com.mofei.spring.demo.Cat {
	String color;//动物的颜色
	int leg;//动物腿的个数

	public void eat() {//吃饭的功能
		System.out.println("吃饭");
	}

	public void sleep() {//睡觉的功能
		System.out.println("睡觉");
	}
}

class Dog {
	String color;//动物的颜色
	int leg;//动物腿的个数

	public void eat() {//吃饭的功能
		System.out.println("吃饭");
	}

	public void sleep() {//睡觉的功能
		System.out.println("睡觉");
	}
}
```
D:案例演示:

	使用继承后:只要动物类写相同的代码,猫狗类都继承就有了,里面不用写,提高代码的复用性,如下:
```
class Animal {
	String color;//动物的颜色
	int leg;//动物腿的个数

	public void eat() {//吃饭的功能
		System.out.println("吃饭");
	}

	public void sleep() {//睡觉的功能
		System.out.println("睡觉");
	}
}

class com.mofei.spring.demo.Cat extends Animal {
	
}

class Dog extends Animal {
	
}
```

#08.04_面向对象(继承的好处和弊端)(掌握)
###A:继承的好处:
    a:提高了代码的复用性
	b:提高了代码的维护性,(如在动物类里增加一个属性,猫狗类继承类动物类就不用写都有了)
	c:让类与类之间产生了关系,是多态的前提
	
###B:继承的弊端:
	类的耦合性增强了
	
	开发的原则:高内聚,低耦合
	耦合:类与类的关系
	内聚:就是自己完成某件事情的能力

#08.05_面向对象(Java中类的继承特点)(掌握)
###A:Java中类的继承特点:
    a:Java只支持单继承,不支持多继承(一个儿子只能有一个爹),(因为有安全隐患,如果支持的话子类调用两个父类中的同名方法不知道调用的是谁)
		
	b:Java支持多层继承(继承体系)(A继承B,B继承C,那么A也便有了C的非私有方法等,即自己没有找爹,爹没有找爷爷)
	
B:案例演示:

    Java中类的继承体系特点:
		如果想用这个体系的所有功能,用最底层的类创建对象
		如果想看这个体系的共性功能,用最顶层的类
代码演示如下:
```
class Demo2_Extends {
	public static void main(String[] args) {
		DemoC d = new DemoC();
		d.show();
	}
}

class DemoA {               //最顶层的类,共性功能
	public void show() {
		System.out.println("DemoA");
	}
}

class DemoB extends DemoA {
	public void method() {
		System.out.println("DemoB");
	}
}

class DemoC extends DemoB {//最底层的类,所有功能
	public void print() {
		System.out.println("DemoC");
	}
}
```

#08.06_面向对象(继承的注意事项和什么时候使用继承)(掌握)
###A:继承的注意事项:
    a:子类只能继承父类所有非私有的成员(成员方法和成员变量)
	b:子类不能继承父类的构造方法,但是可以通过super(马上讲)关键字去访问父类构造方法
	c:不要为了部分功能而去继承
	
###B:什么时候使用继承?
    继承其实体现的是一种关系:"is a",如下:
    	Person
    		Student
    		Teacher
    		
    	水果
    		苹果
    		香蕉
    		橘子
			
	采用假设法,
		如果有两个类A,B,只要他们符合A是B的一种,或者B是A的一种,就可以考虑使用继承!
代码演示如下:
```
class Demo3_Extends {
	public static void main(String[] args) {
		Son s = new Son();
		s.show();//报错,父类私有方法不能继承,因为私有根本就看不到
	}
}

class Father {
	private String name;
	private void show() {
		System.out.println("Hello World!");
	}
}

class Son extends Father {
}
```

#08.07_面向对象(继承中成员变量的关系)(掌握)
A:案例演示
###a:不同名的变量:子类有就用子类的,子类没有就用父类的,其实也是就近原则

###b:同名的变量:就近原则,子类有就使用,否则看父类的,
    子父类出现同名的变量只是在讲课中举例子有,在开发中是不会出现这种情况的,
	子类继承父类就是为了使用父类的成员,那么如果定义了同名的成员变量就没有意义了
代码演示如下:
```
class Demo4_Extends {
	public static void main(String[] args) {
		Son s = new Son();
		s.print();
	}
}

class Father {
	int num1 = 10;
	int num2 = 30;
}

class Son extends Father {
	int num2 = 20;

	public void print() {
		System.out.println(num1);//输出10,就近原则,子类有就用子类,子类没有就用父类
		System.out.println(num2);//输出20,就近原则,谁靠近我就用谁
	}
}
```
总结:就近原则,谁靠近我就用谁,子类有就用子类,子类没有就用父类

#08.08_面向对象(this和super的区别和应用)(掌握)
###A:this和super都代表什么:
    this:代表当前对象的引用,谁来调用我,我就代表谁
	super:代表当前对象父类的引用
	
###B:this和super的使用区别:
    a:调用成员变量:
	    this.成员变量,调用本类的成员变量,也可以调用父类的成员变量
		super.成员变量,调用父类的成员变量
		
	b:调用构造方法:
		this(...),调用本类的构造方法,//注意,这里的...表示可有可无,即分别代表无参和有参的情况
		super(...),调用父类的构造方法//...意思同上注释所示
		
	c:调用成员方法:
		this.成员方法,调用本类的成员方法,也可以调用父类的方法
		super.成员方法,调用父类的成员方法
		
代码演示如下:
```
class Demo4_Extends {
	public static void main(String[] args) {
		Son s = new Son();
		s.print();
	}
}

class Father {
	int num1 = 10;
	int num2 = 30;
}

class Son extends Father {
	int num2 = 20;

	public void print() {
		System.out.println(this.num1);//this调用父类成员
		System.out.println(this.num2);//this调用本类成员
		System.out.println(super.num2);super调用父类成员
	}
}
```
总结:this既能调用本类成员,也能调用父类成员,super只能调用父类成员,成员包括成员变量和成员方法,而构造方法只调用自己的

#08.09_面向对象(继承中构造方法的关系)(掌握)
A:案例演示:
###子类中所有的构造方法默认都会访问父类中空参数的构造方法
###B:为什么呢?
    因为子类会继承父类中的数据,可能还会使用父类的数据
	所以,子类初始化之前,一定要先完成父类数据的初始化
	其实:每一个构造方法的第一条语句默认都是:super();
	
	另外,要知道,Object类是最顶层的父类
代码演示如下:
```
class Demo5_Extends {
	public static void main(String[] args) {
		Son s = new Son();//先输出Father的构造方法,然后输出Son的构造方法
	}
}

class Father extends Object {
	public Father() {
		super();
		System.out.println("Father 的构造方法");
	}
}

class Son extends Father {
	public Son() {
		super();//这是一条语句,如果不写,系统会默认加上,用来访问父类中的空参构造
		System.out.println("Son 的构造方法");
	}
}
```
总结:子类构造初始化之前,一定要完成父类的初始化,子类构造的第一条语句默认是super();这是系统默认,
跟系统默认提供的构造方法规律一样,你不给我给,你给我不给,
另外注意,构造方法不能递归调用(递归就是方法定义的时候调用到方法自己本身,后面会讲,先知道概念即可)!

#08.10_面向对象(继承中构造方法的注意事项)(掌握)
A:案例演示:
###父类没有无参构造方法,子类怎么办?
    super解决:在子类构造方法第一行调用父类有参构造即可
    
	this解决:在子类构造方法第一行调用子类的有参构造,子类自己的有参构造调用父类的有参构造,从而间接调用父类的有参构造来解决!
	
###B:注意事项:
    super(…)或者this(….)必须出现在构造方法的第一条语句上,(且只能出现一条,不能两个都是this或者super)!
    另外,super(…)或者this(….)构造方法不管无参还是有参,都只能在构造方法里调用,其他方法里不能调用!
代码演示如下:
```
class Demo6_Extends {
	public static void main(String[] args) {
		Son s1 = new Son();
		System.out.println(s1.getName() + "..." + s1.getAge());
		System.out.println("--------------------");
		
		Son s2 = new Son("张三",23);
		System.out.println(s2.getName() + "..." + s2.getAge());
	}
}

class Father {
	private String name;//姓名
	private int age;//年龄

	//public Father() {//空参构造
	//	System.out.println("Father 空参构造");
	//}

	public Father(String name,int age) {//有参构造
		this.name = name;
		this.age = age;
		System.out.println("Father 有参构造");
	}

	public void setName(String name) {//设置姓名
		this.name = name;
	}

	public String getName() {//获取姓名
		return name;
	}

	public void setAge(int age) {//设置年龄
		this.age = age;
	}

	public int getAge() {//获取年龄
		return age;
	}
}

class Son extends Father {
	public Son() {//空参构造
		this("王五",25);//本类中的构造方法,只能写一条不论前后
		//super("李四",24);//调用父类中的构造方法
		
		System.out.println("Son 空参构造");
	}

	public Son(String name,int age) {//有参构造
		super(name,age);//只能写一个super或者this语句
		System.out.println("Son 有参构造");
	}
}
```
总结:每一个构造方法的第一条语句默认都是:super();
但是如果我们手动改为this(有参或者无参)或者super(有参或者无参),(只能调用其中一个方法,否则报错),
就会调用我们修改的方法,原有的super();不再给出

#08.11_面向对象(继承中的面试题)(掌握)
A:案例演示:
 		
    //看程序写结果1
    class Fu{
    	public int num = 10;
    	public Fu(){
    		System.out.println("fu");
    	}
    }
    
    class Zi extends Fu{
    	public int num = 20;
    	public Zi(){
    		System.out.println("zi");
    	}
    	public void show(){
    		int num = 30;
    		System.out.println(num);
    		System.out.println(this.num);
    		System.out.println(super.num);
    	}
    }
    
    class Test1_Extends {
    	public static void main(String[] args) {
    		Zi z = new Zi();
    		z.show();
    	}
    }
###总结:考点就是子类构造的第一条语句默认是super();所以先去完成父类初始化

    //看程序写结果2
    class Fu {
    	static {
    		System.out.println("静态代码块Fu");
    	}
    
    	{
    		System.out.println("构造代码块Fu");
    	}
    
    	public Fu() {
    		System.out.println("构造方法Fu");
    	}
    }
    
    class Zi extends Fu {
    	static {
    		System.out.println("静态代码块Zi");
    	}
    
    	{
    		System.out.println("构造代码块Zi");
    	}
    
    	public Zi() {
    		System.out.println("构造方法Zi");
    	}
    }
    
    class Test2_Extends {
    	public static void main(String[] args) {
    		Zi z = new Zi();
    	}
    }

###总结:先走父类子类的静态代码块,然后走父类初始化先走父类构造代码块再走构造方法,父类初始化完毕,然后子类初始化先走子类构造代码块再走子类构造方法

#08.12_面向对象(继承中成员方法关系)(掌握)
A:案例演示:
###a:不同名的方法:就近原则,子类没有,找父类,父类没有找最终父类,没有报错

###b:同名的方法:就近原则,子类有就使用子类的
    子类对象调用方法的时候:
	    先找子类本身,再找父类(说白了其实还是就近原则的应用)
	    
代码演示如下:
```
//这个例子也能引出下面的重写的概念
class Demo7_Extends {
	public static void main(String[] args) {
		Son s = new Son();
		s.print();//Zi print
		s.method();//Zi Method"
	}
}

class Father {
	public void print() {
		System.out.println("Fu print");
	}
}

class Son extends Father {
	public void method() {
		System.out.println("Zi Method");
	}

	public void print() {
		//super.print();//super可以调用父类的成员方法//Fu print
		System.out.println("Zi print");
	}
}
```
总结:就近原则,谁靠近我就用谁,子类有就用子类,子类没有就用父类

#08.13_面向对象(方法重写概述及其应用)(掌握)
###A:什么是方法重写?
    重写:子父类出现了一模一样的方法(注意:返回值类型可以是子父类,如下所示↓)
    
代码演示如下:
```
class Demo4_Override {
	public static void main(String[] args) {
	System.out.println("重写:子父类出现了一模一样的方法(注意:返回值类型可以是子父类)");//输出没问题
		Father f = new Son();
		f.method().print();//输出Student
	}
}

class Person {
	public void print() {
		System.out.println("Person");
	}
}

class Student extends Person {
	public void print() {
		System.out.println("Student");
	}
}

class Father { 
	public Person method() {
		return new Person();
	}
}

class Son extends Father {
	public Student method() {//重写了父类方法,但是返回值是父类返回值的子类,也是可以的,子类更加强大了
		return new Student();//注意,反过来则不行,不能父类返回子类,子类返回父类返回值的子类,乱套了
	}
}
```

###B:方法重写的应用:
    当子类需要父类的功能,而功能主体子类有自己特有内容时,可以重写父类中的方法,这样,
	即沿袭了父类的功能,又定义了子类特有的内容
	
C:案例演示

    定义一个手机类
代码演示如下:
```
class Demo7_Phone {
	public static void main(String[] args) {
		Ios8 i = new Ios8();
		i.siri();//输出说中文,speak English
		i.call();//输出打电话
	}
}

class Ios7 {
	public void call() {
		System.out.println("打电话");
	}

	public void siri() {
		System.out.println("speak English");
	}
}

class Ios8 extends Ios7 {
	public void siri() {//重写父类方法,可以沿袭父类功能,增加自己的特有新功能
		
		System.out.println("说中文");
		super.siri();
	}
}
```
总结:重写作用:重写父类方法,可以沿袭父类功能,增加自己的特有新功能

#08.14_面向对象(方法重写的注意事项)(掌握)
###A:方法重写注意事项:
    a:父类中私有方法不能被重写:
		因为父类私有方法,子类根本就无法继承
		
	b:子类重写父类方法时,访问权限不能更低,(否则编译不通过):
		最好就一致
		
	c:父类静态方法,子类也必须通过静态方法进行重写(否则编译不通过):
		其实这个算不上方法重写,但是现象确实如此,至于为什么算不上方法重写,多态中我会讲解(静态只能覆盖静态)
		
	d:子类重写父类方法的时候,最好声明一模一样(返回值类型可以是子父类)
	
B:案例演示

	方法重写注意事项
总结:父类中私有方法不能被重写,
可以从重写的应用和多态应用或者重写的英文Override来理解,ride是骑在什么之后,既要有父类功能的基础上,私有方法不能被继承,所以不能重写

#08.15_面向对象(方法重写的面试题)(掌握)
A:方法重写的面试题:

	Override和Overload的区别?Overload能改变返回值类型吗?
	    答:
###Override方法重写:子类中出现了和父类中方法声明一模一样的方法,与返回值类型有关,返回值是一致(或者是子父类)的;
	    
###Overload方法重载:本类中出现的方法名一样,参数列表不同的方法,与返回值类型无关,
	    所以,Overload可以改变返回值类型,只看参数列表

#08.16_面向对象(使用继承前的学生和老师案例)(掌握)
A:案例演示

    使用继承前的学生和老师案例
	属性:姓名,年龄
	行为:吃饭
	老师有特有的方法:讲课
	学生有特有的方法:学习
总结:跟上面的动物,猫狗类继承前和继承后的例子是一样的,可以当做练习

#08.17_面向对象(使用继承后的学生和老师案例)(掌握)
A:案例演示

	使用继承后的学生和老师案例
总结:跟上面的动物,猫狗类继承前和继承后的例子是一样的,可以当做练习

###08.18_面向对象(猫狗案例分析,实现及测试)(掌握)
A:猫狗案例分析
B:案例演示

	猫狗案例继承版
	属性:毛的颜色,腿的个数
	行为:吃饭
	猫特有行为:抓老鼠catchMouse
	狗特有行为:看家lookHome
代码演示如下:
```
class Test5_Animal {
	public static void main(String[] args) {
		com.mofei.spring.demo.Cat c1 = new com.mofei.spring.demo.Cat("花",4);
		System.out.println(c1.getColor() + "..." + c1.getLeg());
		c1.eat();
		c1.catchMouse();

		Dog d1 = new Dog("黑",2);
		System.out.println(d1.getColor() + "..." + d1.getLeg());
		d1.eat();
		d1.lookHome();
	}
}

class Animal {
	private String color;//毛的颜色
	private int leg;//腿的个数

	public Animal(){}

	public Animal(String color,int leg) {
		this.color = color;
		this.leg = leg;
	}

	public void setColor(String color) {//设置颜色
		this.color = color;
	}

	public String getColor() {//获取颜色
		return color;
	}

	public void setLeg(int leg) {//设置腿的个数
		this.leg = leg;
	}

	public int getLeg() {//获取腿的个数
		return leg;
	}

	public void eat() {//吃饭
		System.out.println("吃饭");
	}
}

class com.mofei.spring.demo.Cat extends Animal {
	public com.mofei.spring.demo.Cat() {}	//空参构造

	public com.mofei.spring.demo.Cat(String color,int leg) {//有参构造
		super(color,leg);
	}

	public void eat() {//吃鱼
		System.out.println("猫吃鱼");
	}

	public void catchMouse() {//抓老鼠
		System.out.println("抓老鼠");
	}
}

class Dog extends Animal {
	public Dog() {}//空参构造

	public Dog(String color,int leg) {//有参构造
		super(color,leg);
	}

	public void eat() {//吃肉
		System.out.println("狗吃肉");
	}

	public void lookHome() {//看家
		System.out.println("看家");
	}
}
```

#08.19_面向对象(final关键字修饰类,方法以及变量的特点)(掌握)
A:final概述:final关键字,最终的意思

###B:final修饰特点:
    修饰类,类不能被继承
	修饰变量,变量就变成了常量,只能被赋值一次
	修饰方法,方法不能被重写
	
C:案例演示

	final修饰特点
代码演示如下:
```
class Demo1_Final {
	public static void main(String[] args) {
		Son s = new Son();
		s.print();
	}
}

/*
final class Father {
	public void print() {
		System.out.println("访问底层数据资源");
	}
	
	//public final void print() {//不能被子类重写,编译报错不通过
	//	System.out.println("访问底层数据资源");
	//}
}
*/

class Son /*extends Father*/ {
	final int NUM = 10;//成员变量被final修饰,只能通过显示初始化,构造代码块初始化,构造方法初始化赋值
	
	public static final double PI = 3.14;//final修饰变量叫做常量,一般会与public static共用
	
	public void print() {
		//NUM = 20;//成员变量被final修饰,不能在成员方法里赋值,去掉final即可赋值
		System.out.println(NUM);
	}
}
```
总结:要注意final关键字修饰成员变量的初始化问题,要跟局部变量区分开

###08.20_面向对象(final关键字修饰局部变量)(掌握)
A:案例演示:

	方法内部或者方法声明上都演示一下(了解)

###基本类型,是值不能被改变
###引用类型,是地址值不能被改变,对象中的属性可以改变,
    如学生类对象被final修饰,不能再给其赋值另一个对象即地址值,
	但是可以通过对象的方法设置对象所属类的属性
	
代码演示如下:
```
class Demo2_Final {
	public static void main(String[] args) {
		final int num = 10;
		//num = 20;//报错,常量不能再赋值
		System.out.println(num);

		final Person p = new Person("张三",23);
		//p = new Person("李四",24);//报错,常量存储的地址值不能再赋值
		p.setName("李四");
		p.setAge(24);//但是,对象中的属性可以改变↓

		System.out.println(p.getName() + "..." + p.getAge());//24

		method(10);
		method(20);
	}

	public static void method(final int x) {
		System.out.println(x);
	}
}

class Person {
	private String name;//姓名
	private int age;//年龄

	public Person(){}//空参构造

	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {//设置姓名
		this.name = name;
	}

	public String getName() {//获取姓名
		return name;
	}

	public void setAge(int age) {//设置年龄
		this.age = age;
	}

	public int getAge() {//获取年龄
		return age;
	}
}
```

#08.21_面向对象(final修饰变量的初始化时机)(掌握)
###A:final修饰变量的初始化时机:
    显示初始化 
	在对象构造完毕前即可
	
代码演示如下:
```
class Demo3_Final {
	public static void main(String[] args) {
		Demo d = new Demo();
		d.print();
	}
}

/*
    A:final修饰变量的初始化时机:
	显示初始化
	在对象构造完毕前即可
*/
class Demo {
	final int num;//final修饰的成员变量的默认初始化值是无效值,假如有效的话那永远是0,那就没有意思了,
	//只能通过显示初始化,构造代码块,构造方法三种方式之一赋值:↓
	//{
	//	num =999;//除了直接赋值显示初始化,还可以通过构造代码块赋值
	//}
	
	public Demo() {
		num = 10;////除了直接赋值显示初始化,还可以通过构造方法赋值
	}
	
	public void print() {
		//num = 666;//报错,这样赋值也是不行的,要跟不加final修饰时的成员变量赋值(默认值有效)区别开来,
		System.out.println(num);//所以要学习final修饰成员变量的初始化时机
	}
}
```

    总结:被final修饰的成员变量的默认初始化值无效,如final int NUM;系统会认为可以尚未初始化变量,报错,
    这点跟局部变量不一样,局部变量如final int NUM;在没有使用之前,就算没有赋值,也不会报错,这点要区分开,
    可以通过显示赋值来显示初始化或者通过构造方法(或者构造代码块)给其赋值来初始化来解决问题
	
#08.22_day08总结
    把今天的知识点总结一遍:
    代码块-继承-方法重写-final关键字
    
