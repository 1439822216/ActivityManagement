package com.example.administrator.activitymanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteAdapter {
    //数据库连接和关闭
    SQLiteDatabase database = null;
    Context context = null;

    public MySQLiteAdapter(Context context) {
        this.context = context;
    }
    public void openDB(){
        MyHelper helper = new MyHelper(context,"database.db",null,1);
        database = helper.getWritableDatabase();

    }
    public void closeDB(){
        if (database.isOpen()){
            database.close();
        }
        database = null;
    }
    public List<UserInfo> query(String username ,String password){
        List<UserInfo> list = new ArrayList<>();
        openDB();
        String sql = "select * from userInfo where username = ? and password = ?";
        Cursor cursor = database.rawQuery(sql,new String[]{username,password});
        while (cursor.moveToNext()){
            UserInfo info = new UserInfo();
            info.setUid(cursor.getString(0));
            info.setName(cursor.getString(1));
            info.setTelephone(cursor.getString(2));
            info.setClazz(cursor.getString(3));
            info.setTouxiang(cursor.getString(4));
            info.setUsername(cursor.getString(5));
            info.setPassword(cursor.getString(6));
            list.add(info);
        }
        closeDB();
        return list;
    }

}
