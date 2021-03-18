- 安装Erlang
- 配置Erlang环境变量
- 启动mq-service install start
- 启动 mq-plugin enable management 
- 访问地址localhost:15672
```$xslt
guest
guest
```
根据官方demo [quick-start](https://github.com/rabbitmq/rabbitmq-tutorials/blob/master/java/Recv.java)
- 加入pom依赖
```$xslt
         <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.7.3</version>
        </dependency>
```
- 编写Producer 启动

观察web端页面是否产生了hello queue

- 编写comsumer ,启动接收到了消息hello world