package com.fq.inpaokeuse.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.GlideUtil;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 该类主要测试Glide最新库4.4.0的使用方法
 *
 * @author fengqing
 * @date 2018/2/11
 */

public class GlideTestActivity extends AppCompatActivity {

    private Context mContext;
    private ImageView imageView;
    private String url = "http://guolin.tech/book.png";
    private String baiduUrl = "https://www.baidu.com/img/bd_logo1.png";
    private String url2 = "http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mContext = this;
        imageView = findViewById(R.id.image_view);
    }

    public void loadImage(View view) {
//        GlideUtil.loadImageInCircle(mContext, url2, imageView);

        RequestOptions options = new RequestOptions().dontTransform().transform(new RoundedCornersTransformation(100, 200));
        GlideUtil.loadImageWithOptions(mContext, url2, imageView, options);


//        GlideUtil.loadImageInSpecailSize(mContext, baiduUrl, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, imageView);
    }

}
