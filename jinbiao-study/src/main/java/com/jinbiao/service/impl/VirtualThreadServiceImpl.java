package com.jinbiao.service.impl;

import com.jinbiao.service.VirtualThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @Author：Jinbiao
 * @Date：2024/8/11 15:36
 * @Desc：使用 SpringBoot + 虚拟线程将服务性能提升几百倍！
 * 虚拟线程简介
 * 虚拟线程是 Java 平台的一项创新特性。虚拟线程是一种轻量级的线程实现，它在操作系统层面并不对应真实的内核线程，
 * 而是由 JVM 进行管理和调度。这使得可以在不消耗大量系统资源的情况下创建大量的线程，从而能够更高效地处理并发任务。
 *
 * 虚拟线程与普通线程的区别：
 * 资源消耗：普通线程通常与操作系统的内核线程直接对应，创建和切换成本较高，资源消耗大。虚拟线程则轻量得多，创建和切换成本极低，能够创建大量的虚拟线程而不会导致系统资源紧张。
 * 调度方式：普通线程的调度由操作系统内核负责，而虚拟线程的调度由 JVM 管理，更加灵活高效。
 * 并发能力：由于虚拟线程的低消耗特性，可以创建更多的虚拟线程来处理并发任务，从而提高系统的并发处理能力。
 *
 * 测试结果：
 * 条件：tomcatd的max-threads: 500 最大线程数为500
 * 1.如果创建10000个线程，虚拟线程方法执行时间: 5668 毫秒，虚拟线程不会触发拒绝策略，普通线程会触发拒绝策略
 * 2.如果创建500个线程，虚拟线程方法执行时间：5042 毫秒， 普通线程方法执行时间: 280437 毫秒
 */
@Service
public class VirtualThreadServiceImpl implements VirtualThreadService {

    // 获取虚拟线程执行器
    @Autowired
    private ExecutorService virtualThreadExecutor;

    // 获取异步任务执行器
    @Autowired
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    /**
     * 创建线程数量
     */
    private static Integer THREAD_NUM = 1000;

    /**
     * 虚拟线程方法执行时间: 5042 毫秒
     * @throws InterruptedException
     */
    @Async("virtualThreadExecutor")
    public void testVirtualThreadTask() throws InterruptedException {
        Instant start = Instant.now();
        CountDownLatch taskLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            virtualThreadExecutor.execute(() -> {
                // 模拟耗时任务
                try {
                    Thread.sleep(1000);
                    System.out.println("虚拟线程任务完成！");
                    taskLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        taskLatch.await();
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("虚拟线程方法执行时间: " + duration + " 毫秒");
    }

    @Async("asyncTaskExecutor")
    public void testNormalThreadTask() throws InterruptedException {
        Instant start = Instant.now();
        CountDownLatch taskLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            asyncTaskExecutor.execute(() -> {
                // 模拟耗时任务
                try {
                    Thread.sleep(1000);
                    System.out.println("普通线程任务完成！");
                    taskLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        taskLatch.await();
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("普通线程方法执行时间: " + duration + " 毫秒");
    }
}
