package com.example.administrator.activitymanagement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.activitymanagement.domain.UserInfo;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_main_home,tv_main_edit,tv_main_me;
    Bundle bundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = getIntent();
        bundle = intent.getExtras();
        UserInfo user = (UserInfo)bundle.getSerializable("user");
        Log.i("haha",user.toString());
        HomeActivity homeActivity = new HomeActivity();
        replaceFragment(homeActivity);
        tv_main_home.setOnClickListener(l);
        tv_main_edit.setOnClickListener(l);
        tv_main_me.setOnClickListener(l);

    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_main_home:
                    replaceFragment(new HomeActivity());
                    break;
                case R.id.tv_main_edit:
                    replaceFragment(new EditActivity());
                    break;
                case R.id.tv_main_me:
                    replaceFragment(new MeActivity());
                    break;
            }
        }
    };
    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_layout, fragment);
        ft.commit();
    }
    private void initView() {
        tv_main_edit = findViewById(R.id.tv_main_edit);
        tv_main_home = findViewById(R.id.tv_main_home);
        tv_main_me = findViewById(R.id.tv_main_me);
    }
}
