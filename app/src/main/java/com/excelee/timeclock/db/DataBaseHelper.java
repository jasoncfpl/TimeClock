package com.excelee.timeclock.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by lijia on 15/12/20.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    //数据库名称
    private static final String DATABASE_NAME = "timeclock.db";
    private static final int DATABASE_VERSION = 1;
    //表名称
    public static final String CLOCK_TABLE_NAME = "clockTbl";
    //clock表建表sql
    private static final String CREATE_CLOCK_TABLE = "create table "
            + CLOCK_TABLE_NAME
            + "( _id integer primary key autoincrement ,"
            + "repeat_counts integer DEFAULT 1 ,"
            + "clock_ring varchar(100) ,"
            + "remark varchar(100) ,"
            + "clock_time varchar(20) "
            +")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
