package com.example.administrator.activitymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table userInfo (uid varchar(20) primary key ,name varchar(20),telephone varchar(20),clazz varchar(20),touxiang varchar(20),username varchar(20) unique,password varchar(20))";
        String sql1 = "create table activity(aid varchar(20) primary key ,aName varchar(20),aimageId varchar(20),aUid varchar(20),aOpenTime datetime, aEndTime datetime,aPlace varchar(30),aInfo varchar(100),aTelephone varchar(20),aNumber int(15))";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL("insert into userInfo values('E3BF64A5C21243678CDBC4C21649C954','戴俊迈','123456','移动172','1.jpg','111','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('ADC80B549F2D4D1DB1E5315C1144F0BA','戴逸仙','123456','化学172','2.jpg','222','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('8BB3195305814946903BBFA742D1BC49','文安然','123456','数学172','3.jpg','333','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('0559A1CC0D8F4CDAB3F46AF80A8ACD14','董思远','123456','机器人17','4.jpg','444','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('31F0275BBE62445681A451701826B607','范德华','123456','网站171','5.jpg','555','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('F192C168E2594B33BF49DCB74AF10AE4','金羽彤','123456','移动172','1.jpg','666','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('AA3326A5654747E3878E3C34429EFEAF','毛梓珊','123456','化学172','2.jpg','777','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('F46704EE82984AF1A6DF8F25A9E5FF5C','冯山柏','123456','数学172','3.jpg','888','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('7A661C34C4FD4E29A018B46AF2477DC7','蒋迎松','123456','机器人17','4.jpg','999','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('42D284C4837E4830B53C5CF3E22B1A9C','乔曼妮','123456','网站171','5.jpg','110','123456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
