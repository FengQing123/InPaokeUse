package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.adapter.PictureAdapter;
import com.fq.inpaokeuse.bean.Picture;
import com.fq.inpaokeuse.widget.Rotate3DAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqing
 * @date 2018/2/1
 */

public class Animation3DActivity extends AppCompatActivity {

    /**
     * 根布局
     */
    private RelativeLayout layout;

    /**
     * 用于展示图片列表的ListView
     */
    private ListView picListView;

    /**
     * 用于展示图片详细的ImageView
     */
    private ImageView picture;

    /**
     * 图片列表的适配器
     */
    private PictureAdapter adapter;

    /**
     * 存放所有图片的集合
     */
    private List<Picture> picList = new ArrayList<Picture>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_animation);
        // 对图片列表数据进行初始化操作
        initPics();
        layout = findViewById(R.id.layout);
        picListView = findViewById(R.id.pic_list_view);
        picture = (ImageView) findViewById(R.id.picture);
        adapter = new PictureAdapter(this, 0, picList);
        picListView.setAdapter(adapter);
        picListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 当点击某一子项时，将ImageView中的图片设置为相应的资源
                picture.setImageResource(picList.get(position).getResource());
                // 获取布局的中心点位置，作为旋转的中心点
                float centerX = layout.getWidth() / 2f;
                float centerY = layout.getHeight() / 2f;
                // 构建3D旋转动画对象，旋转角度为0到90度，这使得ListView将会从可见变为不可见
                final Rotate3DAnimation rotation = new Rotate3DAnimation(0, 90, centerX, centerY,
                        300.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new TurnToImageView());
                layout.startAnimation(rotation);
            }
        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取布局的中心点位置，作为旋转的中心点
                float centerX = layout.getWidth() / 2f;
                float centerY = layout.getHeight() / 2f;
                // 构建3D旋转动画对象，旋转角度为360到270度，这使得ImageView将会从可见变为不可见，并且旋转的方向是相反的
                final Rotate3DAnimation rotation = new Rotate3DAnimation(180, 90, centerX,
                        centerY, 300.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new TurnToListView());
                layout.startAnimation(rotation);
            }
        });
    }

    /**
     * 初始化图片列表数据。
     */
    private void initPics() {
        Picture bird = new Picture("Bird", R.mipmap.ic_launcher_round);
        picList.add(bird);
        Picture winter = new Picture("Winter", R.mipmap.ic_launcher_round);
        picList.add(winter);
        Picture autumn = new Picture("Autumn", R.mipmap.ic_launcher_round);
        picList.add(autumn);
        Picture greatWall = new Picture("Great Wall", R.mipmap.ic_launcher_round);
        picList.add(greatWall);
        Picture waterFall = new Picture("Water Fall", R.mipmap.ic_launcher_round);
        picList.add(waterFall);
    }

    /**
     * 注册在ListView点击动画中的动画监听器，用于完成ListView的后续动画。
     *
     * @author guolin
     */
    class TurnToImageView implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            // 将ListView隐藏
            picListView.setVisibility(View.GONE);
            // 将ImageView显示
            picture.setVisibility(View.VISIBLE);
            picture.requestFocus();
            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            final Rotate3DAnimation rotation = new Rotate3DAnimation(90, 180, centerX, centerY,
                    300.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }

    /**
     * 注册在ImageView点击动画中的动画监听器，用于完成ImageView的后续动画。
     *
     * @author guolin
     */
    class TurnToListView implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ImageView的动画完成后，还需要再启动ListView的动画，让ListView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            // 将ImageView隐藏
            picture.setVisibility(View.GONE);
            // 将ListView显示
            picListView.setVisibility(View.VISIBLE);
            picListView.requestFocus();
            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
            final Rotate3DAnimation rotation = new Rotate3DAnimation(90, 0, centerX, centerY,
                    300.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}
