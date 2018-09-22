#19.01_异常(异常的概述和分类)
A:异常的概述:

    异常就是Java程序在运行过程中出现的错误,这句话有待商榷,难道编译时异常不也是异常吗?可以理解编译也是运行
    
B:异常的分类:

	通过API查看Throwable,见截图
	Error
		服务器宕机,数据库崩溃等
	Exception
	
C:异常的继承体系:

	Throwable
		Error	
		Exception
		    RuntimeException
总结:Exception下除了RuntimeException运行时异常,其他都是编译时异常

#19.02_异常(JVM默认是如何处理异常的)
A:JVM默认是如何处理异常的:

    main函数收到这个问题时,有两种处理方式:
	a:自己将该问题处理,然后继续运行;
	b:自己没有针对的处理方式,只有交给调用main的jvm来处理,
	  jvm有一个默认的异常处理机制,就将该异常进行处理,
	  并将该异常的名称,异常的信息,异常出现的位置打印在了控制台上,同时将程序停止运行
	  
B:案例演示:

    JVM默认如何处理异常:
代码演示如下:
```
class Test {
    public static void main(String[] args) {
        //demo1();
		Demo d = new Demo();
		int x = d.div(10, 0);
    	System.out.println(x);//java.lang.ArithmeticException: / by zero,//运行时异常算术异常,除数不能为0
    	System.out.println("66666666");//没有输出,因为虚拟机退出了
    }
    
	public static void demo1() {
		int[] arr = {11,22,33,44,55};
		//arr = null;//NullPointerException空指针异常
		System.out.println(arr[10]);//ArrayIndexOutOfBoundsException数组索引越界异常
	}
}

class Demo {
	public int div(int a,int b) {//a = 10,b = 0
		return a / b;//当除数是0时,违背了算数运算法则,抛出异常new ArithmeticException("/ by zero");
	}
}
```

#19.03_异常(try...catch的方式处理异常1)
A:异常处理的两种方式:

    a:try…catch…finally
		try catch
		try catch finally
		try finally 
		//alt + shif + z (try catch eclipse快捷键)
		
	b:throws
	
B:try...catch处理异常的基本格式:

    try…catch…finally,它们的作用:try用来检测异常的,catch用来捕获异常的,finally用来释放资源的
    
C:案例演示:

    try...catch的方式处理1个异常:
代码演示如下:
```
class Test2 {
    public static void main(String[] args) {
        Demo2 d = new Demo2();
    	try{
    		int x = d.div(10, 0);//可能存在异常的代码越少越好,
    		System.out.println(x);//没有输出,上面出现异常就走到catch代码块中进行捕获处理
    	}catch(ArithmeticException a) {//ArithmeticException a = new ArithmeticException();
    		System.out.println("出错了,除数为零了");//捕获异常,输出提示
    	}
    	//当通过try...catch将问题处理了,程序会继续执行:
    	System.out.println("1111111111111111");//输出1111111111111111,因为try catch处理了异常
    }

}

class Demo2 {
	public int div(int a,int b) {
		return a / b;//当除数是0的时候违背了算数运算法则,抛出异常new ArithmeticException("/ by zero");
	}
}
```

#19.04_异常(try...catch的方式处理异常2)
A:案例演示:

	try...catch的方式处理多个异常
	
	JDK7以后处理多个异常的方式及注意事项:
    总结:JDK1.7之前,try后面如果跟多个catch,那么小的异常放前面,大的异常放后面,根据多态的原理,如果大的放前面,
    就会将所有的子类对象接收,后面的catch就没有意义了,JDK1.7后用|或把多个异常放在同一个catch块里,但是要注意,
    |或表示平级的关系,如果其中一个是Exception,那就被接收了,
    另一个报错:The exception XxxException is already caught by the alternative Exception
    如不能是catch (ArithmeticException | Exception e),也不能是catch (Exception | ArrayIndexOutOfBoundsException e)
代码演示如下:
```
public static void main(String[] args) {
   // demo1();
    int a = 10;
    int b = 0;
    int[] arr = {11,22,33,44,55};
    
    //JDK7如何处理多个异常:|或者表示平级的关系,如果其中一个是Exception,那就被接收了,另一个报错,如下所示:
    try {
    	System.out.println(a / b);
    	System.out.println(arr[10]);
    } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {//不能是ArithmeticException | Exception e
    System.out.println("JDK7如何处理多个异常出错了");//也不能是Exception | ArrayIndexOutOfBoundsException e
    }
    System.out.println("JDK7异常处理了,下面代码正常输出");//异常处理了,下面代码正常输出
}

public static void demo1() {
	int a = 10;
	int b = 0;
	int[] arr = {11,22,33,44,55};
	
	try {
		System.out.println(a / b);//出现异常,下面代码不走,走到catch块中捕获处理
		System.out.println(arr[10]);//不走
		arr = null;//不走
		System.out.println(arr[0]);//不走
	} catch (ArithmeticException e) {
		System.out.println("除数不能为零");
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("索引越界了");
	} catch (Exception e) {//Exception e = new NullPointerException();父类引用指向子类对象,
	//try后面如果跟多个catch,那么小的异常放前面,大的异常放后面,根据多态的原理,如果大的放前面,
	//就会将所有的子类对象接收,后面的catch就没有意义了
		System.out.println("出错了");
	}
	
	System.out.println("over,异常处理了,下面代码正常输出");//over,异常处理了,下面代码正常输出
}
```

#19.05_异常(编译期异常和运行期异常的区别)
A:编译期异常和运行期异常的区别:

    Java中的异常被分为两大类:编译时异常和运行时异常:
	所有的RuntimeException类及其子类的实例被称为运行时异常,其他的异常就是编译时异常
	编译时异常:
	    Java程序必须显示处理,否则程序就会发生错误,无法通过编译
	运行时异常:
		无需显示处理,也可以和编译时异常一样处理
		
B:案例演示:

	编译期异常和运行期异常的区别:
代码演示如下:
```
public static void main(String[] args) {
	//编译时异常,Java程序必须显示处理,否则程序就会发生错误,无法通过编译
    //FileInputStream fis = new FileInputStream("xxx.txt");//
	
	try {
		FileInputStream fis2 = new FileInputStream("xxx.txt");
	} catch(Exception e) {
		
	}

	//运行时异常,无需显示处理,也可以和编译时异常一样处理
	System.out.println(1/0);//无需显示处理
	try {
		System.out.println(1/0);//和编译时异常一样处理
	} catch (Exception e) {//Exception e = new ArithmeticException("/ by zero");
		
	}
}
```

#19.06_异常(Throwable的几个常见方法)
A:Throwable的几个常见方法:

    a:getMessage()
	    获取异常信息,返回字符串
	b:toString()
		获取异常类名和异常信息,返回字符串
	c:printStackTrace()
		获取异常类名和异常信息,以及异常出现在程序中的位置,注意其返回值是void
		
B:案例演示:

    Throwable的几个常见方法的基本使用:
代码演示如下:
```
public static void main(String[] args) {
    try {
    	System.out.println(1/0);
    } catch (Exception e) {//Exception e = new ArithmeticException("/ by zero");
    //System.out.println(e.getMessage());/// by zero,获取异常信息
    //System.out.println(e);//java.lang.ArithmeticException: / by zero,调用toString方法,打印异常类名和异常信息
    e.printStackTrace();//jvm默认用这种方式处理异常,获取异常类名和异常信息,以及异常出现在程序中的位置,返回值void,
    //java.lang.ArithmeticException: / by zero
    //at com.heima.exception.Demo5_Throwable.main(Demo5_Throwable.java:18)
    }
}
```

#19.07_异常(throws的方式处理异常)
A:throws的方式处理异常:

    定义功能方法时,需要把出现的问题暴露出来,让调用者去处理,
	那么就通过throws在方法上标识
	
B:案例演示:

	举例分别演示编译时异常和运行时异常的抛出:
总结:抛出编译时异常,调用者必须处理或者声明异常,但声明异常,里面可以不抛出异常对象,↓
抛出运行时异常,上面可以不声明异常即可以去掉throws RuntimeException,
抛出运行时异常,调用者可以不做处理,也可以像编译时异常一样处理或者声明异常Excepton或者RuntimeException
代码演示如下:
```
class Test3 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.setAge(-17);//自定义的编译时异常,即继承Exception类的自定义类
        //p.setAge0(-17);//抛出编译时异常,调用者必须处理或者声明异常,但声明异常,里面可以不抛出异常↓
        //p.setAge1(-17);//抛出运行时异常,调用者可以不做处理,也可以像编译时异常一样处理或者声明异常
        System.out.println(p.getAge());//编译时异常的抛出必须对其进行处理,运行时异常的抛出可以处理,也可以不处理
    }
}

class Person {
	//由于空间问题,这里省略年龄和姓名属性以及无参有参构造和部分setter和getter方法
	public void setAge0(int age) throws Exception {//编译时异常的抛出必须对其进行处理,
		if(age >0 && age <= 150) {
			this.age = age;
		}else {
			System.out.println("下面没有抛出编译时异常,还会正常输出年龄的默认值0");
			//Exception e = new Exception("年龄非法");
			//throw e;
	        throw new Exception("年龄非法");//抛出编译时异常,这里抛出,上面必须声明,上面声明,这里可以不抛出↑
		}
	}
	
//	public void setAge1(int age) throws RuntimeException {
	public void setAge1(int age) {//抛出运行时异常,上面可以不声明异常即可以去掉throws RuntimeException,
		if(age >0 && age <= 150) {//即运行时异常的抛出可以处理,也可以不处理
			this.age = age;
		}else {
			System.out.println("下面没有抛出运行时异常,还会正常输出年龄的默认值0");
			//Exception e = new Exception("年龄非法");
			//throw e;
			throw new RuntimeException("年龄非法");
		}
	}
	
	public void setAge(int age) throws AgeOutOfBoundsException {//抛出自定义的编译时异常
		if(age >0 && age <= 150) {
	        this.age = age;//总结:抛出编译时异常,调用者必须处理或者声明异常,但声明异常,里面可以不抛出异常
		}else {
			//Exception e = new Exception("年龄非法");
			//throw e;
			throw new AgeOutOfBoundsException("年龄非法");
		}
	}
	
//自定义编译时异常的写法:
class AgeOutOfBoundsException extends Exception {

	public AgeOutOfBoundsException() {
		super();//无参构造写法,模仿Exception类的即可
	}
	//通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的方法
	public AgeOutOfBoundsException(String message) {
		super(message);//有参构造写法,模仿Exception类的即可
	}
}
```

#19.08_异常(throw的概述以及和throws的区别)
A:throw的概述:

    在功能方法内部出现某种情况,程序不能继续运行,需要进行跳转时,就用throw把异常对象抛出
    
B:案例演示:

	分别演示编译时异常对象和运行时异常对象的抛出:
	
C:throws和throw的区别:

	a:throws
	    用在方法声明后面,跟的是异常类名
		可以跟多个异常类名,用逗号隔开
		表示抛出异常,由该方法的调用者来处理
	b:throw
		用在方法体内,跟的是异常对象名
		只能抛出一个异常对象名
		表示抛出异常,由方法体内的语句处理
理解记忆:throws后面有个s(死的谐音),死后就跟的是灵魂即异常类名,并且s是复数,可以跟多个异常类名
throw没有死,后面跟的是活的个体异常对象,没有复数,只能抛出一个异常对象名
代码演示如下:
```
public void setAge1(int age) throws Exception,RuntimeException {//throws可以跟多个异常类名,用逗号隔开,
    //public void setAge1(int age) {//这时调用setAge1方法的main方法可以throws Exception,
		if(age >0 && age <= 150) {//或者throws Exception,RuntimeException两个
		this.age = age;//不能单单throws RuntimeException,因为RuntimeException不能接收父类Exception
		}else {
			throw new RuntimeException("年龄非法");//有参构造模仿Exception类的即可
			//通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的
		}
	}
```

#19.09_异常(finally关键字的特点及作用)
A:finally的特点:

    被finally控制的语句体一定会执行,除非,虚拟机退出了,如:
	特殊情况:在执行到finally之前jvm退出了(比如调用了System.exit(0))
	
B:finally的作用:

    用于释放资源,在IO流操作和数据库操作中会见到
    
C:案例演示:

	finally关键字的特点及作用:
总结:另外,return语句相当于是方法的最后一口气,那么在他将死之前会看一看有没有finally帮其完成遗愿,
如果有就将finally执行后,再彻底返回
代码演示如下:
```
public static void main(String[] args) {
    try {
    	System.out.println(10/0);
    } catch (Exception e) {
        System.out.println("除数为零了");
        //System.exit(0);//退出jvm虚拟机,finally不执行
    	return;//return语句返回之前看看有没有finally,有就将finally执行后,再彻底返回
    } finally {
    	System.out.println("看看finally我执行了吗");
    }
}
```

#19.10_异常(finally关键字的面试题)
A:面试题1:

    final,finally和finalize的区别:
    
B:面试题2:

    如果catch里面有return语句,请问finally的代码还会执行吗?如果会,请问是在return前还是return后?
看看下面代码就明白了:
```
class Test4 {
    public static void main(String[] args) {
		Demo d = new Demo();
		System.out.println(d.method());//输出catch块里的结果是30
    }
}//1.final可以修饰类不能被继承,修饰方法不能被重写,修饰变量是常量只能被赋值一次
//3.finalize是一个方法,当垃圾回收器确定不存在对该对象的更多引用时,由对象的垃圾回收器调用此方法
class Demo {
	public int method() {
		int x = 10;
		try {
			x = 20;
			System.out.println(1/0);//报异常,所以下面代码不走,走到catch块捕获处理,注掉,返回的是20
			return x;
		} catch (Exception e) {
			x = 30;
			return x;
		} finally {//2.finally是try语句中的一个语句体,不能单独使用,用来释放资源
			x = 40;
			System.out.println("finally执行了");
//	return x;//输出40,千万不要在finally里面写返回语句,因为finally的作用是为了释放资源,是肯定会执行的
			//如果在这里面写返回语句,那么try和catch的结果都会被改变,所以这么写就是不合理的,哈哈
		}
	}//结论:如果catch里面有return语句,finally的代码还会执行,是在return前执行
}
```
	
#19.11_异常(自定义异常概述和基本使用)
A:为什么需要自定义异常,见名知义,通过名字区分到底是神马异常,从而有针对的解决办法:

	举例:人的年龄
	
B:自定义异常概述:

    继承自Exception
	继承自RuntimeException
	
C:案例演示:

    自定义异常的基本使用:
    总结:有参构造方法不会写,通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的自动生成即可:
代码演示如下:
```
class AgeOutOfBoundsException extends Exception {
	public AgeOutOfBoundsException() {//无参构造
		super();//通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的
		//通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的自动生成
	}

	public AgeOutOfBoundsException(String message) {
		super(message);//有参构造,不会写,打开Exception的源码,模范它的构造方法即可
		//通过eclipse快捷键Alt+shift+s再按c即可调用父类Exception的自动生成
	}
}
```

#19.12_异常(异常的注意事项及如何使用异常处理)
A:异常注意事项:

    a:子类重写父类方法时,子类的方法必须抛出相同的异常或父类异常的子类(父亲坏了,儿子不能比父亲更坏)
    
	b:如果父类抛出了多个异常,子类重写父类时,只能抛出相同的异常或者是他的子集,子类不能抛出父类没有的异常
	
	c:如果被重写的方法没有异常抛出,那么子类的方法绝对不可以抛出异常,如果子类方法内有异常发生,
	那么子类只能try,不能throws
	
	总结就一句话的理解:子类不能抛出父类没有的异常

B:如何使用异常处理:

	原则:如果该功能内部可以将问题处理,用try,如果处理不了,交由调用者处理,这时用throws
	区别:
	    后续程序需要继续运行就try
		后续程序不需要继续运行就throws
		
	如果JDK没有提供对应的异常,需要自定义异常

#19.13_异常(练习)
键盘录入一个int类型的整数,对其求二进制表现形式:

	 如果录入的整数过大(即能用BigInteger接收),给予提示,录入的整数过大请重新录入一个整数
	 如果录入的是小数,给予提示,录入的是小数,请重新录入一个整数
	 如果录入的是其他字符,给予提示,录入的是非法字符,请重新录入一个整数
	 
总结:考的是try...catch的嵌套使用,同时注意接收键盘录入的变量的范围应该在死循环内,才不影响判断逻辑跳出死循环,
另外,键盘录入的数据,能够传入某个构造,说明是某类型范围的数,给予提示
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
	System.out.println("请输入一个整数:");

    //String line = sc.nextLine();//将键盘录入的结果存储在line中,这样会不断循环输出,因为不用等键盘录入就有数据了,
	while(true) {//而且进来的一直都是同一个数据,
        String line = sc.nextLine();//而里面会定义变量重新接收你键盘录入的数据,能走到正确的判断逻辑跳出死循环
		try {//其实考的是try...catch的嵌套使用
			int num = Integer.parseInt(line);//将字符串转换为整数
			System.out.println(Integer.toBinaryString(num));//将整数转换为二进制
			break;//不出异常,就跳出死循环
		}catch(Exception e) {
			try {
				new BigInteger(line);//能用传入这个构造不报错,说明line是一个过大整数
			    System.out.println("录入错误,您录入的是一个过大整数,请重新输入一个整数:");
			}catch (Exception e2) {//alt + shif + z (try catch快捷键)
				try {
    				new BigDecimal(line);//能用传入这个构造不报错,说明line是一个小数
    			    System.out.println("录入错误,您录入的是一个小数,请重新输入一个整数:");
				} catch (Exception e1) {//最后可以是其他的非法字符
				    System.out.println("录入错误,您录入的是非法字符,请重新输入一个整数:");
				}
			}
		}
	}
}
```

#19.14_File类(File类的概述和构造方法)
A:File类的概述:

	File更应该叫做一个路径,
	    文件路径或者文件夹路径,
		路径分为绝对路径和相对路径,
		绝对路径是一个固定的路径,从盘符开始,
		相对路径相对于某个位置,在eclipse下是指当前项目下,在dos下指的是当前路径,
		
	是文件和目录路径名的抽象表示形式
	
B:构造方法:

    File(String pathname)//根据一个路径得到File对象
	File(String parent, String child)//根据一个目录和一个子文件/目录得到File对象
	File(File parent, String child)//根据一个父File对象和一个子文件/目录得到File对象

C:案例演示:

    File类的构造方法:
    总结:其实无非就是一个路径或者拆分一个路径为两个,创建一个代表文件或者目录的File类对象,最后直接用File对象
    和路径来创建File对象,这样就综合了文件和目录的组合,方便使用
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    
    File parent = new File("F:\\双元课堂\\day19\\video");
    String child = "001_今日内容.avi";
    File file = new File(parent, child);//构造传入父文件对象和子路径名字符串
    System.out.println(file.exists());//false,不存在
    System.out.println(parent.exists());//false,不存在
}

public static void demo2() {
	String parent = "F:\\双元课堂\\day19\\video";
	String child = "001_今日内容.avi";
	File file = new File(parent,child);//构造传入父路径名字符串和子路径名字符串
	System.out.println(file.exists());//false,没有该组合路径
}

public static void demo1() {//反斜线要转义多加一个\,或者用/也可以
	File file = new File("F:\\双元课堂\\day19\\video\\001_今日内容.avi");//构造传入路径名字符串
	System.out.println(file.exists());//false,文件或者目录都没有存在
	
	File file2 = new File("xxx.txt");//eclipse中默认是项目路径下
	System.out.println(file2.exists());//true,项目路径下有该文件
	
	File file3 = new File("yyy.txt");
	System.out.println(file3.exists());//false,项目路径下没有该文件
}
```

#19.15_File类(File类的创建功能)
A:创建功能:

    public boolean createNewFile()//创建文件,如果存在这样的文件,就不创建了
	public boolean mkdir()//创建文件夹,如果存在这样的文件夹,就不创建了
	public boolean mkdirs()//创建多级文件夹,如果父文件夹不存在,会帮你创建出来

B:案例演示:

	File类的创建功能:
	注意事项:
	    如果你创建文件或者文件夹忘了写盘符路径,那么,默认在当前项目路径下
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
    
    File dir1 = new File("aaa");//根据一个路径得到File对象,
    System.out.println(dir1.mkdir());//true,创建文件夹,如果存在这样的文件夹,就不创建了
    
    File dir2 = new File("bbb.txt");//文件名这样写是可以的,文件夹也是可以有后缀的命名的
    System.out.println(dir2.mkdir());//false,创建文件夹,如果存在这样的文件夹,就不创建了,返回false
    
    File dir3 = new File("ccc\\ddd");//创建文件夹,如果父文件夹不存在,会帮你创建出来
    System.out.println(dir3.mkdirs());//false,创建多级目录,已经存在就不创建,返回false
}

public static void demo1() throws IOException {//在工程项目F5刷新才能看到生成的文件
    File file = new File("yyy.txt");//创建文件,如果存在这样的文件,就不创建了
    System.out.println(file.createNewFile());//true,如果文件不存在就创建,返回true
    
    File file2 = new File("zzz");//不加后缀名也可以创建文件
    System.out.println(file2.createNewFile());//false,如果文件存在就不创建,返回false
}
```
		
#19.16_File类(File类的重命名和删除功能)
A:重命名和删除功能:

    public boolean renameTo(File dest)//把文件重命名并剪切到指定路径,如果路径相同就是改名
	public boolean delete()//删除文件或者文件夹,要删除一个文件夹,请注意该文件夹内不能包含文件或者文件夹

B:重命名注意事项:

	如果路径名相同,就是改名
	如果路径名不同,就是改名并剪切

C:删除注意事项:

	Java中的删除不走回收站
	要删除一个文件夹,请注意该文件夹内不能包含文件或者文件夹
	
总结:为什么叫renameTo而不叫rename?因为除了重命名还有to即剪切到指定路径的功能
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    File file1 = new File("xxx.txt");//Java中的删除不走回收站,删了就没了
    System.out.println(file1.delete());//删除文件或者文件夹,这里是删除文件
    		
    File file2 = new File("aaa");
    System.out.println(file2.delete());//删除文件或者文件夹,这里是删除文件夹
    	
    File file3 = new File("ccc");//如果删除一个文件夹,那么文件夹必须是空的
    System.out.println(file3.delete());//false,ccc文件夹下还有ddd文件夹,不能删除
}

public static void demo1() {
    //如果路径名相同,就是改名,假如路径都是当前项目路径下↓
    //File file1 = new File("ooo.txt");//把ooo.txt改名为xxx.txt
    //File file2 = new File("xxx.txt");
    //System.out.println(file1.renameTo(file2));//true,改名成功
    
    //如果路径名不同,就是改名并剪切到指定路径↓
    File file1 = new File("yyy.txt");//把yyy.txt改名为xxx.txt并剪切到D盘下
    File file2 = new File("D:\\xxx.txt");
    System.out.println(file1.renameTo(file2));//true,改名并剪切成功
}
```

#19.17_File类(File类的判断功能)
A:判断功能:

    public boolean isDirectory()//判断是否是目录或者说文件夹
	public boolean isFile()//判断是否是文件
	public boolean exists()//判断路径是否存在
	public boolean canRead()//判断是否可读
	public boolean canWrite()//判断是否可写
	public boolean isHidden()//判断是否隐藏
	
B:案例演示:

	File类的判断功能:
总结:不管是否设置,windows系统认为所有的文件都是可读的,但windows系统可以设置为不可写
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    File file = new File("zzz");
    file.setReadable(false);//在window不起作用,在Linux下可以
    System.out.println(file.canRead());//true,windows系统认为所有的文件都是可读的
    
    file.setWritable(false);
    System.out.println(file.canWrite());//false,windows系统可以设置文件为不可写
    
    File file2 = new File("aaa.txt");
    System.out.println(file2.isHidden());//false,判断是否是隐藏文件
    System.out.println(file.isHidden());//false,
}

public static void demo1() {
	File dir1 = new File("ccc");
	System.out.println(dir1.isDirectory());//true,判断是否是文件夹
	
	File dir2 = new File("zzz");
	System.out.println(dir2.isDirectory());//false,判断是否是文件夹
	
	System.out.println(dir1.isFile());//false,判断是否是文件
	System.out.println(dir2.isFile());//true
}
```
	
#19.18_File类(File类的获取功能)
A:获取功能:

    public String getAbsolutePath()//获取绝对路径
	public String getPath()//获取File类构造方法中传入的路径,有可能是绝对路径,有可能是相对路径,看你传什么
	public String getName()//获取名称,获取文件或者文件夹的名字
	public long length()//获取长度,字节数
	public long lastModified()//获取最后一次的修改时间,毫秒值
	public String[] list()//获取指定目录下的所有文件或者文件夹的名称数组
	public File[] listFiles()//获取指定目录下的所有文件或者文件夹的File数组
	
B:案例演示:

    File类的获取功能:
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    //File dir = new File("F:/双元课堂/day19/video");
File dir = new File("D:\\hmJavase2015\\day19异常&IO(File类)\\day19(异常&IO(File类))\\day19_video");
	String[] arr = dir.list();//取指定目录下的所有文件或者文件夹的名称数组
	System.out.println(arr);//null,说明对应路径下没有该文件时,list方法返回的是null

	for (String string : arr) {//遍历名称数组元素,输出元素名,没有该文件就会报空指针异常,为什么?
		System.out.println(string);//反编译查看这个增强for循环变量数组底层代码如下所示:
	}
	/*String args1[];//反编译查看这个增强for循环变量数组底层代码
	int j = (args1 = arr).length;//null.length,所以空指针异常 
	for (int i = 0; i < j; i++)
	{
		String string = args1[i];
		System.out.println(string);
	}*/
	System.out.println("-------------------分割线-------------");
	
	File[] subFiles = dir.listFiles();//获取指定目录下的所有文件或者文件夹的File数组 
	
	for (File file : subFiles) {//遍历File数组元素,获取文件对象
		System.out.println(file);//重写了toString方法,底层调用的是return getPath();
//D:\hmJavase2015\day19异常&IO(File类)\day19(异常&IO(File类))\day19_video\19.01_异常(异常的概述和分类).avi
	}
	/*File afile[];//反编译查看这个增强for循环变量数组底层代码
	int l = (afile = subFiles).length;
	for (int k = 0; k < l; k++)
	{
		File file = afile[k];
		System.out.println(file);
	}*/
 }

public static void demo1() {
    File file1 = new File("ccc.txt");
    File file2 = new File("D:\\双元课堂\\day19\\ccc.txt");
    //System.out.println(file1.getAbsolutePath());//E:\jase058\eclipesews\day19\ccc.txt,获取绝对路径
    //System.out.println(file2.getAbsolutePath());//D:\双元课堂\day19\ccc.txt
    
    //System.out.println(file1.getPath());//ccc.txt,获取构造方法中传入路径,构造传入什么就是什么↓
    //System.out.println(file2.getPath());//D:\双元课堂\day19\ccc.txt
    
    //System.out.println(file1.getName());//ccc.txt,获取文件或者文件夹的名字
    //System.out.println(file2.getName());//ccc.txt,
    
    //System.out.println(file1.length());//0,获取长度,字节数
    
    Date d = new Date(file1.lastModified());//文件的最后修改时间
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    System.out.println(sdf.format(d));//1970年01月01日 08:00:00
}
```

#19.19_File类(输出指定目录下指定后缀的文件名)
A:案例演示:

    需求:判断E盘目录下是否有后缀名为.jpg的文件,如果有,就输出该文件名称:
代码演示如下:
```
public static void main(String[] args) {
	File dir = new File("E:\\");
	
	String[] arr = dir.list();//方式一,获取e盘下所有的文件或文件夹的名称数组
	for (String string : arr) {
        if(new File(dir,sting).isFile() && string.endsWith(".jpg")) {//判断是文件要用目录加文件名的路径封装的File对象,因为文件夹也可以命名为如a.jpg
            System.out.println(string);//否则只用文件名如这里的string,那么路径就是当前项目路径下加文件名构成的路径
        }
	}
	
	File[] subFiles = dir.listFiles();//方式二,获取e盘下所有的文件或文件夹对象
	
	for (File subFile : subFiles) {//要判断是文件并且文件名后缀是.jpg
		if(subFile.isFile() && subFile.getName().endsWith(".jpg")) {
			System.out.println(subFile);//E:\新建文本文档.jpg
		}
	}
}
```

#19.20_File类(文件名称过滤器的概述及使用)
A:文件名称过滤器的概述:

    public String[] list(FilenameFilter filter)//传入文件名称过滤器,列出名称数组
	public File[] listFiles(FileFilter filter)//传入文件过滤器,列出文件对象数组

B:文件名称过滤器的使用:

    需求:判断E盘目录下是否有后缀名为.jpg的文件,如果有,就输出该文件名称:
    
C:源码分析:

    带文件名称过滤器的list()方法的源码如下:
     public String[] list(FilenameFilter filter) {
            String names[] = list();
            if ((names == null) || (filter == null)) {
                return names;
            }
            List<String> v = new ArrayList<>();
            for (int i = 0 ; i < names.length ; i++) {
                if (filter.accept(this, names[i])) {
                    v.add(names[i]);//accept方法返回true,接收的数组才有元素,否则是一个没有元素的数组,数组长度为0
                }
            }
            return v.toArray(new String[v.size()]);
        }
        
代码演示如下:
```
public static void main(String[] args) {
	File dir = new File("E:\\");
	
//	String[] arr = dir.list();//方式一,获取e盘下所有的文件或文件夹
//	for (String string : arr) {
//		if(new File(dir,string).isFile() && string.endsWith(".jpg")) {//是文件并且文件后缀名是.jpg
//			System.out.println(string);//新建文本文档.jpg,
//		}
//	}
//		
//	File[] subFiles = dir.listFiles();//方式二,获取e盘下所有的文件或文件夹对象
//		
//	for (File subFile : subFiles) {///要判断是文件并且文件名后缀是.jpg
//		if(subFile.isFile() && subFile.getName().endsWith(".jpg")) {
//			System.out.println(subFile);//E:\新建文本文档.jpg
//		}
//	}
	
	String[] arr = dir.list(new FilenameFilter() {//方式三,利用list方法传入文件名称过滤器
		
    @Override
    	public boolean accept(File dir, String name) {
    //				System.out.println(dir);//E:\,目录,打印看看分别是什么
    //				System.out.println(name);//javase&javaee...文件或者文件夹的名字
    		File file = new File(dir, name);//利用参数构造File对象
    //		return file.isFile() && file.getName().endsWith(".jpg");//返回true数组才有值,
    		return false;
    	}
    });
    
	//打印accept方法返回false时接收的数组内容看看是什么不就知道了
	System.out.println(arr);//[Ljava.lang.String;@949f69有地址,不是null,所以不会抛出空指针异常
	for (String string : arr) {//若上面accept方法返回false,这里什么也没有输出,也不会报异常
		System.out.print("00000");//没有输出,说明没进来
		System.out.println(string);//
		System.out.println("11111");//没有输出,说明没进来
	}
	//返回false,什么也没有输出的原因,反编译看底层调用的是什么,如下所示,根本没有进入到循环里:
	/*String args1[];
	int j = (args1 = arr).length;//0,数组长度为0,说明里面没有元素,
	for (int i = 0; i < j; i++)//不满足条件,就不进去循环,所以什么也没有输出,说明j=0;
	{
		String string = args1[i];
		System.out.print("00000");
		System.out.println(string);
		System.out.println("11111");
	}*/
	System.out.println("666666666666");//能走到这,更加说明没有进入到增强for循环里面
}
```
    总结:File类的list方法传入文件名称过滤器调用accept方法返回true,接收的数组才有元素,否则是一个没有元素的数组,
    数组长度为0,所以返回false时打印接收的数组元素什么也没有输出,既然返回true时有元素输出,那么用符合条件,
    返回true则数组里元素就是添加的内容,如return file.isFile() && file.getName().endsWith(".jpg");

#19.21_day19总结
    把今天的知识点总结一遍:
    异常以及异常处理-File类

