#07.01_面向对象(构造方法Constructor概述和格式)(掌握)
###A:构造方法概述和作用:
    给对象的数据(属性)进行初始化
###B:构造方法格式特点:
	a:方法名与类名相同(大小也要与类名一致)
	b:没有返回值类型,连void都没有
	c:没有具体的返回值,但可以return;
	
	怎么记忆?构造方法还叫构造器,名字特别,特点特别!!!
代码演示如下:
```
class Demo1_Constructor {
	public static void main(String[] args) {
		Person p = new Person();//在一创建对象的时候,系统就帮我调用了构造方法
		//p.Person();//构造方法不能用对象调用
		p.show();

		Person p2 = new Person();//再次创建对象,调用的还是无参构造里面赋的值
		p2.show();
	}
}

class Person {
	private String name;
	private int age;

	//构造方法
	public Person() {
		//System.out.println("Hello World!");
		//return;//构造方法也是有return语句的,格式是return;
		name = "张三";
		age = 23;
	}

	public void show() {
		System.out.println(name + "..." + age);
	}
}
```
总结:在一创建对象的时候,系统就帮我调用了构造方法,构造方法不能用对象调用,构造方法也是有return语句的,是return;

#07.02_面向对象(构造方法的重载及注意事项)(掌握)
A:案例演示
###构造方法的重载:
    重载:(前提是在同一个类中),方法名相同,与返回值类型无关(构造方法没有返回值),只看参数列表
###B:构造方法注意事项:
    a:如果我们没有给出构造方法,系统将自动提供一个无参构造方法
    
	b:如果我们给出了构造方法,系统将不再提供默认的无参构造方法,
        注意:这个时候,如果我们还想使用无参构造方法,就必须自己给出,建议永远自己给出无参构造方法
代码演示如下:
```
class Demo2_Person {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.show();

		System.out.println("---------------------");

		Person p2 = new Person("张三",23);
		p2.show();

		System.out.println("---------------------");

		Person p3 = new Person("李四",24);
		p3.show();
	}
}

class Person {
	private String name;//姓名
	private int age;//年龄

	public Person() {//空参构造
		System.out.println("空参的构造");
	}

	public Person(String name,int age) {
		this.name = name;
		this.age = age;
		System.out.println("有参的构造");
	}
	
	public void show() {
		System.out.println(name + "..." + age);
	}
}
```
总结:用上面代码统一变量法控制演示即可
		
#07.03_面向对象(给成员变量赋值的两种方式的区别)
###A:setXxx()方法:
    修改属性值,开发中更多使用它,因为它更加灵活
###B:构造方法:
    给对象中属性进行初始化
代码演示如下:
```
class Demo3_Person {
	public static void main(String[] args) {
		Person p1 = new Person("张三",23);
		//p1 = new Person("张天一",23);	//这种方式看运行结果,貌似是改名了,其实是将原对象变成垃圾
		System.out.println(p1.getName() + "..." + p1.getAge());

		Person p2 = new Person();		//空参构造创建对象
		p2.setName("李四");
		p2.setAge(24);

		p2.setName("李鬼");//实现改名
		System.out.println(p2.getName() + "..." + p2.getAge());
	}
}

class Person {
	private String name;	//姓名
	private int age;		//年龄

	public Person() {//空参构造

	}

	public Person(String name,int age) {//有参构造
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {//设置姓名
		this.name = name;
	}

	public String getName() {	//获取姓名
		return name;
	}

	public void setAge(int age) {	//设置年龄
		this.age = age;
	}

	public int getAge() {		//获取年龄
		return age;
	}
}
```
总结:这里要注意一个问题,成员变量是有默认初始化值的,不能直接用用所谓的先定义,后赋值的方式,
实在要赋值的话可以直接赋值显示初始化,或者通过{}代码块在里面给变量赋值

#07.04_面向对象(学生类的代码及测试)(掌握)
A:案例演示

    学生类:
		成员变量:
			name，age
		构造方法:
			无参,带两个参
		成员方法:
			getXxx()/setXxx()
			show():输出该类的所有成员变量值
			
B:给成员变量赋值:

    a:setXxx()方法
	b:构造方法
	
C:输出成员变量值的方式:

	a:通过getXxx()分别获取然后拼接
	b:通过调用show()方法搞定
代码演示如下:
```
class Demo4_Student {
	public static void main(String[] args) {
		Student s1 = new Student();//使用空参构造
		s1.setName("张三");//设置姓名
		s1.setAge(23);//设置年龄

		System.out.println("我的姓名是:" + s1.getName() + ",我的年龄是:" + s1.getAge());
		//getXxx()获取属性值,可以打印,也可以赋值给其他的变量,做其他的操作
		Student s2 = new Student("李四",24);
		s2.show();//只是为了显示属性值
	}
}

class Student {
	private String name;//姓名
	private int age;//年龄

	public Student(){}//空参构造

	public Student(String name,int age) {//有参构造
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

	public void show() {
		System.out.println("我的姓名是:" + name +  ",我的年龄是:" +  age);
	}
}
```
总结:留意一下输出成员变量值的方式,其实就是定义方法时不同的考虑而已

#07.05_面向对象(手机类的代码及测试)(掌握)
A:案例演示

	模仿学生类,完成手机类代码
总结:来活学活用,当堂练习

#07.06_面向对象(创建一个对象的步骤)(掌握)
A:画图演示:见截图
###画图说明一个对象的创建过程做了哪些事情?
    Student s = new Student();
	1,Student.class加载进内存,
	2,声明一个Student类型引用s,
	3,在堆内存创建对象,
	4,给对象中属性默认初始化值,
	5,属性进行显示初始化,
	6,构造方法进栈,对对象中的属性赋值,构造方法弹栈,
	7,将对象的地址值赋值给s
总结:容易忘了默认初始化后,显示初始化,构造方法进栈初始化
代码演示如下:
```
class Demo1_Student {
	public static void main(String[] args) {
		Student s = new Student();
		s.show();//为什么输出是李四,24,你要明白一个对象的创建过程做了哪些事情
	}
}

class Student {
	private String name = "张三";
	private int age = 23;

	public Student() {
		name = "李四";
		age = 24;
	}

	public void show() {
		System.out.println(name + "..." + age);
	}
}
```

#07.07_面向对象(长方形案例练习)(掌握)
A:案例演示

    需求：
	    定义一个长方形类,定义求周长和面积的方法,
		然后定义一个测试类进行测试
代码演示如下:
```
class Test1_Rectangle {				//Rectangle矩形
	public static void main(String[] args) {
		Rectangle r = new Rectangle(10,20);
		System.out.println(r.getLength());//周长
		System.out.println(r.getArea());//面积
	}
}

class Rectangle {
	private int width;			//宽
	private int high;			//高

	public Rectangle(){}		//空参构造

	public Rectangle(int width,int high) {
		this.width = width;		//有参构造
		this.high = high;
	}

	public void setWidth(int width) {	//设置宽
		this.width = width;
	}

	public int getWidth() {		//获取宽
		return width;
	}

	public void setHigh(int high) {		//设置高
		this.high = high;
	}

	public int getHigh() {		//获取高
		return high;
	}

	public int getLength() {	//获取周长
		return 2 * (width + high);
	}

	public int getArea() {		//获取面积
		return width * high;
	}
}
```

#07.08_面向对象(员工类案例练习)(掌握)
A:案例演示

	需求:定义一个员工类Employee
	自己分析出几个成员,然后给出成员变量
		姓名name,工号id,工资salary
	构造方法,
		空参和有参的,
	getXxx()setXxx()方法,
	以及一个显示所有成员信息的方法,并测试
		work方法
代码演示如下:
```
class Test2_Employee {						//employee员工
	public static void main(String[] args) {
		Employee e = new Employee("令狐冲","9527",20000);
		e.work();
	}
}

class Employee {
	private String name;					//姓名
	private String id;					    //工号
	private double salary;					//工资

	public Employee() {}					//空参构造

	public Employee(String name, String id, double salary) {//有参构造
		this.name = name;
		this.id = id;
		this.salary = salary;
	}

	public void setName(String name) {		//设置姓名
		this.name = name;
	}

	public String getName() {			    //获取姓名
		return name;
	}

	public void setId(String id) {			//设置id
		this.id = id;
	}

	public String getId() {				    //获取id
		return id;
	}

	public void setSalary(double salary) {	//设置工资
		this.salary = salary;
	}
	
	public double getSalary() {			    //获取工资
		return salary;
	}

	public void work() {
		System.out.println("我的姓名是:" + name + ",我的工号是:" + id + ",我的工资是:" + salary 
			+ ",我的工作内容是敲代码");
	}
}
```

#07.09_面向对象(static关键字及内存图)(了解)
A:案例演示

	通过一个案例引入static关键字
	人类:Person,每个人都有国籍,中国
代码演示如下:
```
class Demo1_Static {
	public static void main(String[] args) {
		/*
		Person p1 = new Person();	//创建对象
		p1.name = "苍老师";		//调用姓名属性并赋值
		p1.country = "日本";		//调用国籍属性并赋值
		

		Person p2 = new Person();
		p2.name = "小泽老师";		//调用姓名属性并赋值
		//p2.country = "日本";		//调用国籍属性并赋值

		p1.speak();
		p2.speak();
		*/
		//加了静态,有一次赋值即可,第二个对象使用,不用再赋值,输出也是日本

		Person.country = "日本";	//静态多了一种调用方式,可以通过类名.
		System.out.println(Person.country);
	}
}

class Person {
	String name;				    //姓名
	static String country;			//国籍

	public void speak() {			//说话的方法
		System.out.println(name + "..." + country);
	}
}
```
B:画图演示:见截图

    带有static的内存图
总结:静态static把共享的内容放到了方法区内存块中的字节码内存块的静态区,节省了不用静态时创建多个对象调用的内存
 
#07.10_面向对象(static关键字的特点)(掌握)
###A:static关键字的特点:
    a:随着类的加载而加载
	b:优先于对象存在
	c:被类的所有对象共享:
	    举例:咱们班级的学生应该共用同一个班级编号,
		其实这个特点也是在告诉我们什么时候使用静态?
			如果某个成员变量是被所有对象共享的,那么它就应该定义为静态的
		举例：
		    饮水机(用静态修饰)
			水杯(不能用静态修饰)
			共性用静态,特性用非静态
			
	d:可以通过类名调用:
		其实它本身也可以通过对象名调用,
		推荐使用类名调用,
		静态修饰的内容一般我们称其为:与类相关的,类成员
		
B:案例演示

	static关键字的特点
总结:随类被共享可用类名调用,优先于对象存在

#07.11_面向对象(static的注意事项)(掌握)
###A:static的注意事项:
    a:在静态方法中是没有this关键字的:
		如何理解呢?
			静态是随着类的加载而加载,this是随着对象的创建而存在,
			静态比对象先存在
			
	b:静态方法只能访问静态的成员变量和静态的成员方法:
		静态方法:
			成员变量:只能访问静态变量
			成员方法:只能访问静态成员方法
			
		非静态方法：
			成员变量:可以是静态的,也可以是非静态的
			成员方法:可是是静态的成员方法,也可以是非静态的成员方法
			
		简单记：
			静态只能访问静态
B:案例演示

    static的注意事项
代码演示如下:
```
class Demo2_Static {
	public static void main(String[] args) {
		Demo d = new Demo();
		d.print1();

		Demo.print2();
	}
}

class Demo {
	int num1 = 10;		    //非静态的成员变量
	static int num2 = 20;	//静态的成员变量

	public void print1() {	//非静态的成员方法,既可以访问静态的成员也可以访问非静态的
		System.out.println(num1);
		System.out.println(num2);
	}

	public static void print2() {	//静态的成员方法
	//System.out.println(this.num1);//静态的成员方法不能访问非静态的,错误:无法从静态上下文中引用非静态变量
		System.out.println(num2);
	}
}
```
总结:static静态方法没有this关键字因为对象还可能不存在,静态方法只能访问静态,非静态方法即随便

#07.12_面向对象(静态变量和成员变量的区别)(掌握)
###A:所属不同:
    静态变量属于类,所以也称为为类变量
	成员变量属于对象,所以也称为对象变量(或者实例变量)
###B:内存中位置不同:
	静态变量存储于方法区的静态区
	成员变量存储于堆内存
###C:内存出现时间不同:
	静态变量随着类的加载而加载,随着类的消失而消失
	成员变量随着对象的创建而存在,随着对象的消失而消失
###D:调用不同:
	静态变量可以通过类名调用,也可以通过对象调用
	成员变量只能通过对象名调用
总结:分别从所属分类,内存中位置,生命周期,调用方法四个方面来回答静态变量和成员变量的区别

#07.13_面向对象(main方法的格式详细解释)(了解)
###A:格式
    public static void main(String[] args) {}
###B:针对格式的解释:
    public 被jvm调用,访问权限要足够大,
	static 被jvm调用,不用创建对象,直接用类名访问
	void被jvm调用,不需要给jvm返回值,
	main,一个通用的名称,虽然不是关键字,但是被jvm识别
	String[] args 以前用于接收键盘录入的,具体可打开命令行进入类文件所在路径,javac编译你的类名.java,
	然后输入java命令和类名,在后面输入的任意字符串,以空格分隔,就存储到字符串数组args的对应元素中
C:演示案例

	通过args接收键盘例如数据:
    如命令行方式下用javac编译了Hello类,然后运行时java Hello ni hao ma回车,那么对应的args[0]就是ni,args[1]就是hao,arg[2]就是ma
代码演示如下:
```
class Demo3_Main {
	public static void main(String[] args) {			
		/*
		public : 被jvm调用,所以权限要足够大
		static : 被jvm调用,不需要创建对象,直接类名.调用即可
		void   : 被jvm调用,不需要有任何的返回值
		main   : 只有这样写才能被jvm识别,但是main不是关键字
		String[] args : 以前是用来接收键盘录入的,格式是命令行方式运行时后面加入,
		如这里可用java Demo3_Main ni hao ma回车,注意多个是以空格分隔的
		*/

		System.out.println(args.length);
		for (int i = 0;i < args.length ;i++ ) {
			System.out.println(args[i]);//分别输出ni hao ma
		}
	}
}
```

#07.14_面向对象(工具类中使用静态)(了解)
A:制作一个工具类:

	名字是ArrayTool
	1,获取最大值
	2,数组的遍历
	3,数组的反转:从中间开始分成两段,首尾元素交换位置,依次类推,循环次数是从0到数组长度的一半
代码演示如下:
```
/**
这是一个数组工具类,里面封装了查找数组最大值,打印数组,数组反转的方法
@author fengjia
@version v1.0
*/
public class ArrayTool {
	//如果一个类中所有的方法都是静态的,需要再多做一步,私有构造方法,目的是不让其他类创建本类对象
	//直接用类名.调用即可
	/**
	私有构造方法
	*/
	private ArrayTool(){}

	//1,获取最大值
	/**
	这是获取数组中最大值的方法
	@param arr 接收一个int类型数组
	@return 返回数组中最大值
	*/
	public static int getMax(int[] arr) {
		int max = arr[0];//记录第一个元素
		for (int i = 1;i < arr.length ;i++ ) {	//从第二个元素开始遍历
			if (max < arr[i]) {//max与数组中其他的元素比较
				max = arr[i];//记录住较大的
			}
		}

		return max;//将最大值返回
	}
	
	//2,数组的遍历
	/**
	这是遍历数组的方法
	@param arr 接收一个int类型数组
	*/
	public static void print(int[] arr) {
		for (int i = 0;i < arr.length ;i++ ) {//遍历数组
			System.out.print(arr[i] + " ");
		}
	}
	
	//3,数组的反转
	/**
	这是数组反转的方法
	@param arr 接收一个int类型数组
	*/
	public static void revArray(int[] arr) {
		for (int i = 0;i < arr.length / 2 ;i++ ) {//循环次数是元素个数的一半
			/*
			arr[0]与arr[arr.length-1-0]	交换
			arr[1]与arr[arr.length-1-1]	交换
			arr[2]与arr[arr.length-1-2] 交换
			*/
			int temp = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = temp;
		}
	}
}

class Demo1_ArrayTool {
	public static void main(String[] args) {
		int[] arr = {33,11,22,66,55,44};
		/*ArrayTool at = new ArrayTool();//这个类的构造方法被private修饰私有了,我测试类就访问不到了,
		int max = at.getMax(arr);//获取最值
		System.out.println(max);
		
		at.print(arr);			 //打印
		System.out.println();
	
		System.out.println("反转后:");
		at.revArray(arr);		 //反转
		at.print(arr);	*/

		ArrayTool.print(arr);    //但是由于方法都是静态static修饰的,我可以用类名.调用
	}
}
```

#07.15_面向对象(说明书的制作过程)(了解)
    制作文档说明书,首先要写一个类文件并在里面的方法和类上做文档注释,(注意该类要用public声明为公共的,否则报错并且生成空的文件夹),然后进入该文件所在路径打开命令行输入:javadoc -d 写入你要生成的文档说明书所在的文件夹名字 -version -author 你的类文件名.java回车,等待文档说明书的生成即可,另外并不需要提前编译改类文件,也就是说不需要字节码文件
    
A:对工具类加入文档注释

B:文档注释/** */里可以加入如下符号来生成相关说明:

    @author(提取作者内容)
	@version(提取版本内容)
	@param 参数名称//形式参数的变量名称
	@return 函数运行完返回的数据

C:javadoc命令生成说明书如:javadoc -d 指定的文件目录 -author -version ArrayTool.java

    总结:首先类要用public公有修饰,然后写上文档注释,最后打开命令行,输入:javadoc命令生成说明书:javadoc -d 指定的文件目录 -author -version ArrayTool.java回车即可

#07.16_面向对象(如何使用JDK提供的帮助文档)(了解)
    A:找到文档,打开文档
    B:点击显示,找到索引,出现输入框
    C:你应该知道你找谁?举例:Scanner
    D:看这个类的结构(需不需要导包)
    	成员变量	字段
    	构造方法	构造方法
    	成员方法	方法

#07.17_面向对象(学习Math类的随机数功能)(了解)
    打开JDK提供的帮助文档学习
    A:Math类概述:
    	类包含用于执行基本数学运算的方法
    B:Math类特点:
    	由于Math类在java.lang包下,所以不需要导包,
    	因为它的成员全部是静态的,所以私有了构造方法
    	
    C:获取随机数的方法:
###public static double random():返回带正号的double值,该值大于等于0.0且小于 1.0
    	
    D:我要获取一个1-100之间的随机数,肿么办?
###int number = (int)(Math.random()*100)+1;
代码演示如下:
```
class Demo2_Math {
	public static void main(String[] args) {
		//double d = Math.random();
		//System.out.println(d);
		
		//Math.random()会生成大于等于0.0并且小于1.0的伪随机数
		for (int i = 0;i < 10 ;i++ ) {
			System.out.println(Math.random());
		}

		//生成1-100的随机数推导过程:
		//Math.random()0.0000000 - 0.999999999
		//Math.random() * 100 ====> 0.00000 - 99.999999999
		//(int)(Math.random() * 100) ====> 0 - 99
		//(int)(Math.random() * 100) + 1

		for (int i = 0;i < 10 ;i++ ) {
			System.out.println((int)(Math.random() * 100) + 1);
		}
	}
}
```
	
#07.18_面向对象(猜数字小游戏案例)(了解)
A:案例演示

	需求:猜数字小游戏(数据在1-100之间)
代码演示如下:
```
import java.util.Scanner;
class Test1_GuessNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//创建键盘录入对象
		System.out.println("请输入一个整数,范围在1-100之间");
		int guessNum = (int)(Math.random() * 100) + 1;//心里想的随机数
		
		while (true) {//因为需要猜很多次,所以用无限循环
			int result = sc.nextInt();//大家猜的数
			
			if (result > guessNum) {//如果你们猜的数大于了我心里想的数
				System.out.println("大了");//提示大了
			} else if (result < guessNum) {//如果你们猜的数小于了我心里想的数
				System.out.println("小了");//提示小了
			} else {										//如果既不大也不小
				System.out.println("中了");//中了跳出死循环
				break;
			}
		}
	}
}
```
总结:先定义随机数,然后在while(true)死循环里面,得到每一个键盘录入的整数与随机数不断比较,相等才跳出即可

#07.19_day07总结
    把今天的知识点总结一遍:
    构造方法与其重载-给成员变量赋值-创建一个对象的步骤-static关键字特点以及注意事项-静态变量和成员变量-
    main方法格式详解-工具类中用静态-说明书的制作-Math类的随机数功能

