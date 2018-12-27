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
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.Calendar;
import java.util.List;

public class EditActivity extends Fragment {
    EditText ed_edit_aName, ed_edit_aOpenTime, ed_edit_aEndTime, ed_edit_aPlace, ed_edit_aInfo;

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
        List<UserInfo> user = (List<UserInfo>) getArguments().getSerializable("user");

    }

    public void initView() {
        ed_edit_aName = getActivity().findViewById(R.id.ed_edit_aName);
        ed_edit_aOpenTime = getActivity().findViewById(R.id.ed_edit_aOpenTime);
        ed_edit_aEndTime = getActivity().findViewById(R.id.ed_edit_aEndTime);
        ed_edit_aPlace = getActivity().findViewById(R.id.ed_edit_aPlace);
        ed_edit_aInfo = getActivity().findViewById(R.id.ed_edit_aInfo);
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
}



