package com.excelee.timeclock.ui.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.excelee.timeclock.ui.receiver.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by lijia on 15/12/17.
 */
public class ClockActivity extends Activity{
    //广播接收器action
    private static final String ALARM_ACTION = "android.intent.action.ALARM_BROADCAST";
    //时间选择器
    TimePicker mTimePicker;

    //日历
    Calendar mCalendar;
    private int mHour;
    private int mMinute;
    //取消按钮
    private Button titleLeftBtn;
    //确定按钮
    private Button titleRightBtn;
    //按钮点击事件监听
    private MyOnClickListener myOnClickListener;
    //时间选择器监听
    private MyOnTimeChangeListener myOnTimeChangeListener;
    //闹钟管理器
    AlarmManager mAlarmManager;

    Intent mIntent = null;

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

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        titleLeftBtn = (Button) findViewById(R.id.clock_titlebtn_left);
        titleRightBtn = (Button) findViewById(R.id.clock_titlebtn_right);

        mTimePicker = (TimePicker) findViewById(R.id.clock_timepicker);
        mTimePicker.setIs24HourView(true);


        myOnTimeChangeListener = new MyOnTimeChangeListener();
        mTimePicker.setOnTimeChangedListener(new MyOnTimeChangeListener());

        myOnClickListener = new MyOnClickListener();
        titleLeftBtn.setOnClickListener(myOnClickListener);
        titleRightBtn.setOnClickListener(myOnClickListener);
    }

    /**
     * 初始化数据
     */
    public void initData(){

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR);
        mMinute = mCalendar.get(Calendar.MINUTE);
    }

    /**
     * 设置闹钟
     * @param hour
     * @param minute
     */
    public void setAlarmTime(int hour,int minute){

        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY,hour);
        mCalendar.set(Calendar.MINUTE,minute);
        mCalendar.set(Calendar.SECOND,0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                mCalendar.getTimeInMillis(),
                pendingIntent);
//        mAlarmManager.set();
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                //取消按钮
                case R.id.clock_titlebtn_left :
                    break;
                //确定按钮
                case R.id.clock_titlebtn_right :
                    break;
            }
        }
    }
    class MyOnTimeChangeListener implements TimePicker.OnTimeChangedListener{
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            //设置闹钟
            setAlarmTime(hourOfDay,minute);
            Log.i("TAG","MyOnTimeChangeListener : hour = "+hourOfDay +"  minite = "+minute);
        }
    }
}
