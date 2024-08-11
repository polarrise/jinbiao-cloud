package com.jinbiao.service;

/**
 * @Author：Jinbiao
 * @Date：2024/8/11 15:35
 * @Desc：
 */
public interface VirtualThreadService {


    /**
     * 测试虚拟线程表现
     */
    void testVirtualThreadTask() throws InterruptedException;

    /**
     * 测试普通线程表现
     */
    void testNormalThreadTask() throws InterruptedException;
}
