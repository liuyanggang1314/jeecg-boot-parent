package org.jeecg.modules.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.boot.starter.rabbitmq.core.BaseRabbiMqHandler;
import org.jeecg.boot.starter.rabbitmq.listenter.MqListener;
import org.jeecg.common.annotation.RabbitComponent;
import org.jeecg.common.base.BaseMap;
import org.jeecg.common.util.RedisUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Slf4j
@RabbitComponent(value = "redisListener")
public class RedisRabbitMqListener extends BaseRabbiMqHandler<BaseMap> {
    @Autowired
    private RedisUtil redisUtil;

    @RabbitListener(queues = "delRedisData")
    public void delRedisData(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
            @Override
            public void handler(BaseMap map, Channel channel) {
                String name = map.get("name");
                log.info("清除redis缓存:" + name);
                redisUtil.del(name);
            }
        });
    }

    @RabbitListener(queues = "hdelRedisData")
    public void hdelRedisData(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
            @Override
            public void handler(BaseMap map, Channel channel) {
                String name = map.get("name");
                String item = map.get("item");
                log.info("清除redis-hash缓存:" + name + " - " + item);
                redisUtil.hdel(name, item);
            }
        });
    }

}
