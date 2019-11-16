package com.mmall.concurrency.example.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

//该类是为了保证某一段代码不管有多少次并发都只执行一次
public class AtomicBoolenTest {

    private static AtomicBoolean isHappened = new AtomicBoolean();

    //请求总数
    public static int clienttotal = 5000;
    //并发总数
    public static int threadtotal = 200;

    public static Logger log = LoggerFactory.getLogger(AtomicExample1.class);;

    public static void main(String[] args) throws InterruptedException {
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadtotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clienttotal);
        //lamada表达式写法
        for(int i = 0;i < clienttotal; i++){
            executorService.execute(() ->{
                try{
                    //引入信号量,达到一定并发数时，可能会阻塞add
                    semaphore.acquire();;
                    test();
                    //信号量释放
                    semaphore.release();

                }catch (Exception e){
                    log.error("Exception");
                }
                //闭锁 每执行一次，其中的计数值都会减少一个
                countDownLatch.countDown();
            });
        }
        //当计数器减为0时，让线程等待
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("ishappended:{}",isHappened.get());
    }
    private static void test(){
        if(isHappened.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
