package pers.ocean.samplemq.core.one;

import pers.ocean.samplemq.core.OceanMqMessage;

/**
 * @Description 第一个版本消息提供者
 * @Author ocean_wll
 * @Date 2021/8/1 4:22 下午
 */
public class OceanProducer {

    /**
     * OceanBroker对象
     */
    private final OceanBroker broker;

    public OceanProducer(OceanBroker broker) {
        this.broker = broker;
    }

    /**
     * 发送消息
     *
     * @param topic   topic
     * @param message 消息
     * @return true 发送成功，false 发送失败
     */
    public boolean send(String topic, OceanMqMessage message) {
        OceanMq oceanMq = this.broker.findOceanMq(topic);
        if (oceanMq == null) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return oceanMq.send(message);
    }
}
