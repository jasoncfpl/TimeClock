package com.excelee.timeclock.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.excelee.timeclock.ui.activity.RingActivity;


/**
 * 广播接收器
 * Created by lijia on 15/12/20.
 */
public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
//        ClockApplication.getInstance().toast("ring ring ring !!!");
        Log.i("TAG","ring ring ring !!!");
        Toast.makeText(context,"Ring ring !!!",Toast.LENGTH_SHORT).show();
        Intent mIntent  = new Intent(context, RingActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mIntent);
    }
}
