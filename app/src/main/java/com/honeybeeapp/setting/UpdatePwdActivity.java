package com.honeybeeapp.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.honeybeeapp.R;
import com.honeybeeapp.base.BaseToolBarActivity;

public class UpdatePwdActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        initView();
    }

    private void initView() {
        getLlBasetitleBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitleText("修改密码");

        hideMorePic();
        hideRightText();
    }
}
