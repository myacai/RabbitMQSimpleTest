package com.cai;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class CustomPriority {
    private final static String QUEUE_NAME = "queue_priority";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();


    }
}
