package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.dialog.BaseDialog;

/**
 * @author fengqing
 * @date 2019/1/14
 */

public class DialogFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog_fragment);
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDialog dialog = new BaseDialog();
                dialog.show(getSupportFragmentManager(), "tag");
            }
        });
    }
}
