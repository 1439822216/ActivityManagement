package com.example.administrator.activitymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
EditText ed_reg_name,ed_reg_password,ed_reg_rpassword;
Button btn_reg_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register();
    }
    private void register(){
        ed_reg_name=findViewById(R.id.ed_reg_name);
        ed_reg_password=findViewById(R.id.ed_reg_password);
        ed_reg_rpassword=findViewById(R.id.ed_reg_rpassword);

    }
}
