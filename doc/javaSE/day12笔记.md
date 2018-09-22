#12.01_常见对象(Scanner的概述和方法介绍)(掌握)
    A:Scanner的概述:
    B:Scanner的构造方法原理:
        Scanner(InputStream source)
    	System类下有一个静态的字段:
    		public static final InputStream in;标准的输入流,对应着键盘录入
    		
C:一般方法:
###hasNextXxx();判断是否还有下一个输入项,其中Xxx可以是Int,Double等,如果需要判断是否包含下一个字符串,则可以省略Xxx

###nextXxx();获取下一个输入项,Xxx的含义和上个方法中的Xxx相同,默认情况下,Scanner使用空格,回车等作为分隔符

代码演示如下:
```
public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//创建键盘录入对象
//		int i = sc.nextInt();//调用这个方法时,如果你录入的不是整数,会报输入不匹配异常

//		System.out.println(i);//解决方法,可用下面判断输入的是不是整数的方法hasNextInt:
		if(sc.hasNextInt()) {//判断键盘录入的是否是int类型的数据
			int i = sc.nextInt();//键盘录入的数据存储在i中
			System.out.println(i);
		}else {
			System.out.println("输入的类型错误");
		}	
}
```	

#12.02_常见对象(Scanner获取数据出现的小问题及解决方案)(掌握)
A:两个常用的方法:
###public int nextInt():获取一个int类型的值
###public String nextLine():获取一个字符串String类型的值
	
B:案例演示:

	a:先分别演示获取多个int值,多个String值的情况
###b:再演示先获取int值,然后获取String值出现问题:
###c:问题解决方案:
    第一种:先获取一个数值后,在创建一个新的键盘录入对象获取字符串
    
    第二种:把所有的数据都先按照字符串获取,然后要什么,你就对应的转换为什么(后面讲)
		
代码演示如下:
```
public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入第一个整数:");
		//nextInt()是键盘录入整数的方法,当我们录入10回车的时候,其实在键盘上录入的是10和\r\n,
		int i = sc.nextInt();//nextInt()方法只获取10就结束了,
		
		System.out.println("请输入第二个字符串:");
		//nextLine()是键盘录入字符串的方法,可以接收任意类型,但是他只要遇到\r\n就结束一行,这是问题出现的关键!!!!!!!!!
		String line2 = sc.nextLine();//遇到\r\n就结束一行,所以什么也没有接收到,输出结果如下
		System.out.println("i = " + i + ", line2 = " + line2);//输出i = 10, line2 = 
		System.out.println(i);//10
		System.out.print("11111111111");//111111111112222222222222,说明line什么都没有
		System.out.print(line2);//遇到遇到\r\n就结束了,什么也没接收到
		System.out.println("2222222222222");
		
		//解决方法一:创建两次对象,但是浪费空间,如下所示:
		/*
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		
		Scanner sc2 = new Scanner(System.in);
		String line = sc2.nextLine();
		
		System.out.println(i);
		System.out.println(line);
		*/
		
	    //解决方法二:键盘录入的都是字符串,都用nextLine方法,然后再把整数字符串转换成整数的方法,后面讲
	    
}
```
    总结:nextInt()是键盘录入整数的方法,当我们录入10回车的时候,其实在键盘上录入的是10和\r\n,(因为回车换行对应的就是\r\n),
    而nextLine()是键盘录入字符串的方法,可以接收任意类型,但是他只要遇到\r\n就结束一行,什么没有接收到,这是问题出现的关键!
	
#12.03_常见对象(String类的概述)(掌握)
A:String类的概述:

    通过JDK提供的API,查看String类的说明
	可以看到这样的两句话:
###a:字符串字面值"abc"也可以看成是一个字符串对象,
###b:字符串是常量,一旦被赋值,就不能被改变
代码演示如下:
```
public static void main(String[] args) {

		String str = "abc";//"abc"可以看成是一个字符串对象,
		str = "def";//当把"def"赋值给str,原来的"abc"就变成了垃圾,不能改变的是abc,def,而不是指str引用
		
		System.out.println(str);//输出def,说明String类重写了toString方法返回的是该对象本身
		
}
```

#12.04_常见对象(String类的构造方法)(掌握)
A:常见构造方法:

	public String():空构造
	
	public String(byte[] bytes):把字节数组转成字符串
	public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串
	
	public String(char[] value):把字符数组转成字符串
	public String(char[] value,int index,int count):把字符数组的一部分转成字符串
	
	public String(String original):把字符串常量值转成字符串
	
B:案例演示:	

	演示String类的常见构造方法:
代码演示如下:
```
public static void main(String[] args) {
		String s1 = new String();
		System.out.print("111111111");
		System.out.print(s1);//空,什么都没有,对应"",不信?构造方法传入""输出看看
		System.out.println("222222222");//111111111222222222
		
		byte[] arr1 = {97,98,99};		
		String s2 = new String(arr1);//解码,将计算机读的懂的转换成我们读的懂的
		System.out.println(s2);//abc
		
		byte[] arr2 = {97,98,99,100,101,102};
		String s3 = new String(arr2,2,3);//将arr2字节数组从2索引开始转换3个
		System.out.println(s3);//cde
		
		char[] arr3 = {'a','b','c','d','e'};//将字符数组转换成字符串
		String s4 = new String(arr3);
		System.out.println(s4);//abcde
		
		String s5 = new String(arr3,1,3);//将arr3字符数组,从1索引开始转换3个
		System.out.println(s5);//bcd
		
		String s6 = new String("heima");//把字符串常量值转成字符串
		System.out.println(s6);//heima
}
```	

#12.05_常见对象(String类的常见面试题)(掌握)
    1.判断定义为String类型的s1和s2是否相等:
        String s1 = "abc";//常量池的特点,常量没有就创建,有就使用同一个,所以存储的都是常量池中的地址值都相等
    	String s2 = "abc";
    	System.out.println(s1 == s2);//true					
    	System.out.println(s1.equals(s2));
    	
    2.下面这句话在内存中创建了几个对象?
        String s1 = new String("abc");//一个是常量池中的常量对象,一个是拷贝类常量池的常量作为值的字符串对象
        
    3.判断定义为String类型的s1和s2是否相等:
    	String s1 = new String("abc");//画个内存图就知道,这个引用记录的是堆内存中的字符串地址值	
    	
    	String s2 = "abc";//引用记录的是常量池中常量对象的地址值,所以两个是不相同的
    	System.out.println(s1 == s2);//false	
    	System.out.println(s1.equals(s2));
    	
    4.判断定义为String类型的s1和s2是否相等:
    	String s1 = "a" + "b" + "c";//常量优化机制,在编译时就把右边变成了abc,
    	String s2 = "abc";//上面已经有abc了,就直接使用常量池中的,所以是同一个对象,同一个地址值
    	System.out.println(s1 == s2);//true
    	System.out.println(s1.equals(s2));
    	
    5.判断定义为String类型的s1和s2是否相等:
    	String s1 = "ab";
    	String s2 = "abc";//s2存储的是常量池中的地址值,
    	String s3 = s1 + "c";//字符串拼接,底层创建了sb对象,然后sb转换成字符串对象,字符串对象地址值赋给s3,
    	System.out.println(s3 == s2);//所以,不是同一个对象,他们的地址值不相等,所以false
    	System.out.println(s3.equals(s2));

#12.06_常见对象(String类的判断功能)(掌握)
A:String类的判断功能:

    boolean equals(Object obj):比较字符串的内容是否相同,区分大小写
	boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,忽略大小写
	
	boolean contains(String str):判断大字符串中是否包含小字符串
	
	boolean startsWith(String str):判断字符串是否以某个指定的字符串开头
	boolean endsWith(String str):判断字符串是否以某个指定的字符串结尾
	
	boolean isEmpty():判断字符串是否为空
	
另外注意:""和null的区别:

    ""是字符串常量,同时也是一个String类的对象,既然是对象当然可以调用String类中的方法;
	null是空常量,不能调用任何的方法,否则会出现空指针异常,null常量可以给任意的引用数据类型赋值
	
代码演示如下:
```
public static void main(String[] args) {
		//demo1();
		//demo2();
		
		String s1 = "heima";
		String s2 = "";
		String s3 = null;
		
		System.out.println(s1.isEmpty());//false
		System.out.println(s2.isEmpty());//true
		System.out.println(s3.isEmpty());//java.lang.NullPointerException
		
}

private static void demo2() {
    String s1 = "我爱heima,哈哈";
    String s2 = "heima";
    String s3 = "baima";
    String s4 = "我爱";
    String s5 = "哈哈";
    
    System.out.println(s1.contains(s2));//true,判断是否包含传入的字符串
    System.out.println(s1.contains(s3));//false
    
    System.out.println("------------------");
    System.out.println(s1.startsWith(s4));//true,判断是否以传入的字符串开头
    System.out.println(s1.startsWith(s5));//false
    
    System.out.println("------------------");
    System.out.println(s1.endsWith(s4));//false,判断是否以传入的字符串结尾
    System.out.println(s1.endsWith(s5));//true
}

private static void demo1() {
    String s1 = "heima";
    String s2 = "heima";
    String s3 = "HeiMa";
    
    System.out.println(s1.equals(s2));//true
    System.out.println(s2.equals(s3));//false
    
    System.out.println("---------------");
    
    System.out.println(s1.equalsIgnoreCase(s2));//true	
    System.out.println(s1.equalsIgnoreCase(s3));//true,不区分大小写
}
```

#12.07_常见对象(模拟用户登录)(掌握)
A:案例演示:

    需求:模拟登录,给三次机会,并提示还有几次
	用户名和密码都是admin
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);//创建键盘录入对象
    
    for(int i = 0; i < 3; i++) {
        System.out.println("请输入用户名:");
        String userName = sc.nextLine();//将键盘录入的用户名存储在userName中
        System.out.println("请输入密码:");
        String password = sc.nextLine();//将键盘录入的密码存储在password中
        
        //如果是字符串常量和字符串变量比较,通常都是字符串常量调用方法,将变量当作参数传递,防止空指针异常
        if("admin".equals(userName) && "admin".equals(password)) {
        	System.out.println("欢迎" + userName + "登录");
        	break;//正确就跳出循环
        	
        }else {
        	if(i == 2) {//最后一次换个提示
        		System.out.println("您的错误次数已到,请明天再来吧");
        	}else {
        		System.out.println("录入错误,您还有" + (2-i) + "次机会");
        	}
        }
    }
}
```	

#12.08_常见对象(String类的获取功能)(掌握)
A:String类的获取功能:

    int length():获取字符串的长度,获取的是每一个字符的个数的总数,中文当成一个字符
    
	char charAt(int index):获取指定索引位置的字符
	
	int indexOf(int ch):返回指定字符在此字符串中第一次出现处的索引
	int indexOf(String str):返回指定字符串第一个字符,在此字符串中第一次出现处的索引
	
	int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引
	int indexOf(String str,int fromIndex):返回指定字符串在此字符串中从指定位置后第一次出现处的索引
	
	lastIndexOf两个方法,都是从指定位置从后往前找,第一个字符出现的索引
	
	String substring(int start):从指定位置开始截取字符串,默认到末尾
	String substring(int start,int end):从指定位置开始到指定位置结束截取字符串,不包括末尾
	
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    //demo4();
    
    String s = "woaiheima";
    s.substring(4);//subString截取后会产生一个新的字符串,需要将新的字符串记录,需要用变量接收
    System.out.println(s);//woaiheima,不用变量接收,输出的当然还是原来的字符串
    
}

private static void demo4() {
	String s1 = "heimawudi";
	String s2 = s1.substring(5);//从指定索引开始截取后半段字符串
	System.out.println(s2);//wudi
	
	String s3 = s1.substring(0, 5);//包含头,不包含尾,左闭右开,记:神龙见首不见尾
	System.out.println(s3);//heima
	
}

private static void demo3() {
	String s1 = "woaiheima";
	int index1 = s1.indexOf('a', 3);//从指定位置开始向后找,即从3对应的索引值为i开始
	System.out.println(index1);//8
	
	int index2 = s1.lastIndexOf('a');//从后向前找,第一次出现的字符,对应的索引
	System.out.println(index2);//8
	
	int index3 = s1.lastIndexOf('a', 7);//从指定位置向前找,即从m开始往前找第一个出现的a的索引
	System.out.println(index3);//2
	
}

private static void demo2() {
	String s1 = "heima";
	int index = s1.indexOf('e');//参数接收的是int类型的,传递char类型的会自动提升
	System.out.println(index);//1
	
	int index2 = s1.indexOf('z');//如果不存在返回-1,表示找不到对应字符
	System.out.println(index2);//-1
	
	int index3 = s1.indexOf("ma");//获取字符串中第一个字符出现的位置,对应的索引
	System.out.println(index3);//3,因为m出现的索引是3
	
	int index4 = s1.indexOf("ia");//不是相连字符构成的字符串返回-1,表示找不到
	System.out.println(index4);//-1
	
}

private static void demo1() {
	//int[] arr = {11,22,33};
	//System.out.println(arr.length);//3,数组中的length是属性
	
	String s1 = "heima";
	System.out.println(s1.length());//5,length()是一个方法,获取的是每一个字符的个数的总数
	String s2 = "你要减肥,造吗?";//8,一个中文是一个字符
	System.out.println(s2.length());
	
	char c = s2.charAt(5);//根据索引获取对应位置的字符
	System.out.println(c);//造
	char c2 = s2.charAt(10);//StringIndexOutOfBoundsException字符串索引越界异常
	System.out.println(c2);
	
}
```

#12.09_常见对象(字符串的遍历)(掌握)
A:案例演示:

	需求:遍历字符串:
代码演示如下:
```
public static void main(String[] args) {
		String s = "heima";
		
		for(int i = 0; i < s.length(); i++) {//通过for循环获取到字符串中每个字符的索引
			/*
			char c = s.charAt(i);
			System.out.println(c);
			*/
			System.out.println(s.charAt(i));//通过索引获取每一个字符
		}
		
    //	demo2(s);

}

private static void demo2(String s) {
	System.out.println("字符串转换为字符数组,第二种字符串的遍历方式");
	char[] array = s.toCharArray();
	for (int i = 0; i < array.length; i++) {
		System.out.println(array[i]);
	}
	
	/*
	System.out.println("增强for");//这个后面再讲,可以不看
	for (char c : array) {
		System.out.println(c);
	}
	*/
}
```	
	
###12.10_常见对象(统计不同类型字符个数)(掌握)
A:案例演示:

    需求:统计一个字符串中大写字母字符,小写字母字符,数字字符出现的次数,其他字符出现的次数,如下面的字符串:
	ABCDEabcd123456!@#$%^
	
代码演示如下:
```
public static void main(String[] args) {
    String s = "ABCDEabcd123456!@#$%^";
    
    int big = 0;
    int small = 0;
    int num = 0;
    int other = 0;
    
    //1,获取每一个字符,通过for循环遍历:
    for(int i = 0; i < s.length(); i++) {
    	char c = s.charAt(i);//通过索引获取每一个字符
    	
    	//2,判断字符是否在这个范围内
    	if(c >= 'A' && c <= 'Z') {
    		big++;//如果满足是大写字母,就让其对应的变量自增
    		
    	}else if(c >= 'a' && c <= 'z') {
    		small++;
    	}else if(c >= '0' && c <= '9') {
    		num++;
    	}else {
    		other++;
    	}
    }
    
    //3,打印每一个计数器的结果:
    System.out.println(s + "中大写字母有:" + big + "个,小写字母有:" + small + "个,数字字符:" 
    + num + "个,其他字符:" + other + "个");
    
}
```

#12.11_常见对象(String类的转换功能)(掌握)
A:String的转换功能:

    byte[] getBytes():把字符串转换为字节数组
	char[] toCharArray():把字符串转换为字符数组
	
	static String valueOf(char[] chs):把字符数组转成字符串
	static String valueOf(int i):把int类型的数据转成字符串
	注意:String类的valueOf方法可以把任意类型的数据转成字符串

	String toLowerCase():把字符串转成小写(了解)
	String toUpperCase():把字符串转成大写
	String concat(String str):把字符串拼接
	
代码演示如下:
```
public static void main(String[] args) {
    demo1();
    demo2();
    demo3();

	String s1 = "heiMA";
	String s2 = "chengxuYUAN";
	String s3 = s1.toLowerCase();//heima
	String s4 = s2.toUpperCase();//CHENGXUYUAN
	
    System.out.println(s3);//如果上面没有用变量s3和s4接收,输出s1,s2还是原来的值,跟subString方法一样
	System.out.println(s4);
		
	System.out.println(s3 + s4);//heimaCHENGXUYUAN//用+拼接字符串更强大,可以用字符串与任意类型拼接
	System.out.println(s3.concat(s4));//heimaCHENGXUYUAN//concat方法调用的和传入的都必须是字符串
	
}

private static void demo3() {
    char[] arr = {'a','b','c'};
    String s = String.valueOf(arr);//底层是由String类的构造方法完成的即 return new String(字符数组);
	System.out.println(s);//abc
	
	String s2 = String.valueOf(100);//将100转换为字符串100,与100拼接得出↓
	System.out.println(s2 + 100);//100100
	
	Person p1 = new Person("张三", 23);
    System.out.println(p1);//默认调用了Object类的toString方法,没重写输出的就是类全路径@哈希码的十六进制
    
    String s3 = String.valueOf(p1);//调用的是对象的toString方法,没重写输出的就是类全路径@哈希码的十六进制
	System.out.println(s3);//跟p1结果是一样的
	
}

private static void demo2() {
	String s = "heima";
	char[] arr = s.toCharArray();//将字符串转换为字符数组,进行字符串的遍历方式二
	
	for (int i = 0; i < arr.length; i++) {
		System.out.print(arr[i] + " ");//h e i m a 
	}
	
}

private static void demo1() {
	String s1 = "abc";
	byte[] arr = s1.getBytes();
	
	for (int i = 0; i < arr.length; i++) {
	    //System.out.print(arr[i] + " ");//97 98 99即输出字符对应的ASCII码表值
	    
	}
	
	String s2 = "你好你好";
	byte[] arr2 = s2.getBytes();//通过gbk码表将字符串转换成字节数组
	
	for (int i = 0; i < arr2.length; i++) {	//编码:把我们看的懂转换为计算机看的懂得
//	System.out.print(arr2[i] + " ");//-60 -29 -70 -61 -60 -29 -70 -61都是负数,用的是没升级的码表

		//gbk码表一个中文代表两个字节,第一个字节是负数,不信?看下面↓
		
	}									
	
	String s3 = "琲";
	byte[] arr3 = s3.getBytes();
	
	for (int i = 0; i < arr3.length; i++) {////gbk码表特点,中文的第一个字节肯定是负数
		System.out.print(arr3[i] + " ");//-84 105
	}
	
}
```	
	
#12.12_常见对象(按要求转换字符)(链式编程掌握)
A:案例演示:

    需求:把一个字符串的首字母转成大写,其余为小写(只考虑英文大小写字母字符)
    
代码演示如下:
```
public static void main(String[] args) {
    String s = "woaiHEImaniaima";
    
    String s2 = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());
    
    System.out.println(s2);//链式编程,只要保证每次调用完方法返回的是对象,就可以继续调用
		
}
```	

#12.13_常见对象(把数组转成字符串)
A:案例演示:

    需求:把数组中的数据按照指定个格式拼接成一个字符串
		举例:
			int[] arr = {1,2,3};
		输出结果:
			"[1, 2, 3]"
			
代码格式如下:
```
public static void main(String[] args) {
    int[] arr = {1,2,3};
    String s = "[";//定义一个字符串用来与数组中元素拼接
    
    for (int i = 0; i < arr.length; i++) {//{1,2,3}
    	if(i == arr.length - 1) {
    		s = s + arr[i] + "]";//[1, 2, 3]//循环里面得到数组元素判断拼接,循环后输出结果
    		
    	}else {
    		s = s + arr[i] + ", ";//[1, 2, 
    	}
    	
    }
    
    System.out.println(s);//这里节省了代码,把逗号和空格写在一个字符串里", ",有点聪明哦!
		
}
```	

#12.14_常见对象(String类的其他功能)
A:String的替换功能及案例演示:

    String replace(char old,char new)//replace sth with sth,用后者替换前者,如果前者不存在,结果还是原来的
    
    String replace(String old,String new)//一样的,如果前者不存在,就没有替换成功,还是原来的字符串
    
B:String的去除字符串两端空格及案例演示:

    String trim()//去除字符串两端空格,当然不包括中间的空格
    
C:String的按字典顺序比较两个字符串及案例演示:

	int compareTo(String str)//字符不一样,按照码表值比较,第一个字符的值减去第二个字符值,如果相等,
    依此比较第二个字符值,但是字符一样,如果第一个相等,后面没有了,比较的就是字符的个数,如这里的1个a-4个a=-3;
    
	int compareToIgnoreCase(String str)(了解)
	
代码演示如下:
```
public static void main(String[] args) {
    demo1();
    demo2();
    
    String s1 = "a";
    String s2 = "aaaa";
    
    int num = s1.compareTo(s2);//按照码表值比较,第一个字符的值减去第二个字符值,如果相等,依此比较第二个字符
    System.out.println(num);//-3,如果第一个相等,后面没有了,比较的就是字符的个数,如这里的1个a-4个a=-3;
    	
    String s3 = "黑";
    String s4 = "马";
    int num2 = s3.compareTo(s4);
    System.out.println('黑' + 0);//40657,查找的是unicode码表值
    System.out.println('马' + 0);//39532
    System.out.println(num2);//1125 = 40657 - 39532;
    
    String s5 = "heima";
    String s6 = "HEIMA";
    int num3 = s5.compareTo(s6);
    System.out.println(num3);//32
    	
    int num4 = s5.compareToIgnoreCase(s6);
    System.out.println(num4);//0,不区分大小写
    
}

//另外,compareToIgnoreCase底层源码调用的是下面的compare方法:
public int compare(String s1, String s2) {
    int n1 = s1.length();
    int n2 = s2.length();
    int min = Math.min(n1, n2);
    for (int i = 0; i < min; i++) {
        char c1 = s1.charAt(i);
        char c2 = s2.charAt(i);
        if (c1 != c2) {//两个字符串对应位置的字符比较,如果不相等,
            c1 = Character.toUpperCase(c1);//将c1字符转换成大写
            c2 = Character.toUpperCase(c2);//将c2字符转换成大写
            if (c1 != c2) {
                c1 = Character.toLowerCase(c1);//将c1字符转换成小写
                c2 = Character.toLowerCase(c2);//将c2字符转换成小写
                if (c1 != c2) {
                    // No overflow because of numeric promotion
                    return c1 - c2;//还是不等就相减
                }
                
            }
            
        }
        
    }
    return n1 - n2;//不满足条件,字符串的长度相减
}

private static void demo2() {
    String s = "   hei   ma   ";
	String s2 = s.trim();//去除字符串两端空格,当然不包括中间的空格
	System.out.println(s2);//hei   ma
}

private static void demo1() {
    String s = "heima";
    String s2 = s.replace('i', 'o');//用o替换i
    System.out.println(s2);//heoma
    
    String s3 = s.replace('z', 'o');//z不存在,保留原字符不改变
    System.out.println(s3);//heima
    
    String s4 = s.replace("ei", "ao");
    System.out.println(s4);//haoma
    
    String s5 = s.replace("e i", "ao");//如果前者不存在(包括不连续),就没有替换成功,还是原来的字符串
    System.out.println(s5);//heima
}
```	

#12.15_常见对象(字符串反转)
A:案例演示:

    需求:把字符串反转:
	举例:键盘录入"abc"		
	输出结果:"cba"
	
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);//创建键盘录入对象
    System.out.println("请输入一个字符串:");
    String line = sc.nextLine();//将键盘录入的字符串存储在line中
    
    char[] arr = line.toCharArray();//将字符串转换为字符数组
    
    String s = "";
    for(int i = arr.length-1; i >= 0; i--) {//倒着遍历字符数组元素,
    	s = s + arr[i];//拼接成字符串
    }
    
    System.out.println(s);
}
```
总结:字符串反转,就是倒序遍历字符数组拼接每一个元素到一个新的空的字符串中即可

#12.16_常见对象(在大串中查找小串出现的次数思路图解)
A:画图演示:见截图:

    需求:统计大串中小串出现的次数:
	这里的大串和小串可以自己根据情况给出
	
    总结:思路就是找到小字符串返回索引,截取返回索引到小字符串的长度赋值给大的字符串,不断循环,
    对indexOf方法有返回值就是找到小字符串,和subString方法还有循环的考查,
代码演示如下:
```
public static void main(String[] args) {
    //定义大串
    String max = "woaiheima,heimabutongyubaima,wulunheimahaishibaima,zhaodaogongzuojiushihaoma";
    //定义小串
    String min = "heima";
    
    //定义计数器变量
    int count = 0;
    //定义索引
    int index = 0;
    //定义循环,判断小串是否在大串中出现
    while((index = max.indexOf(min)) != -1) {
    	count++;//计数器自增
    	max = max.substring(index + min.length());//注意这里截取的是从开始索引到末尾的那段
    	
    }
    
    System.out.println(count);
}
```

#12.17_常见对象(在大串中查找小串出现的次数代码实现)
A:案例演示:

	统计大串中小串出现的次数:
	
代码演示如下:
```
public static void main(String[] args) {
    //定义大串
    String max = "woaiheima,heimabutongyubaima,wulunheimahaishibaima,zhaodaogongzuojiushihaoma";
    //定义小串
    String min = "heima";
    
    //定义计数器变量
    int count = 0;
    
    //上面的方法不会写?那就用死循环来做,然后判断条件跳出
    while (true) {
        int indexFind = max.indexOf(min);
        if (indexFind==-1) {
        	break;
        }
        
        count++;
        max = max.substring(indexFind+min.length());
    }
    
    System.out.println(count);
}
```

#12.18_day12总结
    把今天的知识点总结一遍:
    Scanner类-String类
    
