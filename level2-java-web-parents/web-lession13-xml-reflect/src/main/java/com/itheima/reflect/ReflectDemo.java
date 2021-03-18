package com.itheima.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.junit.Test;

import com.itheima.bean.Student;

public class ReflectDemo {
	
	/**
	 * 得到字节码
	 * 1.Class.forName("类的全限定名")
	 * 2.对象.getClass();
	 * 3.类名.class
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void fun01() throws Exception{
		// 1.Class.forName("类的全限定名")
		//Class  clazz = Class.forName("com.itheima.bean.Student");
		//2.对象.getClass();
		//Student student = new Student();
		//Class  clazz2 = student.getClass();
		
		//3.类名.class
		Class  clazz3 = Student.class;
		
		
	}
	
	
	/**
	 * 根据字节码创建对象(使用的是默认的构造函数)重要
	 * @throws Exception
	 */
	@Test
	public void fun02() throws Exception{
		// 1.Class.forName("类的全限定名")
		Class  clazz = Class.forName("com.itheima.bean.Student");
		//2.根据字节码创建对象(使用的是默认的构造函数,没有参数的构造函数)
		Student student = (Student) clazz.newInstance();
		
		student.speak();
	
	}
	
	/**
	 * 方式构造函数
	 * @throws Exception
	 */
	@Test
	public void fun03() throws Exception{
		// 1.获得字节码
		Class  clazz = Class.forName("com.itheima.bean.Student");
		/*//获得所有的公共的构造函数
		Constructor[] constructors = clazz.getConstructors();
		System.out.println(constructors.length);*/
		//反射public Student(String name, int age, String sex)构造函数
		Constructor constructor = clazz.getConstructor(String.class,int.class,String.class);
		Student student = (Student) constructor.newInstance("张三",18,"男");
		System.out.println(student.getName());
		
	
	}
	
	
	//反射成员变量(字段)
	@Test
	public void fun04() throws Exception{
		//1.获得字节码
		Class  clazz = Class.forName("com.itheima.bean.Student");
		//获得所有的公共的字段
		/*Field[] fields = clazz.getFields();
		System.out.println("公共的字段的个数:"+fields.length);
		for (Field field : fields) {
			System.out.println(field.getName());
		}*/
		Student student = new Student("张三", 18, "男");
		System.out.println(student.getName());
		
		//获得指定的字段
		Field field = clazz.getDeclaredField("name");
		//暴力
		field.setAccessible(true);
		field.set(student, "李四");
		System.out.println(student.getName());
		
	}
	
	
	//反射方法(重点)
	@Test
	public void fun05() throws Exception{
		//1.获得字节码
		Class  clazz = Class.forName("com.itheima.bean.Student");
		//获得所有的公共方法(包括父类方法)
		//Method[] methods = clazz.getMethods();
		//获得所有的方法(不包括父类方法)
	/*	Method[] methods  = clazz.getDeclaredMethods();
		for (Method method : methods) {
			
			System.out.println(method.getName());
			
		}*/
		
		//反射speak()-->student.speak();
		/*Method method = clazz.getDeclaredMethod("speak");
		method.invoke(clazz.newInstance());*/
		
		//反射 speak(String name) 
	/*	Method method = clazz.getDeclaredMethod("speak", String.class);
		//暴力
		method.setAccessible(true);
		method.invoke(clazz.newInstance(), "张三");*/
		
		//反射 speak(String name,int age)
		Method method = clazz.getDeclaredMethod("speak", String.class, int.class);
		
		method.invoke(clazz.newInstance(), "李四",20);
		
	}
	
	
	//反射main方法main(String[] args)
	@Test
	public void fun06() throws Exception{
		//1.得到字节码
		Class clazz = Class.forName("com.itheima.bean.Student");
		//得到main方法  main(String[] args)
		Method method = clazz.getDeclaredMethod("main", String[].class);
		//因为main方法是静态的,所有调用的时候传入null
		method.invoke(null, (Object)new String[]{"aaa","bbb","ccc"});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
