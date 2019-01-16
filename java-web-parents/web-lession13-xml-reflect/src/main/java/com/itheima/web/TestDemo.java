package com.itheima.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class TestDemo {

	public static void main(String[] args) throws Exception {

		/*MyServlet myServlet = new MyServlet();
		myServlet.service();*/
		
		//1.解析web.xml, 获得 <servlet-class>com.itheima.web.MyServlet</servlet-class>
		//1.1创建SaxReader对象
		SAXReader saxReader = new SAXReader();
		//1.2读取xml
		Document document = saxReader.read("src/web.xml");
		//获得servlet-class标签里面的值
		String className = document.selectSingleNode("/web-app/servlet/servlet-class").getText();
		//2.得到字节码
		Class clazz = Class.forName(className);
		//3.根据字节码创建MyServlet对象(没有参数的构造函数)
		MyServlet myServlet = (MyServlet) clazz.newInstance();
		
		myServlet.service();
	
	}

}
