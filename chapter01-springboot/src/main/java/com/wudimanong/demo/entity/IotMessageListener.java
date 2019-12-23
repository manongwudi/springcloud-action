package com.wudimanong.demo.entity;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author joe
 */
public class IotMessageListener implements MessageListener {

    IotMessageTask iotMessageTask;

    public IotMessageListener(IotMessageTask iotMessageTask) {
        this.iotMessageTask = iotMessageTask;
    }

    //实现消息发布监听处理方法
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("subscribe redis iot task response:{}" + message.toString());
        //线程阻塞完成
        iotMessageTask.getIotMessageFuture().complete(message);
    }
}
