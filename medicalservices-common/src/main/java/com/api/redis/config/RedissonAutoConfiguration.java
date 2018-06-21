package com.api.redis.config;


import com.api.redis.locker.DistributedLocker;
import com.api.redis.locker.impl.RedissonDistributedLocker;
import com.api.setting.RedissonSetting;
import com.api.util.RedissLockUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson+springboot 自动配置类
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonSetting.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonSetting redissonSetting;

//    /**
//     * 哨兵模式自动装配
//     * @return
//     */
//    @Bean
//    @ConditionalOnProperty(name="redisson.master-name")
//    RedissonClient redissonSentinel() {
//        Config config = new Config();
//        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redissonSetting.getSentinelAddresses())
//                .setMasterName(redissonSetting.getMasterName())
//                .setTimeout(redissonSetting.getTimeout())
//                .setMasterConnectionPoolSize(redissonSetting.getMasterConnectionPoolSize())
//                .setSlaveConnectionPoolSize(redissonSetting.getSlaveConnectionPoolSize());
//
//    	if(redssionProperties.getPassword() != null && !"".equals(redissonSetting.getPassword())) {
//            serverConfig.setPassword(redissonSetting.getPassword());
//        }
//        return Redisson.create(config);
//    }

    /**
     * 单机模式自动装配
     * @return
     */
    @Bean
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissonSetting.getAddress())
                .setTimeout(redissonSetting.getTimeout())
                .setConnectionPoolSize(redissonSetting.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonSetting.getConnectionMinimumIdleSize());

        if(redissonSetting.getPassword() != null && !"".equals(redissonSetting.getPassword())) {
            serverConfig.setPassword(redissonSetting.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     * @return
     */
    @Bean
    DistributedLocker distributedLocker(RedissonClient redissonClient) {
    	RedissonDistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(locker);
        return locker;
    }

}