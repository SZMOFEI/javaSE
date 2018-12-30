#14.01_常见对象(正则表达式的概述和简单使用)
A:正则表达式:

    是指一个用来描述或者匹配一系列符合某个语法规则的字符串的单个字符串,其实就是一种规则,有自己特殊的应用
    作用:比如注册邮箱,邮箱有用户名和密码,一般会对其限制长度,这个限制长度的事情,就是正则表达式做的
    
B:案例演示:

    需求:校验qq号码:
    	1:要求必须是5-15位数字
    	2:0不能开头
    	3:必须都是数字	
    a:非正则表达式实现
    b:正则表达式实现
代码演示如下:
```
public static void main(String[] args) {
	System.out.println(checkQQ("012345"));//false
	System.out.println(checkQQ("a1b345"));//false
	System.out.println(checkQQ("123456"));//true
	System.out.println(checkQQ("1234567890987654321"));//false
	
	//正则表达式实现:String类的matches(String regex)方法,看看调用字符串是否匹配正则表达式,匹配返回true
	String regex = "[1-9]\\d{4,14}";
	System.out.println("2553868".matches(regex));//true,看看调用字符串是否匹配正则表达式,匹配返回true
	System.out.println("012345".matches(regex));//false
	System.out.println("2553868abc".matches(regex));//false
}
	
//非正则表达式实现:
public static boolean checkQQ(String qq) {
	boolean flag = true;//如果校验qq不符合要求就把flag置为false,如果符合要求直接返回
	
	if(qq.length() >= 5 && qq.length() <= 15) {//要求必须是5-15位数字
		if(!qq.startsWith("0")) {//0不能开头
			char[] arr = qq.toCharArray();//将字符串转换成字符数组
			for (int i = 0; i < arr.length; i++) {
				char ch = arr[i];//得到每一个字符
				if(!(ch >= '0' && ch <= '9')) {//必须都是数字,判断字符范围
					flag = false;//不是数字
					break;
				}
			}
		}else {
			flag = false;//以0开头,不符合qq标准
		}
	}else {
		flag = false;//长度不符合
	}

	return flag;
}
```
总结:正则表达式就是一个字符串,代表一定的规则,如果我们需要的字符串要匹配某种规则,就可以调用字符串的匹配方法传入代表一定规则的正则表达式字符串,
如果匹配,matches(String regex)方法的就会返回true作为返回值,不匹配就是返回false!
下面来学习不同的字符串表示的不同规则:总体而言就是单个字符和多个字符的规则,看下面知识会有总结:↓

#14.02_常见对象(字符类演示)//打开文档说明书,搜索Pattern类(正则表达式的编译表示形式)可以找到以下内容:

A:字符类//在打开文档里面的文字-正则表达式的构造摘要下找到:

    [abc] a、b 或 c(简单类)
	[^abc] 任何字符,除了 a、b 或 c(否定)
	[a-zA-Z] a到z或A到Z,两头的字母包括在内(范围)
	[0-9] 0到9的字符都包括在内
	
总结:注意,这里的字符类针对的都是单个字符,下面例子会有说明:↓
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    //demo4();
    //demo5();
    //demo6();
    
    String regex = "[a-z&&[^m-p]]";//a 到 z,而非 m 到 p:[a-lq-z](减去) 
    System.out.println("m".matches(regex));//false
    System.out.println("a".matches(regex));//true
    System.out.println("z".matches(regex));//true
    System.out.println("n".matches(regex));//false
    System.out.println("ab".matches(regex));//false,这里针对的都是单个字符,ab是两个字符
    
}

private static void demo6() {
	String regex = "[a-z&&[^bc]]";//[a-z&&[^bc]] a 到 z，除了 b 和 c:[ad-z](减去)
	System.out.println("a".matches(regex));//true
	System.out.println("b".matches(regex));//false
	System.out.println("1".matches(regex));//false
	System.out.println("az".matches(regex));//false,这里针对的都是单个字符,az是两个字符
	
}

private static void demo5() {
	String regex = "[a-z&&[def]]";//[a-z&&[def]] d、e 或 f(交集),取交集
	System.out.println("a".matches(regex));//false
	System.out.println("d".matches(regex));//true
	System.out.println("de".matches(regex));//false,这里针对的都是单个字符,de是两个字符
	
}

private static void demo4() {
	String regex = "[a-d[m-p]]";//a到 d或 m到 p:[a-dm-p](并集)
	System.out.println("a".matches(regex));//true
	System.out.println("m".matches(regex));//true
	System.out.println("n".matches(regex));//true
	System.out.println("z".matches(regex));//false
	System.out.println("am".matches(regex));//false,这里针对的都是单个字符,am是两个字符
	
}

private static void demo3() {
	String regex = "[a-zA-Z]";//a到 z或 A到 Z,两头的字母包括在内(范围)
	System.out.println("a".matches(regex));//true
	System.out.println("A".matches(regex));//true
	System.out.println("z".matches(regex));//true
	System.out.println("Z".matches(regex));//true
	System.out.println("1".matches(regex));//false
	System.out.println("%".matches(regex));//false
	System.out.println("Ab".matches(regex));//false,这里针对的都是单个字符,Ab是两个字符
	
}

private static void demo2() {
	String regex = "[^abc]";//单个字符,除了 a、b 或 c(否定)
	System.out.println("a".matches(regex));//false
	System.out.println("b".matches(regex));//false
	System.out.println("c".matches(regex));//false
	System.out.println("d".matches(regex));//true
	System.out.println("1".matches(regex));//true
	System.out.println("%".matches(regex));//true
	System.out.println("10".matches(regex));//false,注意10代表1字符和0字符,而不是单个字符
	
}

private static void demo1() {
    String regex = "[abc]";//[]代表单个字符,a、b 或 c(简单类)
	System.out.println("a".matches(regex));//true
	System.out.println("b".matches(regex));//true
	System.out.println("c".matches(regex));//true
	System.out.println("d".matches(regex));//false
	System.out.println("1".matches(regex));//false
	System.out.println("%".matches(regex));//false
	System.out.println("ab".matches(regex));//false,这里针对的都是单个字符,ab是两个字符
	
}
```	

#14.03_常见对象(预定义字符类演示)
A:预定义字符类:

    . 任何字符
	\d 数字:[0-9]
	\w 单词字符:[a-zA-Z_0-9]
	
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    //demo4();
    //demo5();
    //demo6();
    
    String regex = "\\W";//非单词字符:[^\w],即不是英文字符大小写或者下划线或者数字单个字符
    System.out.println("a".matches(regex));//false,这里就算是多个字符如"ab".matches(regex)也是false
    System.out.println("z".matches(regex));//false
    System.out.println("_".matches(regex));//false
    System.out.println("%".matches(regex));//true
    
}

private static void demo6() {
	String regex = "\\w";//单词字符:[a-zA-Z_0-9],英文字符大小写或者下划线或者数字单个字符
System.out.println("a".matches(regex));//true,必须是单个字符,如多个字符"aa".matches(regex)则false
	System.out.println("z".matches(regex));//true
	System.out.println("_".matches(regex));//true//不要忘了下划线也是单词字符
	System.out.println("%".matches(regex));//false
	
}

private static void demo5() {
	String regex = "\\S";//非空白字符:[^\s],即不是空白字符
	System.out.println(" ".matches(regex));//空格,false
	System.out.println("	".matches(regex));//tab,false
	System.out.println("a".matches(regex));//true
	System.out.println("ab".matches(regex));//false,这里针对的也是单个字符
	
}

private static void demo4() {
	String regex = "\\s";//\s 空白字符:[ \t\n\x0B\f\r],即一个空格或者tab,回车,换行,垂直tab,翻页
	System.out.println(" ".matches(regex));//true,一个空格
	System.out.println("	".matches(regex));//true,一个tab键
	System.out.println("    ".matches(regex));//false四个空格是四个字符不是单个字符,容易迷惑为tab
	
}

private static void demo3() {
	String regex = "\\D";//非数字:[^0-9]不是0到9的字符
	System.out.println("0".matches(regex));//false
	System.out.println("9".matches(regex));//false
	System.out.println("a".matches(regex));//true
	System.out.println("ab".matches(regex));//false,这里针对的也是单个字符
	
}

private static void demo2() {
	String regex = "\\d";//数字:[0-9] 数字0到9,//\代表转义字符,如果想表示\d的话,需要\\d
	System.out.println("0".matches(regex));//true
	System.out.println("a".matches(regex));//false
	System.out.println("9".matches(regex));//true
	System.out.println("01".matches(regex));//false,针对的是单个字符
	
}

private static void demo1() {
	String regex = ".";//. 任何一个字符,那么..代表任意两个字符
	System.out.println("a".matches(regex));//true
	System.out.println("ab".matches(regex));//false,针对的是单个字符
	
}
```
总结:这里的大小写是有区别的,一般大写表示非什么,如\d表示数字,\D表示非数字;\w表示单词,\W非单词;\s空白字符,\S非空白字符,同样,
这里的预定义字符类针对的也是单个字符,同样要注意哦!

#14.04_常见对象(数量词)
A:Greedy 数量词:

    X? X,一次或一次也没有
	X* X,零次或多次
	X+ X,一次或多次
	X{n} X,恰好n次 
	X{n,} X,至少n次 
	X{n,m} X,至少n次,但是不超过m次
	
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    //demo4();
    //demo5();
    
    String regex = "[abc]{5,15}";//至少 n次,但是不超过 m次,这里是至少 5次,但是不超过 15次
    System.out.println("abcba".matches(regex));//true,单个字符出现总数,至少 5次,但是不超过 15次
    System.out.println("abcbaabcabbabab".matches(regex));//true,单个字符出现总数是15次
    System.out.println("abcb".matches(regex));//false,单个字符出现总数为4次
    System.out.println("abcbaabaabcbaaba".matches(regex));//false单个字符出现总数超过 15次
    
}

public static void demo5() {
	String regex = "[abc]{5,}";//abc不管是单个还是单个字符总共出现至少 n次,这里是至少5次,包括5次
	System.out.println("abcba".matches(regex));//true,单个字符出现总数是5
	System.out.println("abcbaabcabbabab".matches(regex));//true,单个字符出现总数是5次以上
	System.out.println("abcb".matches(regex));//false,单个字符出现总数是4,不够5次
	System.out.println("abcbaaba".matches(regex));//true,单个字符出现总数是5次以上
	
}

public static void demo4() {
	String regex = "[abc]{5}";//abc其中一个字符或者总数出现恰好n次 ,这里的是恰好5次
	System.out.println("abcba".matches(regex));//true,abc每一个字符总共出现5次,恰好5次-----
	System.out.println("abcbaabcabbabab".matches(regex));//false,a出现6次,不是5次
	System.out.println("abcb".matches(regex));//false,a出现一次,不是5次
	System.out.println("abcbaaba".matches(regex));//false,a出现4次,不是5次
	System.out.println("aaaaa".matches(regex));//true,a单个出现5次,恰好5次------
	
}

public static void demo3() {
	String regex = "[abc]+";//abc其中一个字符,出现一次或多次,不能有其他非中括号中的字符
	System.out.println("".matches(regex));//false,出现零次
	System.out.println("a".matches(regex));//true,a出现一次
	System.out.println("aaaaabbbbccccc".matches(regex));//true,a出现多次
	System.out.println("aaaaabbbbcdcccc".matches(regex));//false,不能有其他非中括号中的字符
	
}

public static void demo2() {
	String regex = "[abc]*";//abc其中一个字符出现零次或多次,多次包括一次,不能有其他非中括号中的字符
	System.out.println("".matches(regex));//true,出现零次的情况
	System.out.println("abc".matches(regex));//true,a出现1次
	System.out.println("a".matches(regex));//true,a出现1次
	System.out.println("abd".matches(regex));//false,不能有其他字符
	
}

public static void demo1() {
	String regex = "[abc]?";//中括号里的单个字符出现一次或一次也没有,不能有其他非中括号中的字符
	System.out.println("a".matches(regex));//true,a出现一次或者一次也没有
	System.out.println("b".matches(regex));//true
	System.out.println("c".matches(regex));//true
	System.out.println("d".matches(regex));//false,不能是非中括号里的字符
	System.out.println("".matches(regex));//true,这个就是一次也没有的情况,返回的是true
	
}
```

#14.05_常见对象(正则表达式的分割功能)
A:正则表达式的分割功能:

	String类的功能:public String[] split(String regex)//字符串匹配正则表达式切割,返回字符串数组接收
	
B:案例演示:

	正则表达式的分割功能:
代码演示如下:
```
public static void main(String[] args) {
    String s = "金三胖.郭美美.李dayone";
    String[] arr = s.split("\\.");//单个.代表任意字符,所以用转义字符\\.,通过正则表达式切割字符串
    
    for (int i = 0; i < arr.length; i++) {
    	System.out.println(arr[i]);//金三胖,换行输出郭美美,换行输出李dayone
    }
    
    System.out.println("11111111111111111");//检测不用转义字符用.的时候没有输出内容是否执行到这里
    
}
```

#14.06_常见对象(把给定字符串中的数字排序)
A:案例演示:

    需求:我有如下一个字符串:”91 27 46 38 50”,请写代码实现最终输出结果是:”27 38 46 50 91”
    
    总结:思路,字符串匹配空格切割成字符串数组,赋值字符串元素到一个新的当长度的整型数组,利用到基本类型包装类的解析整型方法,
    然后调用数组工具类对整型数组进行排序,最后数组元素拼接成字符串或者字符串缓冲区输出即可:
    
代码演示如下:
```
public static void main(String[] args) {
    String s = "91 27 46 38 50";
    //1,将字符串切割成字符串数组
    String[] sArr = s.split(" ");
    
    //2,将字符串转换成数字并将其存储在一个等长度的int数组中
    int[] arr = new int[sArr.length];
    for (int i = 0; i < arr.length; i++) {
    	arr[i] = Integer.parseInt(sArr[i]);//将数字字符串转换成数字,然后排序新数组
    }
    
    //3,排序
    Arrays.sort(arr);
    
    //4,将排序后的结果遍历并拼接成一个字符串27 38 46 50 91
    /*
    String str = "";
    for (int i = 0; i < arr.length; i++) {
    	if(i == arr.length - 1) {
    		str = str + arr[i];//27 38 46 50 91
    	}else {
    		str = str + arr[i] + " ";//27 38 46 50
    	}
    }
    System.out.println(str);
    */
    
    //推荐用字符串缓冲区来做,因为上面用字符串拼接时底层也会创建字符串缓冲区,并且会成为垃圾
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
    	if(i == arr.length - 1) {
    		sb.append(arr[i]);
    	}else {
    		sb.append(arr[i] + " ");
    	}
    }
    
    System.out.println(sb);
    
}
```

#14.07_常见对象(正则表达式的替换功能)
A:正则表达式的替换功能:

    String类的功能:public String replaceAll(String regex,String replacement)//把正则表达式匹配到的内容用后者替换
    
B:案例演示:

    正则表达式的替换功能://把正则表达式匹配到的所有内容不管是单个字符还是多个字符,都用后者替换
    
代码演示如下:
```
public static void main(String[] args) {
    String s = "wo111ai222heima";
    String regex = "\\d";//\\d代表的是任意数字
    
    String s2 = s.replaceAll(regex, "");//把正则表达式匹配到的任意数字都用空字符串替换
    System.out.println(s2);//woaiheima
}
```

#14.08_常见对象(正则表达式的分组功能)
A:正则表达式的分组功能:

    捕获组可以通过从左到右计算其开括号来编号,例如,在表达式((A)(B(C)))中,存在四个这样的组: 
        1     ((A)(B(C))) 
        2     (A 
        3     (B(C)) 
        4     (C) 
        
        组零始终代表整个表达式
        
B:案例演示:

    a:切割:
    	需求:请按照叠词切割:"sdqqfgkkkhjppppkl",最后输出字符串为"sdfghjkl"
    	
    b:替换:
    	需求:我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程
    	将字符串还原成:“我要学编程”
    	
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    
    String s = "我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程";//我要学编程
    String s2 = s.replaceAll("\\.+", "");//一个点或者多个点替换成空字符串,就成了下面的叠词,
    System.out.println(s2);//我我我我要要要要学学学学编编编编程程程程,然后一个或多个字符替换为任意一个字符,
    String s3 = s2.replaceAll("(.)\\1+", "$1");//$1代表第一组中的内容,(.)代表任意一个字符为一组,
    System.out.println(s3);//\\1+代表第一组出现一次到多次,最后输出我要学编程
 
}

public static void demo2() {
	//需求:请按照叠词切割:"sdqqfgkkkhjppppkl",最后输出字符串为"sdfghjkl"
	String s = "sdqqfgkkkhjppppkl";
	String regex = "(.)\\1+";//+代表第一组出现一次到多次,这里(.)代表任意一个字符为一组
	String[] arr = s.split(regex);//匹配正则表达式切割成字符串数组
	
	for (int i = 0; i < arr.length; i++) {//遍历字符串数组元素并不换行输出
		System.out.print(arr[i]);//sdfghjkl
	}
}

public static void demo1() {
	//叠词 快快乐乐,高高兴兴//(.)代表一个字符一组即对应一个快字
	String regex = "(.)\\1(.)\\2";//\\1代表第一组又出现一次,\\2代表第二组又出现一次
	System.out.println("快快乐乐".matches(regex));//true,匹配到正则表达式
	System.out.println("快乐乐乐".matches(regex));//false
	System.out.println("高高兴兴".matches(regex));//true,匹配到正则表达式
	System.out.println("死啦死啦".matches(regex));//false
	
	//叠词 死啦死啦,高兴高兴
String regex2 = "(..)\\1";//(..)代表任意两个字符为一组,对应死啦或者高兴两字,\\1代表第一组又出现一次
	System.out.println("死啦死啦".matches(regex2));//true
	System.out.println("高兴高兴".matches(regex2));//true
	System.out.println("快快乐乐".matches(regex2));//false
}
```

#14.09_常见对象(Pattern和Matcher的概述)
A:Pattern和Matcher的概述:

B:模式和匹配器的典型调用顺序:

    通过JDK提供的API,查看Pattern类的说明:
	典型的调用顺序是:首先要知道,模式类Pattern,代表的是正则表达式的编译表示形式,
	Pattern p = Pattern.compile("a*b");//模式类的静态编译方法得到模式类对象,
	Matcher m = p.matcher("aaaaab");//模式类对象调用匹配器方法得到匹配器类对象,
	boolean b = m.matches();//匹配器类对象调用匹配方法看是否匹配正则表达式
	
代码演示如下:
```
public static void demo1() {
    Pattern p = Pattern.compile("a*b");//a*b,a出现零次或多次,然后b,即获取到正则表达式
    Matcher m = p.matcher("aaaaab");//获取匹配器
    boolean b = m.matches();//看是否能匹配,匹配就返回true
    System.out.println(b);//true
    
    //String类的匹配正则表达式方法,与上面的模式和匹配器的典型调用顺序,结果一样:
    System.out.println("aaaaab".matches("a*b"));//true,String类的匹配正则表达式方法,与上面的结果一样
    //既然这样那为什么要用上面呢,别急,看下面的知识就知道,还有其他作用,例如用匹配器的找到的和找到内容方法
	
}
```

#14.10_常见对象(正则表达式的获取功能)
A:正则表达式的获取功能:

    Pattern和Matcher的结合使用:
    
B:案例演示:

    需求:把一个字符串中的手机号码获取出来
    
代码演示如下:
```
public static void main(String[] args) {

		String s = "我的手机是18511866260,我曾用过18987654321,还用过18812345678";
		String regex = "1[3578]\\d{9}";
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		
		/*
		boolean b1 = m.find();//true,找到
		System.out.println(b1);//
		System.out.println(m.group());//18511866260,找到的内容
		
		boolean b2 = m.find();
		System.out.println(b2);//
		System.out.println(m.group());//下面用循环来找到,并输出找到内容
        */
        
		while(m.find())//是否找到,并输出找到的内容:
			System.out.println(m.group());//18511866260换行输出18987654321换行输出18812345678
			
}
```
总结:匹配器有匹配方法matches,找到对应规则的找到方法find,输出对应规则内容方法group,但这些的前提是要得到匹配器,
匹配器可以通过Pattern类编译正则表达式成对象,对象调用匹配器方法传入字符串内容,得到匹配器对象即可

#14.11_常见对象(Math类概述和方法使用)
A:Math类概述:

    Math类包含用于执行基本数学运算的方法,如初等指数,对数,平方根和三角函数
    
B:成员方法:

	public static int abs(int a)//取绝对值
	
	public static double ceil(double a)//ceil天花板,返回大于等于传入参数的最小整数的double值
	public static double floor(double a)//floor地板,返回小于等于传入参数的最大整数的double值
	
	public static int max(int a,int b)//获取两个值中的最大值
	
	public static double pow(double a,double b)//a的b次方,double值
	public static double random()//生成0.0到1.0之间的随机小数,包括0.0,不包括1.0,double值
	
	public static int round(float a)//四舍五入
	public static double sqrt(double a)//开平方,double值
	
代码演示如下:
```
public static void main(String[] args) {
    System.out.println(Math.PI);//3.141592653589793
    System.out.println(Math.abs(-10));//10,取绝对值
    
    //ceil天花板,返回大于等于传入参数的最小整数的double值
    System.out.println(Math.ceil(12.3));//13.0,向上取整,但是结果是一个double
    System.out.println(Math.ceil(12.99));//13.0
    
    //floor地板,返回小于等于传入参数的最大整数的double值
    System.out.println(Math.floor(12.3));//12.0,向下取整,但是结果是一个double
    System.out.println(Math.floor(12.99));//12.0
    
    //获取两个值中的最大值
    System.out.println(Math.max(20, 30));//30
    
    //前面的数是底数,后面的数是指数
    System.out.println(Math.pow(2, 3));//8.0,2.0 ^ 3.0
    
    //生成0.0到1.0之间的所以小数,包括0.0,不包括1.0
    System.out.println(Math.random());//0.6992298719551379
    
    //四舍五入
    System.out.println(Math.round(12.3f));//12
    System.out.println(Math.round(12.9f));//13
    
    //开平方
    System.out.println(Math.sqrt(4));//2.0
    System.out.println(Math.sqrt(2));//1.4142135623730951
    System.out.println(Math.sqrt(3));//1.7320508075688772
		
}
```

#14.12_常见对象(Random类的概述和方法使用)
A:Random类的概述:

	此类用于产生随机数,如果用相同的种子,创建两个Random实例,
	则对每个实例进行相同的方法调用序列,它们将生成并返回相同的数字序列
	
B:构造方法:

	public Random()//无参构造
	public Random(long seed)//传入种子的有参构造
	
C:成员方法:

	public int nextInt()//返回int类型范围内的随机整数
	public int nextInt(int n)//生成在0到n范围内的随机数,包含0不包含n(要求重点掌握)
	
代码演示如下:
```
public static void main(String[] args) {
    Random r = new Random();
    int x = r.nextInt();//返回int类型范围内的随机整数
    
    //System.out.println(x);//第一次运行输出-2113930310,第二次运行输出764408995
    
    for(int i = 0; i < 10; i++) {
    //System.out.println(r.nextInt(0));//如果传入非正数,会报非法参数异常("n must be positive");
    //System.out.println(r.nextInt(100));//生成在0到n范围内的随机数,包含0不包含n,要求传入的参数是正数
    }
    
    //Random r2 = new Random(1001);
    //int a = r2.nextInt();//传入种子创建一个Random类对象,调用同一个方法两次,
    //int b = r2.nextInt();//生成的结果不一致,如下输出结果所示:
    //System.out.println(a);//第一次运行输出-1245131070,第二次运行输出-1245131070
    //System.out.println(b);//第一次运行输出-2078988849,第二次运行输出-2078988849
    
    Random r3 = new Random(1001);
    Random r4 = new Random(1001);
    int a = r3.nextInt();//相同的种子创建两个Random类实例,调用同一个方法,
    int b = r4.nextInt();//生成并返回相同的数字序列,如下输出结果所示:
    
    System.out.println(a);//第一次运行输出-1245131070,第二次运行输出-1245131070
    System.out.println(b);//第一次运行输出-1245131070,第二次运行输出-1245131070
		
}
```

#14.13_常见对象(System类的概述和方法使用)
A:System类的概述:

	System类包含一些有用的类字段和方法,它不能被实例化,因为私有了构造方法不让其他类创建本类对象,所以方法都是静态的,通过类名.方法名调用
	
B:成员方法:

    public static void gc()//
	public static void exit(int status)//
	public static long currentTimeMillis()//
	pubiic static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)// 
	
C:案例演示:
    System类的成员方法使用:
代码演示如下:
```
public static void main(String[] args) {
//		demo1();
//		demo2();
//		demo3();

//数组拷贝,从源数组的开始位置拷贝源数组长度个元素到目标数组,从0开始,目的数组多出来的元素为类型的默认值,这里是0
		int[] src = {11,22,33,44,55};//数组源
		int[] dest = new int[8];//新建int型目的数组,长度为8
		for (int i = 0; i < dest.length; i++) {
			System.out.println(dest[i]);//整型数组元素默认值为0,换行输出8个0
		}
		System.out.println("系统类方法进行数据拷贝");
System.arraycopy(src, 0, dest, 0, src.length);//从源数组的开始位置拷贝源数组长度个元素到目标数组,从0索引开始拷
		
		for (int i = 0; i < dest.length; i++) {
		System.out.println(dest[i]);//换行输出11 22 33 44 55 0 0 0//目的数组多出来的元素还是默认值0
		}
		
}

public static void demo3() {//系统当前时间毫秒值
	long start = System.currentTimeMillis();//1秒等于1000毫秒,输出1000个*调用前的时间
	for(int i = 0; i < 1000; i++) {
		System.out.println("*");//运行输出1000个*
	}
	long end = System.currentTimeMillis();//获取当前时间的毫秒值,输出1000个*调用前的时间
	
	System.out.println(end - start);//相减,就是输出1000个*调用花费的时间
}

public static void demo2() {
	System.exit(1);//非0状态是异常终止,退出jvm,注意传入0是正常退出虚拟机jvm,后面的代码都不会调用
	System.out.println("11111111111");//不会输出11111111111
}

public static void demo1() {
	for(int i = 0; i < 100; i++) {
    new Demo();//在1到100范围内不断创新匿名对象,匿名对象调用完毕就是垃圾,系统类调用静态gc方法等待回收,
		System.gc();//运行垃圾回收器,相当于呼喊保洁阿姨,
	}
}

class Demo {//在一个源文件中不允许定义两个用public修饰的类

	@Override
	public void finalize() {//调用gc等待回收方法之后会调用这个Object类的方法进行垃圾清理
		System.out.println("垃圾被清扫了");//输出100行垃圾被清扫了,表示正在清理垃圾
	}							
	
}
```

#14.14_常见对象(BigInteger类的概述和方法使用)
A:BigInteger的概述:

	可以让超过Integer范围内的数据进行运算
	
B:构造方法:

    public BigInteger(String val)//传入字符串的有参构造
    
C:成员方法:

    public BigInteger add(BigInteger val)//加
	public BigInteger subtract(BigInteger val)//减
	public BigInteger multiply(BigInteger val)//乘
	public BigInteger divide(BigInteger val)//除
	public BigInteger[] divideAndRemainder(BigInteger val)//取商和余数,返回一个数组,遍历数组元素得到
	
代码演示如下:
```
public static void main(String[] args) {
    //long num = 123456789098765432123L;//编译报错,超出long类型取值范围,那要想存这么大的数怎么办?
    String s = "123456789098765432123";//可以改成数字字符串接收,也可以用下面的BigInteger类↓
    
    BigInteger bi1 = new BigInteger("100");//有参构造方法传入字符串来接收
    BigInteger bi2 = new BigInteger("2");
    
    System.out.println(bi1.add(bi2));//102,加
    System.out.println(bi1.subtract(bi2));//98,减
    System.out.println(bi1.multiply(bi2));//200,乘
    System.out.println(bi1.divide(bi2));//50,除
    
    BigInteger[] arr = bi1.divideAndRemainder(bi2);//取商和余数,返回一个数组,遍历数组元素得到
    
    for (int i = 0; i < arr.length; i++) {
    	System.out.println(arr[i]);//第一个是商50,换行输出第二个是余数0
    }
		
}
```
总结:注意divideAndRemainder(bi2)方法是取商和余数,返回一个数组,遍历数组元素得到

#14.15_常见对象(BigDecimal类的概述和方法使用)
A:BigDecimal的概述:

	由于在运算的时候,float类型和double很容易丢失精度,演示案例
	所以,为了能精确的表示,计算浮点数,Java提供了BigDecimal类,
	它是不可变的,任意精度的有符号十进制数
	
B:构造方法:
	public BigDecimal(String val)//传入字符串的有参构造
	
C:成员方法:

	public BigDecimal add(BigDecimal augend)//加
	public BigDecimal subtract(BigDecimal subtrahend)//减
	public BigDecimal multiply(BigDecimal multiplicand)//乘
	public BigDecimal divide(BigDecimal divisor)//除
	public static BigDecimal valueOf(double val)//把传入的double值变成BigDecimal对象
	
D:案例演示:
	BigDecimal类的构造方法和成员方法使用:
代码演示如下:
```
public static void main(String[] args) {
    //System.out.println(2.0 - 1.1);//0.8999999999999999
    
    //BigDecimal bd1 = new BigDecimal(2.0);//通过构造中传入double值,这种方式在开发中不推荐,因为不够精确
    //BigDecimal bd2 = new BigDecimal(1.1);
    //System.out.println(bd1.subtract(bd2));//899999999999999911182158029987476766109466552734375
    
    BigDecimal bd1 = new BigDecimal("2.0");//通过构造中传入字符串的方式,开发时推荐,更加精确,符合实际值
    BigDecimal bd2 = new BigDecimal("1.1");
    System.out.println(bd1.subtract(bd2));//0.9
    
    BigDecimal bd3 = BigDecimal.valueOf(2.0);//调用BigDecimal类的静态方法valueOf开发中也是推荐的,
    BigDecimal bd4 = BigDecimal.valueOf(1.1);
    System.out.println(bd3.subtract(bd4));//0.9,,更加精确,符合实际值
		
}
```
总结:构造传入字符串或者用BigDecimal.valueOf()方法创建对象,再进行加减乘除操作精确度就是最高的,符合我们的需求

#14.16_常见对象(Date类的概述和方法使用)(掌握)
A:Date类的概述:

	类Date表示特定的瞬间,精确到毫秒,注意Date类是util包下的,不要导入sql包下的
	
B:构造方法:

	public Date()//无参构造创建Date类对象表示当前时间
	public Date(long date)//有参构造传入参数0创建Date类对象,代表的是1970年1月1日(你所在的时区)时0分0秒
	
C:成员方法:

	public long getTime()//得到时间毫秒值
	public void setTime(long time)//设置时间毫秒值,改变时间对象,相当于有参构造方法传入0加上你设置的时间
	
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    
    Date d1 = new Date();//如果没有传参数代表的是当前时间,
    d1.setTime(1000);//设置毫秒值,改变时间对象,输出的是从1970年1月1日(你所在的时区)时0分0秒加上你设置的时间
    System.out.println(d1);//Thu Jan 01 08:00:01 CST 1970,记得加上你所在的时区的小时数,如东八区的8
	
}

public static void demo2() {
    Date d1 = new Date();//如果没有传参数代表的是当前时间,
    System.out.println(d1.getTime());//通过时间对象获取毫秒值,所以这里也是当前时间毫秒值1463450987907
    System.out.println(System.currentTimeMillis());//通过系统类的方法获取当前时间毫秒值1463450987907
    
}

public static void demo1() {
	Date d1 = new Date();//如果没有传参数代表的是当前时间
	System.out.println(d1);//Tue May 17 10:05:28 CST 2016
	
	Date d2 = new Date(0);//如果构造方法中参数传为0代表的是1970年1月1日(你所在的时区)时0分0秒
	System.out.println(d2);//Thu Jan 01 08:00:00 CST 1970,这里的8代表你所在的时区,如我们的东八区
	
}
```
总结:注意Date类无参构造和有参的构造的区别,以及setTime方法传入参数能把无参构造变成有参构造的效果

#14.17_常见对象(SimpleDateFormat类实现日期和字符串的相互转换)(掌握)
A:DateFormat类的概述:

    DateFormat是日期/时间格式化子类的抽象类,它以与语言无关的方式格式化并解析日期或时间,是抽象类,
    所以使用其子类SimpleDateFormat
    
B:SimpleDateFormat构造方法:

	public SimpleDateFormat()//无参构造,默认格式16-5-17 上午10:56
	public SimpleDateFormat(String pattern)//传入指定格式如"yyyy/MM/dd HH:mm:ss",对应2016/05/17 10:56:51

C:成员方法,也就是Date与String的相互转换,有很多作用,如下面的计算你来到世界多少天的例子:↓

	public final String format(Date date)//简单日期格式化对象调用格式方法格式日期时间对象,返回字符串
	
	public Date parse(String source)//简单日期格式对象解析字符串得到日期时间对象,返回日期时间对象,但是,
	这里要注意,构造的简单日期格式对象的传入格式要与解析的字符串相同,否则会报无法解析的运行时异常,如↓
	
代码演示如下:
```
public static void main(String[] args) throws ParseException {
    //demo1();
    //demo2();
    //demo3();
    
    //将时间字符串转换成日期对象,即简单日期格式对象解析字符串得到日期时间对象
    String str = "2000年08月08日 08:08:08";//↓
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//这里格式要匹配上面的字符串↓
    Date d = sdf.parse(str);//将时间字符串转换成日期对象
    System.out.println(d);//Tue Aug 08 08:08:08 CST 2000
    
}

public static void demo3() {//简单日期格式化对象调用格式方法格式日期时间对象,格式为其有参构造传入格式
	Date d = new Date();//获取当前时间对象
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期格式化类对象
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//创建日期格式化类对象
	System.out.println(sdf.format(d));//2016/05/17 10:56:51,将日期对象转换为字符串
	System.out.println(sdf2.format(d));//2016年05月17日 11:10:26,将日期对象转换为字符串
	
}

public static void demo2() {//简单日期格式化对象调用格式方法格式日期时间对象
	Date d = new Date();//获取当前时间对象,
	SimpleDateFormat sdf = new SimpleDateFormat();//创建日期格式化类对象,
	System.out.println(sdf.format(d));//16-5-17 上午10:56
	
}

public static void demo1() {
//DateFormat df = new DateFormat();//DateFormat是抽象类,不允许实例化
	DateFormat df1 = new SimpleDateFormat();//父类引用指向子类对象
    DateFormat df2 = DateFormat.getDateInstance();//看源码知道,右边的方法返回一个子类对象,父类引用指向子类对象

}
```

#14.18_常见对象(你来到这个世界多少天案例)(掌握)
A:案例演示:

    需求:算一下你来到这个世界多少天?其实就是String字符串到Date时间的转换过程,对吧:
    
    总结:思路,利用简单日期格式对象解析生日字符串得到日期时间对象,然后getTime得到时间毫秒值,与当前时间毫秒值求差值,
    把毫秒值转换为天数即可
    
代码演示如下:
```
public static void main(String[] args) throws ParseException {
    //1,将生日字符串和今天字符串存在String类型的变量中
    String birthday = "1983年07月08日";
    String today = "2088年6月6日";//这里要注意,什么样的字符串,↓
    
    //2,定义日期格式化对象
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");//就要对应什么样格式的构造的对象↑
    //3,将日期字符串转换成日期对象
    Date d1 = sdf.parse(birthday);
    Date d2 = sdf.parse(today);
    
    //4,通过日期对象后期时间毫秒值
    long time = d2.getTime() - d1.getTime();
    //5,将两个时间毫秒值相减除以1000,再除以60,再除以60,再除以24得到天
    System.out.println(time / 1000 / 60 / 60 / 24+"天" );
		
}
```

#14.19_常见对象(Calendar类的概述和获取日期的方法)(掌握)
A:Calendar类的概述:

    Calendar类是一个抽象类,它为特定瞬间与一组诸如YEAR、MONTH、DAY_OF_MONTH、HOUR等日历字段之间的转换提供了一些方法,并为操作日历字段(例如获得下星期的日期)提供了一些方法
    
B:成员方法:

	public static Calendar getInstance()//返回的是Calendar类的子类,即父类引用指向子类对象接收
	
	public int get(int field)//得到Calendar类的字段如年月日的值,其中月份是从0开始的,星期日是第一天对应1

#14.20_常见对象(Calendar类的add()和set()方法)(掌握)
A:成员方法:

    public void add(int field,int amount)//增加Calendar类字段如月份减1,即用c.add(Calendar.MONTH, -1);
    
	public final void set(int year,int month,int date)//设置年月日的值,再次强调月份从0开始对应一月
	
	如:c.set(Calendar.YEAR, 2000);//设置年份为多少
	
B:案例演示:

    Calendar类的成员方法使用:
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    Calendar c = Calendar.getInstance();//父类引用指向子类对象
    //c.add(Calendar.MONTH, -1);//增加月份的值-1,则月份减去1,对指定的字段进行向前减或向后加,4-1=3;
    //c.set(Calendar.YEAR, 2000);//设置年为多少,修改指定字段,2000年05月17日星期三
    c.set(2000, 7, 8);//设置年月日,月份传入7,其实设置的是8月,因为月份是从0开始的,2000年08月08日星期二
    
    System.out.println(c.get(Calendar.YEAR) + "年" + getNum((c.get(Calendar.MONTH)+1))//3+1=4;
    + "月" + getNum(c.get(Calendar.DAY_OF_MONTH)) + "日" + getWeek(c.get(Calendar.DAY_OF_WEEK)));
				
}//输出2000年08月08日星期二

public static void demo1() {
    Calendar c = Calendar.getInstance();//右边返回的是子类对象,即父类引用指向子类对象
	//System.out.println(c);//输出一长串有关年月日等信息的字符串,说明重写了toString方法
	System.out.println(c.get(Calendar.YEAR));//2016,通过字段获取年
	System.out.println(c.get(Calendar.MONTH));//4,实际是5,通过字段获取月,但是月是从0开始编号的
	System.out.println(c.get(Calendar.DAY_OF_MONTH));//17,月中的第几天
	System.out.println(c.get(Calendar.DAY_OF_WEEK));//3,即星期二,因为周日是第一天,周六是最后一天
	
	System.out.println(c.get(Calendar.YEAR) + "年" + getNum((c.get(Calendar.MONTH)+1)) 
	+ "月" + getNum(c.get(Calendar.DAY_OF_MONTH)) + "日" + getWeek(c.get(Calendar.DAY_OF_WEEK)));
	
}//输出2016年05月17日星期二

public static String getWeek(int week) {
	String[] arr = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	//为了让输入3时,对应星期二,所以0位置加入空字符串
	return arr[week];
	
}

public static String getNum(int num) {
	/*if(num > 9) {
		return "" + num;
	}else {
		return "0" + num;
	}*/
	return num > 9 ? "" + num : "0" + num;//如果是个数数字前面补0
	
}
```
总结:Calendar类里月份是从0开始的对应一月,星期日是第一天对应1,即星期的第一天是星期日,星期六是最后一天

#14.21_常见对象(如何获取任意年份是平年还是闰年)(掌握)
A:案例演示:

    需求:键盘录入任意一个年份,判断该年是闰年还是平年
    
    总结:你要知道一个常识,就是2月有29天就是闰年,所以通过set方法设置录入的年和3月1日,将日向前减去1,判断是否是29天,
    是表示闰年,返回true
    
代码使用如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入年份,判断该年份是闰年还是平年:");
    //int year = sc.nextInt();//不用这个方法
    
    String line = sc.nextLine();//录入数字字符串
    int year = Integer.parseInt(line);//将数字字符串转换成数字年
    boolean b = getYear(year);
    System.out.println(b);
		
}

private static boolean getYear(int year) {
    //2,创建Calendar c = Calendar.getInstance();
    Calendar c = Calendar.getInstance();
    
    c.set(year, 2, 1);//1,设置为那一年的3月1日,这里传入2对应的是3月,因为月份从0开始对应的是一月
    
    c.add(Calendar.DAY_OF_MONTH, -1);//2,将日向前减去1,
    //判断是否是29天
    return c.get(Calendar.DAY_OF_MONTH) == 29;//3,判断是否是29天,是表示闰年,返回true
    
}
```

#14.22_day14总结
    把今天的知识点总结一遍:
    正则表达式-Math类-Random类-System类-BigInteger类-BigDecimal类-Date类-SimpleDateFormat类-Calendar类
    
