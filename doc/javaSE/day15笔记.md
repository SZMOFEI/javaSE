#15.01_集合框架(对象数组的概述和使用)
A:案例演示:

    需求:我有5个学生,请把这个5个学生的信息存储到数组中,并遍历数组,
    获取得到每一个学生信息

代码演示如下:
```
public static void main(String[] args) {
    Student[] arr = new Student[5];	//存储学生对象
    arr[0] = new Student("张三", 23);
    arr[1] = new Student("李四", 24);
    arr[2] = new Student("王五", 25);
    
    for (int i = 0; i < arr.length; i++) {
	System.out.println(arr[i]);//如果Student类里没有重写toString方法,前三个输出是地址,后两个没有就是null
    
    }
}		
```

B:画图演示:见截图

    把学生数组的案例画图讲解
    数组和集合存储引用数据类型,存的都是地址值

总结:数组元素存储引用类型对象,存储的是对象的地址值,而不是这个对象,通过地址值找到对象

#15.02_集合框架(集合的由来及集合继承体系图)
###A:集合的由来:
    数组长度是固定,当添加的元素超过了数组的长度时需要对数组重新定义,太麻烦,
    java内部给我们提供了集合类,能存储任意对象,长度是可以改变的,随着集合元素的增加而增加,随着集合元素的减少而减少

###B:数组和集合的区别:
区别1:

    数组既可以存储基本数据类型,又可以存储引用数据类型,基本数据类型存储的是值,引用数据类型存储的是地址值

    集合只能存储引用数据类型(对象),集合中也可以存储基本数据类型,但是在存储的时候会自动装箱变成对象

区别2:

	数组长度是固定的,不能自动增长
	集合的长度的是可变的,可以根据元素的增加而增长

###C:数组和集合什么时候用:
	 1,如果元素个数是固定的,推荐用数组
	 2,如果元素个数不是固定的,推荐用集合

###D:集合继承体系图:见截图

#15.03_集合框架(Collection集合的基本功能测试)
A:案例演示:

    基本功能演示:
    boolean add(E e)//add方法,如果是List集合一直都返回true,因为List集合中是可以存储重复元素的;//如果是Set集合,当存储重复元素的时候,就会返回false
    
    boolean remove(Object o)//是否移除传入集合元素
    
    void clear()//清空集合元素
    
    boolean contains(Object o)//是否包含传入集合元素
    
    boolean isEmpty()//集合元素是否为空
    
    int size()//集合元素的个数

B:注意:

    collectionXxx.java使用了未经检查或不安全的操作,注意:要了解详细信息,请使用 -Xlint:unchecked重新编译,
    java编译器认为该程序存在安全隐患,温馨提示:这不是编译失败,所以先不用理会,等学了泛型你就知道了

代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    Collection c = new ArrayList();
    c.add("a");
    c.add("b");
    c.add("c");
    c.add("d");
    
    c.remove("b");//删除了b,删除指定元素
    c.clear();//清空集合元素
    System.out.println(c.contains("b"));//false,判断是否包含元素
    System.out.println(c.isEmpty());//true,集合元素是否为空
    System.out.println(c.size());//0,获取集合元素的个数
    System.out.println(c);//[],重写了toString方法
}

public static void demo1() {
    Collection c = new ArrayList();//父类引用指向子类对象 Collection 的两个子类 list set
    boolean b1 = c.add("abc");
    boolean b2 = c.add(true);//自动装箱new Boolean(true);
    boolean b3 = c.add(100);
    boolean b4 = c.add(new Student("张三",23));			
    boolean b5 = c.add("abc");
    
    System.out.println(b1);//true
    System.out.println(b2);//true
    System.out.println(b3);//true
    System.out.println(b4);//true
    System.out.println(b5);//true
    //ArrayList的父类的父类重写toString方法,所以输出的不是Object类中toString的结果
    System.out.println(c.toString());//[abc, true, 100, Student [name=张三, age=23], abc]
    //接口中的多态为什么不遵循编译看左边了呢?
    //通过c.提示调用你就知道,接口引用里面是会有Object类的所有方法的,所以不报错
}
```

#15.04_集合框架(集合的遍历之集合转数组遍历)
###A:集合的遍历:其实就是依次获取集合中的每一个元素

B:案例演示:

	把集合转成数组,可以实现集合的遍历
	用toArray()方法
代码演示如下:
```
public static void main(String[] args) {
    Collection c = new ArrayList();
    c.add(new Student("张三", 23));//Object obj = new Student("张三",23);
    c.add(new Student("李四", 24));
    c.add(new Student("王五", 25));
    c.add(new Student("赵六", 26));
    
    Object[] arr = c.toArray();//将集合转换成数组,遍历数组元素,
    for (int i = 0; i < arr.length; i++) {
    	Student s = (Student)arr[i];//向下转型,转成学生对象,
    	System.out.println(s.getName() + "..." + s.getAge());
    }
}
```

#15.05_集合框架(Collection集合的带All功能测试)
A:案例演示:

    带All的功能演示:	
    boolean addAll(Collection c)//将传入集合中的每一个元素,添加到调用该方法的集合中,是追加,不会覆盖原集合
    
    boolean removeAll(Collection c)//删除的是两个集合元素的交集
    
    boolean containsAll(Collection c)//集合中是否包含传入的集合元素,比如说你有我也有就行,不限个数
    
    boolean retainAll(Collection c)//取两个集合的交集,调用方法的集合改变就会返回true,否则返回false

总结:注意一个是删除交集,一个是取交集,取交集时调用方法集合改变才返回true

代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    
    Collection c1 = new ArrayList();
    c1.add("a");
    c1.add("b");
    c1.add("c");
    c1.add("d");
    
    Collection c2 = new ArrayList();
    c2.add("a");
    c2.add("b");
    c2.add("c");
    c2.add("d");
    c2.add("e");
    c2.add("f");//注意如果两个集合没有相同元素,那么交集为空,即调用者为[],改变了返回true
    
    //retainAll取交集,如果调用的集合改变就返回true,如果调用的集合不变就返回false
    boolean b = c1.retainAll(c2);//取交集,即a,b,c,d
    System.out.println(b);//false,调用的集合不变就返回false
    System.out.println(c1);//[a, b, c, d]
}

public static void demo3() {
    Collection c1 = new ArrayList();
    c1.add("a");
    c1.add("b");
    c1.add("c");
    c1.add("d");
    
    Collection c2 = new ArrayList();
    c2.add("a");
    c2.add("b");
    c2.add("z");//如果这里的z改成b,即还是包含的,针对的是集合元素有就可以,不限个数
    
    boolean b = c1.containsAll(c2);//集合c1中的元素是否包含有集合c2中的所有元素,有就可以,不限个数
    System.out.println(b);//false,不包含,因为集合c1中没有集合c2中的元素z
}

public static void demo2() {
    Collection c1 = new ArrayList();
    c1.add("a");
    c1.add("b");
    c1.add("c");
    c1.add("d");
    
    Collection c2 = new ArrayList();
    c2.add("a");
    c2.add("b");
    c2.add("z");
    
    boolean b = c1.removeAll(c2);//删除的是交集,即a,b两个元素
    System.out.println(b);//true,删除交集成功
    System.out.println(c1);//[c, d],
}

public static void demo1() {
    Collection c1 = new ArrayList();
    c1.add("a");
    c1.add("b");
    c1.add("c");
    c1.add("d");
    
    Collection c2 = new ArrayList();//alt + shift + r改名
    c2.add("a");
    c2.add("b");
    c2.add("c");
    c2.add("d");
    
    c1.addAll(c2);//将c2中的每一个元素添加到c1中,是在原集合中追加元素而不是覆盖,如下所示:↓
    System.out.println(c1);//[a, b, c, d, a, b, c, d]
    
    //c1.add(c2);//将c2看成一个对象添加到c1中,即添加的元素就是一个集合:
    //System.out.println(c2);//[a, b, c, d]
    //System.out.println(c1);//[a, b, c, d, [a, b, c, d]]
}
```

#15.06_集合框架(集合的遍历之迭代器遍历)
A:迭代器概述:

    集合是用来存储元素的,存储的元素需要查看,那么就需要迭代(遍历) 

B:案例演示:

    迭代器的使用
代码演示如下:
```
public static void main(String[] args) {
    Collection c = new ArrayList();
    c.add(new Student("张三", 23));//Object obj = new Student("张三",23);
    c.add(new Student("李四", 24));
    c.add(new Student("王五", 25));
    c.add(new Student("赵六", 26));//注释测试it.next()两次调用的报错后果
    
    //获取迭代器
    Iterator it = c.iterator();
    while(it.hasNext()) {
        //System.out.println(it.next());//it.next()两次调用会造成向下移动,如果下面没有就报错
        //System.out.println(((Student)(it.next())).getName() + "," + ((Student)(it.next())).getAge());
        
        Student s = (Student)it.next();//向下转型
        System.out.println(s.getName() + "..." + s.getAge());
    }
}
```

#15.07_集合框架(Collection存储自定义对象并遍历)
A:案例演示:

    Collection存储自定义对象并用迭代器遍历
代码演示如下:
```
public static void main(String[] args) {
    Collection c = new ArrayList();
    c.add(new Student("张三",23));
    c.add(new Student("李四",24));
    c.add(new Student("王五",25));
    c.add(new Student("赵六",26));
    c.add(new Student("赵六",26));
    
    for(Iterator it = c.iterator();it.hasNext();) {
    	Student s = (Student)it.next();//向下转型
    	System.out.println(s.getName() + "," + s.getAge());//获取对象中的姓名和年龄
    }
    System.out.println("-----------------分割线------------------);
    
    Iterator it = c.iterator();//获取迭代器
    while(it.hasNext()) {//判断集合中是否有元素,it.next()调用两次会向下移动,如果没有会报错,不能这样使用↓
    //System.out.println(((Student)(it.next())).getName() + "," + ((Student)(it.next())).getAge());
    
    	Student s = (Student)it.next();//向下转型
    	System.out.println(s.getName() + "," + s.getAge());//获取对象中的姓名和年龄
    }	
}
```

#15.08_集合框架(迭代器的原理及源码解析)(了解)
A:迭代器原理:

    迭代器原理:迭代器是对集合进行遍历,而每一个集合内部的存储结构都是不同的,
    所以每一个集合存和取都是不一样的,那么就需要在每一个类中都定义hasNext()和next()方法,这样做是可以的,
    但是会让整个集合体系过于臃肿,迭代器是将这样的方法向上抽取成接口,然后在每个类的内部,
    定义自己的迭代方式;这样做的好处有二,第一规定了整个集合体系的遍历方式都是hasNext()和next()方法,
    第二,代码有底层内部实现,使用者不用管怎么实现的,会用即可

B:迭代器源码解析:

    1,在eclipse中ctrl + shift + t找到ArrayList类
    2,ctrl+o查找iterator()方法
    3,查看方法返回值类型是new Itr(),说明Itr这个类实现了Iterator接口
    4,查找Itr这个内部类,发现重写了Iterator中的所有抽象方法

#15.09_集合框架(List集合的特有功能概述和测试)
A:案例演示:

    List集合的特有功能概述:
###void add(int index,E element)//这个方法其实是往指定索引位置插入元素,插入位置包括0和size()范围
	E remove(int index)//删除集合传入索引位置元素,返回删除掉的元素
	E get(int index)//传入索引得到集合元素
	E set(int index,E element)//设置传入索引的集合元素为传入值,超出索引范围会报索引越界异常
总结:只有插入的方法可以包括到size()范围,其他方法超过索引范围会报索引越界异常

代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    //demo2();
    //demo3();
    //demo4();
    
    List list = new ArrayList();
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.set(1, "z");
    //		list.set(4, "z");//将指定位置的元素修改,超出索引范围会报索引越界异常
    System.out.println(list);//[a, z, c, d]
}

public static void demo4() {
    List list = new ArrayList();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	
    //Object obj1 = list.get(2);
    //System.out.println(obj1);//c
    
	//通过索引遍历List集合
	for(int i = 0;i < list.size(); i++) {
		System.out.println(list.get(i));//传入索引得到集合元素,换行输出a,b,c,d
	}
}

public static void demo3() {
	List list = new ArrayList();
	list.add(111);
	list.add(222);
	list.add(333);
	
	list.remove(111);//注意,删除的时候不会自动装箱,111是索引,所以报错:
	System.out.println(list);// java.lang.IndexOutOfBoundsException: Index: 111, Size: 3
}

public static void demo2() {
	List list = new ArrayList();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	
	Object obj = list.remove(1);//通过索引删除元素,将被删除的元素返回
	System.out.println(obj);//b
	System.out.println(list);//[a, c, d]
}

public static void demo1() {
    List list = new ArrayList();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	list.add(4, "f");//index<=size并且index>=0都不会报异常,因为是插入的,打开源码就知道
    //list.add(1,"e");//往1位置插入e,原来的b往后退
	//list.add(10, "z");//java.lang.IndexOutOfBoundsException,当存储时使用不存在的索引时
	System.out.println(list);//[a, b, c, d, f]
}
```

#15.10_集合框架(List集合存储学生对象并遍历)
A:案例演示:
###通过size()和get()方法结合使用遍历:
代码演示如下:
```
public static void main(String[] args) {
    List list = new ArrayList();
	list.add(new Student("张三", 23));//Object obj = new Student("张三",23);
	list.add(new Student("李四", 24));
	list.add(new Student("王五", 25));
	list.add(new Student("赵六", 26));
	
	for(int i = 0; i < list.size(); i++) {
		//System.out.println(list.get(i));//通过索引获取每一个元素
		Student s = (Student)list.get(i);//向下转型为学生对象,再调用方法输出
		System.out.println(s.getName() + "..." + s.getAge());
	}
}
```

#15.11_集合框架(并发修改异常产生的原因及解决方案)
A:案例演示3:Demo3_List

    需求:我有一个集合,我想判断里面有没有"world"这个元素,如果有我就添加一个"javaee"元素,请写代码实现:
代码演示如下:
```
public static void main(String[] args) {
    List list = new ArrayList();
	list.add("a");
	list.add("b");
	list.add("world");
	list.add("c");
	list.add("d");
	list.add("e");
	
	Iterator it = list.iterator();
	while(it.hasNext()) {
		String str = (String)it.next();
		if(str.equals("world")) {
			list.add("javaee");//这里会抛出ConcurrentModificationException并发修改异常
		}
	}
}
```
###总结:迭代器遍历的同时用集合添加元素,会产生并发修改异常,解决方案是:集合遍历集合添加,或者迭代器遍历迭代器添加

B:ConcurrentModificationException出现:

    迭代器遍历,集合修改集合

C:解决方案:

	a:迭代器迭代元素,迭代器修改元素(ListIterator的特有功能add方法)
	b:集合遍历元素,集合修改元素

代码演示如下:
```
public static void main(String[] args) {
	//迭代器迭代元素,迭代器修改元素,用ListIterator的特有功能add方法:
	ListIterator lit = list.listIterator();//如果想在遍历的过程中添加元素,可以用ListIterator中的add方法
	
	while(lit.hasNext()) {
		String str = (String)lit.next();
		if(str.equals("world")) {//
			lit.add("javaee");//迭代器遍历迭代器添加,在调用next方法返回值之前插入,即c前插入元素	
			//list.add("javaee");
		}
	}

	//利用集合自己遍历,自己增删解决并发修改异常:
	for (int i = 0; i < list.size(); i++) {
		String str = (String) list.get(i);
		if ("world".equals(str)) {
			list.add("javaee");//集合遍历集合添加,集合末尾追加元素
//				lit.add("javaee");//
		}
	}
}
```

总结:用迭代器遍历集合元素,用集合删除元素时有可能会出现并发修改异常,是否出现关键在于迭代得到的元素个数与
最后的集合元素个数是否相同,如果相同就不会报并发修改异常,否则调用源码if(modCount != expectedModCount)throw new ConcurrentModificationException();

代码演示如下:
```
public class Work{
    public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("a");
		al.add("b");
		al.add("c");
		
		Iterator<String> iterator = al.iterator();
		while (iterator.hasNext()) {
String next = iterator.next();//输出的元素个数与操作后集合中的元素个数不相同就报并发修改异常
System.out.println(next);//例如这里输出a b c迭代得到的元素个数为3,下面添加了一个元素,最后的集合元素个数为4就报异常
			if (next.equals("c")) {
//				al.remove("b");//删除b后c往上移,所以没有下一个元素跳出循环
				al.add("c");//先移除后添加保证个数相同就没有问题
				
			}
		}
		
System.out.println(al);//总结:当next即迭代得到的元素个数与最后集合元素个数相同时就不会报并发修改异常
	}
}
```

#15.12_集合框架(ListIterator)(了解)
案例演示:

    boolean hasNext()//是否有下一个
    boolean hasPrevious()//是否有前一个
    Object next()//返回下一个元素
    Object previous()//返回上一个元素
总结:其实很简单,就是游标或者数组指针的类似概念,一开始是在索引0位置的上面,
使用要两两配对,要先后对后,才能调用前对前,另外不要乱配对,否则报错或什么也没有输出

代码演示如下:
```
public static void main(String[] args) {
    List list = new ArrayList();
    list.add("a");
    list.add("b");
    list.add("world");
    list.add("c");
    list.add("d");
    list.add("e");
    
    ListIterator lit = list.listIterator();//获取迭代器,
    //后后
    while(lit.hasNext()) {//有下一个元素才进来,
    	System.out.println(lit.next());//获取到这个元素,并将指针向后移动
    }
    System.out.println("要先后后调用,才能前前调用----------");
    //前前
    while(lit.hasPrevious()) {//有前一个元素才进来
        System.out.println(lit.previous());//获取这个元素,并将指针向前移动,所以要先后后配对调用,否则什么也没有输出,因为没有前一个元素
    }
    
    System.out.println("--------互相配对胡乱组合会报错没有这个元素或者没进来,所以什么也没有输出-------------------------------");
    
    //下,前
    //while(lit.hasNext()) {//有下一个元素才进来,
    //System.out.println(lit.previous());//获取前一个元素并将指针向前移动,但是没有前一个元素,所以会报Exception in thread "main" java.util.NoSuchElementException
    //}
    
    //前,下
    while(lit.hasPrevious()) {//有前一个元素才进来,
        System.out.println(lit.next());//获取下一个元素并将指针向后移动,因为没有前一个元素,所以不进来,没有输出
    }
}
```

#15.13_集合框架(Vector的特有功能)(了解)
A:Vector类概述:

B:Vector类特有功能:

    public void addElement(E obj)//添加集合元素
    public E elementAt(int index)//返回传入索引对应的集合元素
    public Enumeration elements()//获取枚举类对象

C:案例演示:

    Vector的迭代
代码演示如下:
```
public static void main(String[] args) {
    Vector v = new Vector();//创建集合对象,List的子类,
	v.addElement("a");//添加集合元素
	v.addElement("b");
	v.addElement("c");
	v.addElement("d");
	
	//Vector迭代
	Enumeration en = v.elements();//获取枚举对象,这里跟获取迭代器的用法很相似,可以一样记忆
	while(en.hasMoreElements()) {//枚举对象调用方法,判断集合中是否有元素
		System.out.println(en.nextElement());//枚举对象调用方法,获取集合中的元素
	}
}
```
总结:Vector是JDK1.0就有的,后来JDK1.2加入到了List接口门下,还有他底层也是数组,只不过线程同步安全,效率低

#15.14_集合框架(数据结构之数组和链表)
A:数组:

    查询快,修改也快
    增删慢

B:链表:

	查询慢,修改也慢
	增删快
总结:数组有索引查询的时候很快,增删元素的时候,后面的元素要向后或者向前移动,很慢;
而链表存储的是链表头和上下元素之间的地址值,例如查个中间值,要从头开始问起,很慢,而插入的时候,改变要插入位置的
前一个元素存储的地址和后一个元素存储的地址即可,很快,详情见截图

#15.15_集合框架(List的三个子类的特点)
A:List的三个子类的特点:

    ArrayList:
    	底层数据结构是数组,查询快,增删慢
    	线程不安全,效率高
    Vector:
    	底层数据结构是数组,查询快,增删慢
    	线程安全,效率低
    Vector相对ArrayList查询慢(线程安全的)
    
    Vector相对LinkedList增删慢(数组结构)
    LinkedList:
    	底层数据结构是链表,查询慢,增删快
    	线程不安全,效率高
    
    Vector和ArrayList的区别:
    	Vector是线程安全的,效率低
    	ArrayList是线程不安全的,效率高
    共同点:都是数组实现的
    
    ArrayList和LinkedList的区别:
    	ArrayList底层是数组结构,查询和修改快
    	LinkedList底层是链表结构的,增和删比较快,查询和修改比较慢
    共同点:都是线程不安全的

B:List有三个儿子,我们到底使用谁呢?

    查询多,用ArrayList
    增删多,用LinkedList
    如果都,多用ArrayList

#15.16_day15总结
    把今天的知识点总结一遍:
    集合的由来以及继承体系-Collection集合的基本功能-集合的遍历以及并发修改异常-List集合的特有功能以及特有迭代器-
    Vector的特有功能-数据结构之数组和链表-List的三个子类的特点


