package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;


@NotThreadSafe
//线程2可能会调用线程1刚刚初始化好的对象进行发布
public class SingletonExample4 {
    private SingletonExample4(){};

    //一个类的加载过程
    //1.memory = allocate()分配对象的内存空间
    //2.ctorInstance()初始化对象
    //3.instance = memory 设置instance指向刚分配的内存
    //上述步骤在单线程的时候是线程安全的，但是在多线程中，JVM和cpu优化发生了指令重排

    //1.memory = allocate()分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance()初始化对象

    private static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance(){
        if(instance == null){//双重检测机制
            synchronized (SingletonExample4.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
