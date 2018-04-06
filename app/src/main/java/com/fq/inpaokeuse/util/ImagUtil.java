package com.fq.inpaokeuse.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author fengqing
 * @date 2018/4/5
 */


public class ImagUtil {
    /**
     * 保存bitmap图片
     *
     * @param dirPath  目录路径
     * @param filename 文件名
     * @param bitmap   图片资源
     */
    public static void saveBitmapWithPath(String dirPath, String filename, Bitmap bitmap) {
        try {
            if (StringUtil.isNoNullOrNoBlank(dirPath) && StringUtil.isNoNullOrNoBlank(filename) && bitmap != null) {
                if (FileUtil.isMakeFile(dirPath, filename)) {
                    File file = new File(dirPath, filename);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置背景为圆角
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap removeYuanjiao(Bitmap bitmap, int pixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap creBitmap = Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(creBitmap);

        Paint paint = new Paint();
        float roundPx = pixels;
        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return creBitmap;
    }

    /**
     * 裁剪图片大小
     *
     * @param bitmap
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    public static Bitmap scaleBitmapSize(Bitmap bitmap, int dstWidth, int dstHeight) {
        Bitmap dst = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, true);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return dst;
    }
}
