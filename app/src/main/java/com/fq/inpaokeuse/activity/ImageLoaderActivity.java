package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2019/7/8
 */

public class ImageLoaderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

//        //当前进程的可用内存，单位KB
//        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        //缓存的总容量，单位KB
//        int cacheSize = maxMemory / 8;
//        //创建LruCache缓存
//        LruCache mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
//                //计算Bitmap对象的内存大小并返回，单位KB
//                return value.getRowBytes() * value.getHeight() / 1024;
//            }
//        };
//
//
//        try {
//
//
//            long DISK_CACHE_SIZE = 1024 * 1024 * 50;
//            File diskCacheDir = getDiskCacheDir();
//            if (!diskCacheDir.exists()) {
//                diskCacheDir.mkdirs();
//            }
//            DiskLruCache mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
//
//
//            String url = "";
//            int DISK_CACHE_INDEX = 0;
//            String key = hashKeyFormUrl(url);
//            //通过edit()方法获取Editor对象
//            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
//            //如果这个缓存正在被编辑，那么edit()会返回null,即DiskLruCache不允许同时编辑一个缓存对象
//            if (editor != null) {
//                //由于open方法中设置了一个节点只能有一个数据，因此DISK_CACHE_INDEX常量直接设置为0
//                OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
//                //获取到的文件输出流写入文件系统上
//                if (downloadUrlToStream(url, outputStream)) {
//                    //写入成功，还必须通过Editor的commit()来提交写入操作
//                    editor.commit();
//                } else {
//                    //写入失败，使用Editor的abort()来回退整个操作
//                    editor.abort();
//                }
//            }
//            mDiskLruCache.flush();
//
//
//            Bitmap bitmap = null;
//            String key = hashKeyFormUrl(url);
//            //获取Snapshot对象
//            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
//            if (snapshot != null) {
//                //通过Snapshot对象得到缓存的文件输入流
//                FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
//                //通过文件流得到它所对应的文件描述符
//                FileDescriptor fileDescriptor = fileInputStream.getFD();
//                //获取缩放后的Bitmap
//                bitmap = decodeSampledBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
//                if (bitmap != null) {
//                    //添加到LruCache内存缓存中
//                    addBitmapToMemoryCache(key, bitmap);
//                }
//            }
//
//
//        } catch (Exception e) {
//
//        }
    }

//    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFileDescriptor(fd, null, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFileDescriptor(fd, null, options);
//    }
//
//    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
//
//        int IO_BUFFER_SIZE = 8 * 1024;
//        HttpURLConnection urlConnection = null;
//        BufferedInputStream in = null;
//        BufferedOutputStream out = null;
//
//        try {
//            URL url = new URL(urlString);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
//            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
//            int b;
//            while ((b = in.read()) != -1) {
//                out.write(b);
//            }
//            return true;
//        } catch (IOException e) {
//            //失败
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
//
//    private String hashKeyFormUrl(String url) {
//        String cacheKey;
//        try {
//            MessageDigest mDigest = MessageDigest.getInstance("MD5");
//            mDigest.update(url.getBytes());
//            cacheKey = byteToHexString(mDigest.digest());
//        } catch (NoSuchAlgorithmException e) {
//            cacheKey = String.valueOf(url.hashCode());
//        }
//
//        return cacheKey;
//    }
//
//    private String byteToHexString(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < bytes.length; i++) {
//            String hex = Integer.toHexString(0xFF & bytes[i]);
//            if (hex.length() == 1) {
//                sb.append("0");
//            }
//            sb.append(hex);
//        }
//        return sb.toString();
//    }
}
