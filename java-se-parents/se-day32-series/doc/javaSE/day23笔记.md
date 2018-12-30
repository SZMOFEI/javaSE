#23.01_File类递归练习(统计该文件夹大小)
需求:1,从键盘接收一个文件夹路径,统计该文件夹大小:
代码演示如下:
```
public static void main(String[] args) {
    //File dir = new File("F:\\day06");
	//System.out.println(dir.length());//直接获取文件夹的结果是0
		
	File dir = getDir();
	System.out.println(getFileLength(dir);	
}
	
public static File getDir() {
	//1,创建键盘录入对象
	Scanner sc = new Scanner(System.in);
	System.out.println("请输入一个文件夹路径:");
	//2,定义一个无限循环
	while(true) {
		//3,将键盘录入的结果存储并封装成File对象
		String line = sc.nextLine();
		File dir = new File(line);
		//4,对File对象判断
		if(!dir.exists()) {
			System.out.println("您录入的文件夹路径不存在,请输入一个文件夹路径:");
		}else if(dir.isFile()) {
			System.out.println("您录入的是文件路径,请输入一个文件夹路径:");
		}else {
			//5,将文件夹路径对象返回
			return dir;
		}
	}	
}
	
public static long getFileLength(File dir) {
	//1,定义一个求和变量
	long len = 0;
	//2,获取该文件夹下所有的文件和文件夹listFiles();
	File[] subFiles = dir.listFiles();
	//3,遍历数组
	for (File subFile : subFiles) {
		//4,判断是文件就计算大小并累加
		if(subFile.isFile()) {
			len = len + subFile.length();
		//5,判断是文件夹,递归调用
		}else {
			len = len + getFileLength(subFile);
		}
	}
	return len;
}//这个方法如果不理解,可以定义一个静态变量来统计更容易理解
```

#23.02_File类递归练习(删除该文件夹)
需求:2,从键盘接收一个文件夹路径,删除该文件夹:
代码演示如下:
```
public static void main(String[] args) {
	File dir = Test1.getDir();//获取文件夹路径
	deleteFile(dir);
}

public static void deleteFile(File dir) {	
	//1,获取该文件夹下的所有的文件和文件夹
	File[] subFiles = dir.listFiles();		
	//2,遍历数组
	for (File subFile : subFiles) {
		//3,判断是文件直接删除
		if(subFile.isFile()) {
			subFile.delete();
		//4,如果是文件夹,递归调用
		}else {
			deleteFile(subFile);			
		}
	}
	//5,循环结束后,记得把空文件夹删掉,比上一个案例多了这一步,其他都类似
	dir.delete();//Java中的删除操作不走回收站
}
```

#23.03_File类递归练习(拷贝)
需求:3,从键盘接收两个文件夹路径,把其中一个文件夹中(包含内容)拷贝到另一个文件夹中:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	File src = Test1.getDir();
	File dest = Test1.getDir();
	if(src.equals(dest)) {
		System.out.println("目标文件夹是源文件夹的子文件夹");
	}else {
		copy(src,dest);
	}
}
	
public static void copy(File src, File dest) throws IOException {
	//1,在目标文件夹中创建原文件夹
	File newDir = new File(dest, src.getName());
	newDir.mkdir();
	//2,获取原文件夹中所有的文件和文件夹,存储在File数组中
	File[] subFiles = src.listFiles();
	//3,遍历数组
	for (File subFile : subFiles) {
		//4,如果是文件就用io流读写
		if(subFile.isFile()) {
		    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(subFile));//读取文件
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newDir,subFile.getName())));//写到文件
			//这里要分清楚,读和写操作的都是文件,而不是文件夹!!!
			int b;
			while((b = bis.read()) != -1) {//貌似读一个字节,实则一次读8192个字节到内置数组缓冲区
				bos.write(b);
			}
			
			bis.close();
			bos.close();
		//5,如果是文件夹就递归调用,
		}else {
			copy(subFile,newDir);//源和目的文件夹都相应发生改变
		}
	}
}
```
总结:读取文件内容,写入到对应的文件中或者文件路径中,不能单单写入到文件夹,文件夹下要有文件的名字的路径才行!!!

#23.04_File类递归练习(按层级打印)
    需求:4,从键盘接收一个文件夹路径,把文件夹中的所有文件以及文件夹的名字按层级打印,例如:
    	aaa是文件夹,里面有bbb.txt,ccc.txt,ddd.txt这些文件,有eee这样的文件夹,eee中有fff.txt和ggg.txt文件,
    	打印出层级来,如下所示:
    	aaa
    		bbb.txt
    		ccc.txt
    		ddd.txt
    	
    		eee
    			fff.txt
    			ggg.txt
代码演示如下:
```
public static void main(String[] args) {
		File dir = Test1.getDir();//获取文件夹路径
		printLev(dir,0);
}

public static void printLev(File dir,int lev) {
	//1,把文件夹中的所有文件以及文件夹的名字按层级打印
	File[] subFiles = dir.listFiles();
	//2,遍历数组
	for (File subFile : subFiles) {
		for(int i = 0; i <= lev; i++) {//在层级标记范围内的都输出tab键,这个不要忘!!!
			System.out.print("\t");//这里不换行,注意!!!
		}
		
		//3,无论是文件还是文件夹,都需要直接打印名字
		System.out.println(subFile);
		
		//4,如果是文件夹,递归调用
		if(subFile.isDirectory()) {
			//printLev(subFile,lev + 1);
			printLev(subFile,++lev);
		}
	}
}
```

#23.05_递归练习(斐波那契数列)
    不死神兔:
    故事得从西元1202年说起,话说有一位意大利青年,名叫斐波那契,
    在他的一部著作中提出了一个有趣的问题:假设一对刚出生的小兔一个月后就能长成大兔,再过一个月就能生下一对小兔,
    并且此后每个月都生一对小兔,一年内没有发生死亡,
    问:一对刚出生的兔子,一年内繁殖成多少对兔子?
    1 1 2 3 5 8 13
    第一个月一对小兔子					1
    第二个月一对大兔子					1
    第三个月一对大兔子生了一对小兔子	2
    第四个月一对大兔子生了一对小兔子
    		一对小兔子长成大兔子		3
    第五个月两对大兔子生两对小兔子	
     		 一对小兔子长成大兔子		5
代码演示如下:
```
public static void main(String[] args) {
		//demo1();
		System.out.println(fun(8));
}

public static void demo1() {
	//用数组做不死神兔
	int[] arr = new int[8];
	//数组中第一个元素和第二个元素都为1
	arr[0] = 1;
	arr[1] = 1;
	//遍历数组对其他元素赋值
	for(int i = 2; i < arr.length; i++) {
		arr[i] = arr[i - 2] + arr[i - 1];
	}
	//如何获取最后一个数
	System.out.println(arr[arr.length - 1]);
}

	
//用递归求斐波那契数列
public static int fun(int num) {
	if(num == 1 || num == 2) {
		return 1;
	}else {
		return fun(num - 2) + fun(num - 1);
	}
}
```

#23.06_递归练习(1000的阶乘所有零和尾部零的个数)
需求:求出1000的阶乘所有零和尾部零的个数,不用递归做:
代码演示如下:
```
public static void main(String[] args) {
    //int result = 1;
    //for(int i = 1; i <= 1000; i++) {
        //result = result * i;
    //}
	
    //System.out.println(result);//0,因为1000的阶乘远远超出了int的取值范围
    
    //demo1();//求1000的阶乘中所有的零,不用递归做
    demo2();//获取1000的阶乘尾部有多少个零,不用递归做
}

public static void demo2() {//获取1000的阶乘尾部有多少个零,不用递归做:
    BigInteger bi1 = new BigInteger("1");
	for(int i = 1; i <= 1000; i++) {
		BigInteger bi2 = new BigInteger(i+"");
		bi1 = bi1.multiply(bi2);//将bi1与bi2相乘的结果赋值给bi1
	}
	String str = bi1.toString();//获取字符串表现形式
	StringBuilder sb = new StringBuilder(str);
	str = sb.reverse().toString();//反转,防止前面的0影响,链式编程
	
	int count = 0;//定义计数器
	for(int i = 0; i < str.length(); i++) {
		if('0' != str.charAt(i)) {//反转遇到第一个不是0的字符就跳出循环,
			break;
		}else {
			count++;//否则统计字节个数
		}
	}
	
	System.out.println(count);//249
}

public static void demo1() {//求1000的阶乘中所有的零,不用递归做:
	BigInteger bi1 = new BigInteger("1");
	for(int i = 1; i <= 1000; i++) {
		BigInteger bi2 = new BigInteger(i+"");
		bi1 = bi1.multiply(bi2);//将bi1与bi2相乘的结果赋值给bi1
	}
	String str = bi1.toString();//获取字符串表现形式
	int count = 0;
	for(int i = 0; i < str.length(); i++) {
		if('0' == str.charAt(i)) {//如果字符串中出现了0字符,
			count++;//计数器加1,统计字符个数
		}
	}
	System.out.println(count);//472
}
```
总结:不用递归做,用BigInteger接收做出乘积表达式转换成字符串,从而对字符串遍历得到每一个字符判断是字符0就统计,
或者对字符串存储到字符缓冲区中反转之后转成字符串,在遍历字符判断不是字符0就跳出,是就统计

#23.07_递归练习(1000的阶乘尾部零的个数)
需求:求出1000的阶乘尾部零的个数,用递归做:
代码演示如下:
```
public static void main(String[] args) {
    System.out.println(fun(1000));//249
}

public static int fun(int num) {
	if(num > 0 && num < 5) {//利用5的阶乘是120,末尾有一个0,而小于5的阶乘末尾没有0,
		return 0;//,所以返回0表示末尾没有0
	}else {
		return num / 5 + fun(num / 5);//有多少个5的阶乘末尾就有多少个0,再加上递归调用的个数
	}
}
```

#23.08_集合练习(约瑟夫环)
即获取幸运数字:
代码演示如下:
```
public static void main(String[] args) {
		System.out.println(getLucklyNum(8));//7
}

//获取幸运数字:
public static int getLucklyNum(int num) {
	ArrayList<Integer> list = new ArrayList<>();//创建集合存储1到num的对象,
	for(int i = 1; i <= num; i++) {
		list.add(i);//将1到num存储在集合中
	}
	
	int count = 1;//起始数为1,用来数数的,只要是3的倍数就杀人,
	for(int i = 0; list.size() != 1; i++) {//只要集合中人数不是1,就要不断的杀,
		if(i == list.size()) {//如果i增长到集合最大的索引+1时,
			i = 0;//就要重新归零,
		}
		
		if(count % 3 == 0) {//如果是3的倍数,
			list.remove(i--);//就杀人,集合删除元素索引要减减
		}
		count++;
	}
	
	return list.get(0);//删除其他元素后,只剩下一个元素,即集合的第一个元素返回作为幸运数字
}
```

#23.09_day23总结
    把今天的知识点总结一遍:
    列出File对象下的数组,遍历数组元素,判断如果是文件做操作如删除复制等,如果是文件夹递归调用方法
    
