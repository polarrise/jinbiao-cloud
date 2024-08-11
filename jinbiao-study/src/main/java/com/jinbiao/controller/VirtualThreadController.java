package com.jinbiao.controller;

import com.jinbiao.service.VirtualThreadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Jinbiao
 * @Date：2024/8/11 15:31
 * @Desc：使用虚拟线程
 */
@RestController
@RequestMapping("/testVirtualThread")
@Api(value = " 测试虚拟线程",tags={"测试虚拟线程VirtualThreadController"})
public class VirtualThreadController {

    @Autowired
    private VirtualThreadService virtualThreadService;

    @GetMapping("/triggerVirtualThreadTask")
    public String triggerVirtualThreadTask() throws InterruptedException {
        virtualThreadService.testVirtualThreadTask();
        return "虚拟线程任务已触发！";
    }

    @GetMapping("/triggerNormalThreadTask")
    public String triggerNormalThreadTask() throws InterruptedException {
        virtualThreadService.testNormalThreadTask();
        return "普通线程任务已触发！";
    }
}
