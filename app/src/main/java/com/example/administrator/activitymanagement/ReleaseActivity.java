package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.List;

public class ReleaseActivity extends AppCompatActivity {
    RecyclerView rv_release_item;
    List<ActivityListBean> listBeans;
    UserInfo me;
    MySQLiteAdapter mySQLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        rv_release_item = findViewById(R.id.rv_release_item);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        me = (UserInfo) bundle.getSerializable("me");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_release_item.setLayoutManager(linearLayoutManager);
        //查询所有自己发布的活动
        mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
        listBeans = mySQLiteAdapter.queryActivityByUser(me.getUid());
        ActivityAdapter activityAdapter = new ActivityAdapter(listBeans,this);
        rv_release_item.setAdapter(activityAdapter);
        activityAdapter.setOnItemClickListener(myItemClickListener);

    }
    private ActivityAdapter.OnItemClickListener myItemClickListener = new ActivityAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, ActivityAdapter.ViewName viewName, int position) {
            switch (v.getId()){
                case R.id.btn_drop_update:
                    //Toast.makeText(ReleaseActivity.this, "你点击了修改按钮", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_drop_delete:
                    //Toast.makeText(ReleaseActivity.this, "你点击了删除按钮", Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        //Toast.makeText(ReleaseActivity.this, "你点击了item", Toast.LENGTH_SHORT).show();
                        clickInfo(v,position,listBeans,me);
                        break;
            }
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };
    public void clickInfo(View view, int position, List<ActivityListBean> listBeans,UserInfo user){
        //获取点击的活动的所有信息
        //Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        //获取活动信息跳转到活动详情页面
        ActivityListBean activityListBean = listBeans.get(position);
        //Log.i("aaa",activityListBean.toString());
        //查询所有报名活动的人数
        int num = mySQLiteAdapter.queryActivityByUserToNum(activityListBean.getAid());
        Bundle bundle = new Bundle();
        bundle.putSerializable("activity",activityListBean);
        Intent intent = new Intent(view.getContext(),InfoActivity.class);
        intent.putExtra("myActivity",1);
        intent.putExtra("number",num);
        bundle.putSerializable("user",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
