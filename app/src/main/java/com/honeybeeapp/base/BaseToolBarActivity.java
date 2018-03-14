package com.honeybeeapp.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honeybeeapp.R;


//import android.support.v7.app.AppCompatActivity;


public  class BaseToolBarActivity extends BaseActivity {


    private RelativeLayout rlRoot;
    private ImageButton ivBack;
    private TextView tvBaseTitle;
  //  private TextView tvBasetitleOK;
    private ImageView ivMore;
    public TextView tvBaseRight;

    private RelativeLayout rlRetryView;
    private LinearLayout llClickRetry;
    private int nFunctionId = -1;

    private IRetryListener mListener = null;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int resId) {
        View localView = getLayoutInflater().from(this).inflate(R.layout.base_toolbar, null);
        View refreshView = getLayoutInflater().from(this).inflate(R.layout.retry_layout, null);
        rlRetryView = (RelativeLayout)refreshView.findViewById(R.id.rl_refresh);
        llClickRetry = (LinearLayout)refreshView.findViewById(R.id.rl_click_retry);

        hideRetry();
        findView(localView);
        View view = getLayoutInflater().inflate(resId, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.base_title_bar);

        if (null != rlRoot){
            rlRoot.addView(view, lp);
            rlRoot.addView(refreshView,lp);
        }

        super.setContentView(rlRoot);

        llClickRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    hideRetry();
                    mListener.onRetry(nFunctionId);
                    mListener = null;
                    nFunctionId = -1;
                }
            }
        });
    }

    private void findView(View v) {
        rlRoot = (RelativeLayout) v.findViewById(R.id.base_view);
        ivBack = (ImageButton) v.findViewById(R.id.base_back_btn);
        tvBaseTitle = (TextView) v.findViewById(R.id.base_title_tv);
   //     tvBasetitleOK = (TextView) v.findViewById(R.id.base_menu_btn);
        ivMore = (ImageView) v.findViewById(R.id.iv_base_more);
        tvBaseRight = (TextView)v.findViewById(R.id.tv_base_right);
    }

    public void setTitleText(CharSequence c) {
        if (tvBaseTitle != null)
            tvBaseTitle.setText(c);
    }

    public void setRightText(CharSequence c) {
        if (tvBaseRight != null)
            tvBaseRight.setText(c);
    }

    public void setRightTextColor(int resId){
        if (tvBaseRight != null)
            tvBaseRight.setTextColor(resId);
    }

    public void hideRightText(){
        if (tvBaseRight != null)
            tvBaseRight.setVisibility(View.GONE);
    }

    public void showRightText(){
        if (tvBaseRight != null)
            tvBaseRight.setVisibility(View.VISIBLE);
    }

    public void hideMorePic(){
        if (ivMore != null)
            ivMore.setVisibility(View.GONE);
    }

    public void showMorePic(){
        if (ivMore != null)
            ivMore.setVisibility(View.VISIBLE);
    }

    /**
     *
     * 设置中间标题文字
     * @param resId
     */
    public void setTitleText(int resId) {
        if (tvBaseTitle != null)
            tvBaseTitle.setText(resId);
    }



    public void  showRetry(){
        rlRetryView.setVisibility(View.VISIBLE);
    }

    public void hideRetry(){
        rlRetryView.setVisibility(View.GONE);
    }

    public ImageButton getLlBasetitleBack() {
        return ivBack;
    }

    public void setBasetitleBackVisible(int nStatus) {
        ivBack.setVisibility(nStatus);
    }


    public TextView getTvBaseTitle() {
        return tvBaseTitle;
    }


    public ImageView getIvBaseMore(){return  ivMore;}

    public TextView getTvBaseRight(){
        return tvBaseRight;
    }



    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus()
    {
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

    }



    public static interface IRetryListener {
        public void onRetry(int nIndex);
    }

    public void setRetryListener(IRetryListener listener){
        mListener = listener;
    }

    public void showLoading(String strContent,IRetryListener listener){

        mListener = listener;
        super.showLoading(strContent);
    }

    public void dismissLoadingIOException(){
        if (mListener != null){
            showRetry();

        }

        dismissLoading();
    }

}