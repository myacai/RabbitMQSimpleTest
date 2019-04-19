package com.cai;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("192.168.163.130");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        factory.setPort(5672);
        //创建一个新的连接
        return factory.newConnection();
    }
}
