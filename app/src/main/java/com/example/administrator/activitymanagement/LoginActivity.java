package com.example.administrator.activitymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
EditText ed_login_name,ed_login_password;
CheckBox cb_login_Remember;
Button btn_login_login,btn_login_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();

    }
    private void login(){
        ed_login_name=findViewById(R.id.ed_login_name);
        ed_login_password=findViewById(R.id.ed_login_password);
        cb_login_Remember=findViewById(R.id.cb_login_Remember);
        btn_login_login=findViewById(R.id.btn_login_login);
        btn_login_register=findViewById(R.id.btn_login_register);
    }
}
