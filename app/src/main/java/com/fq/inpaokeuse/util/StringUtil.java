package com.fq.inpaokeuse.util;

/**
 * @author fengqing
 * @date 2018/4/5
 */


public class StringUtil {
    /**
     * 判断字符串非空或者非空格
     *
     * @param s
     * @return
     */
    public static boolean isNoNullOrNoBlank(String s) {
        return !((s == null) || (s.equals("")));
    }
}
