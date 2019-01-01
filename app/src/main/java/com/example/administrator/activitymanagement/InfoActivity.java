package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;
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
        final ActivityListBean activityListBean = (ActivityListBean) bundle.getSerializable("activity");
        final UserInfo user = (UserInfo) bundle.getSerializable("user");
       //计算活动开始时间
        String openTime = activityListBean.getaOpenTime();
        String[] split = openTime.split("-");
        String openYear = split[0];
        String openMonth = split[1];
        String openDay = split[2];
        //Log.i("zzz","年 = " + year  + "月 = " + month + "日 = " + day);
        boolean openFlag = CalendarUtils.openTime(Integer.valueOf(openYear), Integer.valueOf(openMonth), Integer.valueOf(openDay));
        //Log.i("flag",String.valueOf(flag));
        //设置button的状态，0为未报名，1为已报名;
        btn_info_sign.setTag("0");
        if (openFlag == false) {
            tv_info_status.setText("活动中");
            btn_info_sign.setText("报名结束");
            tv_info_status.setBackgroundColor(Color.parseColor("#ffff8800"));
            btn_info_sign.setBackgroundColor(Color.parseColor("#ffff8800"));
            btn_info_sign.setEnabled(false);
        }else {
            int myActivity = intent.getIntExtra("myActivity", 0);
            if (myActivity == 1){
                int number = intent.getIntExtra("number", 0);
                btn_info_sign.setText("已报名" + number + "人");
                btn_info_sign.setEnabled(false);
            }else {
                String uid = user.getUid();
                String aid = activityListBean.getAid();
                MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
                boolean b = mySQLiteAdapter.querySign(uid, aid);
                if (b == true){
                    btn_info_sign.setText("取消报名");
                    btn_info_sign.setBackgroundColor(Color.parseColor("#ffff4444"));
                    btn_info_sign.setTag("1");
                }else {
                    tv_info_status.setText("报名中");
                }
            }

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

        int myActivity = intent.getIntExtra("myActivity", 0);
        if (myActivity == 1){
            int number = intent.getIntExtra("number", 0);
            btn_info_sign.setText("已报名" + number + "人");
            btn_info_sign.setEnabled(false);
        }

        //设置报名的点击事件
        btn_info_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnStatus =  btn_info_sign.getTag().toString();
                Log.i("aaa",btnStatus);
                if (btnStatus.equals("0")){
                    MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(view.getContext());
                    String uid = user.getUid();
                    String aid = activityListBean.getAid();
                    boolean b = mySQLiteAdapter.insertSign(uid, aid);
                    if (b == true){
                        Toast.makeText(InfoActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                        btn_info_sign.setText("取消报名");
                        btn_info_sign.setBackgroundColor(Color.parseColor("#ffff4444"));
                        btn_info_sign.setTag("1");
                    }
                }else if (btnStatus.equals("1")){
                    MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(view.getContext());
                    String uid = user.getUid();
                    String aid = activityListBean.getAid();
                    boolean b = mySQLiteAdapter.deleteSign(uid, aid);
                    if (b == true){
                        Toast.makeText(InfoActivity.this, "取消报名成功", Toast.LENGTH_SHORT).show();
                        btn_info_sign.setText("立即报名");
                        btn_info_sign.setBackgroundColor(Color.parseColor("#FF33B5E5"));
                        btn_info_sign.setTag("0");
                    }
                }
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
