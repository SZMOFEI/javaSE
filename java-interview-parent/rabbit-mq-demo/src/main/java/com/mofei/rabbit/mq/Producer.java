package com.mofei.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author com.mofei
 * @version 2020/4/8 23:46
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //默认的虚拟机MQ服务器
        connectionFactory.setVirtualHost("/");
//创建与RabbitMQ服务的TCP连接
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            /**
             * 声明队列，如果Rabbit中没有此队列将自动创建
             *  param1:队列名称
             * param2:是否持久化
             *  param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             ** param5:队列参数
             **/
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            StringBuilder stringBuilder = new StringBuilder();
            String message = "Hello World ";
            stringBuilder.append(message);
            for (int i = 0; i < 10000; i++) {
                stringBuilder.append(i);
                channel.basicPublish("", QUEUE_NAME, null, stringBuilder.toString().getBytes());
                int length = (i + "").length();
                stringBuilder.deleteCharAt(stringBuilder.length() - length);
                System.out.println(" [x] Sent '" + stringBuilder + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            //TODO 关闭channel
            //TODO 关闭connection
        }
    }

    @Test
    public void test() {
        String s = "hello world ";
        StringBuilder builder = new StringBuilder();
        builder.append(s);
        for (int i = 0; i < 3; i++) {
            StringBuilder append = builder.append(i);
            System.out.println("append = " + append);
            int length = (i + "").length();
//            System.out.println("length = " + length);
            append.deleteCharAt(append.length() - length);
//            System.out.println("append = " + append);
        }
    }
}

