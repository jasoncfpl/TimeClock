package com.excelee.timeclock.bean;

/**
 * Created by lijia on 15/12/20.
 */

import java.util.Date;

public class Clock {
    //id
    private int _id;
    //重复次数
    private int repeatCounts;
    //响铃铃声
    private String ring;
    //备注
    private String remark;
    //响铃时间
    private Date clockTime;

    private long isUsing;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getRepeatCounts() {
        return repeatCounts;
    }

    public void setRepeatCounts(int repeatCounts) {
        this.repeatCounts = repeatCounts;
    }

    public String getRing() {
        return ring;
    }

    public void setRing(String ring) {
        this.ring = ring;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    public long getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(long isUsing) {
        this.isUsing = isUsing;
    }
}
