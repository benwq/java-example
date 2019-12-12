package com.wqb.controller;

import com.google.common.hash.Funnels;
import com.wqb.redis.BloomFilterHelper;
import com.wqb.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisService redisService;

    BloomFilterHelper<Integer> bloomFilterHelper = new BloomFilterHelper<>(Funnels.integerFunnel(), 100000, 0.05);

    @GetMapping("/test1")
    public Object test1(@RequestParam Integer number) {
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        int total = number;
        for (int i = 1; i <= total; i++) {
            list.add(i);
        }
        redisService.addByBloomFilter(bloomFilterHelper, "redis:bloomfilter", list);
        long end = System.currentTimeMillis();
        return (end - start);
    }

    @GetMapping("/test2")
    public Object test2(@RequestParam Integer number) {
        boolean exist = redisService.includeByBloomFilter(bloomFilterHelper, "redis:bloomfilter", number);
        return exist;
    }

    @GetMapping("/test3")
    public Object test3(@RequestParam Integer start, @RequestParam Integer end) {
        int count = 0;
        long startlong = System.currentTimeMillis();
        for (int i = start; i <= end; i++) {
            if (redisService.includeByBloomFilter(bloomFilterHelper, "redis:bloomfilter", i)) {
                count++;
            }
        }
        long endlong = System.currentTimeMillis();
        return count + "个单位漏网:" + (endlong - startlong);
    }
}
