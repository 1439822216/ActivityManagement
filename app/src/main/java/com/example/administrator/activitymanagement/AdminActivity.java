package com.example.administrator.activitymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    MySQLiteAdapter mySQLiteAdapter;
    RecyclerView rv_admin_item;
    private AlertDialog.Builder builder;
    ActivityAdapter activityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        rv_admin_item = findViewById(R.id.rv_admin_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_admin_item.setLayoutManager(linearLayoutManager);
        mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
        final List<ActivityListBean> listBeans = mySQLiteAdapter.queryActivity();
        activityAdapter = new ActivityAdapter(listBeans,getApplicationContext());
        rv_admin_item.setAdapter(activityAdapter);
        activityAdapter.setOnItemClickListener(new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, ActivityAdapter.ViewName viewName, int position) {
                ActivityListBean activityListBean = listBeans.get(position);
                switch (v.getId()){
                    case R.id.btn_drop_update:
                        //Toast.makeText(ReleaseActivity.this, "你点击了修改按钮", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(),DropActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("dropActivity",activityListBean);
                        //bundle.putSerializable("me",me);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                        break;
                    case R.id.btn_drop_delete:
                        //Toast.makeText(ReleaseActivity.this, "你点击了删除按钮", Toast.LENGTH_SHORT).show();
                        showDialog(position,activityListBean);
                        break;
                    default:
                        //Toast.makeText(ReleaseActivity.this, "你点击了item", Toast.LENGTH_SHORT).show();
                        clickInfo(v,position,listBeans);
                        break;
                }
            }

            @Override
            public void onItemLongClick(View v) {

            }
        });
    }
    public void clickInfo(View view, int position, List<ActivityListBean> listBeans){
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
        //bundle.putSerializable("user",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //设置删除按钮的对话框
    public void  showDialog(final int position, final ActivityListBean activityListBean){
        builder = new AlertDialog.Builder(this).setTitle("删除").setMessage("请确认是否删除").setPositiveButton("确认",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean b = mySQLiteAdapter.deleteActivityAndJoin(activityListBean.getAid());
                if (b == true){
                    Toast.makeText(AdminActivity.this, "活动删除成功", Toast.LENGTH_SHORT).show();
                    activityAdapter.removeList(position);
                    dialog.dismiss();
                }else {
                    Toast.makeText(AdminActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == 2){
                List<ActivityListBean> listBeans = mySQLiteAdapter.queryActivity();
                activityAdapter = new ActivityAdapter(listBeans,this);
                rv_admin_item.swapAdapter(activityAdapter,false);
            }
        }
    }
}
