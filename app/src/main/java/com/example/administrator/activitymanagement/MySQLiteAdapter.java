package com.example.administrator.activitymanagement;

import android.content.ContentValues;
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

    /**
     * 根据用户名和密码查询该用户所有信息
     * @param username
     * @param password
     * @return
     */
    public UserInfo query(String username ,String password){
        UserInfo info = null;
        openDB();
        String sql = "select * from userInfo where username = ? and password = ?";
        Cursor cursor = database.rawQuery(sql,new String[]{username,password});
        while (cursor.moveToNext()){
            info = new UserInfo();
            info.setUid(cursor.getString(0));
            info.setName(cursor.getString(1));
            info.setTelephone(cursor.getString(2));
            info.setClazz(cursor.getString(3));
            info.setTouxiang(cursor.getString(4));
            info.setUsername(cursor.getString(5));
            info.setPassword(cursor.getString(6));

        }
        closeDB();
        return info;
    }

    /**
     * 对注册用户进行插入user
     * @param userInfo
     * @return
     */
    public boolean insertUser(UserInfo userInfo){
        openDB();
        boolean result = false;
        ContentValues values = new ContentValues();
        values.put("username",userInfo.getUsername());
        values.put("password",userInfo.getPassword());
        values.put("name",userInfo.getName());
        values.put("telephone",userInfo.getTelephone());
        values.put("clazz",userInfo.getClazz());
        values.put("uid",userInfo.getUid());
        values.put("touxiang",userInfo.getTouxiang());
        long num = database.insert("userInfo",null,values);
        if (num != -1){
            result = true;
        }
        closeDB();
        return result;
    }

}
