package com.fq.inpaokeuse.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    /**
     * 像素压缩图片，适应ImageView
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置Options的参数inJustDecodeBounds为true
        options.inJustDecodeBounds = true;
        //加载图片，这里只会解析图片的原始图片宽高信息，并不会真正的去加载图片
        BitmapFactory.decodeResource(res, resId);
        //计算inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //使用设置后的inSampleSize加载图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //原始图片的宽高
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight / inSampleSize) >= reqHeight) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
