package com.wqb.amqp.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Processor.class)
public class StreamReceiver {
    @StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public String process(String message)
    {
        System.out.println(message);
        return "我是回执";
    }
    //@StreamListener(Processor.OUTPUT)
    //public void process1(Person person)
    //{
    //    System.out.println(person);
    //    log.info("process1: StreamReceiver:{}",person);
    //}

    @StreamListener(Processor.INPUT)
    public void process2(String message)
    {
        System.out.println(message);
    }
}
