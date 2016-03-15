package com.excelee.timeclock.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.excelee.timeclock.bean.Clock;
import com.excelee.timeclock.db.DataBaseManager;
import com.excelee.timeclock.ui.BaseActivity;
import com.excelee.timeclock.ui.R;
import com.excelee.timeclock.ui.adapter.ClockAdapter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  Author : chia(cnexcelee@gmail.com)
 *  Time : 2016/3/15  18:17
 *  FileName : MainActivity.java
 *  Desc : 主界面
*/
public class MainActivity extends BaseActivity {

    /** 添加闹钟按钮 **/
    FloatingActionButton btn_addClock;
    RecyclerView mRecyclerView;

    ClockAdapter clockAdapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initContentView();
        setListener();
        initData();

    }

    @Override
    protected void initContentView() {

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolsbar);
        setSupportActionBar(toolbar);

        btn_addClock = (FloatingActionButton) findViewById(R.id.mainActivity_btn_add);
        mRecyclerView = (RecyclerView) findViewById(R.id.mainActivity_recyclerview);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        btn_addClock.setOnClickListener(new MyClickListener());

//        getSupportActionBar().setBackgroundDrawable();
    }


    /**
     * 设置点击事件
     */
    public void setListener(){

    }

    /**
     * 初始化数据
     */
    public void initData(){

        DataBaseManager dbManager = DataBaseManager.getInstance(this);
        List<Clock> clocks = dbManager.queryClock();
        Log.i("TAG","clocks size : "+clocks.size());
        if (clocks !=null && clocks.size() > 0){
            Date clockDate = clocks.get(0).getClockTime();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        }

        clockAdapter = new ClockAdapter(this,clocks);
        mRecyclerView.setAdapter(clockAdapter);
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
