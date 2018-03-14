package com.honeybeeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honeybeeapp.Interface.OpenHistoryInterface;
import com.honeybeeapp.R;
import com.honeybeeapp.base.BaseAdapter;
import com.honeybeeapp.bean.GoodsInfo;


/**
 * Created by y on 2017/11/17.
 */

public class TransactionAdapter extends BaseAdapter<GoodsInfo, RecyclerView.ViewHolder> {

private OpenHistoryInterface openHistoryInterface;

    public TransactionAdapter(Context ctx, OpenHistoryInterface openHistoryInterface) {
        super(ctx);
        this.openHistoryInterface = openHistoryInterface;
    }

    @Override
    protected void bindViewHolderData(RecyclerView.ViewHolder viewHolder, final GoodsInfo data, final int position) {
        if (null != data) {
            TransactionHolder holder = (TransactionHolder) viewHolder;
            holder.tv_name.setText(data.getName());
            holder.tv_qishu.setText(data.getQishu());
            holder.tv_time.setText(data.getTime());
            if(!"".equals(data.getHistory())){
                holder.tv_lishi.setText(data.getHistory());
            }else{
                holder.tv_lishi.setVisibility(View.GONE);
            }
            if(!"".equals(data.getZoushi())){
                holder.tv_zoushi.setText(data.getZoushi());
            }else{
                holder.tv_zoushi.setVisibility(View.GONE);
            }
            if(!"".equals(data.getZhongjiang())){
                holder.tv_zhoongjiangchaxun.setText(data.
                getZhongjiang());
            }else{
                holder.tv_zhoongjiangchaxun.setVisibility(View.GONE);
            }
            if(!"".equals(data.getZhuanjia())){
                holder.tv_zhuanjia.setText(data.
                        getZhuanjia());
            }else{
                holder.tv_zhuanjia.setVisibility(View.GONE);
            }
            holder.tv_lishi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHistoryInterface.lishi(position);
                }
            });
            holder.tv_zoushi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHistoryInterface.zoushi(position);
                }
            });
            holder.tv_zhuanjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHistoryInterface.zhuanjia(position);
                }
            });
            holder.tv_zhoongjiangchaxun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHistoryInterface.zhongjiang(position);
                }
            });

//            holder.iv_icon.setImageBitmap();


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_transaction_record, null, false);
        TransactionHolder mainItemHolder = new TransactionHolder(view);
//            mainItemHolder.recyclerView.setLayoutManager(new GridLayoutManager(mCtx, 1, FullyGridLayoutManager.VERTICAL, false));
        return mainItemHolder;
    }
}
