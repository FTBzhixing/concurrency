package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;


@ThreadSafe
/**
 * 懒汉模式  双重同步锁单例模式
 */
public class SingletonExample5 {
    private SingletonExample5(){};

    //这里加上volatile可以防止JVM发生指令重排进而实现了线程安全
    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance(){
        if(instance == null){//双重检测机制
            synchronized (SingletonExample5.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
