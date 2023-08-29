package com.example.laddingwu.adapterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv2, tv1, tv3, tv4;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        view = findViewById(R.id.view_show);
        view.setOnClickListener(this);

        tv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                Log.e("查看宽度分辨率：", String.valueOf(dm.widthPixels));
                Log.e("查看高度分辨率：", String.valueOf(dm.heightPixels));
                String reswidth = String.valueOf(DensityUtil.px2dip(MainActivity.this, dm.widthPixels));
                String resheigh = String.valueOf(DensityUtil.px2dip(MainActivity.this, dm.heightPixels));
                Log.e("转成DP的宽：", "www" + reswidth);
                Log.e("转成DP的高：", "ggg" + resheigh);
                int width = Math.min(dm.widthPixels, dm.heightPixels);
                tv1.setText("分辨率：" + dm.widthPixels + "*" + dm.heightPixels + " 屏幕 dpi是 : " + dm.densityDpi + "   smallest width pixels : " + width);
                tv2.setText("计算出来的smallestWidth : " + width / (dm.densityDpi / 160.0) + "dp");
                tv3.setText("实际使用的smallestWidth :  " + getResources().getString(R.string.base_dpi));
                tv4.setText("当前手机： " + SystemUtil.getDeviceBrand() + "  " + SystemUtil.getSystemModel() + " \n" + "当前系统： " + SystemUtil.getSystemVersion() + "转出dp ");
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) view.getLayoutParams();
//                p.width = getResources().getDimensionPixelSize(R.dimen.qb_px_375);
//
//                view.setLayoutParams(p);
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
//            case R.id.view_show:
//                LogUtils.e("测试一下");
//                Intent intent = new Intent(this, NavUtilsActivity.class);
//                startActivity(intent);
//                break;
        }

    }
}
