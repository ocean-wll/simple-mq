package pers.ocean.samplemq.demo.two;

import pers.ocean.samplemq.core.OceanMqMessage;
import pers.ocean.samplemq.core.two.OceanBroker;
import pers.ocean.samplemq.core.two.OceanConsumer;
import pers.ocean.samplemq.core.two.OceanProducer;

/**
 * @Description 第二版本mq的demo
 * @Author ocean_wll
 * @Date 2021/8/1 5:15 下午
 */
public class OceanMqDemo {

    /**
     * 定义测试的topic
     */
    private static final String TOPIC_WLL = "wll";

    public static void main(String[] args) {
        OceanBroker broker = new OceanBroker();
        broker.createTopic(TOPIC_WLL);

        // 创建生产者
        OceanProducer producer = broker.createProducer();
        for (int i = 0; i < 10; i++) {
            OceanMqMessage<String> mqMessage = new OceanMqMessage<>();
            mqMessage.setBody("hello " + i);
            producer.send(TOPIC_WLL, mqMessage);
        }

        // 创建消费者
        OceanConsumer consumer = broker.createConsumer();
        consumer.subscribe(TOPIC_WLL);
        for (int i = 0; i < 5; i++) {
            OceanMqMessage msgA = consumer.pull();
            System.out.println("consumer.pull: " + msgA.getBody());
        }
        OceanMqMessage msgB = consumer.seek(8);
        System.out.println("consumer.seek: " + msgB.getBody());
    }
}
