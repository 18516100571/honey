package com.honeybeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honeybeeapp.R;
import com.honeybeeapp.adapter.TransactionAdapter;
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

        mAdpater = new TransactionAdapter(getActivity());

        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv_recycleView.setLayoutManager(manager);
        rv_recycleView.setAdapter(mAdpater);
        mAdpater.setData(Utils.getDateList());

        return view;
    }
}
