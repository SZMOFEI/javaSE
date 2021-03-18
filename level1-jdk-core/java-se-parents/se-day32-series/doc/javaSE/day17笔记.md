#17.01_集合框架(HashSet存储字符串并遍历)
###A:Set集合概述及特点:无索引,不可以重复,无序(存取不一致)
    通过API查看即可
    
B:案例演示Demo1_HashSet

    HashSet存储字符串并遍历:
代码演示如下:
```
public static void demo1() {
	HashSet<String> hs = new HashSet<>();//创建HashSet对象
	boolean b1 = hs.add("a");
	boolean b2 = hs.add("a");//当向set集合中存储重复元素的时候返回为false,不可以重复
	hs.add("b");
	hs.add("c");
	hs.add("d");
System.out.println(hs);//[d, b, c, a]说明无序(存取不一致)和HashSet的继承体系中有重写toString方法
	System.out.println(b1);//true
	System.out.println(b2);//false
	
	for (String string : hs) {//只要能用迭代器迭代的,就可以使用增强for循环遍历
		System.out.print(string);//dbca,这里用不换行输出为了节省空间
	}
}
```	

#17.02_集合框架(HashSet存储自定义对象保证元素唯一性)
A:案例演示Demo1_HashSet

    存储自定义对象,并保证元素唯一性:
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    //HashSet存储自定义对象保证元素唯一性,要重写自定义类里的hashCode()和equals()方法,工具自动生成即可
    HashSet<Person> hs = new HashSet<>();
    hs.add(new Person("张三", 23));//Person类里有重写hashCode()和equals()方法,可手写,也可自动生成的
    hs.add(new Person("张三", 23));
    hs.add(new Person("李四", 24));
    hs.add(new Person("李四", 24));
    hs.add(new Person("李四", 24));
    hs.add(new Person("李四", 24));
    hs.add(null);//可以存储null
    System.out.println(hs.size());//3
    System.out.println(hs);//[null, Person [name=张三, age=23], Person [name=李四, age=24]]
}

//另一个源文件里的Person类写法如下:
public class Person {
    private String name;
	private int age;
	//Alt+shift+S分别按c,o,r,s生成无参构造,有参构造,setter和getter方法,toString方法,不写出来节省空间...

	//手动重写两个方法:
	/*
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals方法被执行的越少效率越高");
		Person p = (Person)obj;
		return this.name.equals(p.name) && this.age == p.age;
	}
	
	@Override
	public int hashCode() {//手动重写hashCode方法,尽量让其返回值不同,减少调用equals方法的次数,
		final int NUM = 38;//引用类型的属性的哈希码值乘以一个整形系数,让方法返回结果尽量不同,
		return name.hashCode() * NUM + age;//一般返回的是引用类型属性的哈希码值加上基本数据类型的值
	}
	*/
	
	//eclipse工具自动生成重写两个方法的代码:
	/*
	 * 为什么是31?
	 * 1,31是一个质数,质数是能被1和自己本身整除的数
	 * 2,31这个数既不大也不小
	 * 3,31这个数好算,是2的五次方-1,2向左移动5位
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)			//调用的对象和传入的对象是同一个对象
			return true;			//直接返回true
		if (obj == null)			//传入的对象为null
			return false;			//返回false
		if (getClass() != obj.getClass())	//判断两个对象对应的字节码文件是否是同一个字节码
			return false;			//如果不是直接返回false
		Person other = (Person) obj;		//向下转型
		if (age != other.age)			//调用对象的年龄不等于传入对象的年龄
			return false;			//返回false
		if (name == null) {			//调用对象的姓名为null
			if (other.name != null)		//传入对象的姓名不为null
				return false;		//返回false
		} else if (!name.equals(other.name))	//调用对象的姓名不等于传入对象的姓名
			return false;			//返回false
		return true;				//返回true
	}
}
```
总结:需要Person类里面重写hashCode()和equals()方法,可手写,可工具自动生成

#17.03_集合框架(HashSet存储自定义对象保证元素唯一性图解及代码优化)
A:画图演示:

    画图说明比较过程:课堂里举了一个上火车坐看票的例子,hashCode返回值对应票的号码,
	equals方法看是不是同一个人,如果同号同人就一起坐
	
B:代码优化:

	为了减少比较,优化hashCode()代码写法,尽量让hashCode方法的返回值不同,
	最终版就是用eclipse自动生成即可
	
总结:源码中是hashCode方法返回值不同就不是同一个元素,那么集合添加元素不用调用equals方法做比较,
如果hashCode方法返回值相同有可能是同一个元素,就要调用equals方法做比较,当equals方法返回true时是同一个元素,
就不添加,反之当equals方法返回false时表示不是同一个元素,那么集合就添加元素,从而保证元素的唯一性

#17.04_集合框架(HashSet如何保证元素唯一性的原理)
1.HashSet原理:

    我们使用Set集合都是需要去掉重复元素的,如果在存储的时候逐个equals()比较,效率较低,哈希算法提高了去重复的效率,
    降低了使用equals()方法的次数,
    当HashSet调用add()方法存储对象的时候,先调用对象的hashCode()方法得到一个哈希值,
    然后在集合中查找是否有哈希值相同的对象,
    如果没有哈希值相同的对象就直接存入集合,
    如果有哈希值相同的对象,就和哈希值相同的对象逐个进行equals()比较,比较结果为false就存入,true则不存
    
2.将自定义类的对象存入HashSet去重复:

    类中必须重写hashCode()和equals()方法:
    hashCode():属性相同的对象返回值必须相同,属性不同的返回值尽量不同(提高效率)
    equals():属性相同返回true,属性不同返回false,返回false的时候存储

#17.05_集合框架(LinkedHashSet的概述和使用)
A:LinkedHashSet的特点:

B:案例演示Demo2_LinkedHashSet

    底层是链表实现的,是set集合中唯一一个能保证怎么存就怎么取的集合对象,
	因为是HashSet的子类,所以也是保证元素唯一的,与HashSet的原理一样
	LinkedHashSet的特点:
	    可以保证怎么存就怎么取
代码演示如下:
```
public static void main(String[] args) {
    LinkedHashSet<String> lhs = new LinkedHashSet<>();
    lhs.add("a");
    lhs.add("a");
    lhs.add("a");
    lhs.add("a");
    lhs.add("b");
    lhs.add("c");
    lhs.add("d");
    
    System.out.println(lhs);//[a, b, c, d],可以保证怎么存就怎么取,也能去重复
}
```

#17.06_集合框架(产生10个1-20之间的随机数要求随机数不能重复)
A:案例演示Test1

	需求:编写一个程序,获取10个1至20的随机数,要求随机数不能重复,并把最终的随机数输出到控制台
代码演示如下:
```
public static void main(String[] args) {
    HashSet<Integer> hs = new HashSet<>();//创建集合对象,
    Random r = new Random();//创建随机数对象,
    
    while(hs.size() < 10) {
    	int num = r.nextInt(20) + 1;//生成1到20的随机数,
    	hs.add(num);//添加到去重复集合中,
    }
    
    for (Integer integer : hs) {//遍历集合元素,
    	System.out.println(integer);//打印每一个元素
    }
}
```

#17.07_集合框架(练习)
案例演示Test2

    使用Scanner从键盘读取一行输入,去掉其中重复字符,打印出不同的那些字符,如录入aaaabbbcccddd,输出结果为abcd
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);//创建键盘录入对象
    System.out.println("请输入一行字符串:");
    String line = sc.nextLine();//将键盘录入的字符串存储在line中
    char[] arr = line.toCharArray();//将字符串转换成字符数组
    HashSet<Character> hs = new HashSet<>();//创建HashSet集合对象
    
    for(char c : arr) {//遍历字符数组,这里char改为Character也是可以的,因为有自动装箱
    	hs.add(c);//将字符数组中的字符添加到去重复集合中
    }
    
    for (Character ch : hs) {//遍历集合元素
    	System.out.println(ch);//如果需要怎么存怎么取,用LinkedHashSet替换HashSet
    }
}
```

#17.08_集合框架(练习)
案例演示Test3

    将集合中的重复元素去掉:
代码演示如下:
```
public static void main(String[] args) {
	ArrayList<String> list = new ArrayList<>();
	list.add("a");
	list.add("a");
	list.add("a");
	list.add("b");
	list.add("b");
	list.add("b");
	list.add("b");
	list.add("c");
	list.add("c");
	list.add("c");
	list.add("c");
	
	System.out.println(list);
	System.out.println("去除重复后:");
	getSingle(list);
	System.out.println(list);
}

public static void getSingle(List<String> list) {
	LinkedHashSet<String> lhs = new LinkedHashSet<>();
	lhs.addAll(list);//去重复加上怎么存怎么取,用LinkedHashSet添加传入集合元素,
	list.clear();//传入集合清空元素,用于添加去重复集合的元素,
	list.addAll(lhs);//传入集合添加去重复集合元素
}
```

#17.09_集合框架(TreeSet存储Integer类型的元素并遍历)
A:案例演示Demo3_TreeSet

    TreeSet存储Integer类型的元素并遍历
代码演示如下:
```
public static void demo1() {
    //TreeSet存储Integer类型的元素并遍历,
    TreeSet<Integer> ts = new TreeSet<>();
    ts.add(3);
    ts.add(1);
    ts.add(1);
    ts.add(2);
    ts.add(2);
    ts.add(3);
    ts.add(3);
        //TreeeSet能排序,但不是怎么存就怎么取(set集合中只有LinkedHashSet能),
    System.out.println(ts);//[1, 2, 3]
}
```

#17.10_集合框架(TreeSet存储自定义对象)
A:案例演示Demo3_TreeSet

	存储Person对象,如果Person类没实现Comparable接口并复写compareTo方法会报类型转换异常,
    如Person cannot be cast to java.lang.Comparable
    当compareTo方法返回0的时候集合中只有一个元素,
    当compareTo方法返回正数的时候集合会怎么存就怎么取,(哎自定义类型可以通过返回值改变怎么存就怎么取)
    当compareTo方法返回负数的时候集合会倒序存储
    
代码演示如下:
```
public static void demo2() {
    //TreeSet存储自定义对象,
    TreeSet<Person> ts = new TreeSet<>();
    ts.add(new Person("张三", 23));
    ts.add(new Person("李四", 13));
    ts.add(new Person("周七", 13));
    ts.add(new Person("王五", 43));
    ts.add(new Person("赵六", 33));
    //如果Person类没实现Comparable接口并复写方法会报类型转换异常,如Person cannot be cast to java.lang.Comparable
    System.out.println(ts);//输出结果取决于Person类实现Comparable接口并复写compareTo方法的返回值
}

//另一个源文件中Person类的写法如下:
public class Person implements Comparable<Person> {
    private String name;
	private int age;
	//Alt+shift+S分别按c,o,r,s生成无参构造,有参构造,setter和getter方法,toString方法,不写出来节省空间...
	
	/* @Override
	//按照年龄排序,
	public int compareTo(Person o) {
		int num = this.age - o.age;//年龄是比较的主要条件,
		return num == 0 ? this.name.compareTo(o.name) : num;//姓名是比较的次要条件
	}*/

	/* @Override
	//按照姓名排序,
	public int compareTo(Person o) {
		int num = this.name.compareTo(o.name);//姓名是主要条件,
		return num == 0 ? this.age - o.age : num;//年龄是次要条件
	}*/

	/*
	 * 当compareTo方法返回0时,集合中只有一个元素,
	 * 当compareTo方法返回正数时,集合会怎么存就怎么取,
	 * 当compareTo方法返回负数时,集合会倒序存储
	 */
	public int compareTo(Person o) {
	    //System.out.println("compareTo方法被调用了,"+"this.getName()是"+this.getName()+",o.name是"+o.name);
	    
		int length = this.name.length() - o.name.length();//比较姓名长度为主要条件,
		int num = length == 0 ? this.name.compareTo(o.name) : length;//比较姓名内容为次要条件,
		return num == 0 ? this.age - o.age : num;//比较年龄为次要条件
	}	
}
```

#17.11_集合框架(TreeSet保证元素唯一和自然排序的原理和图解)
A:画图演示:见截图

    TreeSet保证元素唯一和自然排序的原理和图解:
    总结:第一个元素先进来调用compareTo方法自己跟自己比肯定相等返回0存储自己,然后第二个元素进来调用compareTo方法,
    跟第一个元素比,当方法返回值是0就不存储,当方法返回值是-1就存储在第一个元素下方的左边,
    反之当方法返回值是1就存储在第一个元素下方的右边,然后第三个元素先跟第一个元素比较,然后再跟第二个元素比较,
    依次类推存放,这就是二叉树的数据结构,
    取的时候按从小到大的顺序来,如先左下方的左,到第一个元素,再到右下方的左,然后到右下方的右

#17.12_集合框架(TreeSet存储自定义对象并遍历练习1)
A:案例演示Person

    TreeSet存储自定义对象并遍历练习1(按照姓名排序):
代码演示如下:
```
public static void demo3() {
    //分别测试修改Person类的实现接口复写方法,按照姓名排序和按照年龄排序输出测试结果:
    TreeSet<Person> ts = new TreeSet<>();
    ts.add(new Person("李四", 13));
    ts.add(new Person("张三", 23));
    ts.add(new Person("王五", 43));
    ts.add(new Person("赵六", 33));
    
    System.out.println('张' + 0);//24352
    System.out.println('李' + 0);//26446
    System.out.println('王' + 0);//29579
    System.out.println('赵' + 0);//36213
    System.out.println('周' + 0);//21608,数值最小
    
    System.out.println(ts);//
}

//另一个源文件中Person类的写法如下:
public class Person implements Comparable<Person> {
	private String name;
	private int age;
	//Alt+shift+S分别按c,o,r,s生成无参构造,有参构造,setter和getter方法,toString方法,不写出来节省空间...
	
	//按照年龄为主要条件进行排序,
	@Override
	public int compareTo(Person o) {
		int num = this.age - o.age;//年龄是比较的主要条件,
		return num == 0 ? this.name.compareTo(o.name) : num;//姓名是比较的次要条件
	}
}
```

#17.13_集合框架(TreeSet存储自定义对象并遍历练习2)
A:案例演示Person

    TreeSet存储自定义对象并遍历练习2(按照姓名的长度排序):
代码演示如下:
```
public static void demo4() {
    //按照姓名的长度为主要条件进行排序,
    TreeSet<Person> ts = new TreeSet<>();
    ts.add(new Person("zhangsan", 23));
    ts.add(new Person("lisi", 13));
    ts.add(new Person("wangwu", 33));
    ts.add(new Person("zhaoliu", 43));
    ts.add(new Person("aaaa", 53));
    
    System.out.println(ts);//[Person [name=aaaa, age=53], Person [name=lisi, age=13], ...
}

//另一个源文件中Person类的写法如下:
public class Person implements Comparable<Person> {
	private String name;
	private int age;
	//Alt+shift+S分别按c,o,r,s生成无参构造,有参构造,setter和getter方法,toString方法,不写出来节省空间...
	
	//按照姓名的长度为主要条件进行排序
	public int compareTo(Person o) {
		int length = this.name.length() - o.name.length();//比较姓名长度为主要条件,
		int num = length == 0 ? this.name.compareTo(o.name) : length;//比较姓名内容为次要条件,
		return num == 0 ? this.age - o.age : num;//比较年龄为次要条件
	}	
}
```

#17.14_集合框架(TreeSet保证元素唯一和比较器排序的原理及代码实现)
A:案例演示Demo3_TreeSet

    TreeSet保证元素唯一和比较器排序的原理及代码实现:
    总结:系统提供的String类复写的compareTo方法是按照字典顺序排序的,无法满足按字符串长度排序的需求,
    所以要用比较器排序来实现这个需求,因为TreeSet构造函数什么都不传,默认按照类中Comparable的顺序,
    没有就报错ClassCastException,TreeSet如果传入Comparator比较器,就优先按照Comparator比较器来
代码演示如下:
```
public class Demo3_TreeSet {
	public static void main(String[] args) {
	    //需求:字符串String类里复写了compareTo方法采用的是字典顺序,
		TreeSet<String> ts = new TreeSet<>(new CompareByLen());//Comparator c = new CompareByLen();
		ts.add("aaaaaaaa");
		ts.add("z");
		ts.add("wc");
		ts.add("nba");
		ts.add("cba");
		//所以要用比较器来解决字符串按照长度排序需求:
		System.out.println(ts);//[z, wc, cba, nba, aaaaaaaa]
	}
}

class CompareByLen implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {//按照字符串的长度比较:
		int num = s1.length() - s2.length();//长度为主要条件,
		return num == 0 ? s1.compareTo(s2) : num;//内容为次要条件
	}	
}
```

#17.15_集合框架(TreeSet原理)
1.特点:

    TreeSet是用来排序的,可以指定一个顺序,对象存入之后会按照指定的顺序排列
    
2.使用方式:

	a.自然顺序(Comparable):
	    TreeSet类的add()方法中会把存入的对象提升为Comparable类型,
		调用对象的compareTo()方法和集合中的对象比较,
		根据compareTo()方法返回的结果进行存储

	b.比较器顺序(Comparator):
		创建TreeSet的时候可以制定一个Comparator比较器,
		如果传入了Comparator的子类对象,那么TreeSet就会按照比较器中的顺序排序,
		add()方法内部会自动调用Comparator接口中compare()方法排序,
		调用的对象是compare方法的第一个参数,集合中的对象是compare方法的第二个参数

	c.两种方式的区别:
		TreeSet构造函数什么都不传,默认按照类中Comparable的顺序(没有就报错ClassCastException),
		TreeSet如果传入Comparator比较器,就优先按照Comparator比较器来排序

#17.16_集合框架(练习)
案例演示Test4

    在一个集合中存储了无序并且重复的字符串,定义一个方法,让其有序(字典顺序),而且还不能去除重复
代码演示如下:
```
public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
	list.add("ccc");
	list.add("ccc");
	list.add("aaa");
	list.add("aaa");
	list.add("bbb");
	list.add("ddd");
	list.add("ddd");
	
	sort(list);
	System.out.println(list);
}

/*
 * 对集合中的元素排序,并保留重复
 */
public static void sort(List<String> list) {
	TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {//匿名内部类传入比较器实现类

	@Override
	public int compare(String s1, String s2) {//重写compare方法,
	        //int num = s2.compareTo(s1);//如果是倒序输出,应该这样反过来调用↓:
			int num = s1.compareTo(s2);//比较内容相等当返回0时就不存储重复,所以我们手动返回1,
			return num == 0 ? 1 : num;//或者非0的数,即可存储重复字符串元素
		}
	});
	
	ts.addAll(list);//将list集合中的所有元素添加到ts中
	list.clear();//清空list
	list.addAll(ts);//将ts中排序并保留重复的结果在添加到list中
}
```
总结:String类中复写的comparable接口的compareTo方法比较的是字典顺序,所以我们用比较器来解决,在compare方法里面,
我们比较字符串的内容如果等于0,我们就返回不等于0的数让字符串元素可以存储重复的来达到间接改写String类的目的

#17.17_集合框架(练习)
案例演示Test5

    从键盘接收一个字符串,程序对其中所有字符进行排序,例如键盘输入:helloitcast程序打印:acehillostt
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);//创建键盘录入对象
    System.out.println("请输入一行字符串:");
    String line = sc.nextLine();//将键盘录入的字符串存储在line中
    char[] arr = line.toCharArray();//将字符串转换成字符数组
    TreeSet<Character> ts = new TreeSet<>(new Comparator<Character>() {
    
    @Override
    	public int compare(Character c1, Character c2) {
    		//int num = c1.compareTo(c2);//这样可以,
    		int num = c1 - c2;//这样也可以,因为有自动拆箱
    		return num == 0 ? 1 : num;//返回不为0存储重复元素
    	}
    });
    
    for(char c : arr) {
    	ts.add(c);//自动装箱
    }
    
    for(Character ch : ts) {
    	System.out.print(ch);//acehillostt
    }
}
```
总结:跟上面的练习,其实考点是一样的,都是用比较器来改写系统写好的类的比较方法,来达到可存储重复元素和排序的目的

#17.18_集合框架(练习)
案例演示Test6

    程序启动后,可以从键盘输入接收多个整数,直到输入quit时结束输入,把所有输入的整数倒序排列打印
代码演示如下:
```
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);//创建键盘录入对象
	System.out.println("请输入:");
	TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {//将比较器传给TreeSet的构造方法

	@Override
		public int compare(Integer i1, Integer i2) {
			//int num = i2 - i1;//自动拆箱,倒序调用顺序反转过来,
			int num = i2.compareTo(i1);//八种数据类型包装类都重写了这个方法
			return num == 0 ? 1 : num;//这里返回1,还是想储存重复元素
		}
	});
	
	while(true) {//定义一个无限循环,
		String line = sc.nextLine();//将键盘录入的字符串存储在line中
		if("quit".equals(line))//如果字符串常量和变量比较,常量放前面,这样不会出现空指针异常
			break;
			
		try {
			int num = Integer.parseInt(line);//字符串接收解析成整数数字,存储到排序集合中,
			ts.add(num);//这里要注意Integer.parseInt(line);要求传入参数是数字字符串,否则报异常
		} catch (Exception e) {
			System.out.println("您录入的数据有误,请输入一个整数");
		}
		
	}
	
	for (Integer i : ts) {//遍历TreeSet集合元素输出
		System.out.println(i);//
	}
}
```
    总结:首先将数字字符串解析成数字,然后倒序调用:
	    public int compare(Integer i1, Integer i2) {
			//int num = i2 - i1;//自动拆箱,
			int num = i2.compareTo(i1);//这个跟上面一样,倒序调用顺序反转过来,
			return num == 0 ? 1 : num;//这里返回1,还是想储存重复元素
		}

#17.19_集合框架(键盘录入学生信息按照总分排序后输出在控制台)
A:案例演示Test7

    需求:键盘录入5个学生信息(姓名,语文成绩,数学成绩,英语成绩),按照总分从高到低输出到控制台:
代码演示如下:
```
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("请输入5个学生成绩格式是:(姓名,语文成绩,数学成绩,英语成绩)");
	TreeSet<Student> ts = new TreeSet<>(new Comparator<Student>() {
		@Override
		public int compare(Student s1, Student s2) {
			int num = s2.getSum() - s1.getSum();//根据学生的总成绩降序排列
			return num == 0 ? 1 : num;//返回1,为了保留重复总分元素
		}
	});
	
	while(ts.size() < 5) {
		String line = sc.nextLine();
		try {
			String[] arr = line.split(",");//键盘录入格式这样:姓名,语文成绩,数学成绩,英语成绩,
			int chinese = Integer.parseInt(arr[1]);//字符串切割逗号解析成语文成绩,
			int math = Integer.parseInt(arr[2]);//数学成绩,
			int english = Integer.parseInt(arr[3]);//英语成绩,
			ts.add(new Student(arr[0], chinese, math, english));//添加到排序集合中,
		} catch (Exception e) {
		System.out.println("录入格式有误,输入5个学生成绩格式是:(姓名,语文成绩,数学成绩,英语成绩");
		}
		
	}
	
	System.out.println("排序后的学生成绩是:");
	for (Student s : ts) {
		System.out.println(s);
	}
}

//另一个源文件中Student类的代码内容如下:
public class Student {
	private String name;
	private int chinese;
	private int math;
	private int english;
	private int sum;//总分
	
	public Student() {
		super();
		
	}
	public Student(String name, int chinese, int math, int english) {
		super();
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.sum = this.chinese + this.math + this.english;//总分赋值
	}
	
	public int getSum() {
		return sum;//返回总分
	}
	
	public String toString() {
		return name + "," + chinese + "," + math + "," + english + "," + sum;
	}
}
```

#17.20_day17总结
    把今天的知识点总结一遍:
    HashSet必须重写hashCode和equals方法来保证存储元素的唯一性-LinkedHashSet除了保证唯一还能怎么存就怎么取
    -TreeSet除了唯一还能排序-自然排序-比较器排序-两者都有优先用比较器排序
    

