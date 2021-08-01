package pers.ocean.samplemq.demo.one;

import pers.ocean.samplemq.core.OceanMqMessage;
import pers.ocean.samplemq.core.one.OceanBroker;
import pers.ocean.samplemq.core.one.OceanConsumer;
import pers.ocean.samplemq.core.one.OceanProducer;

import java.util.concurrent.TimeUnit;

/**
 * @Description 示例代码
 * @Author ocean_wll
 * @Date 2021/8/1 4:47 下午
 */
public class OceanMqDemo {

    /**
     * 测试的topic
     */
    private static final String TOPIC_OCEAN = "ocean";

    public static void main(String[] args) {
        OceanBroker broker = new OceanBroker();
        broker.createTopic(TOPIC_OCEAN);

        // 创建生产者
        OceanProducer producer = broker.createProducer();
        OceanMqMessage<String> mqMessage = new OceanMqMessage<>();
        mqMessage.setBody("hello ocean!!!");
        producer.send(TOPIC_OCEAN, mqMessage);
        System.out.println("消息发送成功！");

        OceanConsumer<String> consumer = broker.createConsumer();
        consumer.subscribe(TOPIC_OCEAN);
        OceanMqMessage<String> consumeMsg = consumer.poll(10, TimeUnit.SECONDS);
        System.out.println("消费到的数据为：" + consumeMsg.getBody());
    }
}
