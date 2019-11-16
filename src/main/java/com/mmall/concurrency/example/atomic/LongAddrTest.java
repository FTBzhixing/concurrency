package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
//LongAdder和AtomicLong很类似，在并发量大的时候AtomicInteger的底层实现是在一个死循环里不断的尝试修改数值，在并发量大的时候很容易修改错误，对性能造成影响
//在JVM中允许将long和double类型64位的读写操作拆成两个32位操作，LongAdder将一个热点数据分割成一个数组，每个数字对应一个哈希值，各自维护自己的数字，在取数时将整个数组求和
//但是LongAdder也有缺点，在统计的时候如何有并发更新，统计的数值可能会有误差，实际在高并发时应使用LongAdder，但是在并发数不高，但对数字要求准确时更推荐使用AtomicLong
public class LongAddrTest {

    //请求总数
    public static int clienttotal = 5000;
    //并发总数
    public static int threadtotal = 200;

    public static LongAdder count = new LongAdder();

    public static Logger log = LoggerFactory.getLogger(LongAddrTest.class);
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
                    add();
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
        log.info("count:{}",count);
    }
    private static void add(){
        //先增加再获取当前值,影响接受返回值操作
        //源码中的compareAndSwapInt很关键，是native方法
        count.increment();
        //先获取当前值再增加
//        count.getAndAccumulate();
    }
}
