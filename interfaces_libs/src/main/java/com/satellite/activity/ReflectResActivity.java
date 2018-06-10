package com.satellite.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.satellite.utils.ResourcesUtil;


/**
 * Created by leador_yang on 2018/6/9.
 */

public class ReflectResActivity extends Activity {

    private TextView tv_res;
    private int resId_tv = 0x7f0e008d;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_refelctres);
        try {
            ResourcesUtil.init(this.getApplicationContext());
            View var1 = ResourcesUtil.a(this, 0x7f04001f, (ViewGroup)null);
            setContentView(var1);

//        tv_res = (TextView) findViewById(R.id.tv_res);
//        int id = getResources().getIdentifier("btn" + 0, "id", getPackageName());
            // 插件包名是编译好的插件中AndroidManifest中的包名，getPackageName()是当前应用进程的包名
            int tvId = ResourcesUtil.a().getIdentifier("tv_res", "id", "com.hencoder.hencoderpracticelayout1");
            // getResources()会从当前运行的进程包的res中去找资源,肯定是找不到tv_res
//            int tvId = getResources().getIdentifier("tv_res", "id", "com.hencoder.hencoderpracticelayout1");
            Log.e("@@@", "tvId = " + tvId);
            tv_res = (TextView) var1.findViewById(tvId);
            tv_res.setText("activity_reflect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
