package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;
import com.example.administrator.activitymanagement.domain.UserSignBean;

import java.util.List;

public class MeInfoActivity extends AppCompatActivity {
    RecyclerView rv_me_info_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_info);
        rv_me_info_item = findViewById(R.id.rv_me_info_item);
        //获取该用户的信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final UserInfo me = (UserInfo) bundle.getSerializable("me");
        //Log.i("me",me.toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //设置布局
        rv_me_info_item.setLayoutManager(linearLayoutManager);
        //设置分割线
        rv_me_info_item.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        //获取数据源
        MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
        List<UserSignBean> list = mySQLiteAdapter.queryJoinByUser(me.getUid());
        final  List<ActivityListBean> listBeans = mySQLiteAdapter.queryActivityByAid(list);
        MyAdapter adapter = new MyAdapter(getApplicationContext(),listBeans);
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //获取点击的活动的所有信息
                //Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                //获取活动信息跳转到活动详情页面
                ActivityListBean activityListBean = listBeans.get(position);
                //Log.i("aaa",activityListBean.toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("activity",activityListBean);
                Intent intent = new Intent(view.getContext(),InfoActivity.class);
                bundle.putSerializable("user",me);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rv_me_info_item.setAdapter(adapter);

    }
}
