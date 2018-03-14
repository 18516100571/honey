package com.honeybeeapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.honeybeeapp.Interface.DialogClearInterface;
import com.honeybeeapp.R;
import com.honeybeeapp.setting.AboutAppActivity;
import com.honeybeeapp.setting.NewMessageNoiceActivity;
import com.honeybeeapp.setting.PersonalDataActivity;
import com.honeybeeapp.setting.UpdatePwdActivity;
import com.honeybeeapp.utils.DialogUtils;
import com.honeybeeapp.utils.LogHelp;

/**
 * Created by yyszsq on 2018/2/28.
 */

public class MyFragment extends Fragment {
    private LinearLayout ll_personal;
    private LinearLayout ll_update_pwd;
    private LinearLayout ll_new_message;
    private LinearLayout ll_clear;
    private LinearLayout ll_about_us;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_my, null);
        initView(view);
        setOnClick();
        return view;
    }

    private void initView(View view) {
        ll_personal = view.findViewById(R.id.ll_personal);
        ll_update_pwd = view.findViewById(R.id.ll_update_pwd);
        ll_new_message = view.findViewById(R.id.ll_new_message);
        ll_clear = view.findViewById(R.id.ll_clear);
        ll_about_us = view.findViewById(R.id.ll_about_us);

    }

    private void setOnClick() {
        ll_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PersonalDataActivity.class));


            }
        });
        ll_update_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UpdatePwdActivity.class));

            }
        });
        ll_new_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewMessageNoiceActivity.class));
            }
        });
        ll_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogHelp.d("jianting --");
                DialogUtils.showUpdateDialog(getActivity(), "", new DialogClearInterface() {
                    @Override
                    public void okClear() {

                    }
                });

            }
        });
        ll_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AboutAppActivity.class));

            }
        });
    }
}
