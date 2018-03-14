package com.honeybeeapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honeybeeapp.Interface.OpenHistoryInterface;
import com.honeybeeapp.R;
import com.honeybeeapp.activity.WebActivity;
import com.honeybeeapp.adapter.TransactionAdapter;
import com.honeybeeapp.base.BaseAdapter;
import com.honeybeeapp.utils.Utils;

/**
 * Created by yyszsq on 2018/2/28.
 */

public class NoticeFragment extends Fragment {
    private RecyclerView rv_recycleView;
    private TransactionAdapter mAdpater;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_notice, null);
        rv_recycleView = view.findViewById(R.id.rv_recycleView);

        mAdpater = new TransactionAdapter(getActivity(), new OpenHistoryInterface() {
            @Override
            public void lishi(int position) {
                String url = "";
                Intent intent = new Intent(getActivity(), WebActivity.class);
                if(position == 0){
                   url = "http://m.500.com/info/kaijiang/moreexpect/dlt/?from=index";
                }else if(position == 1){
                    url = "http://m.500.com//info/kaijiang/moreexpect/ssq/?from=index";

                }
                else if(position == 2){
                    url = "http://m.500.com//info/kaijiang/moreexpect/sd/?from=index";
                }
                else if(position == 3){
                    url = "http://m.500.com//info/kaijiang/moreexpect/pls/?from=index";
                }
                else if(position == 4){
                    url = "http://m.500.com//info/kaijiang/moreexpect/plw/?from=index";
                }
                else if(position == 5){
                    url = "http://m.500.com//info/kaijiang/moreexpect/qxc/?from=index";
                }
                else if(position == 6){
                    url = "http://m.500.com//info/kaijiang/moreexpect/qlc/?from=index";
                }
                intent.putExtra("Url",url);
                startActivity(intent);
            }

            @Override
            public void zoushi(int position) {
                String url = "";
                Intent intent = new Intent(getActivity(), WebActivity.class);
                if(position == 0){
                    url = "http://m.500.com/datachart/dlt/jb.html";
                }else if(position == 1){
                    url = "http://m.500.com/datachart/ssq/jb.html";

                }
                else if(position == 2){
                    url = "http://m.500.com/datachart/sd/jb.html";

                }
                else if(position == 3){
                    url = "http://m.500.com/datachart/pls/jb.html";

                }
                else if(position == 4){
                    url = "http://m.500.com/datachart/plw/jb.html";

                }
                else if(position == 5){
                    url = "http://m.500.com/datachart/qxc/jb.html";

                }
                else if(position == 6){
                    url = "http://m.500.com/datachart/qlc/jb.html";

                }

                intent.putExtra("Url",url);
                startActivity(intent);
            }

            @Override
            public void zhuanjia(int position) {
                String url = "";
                Intent intent = new Intent(getActivity(), WebActivity.class);
                if(position == 0){
                    url = "http://m.500.com/info/article/6/?from=kaijiang";
                }else if(position == 1){
                    url = "http://m.500.com/info/article/7/?from=kaijiang";

                }
                else if(position == 2){
                    url = "http://m.500.com/info/article/27/?from=kaijiang";

                }
                else if(position == 3){
                    url = "http://m.500.com/info/article/28/?from=kaijiang";

                }
                else if(position == 4){
                    url = "http://m.500.com/info/article/29/?from=kaijiang";

                }
                else if(position == 5){
                    url = "http://m.500.com/info/kaijiang/qxc/?from=kaijiang#main";

                }
                else if(position == 6){
                    url = "";

                }

                intent.putExtra("Url",url);
                startActivity(intent);
            }

            @Override
            public void zhongjiang(int position) {
                String url = "";
                Intent intent = new Intent(getActivity(), WebActivity.class);
                if(position == 0){
                    url = "http://m.500.com/info/index.php?c=zhongjiang&a=dlt&from=kaijiang";
                }else if(position == 1){
                    url = "http://m.500.com/info/index.php?c=zhongjiang&a=ssq&from=kaijiang";
                }
                else if(position == 2){
                    url = "http://m.500.com/info/index.php?c=zhongjiang&a=ssq&from=kaijiang";

                }
                else if(position == 3){
                    url = "";
                }
                else if(position == 4){
                    url = "";
                }
                else if(position == 5){
                    url = "";
                }
                else if(position == 6){
                    url = "";
                }

                intent.putExtra("Url",url);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv_recycleView.setLayoutManager(manager);
        rv_recycleView.setAdapter(mAdpater);
        mAdpater.setData(Utils.getDateList());
        mAdpater.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                String url = "";
                Intent intent = new Intent(getActivity(), WebActivity.class);
                if(position == 0){
                    url = "http://m.500.com/info/kaijiang/dlt/#main";
                }else if(position == 1){
                    url = "http://m.500.com/info/kaijiang/ssq/#main";

                }
                else if(position == 2){
                    url = "http://m.500.com/info/kaijiang/sd/#main";

                }
                else if(position == 3){
                    url = "http://m.500.com/info/kaijiang/pls/#main";

                }
                else if(position == 4){
                    url = "http://m.500.com/info/kaijiang/plw/#main";

                }
                else if(position == 5){
                    url = "http://m.500.com/info/kaijiang/qxc/#main";

                }
                else if(position == 6){
                    url = "http://m.500.com/info/kaijiang/qlc/#main";

                }
                intent.putExtra("Url",url);
                startActivity(intent);
            }
        });

        return view;
    }
}
