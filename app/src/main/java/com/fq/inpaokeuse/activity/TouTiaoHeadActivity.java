package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/3/17
 */

public class TouTiaoHeadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toutiao);


//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        List<Fragment> fragments = new ArrayList<Fragment>();
//        for (int i = 0; i < titles.length; i++) {
//            Fragment fragment = new MyFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("text", titles[i]);
//            fragment.setArguments(bundle);
//            fragments.add(fragment);
//        }
//        viewPager.setAdapter(new TabFragmentAdapter(fragments, titles, getSupportFragmentManager(), this));

    }
}


//class TabFragmentAdapter extends FragmentPagerAdapter {
//
//    private final String[] titles;
//    private Context context;
//    private List<Fragment> fragments;
//
//    public TabFragmentAdapter(List<Fragment> fragments, String[] titles, FragmentManager fm, Context context) {
//        super(fm);
//        this.context = context;
//        this.fragments = fragments;
//        this.titles = titles;
//    }
//
//
//    @Override
//    public Fragment getItem(int position) {
//        return fragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return titles.length;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }
//}
