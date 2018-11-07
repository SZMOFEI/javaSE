#18.01_集合框架(Map集合概述和特点)
A:Map接口概述:双列

    查看API可以知道:
    将键映射到值的对象,
    一个映射不能包含重复的键,
    每个键最多只能映射到一个值

B:Map接口和Collection接口的不同:

    Map是双列的,Collection是单列的
	Map的键唯一,Collection的子体系Set是唯一的
	Map集合的数据结构值针对键有效,跟值无关;Collection集合的数据结构是针对元素有效
	
#18.02_集合框架(Map集合的功能概述)
A:Map集合的功能概述:

    a:添加功能:
        V put(K key,V value):添加元素,
		如果键是第一次存储,就直接存储元素,返回null,
		如果键不是第一次存储,就用值把以前的值替换掉,返回以前的值

	b:删除功能:
		void clear():移除所有的键值对元素
		V remove(Object key):根据键删除键值对元素,并把值返回
	
	c:判断功能:
		boolean containsKey(Object key):判断集合是否包含指定的键
		boolean containsValue(Object value):判断集合是否包含指定的值
		boolean isEmpty():判断集合是否为空

	d:获取功能:
		Set<Map.Entry<K,V>> entrySet()://获取集合中所有键值对的集合
		V get(Object key):根据键获取值,这个方法要注意,如果我们用比较器返回1,那么键对应的值为null
		Set<K> keySet():获取集合中所有键的集合
		Collection<V> values():获取集合中所有值的集合

	e:长度功能:
		int size():返回集合中的键值对的个数
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    
    Map<String, Integer> map = new HashMap<>();
    map.put("张三", 23);
    map.put("李四", 24);
    map.put("王五", 25);
    map.put("赵六", 26);
    
    Collection<Integer> c = map.values();
    System.out.println(c);//[26, 23, 24, 25],改写了object类继承过来的toString方法
    System.out.println(map.size());//4
}

public static void demo2() {
	Map<String, Integer> map = new HashMap<>();
	map.put("张三", 23);
	map.put("李四", 24);
	map.put("王五", 25);
	map.put("赵六", 26);
	
	Integer value = map.remove("张三");//根据键删除元素,返回键对应的值
	System.out.println(value);//23
	System.out.println(map.containsKey("张三"));//false,判断是否包含传入的键
	System.out.println(map.containsValue(100));//false,判断是否包含传入的值
	System.out.println(map);//{赵六=26, 李四=24, 王五=25}
}

public static void demo1() {
	Map<String, Integer> map = new HashMap<>();
	Integer i1 = map.put("张三", 23);//如果键是第一次存储,就直接存储元素,返回null
	Integer i2= map.put("李四", 24);
	Integer i3 = map.put("王五", 25);
	Integer i4 = map.put("赵六", 26);
	Integer i5 = map.put("张三", 26);//相同的键不存储,值覆盖,把被覆盖的值返回
	
	System.out.println(map);//{赵六=26, 张三=26, 李四=24, 王五=25}
	
	System.out.println(i1);//null
	System.out.println(i2);//null
	System.out.println(i3);//null
	System.out.println(i4);//null
	System.out.println(i5);//23
}
```
总结:V get(Object key):根据键获取值,这个方法要注意,如果我们用比较器返回1,那么键对应的值为null,为什么看其源码,
是如何返回null的就知道了:
代码演示如下:
```
public class TestGetValueByKeyMethod {
	public static void main(String[] args) {
		
	    TreeMap<Integer, Integer> tm = new TreeMap<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
//				return 0;
				return 1;///表示前后比较的两者,前者比后者大,其实相当于前者减去后者的值
//				return -1;
			}
		});
		
		tm.put(1, 2);
		tm.put(2, 3);
		tm.put(3, 4);
		tm.put(5, 6);
		tm.put(7, 9);
		tm.put(5, 56);
		tm.put(4, 44);
		
        Iterator<Integer> iterator = tm.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = tm.get(key);//通过键返回值方法的返回值有问题,看源码知道如果比较器返回1会返回null作为键的值
            System.out.print(key+"="+value+",");//1=null,2=null,3=null,5=null,7=null,5=null,4=null,
        }
        
        System.out.println("----------分割线--------");

//如何解决,通过map的第二种方式即得到键值对集合的方式来解决:
        Iterator<Entry<Integer, Integer>> iterator2 = tm.entrySet().iterator();
        while (iterator2.hasNext()) {
        	Entry<Integer, Integer> entry = iterator2.next();
        	Object key = entry.getKey();
        	Object value = entry.getValue();
        	System.out.print(key+"="+value+",");//1=2,2=3,3=4,5=6,7=9,5=56,4=44,
        }		
    }
}
```

#18.03_集合框架(Map集合的遍历之键找值)
A:键找值思路:

    获取所有键的集合,
	遍历键的集合,获取到每一个键,
	根据键找值

B:案例演示:

    Map集合的遍历之键找值:
代码演示如下:
```
public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<>();
    map.put("张三", 23);
    map.put("李四", 24);
    map.put("王五", 25);
    map.put("赵六", 26);
    
    Integer i = map.get("张三");//根据键获取值
    System.out.println(i);//23
    
    //获取所有的键
    Set<String> keySet = map.keySet();//获取所有键的集合,
    Iterator<String> it = keySet.iterator();//用键的集合获取迭代器,
    while(it.hasNext()) {//判断集合中是否有元素,
    	String key = it.next();//获取集合中的每一个键,
    	Integer value = map.get(key);//根据键获取值,
    	System.out.print(key + "=" + value);//赵六=26张三=23李四=24王五=25
    }
    System.out.println("通过迭代器遍历的,也可以通过增强for遍历");
    
    //使用增强for循环遍历
    for(String key : map.keySet()) {//map.keySet()是所有键的集合
    	System.out.print(key + "=" + map.get(key));//赵六=26张三=23李四=24王五=25
    }
}
```
	
#18.04_集合框架(Map集合的遍历之键值对对象找键和值)
A:键值对对象找键和值思路:

    获取所有键值对对象的集合,
	遍历键值对对象的集合,获取到每一个键值对对象,
	根据键值对对象找键和值

B:案例演示:

    Map集合的遍历之键值对对象找键和值:
代码演示如下:
```
public static void main(String[] args) {
	Map<String, Integer> map = new HashMap<>();
	map.put("张三", 23);
	map.put("李四", 24);
	map.put("王五", 25);
	map.put("赵六", 26);

	//Map.Entry说明Entry是Map的内部接口,将键和值封装成了Entry对象,并存储在Set集合中
	Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
	Iterator<Map.Entry<String, Integer>> it = entrySet.iterator();//集合获取迭代器

	while(it.hasNext()) {
		//获取的是子类对象,这里Entry类是Map接口的内部接口Map.Entry的实现类
		//Entry<String, Integer> en = it.next();

		//获取每一个Entry对象,
		Map.Entry<String, Integer> en = it.next();//父类引用指向子类对象,多态
		String key = en.getKey();//根据键值对对象获取键,
		Integer value = en.getValue();//根据键值对对象获取值
		System.out.print(key + "=" + value);//
	}

	//通过增强for循环来遍历
	for(Entry<String, Integer> en : map.entrySet()) {
	//不写Map.Entry,写Entry也可这是因为Entry类是Map接口的内部接口Map.Entry的实现类
		System.out.print(en.getKey() + "=" + en.getValue());//
	}
}
```

C:源码分析:见截图,其实也是说明Entry是类并且是Map接口的内部接口Map.Entry的实现类:

    总结:不写Map.Entry,直接写Entry也可以的原因:这里Entry是类并且是Map接口的内部接口Map.Entry的实现类

#18.05_集合框架(HashMap集合键是Student值是String的案例)
A:案例演示:

    HashMap集合键是Student,值是String的案例:
代码演示如下:
```
public static void main(String[] args) {
    HashMap<Student, String> hm = new HashMap<>();
    hm.put(new Student("张三", 23), "北京");//自定义类Student没有重写hashCode方法和equals方法,无法保证键是唯一的,
	hm.put(new Student("张三", 23), "上海");//这时,这个键值对也会存储进来,
	hm.put(new Student("李四", 24), "广州");
	hm.put(new Student("王五", 25), "深圳");
	
	System.out.println(hm);//
}

//另一个源文件中Student类的代码如下:
public class Student {
	private String name;
	private int age;
    //Alt+shift+S分别按c,o,r,s,h生成无参构造,有参构造,setter和getter方法,toString方法,hashCode和equals方法
}
```
总结:要保证键是自定义类型时的键唯一,需要自定义类重写hashCode方法和equals方法,原理跟hashSet是一样的

#18.06_集合框架(LinkedHashMap的概述和使用)
A:案例演示:

    LinkedHashMap的特点:
	底层是链表实现的,可以保证怎么存就怎么取:
代码演示如下:
```
public static void main(String[] args) {
    LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
    lhm.put("张三", 23);
    lhm.put("李四", 24);
    lhm.put("赵六", 26);
    lhm.put("王五", 25);
    
    System.out.println(lhm);//怎么存就怎么取:{张三=23, 李四=24, 赵六=26, 王五=25}
}
```

#18.07_集合框架(TreeMap集合键是Student值是String的案例)
A:案例演示:

    TreeMap集合键是Student值是String的案例:
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    TreeMap<Student, String> tm = new TreeMap<>(new Comparator<Student>() {
    
    @Override
        public int compare(Student s1, Student s2) {
        	int num = s1.getName().compareTo(s2.getName());//按照姓名比较
        	return num == 0 ? s1.getAge() - s2.getAge() : num;
        }
    });
    
    tm.put(new Student("张三", 23), "北京");
    tm.put(new Student("李四", 13), "上海");
    tm.put(new Student("赵六", 43), "深圳");
    tm.put(new Student("王五", 33), "广州");
    //按照姓名为主要条件,
    System.out.println(tm);//
    //{Student [name=张三, age=23]=北京, Student [name=李四, age=13]=上海, ...
}

public static void demo1() {
	TreeMap<Student, String> tm = new TreeMap<>();
	tm.put(new Student("张三", 23), "北京");
	tm.put(new Student("李四", 13), "上海");
	tm.put(new Student("王五", 33), "广州");
	tm.put(new Student("赵六", 43), "深圳");
	//以年龄为主要条件,
	System.out.println(tm);//
	//{Student [name=李四, age=13]=上海, Student [name=张三, age=23]=北京, ...
}

//另一个源文件中Student类的代码如下:
public class Student implements Comparable<Student> {
	private String name;
	private int age;
//Alt+shift+S分别按c,o,r,s,h生成无参构造,有参构造,setter和getter方法,toString方法,hashCode和equals方法

	@Override//复写可比较接口方法
	public int compareTo(Student o) {
//		int num = o.age - this.age;//以年龄为主要条件,倒序输出

		int num = this.age - o.age;//以年龄为主要条件,
		return num == 0 ? this.name.compareTo(o.name) : num;//姓名为次要条件
	}
}
```
    总结:跟TreeSet一样,这里针对的是键,当键是自定义类型Student时,必须实现Comparable接口并复写接口方法来比较,或者
    在创建TreeMap对象时通过有参构造方法传入比较器接口并复写接口方法来比较排序,如果上面两个都没有会报错类型转换
    异常:自定义类Student不能转换到Comparable,如果两个都有,优先使用的是比较器接口

#18.08_集合框架(统计字符串中每个字符出现的次数)
A:案例演示:

    需求:统计字符串中每个字符出现的次数:
代码演示如下:
```
public static void main(String[] args) {
	String str = "aaaabbbcccccccccc";
	char[] arr = str.toCharArray();//将字符串转换成字符数组
	HashMap<Character, Integer> hm = new HashMap<>();//创建双列集合存储键和值
	
	for(char c : arr) {//遍历字符数组
		if(!hm.containsKey(c)) {//如果不包含这个键,就将键和值为1添加
			hm.put(c, 1);
		}else {//如果包含这个键,就将键和值再加1添加进来,
			hm.put(c, hm.get(c) + 1);
		}
		
		//hm.put(c, !hm.containsKey(c) ? 1 : hm.get(c) + 1);
		//Integer i = !hm.containsKey(c) ? hm.put(c, 1) : hm.put(c, hm.get(c) + 1);
	}
	
	for (Character key : hm.keySet()) {//遍历键的集合得到键,通过键获取值
		System.out.print(key + "=" + hm.get(key));//b=3c=10a=4
	}
}
```
总结:字符串转字符数组,遍历字符数组就可以得到每一个字符,判断字符是否在双列集合中,不在就存储键为字符,值为1,
在就存储键为字符,值为键对应值再加1,最后遍历双列集合,获取值就是字符出现在字符串中的个数

#18.09_集合框架(集合嵌套之HashMap嵌套HashMap)
A:案例演示:

    集合嵌套之HashMap嵌套HashMap,也就是一个双列集合的键是一个双列集合的情况下的集合的遍历:
代码演示如下:
```
public static void main(String[] args) {
    //定义88期基础班
	HashMap<Student, String> hm88 = new HashMap<>();
	hm88.put(new Student("张三", 23), "北京");
	hm88.put(new Student("李四", 24), "北京");
	hm88.put(new Student("王五", 25), "上海");
	hm88.put(new Student("赵六", 26), "广州");
	
	//定义99期基础班
	HashMap<Student, String> hm99 = new HashMap<>();
	hm99.put(new Student("唐僧", 1023), "北京");
	hm99.put(new Student("孙悟空",1024), "北京");
	hm99.put(new Student("猪八戒",1025), "上海");
	hm99.put(new Student("沙和尚",1026), "广州");
	
	//定义双元课堂
	HashMap<HashMap<Student, String>, String> hm = new HashMap<>();
	hm.put(hm88, "第88期基础班");
	hm.put(hm99, "第99期基础班");
	
	//遍历双列集合
	for(HashMap<Student, String> h : hm.keySet()) {//hm.keySet()代表的是双列集合中键的集合
		String value = hm.get(h);//get(h)根据键对象获取值对象
		//遍历键的双列集合对象
		for(Student key : h.keySet()) {//h.keySet()获取集合中所有的学生键对象
			String value2 = h.get(key);
			
			System.out.println(key + "=" + value2 + "=" + value);
		}
	}
}
```
总结:挺简单的,就是创建一个双列集合A的键是一个双列集合B的情况下的集合的遍历,说白了还是考了双列集合的遍历方式

#18.10_集合框架(HashMap和Hashtable的区别)
A:面试题:

    共同点:底层都是哈希算法,都是双列集合,

	HashMap和Hashtable的区别:
	Hashtable是JDK1.0版本出现的,是线程安全的,效率低,HashMap是JDK1.2版本出现的,是线程不安全的,效率高
	Hashtable不可以存储null键和null值,HashMap可以存储null键和null值
	
B:案例演示:

    HashMap和Hashtable的区别:
    总结:Hashtable看名字就知道命名不规范,是较早时JDK1.0版本出现的,是线程安全的,效率低,不可以存储null键和null值
    HashMap看名字就知道是较后的JDK1.2版本出现的,它刚好与Hashtable的特点相反,有一种后来补充的感觉
代码演示如下:
```
public static void main(String[] args) {
	//HashMap可以存储null键和null值:↓
	HashMap<String, Integer> hm = new HashMap<>();
	hm.put(null, 23);
	hm.put("李四", null);
	System.out.println(hm);//可以输出:{null=23, 李四=null}
	
	//Hashtable不可以存储null键和null值:↓
	Hashtable<String, Integer> ht = new Hashtable<>();
	ht.put(null, 23);//编译时都不报错,运行时出现空指针异常NullPointerException
	ht.put("张三", null);//编译时都不报错,运行时出现空指针异常NullPointerException
	System.out.println(ht);
}
```

#18.11_集合框架(Collections工具类的概述和常见方法讲解)
A:Collections类概述:

    针对集合操作的工具类
    
B:Collections成员方法:

    //对List集合元素进行排序,集合元素必须实现可比较接口,底层依赖的是Arrays工具类的sort排序方法:
	public static <T> void sort(List<T> list)
	代码演示如下:
	ArrayList<String> list = new ArrayList<>();
	list.add("c");
	list.add("a");
	list.add("a");
	list.add("b");
	list.add("d");

	System.out.println(list);//[c, a, a, b, d]
	Collections.sort(list);	//将集合排序
	System.out.println(list);//[a, a, b, c, d]

	public static <T> int binarySearch(List<?> list,T key)//找到返回对应索引,找不到返回-插入点索引-1
	代码演示如下:
	ArrayList<String> list = new ArrayList<>();
	list.add("a");
	list.add("c");
	list.add("d");
	list.add("f");
	list.add("g");
	System.out.println(Collections.binarySearch(list, "c"));//查找到就返回对应的索引,这里是1

	System.out.println(Collections.binarySearch(list, "b"));
	//查找不到,但是b按顺序应该插入到a索引的下一个索引即1的位置,返回的是-插入点-1即-1-1=-2

	public static <T> T max(Collection<?> coll)//根据默认排序结果获取集合中的元素最大值
	代码演示如下:
	ArrayList<String> list = new ArrayList<>();//这里String类默认是字典排序
	list.add("a");
	list.add("c");
	list.add("d");
	list.add("g");
	list.add("f");
	System.out.println(Collections.max(list));//输出g,根据默认排序结果获取集合中的元素最大值

	public static void reverse(List<?> list)//对集合元素进行反转,即倒转过来
	代码演示如下:
	ArrayList<String> list = new ArrayList<>();
	list.add("a");
	list.add("c");
	list.add("d");
	list.add("g");
	list.add("f");
	Collections.reverse(list);//对集合元素进行反转,即倒转过来:
	System.out.println(list);//[f, g, d, c, a]

	public static void shuffle(List<?> list)//对集合元素随机置换,可以用来模拟洗牌功能,还有随机播放等
	代码演示如下:
	ArrayList<String> list = new ArrayList<>();
	list.add("a");
	list.add("c");
	list.add("d");
	list.add("g");
	list.add("f");
	Collections.shuffle(list);//随机置换,可以用来模拟洗牌功能:
	System.out.println(list);//多次运行结果随机,如第一次是[g, f, a, d, c],第二次是[a, g, c, d, f]

#18.12_集合框架(模拟斗地主洗牌和发牌)
A:案例演示:

    模拟斗地主洗牌和发牌,牌没有排序:
代码演示如下:
```
public static void main(String[] args) {
	//1,买一副扑克,其实就是自己创建一个集合对象,将扑克牌存储进去
	String[] num = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};//数字字符串数组
	String[] color = {"红桃","黑桃","方片","梅花"};//花色字符串数组
	ArrayList<String> poker = new ArrayList<>();//扑克集合
	
	//拼接花色和数字
	for(String s1 : color) {//遍历花色,遍历数字,把得到的花色和数字通过方法拼接,添加到扑克集合中
		for(String s2 : num) {
			poker.add(s1.concat(s2));//concat连接两个字符串方法
		}
	}
	poker.add("小王");//别忘了添加大王和小王
	poker.add("大王");

	//2,洗牌
	Collections.shuffle(poker);//将扑克集合元素随机置换,模拟洗牌

	//3,发牌
	ArrayList<String> gaojin = new ArrayList<>();//创建人集合和底牌集合
	ArrayList<String> longwu = new ArrayList<>();
	ArrayList<String> me = new ArrayList<>();
	ArrayList<String> dipai = new ArrayList<>();//底牌
	
	for(int i = 0; i < poker.size(); i++) {//遍历扑克集合元素,分别判断条件,添加到不同人集合中
		if(i >= poker.size() - 3) {//将三张底牌存储在底牌集合中
			dipai.add(poker.get(i));
		} else if(i % 3 == 0) {
			gaojin.add(poker.get(i));//1,3,9张牌发高进
		} else if(i % 3 == 1) {
			longwu.add(poker.get(i));
		} else {
			me.add(poker.get(i));
		}
	}
	
	//4,看牌,就是查看人集合中的元素,集合复写了toString方法,所以打印集合名字即可
	System.out.println(gaojin);
	System.out.println(longwu);
	System.out.println(me);
	System.out.println(dipai);
}
```

#18.13_集合框架(模拟斗地主洗牌和发牌并对牌进行排序的原理图解)
A:画图演示:

    画图说明排序原理,见截图

#18.14_集合框架(模拟斗地主洗牌和发牌并对牌进行排序的代码实现)
A:案例演示:

    模拟斗地主洗牌和发牌,并对牌进行排序的代码实现:
代码演示如下:
```
public static void main(String[] args) {
	//1,买一副扑克,其实就是自己创建一个集合对象,将扑克牌存储进去
	String[] num = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};//数字字符串数组
	String[] color = {"红桃","黑桃","方片","梅花"};//花色字符串数组
	HashMap<Integer, String> hm = new HashMap<>();//存储索引和扑克牌的双列集合
	ArrayList<Integer> list = new ArrayList<>();//存储索引
	int index = 0;//双列集合的索引即键
	
	//拼接扑克牌并索引和扑克牌存储在hm中
	for(String s1 : num) {//遍历数字的同时遍历花色,然后花色拼接数组作为值添加到双列集合中											//获取数字
		for(String s2 : color) {									//获取颜色
			hm.put(index, s2.concat(s1));//存储索引和拼接的扑克牌
			list.add(index);//不要忘了把双列集合的键存储在list集合中,用于随机打乱洗牌									//将索引0到51添加到list集合中
			index++;//索引自增
		}
	}
	
	//将小王添加到双列集合中
	hm.put(index, "小王");//52,小王
	list.add(index);												    //将52索引添加到集合中
	index++;//索引凑够53,给大王
	hm.put(index, "大王");//53,大王
	list.add(index);												    //将52索引添加到集合中
	
	//2,洗牌
	Collections.shuffle(list);//随机置换打乱双列集合的键,来模拟洗牌

	//3,发牌
	TreeSet<Integer> gaojin = new TreeSet<>();//创建可排序的人集合和底牌集合
	TreeSet<Integer> longwu = new TreeSet<>();
	TreeSet<Integer> me = new TreeSet<>();
	TreeSet<Integer> dipai = new TreeSet<>();//底牌集合
	
	for(int i = 0; i < list.size(); i++) {//遍历集合中存储的元素即双列集合的键,
		if(i >= list.size() - 3) {//判断条件添加元素即键到排序集合中,
			dipai.add(list.get(i));//将三张底牌存储在底牌集合中
		}else if(i % 3 == 0) {
			gaojin.add(list.get(i));
		}else if(i % 3 == 1) {
			longwu.add(list.get(i));
		}else {
			me.add(list.get(i));
		}
	}
	
	//看牌,就是遍历排序集合中元素即键,然后通过键找到值输出结果
	lookPoker(hm, gaojin, "高进");
	lookPoker(hm, longwu, "龙五");
	lookPoker(hm, me, "冯佳");
	lookPoker(hm, dipai, "底牌");
}

/**
 * 看牌
 * 1,返回值类型void
 * 2,参数列表HashMap,TreeSet,String name
 */
public static void lookPoker(HashMap<Integer, String> hm,TreeSet<Integer> ts ,String name) {
	System.out.print(name + "的牌是:");
	for(Integer i : ts) {//i代表双列集合中的每一个键，
		System.out.print(hm.get(i) + " ");//根据键获取值
	}
	System.out.println();
}
```

#18.15_集合框架(泛型固定下边界)
? super E

    总结:? super E,其实就是E类及其父类的意思:
代码演示如下:
```
public class Demo2_Genric {
	public static void main(String[] args) {
		//? extends E 代表E类及其子类的意思
		demo1();
		
		//? super E 代表E类及其父类的意思
		//java.util.TreeSet.TreeSet<Student>(Comparator<? super Student> comparator)
		TreeSet<Student> ts1 = new TreeSet<>(new CompareByAge());

		ts1.add(new Student("张三", 33));
		ts1.add(new Student("李四", 13));
		ts1.add(new Student("王五", 23));
		ts1.add(new Student("赵六", 43));
		
		TreeSet<BaseStudent> ts2 = new TreeSet<>(new CompareByAge());
		ts2.add(new BaseStudent("张三", 33));
		ts2.add(new BaseStudent("李四", 13));
		ts2.add(new BaseStudent("王五", 23));
		ts2.add(new BaseStudent("赵六", 43));//不能添加Student
		
		System.out.println(ts2);//[Student [name=李四, age=13], Student [name=王五, age=23],...
		System.out.println("? super E 代表E类及其父类的意思");
	}

	public static void demo1() {
		ArrayList<Student> list1 = new ArrayList<>();
		list1.add(new Student("张三", 23));
		list1.add(new Student("李四", 24));
		
		ArrayList<BaseStudent> list2 = new ArrayList<>();
		list2.add(new BaseStudent("王五", 25));
		list2.add(new BaseStudent("赵六", 26));
		
//		ArrayList<Object> list3 = new ArrayList<>();
//		list1.addAll(list3);//无法添加是因为:不是学生类及其子类
		list1.addAll(list2);
		//addAll(Collection<? extends Student> c)添加的集合存储的只能是Student类及其子类
		System.out.println("? extends E 代表E类及其子类的意思");
	}

}

class CompareByAge implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		int num = s1.getAge() - s2.getAge();//年龄为主要条件,姓名为次要条件
		return num == 0 ? s1.getName().compareTo(s2.getName()) :  num;
	}
}
```
总结:addAll方法的? extends E是把基本学生对象放进学生类集合里,相当于父类引用指向子类对象,可以,
而比较器的? super E是把基本学生对象拿出来放到学生类比较器去排序,而接收的也是学生类,也是父类引用指向子类对象

#18.16_day18总结
    把今天的知识点总结一遍:
    Map集合概述及其功能-Map集合的两种遍历方式-HashMap当存储的键是自定义类时需要该类重写hashCode和equals方法
    -LinkedHashMap怎么存就怎么取-TreeMap当存储的键是自定义类时需要该类实现Comparable接口并实现接口方法,
    或者通过有参构造方法来传入Comparator比较器接口并实现接口方法-Collections工具类的排序,二分查找前提要有序,求最值,
    元素反转,随机置换功能-模拟斗地主洗牌发牌并把牌排序

