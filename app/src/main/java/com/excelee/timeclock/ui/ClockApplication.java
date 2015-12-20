package com.excelee.timeclock.ui;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by lijia on 15/12/20.
 */
public class ClockApplication extends Application {

    private static ClockApplication instance;

    public ClockApplication() {
    }
    public static ClockApplication getInstance(){
        if(instance == null){
            instance = new ClockApplication();
        }
        return instance;
    }
    public void toast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
    public void toast(int resId){
        Toast.makeText(this,getString(resId), Toast.LENGTH_SHORT).show();
    }
}
