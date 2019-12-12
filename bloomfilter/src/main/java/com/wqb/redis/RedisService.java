package com.wqb.redis;

import com.google.common.base.Preconditions;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Description: 单个添加黑名单
     * @Param:
     * @return:
     * @Author: benwq
     * @Date: 2019/12/12
     */
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * @Description: 批量添加黑名单
     * @Param:
     * @return:
     * @Author: benwq
     * @Date: 2019/12/12
     */
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, List<T> valueList) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int perSize = 10000;
        if (valueList.size() <= perSize) {
            batchExec(bloomFilterHelper, key, valueList);
        } else {
            int times = (valueList.size() - valueList.size() % perSize) / perSize;
            for (int i = 1; i <= times; i++) {
                List<T> subList = valueList.subList((i - 1) * perSize, i * perSize <= valueList.size() ? i * perSize : valueList.size());
                batchExec(bloomFilterHelper, key, subList);
            }
        }
    }


    private <T> void batchExec(BloomFilterHelper<T> bloomFilterHelper, String key, List<T> valueList) {
        redisTemplate.executePipelined((RedisCallback) connection -> {
            connection.openPipeline();
            RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
            valueList.stream().forEach(value -> {
                int[] offset = bloomFilterHelper.murmurHashOffset(value);
                for (int i : offset) {
                    connection.setBit(keySerializer.serialize(key), i, true);
                }
            });
            connection.closePipeline();
            return null;
        });
    }

    /**
     * @Description: 根据给定的布隆过滤器判断值是否存在
     * @Param:
     * @return:
     * @Author: benwq
     * @Date: 2019/12/12
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }
}