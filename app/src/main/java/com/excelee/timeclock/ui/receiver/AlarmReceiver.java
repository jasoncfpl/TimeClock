package com.excelee.timeclock.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.excelee.timeclock.ui.ClockApplication;

/**
 * 广播接收器
 * Created by lijia on 15/12/20.
 */
public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        ClockApplication.getInstance().toast("ring ring ring !!!");
    }
}
