package com.example.administrator.activitymanagement;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_home_list.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        rv_home_list.setAdapter(adapter);

    }

    private void initView() {
        et_home_search = getView().findViewById(R.id.et_home_search);
        btn_home_search = getView().findViewById(R.id.btn_home_search);
        rv_home_list = getView().findViewById(R.id.rv_home_list);
    }





}
