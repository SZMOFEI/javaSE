#20.01_IO流(IO流概述及其分类)
1.概念:

    IO流用来处理设备之间的数据传输
	Java对数据的操作是通过流的方式
	Java用于操作流的类都是在IO包中
	
	流按流向分为两种:输入流,输出流
	流按操作类型分为两种:
	    字节流:字节流可以操作任何数据,因为在计算机中任何数据都是以字节的形式存储的
		字符流:字符流只能操作纯字符数据,比较方便
		
2.IO流常用父类(在文档说明书中查找演示)

	字节流的抽象父类:
	    InputStream
		OutputStream
		
	字符流的抽象父类:
		Reader
		Writer	
		
3.IO程序书写:

	使用前,导入IO包中的类
	使用时,进行IO异常处理
	使用后,释放资源

#20.02_IO流(FileInputStream)
###read()//一次读取一个字节:
抽象类InputStream是没用的,用其子类FileInputStream实现功能:
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
	
	FileInputStream fis = new FileInputStream("xxx.txt");//创建流对象并关联文件,内容是abc
	int b;
	while((b = fis.read()) != -1) {//一次读取一个字节赋值给b,当b不等于-1,即没到达文件末尾,输出b
		System.out.print(b);//979899,不换行输出
	}
	
	fis.close();//记得关闭流资源
}

public static void demo1() throws FileNotFoundException, IOException {
	FileInputStream fis = new FileInputStream("xxx.txt");//创建流对象并关联文件,内容是abc
	int x = fis.read();//从硬盘上读取一个字节
	System.out.println(x);//97,对应文件内容的a
	int y = fis.read();
	System.out.println(y);//98
	int z = fis.read();
	System.out.println(z);//99
	int a = fis.read();
	System.out.println(a);//-1,表示到达文件的末位,结束标记
	int b = fis.read();
	System.out.println(b);//-1,表示到达文件的末位,结束标记
	fis.close();//关流,释放资源
}
```
总结:演示的时候一个个输出,看看输出的都是什么,这样容易理解,那read()方法返回值为什么是int?

#20.03_IO流(read()方法返回值为什么是int)
read()方法读取的是一个字节,为什么返回值是int,而不是byte?
原因如下:

    因为字节输入流可以操作任意类型的文件,比如图片音频等,这些文件底层都是以二进制形式的存储的,
	如果每次读取都返回byte,有可能在读到中间的时候遇到11111111,通过-1原码是10000001,
	那么求出11111111是byte类型的-1的补码,我们的程序是遇到-1就会停止不读了,后面的数据就读不到了,
	所以在读取的时候用int类型接收,如果遇到11111111会在其前面补上24个0凑足4个字节,那么byte类型的-1,
	就变成int类型的255了,这样可以保证整个数据读完,而结束标记的-1就是int类型,不用补上24个0,所以返回int

#20.04_IO流(FileOutputStream)
###write(int b)//一次写出一个字节:
    FileOutputStream在创建对象的时候是如果没有这个文件会帮我创建出来,如果有这个文件就会先将文件内容清空:
代码演示如下:
```
public static void main(String[] args) {
    FileOutputStream fos = new FileOutputStream("bbb.txt");	//如果没有bbb.txt,会创建出一个
    //fos.write(97);//虽然写出的是一个int数,但是在写出的时候会将前面的24个0去掉,所以写出的是一个byte
    fos.write(98);
    fos.write(99);
    fos.close();
}
```

#20.05_IO流(FileOutputStream追加)
A:案例演示:
FileOutputStream的构造方法写出数据如何实现数据的追加内容:
代码演示如下:
```
public static void main(String[] args) {
    FileOutputStream fos = new FileOutputStream("bbb.txt",true);//如果没有bbb.txt,会创建出一个
    fos.write(97);//虽然写出的是一个int数,但是在写出的时候会将前面的24个0去掉,所以写出的是一个byte
    fos.write(98);//a后面追加b,
    fos.write(99);//b后面追加c,
    fos.close();//所以打开bbb.txt,里面内容是abc
}
```
总结:多次运行程序,就可以比较不用追加构造和用追加构造的内容追加区别

#20.06_IO流(拷贝图片)
FileInputStream读取
FileOutputStream写出
代码演示如下:
```
public static void main(String[] args) {
    FileInputStream fis = new FileInputStream("a.jpg");//创建输入流对象,关联图片a.jpg
    FileOutputStream fos = new FileOutputStream("copy.jpg");//创建输出流对象,关联图片copy.jpg
    
    int b;
    while((b = fis.read()) != -1) {//一次读取一个字节,对于大容量的文件,效率较低,所以不推荐
    	fos.write(b);
    }
    
    fis.close();//谁读写流,就关闭谁
    fos.close();		
}
```

#20.07_IO流(拷贝音频文件画原理图)
A:案例演示:

    字节流一次读写一个字节复制音频
    
弊端:效率太低

###20.08_IO流(字节数组拷贝之available()方法)
A:案例演示:

    int read(byte[] b):一次读取一个字节数组
	write(byte[] b):一次写出一个字节数组
	
	available()//可获取的,即获取要读取文件的所有字节个数
    弊端:有可能会内存溢出,一次性拷贝,假如你电脑内存是2G,你一次读取的是10G,那么就可能发生内存溢出,所以不推荐:
代码演示如下:
```
public static void main(String[] args) {
	FileInputStream fis = new FileInputStream("致青春.mp3");
	FileOutputStream fos = new FileOutputStream("copy.mp3");
	byte[] arr = new byte[fis.available()];//根据文件大小做一个字节数组
	fis.read(arr);//将文件上的所有字节读取到数组中
	fos.write(arr);//将数组中的所有字节一次写到了文件上
	fis.close();
	fos.close();
}
```

#20.09_IO流(定义小数组)
write(byte[] b)//
###write(byte[] b, int off, int len)//写出有效的字节个数
代码演示如下:
```
public static void demo2() throws FileNotFoundException, IOException {
	FileInputStream fis = new FileInputStream("xxx.txt");//文件内容是abc
	FileOutputStream fos = new FileOutputStream("yyy.txt");
    //FileOutputStream fos = new FileOutputStream("yyy.txt",true);
	
	byte[] arr = new byte[2];
	int len;
	while((len = fis.read(arr)) != -1) {//注意不要和追加搞混淆了,不追加时运行程序一次清空一次
		//fos.write(arr);//yyy.txt打开内容是:abcb,怎么办?用下面的方法来写
		fos.write(arr,0,len);//写入数组的一部分,从0索引开始,写入读取的有效字节个数个元素
	}
	
    //for (byte b : arr) {
        //System.out.println(b);//99换行98,为什么呢?98是第一次读取到数组的第二个元素
    //}
	
	fis.close();
	fos.close();
}

public static void demo1() throws FileNotFoundException, IOException {
	FileInputStream fis = new FileInputStream("xxx.txt");//文件内容是abc
	byte[] arr = new byte[2];
	int a = fis.read(arr);//读取有效个字节,到字节数组中,返回的是读到的有效字节个数
	
	System.out.println(a);//2,读到的有效字节个数
	for (byte b : arr) {//第一次获取到文件上的a和b
		System.out.println(b);//97换行98
	}
	System.out.println("-----------------------");
	int c = fis.read(arr);
	System.out.println(c);//1,读到的有效字节个数
	for (byte b : arr) {
		System.out.println(b);//99换行98,为什么呢?98是第一次读取到数组的第二个元素
	}
	fis.close();
}
```	
		
#20.10_IO流(定义小数组的标准格式)
A:案例演示:

    字节流一次读写一个字节数组复制图片和视频:
代码演示如下:
```
public static void main(String[] args) {
	FileInputStream fis = new FileInputStream("致青春.mp3");
	FileOutputStream fos = new FileOutputStream("copy.mp3");
	int len;
	byte[] arr = new byte[1024 * 8];//自定义字节数组,8192
	
	while((len = fis.read(arr)) != -1){//读取有效个字节,到字节数组中,返回的是读到的有效字节个数len
		//fos.write(arr);//不行,会把第一次存储的数组第二个元素等写入到文件,例如数组长度是2时
		fos.write(arr, 0, len);//写出字节数组,写出有效个字节个数
	}
	
	fis.close();
	fos.close();
}
```
总结:两个地方不要写错(len = fis.read(arr)) != -1和fos.write(arr, 0, len);千万不能写成下面那样,否则问题多多:
(len = fis.read(  )) != -1和fos.write(arr);

#20.11_IO流(BufferedInputStream和BufferOutputStream拷贝)
A:缓冲思想:见截图

    字节流一次读写一个数组的速度明显比一次读写一个字节的速度快很多,
	这是加入了数组这样的缓冲区效果,java本身在设计的时候,
	也考虑到了这样的设计思想(装饰设计模式后面讲解),所以提供了字节缓冲区流
	
B.BufferedInputStream:

	BufferedInputStream内置了一个缓冲区(数组)
	从BufferedInputStream中读取一个字节时,注意,
	BufferedInputStream会一次性从文件中读取8192个字节,存在缓冲区中,返回给程序一个,
	从缓冲区到返回给程序在内存所以很快,
	程序再次读取时,就不用找文件了,直接从缓冲区中获取,
	直到缓冲区中所有的都被使用过,才重新从文件中读取8192个字节
	
C.BufferedOutputStream:

	BufferedOutputStream也内置了一个缓冲区(数组)
	程序向流中写出字节时,不会直接写到文件,先写到缓冲区中,
	直到缓冲区写满,BufferedOutputStream才会把缓冲区中的数据一次性写到文件里
	
D.拷贝的代码:
```
public static void main(String[] args) {
	FileInputStream fis = new FileInputStream("致青春.mp3");//创建文件输入流对象,关联致青春.mp3
	BufferedInputStream bis = new BufferedInputStream(fis);//创建缓冲区对fis装饰
	FileOutputStream fos = new FileOutputStream("copy.mp3");//创建输出流对象,关联copy.mp3
	BufferedOutputStream bos = new BufferedOutputStream(fos);//创建缓冲区对fos装饰
	
	int b;
    while((b = bis.read()) != -1) {//读取一个字节,会一次性从文件中读取8192个,存在缓冲区中,返回给程序一个,
		bos.write(b);//但从缓冲区到返回给程序,因为在内存中,所以操作很快,效率也高
	}
	
	bis.close();//只关闭,装饰后的对象即可,
	bos.close();//或者说谁调用了读和写方法,就关闭谁
}
```

E.小数组的读写和带Buffered的读取哪个更快?

    定义小数组如果是8192个字节大小和Buffered比较的话,
	定义小数组会略胜一筹,因为读和写操作的是同一个数组,
	而Buffered操作的是两个数组

#20.12_IO流(flush和close方法的区别)
flush()方法:

	用来刷新缓冲区的,刷新后可以再次写出
	
close()方法:

	用来关闭流释放资源的的,如果是带缓冲区的流对象的close()方法,不但会关闭流,还会在关闭流之前刷新缓冲区,
	关闭后不能再写出
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //flush和close方法的区别:
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("xxx.txt"));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("yyy.txt"));
    
    int b;
    while((b = bis.read()) != -1) {
    bos.write(b);//直到缓冲区写满,BufferedOutputStream才会把缓冲区中的数据一次性写到文件里
    //bos.close();//close方法刷完之后就不能写了的演示
    }
    //flush方法具备刷新的功能,刷完之后还可以继续写
    //bos.flush();//如果不刷新缓冲区,写入到文件中的字节会减少,因为有些字节还在缓冲区中
    
    bis.close();//具备刷新的功能,在关闭流之前,就会先刷新一次缓冲区,
    bos.close();//将缓冲区的字节全都刷新到文件上,再关闭,close方法刷完之后就不能写了
}
```

#20.13_IO流(字节流读写中文) 
字节流读取中文的问题:

    字节流在读中文的时候有可能会读到半个中文,造成乱码
    
字节流写出中文的问题:

    字节流直接操作的字节,所以写出中文必须将字符串转换成字节数组
	如写出回车换行 write("\r\n".getBytes());//从左往右即捺是正斜杠\
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
    
    //字节流直接操作的字节,所以写出中文必须将字符串转换成字节数组 ,
    //写出回车换行 write("\r\n".getBytes());
    FileOutputStream fos = new FileOutputStream("zzz.txt");
    fos.write("我读书少,你不要骗我".getBytes());//转成字节数组
    fos.write("\r\n".getBytes());//回车换行
    fos.close();
}

public static void demo1() throws FileNotFoundException, IOException {
	FileInputStream fis = new FileInputStream("yyy.txt");//里面内容是你好你好你好
	FileOutputStream fos = new FileOutputStream("hhh.txt");
	byte[] arr = new byte[3];
    //byte[] arr = new byte[4];
	int len;
	//字节流在读中文的时候有可能会读到半个中文,造成乱码 
	while((len = fis.read(arr)) != -1) {
        //System.out.println(len);//3,读取的有效字节个数
		System.out.print(new String(arr,0,len));//输出到控制台即乱码:你?媚?好?愫?
		//fos.write(arr,0,len);//打开hhh.txt文件里面内容还是你好你好你好,不会乱码,这个要注意
	}
	
	fis.close();
}
```
总结读取半个中文输出到控制台会出现乱码,但是写入到文件中打开就没有问题,换言之,字节流拷贝是通用的!!!

#20.14_IO流(流的标准处理异常代码1.6版本及其以前)
try finally嵌套:
代码演示如下:
```
public static void demo1() throws FileNotFoundException, IOException {
    FileInputStream fis = null;
	FileOutputStream fos = null;
	try {
		fis = new FileInputStream("xxx.txt");
		fos = new FileOutputStream("yyy.txt");
		
		int b;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
	}finally {
		try{
			if(fis != null)
			fis.close();
		}finally {//try fianlly的嵌套目的是能关一个尽量关一个
			if(fos != null)
			fos.close();
		}
	}
}
```

#20.15_IO流(流的标准处理异常代码1.7版本)//写法跟匿名内部类的格式有些相似记忆即可
JDK1.7InputStream和OutputStream实现了AutoCloseable自动关闭接口,会在读写代码执行完之后调用close方法关闭流:
```
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
```
原理:

    在try()中创建的流对象必须实现了AutoCloseable这个接口,如果实现了,
    在try后面的{}(读写代码)执行后就会自动调用流对象的close方法,将流关掉:
代码演示如下:
```
public static void main(String[] args) throws IOException {
    try(
		FileInputStream fis = new FileInputStream("xxx.txt");//实现了AutoCloseable接口
		FileOutputStream fos = new FileOutputStream("yyy.txt");//实现了AutoCloseable接口
	    MyClose mc = new MyClose();//自定义类也要实现AutoCloseable接口才能调用关闭方法自动关闭
	){
    //下面是执行代码,执行完之后,上面的实现了AutoCloseable接口的类对象就会调用关闭方法关闭流等
		int b;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
        System.out.println("执行代码,执行完之后,上面的实现了AutoCloseable接口的类对象就会调用关闭方法关闭流等");
	}
}

class MyClose implements AutoCloseable {
    public void close() {
    	System.out.println("我关了");//读写代码执行完后调用这个方法
    }
}
```
总结:JDK1.7InputStream和OutputStream实现了AutoCloseable自动关闭接口,会在读写代码执行完之后调用close方法关闭流,
这个面试会考哦

#20.16_IO流(图片加密)
给图片加密:
代码演示如下:
```
//加密
public static void main(String[] args) throws IOException {
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream("copy.jpg"));
	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy2.jpg"));
	
	int b;
	while((b = bis.read()) != -1) {//注意,这里要一个一个字节读取,因为要一个一个字节亦或后写出
		//将写出的字节异或上一个数,这个数就是密钥,解密的时候再次异或就可以了
		bos.write(b ^ 123);//System.out.println((((byte)(b ^ 1024))==(byte)b));//true
		//注意这里不能亦或上1024的倍数,看write的源码可以知道,会把传入的int数强壮为byte,
		//我们测试发现只要一个数亦或上一个1024的整数倍数,强转为byte跟这个数自己强转为byte的结果一样
	}
	
	bis.close();
	bos.close();
}
//解密的时候,把加密后的文件改为源文件,再创建一个目的文件,再次调用上面的代码逻辑,即再异或一次就解密了:
```
总结:其实考的是文件的拷贝和一个数对另一个数异或两次还是其本身,来达到加密和解密的效果

#20.17_IO流(拷贝文件)
在控制台录入文件的路径,将文件拷贝到当前项目下:
代码演示如下:
```
public static void main(String[] args) throws IOException {
    File file = getFile();//方法获取的是文件,所以file.getName()是文件的名字
    BufferedInputStream  bis = new BufferedInputStream(new FileInputStream(file));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));
    
    int b;
    while((b = bis.read()) != -1) {
    	bos.write(b);
    }
    
    bis.close();
    bos.close();
}

public static File getFile() {//得到文件的方法,路径存在且不是文件夹,那么就是文件
	Scanner sc = new Scanner(System.in);//创建键盘录入对象
	System.out.println("请输入一个文件的路径:");
	while(true) {
		String line = sc.nextLine();//接收键盘录入的路径
		File file = new File(line);//封装成File对象,并对其进行判断
		if(!file.exists()) {
			System.out.println("您录入的文件路径不存在,请重新录入:");
		}else if(file.isDirectory()) {
			System.out.println("您录入的是文件夹路径,请重新录入:");
		}else {
			return file;//路径存在且不是文件夹,那么就是文件咯
		}
	}
}
```

#20.18_IO流(录入数据拷贝到文件)
将键盘录入的数据拷贝到当前项目下的text.txt文件中,键盘录入数据当遇到quit时就退出:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	//1,创建键盘录入对象
	Scanner sc = new Scanner(System.in);
	//2,创建输出流对象,关联text.txt文件
	FileOutputStream fos = new FileOutputStream("text.txt");
	System.out.println("请输入数据:");
	//3,定义无限循环
	while(true) {
		String line = sc.nextLine();//将键盘录入的数据存储在line中
		//4,遇到quit退出循环
		if("quit".equals(line)) {
			break;
		}
		//5,如果不quit,就将内容写出
		fos.write(line.getBytes());//字符串写出必须转换成字节数组
		fos.write("\r\n".getBytes());
	}
	//6,关闭流
	fos.close();
}
```

#20.19_day20总结
    把今天的知识点总结一遍:
    字节流

