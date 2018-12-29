package com.example.administrator.activitymanagement.utils;

import android.util.Log;

import java.util.Calendar;

public class CalendarUtils {
    public static boolean openTime(int year,int month,int day){
        //获取系统当前时间
        Calendar calendar  = Calendar.getInstance();
        boolean flag = true;
        int systemyear = calendar.get(Calendar.YEAR);
        int systemmonth = calendar.get(Calendar.MONTH) + 1;
        int systemday = calendar.get(Calendar.DAY_OF_MONTH);
        //Log.i("asd",systemyear + "-" + systemmonth + "-" + systemday);
       if (year < systemyear){
           flag = false;
       }
       if (year == systemyear && month < systemmonth){
           flag = false;
       }
       if (year == systemyear && month == systemmonth && day < systemday){
           flag = false;
       }
        return flag;
    }
    public static boolean endTime(int year,int month,int day){
        //获取系统当前时间
        Calendar calendar  = Calendar.getInstance();
        boolean flag = true;
        int systemyear = calendar.get(Calendar.YEAR);
        int systemmonth = calendar.get(Calendar.MONTH) + 1;
        int systemday = calendar.get(Calendar.DAY_OF_MONTH);
        //Log.i("asd",systemyear + "-" + systemmonth + "-" + systemday);
        if (year > systemyear){
            flag = false;
        }
        if (year == systemyear && month > systemmonth){
            flag = false;
        }
        if (year == systemyear && month == systemmonth && day > systemday){
            flag = false;
        }
        return flag;
    }
}
