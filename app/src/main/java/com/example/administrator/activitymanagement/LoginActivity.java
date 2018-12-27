package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.administrator.activitymanagement.domain.UserInfo;

import java.io.Serializable;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
EditText ed_login_name,ed_login_password;
CheckBox cb_login_Remember;
Button btn_login_login,btn_login_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed_login_name.getText().toString().trim();
                String password = ed_login_password.getText().toString().trim();
                MySQLiteAdapter mySQLiteAdapter = new MySQLiteAdapter(getApplicationContext());
                List<UserInfo> query = mySQLiteAdapter.query(username, password);
                if (query != null){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", (Serializable) query);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    private void login(){
        ed_login_name=findViewById(R.id.ed_login_name);
        ed_login_password=findViewById(R.id.ed_login_password);
        cb_login_Remember=findViewById(R.id.cb_login_Remember);
        btn_login_login=findViewById(R.id.btn_login_login);
        btn_login_register=findViewById(R.id.btn_login_register);
    }
}
