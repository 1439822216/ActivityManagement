package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.utils.CalendarUtils;

import java.util.Calendar;

public class InfoActivity extends AppCompatActivity {
    TextView tv_info_title,tv_info_status,tv_info_time,tv_info_address,tv_info_user,tv_info_telephone,tv_info_info;
    Button btn_info_sign;
    Bundle bundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
        //获取数据
        Intent intent = getIntent();
        bundle = intent.getExtras();
        ActivityListBean activityListBean = (ActivityListBean) bundle.getSerializable("activity");
       //计算活动开始时间
        String openTime = activityListBean.getaOpenTime();
        String[] split = openTime.split("-");
        String openYear = split[0];
        String openMonth = split[1];
        String openDay = split[2];
        //Log.i("zzz","年 = " + year  + "月 = " + month + "日 = " + day);
        boolean openFlag = CalendarUtils.openTime(Integer.valueOf(openYear), Integer.valueOf(openMonth), Integer.valueOf(openDay));
        //Log.i("flag",String.valueOf(flag));
        if (openFlag == false){
            tv_info_status.setText("活动中");
            btn_info_sign.setText("报名结束");
            tv_info_status.setBackgroundColor(Color.parseColor("#ffff8800"));
            btn_info_sign.setBackgroundColor(Color.parseColor("#ffff8800"));
            btn_info_sign.setEnabled(false);
        }else {
            tv_info_status.setText("报名中");
        }
        //计算活动结束时间
        String endTime = activityListBean.getaEndTime();
        String[] split1 = endTime.split("-");
        String endYear = split1[0];
        String endMonth = split1[1];
        String endDay = split1[2];
        boolean endFlag = CalendarUtils.openTime(Integer.valueOf(endYear), Integer.valueOf(endMonth), Integer.valueOf(endDay));
        if (endFlag == false){
            tv_info_status.setText("活动结束");
            btn_info_sign.setText("活动已结束");
            tv_info_status.setBackgroundColor(Color.parseColor("#ffff4444"));
            btn_info_sign.setBackgroundColor(Color.parseColor("#ffff4444"));
            btn_info_sign.setEnabled(false);
        }
        tv_info_title.setText(activityListBean.getaName());
        tv_info_time.setText("活动时间：" + activityListBean.getaOpenTime() + "至" + activityListBean.getaEndTime());
        tv_info_address.setText("活动地点：" + activityListBean.getaPlace());
        tv_info_user.setText("发起人：" + activityListBean.getaUsername());
        tv_info_telephone.setText("电话：" + activityListBean.getaTelephone());
        tv_info_info.setText(activityListBean.getaInfo());
        //设置报名的点击事件
        btn_info_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initView() {
        tv_info_title = findViewById(R.id.tv_info_title);
        tv_info_status = findViewById(R.id.tv_info_status);
        tv_info_time = findViewById(R.id.tv_info_time);
        tv_info_address = findViewById(R.id.tv_info_address);
        tv_info_user = findViewById(R.id.tv_info_user);
        tv_info_telephone = findViewById(R.id.tv_info_telephone);
        tv_info_info = findViewById(R.id.tv_info_info);
        btn_info_sign = findViewById(R.id.btn_info_sign);
    }
}