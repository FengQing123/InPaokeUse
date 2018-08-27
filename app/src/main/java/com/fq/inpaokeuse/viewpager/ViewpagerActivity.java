package com.fq.inpaokeuse.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.widget.MyViewPagerIndicator;

/**
 * @author fengqing
 * @date 2018/7/17
 */

public class ViewpagerActivity extends AppCompatActivity {

    private static final String[] CONTENT = new String[]{"推荐课程", "我的课程"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        MyViewPagerIndicator indicator = (MyViewPagerIndicator) findViewById(R.id.indicator);
        indicator.setTabItemTitles(CONTENT);
        indicator.setViewPager(pager, 0);
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
