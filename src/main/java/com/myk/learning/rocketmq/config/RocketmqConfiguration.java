package com.myk.learning.rocketmq.config;

import com.myk.learning.rocketmq.mq.Producer;
import com.myk.learning.rocketmq.mq.delay.DelayProducer;
import com.myk.learning.rocketmq.mq.filter.FilterProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created in 2019.04.03
 *
 * @author myk
 */
@Configuration
public class RocketmqConfiguration {

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    /**
     * Mq producer default mq producer.
     *
     * @return the default mq producer
     */
    @Bean
    public DefaultMQProducer mqProducer() {
        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer("testGroup1");
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            System.out.println("-------->:producer启动了");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

    /**
     * Producer producer.
     *
     * @return the producer
     */
    @Bean
    public Producer producer() {
        return new Producer(mqProducer());
    }

    @Bean
    public DelayProducer delayProducer() {
        return new DelayProducer(mqProducer());
    }

    @Bean
    public FilterProducer filterProducer() {
        return new FilterProducer(mqProducer());
    }
}
