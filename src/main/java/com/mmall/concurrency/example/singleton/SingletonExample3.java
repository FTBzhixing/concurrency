package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;

@ThreadSafe
//懒汉模式线程安全类写法，不推荐，性能差
public class SingletonExample3 {
    private SingletonExample3(){};

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance(){
        //这里可能会被两个线程同时拿到同时创建一个对象
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
