package pers.ocean.samplemq.core.one;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 第一个版本的broker
 * @Author ocean_wll
 * @Date 2021/8/1 4:20 下午
 */
public final class OceanBroker {

    /**
     * 定义默认容量大小为1000
     */
    private final Integer CAPACITY = 1000;

    /**
     * map对象用于存放topic与oceanMq的映射关系 key为topic，value为 OceanMq对象
     */
    private final Map<String, OceanMq> oceanMqMap = new ConcurrentHashMap<>(64);

    /**
     * 创建topic
     *
     * @param topic 消息主题
     */
    public void createTopic(String topic) {
        if (!StringUtils.hasLength(topic)) throw new RuntimeException("topic is not empty");
        if (oceanMqMap.get(topic) != null) throw new RuntimeException("topic already exists");
        oceanMqMap.putIfAbsent(topic, new OceanMq(topic, CAPACITY));
    }

    /**
     * 根据topic找到对应的 oceanMq
     *
     * @param topic 消息主题
     * @return OceanMq
     */
    public OceanMq findOceanMq(String topic) {
        return this.oceanMqMap.get(topic);
    }

    /**
     * 创建生产者
     *
     * @return OceanProducer
     */
    public OceanProducer createProducer() {
        return new OceanProducer(this);
    }

    /**
     * 创建消费者
     *
     * @return OceanConsumer
     */
    public OceanConsumer createConsumer() {
        return new OceanConsumer(this);
    }
}
