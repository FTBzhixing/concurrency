package com.mmall.concurrency.example.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public static Logger log = LoggerFactory.getLogger(UnsafePublish.class);

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        //当对象发布后，若有其他线程改动了该对象中某个成员变量的值时，其他线程使用该对象时就不是最开始需要的那个
        //此种方式发布的对象就是线程不安全的
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
