package com.itheima.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXEventRecorder;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XPathDemo {
	//得到apple的价格 18000
	@Test
	public void fun01() throws DocumentException{
		//1.创建解析器
		SAXReader saxReader = new SAXReader();
		//2.读取xml
		Document document = saxReader.read("src/main/resources/books.xml");
		
		String data = document.selectSingleNode("/books/apple/price").getText();
		System.out.println(data);
	}
	
	//得到第一本书的author -->隔壁王叔叔
	@Test
	public void fun02() throws DocumentException{
		//1.创建解析器
		SAXReader saxReader = new SAXReader();
		//2.读取xml
		Document document = saxReader.read("src/books.xml");
		
		String data = document.selectSingleNode("//book[1]/author").getText();
		System.out.println(data);
	}
	

	

}
