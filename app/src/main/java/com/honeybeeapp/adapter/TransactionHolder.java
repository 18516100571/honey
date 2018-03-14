package com.honeybeeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honeybeeapp.R;


/**
 * Created by y on 2017/11/17.
 */

public class TransactionHolder extends RecyclerView.ViewHolder {
    public TextView tv_name,tv_qishu,tv_time,tv_lishi,tv_zoushi,tv_zhoongjiangchaxun,tv_zhuanjia;



    public TransactionHolder(View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_qishu = itemView.findViewById(R.id.tv_qishu);
        tv_time = itemView.findViewById(R.id.tv_time);
        tv_lishi = itemView.findViewById(R.id.tv_lishi);
        tv_zoushi = itemView.findViewById(R.id.tv_zoushi);
        tv_zhoongjiangchaxun = itemView.findViewById(R.id.tv_zhoongjiangchaxun);
        tv_zhuanjia = itemView.findViewById(R.id.tv_zhuanjia);



    }
}
