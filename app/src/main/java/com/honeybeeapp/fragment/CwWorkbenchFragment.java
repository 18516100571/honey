package com.honeybeeapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.banner.CustomBanner;
import com.bumptech.glide.Glide;
import com.honeybeeapp.R;
import com.honeybeeapp.activity.WebActivity;

import java.util.ArrayList;

/**
 * Created by yyszsq on 2018/2/28.
 *
 * 财务工作台
 */





public class CwWorkbenchFragment extends Fragment {
    private CustomBanner<Integer> mBanner;

    private String[] data = {"http://f.apiplus.net/fc3d-20.json", "http://f.apiplus.net/dlt-20.json", "http://f.apiplus.net/pl3-20.json",
            "http://f.apiplus.net/pl5-20.json", "http://f.apiplus.net/qlc-20.json", "http://f.apiplus.net/qxc-20.json", "http://f.apiplus.net/ssq-20.json",
            "http://f.apiplus.net/zcbqc-20.json", "http://f.apiplus.net/zcjqc-20.json", "http://f.apiplus.net/zcsfc-20.json", "http://f.apiplus.net/ahk3-20.json"};
    /**
     * Url
     */
    private String URL1 = "http://m.zhcw.com/khd/zy/lb/16083394.shtml";
    private String URL2 = "http://m.zhcw.com/khd/zy/lb/16082377.shtml";
    private String URL3 = "http://m.zhcw.com/khd/zy/lb/16078168.shtml";
    private String URL4 = "http://m.zhcw.com/khd/zy/lb/16075087.shtml";
    private String URL5 = "http://m.zhcw.com/khd/zy/hmfx/ssq/16080249.shtml";
    private String URL6 = "http://m.zhcw.com/khd/zx/tt/gdlb/16089506.shtml";
    private String URL7 = "http://m.zhcw.com/khd/zx/tt/gdlb/16075032.shtml";
    private String URL8 = "http://m.zhcw.com/khd/zx/tt/gdlb/16075029.shtml";
    private String URL9 = "http://m.zhcw.com/khd/zx/tt/gdlb/16069848.shtml";
    private String URL10= "http://m.zhcw.com/khd/zx/tt/gdlb/16048903.shtml";
    private String URL11= "http://m.zhcw.com/khd/zx/tt/gdlb/16000517.shtml";
    private String URL12= "http://m.zhcw.com/khd/zx/tt/gdlb/15995454.shtml";
    private String URL13= "http://www.zhcw.com/xinwen/caimingushi/ssq/16051066.shtml";
    private String URL14= "http://www.zhcw.com/xinwen/caimingushi/ssq/16046830.shtml";
    private String URL15= "http://www.zhcw.com/xinwen/caimingushi/ssq/16048950.shtml";
    private String URL16= "http://www.zhcw.com/xinwen/caimingushi/ssq/15954600.shtml";
    private String URL17= "http://www.zhcw.com/xinwen/caimingushi/ssq/15954648.shtml";
    private String URL18= "http://www.zhcw.com/xinwen/caimingushi/ssq/15929773.shtml";
    private String URL19= "http://www.zhcw.com/xinwen/caimingushi/ssq/15927741.shtml";
    private String URL20= "http://www.zhcw.com/xinwen/caimingushi/ssq/15976955.shtml";
    private LinearLayout ll_message1;
    private LinearLayout ll_message2;
    private LinearLayout ll_message3;
    private LinearLayout ll_message4;
    private LinearLayout ll_message5;
    private LinearLayout ll_message6;
    private LinearLayout ll_message7;
    private LinearLayout ll_message8;
    private LinearLayout ll_message9;
    private LinearLayout ll_message10;
    private LinearLayout ll_message11;
    private LinearLayout ll_message12;
    private LinearLayout ll_message13;
    private LinearLayout ll_message14;
    private LinearLayout ll_message15;
    private LinearLayout ll_message16;
    private LinearLayout ll_message17;
    private LinearLayout ll_message18;
    private LinearLayout ll_message19;
    private LinearLayout ll_message20;




    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_cwworkbench, null);
        mBanner = (CustomBanner) view.findViewById(R.id.banner);
        initView(view);
        setLisener();

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.caipiao1);
        images.add(R.mipmap.caipiao2);
        images.add(R.mipmap.center);

        setBean(images);
        return view;
    }

    private void setLisener() {
        ll_message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL1);
                startActivity(intent);

            }
        });
        ll_message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL2);
                startActivity(intent);

            }
        });
        ll_message3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL3);
                startActivity(intent);

            }
        });
        ll_message4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL4);
                startActivity(intent);

            }
        });
        ll_message5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL5);
                startActivity(intent);

            }
        });
        ll_message6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL6);
                startActivity(intent);

            }
        });
        ll_message7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL7);
                startActivity(intent);

            }
        });
        ll_message8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL8);
                startActivity(intent);

            }
        });
        ll_message9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL9);
                startActivity(intent);

            }
        });
        ll_message10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL10);
                startActivity(intent);

            }
        });
        ll_message11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL11);
                startActivity(intent);

            }
        });
        ll_message12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL12);
                startActivity(intent);

            }
        });
        ll_message13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL13);
                startActivity(intent);

            }
        });
        ll_message14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL14);
                startActivity(intent);

            }
        });
        ll_message15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL15);
                startActivity(intent);

            }
        });
        ll_message16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL16);
                startActivity(intent);

            }
        });
        ll_message17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL17);
                startActivity(intent);

            }
        });
        ll_message18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL18);
                startActivity(intent);

            }
        });
        ll_message19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL19);
                startActivity(intent);

            }
        });
        ll_message20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("Url",URL20);
                startActivity(intent);

            }
        });
    }

    private void initView(View view) {
        ll_message1 = view.findViewById(R.id.ll_message1);
        ll_message2 = view.findViewById(R.id.ll_message2);
        ll_message3 = view.findViewById(R.id.ll_message3);
        ll_message4 = view.findViewById(R.id.ll_message4);
        ll_message5 = view.findViewById(R.id.ll_message5);
        ll_message6 = view.findViewById(R.id.ll_message6);
        ll_message7 = view.findViewById(R.id.ll_message7);
        ll_message8 = view.findViewById(R.id.ll_message8);
        ll_message9= view.findViewById(R.id.ll_message9);
        ll_message10 = view.findViewById(R.id.ll_message10);
        ll_message11 = view.findViewById(R.id.ll_message11);
        ll_message12 = view.findViewById(R.id.ll_message12);
        ll_message13 = view.findViewById(R.id.ll_message13);
        ll_message14 = view.findViewById(R.id.ll_message14);
        ll_message15 = view.findViewById(R.id.ll_message15);
        ll_message16 = view.findViewById(R.id.ll_message16);
        ll_message17 = view.findViewById(R.id.ll_message17);
        ll_message18 = view.findViewById(R.id.ll_message18);
        ll_message19 = view.findViewById(R.id.ll_message19);
        ll_message20 = view.findViewById(R.id.ll_message20);

    }

    private void setBean(final ArrayList beans) {
        mBanner.setPages(new CustomBanner.ViewCreator<Integer>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, Integer entity) {
                Glide.with(context).load(entity).into((ImageView) view);
            }
        }, beans)
//                //设置指示器为普通指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.ORDINARY)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
//                //设置指示器的方向
//                .setIndicatorGravity(CustomBanner.IndicatorGravity.CENTER)
//                //设置指示器的指示点间隔
//                .setIndicatorInterval(20)
                //设置自动翻页
                .startTurning(5000);
        mBanner.setOnPageClickListener(new CustomBanner.OnPageClickListener() {
            @Override
            public void onPageClick(int position, Object o) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                if(position == 0){
                    Intent intent2 = new Intent(getActivity(),WebActivity.class);
                    intent2.putExtra("Url","http://www.zhcw.com/ssq/caiminzhijia/16054168.shtml");
                    startActivity(intent2);
                }else if(position == 1){
                    Intent intent2 = new Intent(getActivity(),WebActivity.class);
                    intent2.putExtra("Url","http://www.zhcw.com/xinwen/jigouyaowen/15803519.shtml");
                    startActivity(intent2);

                }else if(position == 2){
                    Intent intent2 = new Intent(getActivity(),WebActivity.class);
                    intent2.putExtra("Url","http://www.zhcw.com/ssq/");
                    startActivity(intent2);

                }


            }
        });
    }

//    //设置普通指示器
//    private void setBean(final ArrayList beans) {
//        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
//            @Override
//            public View createView(Context context, int position) {
//                ImageView imageView = new ImageView(context);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                return imageView;
//            }
//
//            @Override
//            public void updateUI(Context context, View view, int position, String entity) {
//                Glide.with(context).load(entity).into((ImageView) view);
//            }
//        }, beans)
//                //设置指示器为普通指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.NUMBER)
//                //设置指示器的方向
//                .setIndicatorGravity(CustomBanner.IndicatorGravity.RIGHT)
//                //设置自动翻页
//                .startTurning(5000);
//    }

//    //设置没有指示器
//    private void setBean(final ArrayList beans) {
//        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
//            @Override
//            public View createView(Context context, int position) {
//                ImageView imageView = new ImageView(context);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                return imageView;
//            }
//
//            @Override
//            public void updateUI(Context context, View view, int position, String entity) {
//                Glide.with(context).load(entity).into((ImageView) view);
//            }
//        }, beans)
//                //设置没有指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.NONE)
//                //设置自动翻页
//                .startTurning(5000);
//    }

}
