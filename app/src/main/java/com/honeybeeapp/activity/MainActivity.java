package com.honeybeeapp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.honeybeeapp.R;
import com.honeybeeapp.adapter.FragmentAdapter;
import com.honeybeeapp.fragment.CwWorkbenchFragment;
import com.honeybeeapp.fragment.InformationFragment;
import com.honeybeeapp.fragment.MyFragment;
import com.honeybeeapp.fragment.NoticeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private ArrayList<TextView> tv_menus;
    private ArrayList<ImageView> imgv_menus;
    private ViewPager mViewPager;

    private ArrayList<Fragment> mFragments;
    private FragmentAdapter mMainMenuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initData();
        initEvent();
    }


    // 初始化控件
    private void initView() {
        tv_menus = new ArrayList<TextView>();
        tv_menus.add((TextView) findViewById(R.id.tv_bottomMenu_chat));
        tv_menus.add((TextView) findViewById(R.id.tv_bottomMenu_addressbook));
        tv_menus.add((TextView) findViewById(R.id.tv_bottomMenu_discovery));
//        tv_menus.add((TextView) findViewById(R.id.tv_bottomMenu_me));
        imgv_menus = new ArrayList<ImageView>();
        imgv_menus.add((ImageView) findViewById(R.id.imgv_bottomMenu_chat));
        imgv_menus
                .add((ImageView) findViewById(R.id.imgv_bottomMenu_addressbook));
        imgv_menus
                .add((ImageView) findViewById(R.id.imgv_bottomMenu_discovery));
//        imgv_menus.add((ImageView) findViewById(R.id.imgv_bottomMenu_me));
        mViewPager = (ViewPager) findViewById(R.id.vp_main_menuContent);
    }

    // 初始化数据
    private void initData() {
        mFragments = new ArrayList<Fragment>();
        String rights="ywy";
        //根据不同的权限加载不同的工作台
            mFragments.add(new CwWorkbenchFragment());

        mFragments.add(new NoticeFragment());
        mFragments.add(new InformationFragment());
        mFragments.add(new MyFragment());
        mMainMenuAdapter = new FragmentAdapter(getSupportFragmentManager(),
                mFragments);
        setMenuSelector(0); // 默认选中第一个菜单项“工作台”
    }

    // 初始化事件
    private void initEvent() {
        mViewPager.setAdapter(mMainMenuAdapter);
        mViewPager.setOnPageChangeListener(this);
        findViewById(R.id.ll_bottomMenu_chat).setOnClickListener(this);
        findViewById(R.id.ll_bottomMenu_addressBook).setOnClickListener(this);
        findViewById(R.id.ll_bottomMenu_discovery).setOnClickListener(this);
//        findViewById(R.id.ll_bottomMenu_me).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bottomMenu_chat:
                setMenuSelector(0);
                break;
            case R.id.ll_bottomMenu_addressBook:
                setMenuSelector(1);
                break;
            case R.id.ll_bottomMenu_discovery:
                Intent intent = new Intent(this,WebMessageActivity.class);
                intent.putExtra("Url","http://zxwap.caipiao.163.com/toutiao");
                startActivity(intent);
//                setMenuSelector(2);
                break;
//            case R.id.ll_bottomMenu_me:
//                setMenuSelector(3);
//                break;

        }
    }

    /**
     * 选中指定的菜单项并显示对应的Fragment
     *
     * @param index
     */
    private void setMenuSelector(int index) {
        reSetSelected();
        tv_menus.get(index).setSelected(true);
        imgv_menus.get(index).setSelected(true);
        mViewPager.setCurrentItem(index);
    }

    /**
     * 重置底部菜单所有ImageView和TextView为未选中状态
     */
    private void reSetSelected() {
        for (int i = 0; i < tv_menus.size(); i++) {
            tv_menus.get(i).setSelected(false);
            imgv_menus.get(i).setSelected(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setMenuSelector(arg0);
    }
}


