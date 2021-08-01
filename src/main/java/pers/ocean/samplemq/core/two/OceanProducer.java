package pers.ocean.samplemq.core.two;

import pers.ocean.samplemq.core.OceanMqMessage;

/**
 * @Description 第二个版本的mq生产者
 * @Author ocean_wll
 * @Date 2021/8/1 5:35 下午
 */
public class OceanProducer {

    /**
     * OceanBroker对象
     */
    private OceanBroker broker;

    public OceanProducer(OceanBroker broker) {
        this.broker = broker;
    }

    /**
     * 发送消息
     *
     * @param topic   topic
     * @param message 消息
     */
    public void send(String topic, OceanMqMessage message) {
        OceanMq oceanMq = this.broker.findOceanMq(topic);
        if (oceanMq == null) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        oceanMq.send(message);
    }
}
