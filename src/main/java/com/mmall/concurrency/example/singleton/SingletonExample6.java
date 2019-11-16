package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

@ThreadSafe
//饿汉模式
//在静态代码块进行加载
public class SingletonExample6 {
    private SingletonExample6(){};
    //这里和static的顺序不能搞错
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }
    public static SingletonExample6 getInstance(){
        return instance;
    }

}
