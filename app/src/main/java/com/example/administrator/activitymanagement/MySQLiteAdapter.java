package com.example.administrator.activitymanagement;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;
import com.example.administrator.activitymanagement.domain.UserSignBean;

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
        cursor.close();
        closeDB();
        return info;
    }

    /**
     * 查询所有活动
     * @return
     */
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
        activity.close();
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

    /**
     * 插入活动
     * @param activityListBean
     * @return
     */
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

    /**
     * 根据搜索内容来查询活动列表
     * @param searchText
     * @return
     */
    public List<ActivityListBean> querySearchList(String searchText){
        openDB();
        List<ActivityListBean> list = new ArrayList();
        String sql = "select * from activity where aName like '%" + searchText + "%'";
        Cursor cursor = database.rawQuery(sql,new String[]{});
        while (cursor.moveToNext()){
            ActivityListBean activityListBean = new ActivityListBean();
            activityListBean.setAid(cursor.getString(0));
            activityListBean.setaName(cursor.getString(1));
            activityListBean.setAimageId(cursor.getString(2));
            activityListBean.setaUid(cursor.getString(3));
            activityListBean.setaUsername(cursor.getString(4));
            activityListBean.setaOpenTime(cursor.getString(5));
            activityListBean.setaEndTime(cursor.getString(6));
            activityListBean.setaPlace(cursor.getString(7));
            activityListBean.setaInfo(cursor.getString(8));
            activityListBean.setaTelephone(cursor.getString(9));
            list.add(activityListBean);
        }
        cursor.close();
        closeDB();
        return list;
    }

    /**
     * 插入学生报名信息
     * @param uid
     * @param aid
     * @return
     */
    public boolean insertSign(String uid , String aid){
        boolean result = false;
        openDB();
        String sql = "insert into joinTo values(?,?)";
        try {
            database.execSQL(sql,new Object[]{uid,aid});
            result = true;
        }catch (Exception e){
            result = false;
        }
        closeDB();
        return result;
    }

    /**
     * 查询学生报名状况
     * @param uid
     * @param aid
     * @return
     */
    public boolean querySign(String uid , String aid){
        boolean result = false;
        openDB();
        String sql = "select * from joinTo where uid = ? and aid = ?";
        Cursor cursor = database.rawQuery(sql,new String[]{uid,aid});
        if (cursor.getCount() != 0) {
            result = true;
        }
        cursor.close();
        closeDB();
        return result;
    }

    /**
     * 删除用户活动报名状况
     * @param uid
     * @param aid
     * @return
     */
    public boolean deleteSign(String uid , String aid){
        boolean result = false;
        openDB();
        int num = database.delete("joinTo", "uid = ? and aid = ?", new String[]{uid, aid});
        if (num == 1){
            result = true;
        }
        closeDB();
        return  result;
    }


    /**
     * 查询自己所有发布的活动
     * @param uid
     * @return
     */
    public List<ActivityListBean> queryActivityByUser(String uid){
        List<ActivityListBean> listBeans = new ArrayList<ActivityListBean>();
        openDB();
        Cursor activity = database.query("activity", null, "aUid = ?", new String[]{uid}, null, null, null);
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
        activity.close();
        closeDB();
        return listBeans;
    }

    /**
     * 根据uid查询活动aid
     * @param uid
     * @return
     */
    public List<UserSignBean> queryJoinByUser(String uid){
        openDB();
        List<UserSignBean> list = new ArrayList<>();
        Cursor joinTo = database.query("joinTo", null, "uid = ?", new String[]{uid}, null, null, null);
        while (joinTo.moveToNext()){
            UserSignBean userSignBean = new UserSignBean();
            userSignBean.setUid(joinTo.getString(0));
            userSignBean.setAid(joinTo.getString(1));
            list.add(userSignBean);
        }
        joinTo.close();
        closeDB();
        return list;
    }

    /**
     * 通过aid查询活动
     * @param list
     * @return
     */
    public List<ActivityListBean> queryActivityByAid(List<UserSignBean> list){
        List<ActivityListBean> listBeans = new ArrayList<ActivityListBean>();
        openDB();
        for (int i = 0 ; i < list.size(); i ++){
            Cursor activity = database.query("activity", null, "aid = ?", new String[]{list.get(i).getAid()}, null, null, null);
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
             activity.close();
        }
        closeDB();
        return listBeans;
    }

    /**
     * 查询活动的报名人数
     * @param aid
     * @return
     */
    public int queryActivityByUserToNum(String aid){
        int num = 0;
        openDB();
        Cursor cursorNum = database.query("joinTo", null, "aid = ?", new String[]{aid}, null, null, null);
        num = cursorNum.getCount();
        cursorNum.close();
        closeDB();
        return  num;
    }

    /**
     * 根据aid删除活动表和中间表的活动
     * @param aid
     * @return
     */
    public boolean deleteActivityAndJoin(String aid){
        boolean result = false;
        openDB();
        database.beginTransaction();
        try {
            String sql = "delete from activity  where aid = ?";
            String sql1 = "delete from joinTo where aid = ?";
            database.execSQL(sql,new Object[]{aid});
            database.execSQL(sql1,new Object[]{aid});
            database.setTransactionSuccessful();
            result = true;
        }catch (Exception e){
            result = false;
        }finally {
            database.endTransaction();
        }
        closeDB();
        return result;
    }

    /**
     * 根据活动aid修改活动信息
     * @param activityListBean
     * @return
     */
    public boolean updateActivity(ActivityListBean activityListBean){
        boolean result = false;
        openDB();
        ContentValues values = new ContentValues();
        values.put("aName",activityListBean.getaName());
        values.put("aOpenTime",activityListBean.getaOpenTime());
        values.put("aEndTime",activityListBean.getaEndTime());
        values.put("aPlace",activityListBean.getaPlace());
        values.put("aInfo",activityListBean.getaInfo());
        int activity = database.update("activity", values, "aid = ?", new String[]{activityListBean.getAid()});
        if (activity == 1){
            result = true;
        }
        closeDB();
        return result;
    }
}
