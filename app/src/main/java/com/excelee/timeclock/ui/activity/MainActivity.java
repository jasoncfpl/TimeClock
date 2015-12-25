package com.excelee.timeclock.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.excelee.timeclock.ui.R;

public class MainActivity extends AppCompatActivity {

    /** 添加闹钟按钮 **/
    Button btn_addClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    /**
     * 初始化view
     */
    public void initView(){

        btn_addClock = (Button) findViewById(R.id.mainactivity_btn_addclock);

        btn_addClock.setOnClickListener(new MyClickListener());

    }

    /**
     * 点击事件
     */
    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("TAG","Click !!!");
            startActivity(new Intent(MainActivity.this,ClockActivity.class));
            finish();
        }
    }
}
