package com.excelee.timeclock.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.excelee.timeclock.bean.Clock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库管理类
 * Created by lijia on 15/12/20.
 */
public class DataBaseManager {

    private final DataBaseHelper dbHelper;

    public DataBaseManager(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    /**
     * 插入记录
     */
    public void insertClock(Clock clock){

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String clockTime = simpleDateFormat.format(clock.getClockTime());
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("repeatCounts",clock.getRepeatCounts());
        mContentValues.put("ring",clock.getRing());
        mContentValues.put("remark",clock.getRemark());
        mContentValues.put("clockTime",clockTime);

        sqlite.insert(DataBaseHelper.CLOCK_TABLE_NAME,null,mContentValues);

    }

    /**
     * 删除记录
     */
    public void deleteClock(int id){

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = "delete from " + DataBaseHelper.CLOCK_TABLE_NAME + " where _id = ?";
        sqlite.execSQL(sql, new Integer[] { id });
        sqlite.close();

    }

    /**
     * 更新记录
     */
    public void updateClock(){

    }

    /**
     * 查询所有记录
     */
    public List<Clock> queryClock(){

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        List<Clock> clocks = new ArrayList<Clock>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        Cursor cursor = sqlite.query(DataBaseHelper.CLOCK_TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Clock clock = new Clock();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int repeatCounts = cursor.getInt(cursor.getColumnIndex("repeat_counts"));
            String ring = cursor.getString(cursor.getColumnIndex("clock_ring"));
            String remark = cursor.getString(cursor.getColumnIndex("remark"));
            String clockTimeStr = cursor.getString(cursor.getColumnIndex("clock_time"));
            Date clockTime = null;
            try {
                clockTime = simpleDateFormat.parse(clockTimeStr);
            } catch (ParseException e) {
                Log.i("TAG"," queryClock() 解析错误 id : "+_id);
                e.printStackTrace();
            }
            clock.set_id(_id);
            clock.setRepeatCounts(repeatCounts);
            clock.setRing(ring);
            clock.setRemark(remark);
            clock.setClockTime(clockTime);
            clocks.add(clock);
        }
        return clocks;
    }
}
