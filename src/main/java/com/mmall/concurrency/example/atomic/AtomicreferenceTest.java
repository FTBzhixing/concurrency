package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@Slf4j
@ThreadSafe
public class AtomicreferenceTest {

    private  static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);//2
        count.compareAndSet(0,1);//no
        count.compareAndSet(1,3);//no
        count.compareAndSet(2,4);//4
        count.compareAndSet(3,5);//no
        System.out.println("count:"+count.get());
    }
}
