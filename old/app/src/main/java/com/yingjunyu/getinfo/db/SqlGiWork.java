package com.yingjunyu.getinfo.db;

/**
 * Created by yingjunyu on 2016/6/8.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqlGiWork {
    public static final String DATABASE_NAME = "yjy_getinfo";

    public static final String DATETIME = "datetime";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";

    public static SqlLite createDBHelper(Context context) {
        //创建一个DatabaseHelper对象
        SqlLite dbHelper = new SqlLite(context,DATABASE_NAME);
        return dbHelper;
    }

    public void insert(SqlLite dbHelper,GiWork giWork) {
        //生成ContentValues对象
        ContentValues values = new ContentValues();
        //想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
        values.put("datetime", giWork.getDatetime());
        values.put("title", giWork.getTitle());
        values.put("content", giWork.getContent());
        values.put("alerttime", giWork.getAlerttime());
        values.put("isok", giWork.getIsok());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //调用insert方法，就可以将数据插入到数据库当中
        db.insert("gi_work", null, values);
        db.close();
    }


    //更新操作就相当于执行SQL语句当中的update语句
    //UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
    public void update(SqlLite dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", "yingjunyu");
        //第一个参数是要更新的表名
        //第二个参数是一个ContentValeus对象
        //第三个参数是where子句
        db.update("gi_work", values, "id=?", new String[]{"1"});
        db.close();
    }

    public void delete(SqlLite dbHelper,String datetime){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // 删除表的所有数据
        // db.delete("users",null,null);
        // 从表中删除指定的一条数据
        db.execSQL("DELETE FROM " + "gi_work" + " WHERE datetime="+ datetime);
        db.close();
    }
}
