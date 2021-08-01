package pers.ocean.samplemq.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @Description mq消息对象
 * @Author ocean_wll
 * @Date 2021/8/1 4:10 下午
 */
@Data
public class OceanMqMessage<T> {

    /**
     * 消息头信息
     */
    private Map<String, Object> headers;

    /**
     * 消息体
     */
    private T body;
}
