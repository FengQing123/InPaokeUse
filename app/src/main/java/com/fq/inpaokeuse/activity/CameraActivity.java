package com.fq.inpaokeuse.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.FileUtil;
import com.fq.inpaokeuse.util.ImagUtil;

import java.io.File;

/**
 * @author fengqing
 * @date 2018/4/4
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    public final int CODE_TAKE_PHOTO = 1;//相机RequestCode
    public final int CODE_ALBUM_PHOTO = 2;//相册
    public final int CODE_CUT_PHOTO = 3;//截取

    private Activity mContext;
    private ImageView imageView;
    private Uri photoUri;
    private File imageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mContext = this;

        imageView = (ImageView) findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                doCamera();
                break;
            case R.id.btn_album:
                doAlbum();
                String s = Build.MANUFACTURER;
                Log.e("--", "onClick: s=" + s);
                break;
        }
    }

    private void doCamera() {
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        photoUri = getMediaFileUri(TYPE_TAKE_PHOTO);
        photoUri = getPhotoUri();
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
    }

    private void doAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(albumIntent, CODE_ALBUM_PHOTO);
    }

    private Uri getPhotoUri() {
        String dirName = FileUtil.makeDirInCache("UserHead");
        String imageName = "head.png";
        FileUtil.isMakeFile(dirName, imageName);
        imageFile = new File(dirName + "/" + imageName);
        return Uri.fromFile(imageFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_TAKE_PHOTO:
//                if (data != null) {
//                    if (data.hasExtra("data")) {
//                        Log.i("URI", "data is not null");
//                        Bitmap bitmap = data.getParcelableExtra("data");
//                        imageView.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
//                    }
//                } else {
//                    Log.i("URI", "Data is null");
//                    Bitmap bitmap = BitmapFactory.decodeFile(photoUri.getPath());
//                    imageView.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
//                }
                Uri uri;
                if (data != null) {
                    uri = data.getData();
                } else {
                    uri = Uri.fromFile(imageFile);
                }
                startPhotoZoom(mContext, uri);
                break;
            case CODE_ALBUM_PHOTO:
                if (data != null) {
                    Uri uri1 = data.getData();
                    startPhotoZoom(mContext, uri1);
                }
                break;
            case CODE_CUT_PHOTO:
                Bitmap bitmap = null;
                if (data != null) {
                    if (data.hasExtra("data")) {
                        bitmap = data.getParcelableExtra("data");
                    }
                } else {
                    bitmap = BitmapFactory.decodeFile(imageFile.getPath());
                }
                Bitmap sBitmap = ImagUtil.removeYuanjiao(bitmap, 500);
                Bitmap tBitmap = ImagUtil.scaleBitmapSize(sBitmap, 240, 240);
                imageView.setImageBitmap(tBitmap);
                break;

        }
    }

    /**
     * 相机、相册回来图片进行裁剪
     *
     * @param activity
     * @param uri
     */
    public void startPhotoZoom(Activity activity, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 裁剪图片的宽高
        intent.putExtra("outputX", 240);
        intent.putExtra("outputY", 240);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, CODE_CUT_PHOTO);
    }
}
