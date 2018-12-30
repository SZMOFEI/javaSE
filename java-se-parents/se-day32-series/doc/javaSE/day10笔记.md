#10.01_面向对象(package关键字的概述及作用)(了解)
A:为什么要有包?

	将字节码(.class)进行分类存放
	包其实就是文件夹
	
B:包的概述:

    举例:
		学生:增加,删除,修改,查询
		老师:增加,删除,修改,查询
		...
		方案1:按照功能分
			com.heima.add
				AddStudent.java
				AddTeacher.java
			com.heima.delete
				DeleteStudent.java
				DeleteTeacher.java
			com.heima.update
				UpdateStudent.java
				UpdateTeacher.java
			com.heima.find
				FindStudent.java
				FindTeacher.java
		
		方案2:按照模块分
			com.heima.teacher
				AddTeacher.java
				DeleteTeacher.java
				UpdateTeacher.java
				FindTeacher.java
			com.heima.student
				AddStudent.java
				DeleteStudent.java
				UpdateStudent.java
				FindStudent.java

#10.02_面向对象(包的定义及注意事项)(掌握)
###A:定义包的格式:
    package 包名;
	多级包用.分开即可
	
###B:定义包的注意事项:
    A:package语句必须是程序的第一条可执行的代码
	B:package语句在一个java文件中只能有一个
	C:如果没有package,默认表示无包名
	
C:案例演示:

	包的定义及注意事项:
	
总结:随便写一个类,控制变量法演示即可
	
###10.03_面向对象(带包的类编译和运行)(掌握)
###A:如何编译运行带包的类?
    a:javac编译的时候带上-d空格点空格源文件的名字即可,如:
###javac -d . HelloWorld.java

	b:通过java命令执行,如:
###java 包名.HelloWorld

总结:编译时不带包名加上-d空格点空格,.表示在当前路径生成带包的字节码文件,然后运行时要加上包名.类名即类的全路径

#10.04_面向对象(不同包下类之间的访问)(掌握)
A:案例演示:见截图

	不同包下类之间的访问:
总结:例如想在A包中访问使用B包中的无关类C,调用时必须带上C的全路径,并且C的权限要是公有,
且其被调用的构造方法也要公有

#10.05_面向对象(import关键字的概述和使用)(掌握)
A:案例演示:
###为什么要有import?
    其实就是让有包的类对调用者可见,不用写全类名了

###B:导包格式:
    import 包名;
	注意:
	这种方式导入是到类的名称,
	虽然可以最后写*,但是不建议:*代表通配符,它会到该包下挨个匹配,匹配上就导入,但是效率太低了

###C:package,import,class有没有顺序关系(面试题):
    有,package必须在代码的第一行且只有一个,
    import要在package的下面但是可以有多个,
    而class必须在package和import的下面,所有他们是有顺序关系的

#10.06_面向对象(四种权限修饰符的测试)(掌握)
A:案例演示:
###四种权限修饰符:私有本类,默认同胞,保护不同胞的子类,公有秒杀-巧记!
	
###B:结论:↓
	分类	     本类  同一个包下(子类和无关类) 不同包下(子类)	不同包下(无关类)
	private 	Y		
	默认(同胞)	 Y		 Y
	protected	Y		Y		               Y
	public		Y		Y		               Y		     Y
	
    总结:类分为本类,子类,无关类,包有同包,不同包,相互组合的情况下,
    私有修饰只在本类可以访问,其他不可以,
    默认修饰比私有权限高,除了本类,也能在同包下访问,
    而受保护权限比默认更强大点,还可以在不同包下子类被访问,
    而公有权限就更无敌了,除了以上所述,它还可以在不同包下的无关类被访问,可谓通行无阻!
    
代码演示如下:
```
package com.heima;
import com.baidu.Person;
import com.xxx.Student;
//import java.util.Scanner;//在开发中我们用的都是导入具体的类
import java.util.*;//*代表通配符,他会到该包下挨个匹配,匹配上就导入

class Demo1_Package {
	public static void main(String[] args) {
		Person p = new Person("张三",23);
		System.out.println(p.getName() + "..." + p.getAge());
		//p.print();//在不同包下的无关类,不允许访问,因为此方法被protected修饰

		/*
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println(x);
		*/
		
		Student s = new Student("李四",24);
		System.out.println(s.getName() + "..." + s.getAge());
		s.method();//被public修饰,不同包下可以访问
	}
}
```
再总结:可以记忆为私有是本类修饰符,默认是同包修饰符,受保护是不同包子类修饰符,公有是不同包无关类修饰符

#10.07_面向对象(类及其组成所使用的常见修饰符)(掌握)
###A:修饰符：
    权限修饰符：private,默认的,protected,public
	状态修饰符：static,final
	抽象修饰符：abstract

###B:类：
	权限修饰符：默认修饰符,public
	状态修饰符：final
	抽象修饰符：abstract
	
	用的最多的就是：public
	
###C:成员变量：
	权限修饰符：private,默认的,protected,public
	状态修饰符：static,final
	
	用的最多的就是：private
	
###D:构造方法：
	权限修饰符：private,默认的,protected,public
	
	用的最多的就是：public
	
###E:成员方法：
	权限修饰符：private,默认的,protected,public
	状态修饰符：static，final
	抽象修饰符：abstract
	
	用的最多的就是：public
	
###F:除此以外的组合规则：
	成员变量：public static final
	成员方法：
	    public static 
		public abstract
		public final

###总结:上面说了那么多,但是需要注意的是,目前学到的修饰符中,对于局部变量,只有final能修饰,其他都不能修饰,否则报错!

#10.08_面向对象(内部类概述和访问特点)(了解)
###A:内部类概述:在类中定义的类

###B:内部类访问特点:
    a:内部类可以直接访问外部类的成员,包括私有(可结合这里的成员内部类是外部类的成员,属于本类范围理解)
    
	b:外部类要访问内部类的成员,必须创建对象:
        外部类名.内部类名 对象名 = 外部类对象.内部类对象;
	    即Outer.Inner oi = new Outer().new Inner();
	    
C:案例演示:

	内部类及其访问特点:
总结:看下面代码就明白了:
```
class Demo1_InnerClass {
	public static void main(String[] args) {
		//Inner i = new Inner();//报错找不到
		//i.method();
		
		//外部类名.内部类名 = 外部类对象.内部类对象
Outer.Inner oi = new Outer().new Inner();//创建内部类对象,这里的Outer.Inner指的是外部类Outer的内部类Inner
		oi.method();//外部类要访问内部类的成员,必须创建对象,包括外部类对象,内部类对象!
		
	}
}

class Outer {
	private int num = 10;
	
	class Inner {//成员内部类
		public void method() {
			System.out.println(num);//输出结果为10,说明内部类可以直接访问外部类的成员,包括私有
		}
	}
}
```
	
#10.09_面向对象(成员内部类私有使用)(了解)
总结:看下面代码就明白了:
```
class Demo2_InnerClass {
	public static void main(String[] args) {
		//Outer.Inner oi = new Outer().new Inner();//成员内部类被私有,所以不能创建其对象
		//oi.method();
		
		Outer o = new Outer();
		o.print();//外部类对象调用本类成员方法,成员方法里面封装了内部类对象调用内部类方法解决
		
	}
}

class Outer {
	private int num = 10;
	
	private class Inner {//成员内部类被私有,所以不能创建其对象,所以要深入内部,
		public void method() {
			System.out.println(num);//输出10
		}
	}
	
	public void print() {//外部类成员方法可以访问外部类成员,自然包括外部类的成员内部类(也是成员)
		Inner i = new Inner();//深入内部,可创建内部类对象,
		i.method();//创建内部类对象调用内部类方法
	}
	
}
```

#10.10_面向对象(静态成员内部类)(了解)
B:成员内部类被静态修饰后的访问方式是:

	外部类名.内部类名 对象名 = 外部类名.内部类对象;
	
总结:看下面的代码就明白了:
```
class Demo1_InnerClass {
	public static void main(String[] args) {
		//外部类名.内部类名 对象名 = 外部类名.内部类对象;//右边本是Outer.new Inner();但是,↓:看这就懂:
		
Outer.Inner oi = new Outer.Inner();//要把new提前,是因为外部类Outer不能用static修饰,不能用类名点调用,否则报错!
		oi.method();//静态内部类调用普通方法的调用方式

		Outer.Inner2.print();//静态内部类静态方法的特有调用方式!!!
	}
}

class Outer {
	static class Inner {//静态内部类普通方法
		public void method() {
			System.out.println("method");
		}
	}

	static class Inner2 {//静态内部类静态方法!!!
		public static void print() {
			System.out.println("print");
		}
	}
}
```

    总结:非私有成员内部类右边用的是,创建外部类对象调用创建的内部类对象即:new Outer().new Inner();
    而静态成员内部类右边用的是,直接创建外部类的内部类对象即:new Outer.Inner();而他们的左边都是外部类的内部类引用
    等于谁即:Outer.Inner oi =  

#10.11_面向对象(成员内部类的面试题)(掌握)
A:面试题

    要求:使用已知的变量,在控制台输出30,20,10
    class Outer {
    	public int num = 10;
    	
    	class Inner {
    		public int num = 20;
    		public void show() {
    			int num = 30;
    			System.out.println(?);//num
    			System.out.println(??);//this.num
    			System.out.println(???);Outer.this.num
    		}
    	}
    }
    
    class InnerClassTest {
    	public static void main(String[] args) {
    		Outer.Inner oi = new Outer().new Inner();
    		oi.show();
    	}	
    }
    
总结:内部类之所以能获取到外部类的成员,是因为他能获取到外部类的引用,即外部类名.this,即上面代码的Outer.this

#10.12_面向对象(局部内部类访问局部变量的问题)(掌握)
A:案例演示:
###局部内部类访问局部变量必须用final修饰:
    局部内部类在访问他所在方法中的局部变量,该局部变量必须用final修饰,为什么?
	因为当调用这个方法时,局部变量如果没有用final修饰,他的生命周期和方法的生命周期是一样的,
	当方法弹栈,这个局部变量也会消失,那么如果局部内部类对象还没有马上消失想用这个局部变量,就没有了,
	如果用final修饰会在类加载的时候进入常量池,即使方法弹栈,常量池的常量还在,也可以继续使用,
	但是jdk1.8取消了这个事情,其实不写加final默认也是加final的,这个要注意,也是默认情况

总结:看下面代码就明白了:
```
class Demo1_InnerClass {
	public static void main(String[] args) {
		Outer o = new Outer();
		o.method();
	}
}

class Outer {
	//int bb = 666;
	public void method() {
		final int num = 10;//不加final,num随着method方法弹栈消失而消失
		
		class Inner {//方法里的内部类即局部内部类,只能被abstract或者final修饰
			public void print() {
				System.out.println(num);//从局部内部类中访问外部类方法的局部变量,
				//该局部变量必须声明为最终类型,即用final修饰!!!
				
			}
		}

		Inner i = new Inner();//局部内部类,只能在其所在的方法中访问,所以这样可以创建对象,
		i.print();//不加final,num随着method方法弹栈消失而消失,为保证此时如果还没消失的对象i能继续使用num,加final使num在类加载时进入常量池变成常量,可以被继续使用
		
	}
	
	/*
	public void run() {
	Inner i = new Inner();//报错,局部内部类,只能在其所在的方法中访问,其实可以把局部内部类当成局部变量来看待!
		i.print();
	}
	*/
	
}
```

    总结:注意,不管局部内部类所在的方法是静态还是非静态,局部内部类只能被abstract或者final修饰,
    另外,如果局部内部类所在的方法是静态的,
    不管在局部内部类里面还是在静态方法里面,访问的成员变量必须是静态的,因为静态只能访问静态,
    而访问方法里面的局部变量,局部内部类里面必须加final,方法里可以看的见,不用加静态!

#10.13_面向对象(匿名内部类的格式和理解)
###A:什么是匿名内部类:
    就是内部类的简化写法,是局部内部类的一种
    
###B:前提:存在一个类或者接口,
    这里的类可以是具体类也可以是抽象类
    
###C:格式:
		new 类名或者接口名(){
			重写方法;
		}
		
###D:本质是什么呢?
    匿名内部类本质:是一个继承了该类或者实现了该接口的子类匿名对象
    
E:案例演示:

    按照要求来一个匿名内部类:
    
总结:看下面的代码就明白了,
最主要明白匿名内部类的本质:是一个继承了该类或者实现了该接口的子类匿名对象:
```
class Demo1_NoNameInnerClass {
	public static void main(String[] args) {
		Outer o = new Outer();
		o.method();
	}
}

interface Inter {
	public void print();
}

class Outer {

	//class Inner implements Inter {//接口的实现类即相当于类的子类
	//	public void print() {
			System.out.println("print");
		}
	//}

	public void method(){//外部类成员方法可以访问成员内部类:↓
		//Inner i = new Inner();//创建有名对象调用方法写法:
		//i.print();
		//new Inner().print();//匿名对象写法
		
	//这里就体现了,匿名内部类,就是内部类的简化写法,是局部内部类的一种:↓
		new Inter() {//匿名内部类写法,实现Inter接口,匿名内部类调用方法
			public void print() {//重写抽象方法
				System.out.println("print");
			}
		}.print();//创建实现了接口方法的实现类对象,来调用对象方法,即子类匿名对象调用方法
		
	}
	
}
```
		
#10.14_面向对象(匿名内部类重写多个方法调用)
A:案例演示:
###匿名内部类只针对重写一个方法时候使用:
    匿名内部类的方法调用:
    总结:如果有多个方法可以创建多个匿名对象调用但这样浪费内存,如果改为用一个父类引用接收,再用引用调用方法,
    这样也有弊端,引用不能调用子类特有方法,因为编译看的是父类,所以匿名内部类只针对重写一个方法时候使用!
    
看下面的代码就明白了:
```
class Demo2_NoNameInnerClass {
	public static void main(String[] args) {
		Outer o = new Outer();
		o.method();
	}
}

interface Inter {
	public void show1();
	public void show2();
}

//匿名内部类只针对重写一个方法时候使用
class Outer {

	public void method() {
		/*new Inter(){
			public void show1() {
				System.out.println("show1");
			}

			public void show2() {
				System.out.println("show2");
			}
		}.show1();

		new Inter(){
			public void show1() {
				System.out.println("show1");
			}

			public void show2() {
				System.out.println("show2");
			}
		}.show2();*/

		Inter i = new Inter(){//父类引用指向子类对象:接口引用指向实现类对象一样
			public void show1() {
				System.out.println("show1");
			}

			public void show2() {
				System.out.println("show2");
			}

			/*
			public void show3() {
System.out.println("show3");//这样写也有弊端就是特有方法show3不能调用,因为接口中没有,编译看的是接口(父类),没有就失败了
			}
			*/
		};

		i.show1();
		i.show2();
		//i.show3();//编译报错,父类中没有这个方法,另外匿名内部类是不能向下转型的,因为没有子类类名
		
	}
	
}
```

###10.15_面向对象(匿名内部类在开发中的应用)
A:代码如下:

    //这里写抽象类,接口都行
    abstract class Person {
    	public abstract void show();
    }
    
    class PersonDemo {
    	public void method(Person p) {
    		p.show();
    	}
    }
    
    class PersonTest {
    	public static void main(String[] args) {
    		//如何调用PersonDemo中的method方法呢?
    		PersonDemo pd = new PersonDemo ();
    		
    	}
    }
    
总结:可用匿名子类对象或者匿名内部类对象实现:
代码演示如下:
```
class Test1_NoNameInnerClass {
	public static void main(String[] args) {
		//如何调用PersonDemo中的method方法呢?
		PersonDemo pd = new PersonDemo ();
		//pd.method(new Student());//匿名子类对象

		pd.method(new Person() {//匿名内部类
			public void show() {
				System.out.println("show");
			}
		});
	}
}

//这里写抽象类,接口都行
abstract class Person {
	public abstract void show();
}

class PersonDemo {
	
	public void method(Person p) {
		p.show();
	}
}

class Student extends Person {
	public void show() {
		System.out.println("show");
	}
}
```

#10.16_面向对象(匿名内部类的面试题)
A:面试题

    按照要求,补齐代码
    interface Inter { void show(); }
    class Outer { //补齐代码 }
    class OuterDemo {
    	public static void main(String[] args) {
    		  Outer.method().show();
    	  }
    }
    要求在控制台输出”show”
    
    总结:调用接口show方法必然是对象,因为接口方法是抽象的不能与静态共存,所以Outer.method必须是一个对象,
    看不到名字,所以是一个匿名内部类对象:
    
代码演示如下:
```
class Test2_NoNameInnerClass {
	public static void main(String[] args) {
		//Outer.method().show();//链式编程,每次调用方法后还能继续调用方法,证明调用方法返回的是对象,其实就是下面的调用过程:
		
		Inter i = Outer.method();
		i.show();
	}
}

//按照要求,补齐代码
interface Inter { 
	void show(); 
}

class Outer {
	//补齐代码 
	public static Inter method() {
	
		return new Inter() {
			public void show() {
				System.out.println("show");
			}
		};
		
	}
}
```
总结:看到接口,要想到接口是没用的,要调用接口中的抽象方法去实现功能,需要的是接口的实现类,而匿名内部类的本质就是一个接口的实现类!!!

#10.17_day10总结
    把今天的知识点总结一遍:
    包-带包编译运行-导入关键字-权限修饰符-成员内部类-私有成员内部类-静态成员内部类-局部内部类-匿名内部类
    
 