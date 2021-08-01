package pers.ocean.samplemq.core.two;

import pers.ocean.samplemq.core.OceanMqMessage;

/**
 * @Description 第二个版本的mq消费者
 * @Author ocean_wll
 * @Date 2021/8/1 5:36 下午
 */
public class OceanConsumer<T> {

    /**
     * OceanBroker对象
     */
    private final OceanBroker broker;

    /**
     * OceanMq对象
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
     * 拉取mq消息
     *
     * @return OceanMqMessage
     */
    public OceanMqMessage<T> pull() {
        return this.oceanMq.pull();
    }

    /**
     * 指定offset拉取数据
     *
     * @param offset 偏移量
     * @return OceanMqMessage
     */
    public OceanMqMessage<T> seek(Integer offset) {
        return this.oceanMq.seek(offset);
    }

}
