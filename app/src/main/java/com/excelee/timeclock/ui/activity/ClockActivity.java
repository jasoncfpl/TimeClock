package com.excelee.timeclock.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.excelee.timeclock.bean.Clock;
import com.excelee.timeclock.db.DataBaseManager;
import com.excelee.timeclock.ui.BaseActivity;
import com.excelee.timeclock.ui.R;
import com.excelee.timeclock.ui.receiver.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by lijia on 15/12/17.
 */
public class ClockActivity extends BaseActivity{
    //广播接收器action
    private static final String ALARM_ACTION = "android.intent.action.ALARM_BROADCAST";
    //时间选择器
    TimePicker mTimePicker;
    //重复次数
    TextView repeatTv;
    LinearLayout repeatLayout;
    //响铃铃声
    TextView ringTv;
    LinearLayout ringLayout;
    //闹钟备注
    TextView remarkTv;
    LinearLayout remarkLayout;
    //日历
    Calendar mCalendar;
    private int mHour;
    private int mMinute;
    //取消按钮
    private Button leftBtn;
    //确定按钮
    private Button rightBtn;

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

        initContentView();
        initData();
    }

    @Override
    protected void initContentView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.clock_title);
        toolbar.setTitle(R.string.str_clock_title_setting);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        leftBtn = (Button) findViewById(R.id.clock_left_btn);
        rightBtn = (Button) findViewById(R.id.clock_right_btn);

        mTimePicker = (TimePicker) findViewById(R.id.clock_timepicker);
        mTimePicker.setIs24HourView(true);

        repeatTv = (TextView) findViewById(R.id.clock_repeat_tv);
        repeatLayout = (LinearLayout) findViewById(R.id.clock_repeat_layout);
        ringTv = (TextView) findViewById(R.id.clock_ring_tv);
        ringLayout = (LinearLayout) findViewById(R.id.clock_ring_layout);
        remarkTv = (TextView) findViewById(R.id.clock_remark_tv);
        remarkLayout = (LinearLayout) findViewById(R.id.clock_repeat_layout);

        settingTimeTv = (TextView) findViewById(R.id.clock_tv_settingtime);

        myOnTimeChangeListener = new MyOnTimeChangeListener();
        mTimePicker.setOnTimeChangedListener(new MyOnTimeChangeListener());

        myOnClickListener = new MyOnClickListener();
        leftBtn.setOnClickListener(myOnClickListener);
        rightBtn.setOnClickListener(myOnClickListener);
        repeatLayout.setOnClickListener(myOnClickListener);
        ringLayout.setOnClickListener(myOnClickListener);
        remarkLayout.setOnClickListener(myOnClickListener);
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
                case R.id.clock_left_btn :
                    break;
                //确定按钮
                case R.id.clock_right_btn :
                    //添加闹钟
                    addClock();
                    break;
                //重复次数
                case R.id.clock_repeat_layout :
                    break;
                //设置铃声
                case R.id.clock_ring_layout :
                    break;
                //备注
                case R.id.clock_remark_layout :
                    break;
                default:
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
        clock.setIsUsing(1);
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
