package com.example.administrator.activitymanagement;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;
import com.example.administrator.activitymanagement.utils.RandomUtils;
import com.example.administrator.activitymanagement.utils.UUIDUtils;

import java.util.Calendar;
import java.util.List;

public class EditActivity extends Fragment {
    EditText ed_edit_aName, ed_edit_aOpenTime, ed_edit_aEndTime, ed_edit_aPlace, ed_edit_aInfo;
    Button btn_edit_Release;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取user对象
        final UserInfo user = (UserInfo)getArguments().getSerializable("user");
        initView();
        //设置发布活动的点击事件
        btn_edit_Release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activityName = ed_edit_aName.getText().toString().trim();
                String openTime = ed_edit_aOpenTime.getText().toString().trim();
                String endTime = ed_edit_aEndTime.getText().toString().trim();
                String place = ed_edit_aPlace.getText().toString().trim();
                String info = ed_edit_aInfo.getText().toString().trim();
                String aid = UUIDUtils.getCode();
                String aimageid = new String("a" +RandomUtils.randomNum(9));
                String uid = user.getUid();
                String name = user.getName();
                String telephone = user.getTelephone();
                if (activityName.equals("") || openTime.equals("") || endTime.equals("") || place.equals("") || info.equals("")){
                    Toast.makeText(view.getContext(), "活动信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    ActivityListBean activityListBean = new ActivityListBean(aid,activityName,aimageid,uid,name,openTime,endTime,place,info,telephone);
                    MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(view.getContext());
                    boolean b = mySQLiteAdapter.insertActivity(activityListBean);
                    if (b == true){
                        Toast.makeText(view.getContext(), "活动发布成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(view.getContext(), "活动发布失败", Toast.LENGTH_SHORT).show();
                    }
                }
                }
        });
    }

    public void initView() {
        ed_edit_aName = getActivity().findViewById(R.id.ed_edit_aName);
        ed_edit_aOpenTime = getActivity().findViewById(R.id.ed_edit_aOpenTime);
        ed_edit_aEndTime = getActivity().findViewById(R.id.ed_edit_aEndTime);
        ed_edit_aPlace = getActivity().findViewById(R.id.ed_edit_aPlace);
        ed_edit_aInfo = getActivity().findViewById(R.id.ed_edit_aInfo);
        btn_edit_Release=getActivity().findViewById(R.id.btn_edit_Release);
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
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),new OnDateSetListener(){

            @Override
            public  void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                EditActivity.this.ed_edit_aOpenTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    protected void showDatePickDlg1(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),new OnDateSetListener(){

            @Override
            public  void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                EditActivity.this.ed_edit_aEndTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}



