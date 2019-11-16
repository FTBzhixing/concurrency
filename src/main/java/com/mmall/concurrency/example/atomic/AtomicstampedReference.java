package com.mmall.concurrency.example.atomic;

public class AtomicstampedReference {
    //为了解决CAS操作时候的ABA问题，即有其他线程把一个变量由A改成了B又改成了A，本线程在进行CAS操作时也会改变该变量的值，
    // 但是此做法与CAS的核心思想不符合，于是AtomicstampedReference这个类就给变量加上一个版本号，在A改变的时候加1
}
