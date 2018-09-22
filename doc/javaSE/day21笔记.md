#21.01_IO流(字符流FileReader)
1.字符流是什么:

    字符流是可以直接读写字符的IO流
	字符流读取字符,就要先读取到字节数据,然后转为字符,如果要写出字符,需要把字符转为字节再写出
	
2.FileReader:

    FileReader类的read()方法可以按照字符大小读取
    
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
    
    FileReader fr = new FileReader("xxx.txt");//xxx.txt里面内容是:大家好
    int c;
    
    while((c = fr.read()) != -1) {//通过项目默认的码表一次读取一个字符
        System.out.print((char)c);//大家好
    }
    
    fr.close();
}

public static void demo1() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("xxx.txt");//xxx.txt里面内容是:大家好
	int x = fr.read();
	System.out.println(x);//22823
	char c = (char)x;
	System.out.println(c);//大
	fr.close();
}
```

#21.02_IO流(字符流FileWriter)
FileWriter类的write()方法可以自动把字符转为字节写出:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	FileWriter fw = new FileWriter("yyy.txt");//文件里起始内容是:大家好
	fw.write("大家好,基础班快接近尾声了,大家要努力,要坚持!!!");
	fw.write(97);//a,Writes a single character
	fw.close();//关闭流后文件内容是:大家好,基础班快接近尾声了,大家要努力,要坚持!!!a
}
```

#21.03_IO流(字符流的拷贝)
代码演示如下:
```
public static void main(String[] args) {
    FileReader fr = new FileReader("a.txt");
    FileWriter fw = new FileWriter("b.txt");
    
    int ch;
    while((ch = fr.read()) != -1) {
    	fw.write(ch);
    }
    
    fr.close();
    fw.close();//Writer类中有一个2K的小缓冲区,
    如果不关流,内容将写到缓冲区,直到缓冲区满了才会写入到文件中,
    要想快速写入到文件中,关流会将缓冲区内容刷新到文件,
    否则打开写入文件内容为空
}
```

#21.04_IO流(什么情况下使用字符流)
字符流也可以拷贝文本文件,但是,不推荐使用,因为读取时会把字节转为字符,写出时还要把字符转回字节:

    程序需要读取一段文本,或者,需要写出一段文本的时候可以使用字符流
    读取的时候是按照字符的大小读取的,不会出现半个中文
    写出的时候可以直接将字符串写出,不用转换为字节数组

#21.05_IO流(字符流是否可以拷贝非纯文本的文件)
不可以拷贝非纯文本的文件(如图片音频):

    因为在读的时候会将字节转换为字符,在转换过程中,可能找不到对应的字符,就会用?代替,
    写出的时候会将字符转换成字节写出去,
    如果是?,直接写出,这样写出之后的文件就乱了,看不了了

#21.06_IO流(自定义字符数组的拷贝)
代码演示如下:
```
public static void main(String[] args) {
    FileReader fr = new FileReader("aaa.txt");//创建字符输入流,关联aaa.txt
    FileWriter fw = new FileWriter("bbb.txt");//创建字符输出流,关联bbb.txt
    
    int len;
    char[] arr = new char[1024*8];//创建字符数组
    while((len = fr.read(arr)) != -1) {//将数据读到字符数组中
    	fw.write(arr, 0, len);//从字符数组将数据写到文件上
    }
    
    fr.close();//关流释放资源
    fw.close();//Writer类中有一个2K的小缓冲区,要关闭流或者手动刷新才能刷新缓冲区数据到文件中!!!
}
```
总结:跟字节流时自定义数组拷贝一样,只不过是把字节换成了字符,对吧?

#21.07_IO流(带缓冲的字符流)
    BufferedReader的read()方法读取字符时会一次读取若干字符到缓冲区,然后逐个返回给程序,降低读取文件的次数,提高效率
    
    BufferedWriter的write()方法写出字符时会先写到缓冲区,缓冲区写满时才会写到文件,降低写文件的次数,提高效率

代码演示如下:
```
public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new FileReader("aaa.txt"));//创建字符输入流对象,关联aaa.txt
    BufferedWriter bw = new BufferedWriter(new FileWriter("bbb.txt"));//创建字符输出流对象,关联bbb.txt
    
    int ch;			
    while((ch = br.read()) != -1) {//read一次,会先将缓冲区读满,从缓冲区中一个一个的返给临时变量ch
    	bw.write(ch);//write一次,是将数据装到字符数组缓冲区,装满后再一起写出去
    }
    
    br.close();//关流
    bw.close();//要记得关流,否则无法刷新缓冲区数据到文件
}
```

#21.08_IO流(readLine()和newLine()方法)	
BufferedReader的readLine()方法可以读取一行字符(不包含换行符号),所以对应用newLine()方法换行(并且它是跨平台的)
BufferedWriter的newLine()可以输出一个跨平台的换行符号"\r\n"

代码演示如下:
```
public static void main(String[] args) {
	BufferedReader br = new BufferedReader(new FileReader("aaa.txt"));
	BufferedWriter bw = new BufferedWriter(new FileWriter("bbb.txt"));
	String line;
	while((line = br.readLine()) != null) {//readLine()方法可以读取一行字符(不包含换行符号),
		bw.write(line);//因为如果有,我下面就不用手动换行了,你注释掉打开文件对比
		//bw.write("\r\n");//只支持windows系统
		bw.newLine();//跨平台的
	}
	
	br.close();
	bw.close();
}
```
总结:readLine()方法是BufferedReader特有的,不要搞混了,newLine()方法是BufferedWriter特有的,他们都是字符流方法

#21.09_IO流(将文本反转)
将一个文本文档上的文本反转,第一行和倒数第一行交换,第二行和倒数第二行交换:
代码演示如下:
```
public class Test1 {
	public static void main(String[] args) throws IOException {
		//改写后是流对象尽量晚开早关,意思是用到什么创建什么,用完就关,想水流一样不要浪费水资源
		// 1,创建输入输出流对象
		BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
		
		//2,创建集合对象
		ArrayList<String> list = new ArrayList<>();
		//3,将读到的数据存储在集合中
		String line;
		while((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();//早关流
		
		//4,倒着遍历集合将数据写到文件上
		BufferedWriter bw = new BufferedWriter(new FileWriter("revzzz.txt"));//晚开流对象,用到再创建
		for(int i = list.size() - 1; i >= 0; i--) {
			bw.write(list.get(i));
			bw.newLine();//换行,否则很难看
		}
		//5,用完要关流
		bw.close();
	}
}
```

#21.10_IO流(LineNumberReader)
LineNumberReader是BufferedReader的子类,具有相同的功能,其特有方法是可以统计行号:

    调用getLineNumber()方法可以获取当前行号
	调用setLineNumber()方法可以设置当前行号
代码演示如下:
```
public static void main(String[] args) {
	LineNumberReader lnr = new LineNumberReader(new FileReader("aaa.txt"));
	String line;
	lnr.setLineNumber(100);	//设置行号为100,调用得到行号方法时显示的是从101行号开始
	while((line = lnr.readLine()) != null) {
		System.out.println(lnr.getLineNumber() + ":" + line);//101:读取内容,获取行号
	}
	
	lnr.close();
}
```

#21.11_IO流(装饰设计模式)
也叫包装设计模式,好处是耦合性不强,被装饰的类的变化与装饰类的变化无关:
代码演示如下:
```
interface Coder {
	public void code();
}

class Student implements Coder {

	@Override
	public void code() {
		System.out.println("javase");
		System.out.println("javaweb");
	}
	
}
		
class HeiMaStudent implements Coder {
	private Student s;//获取到被包装的类的引用
	public HeiMaStudent(Student s) {//通过构造函数创建对象的时候,传入被包装的对象
		this.s = s;
	}
	@Override
	public void code() {//对其原有功能进行升级
		s.code();
		System.out.println("数据库");
		System.out.println("ssh");
		System.out.println("安卓");
		System.out.println(".....");
	}			
}
```
    总结:包装设计模式用接口实现类2包装写入其成员变量为接口实现类1,通过有参构造包装传入接口实现类2赋值成员变量,
    这样在接口实现类2的复写方法里面就可以调用接口实现类1对象和其方法,并加上自己的特有方法,来实现所谓的包装装饰

#21.12_IO流(使用指定的码表读写字符)
    FileReader是使用默认码表读取文件,如果需要使用指定码表读取,那么可以使用InputStreamReader(字节流,编码表)
    
    FileWriter是使用默认码表写出文件,如果需要使用指定码表写出,那么可以使用OutputStreamWriter(字节流,编码表)
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //demo1();
	//demo2();
	
	//什么码表文件用什么码表读,什么码表文件用什么码表写,这样对应的话,就不会出现乱码:
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"), "utf-8"));//更高效的读
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"));//更高效的写
	int c;
	while((c = br.read()) != -1) {
		bw.write(c);
	}
	
	br.close();
	bw.close();
}

public static void demo2() throws UnsupportedEncodingException,FileNotFoundException, IOException {
    //什么码表文件用什么码表读,什么码表文件用什么码表写,这样对应的话,就不会出现乱码:
	InputStreamReader isr = new InputStreamReader(new FileInputStream("utf-8.txt"), "uTf-8");//对应码表文件,指定对应码表读字符
	OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk");//对应码表文件,指定码表写字符
	
	int c;
	while((c = isr.read()) != -1) {
		osw.write(c);
	}
	
	isr.close();
	osw.close();
}

public static void demo1() throws FileNotFoundException, IOException {
	//用gbk码表读取utf-8码表文件内容,用gbk码表写入到gbk码表文件中,打开文件就会出现乱码:
	FileReader fr = new FileReader("utf-8.txt");//utf-8.txt文件手动改为用的是utf-8码表			
	FileWriter fw = new FileWriter("gbk.txt");//gbk.txt文件手动改为用的是gbk码表
	
	int c;
	while((c = fr.read()) != -1) {
		fw.write(c);
	}
	
	fr.close();
	fw.close();
}
```
总结:从new InputStreamReader(new FileInputStream("utf-8.txt"), "uTf-8");知道,
InputStreamReader是字节流FileInputStream通向字符流InputStreamReader的桥梁,
反之,OutputStreamWriter是字符流通向字节流的桥梁;
另外:什么码表文件用什么码表读,什么码表文件用什么码表写,这样对应的话,就不会出现乱码,相反如果不对应,例如用:
用gbk码表读取utf-8码表文件内容,用gbk码表写入到gbk码表文件中,打开文件就会出现乱码

#21.13_IO流(转换流图解)
    转换流InputStreamReader是字节流通向字符流的桥梁,反之,
    转换流OutputStreamWriter是字符流通向字节流的桥梁:
    画图分析转换流:见截图

#21.14_IO流(获取文本上字符出现的次数)
获取一个文本上每个字符出现的次数,将结果写在times.txt上:
代码演示如下:
```
public static void main(String[] args) throws IOException {
    //1,创建带缓冲区的输入流对象
	BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
	//2,创建双列集合对象,目的是把字符当作键,把字符出现的次数当作值
	HashMap<Character, Integer> hm = new HashMap<>();
	//3,通过读取不断向集合中存储,存储的时候要判断,如果不包含这个键就将键和值为1存储,如果包含就将键和值加1存储
	int c;
	while((c = br.read()) != -1) {
		char ch = (char)c;
		/*if(!hm.containsKey(ch)) {
			hm.put(ch, 1);
		}else {
			hm.put(ch, hm.get(ch) +  1);
		}*/
		
		hm.put(ch, !hm.containsKey(ch)? 1 : hm.get(ch) + 1);
	}
	//4,关闭输入流
	br.close();

	//5,创建输出流对象
	BufferedWriter bw = new BufferedWriter(new FileWriter("times.txt"));
	//6,将结果写出
	for (Character key : hm.keySet()) {
		bw.write(key + "=" + hm.get(key));
	}
	
	bw.close();//关闭输出流
}
```

改进版代码演示如下:
```
public static void main(String[] args) throws IOException {
	//1,创建带缓冲的输入流对象
	BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
	//2,创建双列集合对象TreeMap
	TreeMap<Character, Integer> tm = new TreeMap<>();
	//3,将读到的字符存储在双列集合中,存储的时候要做判断,如果不包含这个键,就将键和1存储,如果包含这个键,就将该键和值加1存储
	int ch;
	while((ch = br.read()) != -1) {
		char c = (char)ch;//强制类型转换
		/*if(!tm.containsKey(c)) {
			tm.put(c, 1);
		}else {
			tm.put(c, tm.get(c) + 1);
		}*/
		tm.put(c, !tm.containsKey(c) ? 1 : tm.get(c) + 1);
	}
	//4,关闭输入流
	br.close();
	//5,创建输出流对象
	BufferedWriter bw = new BufferedWriter(new FileWriter("times.txt"));
	//6,遍历集合将集合中的内容写到times.txt中
	for(Character key : tm.keySet()) {
		switch (key) {//筛选情况做不同写出:
		case '\t':
			bw.write("\\t" + "=" + tm.get(key)); 	
			break;
		case '\n':
			bw.write("\\n" + "=" + tm.get(key)); 
			break;
		case '\r':
			bw.write("\\r" + "=" + tm.get(key)); 
			break;
		default:
			bw.write(key + "=" + tm.get(key));//写出键和值
			break;
		}
		bw.newLine();//换行
	}
	//7,关闭输出流
	bw.close();
}
```

#21.15_IO流(试用版软件)
    当我们下载一个试用版软件,没有购买正版的时候,每执行一次就会提醒我们还有多少次使用机会,用学过的IO流知识,
    模拟试用版软件,试用10次机会,执行一次就提示一次您还有几次机会,如果次数到了提示请购买正版:
代码演示如下:
```
public static void main(String[] args) throws IOException {
	//1,创建带缓冲的输入流对象,因为要使用readLine方法,可以保证数据的原样性
	BufferedReader br = new BufferedReader(new FileReader("config.txt"));
	//2,将读到的字符串转换为int数
	String line = br.readLine();//读取一行即读到数字10
	int times = Integer.parseInt(line);//将数字字符串转换为数字
	//3,对int数进行判断,如果大于0,就将其--写回去,如果不大于0,就提示请购买正版
	if(times > 0) {
		//4,在if判断中要将--的结果打印,并将结果通过输出流写到文件上
		System.out.println("您还有" + times-- + "次机会");
		FileWriter fw = new FileWriter("config.txt");
		fw.write(times + "");
		fw.close();//用完关闭输出流,并且write有缓冲区,关流可以刷新缓冲区,这个要注意!!!
	}else {
		System.out.println("您的试用次数已到,请购买正版");
	}
	//最后关闭输入流
	br.close();
}
```
总结:精明之处在于用字符流读取一行保证数据的原样性即读到的是10,并且读和写操作的是同一个文件config.txt

#21.16_File类(递归)
    5的阶乘,即5i = 5 * 4 * 3 * 2 * 1
代码演示如下:
```
public static void main(String[] args) {
    int result = 1;
	
	for(int i = 1; i <= 5; i++) {
		result = result * i;
	}
	
	System.out.println(result);//120
	System.out.println(fun(5));//120
    //System.out.println(fun(6000));//0,求到的值超出了int所能表示的范围就用0表示,另外调用次数会溢出!
}

public static int fun(int num) {
	if(num == 1) {
		return 1;
	}else {
		return num * fun(num - 1);
	}
}
```
总结:递归的弊端:不能调用次数过多,容易导致栈内存溢出;
递归的好处:不用知道循环次数;
注意:构造方法不能使用递归调用,
递归调用不一定(可以有,也可以没有)返回值,
要想求得超出int范围的阶乘结果,如100的阶乘结果,用BigInteger,
代码演示如下:
```
public class Work {
	public static void main(String[] args) {
		BigInteger result = fun(new BigInteger("100"));
		System.out.println("100的阶乘结果是"+result);//↓
//93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000

		//100的阶乘末尾的零有多少个0:↓
		StringBuilder sb = new StringBuilder();
		String string = sb.append(result).reverse().toString();
		char[] charArray = string.toCharArray();
		int count = 0;
		for (char c : charArray) {
			if (c!='0') {//末尾反转后不是0字符就跳出循环,否则统计字符次数
				break;
			}else {
				count++;
			}
		}
		System.out.println("100的阶乘末尾的零有"+count+"个");//24
	}
	
	public static BigInteger fun(BigInteger num) {
		if (num.equals(new BigInteger("1"))) {
			return new BigInteger("1");
		}
		return num.multiply(fun(num.subtract(new BigInteger("1"))));
	}

}
```

#21.17_File类(练习)
需求:从键盘输入接收一个文件夹路径,打印该文件夹下所有的.java文件名:
代码演示如下:
```
public static void main(String[] args) {
	File dir = getDir();
	printJavaFile(dir);
}

//不断判断,文件路径存在不是文件夹就是文件:↓
public static File getDir() {
	Scanner sc = new Scanner(System.in);//创建键盘录入对象
	System.out.println("请输入一个文件夹路径");
	while(true) {
		String line = sc.nextLine();//将键盘录入的文件夹路径存储
		File dir = new File(line);//封装成File对象
		if(!dir.exists()) {
			System.out.println("您录入的文件夹路径不存在,请重新录入");
		}else if(dir.isFile()) {
			System.out.println("您录入的是文件路径,请重新录入文件夹路径");
		}else {
			return dir;//路径存在且不是文件,那么肯定是文件夹
		}
	}
}

//定义方法传入文件夹判断文件下是文件并且是指定后缀即输出,否则递归调用方法再来一次,以此类推:
public static void printJavaFile(File dir) {
	//1,获取到该文件夹路径下的所有的文件和文件夹,存储在File数组中
File[] subFiles = dir.listFiles();//有时输入盘符此方法返回null,下面报空指针异常,就做一个判空并返回解决↓
	if (subFiles==null) {
		return;//判空返回↑
	}
	//2,遍历数组,对每一个文件或文件夹做判断
	for (File subFile : subFiles) {
		//3,如果是文件,并且后缀是.java的,就打印
		if(subFile.isFile() && subFile.getName().endsWith(".java")) {
			System.out.println(subFile);
		//4,其他如果是文件夹,就递归调用
		}else if (subFile.isDirectory()){
			printJavaFile(subFile);
		}
	}
}
```
总结:最后的判断容易写错else,而不是我们想要的else if,这个要注意一下,除了.java文件还有其他文件吧,所以不能用else

#21.18_IO流(总结)
    把今天的知识点总结一遍:
    1.会用BufferedReader读取GBK码表和UTF-8码表的字符
    2.会用BufferedWriter写出字符到GBK码表和UTF-8码表的文件中
    3.会使用BufferedReader从键盘读取一行
    4.Writer类有小缓冲区,要关闭流刷新缓冲区

