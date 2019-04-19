package com.cai;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ProductPriority {
    public final static String QUEUE_NAME="queue_priority";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

        //create exchange
        channel.exchangeDeclare("exchange_priority","direct",true);

        //create queue with priority
        Map<String,Object> argss = new HashMap<>();
        argss.put("x-max-priority", 10);
        channel.queueDeclare("queue_priority", true, false, false, argss);
        channel.queueBind("queue_priority", "exchange_priority", "rk_priority");


        //send message with priority
        for(int i=0;i<10;i++) {
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            if(i%2!=0)
                builder.priority(5);
            AMQP.BasicProperties properties = builder.build();
            channel.basicPublish("exchange_priority","rk_priority",properties,("messages-"+i).getBytes());
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
