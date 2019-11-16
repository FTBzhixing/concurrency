package com.mmall.concurrency.example.singleton;

//枚举方式
public class SingletonExample7 {
    private SingletonExample7(){}
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
