package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.ActivityListBean;

public class InfoActivity extends AppCompatActivity {
    TextView tv_info_title,tv_info_status,tv_info_signNum,tv_info_time,tv_info_address,tv_info_user,tv_info_telephone,tv_info_info;
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

        tv_info_title.setText(activityListBean.getaName());
        tv_info_time.setText("活动时间：" + activityListBean.getaOpenTime() + "至" + activityListBean.getaEndTime());
        tv_info_address.setText("活动地点：" + activityListBean.getaPlace());
        tv_info_user.setText("发起人：" + activityListBean.getaUsername());
        tv_info_telephone.setText("电话：" + activityListBean.getaTelephone());
        tv_info_info.setText(activityListBean.getaInfo());
    }

    private void initView() {
        tv_info_title = findViewById(R.id.tv_info_title);
        tv_info_status = findViewById(R.id.tv_info_status);
        tv_info_signNum = findViewById(R.id.tv_info_signNum);
        tv_info_time = findViewById(R.id.tv_info_time);
        tv_info_address = findViewById(R.id.tv_info_address);
        tv_info_user = findViewById(R.id.tv_info_user);
        tv_info_telephone = findViewById(R.id.tv_info_telephone);
        tv_info_info = findViewById(R.id.tv_info_info);
        btn_info_sign = findViewById(R.id.btn_info_sign);
    }
}
