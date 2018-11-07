#26.01_网络编程(网络编程概述)(了解)
A:计算机网络:

    是指将地理位置不同的具有独立功能的多台计算机及其外部设备,通过通信线路连接起来,在网络操作系统,
	网络管理软件及网络通信协议的管理和协调下,实现资源共享和信息传递的计算机系统
	
B:网络编程:

    就是用来实现(网络互连的不同计算机上运行的程序间可以进行)数据交换的

###26.02_网络编程(网络编程三要素之IP概述)(掌握)
IP是每个设备,在网络中的唯一标识:

    每台网络终端,在网络中都有一个独立的地址,我们在网络中传输数据,就是使用这个地址:
    
    ipconfig:查看本机IP,(另外赠送一个命令行输入getMac命令可以得到Mac地址即物理地址)
    ping:测试连接,例如命令行输入ping 192.168.40.62
    本地回路地址:127.0.0.1 而255.255.255.255是广播地址
    
    IPv4:4个字节组成,4个0-255,大概42亿,30亿都在北美,亚洲4亿,2011年初已经用尽
    
    IPv6:8组,每组4个16进制数,下面多个0000都可以省略,你省略的地方用:表示即可,不管你省略一个还是多个,如下所示:
    1a2b:0000:aaaa:0000:0000:0000:aabb:1f2f
    1a2b::aaaa:0000:0000:0000:aabb:1f2f
    1a2b:0000:aaaa::aabb:1f2f
    1a2b:0000:aaaa::0000:aabb:1f2f
    1a2b:0000:aaaa:0000::aabb:1f2f

#26.03_网络编程(网络编程三要素之端口号概述)(掌握)
端口号是每个程序,在设备上的唯一标识:

    每个网络程序,都需要绑定一个端口号,传输数据的时候,除了确定发到哪台机器上,还要明确发到哪个程序中
    端口号范围从0-65535
    编写网络应用,就需要绑定一个端口号,尽量使用1024以上的,1024以下的基本上都被系统程序占用了
    
常用端口:

	mysql:3306
	oracle:1521
	web:80
	tomcat:8080
	QQ:4000
	feiQ:2425

常用命令:

    命令行输入netstat -ano命令可以查看本机目前所有端口使用情况,包含PID号,通过【任务管理器】对照PID号,
    即可查看到哪个程序调用了xxx端口,
    
    另外还有netstat -anb命令可以查看端口对应的进程,另外赠送查杀进程的命令:
      ntsd -c q -p PID,把最后那个PID,改成你要终止的进程的ID,只有System、SMSS.EXE和CSRSS.EXE不能杀,其他都可以,
      
    如果你不知道进程的ID,任务管理器－>进程选项卡－>查看－>选择列－>勾上"PID（进程标识符）",就可以看见PID了;
    
    还可以用taskkill /im 映像名称.exe /f其中,“/im”后面接映像名称,“/f”的意思的强制结束,/f不要也可以杀掉,
    
    另tasklist命令可以查看任务列表,如想看taskkill的更多用法,可用命令“taskkill /?”进行查看

#26.04_网络编程(网络编程三要素协议)(掌握)
协议为计算机网络中进行数据交换而建立的规则、标准或约定的集合:

    UDP:
    	面向无连接,数据不安全,速度快,不区分客户端与服务端
    	
    TCP:
    　　面向连接(三次握手),数据安全,速度略低,分为客户端和服务端
    	三次握手:客户端先向服务端发起请求,服务端响应请求,传输数据

总结:网络编程三要素无非是三个p,即IP,port,protocol,重点要区别协议中UDP协议和TCP协议的区别,
另外IP和port合称套接字,下面知识点会说,个人理解网络编程无非是套接字和协议

#26.05_网络编程(Socket通信原理图解)(了解)
A:Socket套接字概述:

    网络上具有唯一标识的IP地址和端口号组合在一起才能构成唯一能识别的标识符套接字,
	通信的两端都有Socket,
	网络通信其实就是Socket间的通信,
	数据在两个Socket间通过IO流传输,
	Socket在应用程序中创建,通过一种绑定机制与驱动程序建立关系,告诉自己所对应的IP地址和port端口号

总结:socket相当于码头,而io流就是用来运输货物的船,详情见截图

#26.06_网络编程(UDP传输)(了解)
1.发送Send:

    创建DatagramSocket,随机端口号,可以不指定,下面的DatagramPacket要指定
	创建DatagramPacket,指定数据,长度,地址,端口
	使用DatagramSocket发送DatagramPacket
	关闭DatagramSocket
代码演示如下:
```
public static void main(String[] args) throws Exception {
	String str = "what are you 弄啥呢?";
	DatagramSocket socket = new DatagramSocket();//创建Socket相当于创建码头,端口号随机不指定
	
	//创建Packet相当于集装箱,指定端口号6666
    DatagramPacket packet =	new DatagramPacket(str.getBytes(),str.getBytes().length,InetAddress.getByName("127.0.0.1"), 6666);
    
	socket.send(packet);//发货,将数据发出去
	socket.close();//关闭码头socket,因为底层用的是io流
}
```
总结:发送无非用的是socket.send(packet);所以前提条件你要提供相应的socket码头对象和packet数据集装箱即可

2.接收Receive:

    创建DatagramSocket,指定端口号
	创建DatagramPacket,指定数组,长度
	使用DatagramSocket接收DatagramPacket
	关闭DatagramSocket
	从DatagramPacket中获取数据
代码演示如下:
```
public static void main(String[] args) throws Exception {
	DatagramSocket socket = new DatagramSocket(6666);//创建Socket相当创建码头,端口号用发送过来的
	
	DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建Packet相当于创建集装箱
	
	socket.receive(packet);//接货,接收数据
	
	byte[] arr = packet.getData();//获取数据,
	int len = packet.getLength();//获取有效字节个数
	System.out.println(new String(arr,0,len));
	socket.close();
}
```
总结:接收无非是用socket.receive(packet);所以前提条件你要提供接收货物的socket码头对象和接收货物的packet集装箱,
接收到货物之后就可以通过packet集装箱获取数据getData,另外演示的时候当然要先开接收端,然后发送才有数据显示出来,
可在命令行开两个窗口演示,先运行接收端,再运行发送端,效果明显,详情见截图

3.接收方获取ip和端口号:
代码演示如下:
```
    String ip = packet.getAddress().getHostAddress();
    int port = packet.getPort();
```

#26.07_网络编程(UDP传输优化)
接收端Receive:
代码演示如下:
```
public static void main(String[] args) {
	DatagramSocket socket = new DatagramSocket(6666);//创建socket相当于创建码头
	DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建packet相当于创建集装箱
	
	while(true) {//死循环不断接收演示
		socket.receive(packet);//接收货物
		byte[] arr = packet.getData();
		int len = packet.getLength();
		String ip = packet.getAddress().getHostAddress();
		System.out.println(ip + ":" + new String(arr,0,len));
	}
	
	//接收就不要关闭流了
}
```

发送端Send:
代码演示如下:
```
public static void main(String[] args) {
	DatagramSocket socket = new DatagramSocket();//创建socket相当于创建码头
	Scanner sc = new Scanner(System.in);
	
	while(true) {//死循环不断发送键盘录入数据,直到录入quit退出
		String str = sc.nextLine();
		if("quit".equals(str))
			break;
			
		//创建packet相当于创建集装箱:
        DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 6666);
		socket.send(packet);//发货
	}
	
	socket.close();//发送最后要关闭流
}
```
总结:这个UDP传输优化,无非就是加了死循环发送键盘录入数据和死循环接收数据,来优化演示效果而已

#26.08_网络编程(UDP传输多线程)
发送和接收在一个窗口完成:
代码演示如下:
```
public class Demo3_MoreThread {
	//同一个窗口开启发送线程和接收线程:
	public static void main(String[] args) {
		new Receive().start();
		new Send().start();
	}
}

class Receive extends Thread {
	public void run() {
	    try {
		    DatagramSocket socket = new DatagramSocket(6666);//创建socket相当于创建码头
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建packet相当于创建集装箱
			
			while(true) {
			    socket.receive(packet);//接收货物
				byte[] arr = packet.getData();
				int len = packet.getLength();
				String ip = packet.getAddress().getHostAddress();
				System.out.println(ip + ":" + new String(arr,0,len));
			}
			
			//接收不用关闭流
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

class Send extends Thread {
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket();//创建socket相当于创建码头
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				String str = sc.nextLine();
				if("quit".equals(str))
					break;
					
				DatagramPacket packet = //创建packet相当于创建集装箱
	new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 6666);
				socket.send(packet);//发货
			}
			
			socket.close();//发送要关闭流!!!
			
		}  catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
```
总结:无非实在同一个类里开启接收线程和发送线程,然后把发送和接收的代码写在各种线程类的run方法里而已,
这样就实现了发送和接收在一个窗口完成的演示

#26.09_网络编程(UDP聊天图形化界面)
代码演示如下:
```
public class Demo4_GUIChat extends Frame {//本类继承Frame就可以拿到Frame类里面的所有非私有方法了
    public Demo4_GUIChat() {
	    init();
		southPanel();
		centerPanel();
    }
    
    public void init() {
		this.setLocation(500, 50);
		this.setSize(400, 600);
		this.setVisible(true);
	}
	
    public void centerPanel() {
		Panel center = new Panel();//创建中间的Panel
		viewText = new TextArea();
		sendText = new TextArea(5,1);
		center.setLayout(new BorderLayout());//设置为边界布局管理器
		center.add(sendText,BorderLayout.SOUTH);//发送的文本区域放在南边
		center.add(viewText,BorderLayout.CENTER);//显示区域放在中间
		viewText.setEditable(false);//设置不可以编辑
		viewText.setBackground(Color.WHITE);//设置背景颜色
		sendText.setFont(new Font("xxx", Font.PLAIN, 15));
		viewText.setFont(new Font("xxx", Font.PLAIN, 15));
		this.add(center,BorderLayout.CENTER);
	}

	public void southPanel() {
		Panel south = new Panel();//创建南边的Panel
		tf = new TextField(15);
		tf.setText("127.0.0.1");
		send = new Button("发 送");
		log = new Button("记 录");
		clear = new Button("清 屏");
		shake = new Button("震 动");
		south.add(tf);
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
		this.add(south,BorderLayout.SOUTH);//将Panel放在Frame的南边
	}
}
```
总结:创建对象调用构造方法,构造方法里面调用功能即可,这里本类继承自Frame就是一个窗体了,可以用Frame里的非私有方法

#26.10_网络编程(UDP聊天发送功能)
代码演示如下:
```
send.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			send();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}

});

private void send(byte[] arr, String ip) throws IOException {
	DatagramPacket packet = 
			new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9999);
	socket.send(packet);//发送数据
}
	
private void send() throws IOException {
	String message = sendText.getText();//获取发送区域的内容
	String ip = tf.getText();//获取ip地址;
	ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;
	
	send(message.getBytes(),ip);
	
	String time = getCurrentTime();//获取当前时间
    String str = time + " 我对:" + (ip.equals("255.255.255.255") ? "所有人" : ip) + "说\r\n" + message + "\r\n\r\n";//alt + shift + l 抽取局部变量
	viewText.append(str);//将信息添加到显示区域中,追加方法,
	bw.write(str);//将信息写到数据库中,模拟效果其实是写到文件中,
	sendText.setText("");//控件清空数据
}

//这里少写了接收线程的代码,下面有,省点空间
```
总结:发送按钮增加动作监听,调用UDP的发送功能不断发送数据,同时控件显示,初始化是开机接收线程,线程定义在内部类中,
这样方便调用本类即Frame子类的所有方法
		
#26.11_网络编程(UDP聊天记录功能)
代码演示如下:
```
public static void main(String[] args) {
    new Demo4_GUIChat();
}

public Demo4_GUIChat() {
	init();
	southPanel();
	centerPanel();
	event();
}

public void init() {
	this.setLocation(500, 50);
	this.setSize(400, 600);
	new Receive().start();
	try {
		socket = new DatagramSocket();
		bw = new BufferedWriter(new FileWriter("config.txt",true));//需要在尾部追加
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	this.setVisible(true);
}

public void event() {
	this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				socket.close();
				bw.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			System.exit(0);
		}
	});
		
	send.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				send();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}

	});
		
	log.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				logFile();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}

	});
}

private void send() throws IOException {
    String message = sendText.getText();//获取发送区域的内容
	String ip = tf.getText();//获取ip地址;
	ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;
	
	send(message.getBytes(),ip);
	
	String time = getCurrentTime();//获取当前时间
    String str = time + " 我对:" + (ip.equals("255.255.255.255") ? "所有人" : ip) + "说\r\n" + message + "\r\n\r\n";//alt + shift + l 抽取局部变量
	viewText.append(str);//将信息添加到显示区域中
	bw.write(str);//将信息写到数据库中
	sendText.setText("");
}

private void send(byte[] arr, String ip) throws IOException {
	DatagramPacket packet = 
			new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9999);
	socket.send(packet);//发送数据
}

private void logFile() throws IOException {
	bw.flush();//刷新缓冲区
	FileInputStream fis = new FileInputStream("config.txt");
	ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存中创建缓冲区
	
	int len;
	byte[] arr = new byte[8192];
	while((len = fis.read(arr)) != -1) {
		baos.write(arr, 0, len);
	}
	
	String str = baos.toString();//将内存中的内容转换成了字符串
	viewText.setText(str);
	
	fis.close();
}

//定义一个内部类,可以获取外部类方法,接收和发送需要同时执行,所以定义成多线程的
private class Receive extends Thread {
public void run() {
		DatagramSocket socket = new DatagramSocket(9999);
		DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
		
		while(true) {
			socket.receive(packet);//接收信息
			byte[] arr = packet.getData();//获取字节数据
			int len = packet.getLength();//获取有效的字节数据
			if(arr[0] == -1 && len == 1) {//如果发过来的数组第一个存储的值是-1,并且数组长度是1
				shake();//调用震动方法
				continue;//终止本次循环,继续下次循环,因为震动后不需要执行下面的代码
			}
			String message = new String(arr,0,len);//转换成字符串
			
			String time = getCurrentTime();//获取当前时间
			String ip = packet.getAddress().getHostAddress();//获取ip地址
			String str = time + " " + ip + " 对我说:\r\n" + message + "\r\n\r\n";
			viewText.append(str);
			bw.write(str);
		}
	}
}	
```
总结:聊天记录无非是把发送的接收的内容都追加写出存储在一个文件中,然后给聊天记录按钮增加动作监听,
读取文件内容,写出到内存,然后从内存中取出数据,显示在控件上即可
	
#26.12_网络编程(UDP聊天清屏功能)
代码演示如下:
```
clear.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent e) {
		viewText.setText("");//控件设置为空清空
	}
});
```

#26.13_网络编程(UDP聊天震动功能)
代码演示如下:
```
private void shake() {
	int x = this.getLocation().x;//获取横坐标位置
	int y = this.getLocation().y;//获取纵坐标位置
	
	for(int i = 0; i < 20; i++) {
		try {
			this.setLocation(x + 20, y + 20);
			Thread.sleep(20);
			this.setLocation(x + 20, y - 20);
			Thread.sleep(20);
			this.setLocation(x - 20, y + 20);
			Thread.sleep(20);
			this.setLocation(x - 20, y - 20);
			Thread.sleep(20);
			this.setLocation(x, y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

//震动应该震动的是对方而不是自己,所以,震动监听里面应该发送一个信息给接收端,然后接收端判断是信息才震动:
shake.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			send(new byte[]{-1},tf.getText());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

});

private void send(byte[] arr, String ip) throws IOException {
	DatagramPacket packet = 
			new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9999);
	socket.send(packet);//发送数据
}

private class Receive extends Thread {//接收和发送需要同时执行,所以定义成多线程的
    public void run() {
    	try {
    		DatagramSocket socket = new DatagramSocket(9999);
    		DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
    		
    		while(true) {
    			socket.receive(packet);//接收信息
    			byte[] arr = packet.getData();//获取字节数据
    			int len = packet.getLength();//获取有效的字节数据
    			if(arr[0] == -1 && len == 1) {//如果发过来的数组第一个存储的值是-1,并且数组长度是1
    				shake();//调用震动方法
    				
    	            continue;//终止本次循环,继续下次循环,因为震动后不需要执行下面的代码,这个要注意考虑到--------------
    			}
    			
    			String message = new String(arr,0,len);//转换成字符串
    			String time = getCurrentTime();//获取当前时间
    			String ip = packet.getAddress().getHostAddress();//获取ip地址
    			
    			String str = time + " " + ip + " 对我说:\r\n" + message + "\r\n\r\n";
    			viewText.append(str);
    			bw.write(str);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
```
总结:震动应该震动的是对方而不是自己,所以,震动监听里面应该发送一个信息给接收端,然后接收端判断是信息才震动

#26.14_网络编程(UDP聊天快捷键和代码优化)
代码示范如下:
```
sendText.addKeyListener(new KeyAdapter() {
	@Override
	public void keyReleased(KeyEvent e) {
    	//if(e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown())//ctrl键是否被按下
    	
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {//如果键盘按下的是Enter键,就发送消息出去
			try {
				send();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
});


//代码优化:
private void send() throws IOException {
	String message = sendText.getText();//获取发送区域的内容
	String ip = tf.getText();//获取ip地址;
ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;//255.255.255.255广播地址,所有人都可以接收到,可以做群发
	
	send(message.getBytes(),ip);
	
	String time = getCurrentTime();//获取当前时间
    String str = time + " 我对:" + (ip.equals("255.255.255.255") ? "所有人" : ip) + "说\r\n" + message + "\r\n\r\n";	//alt + shift + l 抽取局部变量
    
	viewText.append(str);//将信息添加到显示区域中
	bw.write(str);//将信息写到数据库中
	sendText.setText("");
}
```
总结:按下Enter键发送信息和对所有人发送广播信息的代码在此,看看就好

###26.15_网络编程(UDP聊天生成jar文件)
    eclipse中选中源文件右键导出为jar包,指定文件,这时不要直接点finish,点next设置Main class为我们的类再finish即可:
    
    这时如何打开jar包运行程序?在命令行窗口输出java -jar jj.jar回车即可打开(jj为你jar包的名字):
    如果想直接打开请参考链接:http://jingyan.baidu.com/article/200957617c3619cb0621b44d.html
    或者看我发的文档26.15_网络编程(UDP聊天生成jar文件)3.docx
    
    UDP聊天程序总的代码见本文的最底部,这里就不写出来,节省点空间

#26.16_网络编程(TCP协议)(掌握)
1.客户端:

    创建Socket连接服务端(指定ip地址,端口号),通过ip地址找对应的服务器
	调用Socket的getInputStream()和getOutputStream()方法获取和服务端相连的IO流
	输入流可以读取服务端输出流写出的数据
	输出流可以写出数据到服务端的输入流
代码演示如下:
```
public class Demo1_Client {
	//客户端关键是创建Socket对象,然后用对象获取客户端的输入流和输出流,分别向服务器读写数据:
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 12345);
		
		InputStream is = socket.getInputStream();//获取客户端的输入流
		OutputStream os = socket.getOutputStream();//获取客户端的输出流
		
		byte[] arr = new byte[1024];
		int len = is.read(arr);//客户端的输入流,可以读取服务器发过来的数据,存放在字节数组里,
		System.out.println(new String(arr,0,len));//将数据转换成字符串,传入数组有效字节部分并打印输出
		
		os.write("学习挖掘机哪家强?".getBytes());//客户端的输出流,可以向服务器写出数据
		
		socket.close();
	}
}
```
总结:客户端关键是创建Socket对象,然后用对象获取客户端的输入流和输出流,分别向服务器读写数据,简单吧

2.服务端:

    创建ServerSocket(需要指定端口号)
	调用ServerSocket的accept()方法接收一个客户端请求,得到一个Socket
	调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的IO流
	输入流可以读取客户端输出流写出的数据
	输出流可以写出数据到客户端的输入流
代码示范如下：
```
public class Demo1_Server {
//服务端关键是创建ServerSocket对象调用accept方法得到Socket对象,然后用对象获取输出流和输入流,向客户端写读数据
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		
		Socket socket = server.accept();//接受客户端的请求,
		
		InputStream is = socket.getInputStream();//获取客户端输入流
		OutputStream os = socket.getOutputStream();//获取客户端的输出流
		
		os.write("百度一下你就知道".getBytes());//服务器向客户端写出数据
		
		byte[] arr = new byte[1024];
		int len = is.read(arr);//读取客户端发过来的数据,存储在字节数组中
		System.out.println(new String(arr,0,len));//将数据转换成字符串,传入数组有效字节部分并打印输出
		
		socket.close();
	}
}
```
总结:服务端关键是创建ServerSocket对象调用accept方法得到Socket对象,然后用对象获取输出流和输入流,向客户端写读数据

#26.17_网络编程(TCP协议代码优化)
客户端:
代码演示如下:
```
public static void main(String[] args) {
    Socket socket = new Socket("127.0.0.1", 9999);//创建Socket指定ip地址和端口号
    InputStream is = socket.getInputStream();//获取输入流
    OutputStream os = socket.getOutputStream();//获取输出流
    
    BufferedReader br = new BufferedReader(new InputStreamReader(is));//读取控制台键盘录入方式2,
    PrintStream ps = new PrintStream(os);//写出数据到控制台,模拟交互
    
    //2.客户端读取服务器写过来的数据,换行输出在控制台
    System.out.println(br.readLine());//因为要调用readLine读取一行方法,所以用BufferedReader
    ps.println("我想报名就业班");//3.客户端向服务器写出数据,
    System.out.println(br.readLine());//6.客户端读取服务器写过来的数据,换行输出在控制台
    ps.println("爷不学了");//7,客户端向服务器写出数据
    socket.close();
}
```

服务端:
代码演示如下:
```
public static void main(String[] args) {
    ServerSocket server = new ServerSocket(9999);//创建服务器
    Socket socket = server.accept();//接受客户端的请求
    
    InputStream is = socket.getInputStream();//获取输入流
    OutputStream os = socket.getOutputStream();//获取输出流
    
    BufferedReader br = new BufferedReader(new InputStreamReader(is));//读取控制台键盘录入数据,
    PrintStream ps = new PrintStream(os);////写出数据到控制台,模拟交互
    
    ps.println("欢迎咨询传智播客");//1.服务器向客户端写出数据,这里要换行,因为上面调用readLine方法
    System.out.println(br.readLine());//4.服务器读取客户端写过来的数据,换行输出在控制台
    ps.println("报满了,请报下一期吧");//5.服务器向客户端写出数据
    System.out.println(br.readLine());//8.服务器读取客户端写过来的数据,换行输出在控制台
    server.close();
    socket.close();
}
```
总结:TCP协议代码优化,无非是把原来的输入流和输出流分别包装在BufferedReader和PrintStream里面,然后调用更多方法,
过程演示我已经在上面代码中标号从1开始到8的过程所示,但是这里的写出到读取数据都是通过控制台演示,
即用BufferedReader特有构造读取用户从控制台录入的数据,用PrintStream把数据写到控制台,来模拟客户端与服务器交互!
这里要注意,ps.println("欢迎咨询传智播客");//1.服务器向客户端写出数据,这里要换行,因为上面调用readLine方法,因为
readLine方法读取一行的判断标记是换行符"\r\n",如果调用的是print方法就没有写出换行符,readLine方法遇不到,所以要
全部用ps.println方法,但是还要注意,readLine方法是读取一行,不包括换行符的

#26.18_网络编程(服务端是多线程的)(掌握)
服务端多线程:
代码演示如下:
```
public static void main(String[] args) {
    ServerSocket server = new ServerSocket(9999);//创建服务器
    
	while(true) {//死循环里面开启线程运行服务端代码,读取控制台键盘录入数据,然后输出到控制台,模拟交互↓
		final Socket socket = server.accept();//接受客户端的请求
		
		new Thread() {
			public void run() {
				try {
		            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintStream ps = new PrintStream(socket.getOutputStream());
					
					ps.println("欢迎咨询传智播客");
					System.out.println(br.readLine());
					ps.println("报满了,请报下一期吧");
					System.out.println(br.readLine());
					
					socket.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
```
总结:死循环里面开启线程运行服务端代码,用BufferedReader读取控制台键盘录入数据,然后输出语句输出读取内容到控制台,
再在控制台输出数据,模拟向客户端写出数据,来模拟服务器与客户端交互的过程,客户端代码也是这样模拟的,
另外注意,socket.close();//关闭socket就会关闭BufferedReader和PrintStream,不用再单独关闭!!!

#26.19_网络编程(练习)
客户端向服务器写字符串(键盘录入),服务器(多线程)将字符串反转后写回,客户端再次读取到是反转后的字符串:
代码演示如下:
```
public class Test1_Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);//创建键盘录入对象
		Socket socket = new Socket("127.0.0.1", 54321);//创建客户端,指定ip地址和端口号
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取输入流
		PrintStream ps = new PrintStream(socket.getOutputStream());//获取输出流
		
		ps.println(sc.nextLine());//将字符串写到服务器去
		System.out.println(br.readLine());//将服务器反转后的结果读出来
		
		socket.close();//关闭socket就会关闭BufferedReader和PrintStream,不用再单独关闭!!!
	}
}

public class Test1_Server {//多个类被public修饰在不同的源文件中
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(54321);
		System.out.println("服务器启动,绑定54321端口");
		
		while(true) {//服务器多线程
			final Socket socket = server.accept();//接受客户端的请求
			
			new Thread() {//开启一条线程
				public void run() {
					try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取输入流
                        PrintStream ps = new PrintStream(socket.getOutputStream());//获取输出流
                        
                        String line = br.readLine();//将客户端写过来的数据读取出来,
                        line = new StringBuilder(line).reverse().toString();//反转,链式编程调用方法
                        ps.println(line);//反转后写回到客户端去
                        
                        socket.close();
                        
					} catch (IOException e) {					
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
```
总结:就是客户端键盘录入数据,输出到控制台并写出到服务器,服务器读取客户端内容,将其进行反转,写出到客户端的过程

#26.20_网络编程(练习)
客户端向服务器上传文件:
客户端代码演示如下:
```
public class Test2_UpdateClient {
	//用write方法写出数据到服务器,服务器得到输入流读取,写到服务器定义的输出流传入文件中,完成所谓的上传:
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.提示输入要上传的文件路径,验证路径是否存在以及是否是文件夹
		File file = getFile();
		
		//2.发送文件名到服务端:
		Socket socket = new Socket("127.0.0.1", 12345);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps = new PrintStream(socket.getOutputStream());
		ps.println(file.getName());
		
		//6.接收结果,如果存在给予提示,程序直接退出:
		String result = br.readLine();//读取存在或不存在的结果
		if("存在".equals(result)) {
			System.out.println("您上传的文件已经存在,请不要重复上传");
			socket.close();
			
			return;
		}
		
		//7.如果不存在,定义FileInputStream读取文件,写出到网络即服务器端
		FileInputStream fis = new FileInputStream(file);
		byte[] arr = new byte[8192];
		int len;
		
		while((len = fis.read(arr)) != -1) {
            ps.write(arr, 0, len);//用write方法写出数据到服务器,服务器得到输入流读取,写到服务器定义的输出流传入文件中,完成上传
		}
		
		fis.close();
		socket.close();
		
	}

	private static File getFile() {
		Scanner sc = new Scanner(System.in);//创建键盘录入对象
		System.out.println("请输入一个文件路径:");
		
		while(true) {
			String line = sc.nextLine();
			File file = new File(line);
			
			if(!file.exists()) {
				System.out.println("您录入的文件路径不存在,请重新录入:");
			}else if(file.isDirectory()) {
				System.out.println("您录入的是文件夹路径,请输入一个文件路径:");
			}else {
				return file;
			}
		}
	}
	
}
```

服务器端代码演示如下:
```
public class Test2_UpdateServer {
	//服务器得到输入流读取客户端写过来的数据,写到服务器定义的输出流传入文件中,完成所谓的上传:
	public static void main(String[] args) throws IOException {
		//3,建立多线程的服务器
		ServerSocket server = new ServerSocket(12345);
		System.out.println("服务器启动,绑定12345端口号");
		
		//4.读取文件名
		while(true) {
			final Socket socket = server.accept();//接受客户端请求
			new Thread() {
				public void run() {
					try {
						InputStream is = socket.getInputStream();//得到输入流,输出流
					    BufferedReader br = new BufferedReader(new InputStreamReader(is));
						PrintStream ps = new PrintStream(socket.getOutputStream());
						String fileName = br.readLine();
						
						//5.判断文件是否存在,将结果发回客户端
						File dir = new File("update");
						dir.mkdir();//创建文件夹
						File file = new File(dir,fileName);//封装成File对象
						
						if(file.exists()) {//如果服务器已经存在这个文件
							ps.println("存在");//将存在写给客户端
							socket.close();//关闭socket
							return;//返回,表示不走下面逻辑
						}else {
							ps.println("不存在");
						}
						
						//8.定义FileOutputStream,从网络读取数据,存储到服务端本地:
						FileOutputStream fos = new FileOutputStream(file);
						byte[] arr = new byte[8192];
						int len;
						
						while((len = is.read(arr)) != -1) {
                            fos.write(arr, 0, len);//服务器得到输入流读取客户端写过来的数据,写到服务器定义的输出流传入文件中,完成上传
						}
						
						fos.close();
						socket.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
		}	
		
	}
	
}
```
总结:用write方法写出数据到服务器,服务器得到输入流读取,写到服务器定义的输出流传入文件中,完成所谓的上传,
这个代码要写一下,就会了解TCP协议传输数据的过程了,其实也很简单,对吧,但是要有注意点,就是方法的调用要准确

#26.21_day26总结
    把今天的知识点总结一遍:
    网络编程三要素IP,端口号port,协议protocol-IP和port组合在一起成socket套接字-UDP协议传输数据-TCP协议传输数据

学习完了TCP协议传输数据,我们再来回顾一下UDP协议聊天程序的案例,代码演示如下:
```
public class Demo4_GUIChat extends Frame {//本类继承自Frame窗体类,自己也是一个窗体,可以调用窗体类方法
	//控件
	private TextField tf;
	private Button send;
	private Button log;
	private Button clear;
	private Button shake;
	private TextArea viewText;
	private TextArea sendText;
	private DatagramSocket socket;
	private BufferedWriter bw;
	
	public Demo4_GUIChat() {//通过创建对象调用构造方法,构造方法调用功能实现布局和监听
		init();
		southPanel();
		centerPanel();
		event();
	}

	public void event() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					socket.close();
					bw.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}

		});
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logFile();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}

		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewText.setText("");
			}
		});
		
		shake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send(new byte[]{-1},tf.getText());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}

		});
		
		sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//if(e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown())//isControlDown ctrl键是否被按下
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						send();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
	}
	

	private void shake() {
		int x = this.getLocation().x;//获取横坐标位置
		int y = this.getLocation().y;//获取纵坐标位置
		
		for(int i = 0; i < 20; i++) {
			try {
				this.setLocation(x + 20, y + 20);
				Thread.sleep(20);
				this.setLocation(x + 20, y - 20);
				Thread.sleep(20);
				this.setLocation(x - 20, y + 20);
				Thread.sleep(20);
				this.setLocation(x - 20, y - 20);
				Thread.sleep(20);
				this.setLocation(x, y);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void logFile() throws IOException {
		bw.flush();//刷新缓冲区
		FileInputStream fis = new FileInputStream("config.txt");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存中创建缓冲区
		
		int len;
		byte[] arr = new byte[8192];
		while((len = fis.read(arr)) != -1) {
			baos.write(arr, 0, len);
		}
		
		String str = baos.toString();//将内存中的内容转换成了字符串
		viewText.setText(str);
		
		fis.close();
		
	}
	
	private void send(byte[] arr, String ip) throws IOException {
		DatagramPacket packet = 
				new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 9999);
		socket.send(packet);//发送数据
	}
	
	private void send() throws IOException {
		String message = sendText.getText();//获取发送区域的内容
		String ip = tf.getText();//获取ip地址;
		ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;
		
		send(message.getBytes(),ip);
		
		String time = getCurrentTime();//获取当前时间
		String str = time + " 我对:" + (ip.equals("255.255.255.255") ? "所有人" : ip) + "说\r\n" + message + "\r\n\r\n";//alt + shift + l 抽取局部变量
		
		viewText.append(str);//将信息添加到显示区域中
		bw.write(str);//将信息写到数据库中,模拟效果其实是写到文件中
		sendText.setText("");
		
	}
	
	private String getCurrentTime() {
		Date d = new Date();//创建当前日期对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(d);//将时间格式化
	}

	public void centerPanel() {
		Panel center = new Panel();//创建中间的Panel
		viewText = new TextArea();
		sendText = new TextArea(5,1);
		center.setLayout(new BorderLayout());//设置为边界布局管理器
		center.add(sendText,BorderLayout.SOUTH);//发送的文本区域放在南边
		center.add(viewText,BorderLayout.CENTER);//显示区域放在中间
		viewText.setEditable(false);//设置不可以编辑
		viewText.setBackground(Color.WHITE);//设置背景颜色
		sendText.setFont(new Font("xxx", Font.PLAIN, 15));
		viewText.setFont(new Font("xxx", Font.PLAIN, 15));
		this.add(center,BorderLayout.CENTER);
	}

	public void southPanel() {
		Panel south = new Panel();//创建南边的Panel
		tf = new TextField(15);
		tf.setText("127.0.0.1");
		send = new Button("发 送");
		log = new Button("记 录");
		clear = new Button("清 屏");
		shake = new Button("震 动");
		south.add(tf);
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
		this.add(south,BorderLayout.SOUTH);//将Panel放在Frame的南边
	}

	public void init() {
		this.setLocation(500, 50);
		this.setSize(400, 600);
		new Receive().start();
		try {
			socket = new DatagramSocket();
			bw = new BufferedWriter(new FileWriter("config.txt",true));//需要在尾部追加
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setVisible(true);
	}
	
	private class Receive extends Thread {//接收和发送需要同时执行,所以定义成多线程的
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(9999);
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				
				while(true) {
					socket.receive(packet);//接收信息
					byte[] arr = packet.getData();//获取字节数据
					int len = packet.getLength();//获取有效的字节数据
					if(arr[0] == -1 && len == 1) {//如果发过来的数组第一个存储的值是-1,并且数组长度是1
						shake();//调用震动方法
						continue;//终止本次循环,继续下次循环,因为震动后不需要执行下面的代码
					}
					
					String message = new String(arr,0,len);//转换成字符串
					String time = getCurrentTime();//获取当前时间
					String ip = packet.getAddress().getHostAddress();//获取ip地址
					String str = time + " " + ip + " 对我说:\r\n" + message + "\r\n\r\n";
					
					viewText.append(str);
					bw.write(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Demo4_GUIChat();//通过构造方法调用功能
	}
	
}
```

