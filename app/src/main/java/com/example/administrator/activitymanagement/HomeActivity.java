package com.example.administrator.activitymanagement;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.ActivityListBean;
import com.example.administrator.activitymanagement.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment  {
    EditText et_home_search;
    Button btn_home_search;
    RecyclerView rv_home_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //获取user对象
        final UserInfo user = (UserInfo)getArguments().getSerializable("user");
        //Log.i("haha",user.toString());
        //获取所有活动
        final MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(getActivity());
        final List<ActivityListBean> listBeans = mySQLiteAdapter.queryActivity();
        //Log.i("aaa",listBeans.toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_home_list.setLayoutManager(linearLayoutManager);
        //List list = new ArrayList();
        rv_home_list.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        final MyAdapter adapter = new MyAdapter(getActivity(),listBeans);
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                clickInfo(view,position,listBeans,user);
            }
        });
        rv_home_list.setAdapter(adapter);


        //搜索按钮
        btn_home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_home_search.getText().toString().trim();
                if (text != null){
                    //修改数据源
                    MySQLiteAdapter mySQLiteAdapter1 = new MySQLiteAdapter(getActivity());
                    final List<ActivityListBean> listBeans1 = mySQLiteAdapter.querySearchList(text);
                    //Log.i("sear",listBeans1.toString());
                    MyAdapter adapter = new MyAdapter(getActivity(),listBeans1);
                    adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            clickInfo(view,position,listBeans1,user);
                        }
                    });
                    rv_home_list.swapAdapter(adapter,true);
                }
            }
        });
    }

    private void initView() {
        et_home_search = getView().findViewById(R.id.et_home_search);
        btn_home_search = getView().findViewById(R.id.btn_home_search);
        rv_home_list = getView().findViewById(R.id.rv_home_list);
    }

    /**
     * adapte的点击事件
     * @param view
     * @param position
     * @param listBeans
     */
    public void clickInfo(View view, int position, List<ActivityListBean> listBeans,UserInfo user){
        //获取点击的活动的所有信息
        //Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        //获取活动信息跳转到活动详情页面
        ActivityListBean activityListBean = listBeans.get(position);
        //Log.i("aaa",activityListBean.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("activity",activityListBean);
        Intent intent = new Intent(view.getContext(),InfoActivity.class);
        bundle.putSerializable("user",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }




}
