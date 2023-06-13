package com.vz.distributed.locks.apis;

import com.vz.distributed.locks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author visy.wang
 * @description: 测试接口
 * @date 2023/3/31 13:35
 */
@RestController
@RequestMapping("/t")
public class TestController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/test1")
    public String test1(@RequestParam(name = "orderId") String orderId){
        orderService.createOrder(orderId);
        return "OK";
    }

    @RequestMapping("/test2")
    public Boolean test2(@RequestParam(name = "orderId") String orderId){
        return orderService.createOrder2(orderId);
    }
}
