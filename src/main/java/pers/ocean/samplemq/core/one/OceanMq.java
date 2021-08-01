package pers.ocean.samplemq.core.one;

import lombok.SneakyThrows;
import pers.ocean.samplemq.core.OceanMqMessage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description 第一个版本的mq，通过linkedBlockingQueue实现
 * @Author ocean_wll
 * @Date 2021/8/1 4:13 下午
 */
public final class OceanMq {

    /**
     * 主题
     */
    private String topic;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 阻塞队列
     */
    private LinkedBlockingQueue<OceanMqMessage> queue;

    public OceanMq(String topic, Integer capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    /**
     * 发送消息
     *
     * @param message 消息
     * @return true 发送成功，false 发送失败
     */
    public boolean send(OceanMqMessage message) {
        return queue.offer(message);
    }

    /**
     * 消费消息
     *
     * @return OceanMqMessage
     */
    public OceanMqMessage poll() {
        return queue.poll();
    }

    /**
     * 指定时间消费消息
     *
     * @param timeout 超时时间
     * @return OceanMqMessage
     */
    @SneakyThrows
    public OceanMqMessage poll(long timeout, TimeUnit timeUnit) {
        return queue.poll(timeout, timeUnit);
    }
}
