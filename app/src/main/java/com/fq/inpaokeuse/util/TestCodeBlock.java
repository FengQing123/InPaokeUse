package com.fq.inpaokeuse.util;

/**
 * @author fengqing
 * @date 2018/8/30
 */

public class TestCodeBlock {

    private static int age;

    static {
        System.out.println("静态代码块");
        age=10;
    }

    {
        System.out.println("非静态代码块");
    }

    public TestCodeBlock() {
        System.out.println("默认构造函数");
    }

    public void doMethod() {
        {
            System.out.println("方法内代码块");
        }
    }
}
