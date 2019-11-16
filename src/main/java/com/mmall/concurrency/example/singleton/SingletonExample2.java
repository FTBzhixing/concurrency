package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

@ThreadSafe
//饿汉模式
//在类加载器中进行加载
public class SingletonExample2 {
    private SingletonExample2(){};

    private static SingletonExample2 instance = new SingletonExample2();

}
