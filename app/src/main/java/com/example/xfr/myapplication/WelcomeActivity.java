package com.example.xfr.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 欢迎界面
 * Created by xfr on 2017/12/24.
 */
public class WelcomeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); //加载启动图片
        Handler handler = new Handler();
        handler.postDelayed((new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }), 1000);
    }
}
