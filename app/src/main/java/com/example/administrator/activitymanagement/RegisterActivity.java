package com.example.administrator.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.activitymanagement.domain.UserInfo;
import com.example.administrator.activitymanagement.utils.RandomUtils;
import com.example.administrator.activitymanagement.utils.UUIDUtils;

public class RegisterActivity extends AppCompatActivity {
EditText ed_reg_name,ed_reg_password,ed_reg_rpassword,ed_reg_vername,ed_reg_phone,ed_reg_class;
Button btn_reg_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register();
        btn_reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed_reg_name.getText().toString().trim();
                String password = ed_reg_password.getText().toString().trim();
                String repassword = ed_reg_rpassword.getText().toString().trim();
                String telephone = ed_reg_phone.getText().toString().trim();
                String clazz = ed_reg_class.getText().toString().trim();
                String name = ed_reg_vername.getText().toString().trim();
                if (username.equals("") || password.equals("") || repassword.equals("") || telephone.equals("") || clazz.equals("") || name.equals("")) {
                    Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(repassword)) {
                        UserInfo info = null;
                        String uid = UUIDUtils.getCode();
                        int i = RandomUtils.randomNum(13);
                        info = new UserInfo(uid, name, telephone, clazz, null, username, password);
                        info.setUid(i + ".jpg");
                        MySQLiteAdapter adapter = new MySQLiteAdapter(getApplicationContext());
                        boolean result = adapter.insertUser(info);
                        if (result == true) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void register(){
        ed_reg_name=findViewById(R.id.ed_reg_name);
        ed_reg_password=findViewById(R.id.ed_reg_password);
        ed_reg_rpassword=findViewById(R.id.ed_reg_rpassword);
        ed_reg_vername=findViewById(R.id.ed_reg_vername);
        ed_reg_phone=findViewById(R.id.ed_reg_phone);
        ed_reg_class=findViewById(R.id.ed_reg_class);
        btn_reg_register=findViewById(R.id.btn_reg_register);
    }

}
