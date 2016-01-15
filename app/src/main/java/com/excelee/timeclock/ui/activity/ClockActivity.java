package com.excelee.timeclock.ui.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.excelee.timeclock.bean.Clock;
import com.excelee.timeclock.db.DataBaseManager;
import com.excelee.timeclock.ui.R;
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

    private TextView settingTimeTv;
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

        settingTimeTv = (TextView) findViewById(R.id.clock_tv_settingtime);

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
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        //设置选中的时间
        settingTimeTv.setText(mHour+":"+mMinute);
        Log.i("TAG","当前时间 ： "+mHour + " : "+ mMinute);
    }

    /**
     * 设置闹钟
     * @param hour
     * @param minute
     */
    public void setAlarmTime(int hour,int minute){

        mHour = hour;
        mMinute = minute;
        //TextView显示当前选中的时间
        settingTimeTv.setText(hour + ":" + minute);
        Log.i("TAG", "Timepicker ： " + hour + " : " + minute);

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
                    //添加闹钟
                    addClock();
                    break;
            }
        }
    }

    /**
     * 添加闹钟
     */
    public void addClock(){

        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);

        saveClock();

        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(),
                pendingIntent);
        Log.i("TAG","clock setting success : "+ mCalendar.getTimeInMillis());
        Log.i("TAG","clock setting time : "+ mCalendar.getTime());



        Intent mIntent = new Intent(this,MainActivity.class);
        startActivity(mIntent);
        finish();
//        mAlarmManager.set();
    }

    /**
     * 保存闹钟
     */
    public void saveClock(){

        Clock clock = new Clock();
        clock.setRepeatCounts(1);
        clock.setRing("ring ring");
        clock.setRemark("闹钟");
        clock.setClockTime(mCalendar.getTime());
        DataBaseManager dbManager = DataBaseManager.getInstance(this);
        dbManager.insertClock(clock);

    }


    class MyOnTimeChangeListener implements TimePicker.OnTimeChangedListener{
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            Log.i("TAG", "MyOnTimeChangeListener : hour = " + hourOfDay + "  minite = " + minute);
            //设置闹钟
            setAlarmTime(hourOfDay, minute);
        }
    }
}
