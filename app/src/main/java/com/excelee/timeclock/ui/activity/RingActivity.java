package com.excelee.timeclock.ui.activity;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.excelee.timeclock.ui.R;


public class RingActivity extends AppCompatActivity {

    Button stopClockBtn;
    //点击事件
    RingOnClickListener mOnClickListener;
    //震动功能
    Vibrator vibrator;
    //震动的频率
    long [] pattern = {100,2000,1000,2000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        initView();
    }

    public void initView(){

        stopClockBtn = (Button) findViewById(R.id.ring_btn_stopClock);


        mOnClickListener = new RingOnClickListener();
        stopClockBtn.setOnClickListener(mOnClickListener);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern,2);
    }

    class RingOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ring_btn_stopClock :

                        vibrator.cancel();
                    break;
            }
        }
    }
}
