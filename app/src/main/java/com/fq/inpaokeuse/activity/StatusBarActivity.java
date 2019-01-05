package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/12/29
 */

public class StatusBarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 使得布局延伸到状态栏和导航栏区域
         */
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            /**
//             * 设置状态栏和导航栏透明（最低版本21）
//             * 这里没有做延伸，只是简单的变透明
//             */
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
//            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.transparent));
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            /**
//             * 使状态栏和导航栏透明（最低版本19）
//             * 会做延伸，但状态栏和导航栏只是透明
//             */
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.layout_scorllview_stick);
    }
}
