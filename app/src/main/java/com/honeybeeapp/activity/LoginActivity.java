package com.honeybeeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.honeybeeapp.R;
import com.honeybeeapp.network.HttpCallbackListener;
import com.honeybeeapp.network.HttpUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }
    //初始化控件
    private void initview() {
        //登录按钮
        login_btn=(TextView)findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);


    }
    private void getDate(){
        HttpUtils.requestGet("", 11, new HttpCallbackListener() {
            @Override
            public void onFinish(int from, String response) {
                if(from == 11){//调用成功

                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.login_btn:// 登录
                Intent login_intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(login_intent);
                break;

            default:
                break;
        }
    }



}