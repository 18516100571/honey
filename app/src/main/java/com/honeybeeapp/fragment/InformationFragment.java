package com.honeybeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honeybeeapp.R;

/**
 * Created by yyszsq on 2018/2/28.
 */

public class InformationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_information, null);
        return view;
    }
}
