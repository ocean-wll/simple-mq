package pers.ocean.samplemq.core.two;

import pers.ocean.samplemq.core.OceanMqMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 第二个版本的mq，基于list实现，引入offset
 * @Author ocean_wll
 * @Date 2021/8/1 5:17 下午
 */
public final class OceanMq {

    /**
     * 主题
     */
    private String topic;

    /**
     * 写指针的下标
     */
    private final AtomicInteger writeIndex;

    /**
     * 读指针的下标
     */
    private final AtomicInteger readIndex;

    /**
     * 消息存储集合
     */
    private List<OceanMqMessage> list;

    public OceanMq(String topic) {
        this.topic = topic;
        this.list = new CopyOnWriteArrayList<>();
        this.writeIndex = new AtomicInteger(0);
        this.readIndex = new AtomicInteger(0);
    }

    /**
     * 发送消息
     *
     * @param mqMessage OceanMqMessage
     */
    public void send(OceanMqMessage mqMessage) {
        synchronized (this.writeIndex) {
            this.list.add(writeIndex.get(), mqMessage);
            writeIndex.incrementAndGet();
        }
    }

    /**
     * 拉取消息
     *
     * @return OceanMqMessage
     */
    public OceanMqMessage pull() {
        synchronized (this.readIndex) {
            if (this.readIndex.get() == this.writeIndex.get()) return null;
            OceanMqMessage msg = this.list.get(readIndex.get());
            readIndex.incrementAndGet();
            return msg;
        }
    }

    /**
     * 根据偏移量获取数据
     *
     * @param offset 偏移量
     * @return OceanMqMessage
     */
    public OceanMqMessage seek(Integer offset) {
        if (offset < 0 || offset >= writeIndex.get()) throw new IllegalArgumentException("offset Illegal");
        return this.list.get(offset);
    }
}
