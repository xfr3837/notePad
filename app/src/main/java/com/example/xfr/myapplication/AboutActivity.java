package com.example.xfr.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 关于
 * Created by xfr on 2017/12/23.
 */

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /* 以下是彩蛋，希望女朋友在用这个软件的时候能发现。
        TextView textView = (TextView) findViewById(R.id.lax);
        textView.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                i++;
                if (i == 5)
                    Toast.makeText(AboutActivity.this, "亲爱哒，我超爱你\n( /) V (\\ ) 嘻嘻~", Toast.LENGTH_LONG).show();
                if (i == 20)
                    Toast.makeText(AboutActivity.this, "你完了，你妈让你嫁给我", Toast.LENGTH_LONG).show();
            }
        });*/
    }
}


