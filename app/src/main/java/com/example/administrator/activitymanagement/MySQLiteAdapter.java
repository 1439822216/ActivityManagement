package com.example.administrator.activitymanagement;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
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

    public List<ActivityListBean> queryActivity(){
        List<ActivityListBean> listBeans = new ArrayList<ActivityListBean>();
        openDB();
        Cursor activity = database.query("activity", null, null, null, null, null, null);
        while (activity.moveToNext()){
            ActivityListBean activityListBean = new ActivityListBean();
            activityListBean.setAid(activity.getString(0));
            activityListBean.setaName(activity.getString(1));
            activityListBean.setAimageId(activity.getString(2));
            activityListBean.setaUid(activity.getString(3));
            activityListBean.setaUsername(activity.getString(4));
            activityListBean.setaOpenTime(activity.getString(5));
            activityListBean.setaEndTime(activity.getString(6));
            activityListBean.setaPlace(activity.getString(7));
            activityListBean.setaInfo(activity.getString(8));
            activityListBean.setaTelephone(activity.getString(9));
            listBeans.add(activityListBean);
        }
        closeDB();
        return listBeans;
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


    public boolean insertActivity(ActivityListBean activityListBean){
        boolean result = false;
        openDB();
        String sql = "insert into activity values(?,?,?,?,?,?,?,?,?,?)";
        Object[] parmars = {activityListBean.getAid(),activityListBean.getaName(),activityListBean.getAimageId(),activityListBean.getaUid(),activityListBean.getaUsername(),activityListBean.getaOpenTime(),activityListBean.getaEndTime()
        ,activityListBean.getaPlace(),activityListBean.getaInfo(),activityListBean.getaTelephone()};
        try{
            database.execSQL(sql,parmars);
            result = true;
        }catch (Exception e){
            result = false;
        }

        closeDB();
        return result;
    }
}
