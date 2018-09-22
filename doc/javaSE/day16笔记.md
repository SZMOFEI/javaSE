#16.01_集合框架(去除ArrayList中重复字符串元素方式)(掌握)
A:案例演示Demo1_ArrayList

    需求:ArrayList去除集合中字符串的重复值(字符串的内容相同)
###思路:创建新集合方式,看其是否包含旧集合元素
代码演示如下:
```
public static void main(String[] args) {
    ArrayList list = new ArrayList();
	list.add("a");
	list.add("a");
	list.add("b");
	list.add("b");
	list.add("b");
	list.add("c");
	list.add("c");
	list.add("c");
	list.add("c");
	
	System.out.println(list);//[a, a, b, b, b, c, c, c, c]
	ArrayList newList = getSingle(list);
	System.out.println(newList);//[a, b, c]
}

public static ArrayList getSingle(ArrayList list) {
    ArrayList newList = new ArrayList();//创建一个新集合,
	Iterator it = list.iterator();//传入集合即老集合,获取迭代器遍历集合元素,
	while(it.hasNext()) {//判断老集合中是否有元素,如果有,
		String temp = (String)it.next();//将每一个老集合元素临时记录住,
		if(!newList.contains(temp)) {//看新集合中是否包含老集合元素,如果没有,
			newList.add(temp);//就将老集合元素添加到新集合中,
		}
	}
	return newList;//返回新集合
}
```

#16.02_集合框架(去除ArrayList中重复自定义对象元素)(掌握)
案例演示Demo2_ArrayList

    关于需求:ArrayList去除集合中自定义对象元素的重复值,如人的姓名和年龄相同,就认为是同一个元素不能重复添加,
    直接通过创建新集合的方法或者删除元素,都不能解决重复问题:
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(new Person("张三", 23));
		list.add(new Person("张三", 23));
		list.add(new Person("李四", 24));
		list.add(new Person("李四", 24));
		list.add(new Person("李四", 24));
		list.add(new Person("李四", 24));

		ArrayList newList = getSingle(list);
		System.out.println(newList);

		//list.remove(new Person("张三", 23));//说明remove方法没有起作用,那看源码看其底层依赖的是什么
		//System.out.println(list);
	}
	
	public static ArrayList getSingle(ArrayList list) {
		ArrayList newList = new ArrayList<>();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			if(!newList.contains(obj)) {//说明contains方法没有起作用,那看源码看其底层依赖的是什么
				newList.add(obj);
			}
		}
		return newList;
	}
	
可以通过下面两种方式解决:

###A.contains方法判断是否包含,底层依赖的是equals方法,需要在自定义类Person中重写equals方法,重写的代码为:
    @Override
    	public boolean equals(Object obj) {
    		Person p = (Person)obj;
    		return this.name.equals(p.name) && this.age == p.age;
    	}

###B.remove方法判断是否删除,底层依赖的也是equals方法,需要在自定义类Person中重写equals方法,重写的代码为:
    @Override
    	public boolean equals(Object obj) {
    		Person p = (Person)obj;
    		return this.name.equals(p.name) && this.age == p.age;
    	}
总结:在自定义类中调用contains方法和remove方法不起作用,其底层依赖的都是equals方法当其返回true时,方法才起作用,
而系统提供的类String里调用却起作用,说明其里面重写了Object类的equals方法

#16.03_集合框架(LinkedList的特有功能)(掌握)
案例演示Demo3_LinkedList

A:LinkedList类概述

B:LinkedList类特有功能:

    public void addFirst(E e)及addLast(E e)
	public E getFirst()及getLast()
	public E removeFirst()及public E removeLast()//移除谁,返回谁,其他方法从字面上理解就可以了
	public E get(int index);
	
代码演示如下:
```
public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.addFirst("a");
    list.addFirst("b");
    list.addFirst("c");
    list.addFirst("d");
    list.addLast("e");//dcbae
    
    //System.out.println(list.getFirst());//d
    //System.out.println(list.getLast());//e
    //System.out.println(list.removeFirst());//d,移除谁返回谁
    //System.out.println(list.removeLast());//e,移除谁返回谁
    
    System.out.println(list.get(0));//d,集合中的索引为0对应的元素即第一个元素
    System.out.println(list);//[d, c, b, a, e],集合输出增加了中括号逗号和空格
}
```

#16.04_集合框架(栈和队列数据结构)(掌握)
栈:

    先进后出,如子弹夹装子弹
    
队列:

	先进先出,如排队,先排的先出

#16.05_集合框架(用LinkedList模拟栈数据结构的集合并测试)(掌握)
A:案例演示Demo4_LinkedList

    需求：请用LinkedList模拟栈数据结构的集合,并测试
	创建一个类将Linked中的方法封装
代码演示如下:
```
public class Stack {
    private LinkedList list = new LinkedList();//创建LinkedList对象
	
	public void in(Object obj) {//模拟进栈
		list.addLast(obj);//封装addLast()方法
	}
	
	public Object out() {//模拟弹栈
		return list.removeLast();//封装removeLast()方法
	}
	
	public boolean isEmpty() {//模拟栈结构是否为空
		return list.isEmpty();//封装isEmpty()方法
	}
}

public class Demo4_LinkedList {//测试类
	public static void main(String[] args) {
//	    demo1();

		Stack s = new Stack();
		s.in("a");//进栈
		s.in("b");
		s.in("c");
		s.in("d");
		
		while(!s.isEmpty()) {//判断栈结构是否为空
			System.out.println(s.out());//弹栈
		}
	}

	public static void demo1() {//模拟栈结构,先进后出
		LinkedList list = new LinkedList();//创建集合对象
		list.addLast("a");
		list.addLast("b");
		list.addLast("c");
		list.addLast("d");//abcd
		
//		System.out.println(list.removeLast());//d
//		System.out.println(list.removeLast());//c
//		System.out.println(list.removeLast());//b
//		System.out.println(list.removeLast());//a
		
		while(!list.isEmpty()) {
			System.out.println(list.removeLast());//
		}
	}
}
```

#16.06_集合框架(泛型概述和基本使用)(掌握)
案例演示Demo1_Generic
A:泛型概述:

B:泛型好处,见截图:

	提高安全性(将运行期的错误转换到编译期)//运行时异常如类型转换异常ClassCastException
	省去强转的麻烦
	
    总结:未添加泛型时,集合什么对象都可以添加,用迭代器迭代强转元素为自定义类型时,会把其他不是该类型或其子类的,
    其他类型强转,造成异类转换异常,如把Integer类型强转为Student类型,或者把猫类型强转为狗,都是不对的
    
C:泛型基本使用:

    <>中放的必须是引用数据类型
    
D:泛型使用注意事项:

    前后的泛型必须一致,或者后面的泛型可以省略不写(即JDK1.7的新特性:菱形泛型)
代码演示如下:
```
public static void main(String[] args) {
    //demo0();
    //demo1();
    
    //int[] arr = new byte[5];//数组要保证前后的数据类型一致
    //ArrayList<Object> list = new ArrayList<Person>();//集合的泛型也要保证前后的数据类型一致
    //ArrayList<Object> list = new ArrayList<>();//1.7版本的新特性,菱形泛型
    ArrayList<Object> list = new ArrayList<>();//泛型最好不要定义成Object,没有意义,
    list.add("aaa");//因为什么都可以添加,同时<>中放的必须是引用数据类型 
    list.add(true);
}

public static void demo1() {//加了泛型,能将运行期的错误转换到编译期,
	ArrayList<Person> list = new ArrayList<Person>();
//	list.add(110);
//	list.add(true);
	list.add(new Person("张三", 23));
	list.add(new Person("李四", 24));
//	list.add(new Person("王五", 25));
	
	Iterator<Person> it = list.iterator();
	while(it.hasNext()) {
		System.out.println(it.next());//加了泛型,能够省去强转的麻烦
//next方法只能调用一次,如果调用多次会将指针向后移动多次:如果后面没有元素了,会报没有这样的元素异常:↓
	    System.out.println(it.next().getName() + "..." + it.next().getAge());//NoSuchElementException
//		Person p = it.next();		
//		System.out.println(p.getName() + "..." + p.getAge());
	}
}

public static void demo0() {//没有泛型的时候会出现类型转换异常等这样的问题:
	ArrayList list = new ArrayList();
	list.add(110);
	list.add(true);
	list.add(new Person("张三", 23));
	
	Iterator it = list.iterator();
	while(it.hasNext()) {
	    Person p = (Person) it.next();//强转集合元素为Person类,会把其他类也强转会报类型转换异常		
        System.out.println(p.getName() + "..." + p.getAge());//ClassCastException extends RuntimeException
	}
}
```

#16.07_集合框架(ArrayList分别存储字符串或自定义对象并遍历泛型版)(掌握)
A:案例演示Demo2_Generic

    ArrayList分别存储字符串或自定义对象并遍历泛型版:
代码演示如下:
```
public static void main(String[] args) {
    //demo1();
    
    ArrayList<Person> list = new ArrayList<>();
    list.add(new Person("张三", 23));
    list.add(new Person("李四", 24));
    list.add(new Person("王五", 25));
    list.add(new Person("赵六", 26));
    
    Iterator<Person> it = list.iterator();
    while(it.hasNext()) {
    	Person p = it.next();//将集合中的每一个元素用Person记录
    	System.out.println(p.getName() + "..." + p.getAge());
    }
}

public static void demo1() {
	ArrayList<String> list = new ArrayList<>();//创建集合对象
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	
	Iterator<String> it = list.iterator();
	while(it.hasNext()) {
		System.out.println(it.next());
	}
}
```

#16.08_集合框架(泛型的由来)(了解)
A:案例演示Demo3_Generic

    泛型的由来:通过Object转型问题引入
	早期,Object类型可以接收任意的对象类型,但是在实际的使用中,会有类型转换的问题,也就存在这隐患,
	所以Java提供了泛型来解决这个安全问题
	
总结:跟上面一样,未添加泛型的时候强转元素会造成类型转换异常
代码演示如下:
```
public static void main(String[] args) {
	demo1();
}

public static void demo1() {
	
    Tool t1 = new Tool();//创建工具类对象
	t1.setObj(new Student("张三",23));
	
	Worker w = (Worker) t1.getObj();//不加泛型,向下转型编译没错,但是运行时报错如下:
	System.out.println(w);//类型转换异常java.lang.ClassCastException:
	//com.heima.bean.Student cannot be cast to com.heima.bean.Worker
	//原因在于工具类设置方法传入的是Object类,
	//得到方法返回值类型也是Object类,所以编译没错,但是运行时异常
	
	//下面看看用了泛型之后的效果,去Tool类加上泛型来做改动,来看下个知识点
}

//另一个文件中定义的类,我把它写在这里好对比
/*
public class Tool {
    private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}*/

//另一个文件中定义的类,我把它写在这里好对比
/*
public class Worker extends Person {

	public Worker() {
	}

	public Worker(String name, int age) {
		super(name, age);
	}
}*/

//另一个文件中定义的类,我把它写在这里好对比
/*
public class Student extends Person {

	public Student() {
	}

	public Student(String name, int age) {
		super(name, age);
	}
}*/
```

#16.09_集合框架(泛型类的概述及使用)(了解)
A:泛型类概述```<T>```:

    把泛型定义在类上
    
B:定义格式:

    public class 类名<泛型类型1,…>
    
C:注意事项:

	泛型类型必须是引用类型
	
D:案例演示Tool:

    泛型类的使用
```
总结:随便定义的类中的Object类型转换成泛型演示,如<Q>
代码演示如下:
//另一个文件中定义的类,我把它写在这里好对比,用了泛型之后,类的改动
public class Tool<Q> {
    private Q q;

	public Q getObj() {
		return q;
	}

	public void setObj(Q q) {
		this.q = q;
	}	
}

public class Demo3_Generic {
	public static void main(String[] args) {
		demo1();
	}

	public static void demo1() {
		//下面看看用了泛型之后的效果,去Tool类加上泛型来做改动
		Tool<Student> t = new Tool<>();
		t.setObj(new Student("张三",23));
		
        //Worker w = (Worker) t.getObj();//加了泛型,向下转型编译报错无法通过,如下所示:
        //System.out.println(w);//Cannot cast from Student to Worker,
		//这样就将运行期的错误转换到编译期,提高安全性
	}
}
```

#16.10_集合框架(泛型方法的概述和使用)(了解)
A:泛型方法概述:

    把泛型定义在方法上
    
B:定义格式:

    public <泛型类型> 返回类型 方法名(泛型类型 变量名)
    
C:案例演示Tool:

    泛型方法的使用:
总结:如果非静态方法的参数泛型类型与类的泛型类型一致,方法的泛型类型可以不写,静态方法和其他情况必须写,
其实说白了,写与不写,都是看定义的泛型类型能否给赋值的问题,如果能,就不用写,不能就要写
```
代码演示如下:
//另一个文件中定义的类,我把它写在这里好对比,用了泛型之后,类的改动
//用了泛型之后,类的改动
public class Tool<Q> {
	private Q q;

	public Q getObj() {
		return q;
	}

	public void setObj(Q q) {//泛型方法如果与类的一致,方法声明上可以不写
		this.q = q;
	}
	
	public<T> void show(T t) {//方法泛型最好与类的泛型一致,
		System.out.println(t);//如果不一致,需要在方法上声明该泛型,否则报错,如下:
	}
	
//	public void show2(T t) {//方法泛型最好与类的泛型一致,
//		System.out.println(t);//如果不一致,不在方法上声明该泛型,报错
//	}
	
//	public static void print2(Q q) {//静态方法必须声明自己的泛型,否则报错
//		System.out.println(q);//即使跟类的一样,也报错
//	}

	public static<W> void print(W w) {//静态方法必须声明自己的泛型
		System.out.println(w);//即使这里的W全够改为Q,也跟类的不一样
	}
	
	public static<Q> void print3(Q w) {//静态方法必须声明自己的泛型
		System.out.println(w);//即使这里的W全够改为Q,也跟类的不一样
	}
}

public class Demo3_Generic {
	public static void main(String[] args) {
		Tool<String> t = new Tool<>();
		t.show("abc");//abc
		t.show(true);//true
	}
}
```

#16.11_集合框架(泛型接口的概述和使用)(了解)
A:泛型接口概述:

    把泛型定义在接口上
    
B:定义格式:	

	public interface 接口名<泛型类型>
	
C:案例演示Demo4_Generic

	泛型接口的使用:
总结:实现泛型接口的时候不推荐给实现类加上泛型类型,想要什么类型如String就加即可如↓
```
代码实现如下:
public class Demo4_Generic {
	public static void main(String[] args) {
		System.out.println("泛型接口使用测试");
	}
}

interface Inter<T> {//这里不可以用public修饰,因为上面已经给类修饰了,一个源文件只能有一个
	public void show(T t);//一个源文件只能有一个,但是如果是内部类的里面类还可以用public修饰
}

class Demo implements Inter<String> {//两种实现泛型接口的方式都可以,但是推荐用这种↑

	@Override
	public void show(String t) {
		System.out.println(t);
	}
	
}

/*
class Demo<T> implements Inter<T> {//这个也可以,但是没必要在实现接口的时候给自己类即实现类加泛型,

	@Override
	public void show(T t) {//谁用到实现类,也要用相应的类型,就写死了,用不了其他类型,
		System.out.println(t);//所以不推荐
	}
	
}
*/
```

#16.12_集合框架(泛型高级之通配符)(了解)
```
案例演示Demo5_Generic
A:泛型通配符<?>
	任意类型,如果没有明确,那么就是Object以及任意的Java类了
	
B:? extends E
	向下限定,E及其子类
	
C:? super E
	向上限定,E及其父类
总结:List<?> list = new ArrayList<Integer>();//当右边的泛型是不确定时,左边可指定为?,但把?换成Object就会报错,
因为左右两边泛型类型不一致
```
代码演示如下:
```
public class Demo5_Generic {
    public static void main(String[] args) {
	List<?> list = new ArrayList<Integer>();//当右边的泛型是不确定时,左边可以指定为?
//	List<Object> list3 = new ArrayList<Integer>();//但如果是Object左右类型不一致报错
	
		ArrayList<Person> list1 = new ArrayList<>();
		list1.add(new Person("张三", 23));
		list1.add(new Person("李四", 24));
		list1.add(new Person("王五", 25));
		
		ArrayList<Student> list2 = new ArrayList<>();
		list2.add(new Student("赵六", 26));
		list2.add(new Student("周七", 27));
		
		//向下限定,E及其子类,Student extends Person,所以Student是Person的子类
		list1.addAll(list2);//addAll(Collection<? extends Person> c)
		System.out.println(list1);//[Person [name=张三, age=23], ...省略不写
		
		//反过来就编译报错了:addAll方法,谁调用它,里面的传入extends 谁的类型
//		list2.addAll(list1);//The method addAll(Collection<? extends Student>
		//要求的是学生类及其子类,你不能传入父类或者其他类
	}
}
```

#16.13_集合框架(增强for的概述和使用)(掌握)
案例演示Demo1_Foreach
A:增强for概述:

    简化数组和Collection集合的遍历
    
B:格式:

	for(元素数据类型 变量 : 数组或者Collection集合) {
		使用变量即可,该变量就是元素
	}
	
C:案例演示:

	数组,集合存储元素用增强for遍历
	
D:好处:

	简化遍历
	
总结:不要忘了增强for循环遍历集合元素,底层依赖的是迭代器,下面会说明
代码演示如下:
```
public static void main(String[] args) {
    int[] arr = {11,22,33,44,55};
    for (int i : arr) {//数组用增强for遍历
    	System.out.println(i);
    }
    
    ArrayList<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    
    for (String string : list) {//存储字符串的集合用增强for遍历
    	System.out.println(string);
    }
}
```

#16.14_集合框架(ArrayList存储字符串和自定义对象并遍历增强for版)(掌握)
A:案例演示Demo1_Foreach

	ArrayList存储字符串并遍历增强for版
	ArrayList存储自定义对象并遍历增强for版
代码演示如下:
```
public static void main(String[] args) {
    ArrayList<Person> list = new ArrayList<>();
    list.add(new Person("张三", 23));
    list.add(new Person("李四", 24));
    list.add(new Person("王五", 25));
    list.add(new Person("赵六", 26));
    
    for (Person person : list) {//存储自定义的集合用增强for遍历
    	System.out.println(person);
    }
}
```

#16.15_集合框架(三种迭代的能否删除)(掌握)
案例演示Demo1_Foreach

    普通for循环,可以删除,但是索引要--,因为一旦删除,后面集合元素会向前移动,填补空缺位置
    
    迭代器,可以删除,但是必须使用迭代器自身的remove方法,否则会出现并发修改异常
    
    增强for循环不能删除,否则会报并发修改异常,因为其底层依赖的是迭代器
代码演示如下:
```
public static void main(String[] args) {		
    ArrayList<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("b");
    list.add("c");
    list.add("d");
    
    //		//1普通for循环,可以删除,但是索引要--,因为一旦删除,后面集合元素会向前移动,填补空缺位置
    //		for(int i = 0; i < list.size(); i++) {
    //			if("b".equals(list.get(i))) {
    //				list.remove(i--);//通过索引删除元素,索引
    //			}
    //		}
    //		
    //		//2迭代器,可以删除,但是必须使用迭代器自身的remove方法,否则会出现并发修改异常
    //		Iterator<String> it = list.iterator();
    //		while(it.hasNext()) {
    //			if("b".equals(it.next())) {
    //				//list.remove("b");//不能用集合的删除方法,否则会出现并发修改异常
    //				it.remove();//可以用迭代器遍历,迭代器移除方法删除
    //			}
    //		}
    
    //      for(Iterator<String> it2 = list.iterator(); it2.hasNext();) {//增强for循环遍历集合底层代码,用完后就释放变量,
    //			if("b".equals(it2.next())) {//可以通过反编译增强for循环的字节码文件看到,
    //				//list.remove("b");//不能用集合的删除方法,否则会出现并发修改异常
    //				it2.remove();//迭代器删除写法2
    //			}
    //		}
    
    //3增强for循环不能删除,否则会报并发修改异常,因为其底层依赖的是迭代器
            for (String string : list) {
            if("b".equals(string)) {
            list.remove("b");//会报并发修改异常ConcurrentModificationException这个运行时异常,
            } //说明增强for循环底层依赖的也是迭代器,或反编译增强for循环的字节码文件看到代码
            }
            System.out.println(list);
}
```

#16.16_集合框架(静态导入的概述和使用)(了解)
案例演示Demo2_StaticImport

A:静态导入概述:静态导入是导入类中静态方法

B:格式:

	import static 包名….类名.方法名;
	可以直接导入到方法的级别
	
C:注意事项:

	方法必须是静态的,如果有多个同名的静态方法,容易不知道使用谁?
	这个时候要使用,必须加前缀即类名点调用,由此可见,意义不大,所以开发中一般不用,但是要能看懂
	
总结:静态导入可以导入到类的静态方法级别,但是当本类或其父类含有同名的方法时,会调用后者,
所以,静态导入意义不大,开发中一般不用,但是有人用了,你也要看的懂就可以了:
代码使用如下:
```
import static java.util.Arrays.sort;//静态导入sort静态方法
import static java.util.Arrays.toString;//静态导入静态toString方法
public class Demo2_StaticImport {
    public static void main(String[] args) {
		int[] arr = {55,22,33,44,11};
		sort(arr);//可以调用这个方法排序,是因为上面使用了静态导入导入方法
        //System.out.println(toString(arr));//报错,不指定前缀,有静态导入也没有用,说明静态导入是JDK1.5的败笔
        System.out.println(Arrays.toString(arr));//即使导入了静态方法,但是由于本类有toString方法,就没有调用
	}
	
//  public static void sort(int[] arr){//放开这里,上面的排序方法调用的就是这里的同名方法,静态导入的方法就没用到
//		System.out.println("静态导入排序方法,跟本类方法同名就调用了本类的了,说明静态导入作用不大");
//	}
}
```

#16.17_集合框架(可变参数的概述和使用)(掌握)
案例演示Demo3_ChangeableArgs

A:可变参数概述:

	定义方法的时候不知道该定义多少个参数
	
B:格式:

	修饰符 返回值类型 方法名(数据类型…  变量名){}
	
C:注意事项:

	这里的变量其实是一个数组
	如果一个方法有可变参数,并且有多个参数,那么,可变参数肯定是最后一个,调用时会对号入座给相应参数赋值
	
总结:要注意,如果一个方法的参数只有可变参数,那么该方法可以不传入任何参数,因为可变参数的范围是0到无穷大,
如果有多个参数,那么,可变参数肯定是最后一个,调用时也会对号入座,给相应的普通参数赋值,
假如可变参数放前面,调用时传入实参就被可变参数(即数组)接收了,后面的参数没有实参给其赋值
看下面代码就明白了:
```
public static void main(String[] args) {
    int[] arr = {11,22,33,44,55};
    //print(arr);//可变参数其实是一个数组,直接传入数组名也可以调用含可变参数方法
    print(11,22,33,44,55);//可变参数的实参调用,底层也是把11,22等封装成一个数组
    System.out.println("-------------------------------");
    //print();//如果一个方法的参数只有可变参数,那么该方法可以不传入任何参数,因为可变参数的范围是0到无穷大
    
    //上面的代码,反编译字节码文件后打开是这样的↓:
    /*
    int arr[] = {
    	11, 22, 33, 44, 55
    };
    print(arr);
    print(new int[] {
    	11, 22, 33, 44, 55
    });
    System.out.println("----------------------------------");
    print(new int[0]);
    */
}
	
/*
public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
    	System.out.println(arr[i]);
    }
}
*/
	
//public static void print(int ... arr) {//可变参数其实是一个数组,
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//System.out.println("如果方法的参数只有可变参数,那么该方法可以不传入任何参数,因为可变参数的范围是0到无穷大");
//}
	
//上面的print方法,反编译字节码文件后打开是这样的↓:
/*
public static transient void print(int arr[])//说明可变参数其实是一个数组
{
	for (int i = 0; i < arr.length; i++)
		System.out.println(arr[i]);

System.out.println("如果方法的参数只有可变参数,那么该方法可以不传入任何参数,因为可变参数的范围是0到无穷大");
}
*/
	
//public static void print(int ... arr,String test) {//多个参数,可变参数一定要在最后
//  for (int i = 0; i < arr.length; i++) {
//	    System.out.println(arr[i]);//如果一个方法有可变参数,并且有多个参数,那么可变参数肯定是最后一个,
//	//因为假如放可变参数前面,调用时传入实参就被可变参数(即数组)接收了,后面的参数没有实参给其赋值
//		}
//}
	
public static void print(int x,int ... arr) {//多个参数可变参数是最后一个,并且会对号入座赋值
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("11没有输出是因为给非可变参数x赋值了");
}
	
//上面的print方法,反编译字节码文件后打开是这样的↓:
/*
public static void print(int ai[], String s){
	throw new Error("Unresolved compilation problem: \n\tThe variable argument type int of the method print must be the last parameter\n");
}

public static transient void print(int x, int arr[]){
	for (int i = 0; i < arr.length; i++)
		System.out.println(arr[i]);

	System.out.println("11没有输出是因为给非可变参数x赋值了");
}
*/
```

#16.18_集合框架(Arrays工具类的asList()方法的使用)(掌握)
A:案例演示Demo4_AsList

	Arrays工具类的asList()方法的使用
	Collection中toArray(T[] a)泛型版的集合转数组//注意是Collection不是Collections
	
代码演示如下:
```
public static void main(String[] args) {
//数组转集合,数组必须是引用数据类型,如果是基本数据类型的数组转换成集合,会将整个数组当作一个对象转换
//	demo1();
//	demo2();
	
	//集合转数组,
	ArrayList<String> list = new ArrayList<>();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");//size=4;
	
    String[] arr = list.toArray(new String[3]);//数组长度小于等于集合的size时,转换后的数组长度等于集合的size,
    //		String[] arr = list.toArray(new String[10]);//数组的长度大于了size,分配的数组长度就用大的,
    //集合转数组,指定的数组长度小于集合长度就用集合长度,如果大了,就用大的长度,保证集合元素都能转换到数组中存储,
    
	for (String string : arr) {
//			System.out.print(string);//abcdnullnullnullnullnullnull
		System.out.print(string);//abcd
	}
}

public static void demo2() {
	int[] arr = {11,22,33,44,55};			
List<int[]> list = Arrays.asList(arr);//基本数据类型的数组转换成集合,会将整个数组当作一个对象转换,
	System.out.println(list);//所以会输出地址值,[[I@181ed9e]
	
	Integer[] arr2 = {11,22,33,44,55};//将数组转换成集合,数组必须是引用数据类型,
	List<Integer> list2 = Arrays.asList(arr2);
	System.out.println(list2);//[11, 22, 33, 44, 55]
}

public static void demo1() {
	String[] arr = {"a","b","c"};
	List<String> list = Arrays.asList(arr);//将数组转换成集合,
    list.add("d");//集合不能添加元素,否则会报不支持的操作异常UnsupportedOperationException这个运行时异常
    //Exception in thread "main" java.lang.UnsupportedOperationException
    System.out.println(list);//数组转集合,集合不能增删,是为了保证数组中的元素都能转换到存储到集合中
}
```

总结:无论数组转集合,还是集合转数组,都必须保证前者的元素都能存储到后者的元素里,
     所以,集合转数组,指定的数组长度小于集合长度就用集合长度,如果大了,就用大的数组长度,
     总之要保证集合中的元素都能转换到数组中存储;
数组转换成集合,数组必须是引用数据类型,虽然不能增加或减少元素,但是可以用集合的思想操作数组,
也就是说可以使用集合中其他的方法

#16.19_集合框架(集合嵌套之ArrayList嵌套ArrayList)(掌握)
A:案例演示Demo5_ArrayListArrayList

	集合嵌套之ArrayList嵌套ArrayList
	
代码示范如下:
```
public class Demo5_ArrayListArrayList {
	public static void main(String[] args) {
		ArrayList<ArrayList<Person>> list = new ArrayList<>();//学科集合有班级元素组成,
		
		ArrayList<Person> first = new ArrayList<>();//创建第一个班级元素,本身也是一个集合
		first.add(new Person("杨幂", 30));
		first.add(new Person("李冰冰", 33));
		first.add(new Person("范冰冰", 20));
		
		ArrayList<Person> second = new ArrayList<>();//创建第二个班级元素,本身也是一个集合
		second.add(new Person("黄晓明", 31));
		second.add(new Person("赵薇", 33));
		second.add(new Person("陈坤", 32));
		
		//将班级元素添加到学科集合中,
		list.add(first);
		list.add(second);
		
		//遍历学科集合,得到每个班级元素,遍历班级元素(集合)输出班级里的每一个元素,即每一个人
		for(ArrayList<Person> a : list) {
			for(Person p : a) {
				System.out.println(p);
			}
		}
	}
}
```

#16.20_day16总结
    把今天的知识点总结一遍:
    ArrayList去重复-LinkedList特点-泛型-增强for循环-集合元素的删除-静态导入-可变参数-数组转集合,集合转数组


