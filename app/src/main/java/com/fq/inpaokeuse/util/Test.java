package com.fq.inpaokeuse.util;

/**
 * @author fengqing
 * @date 2018/1/18
 */


public class Test {
    public static void main(String[] args) {
        TestCodeBlock block1 = new TestCodeBlock();
        block1.doMethod();

        TestCodeBlock block2 = new TestCodeBlock();
        block2.doMethod();
    }

    static {
        System.out.println("---static---");
    }

    {
        System.out.println("---non static---");
    }
}
