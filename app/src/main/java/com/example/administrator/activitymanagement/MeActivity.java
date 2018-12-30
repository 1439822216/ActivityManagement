package com.example.administrator.activitymanagement;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.UserInfo;

public class MeActivity extends Fragment {
TextView tv_me_name,tv_me_class,tv_me_phone,tv_me_End;
ImageView im_me_image;
Button btn_me_sign;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_me, container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取user对象
         final UserInfo user = (UserInfo)getArguments().getSerializable("user");
       // Log.i("haha",user.toString());
        initView();
       final int iamgeId = getResources().getIdentifier(user.getTouxiang(),"drawable",getActivity().getPackageName());
       im_me_image.setImageResource(iamgeId);
       tv_me_name.setText("姓名：" + user.getName());
       tv_me_class.setText("班级：" + user.getClazz());
       tv_me_phone.setText("电话：" + user.getTelephone());
       tv_me_End.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(view.getContext(),LoginActivity.class);
               startActivity(intent);
           }
       });
       btn_me_sign.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(),MeInfoActivity.class);
               Bundle bundle = new Bundle();
               bundle.putSerializable("me",user);
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });
    }

    private void initView() {
        tv_me_name=getActivity().findViewById(R.id.tv_me_name);
        tv_me_class=getActivity().findViewById(R.id.tv_me_class);
        tv_me_phone=getActivity().findViewById(R.id.tv_me_phone);
        tv_me_End=getActivity().findViewById(R.id.tv_me_End);
        im_me_image=getActivity().findViewById(R.id.im_me_image);
        btn_me_sign = getActivity().findViewById(R.id.btn_me_sign);
    }
}
