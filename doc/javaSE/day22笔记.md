#22.01_IO流(序列流)(了解)
    序列流,也叫做合并流,作用就是将多个流合并成一个流,通过这一个流,
    对多条路径的数据进行读取,有两种常用的实现方式:
    
1.什么是序列流:

    序列流可以把多个字节输入流整合成一个,从序列流中读取数据时,将从被整合的第一个流开始读,
	读完一个之后继读第二个,依此类推
	
2.使用方式:

	整合两个:SequenceInputStream(InputStream, InputStream):
代码演示如下:
```
public static void main(String[] args) {
    FileInputStream fis1 = new FileInputStream("a.txt");//创建输入流对象,关联a.txt,内容是大家好
	FileInputStream fis2 = new FileInputStream("b.txt");//创建输入流对象,关联b.txt,内容是才是好
	
	SequenceInputStream sis = new SequenceInputStream(fis1, fis2);//将两个流整合成一个流
	
	FileOutputStream fos = new FileOutputStream("c.txt");//创建输出流对象,关联c.txt
	
	int b;
	while((b = sis.read()) != -1) {//用整合后的读
		fos.write(b);//最后写到c.txt文件中的内容是大家好才是好
	}
	
	sis.close();//最后记得关闭流,整合字节输入流也要关,一般的,谁读写,谁关闭
	fos.close();//谁调用读写方法,谁关闭
}
```
总结:还可以这样整合SequenceInputStream sis = new SequenceInputStream(sis, fis3);间接实现整合多个,下面直接实现:

#22.02_IO流(序列流整合多个)(了解)
    整合多个:SequenceInputStream(Enumeration):
代码演示如下:
```
public static void main(String[] args) {
	FileInputStream fis1 = new FileInputStream("a.txt");//创建输入流对象,关联a.txt,内容是我
	FileInputStream fis2 = new FileInputStream("b.txt");//创建输入流对象,关联b.txt,内容是爱
	FileInputStream fis3 = new FileInputStream("c.txt");//创建输入流对象,关联c.txt,内容是你
	
	Vector<InputStream> v = new Vector<>();//创建vector集合对象,
	v.add(fis1);//将流对象添加,
	v.add(fis2);
	v.add(fis3);
	Enumeration<InputStream> en = v.elements();//通过集合的元素们方法,获取枚举引用,
	
	SequenceInputStream sis = new SequenceInputStream(en);//传递给SequenceInputStream构造
	
	FileOutputStream fos = new FileOutputStream("d.txt");
	int b;
	while((b = sis.read()) != -1) {
		fos.write(b);//最后写出到d.txt文件中的内容是我爱你
	}

	sis.close();//谁读写,谁关闭的原则,但是有一个例外就是内存输出流写不用关闭,看下面知识就知道了:
	fos.close();//
}
```
总结:个人觉得应该翻译为整合字节输入流,可以整合两个或者多个字节输入流为一个输入流对象,这里学习的是一个输入流,
下面学习的是一个输出流,即内存输出流,写出数据到内存,把内存当作一个缓冲区,写出之后可以一次性获取出所有数据

#22.03_IO流(内存输出流)(掌握)
1.什么是内存输出流:

    该输出流可以向内存中写数据,把内存当作一个缓冲区,写出之后可以一次性获取出所有数据:
    
2.使用方式:

	创建对象:new ByteArrayOutputStream()
	写出数据:write(int), write(byte[])
	获取数据:toByteArray()
	
代码演示如下:
```
public static void main(String[] args) {
	FileInputStream fis = new FileInputStream("a.txt");//文件内容你好你好
	ByteArrayOutputStream baos = new ByteArrayOutputStream();//输出到内存,不用传入文件名
	int b;
	while((b = fis.read()) != -1) {
		baos.write(b);
	}
	
	//获取内存中的数据,即将内存中的所有字节数据转化成字节数组:↓
	//byte[] newArr = baos.toByteArray();//将内存缓冲区中所有的字节存储在字节数组newArr中
	//System.out.println(new String(newArr));//你好你好,输出字节数组包装为字符串显示
	System.out.println(baos);//你好你好,说明重写了toString方法
	fis.close();//最后记得关闭输入流,内存输出流不用关闭,这个要注意!
}
```
    总结:个人认为内存输出流ByteArrayOutputStream应该翻译为字节数组字节输出流,写出数据到内存,而内存中存储就是字节,
    可以通过字节数组字节输出流对象的转成字节数组方法得到写出到内存缓冲区中的数据,由于向内存写数据,不用关闭流,
    另外除了用字符流解决FileInputStream读取中文的时候出现乱码问题,也可把FileOutputStream换成ByteArrayOutputStream来解决:
代码演示如下:
```
public static void main(String[] args) {
    FileInputStream fis = new FileInputStream("e.txt");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存中创建了可以增长的内存数组缓冲区,
    
    int b;
    while((b = fis.read()) != -1) {
    	baos.write(b);//将读取到的数据逐个写到内存数组缓冲区中,
    }
    
    byte[] arr = baos.toByteArray();//内存将缓冲区的数据全部获取出来,并赋值给arr数组,
    System.out.println(new String(arr));//你好你好,解决中文乱码
    
    System.out.println(baos.toString());//你好你好,解决中文乱码,将缓冲区的内容转换为了字符串,可省略toString方法
    fis.close();
}
```

#22.04_IO流(内存输出流之黑马面试题)(掌握)
定义文件输入流,调用read(byte[] b)方法,将a.txt文件中的内容打印出来(byte数组大小限制为5),要求不出现乱码问题:
代码演示如下:
```
public static void main(String[] args) {
	FileInputStream fis = new FileInputStream("a.txt");//创建字节输入流,关联a.txt,内容大家好,
	ByteArrayOutputStream baos = new ByteArrayOutputStream();//创建内存输出流
	byte[] arr = new byte[5];//创建字节数组,大小为5
	int len;
	while((len = fis.read(arr)) != -1) {//将文件上的数据读到字节数组中
		baos.write(arr, 0, len);//将字节数组的数据写到内存缓冲区中
        //System.out.print(new String(arr,0,len));//大家??,直接显示乱码
	}
	
	System.out.println(baos);//大家好,,将内存缓冲区的内容转换为字符串打印,不会乱码
	fis.close();
}
```
总结:解决乱码的另一种方式是把数据写入到内存,然后再从内存中取出来,上面分别学习了一个字节输入流,一个字节输出流,
下面我们学习一个RandomAccessFile类不属于流,但是它却同时拥有字节输入流和字节输出流的功能,一个类两个流对象功能:

###22.05_IO流(随机访问流概述和读写数据)(了解)
A:随机访问流概述:

    RandomAccessFile概述:
	RandomAccessFile类不属于流,是Object类的子类,但它却融合了InputStream和OutputStream的功能,
	支持对随机访问文件的读取和写入

B:read(),write(),seek()//seek在指定位置设置指针,例如想在索引为10的位置写入值,可以先seek(10)再写入值:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	RandomAccessFile raf = new RandomAccessFile("g.txt", "rw");
	raf.write(97);//往g.txt文件中写入a,在当前指针位置写
	
	int x = raf.read();//返回数据的下一个字节,如果到达文件结尾返回-1
	System.out.println(x);//第一次运行时-1,因为没有下一个字节,第二次运行是98,即97的下一个字节
	
	raf.seek(1);//在指定索引位置设置指针,
	raf.write(98);//往设置的位置写入数据b
	raf.close();//谁读写,谁关闭
}
```
总结:学习完了一个随机访问文件类不属于流却同时含有字节输入流和字节输出流的功能,
下面我们再学习对对象进行操作的对象字节输出流和对象字节输入流:
	
#22.06_IO流(对象操作流ObjecOutputStream)(了解)
1.什么是对象操作流:

    该流可以将一个对象写出,或者读取一个对象到程序中,也就是执行了序列化和反序列化的操作:
    
2.使用方式:

	写出:new ObjectOutputStream(OutputStream),writeObject()
代码演示如下:
```
public class Demo3_ObjectOutputStream {
	//将对象写出,即序列化
	public static void main(String[] args) throws IOException {
		Person p1 = new Person("张三", 23);
		Person p2 = new Person("李四", 24);

		//无论是字节输出流,还是字符输出流都不能直接写出对象到文件:
        //FileOutputStream fos = new FileOutputStream("e.txt");
        //fos.write(p1);
        //FileWriter fw = new FileWriter("e.txt");
        //fw.write(p1);

		//对象字节输出流才能直接写出对象到文件,打开文件是序列化的看不太懂的内容,怎么办?
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e.txt"));
		oos.writeObject(p1);//特有的写出对象方法,传入对象,写出到文件中
		oos.writeObject(p2);
		oos.close();
	}
}

//注意:要写出的对象必须实现Serializable接口才能被序列化,否则报错,所以另一个源文件中Person类的写法是如下:↓
public class Person implements Serializable {
	private static final long serialVersionUID = 2L;//ctr+1生成,用以解决出现的黄色警告线问题,但不写运行也行!

	private String name;
	private int age;
	//Alt+shift+S分别按c,o,r,s生成无参构造,有参构造,setter和getter方法,toString方法,不写出来节省空间...
}
```

#22.07_IO流(对象操作流ObjectInputStream)(了解)
读取:new ObjectInputStream(InputStream),readObject()
代码演示如下:
```
public class Demo3_ObjectInputStream {
	//读取对象,即反序列化,把文件中的序列化看不太懂东西读取返回为对象:
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e.txt"));
		Person p1 = (Person) ois.readObject();//对象字节输入流,特有的读取对象方法
		Person p2 = (Person) ois.readObject();
		//Person p3 = (Person) ois.readObject();//当文件读取到了末尾时出现EOFException
		System.out.println(p1);
		System.out.println(p2);
		ois.close();
	}
}
```
总结:当写入两个对象到文件,从文件中读取三个,如上面代码加入:Person p3 = (Person) ois.readObject();就会出现异常:
EOFException,当文件读取到了末尾时出现该异常
	
#22.08_IO流(对象操作流优化)(了解)
将对象存储在集合中写出,即写出多个对象:
代码演示如下:
```
public static void main(String[] args) {
	//创建对象,添加到集合中存储
	Person p1 = new Person("张三", 23);
	Person p2 = new Person("李四", 24);
	Person p3 = new Person("君哥", 18);
	Person p4 = new Person("辉哥", 20);
	ArrayList<Person> list = new ArrayList<>();
	list.add(p1);
	list.add(p2);
	list.add(p3);
	list.add(p4);
	
	//对象字节输出流写出对象方法,传入存储对象的集合,写出到文件中
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f.txt"));
	oos.writeObject(list);//写出集合对象
	oos.close();//谁读写,谁关闭
}
```

读取到的是一个集合对象,即读取多个对象:
代码演示如下:
```
public static void main(String[] args) {
	//对象字节输入流读取对象方法,得到存储对象的集合,
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f.txt"));
	ArrayList<Person> list = (ArrayList<Person>)ois.readObject();//泛型在运行期会被擦除,相当于没有泛型
		
	//遍历集合元素,得到每一个对象,
	for (Person person : list) {
		System.out.println(person);//默认调用toString方法输出,
	}
	
	ois.close();//最后记得关闭流
}
```

#22.09_IO流(加上id号)(了解)
    要写出的对象必须实现Serializable接口才能被序列化,
    
    不用必须加id号:
        注意要写出的对象必须实现Serializable接口才能被序列化,
        另外你会发现即使对象类实现了Serializable接口,对象类名下就会出现黄色警告线,
        这时你在对象类中再增加一个成员变量,运行就会报无效类异常后面跟的是两个不同的id号,
        可以通过eclipse的快捷键ctr+1生成id号让黄色警告线消失,这时如果你在对象类中再增加一个成员变量,
        并且修改了生成的id号为一个新的值,那么运行还是会报无效类异常后面跟的是两个不同的id号,
        他们的值分别是你原本生成的id号的值和你修改后的id号的值,所以不用必须加id号,有黄色警告线也没问题

#22.10_IO流(数据输入输出流)(了解)
1.什么是数据输入输出流:

    DataInputStream, DataOutputStream可以按照基本数据类型大小读写数据,
	例如按long大小写出一个数字,写出时该数据占8字节,读取的时候也可以按照long类型读取,一次读取8个字节:
	
2.使用方式:

    DataOutputStream(OutputStream), writeInt(), writeLong():
代码演示如下:
```
public static void main(String[] args) {
	//数据字节输出流特有写出整型方法,传入数据,写出到文件中,最后记得关闭流:
	DataOutputStream dos = new DataOutputStream(new FileOutputStream("b.txt"));
	dos.writeInt(997);//用数据输出流写入数据到文件,打开文件还是乱码,用下面的数据字节输入流读出来:
	dos.writeInt(998);
	dos.writeInt(999);
	dos.close();
}
```

DataInputStream(InputStream), readInt(), readLong():
代码演示如下:
```
public static void main(String[] args) {
	//数据字节输入流特有读取整型方法,返回值用整型变量接收并输出,最后也要关闭流
	DataInputStream dis = new DataInputStream(new FileInputStream("b.txt"));
	int x = dis.readInt();//997,写出数据到文件,内容是乱码,用数据字节输入流读取用变量接收,
	int y = dis.readInt();
	int z = dis.readInt();
	System.out.println(x);//997,输出的值就是我们写出的数据值
	System.out.println(y);
	System.out.println(z);
	dis.close();
}
```

总结:其他这些其他流都有特有的其他方法,这里的数据字节输出流和数据字节输入流分别特有写出整型方法和读取整型方法
这个方法跟对象字节输出流和对象字节输入流有点类似,写出数据到文件打开都是看不懂的乱码,但是有对应的流读取可还原

#22.11_IO流(打印流的概述和特点)(掌握)
1.什么是打印流:

    该流可以很方便的将对象的toString()结果输出,并且自动加上换行,而且可以使用自动刷出的模式:
	如System.out就是一个PrintStream,其默认向控制台输出信息:
代码演示如下:
```
public static void main(String[] args) {
	//字节打印流调用打印方法,打印输出数据到控制台
	System.out.println("aaa");//aaa,输出aaa到控制台
	PrintStream ps = System.out;//获取标准输出流
	ps.println(97);//97,底层通过Integer.toString()将97转换成字符串97并打印到控制台
	ps.write(97);//a,查找码表,找到对应的a并打印到控制台
	
	Person p1 = new Person("张三", 23);
	ps.println(p1);//Person [name=张三, age=23],默认调用p1的toString方法,打印到控制台
	
	Person p2 = null;//打印引用数据类型,如果是null,就打印null,
	ps.println(p2);//null,如果不是null就打印对象的toString方法,这个可以查看源码就知道了
	//ps.println(p2.toString());//注意,这样的话会报运行时异常空指针异常,因为用null调用了方法
	ps.close();
}
```

2.使用方式:

	打印:print(),println()
	自动刷出:PrintWriter(OutputStream out, boolean autoFlush, String encoding) 
	
	打印流只操作数据目的:
代码演示如下:
```
public static void main(String[] args) {
	//字符打印流调用写出和打印方法,分别写出数据到文件中
	PrintWriter pw = new PrintWriter(new FileOutputStream("f.txt"),true);
    pw.println(97);//2,没有关闭流时,换了自动刷新功能构造如上面的,只针对的是println方法有效,打开文件内容是97

	pw.write(97);//无自动刷新缓冲区,打开文件无内容
	pw.print(97);//无自动刷新缓冲区,打开文件无内容
	pw.println(97);//打开f.txt文件内容是97换行a9797
      //pw.close();//1,如果调用的是PrintWriter("f.txt");并且没有关闭流,数据在缓冲区,打开文件没有内容
}
```

总结:打印流当然也分字节打印流和字符打印流,PrintStream和PrintWriter分别是打印的字节流和字符流,只操作数据目的的,
这句话有时候笔试会考,要注意!

    另外对于字符数组char[] c = {'1','2','3',48};用输出语句输出数组名字System.out.println(c);//输出结果是1230
    对于整型数组int[] i = {1,2,3};用输出语句输出数组名System.out.println(i);输出结果是一个地址值如: [I@60e128
    原因是:println方法重载了,对于传入的参数做了不同的操作,查看源码知道,对于整型数组多调用了String s = String.valueOf(x);
    展开valueOf方法源码知道其实是调用了Object类的toString方法输出结果当然是地址值:
    public static String valueOf(Object obj) {
            return (obj == null) ? "null" : obj.toString();
    }//所以对于调用的方法看似都相同,输出结果却不一样的时候,首先应该看看调用方法的源码是怎么做的,再想想其他原因,对吧

#22.12_IO流(标准输入输出流概述和输出语句)
1.什么是标准输入输出流(掌握):

	System.in是InputStream,标准输入流,默认可以从键盘输入读取字节数据
	System.out是PrintStream,标准输出流,默认可以向控制台Console中输出字符和字节数据
	
2.修改标准输入输出流(了解):

	修改输入流: System.setIn(InputStream)
	修改输出流: System.setOut(PrintStream)
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
	
	System.setIn(new FileInputStream("a.txt"));//改变标准输入流,a.txt内容是大家好,
	System.setOut(new PrintStream("b.txt"));//改变标准输出流
	
	InputStream is = System.in;//获取标准的键盘输入流,默认指向键盘,改变后指向文件
	PrintStream ps = System.out;//获取标准输出流,默认指向的是控制台,改变后就指向文件
	
	int b;
	while((b = is.read()) != -1) {//从a.txt文件上读取数据,
		ps.write(b);//将数据写到b.txt文件上,打开内容是大家好,
	}
	//System.out.println();//也是一个输出流,不用关,因为没有和硬盘上的文件产生关联的管道
	is.close();
	ps.close();
	
}

public static void demo1() throws IOException {
	//运行程序,在控制台输入a回车,得到的结果分别是97,13
	InputStream is = System.in;
	int x = is.read();
	System.out.println(x);//97,说明a对应的值是97
	
    //is.close();//流不能关了,因为下面还在读取,否则报错io流异常:流已经关了,
	
	InputStream is2 = System.in;//同时也说明了是同一个流对象
	int y = is2.read();//如果上面关了流,报异常 java.io.IOException: Stream closed
	System.out.println(y);//13,说明回车键对应的是13,这个可以美国码表查,
	//通过这个代码,如果我们不知道如0对应的是什么,就可以控制台输入0回车检查,知道对应的是48
}
```

#22.13_IO流(修改标准输入输出流拷贝图片)(了解)
代码演示如下:
```
public static void main(String[] args) {
	System.setIn(new FileInputStream("IO图片.png"));//改变标准输入流
	System.setOut(new PrintStream("copy.png"));//改变标准输出流
	
	InputStream is = System.in;//获取标准的键盘输入流,默认指向键盘,改变后指向文件
	PrintStream ps = System.out;//获取标准输出流,默认指向的是控制台,改变后就指向文件
	
	int len;
	byte[] arr = new byte[1024 * 8];
	
	while((len = is.read(arr)) != -1) {//读取IO图片.png文件的数据,
		ps.write(arr, 0, len);//写出到copy.png文件中
	}
	
	is.close();
	ps.close();
}
```
总结:要先设置改变标准输入输出流,然后获取标准输入输出流才能把默认指向的键盘或者控制台,改为指向传入的文件

#22.14_IO流(两种方式实现键盘录入)(了解)
A:BufferedReader的readLine()方法:
代码演示如下:
```
public static void main(String[] args) {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//构造变了,传入标准输入流
	String line = br.readLine();//运行程序,往控制台输入123,
	System.out.println(line);//控制台打印输出123
	br.close();
}
```

B:Scanner类实现键盘录入,导包,创建对象,调用方法:
代码演示如下:
```
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	String line = sc.nextLine();//运行程序,往控制台输入123,
	System.out.println(line);//控制台打印输出123
	sc.close();
}
```

#22.15_IO流(Properties的概述和作为Map集合的使用)(了解)
A:Properties类总的概述:

    Properties类表示了一个持久的属性集,
	Properties可保存在流中或从流中加载,
	属性列表中每个键及其对应值都是一个:字符串,有这么一个注意事项:
	    prop.getProperty(key);//根据键获取值,如果返回的值的类型不是一个字符串就会返回null
	    
B:案例演示:

	Properties是Hashtable的子类,有其作为Map集合的使用方式,注意是Hashtable的子类而不是HashMap的:
代码演示如下:
```
public static void main(String[] args) {
	//Properties作为Map集合的使用:↓
	Properties prop = new Properties();
	prop.put("abc", 123);
	System.out.println(prop);//{abc=123}
}
```

#22.16_IO流(Properties的特殊功能使用)(了解)
A:Properties的特殊功能:属性列表中每个键及其对应值都是一个:字符串,在下面方法的参数类型中完全体现:

	public Object setProperty(String key,String value)//设置键和值,
	public String getProperty(String key)//通过键获取值,返回的值的类型不是一个字符串就会返回null!!!
	public Enumeration<String> string PropertyNames()//获取所有的键集合封装到枚举,
	通过枚举的下个元素方法得到每一个键!!!
	
B:案例演示:

    Properties的特殊功能:
代码演示如下:
```
public static void main(String[] args) {
	//设置键和值,通过键获取值,获取所有的键集合封装到枚举,通过枚举的下个元素方法得到每一个键:↓
	Properties prop = new Properties();
	prop.setProperty("name", "张三");
	prop.setProperty("tel", "18912345678");
//	System.out.println(prop);//{tel=18912345678, name=张三}
		
	Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
	while(en.hasMoreElements()) {
		String key = en.nextElement();//获取Properties中的每一个键
String value = prop.getProperty(key);//根据键获取值,看源码知道,如果返回的值的类型不是一个字符串就会返回null
		System.out.println(key + "="+ value);//name=张三,换行,tel=18912345678
	}
}
```

#22.17_IO流(Properties的load()和store()功能)(了解)
A:Properties的load()和store()功能:

B:案例演示:
	* Properties的load()和store()功能:            
代码演示如下:
```
public static void main(String[] args) {
	//Properties的load()和store()功能:↓
	Properties prop = new Properties();
	prop.load(new FileInputStream("config.properties"));//将文件上的键值对读取到集合中,
	prop.setProperty("tel", "123456789");//设置键对应值,
prop.store(new FileOutputStream("config.properties"), null);//第二个参数是对列表参数的描述,可给值,也可给null
	System.out.println(prop);//{qq=12345, tel=123456789, username=zhangsan}

    /*
    另外,config.properties文件内容是:
    qq=12345
    tel=123456789
    username=zhangsan
    */
}
```

#22.18_day22总结
    把今天的知识点总结一遍:
    整合字节输入流-字节数组字节输出流写出数据到内存缓冲区-随机访问文件类拥有字节输入流和字节输出流的读写功能-
    对象字节输出流和对象字节输入流操作对象进行写出序列化和读取反序列化-数据字节输出流和数据字节输入流写出整型数据
    打开文件乱码,和读取整型数据还原写出数据-字节打印流打印写出数据到控制台,字符打印流打印写出数据到文件中-
    要先设置改变标准输入输出流,然后获取标准输入输出流才能把默认指向的键盘或者控制台,改为指向传入的文件-
    Properties类


