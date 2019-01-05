package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2019/1/5
 */

public class DrawLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawlayout);

        final FrameLayout frameLayout = findViewById(R.id.id_framelayout);
        final DrawerLayout mDrawerLayout = findViewById(R.id.drawlayout);
        final LinearLayout mLinearDrawer = findViewById(R.id.id_drawer);
        Button mBtn = findViewById(R.id.id_btn1);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });


        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                Log.e("drawer", "slideOffset" + slideOffset + ",width=" + mLinearDrawer.getWidth());
                frameLayout.setX(slideOffset * mLinearDrawer.getWidth());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.e("drawer", "抽屉被完全打开了！");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Log.e("drawer", "抽屉被完全关闭了！");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.e("drawer", "drawer的状态：" + newState);
            }
        });
    }
}
