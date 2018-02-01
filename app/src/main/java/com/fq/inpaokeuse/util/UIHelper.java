package com.fq.inpaokeuse.util;

import android.content.Context;
import android.content.Intent;

/**
 * @author fengqing
 * @date 2018/1/22
 */


public class UIHelper {
    public static void showActivity(Context mContext, Class clz) {
        Intent intent = new Intent();
        intent.setClass(mContext, clz);
        mContext.startActivity(intent);
    }
}
