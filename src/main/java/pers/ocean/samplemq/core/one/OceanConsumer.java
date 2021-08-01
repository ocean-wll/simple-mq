package pers.ocean.samplemq.core.one;

import pers.ocean.samplemq.core.OceanMqMessage;

import java.util.concurrent.TimeUnit;

/**
 * @Description 第一个版本的消息消费者
 * @Author ocean_wll
 * @Date 2021/8/1 4:23 下午
 */
public class OceanConsumer<T> {

    /**
     * OceanBroker对象
     */
    private final OceanBroker broker;

    /**
     * 队列对象
     */
    private OceanMq oceanMq;

    public OceanConsumer(OceanBroker broker) {
        this.broker = broker;
    }

    /**
     * 订阅topic
     *
     * @param topic topic
     */
    public void subscribe(String topic) {
        this.oceanMq = this.broker.findOceanMq(topic);
        if (this.oceanMq == null) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    /**
     * 获取消息
     *
     * @param timeout  超时时间
     * @param timeUnit 超时时间单位
     * @return OceanMqMessage
     */
    public OceanMqMessage<T> poll(long timeout, TimeUnit timeUnit) {
        return this.oceanMq.poll(timeout, timeUnit);
    }


}
