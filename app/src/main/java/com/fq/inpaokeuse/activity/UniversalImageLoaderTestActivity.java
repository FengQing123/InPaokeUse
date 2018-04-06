package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.UILUtil;

/**
 * @author fengqing
 * @date 2018/2/12
 */


public class UniversalImageLoaderTestActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uil);

        mImageView = (ImageView) findViewById(R.id.image);
    }

    public void showImage(View view) {
        UILUtil.useDisplayImageOne(mImageView);
    }
}
