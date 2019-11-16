package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

@NotThreadSafe
//懒汉模式
public class SingletonExample1 {
    private SingletonExample1(){};

    private static SingletonExample1 instance = null;
    public static SingletonExample1 getInstance(){
        //这里可能会被两个线程同时拿到同时创建一个对象
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
