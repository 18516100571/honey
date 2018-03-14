package com.honeybeeapp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.honeybeeapp.R;
import com.honeybeeapp.base.permission.EasyPermission;
import com.honeybeeapp.base.permission.PermissionCallBackM;
import com.honeybeeapp.utils.CacheActivity;


public  class BaseActivity extends Activity implements EasyPermission.PermissionCallback{

    private ProgressDialog pd = null;

    private int mRequestCode;
    private String[] mPermissions;
    private PermissionCallBackM mPermissionCallBack;
    public int nPermissionHintSTRId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void showLoading(String strContent){
        String strShowContent = strContent;

//        if (strShowContent == null || strShowContent.isEmpty()){
            strShowContent = getResources().getString(R.string.base_progress_dlg_text);
//        }
        if (pd == null){
            pd = ProgressDialog.show(this, "", strShowContent,
                    true);
            pd.setCanceledOnTouchOutside(false);
        }else{
            pd.setMessage(strShowContent);
            pd.show();
        }
    }


    public void dismissLoading(){
        if (pd != null && pd.isShowing()){
            pd.dismiss();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        dismissLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void updateUI(){

    }

    public void addToActivityStack(){
        if (!CacheActivity.activityList.contains(this)) {
            CacheActivity.addActivity(this);
        }
    }

    //rationale: 申请授权理由
    public void requestPermission(int requestCode, String[] permissions, String rationale,
                                     PermissionCallBackM permissionCallback) {
        this.mRequestCode = requestCode;
        this.mPermissionCallBack = permissionCallback;
        this.mPermissions = permissions;

        EasyPermission.with(this)
                .addRequestCode(requestCode)
                .permissions(permissions)
                //.nagativeButtonText(android.R.string.ok)
                //.positveButtonText(android.R.string.cancel)
                .rationale(rationale)
                .request();
    }


    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
            从Settings界面跳转回来，标准代码，就这么写
        */
        if (requestCode == EasyPermission.SETTINGS_REQ_CODE) {
            if (EasyPermission.hasPermissions(this, mPermissions)) {
                //已授权，处理业务逻辑
                onEasyPermissionGranted(mRequestCode, mPermissions);
            } else {
                onEasyPermissionDenied(mRequestCode, mPermissions);
            }
        }
    }

    @Override public void onEasyPermissionGranted(int requestCode, String... perms) {
        if (mPermissionCallBack != null) {
            mPermissionCallBack.onPermissionGrantedM(requestCode, perms);
        }
    }

    @Override public void onEasyPermissionDenied(final int requestCode, final String... perms) {
        //rationale: Never Ask Again后的提示信息
        if (EasyPermission.checkDeniedPermissionsNeverAskAgain(this, getString(nPermissionHintSTRId), android.R.string.ok,
                android.R.string.cancel,
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        if (mPermissionCallBack != null) {
                            mPermissionCallBack.onPermissionDeniedM(
                                    requestCode, perms);
                        }
                    }
                }, perms)) {
            return;
        }

        if (mPermissionCallBack != null) {
            mPermissionCallBack.onPermissionDeniedM(requestCode, perms);
        }
    }


}