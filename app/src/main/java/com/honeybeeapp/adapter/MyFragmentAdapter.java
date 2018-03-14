package com.honeybeeapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yyszsq on 2018/3/1.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
}