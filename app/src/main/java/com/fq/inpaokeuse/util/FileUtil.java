package com.fq.inpaokeuse.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.fq.inpaokeuse.base.BaseApplication;

import java.io.File;
import java.io.IOException;

/**
 * @author fengqing
 * @date 2018/4/5
 */


public class FileUtil {

    /**
     * 获取手机cache目录路径（清除缓存就会被清理）
     *
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            /*  /mnt/sdcard/Android/data/com.paoke/cache  SD卡中，看的见*/
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            /*  /data/user/0/com.paoke/cache  看不见*/
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取手机files目录路径（清除数据就会被清理）
     *
     * @param context
     * @return
     */
    public static String getDiskFilesDir(Context context) {
        String filesPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            filesPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        } else {
            filesPath = context.getFilesDir().getPath();
        }
        return filesPath;
    }

    /**
     * 在cache目录下创建文件夹
     *
     * @param dirName 文件夹的名字
     * @return
     */
    public static String makeDirInCache(String dirName) {
        String cacheDirs = FileUtil.getDiskCacheDir(BaseApplication.getInstance());
        String imagePath = cacheDirs + "/" + dirName;
        File dir = new File(imagePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return imagePath;
    }

    /**
     * 在对应的路径创建文件是否成功
     *
     * @param dirPath  目录路径
     * @param filename 文件名
     * @return
     */
    public static boolean isMakeFile(String dirPath, String filename) {
        try {
            if (!TextUtils.isEmpty(dirPath) && !TextUtils.isEmpty(filename)) {
                File fileDir = new File(dirPath);
                if (!fileDir.exists()) {
                    fileDir.mkdir();
                }
                File file = new File(dirPath, filename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
