package com.wqb.amqp.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {
    @Autowired
    private Processor processor;


    @GetMapping("/sendMessage")
    public void process()
    {
        String message = new StringBuilder().append("now ").append(new Date()).toString();
        processor.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendObject")
    public void sendObject()
    {
        Person person = new Person();
        person.setName("张三");
        person.setAge(123);
        //process中已设置input
        processor.output().send(MessageBuilder.withPayload(person).build());
    }
}

class Person{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
