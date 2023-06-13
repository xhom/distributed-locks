package com.vz.distributed.locks.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author visy.wang
 * @description:
 * @date 2023/3/30 16:06
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private RedissonClient redisson;

    private Long amount = 500L;

    @Override
    public void createOrder(String orderId) {
        boolean acquired = false;
        RLock lock = redisson.getLock("ORDER_LOCK_" + orderId);
        try{
            acquired = lock.tryLock(3, TimeUnit.SECONDS);
            if(acquired){
                log.info("线程:" + Thread.currentThread().getName() + "获得了锁");
                log.info("剩余数量:{}", --amount);
                Thread.sleep(5000);
            }else{
                log.info("线程:" + Thread.currentThread().getName() + "获取锁失败");
            }
        }catch (RedisTimeoutException e){
            log.info("线程:" + Thread.currentThread().getName() + "获取锁超时");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(acquired){
                log.info("线程:" + Thread.currentThread().getName() + "准备释放锁");
                lock.unlock();
            }
        }

    }

    @Override
    public boolean createOrder2(String orderId) {
        String key = "ORDER_" + orderId;
        return false;
    }
}
