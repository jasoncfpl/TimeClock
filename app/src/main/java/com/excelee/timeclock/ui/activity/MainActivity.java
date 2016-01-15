package com.excelee.timeclock.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.excelee.timeclock.bean.Clock;
import com.excelee.timeclock.db.DataBaseManager;
import com.excelee.timeclock.ui.R;
import com.excelee.timeclock.ui.adapter.ClockAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** 添加闹钟按钮 **/
    Button btn_addClock;
    TextView clockTv;
    RecyclerView mRecyclerView;

    ClockAdapter clockAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    /**
     * 初始化view
     */
    public void initView(){

        btn_addClock = (Button) findViewById(R.id.mainActivity_btn_add);
        clockTv = (TextView) findViewById(R.id.mainActivity_tv_clock);
        mRecyclerView = (RecyclerView) findViewById(R.id.mainActivity_recyclerview);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        btn_addClock.setOnClickListener(new MyClickListener());

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
            clockTv.setText(sdf.format(clockDate));
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
