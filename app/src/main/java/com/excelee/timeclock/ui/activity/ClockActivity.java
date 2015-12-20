package com.excelee.timeclock.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by lijia on 15/12/17.
 */
public class ClockActivity extends Activity{

    //时间选择器
    TimePicker mTimePicker;

    //日历
    Calendar mCalendar;
    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        initView();
        initData();
    }

    /**
     * 初始化view
     */
    public void initView(){

        mTimePicker = (TimePicker) findViewById(R.id.clock_timepicker);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new MyOnTimeChangeListener());
    }

    /**
     * 初始化数据
     */
    public void initData(){

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR);
        mMinute = mCalendar.get(Calendar.MINUTE);
    }

    class MyOnTimeChangeListener implements TimePicker.OnTimeChangedListener{
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            Log.i("TAG","MyOnTimeChangeListener : hour = "+hourOfDay +"  minite = "+minute);
        }
    }
}
