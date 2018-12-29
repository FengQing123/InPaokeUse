package com.fq.inpaokeuse.util;

/**
 * @author fengqing
 * @date 2018/1/18
 */


public class Test {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        try {
            int a = array[10];
            System.out.println("aaaa" + a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally");
        }
        System.out.println("last");
    }
}
