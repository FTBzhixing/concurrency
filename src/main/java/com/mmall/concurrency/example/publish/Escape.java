package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;

    public static Logger log = LoggerFactory.getLogger(UnsafePublish.class);

    private class InnerClass{
        //这里是在构造函数过程中就使用个类的引用，使他可以被其他线程所见，是线程不安全的
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }
    public Escape(){
        new InnerClass();
    }

    public static void main(String[] args) {
        Escape escape = new Escape();
    }
}
