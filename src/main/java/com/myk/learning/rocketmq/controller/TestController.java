package com.myk.learning.rocketmq.controller;

import com.myk.learning.rocketmq.mq.Producer;
import com.myk.learning.rocketmq.mq.delay.DelayProducer;
import com.myk.learning.rocketmq.mq.filter.FilterProducer;
import com.myk.learning.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in 2019.04.03
 *
 * @author myk
 */
@RestController
public class TestController {
    /**
     * The Producer.
     */
    @Autowired
    Producer producer;

    @Autowired
    DelayProducer delayProducer;

    @Autowired
    FilterProducer filterProducer;

    /**
     * Test send api result.
     *
     * @param msg the msg
     * @return the api result
     */
    @RequestMapping(value = "/send")
    public ApiResult testSend(@RequestParam String msg) {
        String result = producer.sendMsg("PushTopic", "push", msg);
        return ApiResult.prepare().success(result);
    }

    @RequestMapping(value = "/sendDelay")
    public ApiResult testSendDelay(String msg) {
        String result = delayProducer.sendDelayMsg(msg);
        return ApiResult.prepare().success(result);
    }

    @RequestMapping(value = "/sendFilter")
    public ApiResult testSendFilter(String msg) {
        String result = filterProducer.sendConditionMsg(msg);
        return ApiResult.prepare().success(result);
    }
}
