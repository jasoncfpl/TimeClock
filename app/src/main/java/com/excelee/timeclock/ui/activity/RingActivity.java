package com.excelee.timeclock.ui.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.excelee.timeclock.ui.BaseActivity;
import com.excelee.timeclock.ui.R;

import java.io.IOException;

/**
 *  Author : chia(cnexcelee@gmail.com)
 *  Time : 2016/3/15  18:13
 *  FileName : RingActivity.java
 *  Desc : 响铃activity
*/  
public class RingActivity extends BaseActivity {

    Button stopClockBtn;
    //点击事件
    RingOnClickListener mOnClickListener;
    //播放铃声
    MediaPlayer mMediaPlayer = null;
    //震动功能
    Vibrator vibrator;
    //震动的频率
    long [] pattern = {100,2000,1000,2000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        initContentView();
    }


    @Override
    protected void initContentView() {

        stopClockBtn = (Button) findViewById(R.id.ring_btn_stopClock);

        mOnClickListener = new RingOnClickListener();
        stopClockBtn.setOnClickListener(mOnClickListener);

        //开始震动和播放铃声
        startAlarm();
    }

    /**
     * 开始震动和播放铃声
     */
    private void startAlarm() {

        //震动功能
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern,2);
        //响铃
        mMediaPlayer = MediaPlayer.create(this, getSystemDefultRingtoneUri());
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    /**
     * 获取系统默认铃声uri
     * @return
     */
    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
    }

    /**
     * 停止闹钟响铃和震动
     */
    public void stopAlarm(){
        //停止响铃
        mMediaPlayer.stop();
        mMediaPlayer.release();
        //停止震动
        vibrator.cancel();
    }
    class RingOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.ring_btn_stopClock :

                    stopAlarm();

                    break;
            }
        }
    }
}
