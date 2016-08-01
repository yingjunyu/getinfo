package com.yingjunyu.getinfo.db;

/**
 * Created by yingjunyu on 2016/6/8.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLite extends SQLiteOpenHelper{
    private static final int VERSION = 1;

    public SqlLite(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

    }

    public SqlLite(Context context,String name,int version){
        this(context,name,null,version);
    }

    public SqlLite(Context context,String name){
        this(context,name,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //使用execSQL函数执行SQL语句
        db.execSQL("create table gi_work(datetime varchar(30),title varchar(50),content varchar(500)," +
                "alerttime varchar(30),isok varchar(2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
