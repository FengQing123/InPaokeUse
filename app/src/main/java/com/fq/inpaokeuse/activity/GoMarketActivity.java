package com.fq.inpaokeuse.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.widget.dropmenu.DropPopMenu;
import com.fq.inpaokeuse.widget.dropmenu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqing
 * @date 2018/4/18
 */

public class GoMarketActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GoMarketActivity";
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        mContext = this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_market:
                showDropPopMenu(findViewById(R.id.btn_go_market));
//                String brand = PhoneUtil.getPhoneBrand();
//                String packageName = mContext.getPackageName();
//                Log.e(TAG, "onClick:brand= " + brand + ",packageName=" + packageName);
//                goToMarket(mContext, packageName, brand);
                break;
        }
    }

    public static void goToMarket(Context context, String packageName, String brand) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            if (!TextUtils.isEmpty(brand)) {
                if (brand.contains("Xiaomi")) {
                    goToMarket.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
                    context.startActivity(goToMarket);
                } else {
                    context.startActivity(goToMarket);
                }
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showDropPopMenu(View view) {
        DropPopMenu mDropPopMenu = new DropPopMenu(this);
        mDropPopMenu.setTriangleIndicatorViewColor(Color.WHITE);
        mDropPopMenu.setBackgroundResource(R.drawable.bg_drop_pop_menu_white_shap);
        mDropPopMenu.setItemTextColor(Color.BLACK);
        mDropPopMenu.setOnItemClickListener(new DropPopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, MenuItem menuItem) {
                Toast.makeText(mContext, "点击了 " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
            }
        });
        mDropPopMenu.setIsShowListDivider(true, R.drawable.drop_pop_item_divider);
        mDropPopMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.e(TAG, "onDismiss: dismiss");
            }
        });
        mDropPopMenu.setMenuList(getMenuList());
        mDropPopMenu.show(view);
    }

    private List<MenuItem> getMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(1, "  日  "));
        list.add(new MenuItem(2, "  月  "));
        list.add(new MenuItem(3, "  周  "));
        return list;
    }

}
