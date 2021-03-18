#day13_XML和反射 #

**今日任务**

- 解析xml
- 模拟servlet执行

**教学目标**

- 了解XML的基本语法，并能够编写正确格式的XML.
- 了解XML的约束.
- 掌握XML的解析.
- 掌握反射的常用操作


### 一，XML入门 ###

#### 1.xml概述

+ 可扩展标记语言
+ html里面的标签都是固定的，不能自定义，xml是可以自定义的

#### 2.xml和html区别

+ html语法松散，xml语法严格,区分大小写
+ html做页面展示，xml描述数据
+ html所有标签都是预定义的，xml所有标签都是自定义的

#### 3.xml作用

+ 作为配置文件。

- 用来传输和存储数据。

#### 4.xml语法规范

##### 4.1文档声明

```
<?xml version = "1.0" encoding = "utf-8" standalone="yes" ?>
```

+ 必须写在xml文档的第一行第一列
+ 写法：<?xml version="1.0" ?>
+ 属性:
   1. version：版本号 固定值 1.0
   2. encoding:指定文档的码表。默认值为 iso-8859-1
   3. standalone：指定文档是否独立  yes 或 no(了解)


##### 4.2元素：xml文档中的标签

+ 文档中必须有且只能有一个根元素,其他标签都是这个根标签的子标签或孙标签
+ 元素需要正确闭合

  1. 包含标签主体：<mytag>somecontent</mytag>
  2. 不含标签主体：<mytag/>
+ 元素需要正确嵌套,不允许有交叉嵌套。
+ 元素名称要遵守
  1. 元素名称区分大小写
  2. 数字不能开头

##### 4.3属性

+ 属性值必须用引号引起来。单双引号都行
+ 在一个标签里面属性不能重复

##### 4.4注释

​	语法<!-- -->

​	快捷键: Ctrl+Shift+/

+ 文档声明前面不能有注释
+ 注释不能嵌套

##### 4.5特使字符和CDATA区

​	1.在 XML 中有 5 个预定义的实体引用：

![img](img/tu_1.png)

​	2.CDATA 内部的所有东西都会被解析器忽略,当做文本

​	  	语法:<![CDATA[ 内容 ] ]>



### 二.xml约束  ###

#### 1.xml约束概述

+ 约束就是xml的书写规则, 
+ 约束文档定义了在XML中允许出现的元素(标签)名称、属性及元素(标签)出现的顺序等等。

#### 2.常用约束分类

##### 2.1dtd约束(了解) 

###### 2.1.1 引入方式   

1. 内部引入

   ```
   <!DOCTYPE 根元素 [元素声明]>
   ```

2. 外部引入; dtd是一个独立的文件   .dtd

   + 本地DTD

   ```
   <!DOCTYPE 根元素 SYSTEM "文件名">
   ```

   + 网络DTD

   ```
   <!DOCTYPE 根元素 PUBLIC "DTD名称" "DTD文档的URL">
   ```

###### 2.1.2语法

1. 元素：<!ELEMENT 元素名称 元素组成>

* 元素组成：EMPTY，ANY ，(子元素) ，(#PCDATA)

* 如果出现子元素: ?(出现0次或1次),

                       	    *(出现0次或多次),

                         	    +(出现1次或多次) 表示子元素出现的个数.

                                    |或者（只能出现一个）

                                    , 代表子元素出现必须按照顺序

2. 属性<!ATTLIST 元素名称 属性名称 属性类型 属性使用规则>

* 属性类型: ID,枚举,CDATA

* 使用规则: #REQUIRED:属性值是必需的

  ​	 	 #IMPLIED :属性不是必需的

  ​       		 #FIXED value:属性值是固定的

       ID使用：字母开头

##### 2.2schema  (了解)

###### 2.2.1 dtd和schema的区别

+ XMLSchema符合XML语法结构。 
+ DOM、SAX等XMLAPI很容易解析出XML Schema文档中的内容。 
+ XMLSchema对名称空间支持得非常好。
+ XMLSchema比XML DTD支持更多的数据类型，并支持用户自定义新的数据类型。 
+ XMLSchema定义约束的能力非常强大，可以对XML实例文档作出细致的语义限制。
+ XMLSchema不能像DTD一样定义实体，比DTD更复杂，但Xml Schema现在已是w3c组织的标准，它正逐步取代DTD。  

###### 2.2.1根据schema约束写xml文件步骤

+ 查出Schema文档,找出根元素
+ 根元素来自哪个名称空间
+ 这个名称空间和哪个xsd文件对应(指定约束的路径)
+ schemaLocation不是关键字，来自一个标准的名称空间





### 三，XML解析(重点)

#### 1.xml解析方式

##### 1.1DOM解析    

​	将xml文档加载到内存，形成一颗dom树(document对象)，将文档的各个组成部封装为一些对象。

​	优点: 因为，在内存中会形成dom树，可以对dom树进行增删改查。

​	缺点:dom树非常占内存，解析速度慢。



​		Document:文档节点

​		Element:元素节点

​		Text:文本节点

​		Attribute:属性节点

​		Comment:注释节点

​		

##### 1.2SAX解析

​	逐行读取，基于事件(函数或者方法)驱动   

​	优点：不占内存，速度快

​	 缺点：只能读取，不能回写



#### 2.常用xml解析器(jar包)   

JAXP：sun公司提供的解析。支持dom和sax。

JDOM

DOM4J：document for java 民间方式，但是是事实方式。非常好。 支持dom和sax. 

使用步骤:

1. 导入jar包  dom4j-1.6.1j.jar

2. 创建解析器

   SAXReader reader = new SAXReader();

3. 读取xml 获得document对象 

   Document document = reader.read(url)	

4. 得到根元素

   Element rootElement = document.getRootElement();

   ......

   ​

   获取节点属性的值

   ​	element.attributeValue("属性名")或者element.attribute("属性名").getValue();

   获取所有子元素节点

   ​	List<Element> list=element.elements();

   获得节点的文本值

   ​	element.getText()

   ​

#### 3.XPath的使用,基于dom4J(了解)

​	定义了一种规则,专门用于查询xml

​	使用步骤

​		1、导入jar包 jaxen...jar(当前资料里是: jaxen-1.1-beta-6.jar) 注意: 两个(dom4j的jar包和自己的jar包)

​		2、创建解析器

​			SAXReader reader = new SAXReader();

​		3、解析xml 获得document对象 

​			Document document = reader.read(url)

​	 核心方法：

​		document.selectSingleNode()

​		document.selectNodes():

### 四,反射(***重点)

#### 1.概述

​	反射就是把Java的各种成分(字段,方法)映射成相应的Java类.反射乃框架之灵魂  

​	反射是java语言的一个特性，它允程序在运行时（注意不是编译的时候）来进行自我检查并且对内部的成员进行操作。例如它允许一个java的类获取他所有的成员变量和方法并且显示出来。在其它的程序设计语言中根本就不存在这一特性（来自Sun）

#### 2.获得Class的三种方法

+ 对象.getClass()
+ 使用Class类的forName("类的全限定名")  最常见的方式
+ 使用“类.class”

#### 3.Class<T>中的方法

		---------------------------Class中和Filed相关的方法--------------------------
		public Field[] getFields() 
			返回此 Class 对象所表示的实体的所有公共属性。 
		public Field[] getField(String name) 
			返回此 Class 对象所表示的实体的指定属性名称的公共属性。 
		public Field[] getDeclaredFields()
			返回此 Class 对象所表示的实体的所有属性,包括私有属性,但不包括继承父类的属性。


		---------------------------Class中和Method相关的方法--------------------------
		public Method[] getMethods() 
			返回此 Class 对象所表示的实体的所有公共方法。 
		public Method[] getDeclaredMethods() 
			返回此 Class 对象表示的实体的所有方法,包括私有方法,但不包括继承父类的方法。 
		public Method getMethod(String name, Class... parameterTypes) 
			返回此 Class 对象所表示的实体的指定公共成员方法，
				name指定方法名称，parameterTypes指定方法参数类型


		---------------------------Class中和Constructor(构造)相关的方法--------------------------
		public Constructor[] getConstructors() 
			返回此 Class 对象所表示的类的所有公共构造方法。 
		public Constructor[] getDeclaredConstructors() 
			返回此 Class 对象表示的类声明的所有构造方法,包括私有构造方法。 
		public Constructor<T> getDeclaredConstructor(Class... parameterTypes) 
			返回此 Class 对象所表示的类的指定构造方法。 


		-----------------------------------------------------
		public T newInstance() 
			创建此 Class 对象所表示的类的一个新实例。 
			类必须有一个无参构造方法



#### 4.Field中的方法

		get(Object o);
			得到具体对象的该属性的值
		set(Object o, Object value);
			设置具体对象的该属性的值
#### 5.Method中的方法

	invoke(Object o,Object... args)
		o是调用该方法的对象,args是调用该方法时传入的参数
		如果调用的是静态方法(eg:main),第一个参数传入null



#### 6.反射练习

​	1.创建一个Student类

​	2.用单元测试来获取Student字节码,通过字节码创建出对象，构造函数的反射.成员变量的反射,成员方法的反射,反射main函数



Student类:

```
public class Student {
	
	private String  name;
	private int age;
	private String sex;
	private Date birth;
	
	private String grade;

	
	public Student() {
	}
	

	public Student(String name, int age, String sex) {
		
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	

	public Student(String name, int age, String sex, Date birth) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birth = birth;
	}


	public void speak(){
		System.out.println("hello...");
	}
	
	private void speak(String name){
		System.out.println("你好,我的名字是:"+name);
	}
	
	public void speak(String name,int age){
		System.out.println("你好,我的名字是:"+name+",我"+age+"了");
	}

	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}

	
	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println("arg="+arg);
		}
	}

}
```

​	

##### 1.获取Student字节码

```
	/**
	 * 获取字节码三种方法
	 */
	@Test
	public void fun01(){
		//1.对象.getClass();
		Student student = new Student();
		Class clazz = student.getClass();
		
		//2.类.class
		Class<Student> clazz1 = Student.class;
		
		//3.Class.for("全限定名")
		try {
			Class clazz2 = Class.forName("com.itheima.demo4.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
```

##### 2.通过字节码创建出对象(重点)

```
	@Test
	public void fun02() throws Exception{
		//1.获得字节码
		Class clazz = Class.forName("com.itheima.demo4.Student");
		//2.默认的构造函数,没有参数的
		Student s1  = (Student) clazz.newInstance();
		//3.调用方法
		s1.speak();
		
	}
```

##### 3.构造函数的反射

```
	@Test
	public void fun03() throws Exception{
		//1.获得字节码
		Class clazz = Class.forName("com.itheima.demo4.Student");
		//2.得到Constructor对象
		Constructor constructor = clazz.getConstructor(String.class,int.class,String.class,Date.class);
		Student student = (Student) constructor.newInstance("张三",18,"李四",new Date());
		//student.speak();
		Date birth = student.getBirth();
		System.out.println(birth.toString());
	}
```

##### 4.成员变量的反射

```
	@Test
	public void fun04() throws Exception{
		//得到字节码
		Class  clazz = Class.forName("com.itheima.demo4.Student");
		
		//得到所有的共有字段
		//Field[] fields = clazz.getFields();
		//得到所有的字段
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			//System.out.println(field.getName());
		}
		
		//给成员变量赋值
		Student student = new Student("张三", 18);
		System.out.println(s1.getName());
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);//暴力破解
		field.set(s1, "李四");
		System.out.println(s1.getName());
		
	}
```

##### 5.成员方法的反射(重点)

```
	@Test
	public void fun05() throws Exception{
		Class  clazz = Class.forName("com.itheima.demo4.Student");
		Student student = (Student) clazz.newInstance();
		//得到所有声明的方法
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			//System.out.println(method.getName());
		}
		
		
		//用反射调用speak()方法
		Method method1 = clazz.getDeclaredMethod("speak");
		method1.invoke(student);
		
		//调用speak(String name,int age)
		method1 = clazz.getDeclaredMethod("speak", String.class);
		method1.setAccessible(true);//暴力破解
		method1.invoke(student, "张三");
		
		
		//调用speak(String name)
		method1 = clazz.getDeclaredMethod("speak",String.class,int.class);
		method1.invoke(student, "张三",18);
		
	}
```

##### 6.反射main方法

```
	@Test
	public void fun06() throws Exception{
		Class  clazz = Class.forName("com.itheima.demo4.Student");
		Method method = clazz.getDeclaredMethod("main", String[].class);
		method.setAccessible(true);
		method.invoke(null, (Object)new String[]{"aaa","bbb","ccc"});
	}
```



##### 7.模拟servlet执行流程

+ 创建配置文件web.xml

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app version="2.5">
  	<servlet>
  		<servlet-name>MyServlet</servlet-name>
  		<servlet-class>com.itheima.web.MyServlet</servlet-class>
  	</servlet>
  	<servlet-mapping>
  		<servlet-name>MyServlet</servlet-name>
  		<url-pattern>/hello</url-pattern>
  	</servlet-mapping>
  </web-app>
  ```



+ 创建MyServer类

  ```
  package com.itheima.web;

  public class MyServlet {
  	
  	public void init(){
  		System.out.println("初始化了....");
  	}
  	
  	public void service(){
  		System.out.println("服务....");
  	}
  	
  	public void destory(){
  		System.out.println("销毁....");
  	}
  }
  ```

+ 测试代码

  ```
  public class TestDemo {
  	@Test
  	public void fun01() throws Exception{
  		SAXReader reader = new SAXReader();
  		Document document = reader.read("src/web.xml");
  		String className = document.selectSingleNode("//servlet-class").getText();
  		//System.out.println(className);
  		Class<?> clazz = Class.forName(className);
  		MyServlet myServlet  = (MyServlet) clazz.newInstance();
  		
  		myServlet.init();
  		myServlet.service();
  		myServlet.destory();
  	}
  }
  ```