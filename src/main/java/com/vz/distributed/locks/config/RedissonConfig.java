package com.vz.distributed.locks.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author visy.wang
 * @description: Redisson配置
 * @date 2023/3/30 14:18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redisson.single")
public class RedissonConfig {
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * redis数据库索引
     */
    private Integer database;
    /**
     * redis数据库密码
     */
    private String password;
    /**
     * redis地址（单节点）
     */
    private String address;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setClientName(clientName)
                .setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);
    }

}
