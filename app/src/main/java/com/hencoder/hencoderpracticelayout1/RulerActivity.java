package com.hencoder.hencoderpracticelayout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by leador_yang on 2018/4/15.
 */

public class RulerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_ruler);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        final int pos = getIntent().getBundleExtra("BundlePage").getInt("position");
        bundle.putInt("position", pos);
        intent.putExtra("BundlePage", bundle);
        setResult(2, intent);
        super.onBackPressed();
    }
}
