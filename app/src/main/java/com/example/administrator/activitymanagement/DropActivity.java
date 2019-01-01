package com.example.administrator.activitymanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;

import java.io.Serializable;
import java.util.Calendar;

public class DropActivity extends AppCompatActivity {
    TextView tv_edit_top;
    EditText ed_edit_aName, ed_edit_aOpenTime, ed_edit_aEndTime, ed_edit_aPlace, ed_edit_aInfo;
    Button btn_edit_Release;
    MySQLiteAdapter mySQLiteAdapter;
    //UserInfo me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
        tv_edit_top.setText("活动修改");
        btn_edit_Release.setText("修改");
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final ActivityListBean activityListBean = (ActivityListBean) bundle.getSerializable("dropActivity");
        //me = (UserInfo) bundle.getSerializable("me");
        ed_edit_aName.setText(activityListBean.getaName());
        ed_edit_aOpenTime.setText(activityListBean.getaOpenTime());
        ed_edit_aEndTime.setText(activityListBean.getaEndTime());
        ed_edit_aPlace.setText(activityListBean.getaPlace());
        ed_edit_aInfo.setText(activityListBean.getaInfo());
        btn_edit_Release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aName = ed_edit_aName.getText().toString().trim();
                String openTime = ed_edit_aOpenTime.getText().toString().trim();
                String endTime = ed_edit_aEndTime.getText().toString().trim();
                String info = ed_edit_aInfo.getText().toString().trim();
                String place = ed_edit_aPlace.getText().toString().trim();
                activityListBean.setaName(aName);
                activityListBean.setaOpenTime(openTime);
                activityListBean.setaEndTime(endTime);
                activityListBean.setaPlace(place);
                activityListBean.setaInfo(info);
                mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
                boolean b = mySQLiteAdapter.updateActivity(activityListBean);
                if (b == true){
                    Toast.makeText(DropActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    setResult(2,intent1);
                    finish();
                }
            }
        });
    }
    //初始化数据和时间选择器
    public void initView() {
        ed_edit_aName =findViewById(R.id.ed_edit_aName);
        ed_edit_aOpenTime = findViewById(R.id.ed_edit_aOpenTime);
        ed_edit_aEndTime = findViewById(R.id.ed_edit_aEndTime);
        ed_edit_aPlace = findViewById(R.id.ed_edit_aPlace);
        ed_edit_aInfo = findViewById(R.id.ed_edit_aInfo);
        btn_edit_Release=findViewById(R.id.btn_edit_Release);
        tv_edit_top = findViewById(R.id.tv_edit_top);
        ed_edit_aOpenTime.setInputType(InputType.TYPE_NULL);
        ed_edit_aOpenTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });

        ed_edit_aOpenTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showDatePickDlg();
                }
            }
        });
        ed_edit_aEndTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    showDatePickDlg1();
                    return true;
                }
                return false;
            }
        });

        ed_edit_aEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showDatePickDlg1();
                }
            }
        });
    }
    protected void showDatePickDlg(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){

            @Override
            public  void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                ed_edit_aOpenTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    protected void showDatePickDlg1(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){

            @Override
            public  void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                ed_edit_aEndTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
