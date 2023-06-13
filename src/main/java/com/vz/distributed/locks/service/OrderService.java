package com.vz.distributed.locks.service;

/**
 * @author visy.wang
 * @description:
 * @date 2023/3/30 16:06
 */
public interface OrderService {

    void createOrder(String orderId);

    boolean createOrder2(String orderId);
}
