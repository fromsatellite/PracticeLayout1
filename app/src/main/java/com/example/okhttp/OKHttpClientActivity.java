package com.example.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hencoder.hencoderpracticelayout1.R;

/**
 * Created by leador_yang on 2018/6/6.
 */

public class OKHttpClientActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                String response = HttpUtils.requestGet("https://raw.github.com/square/okhttp/master/README.md");

//                String json = bowlingJson("Jesse", "Jake");
                String json = "{}";
                String response = HttpUtils.requestPost("http://httpbin.org/post", json);
                Log.d("@@@", "response = " + response);
            }
        }).start();
    }

    String bowlingJson(String player1, String player2){
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }
}
