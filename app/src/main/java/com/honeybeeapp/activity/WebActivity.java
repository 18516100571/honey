package com.honeybeeapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.honeybeeapp.R;
import com.honeybeeapp.base.BaseActivity;
import com.honeybeeapp.base.BaseToolBarActivity;
import com.honeybeeapp.base.Constants;
import com.honeybeeapp.utils.Tools;


/**
 * Created by avazu on 2017/7/7.
 */

public class WebActivity extends BaseActivity {

    private WebView mWeb;
    private String mStrUrl;

    public static int LOAD_URL = 1;
    private String myTitle;
    private String callUrl;


    public static CallListener callListener;

    public static void setCallListener(CallListener callListener) {
        callListener = callListener;
    }


    public interface CallListener {
        void call();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        mWeb = (WebView) findViewById(R.id.wv_webview);
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        Intent webIntent = getIntent();
        mStrUrl = webIntent.getStringExtra("Url");
//        mStrUrl = "http://blog.csdn.net/vipzjyno1/article/details/23206387";
//        myTitle = webIntent.getStringExtra("title");
//        myTitle = "开奖信息";
//
//        setTitleText(myTitle);
//
//        hideRightText();
//        hideMorePic();
//        getLlBasetitleBack().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ( mWeb.canGoBack()) {
//                    // 返回上一页面
//                    mWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//                    mWeb.goBack();
//                }else{
//                    finish();
//                }
//            }
//        });

        loadUrl();

    }

    private void loadUrl() {
        if (!Tools.isNetworkAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    WebActivity.this, AlertDialog.THEME_HOLO_LIGHT).setTitle(R.string.net_title)
                    .setMessage(R.string.net_set);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.net_yes,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            Intent mIntent = new Intent(
                                    Settings.ACTION_WIFI_SETTINGS);
                            startActivityForResult(mIntent, Constants.OPEN_NETWORK);
                        }
                    })
                    .setNeutralButton(R.string.net_no,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog,
                                        int whichButton) {
                                    dialog.cancel();
                                    finish();
                                }
                            }).show();
        } else {
//            LogHelp.i( "sadfasdfsd-=-=sdafasd" + mStrUrl);
            mWeb.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.loadUrl("javascript:$('#header').hide();");
//                    tv_asd.setVisibility(View.GONE);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    Log.i("webview", url + "true");
                    if (url.startsWith("market://details?id=")) {
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        return true;
                    } else if (url.startsWith("tel:")) {
//                        Tools.showCallDialog(WebActivity.this,url {
//                            @Override
//                            public void call() {
//                                requestPermission(url);
//                                callUrl = url;
//                            }
//                        });
                        return true;

                    } else {
                        view.loadUrl(url);
                        return true;
                    }
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    return super.shouldOverrideUrlLoading(view, request);
                    Log.i("webview", mStrUrl + "asdfasdfasd");
                    view.loadUrl(mStrUrl);

                    return true;

                }
            });
            Log.i("webview", mStrUrl);
//
            mWeb.loadUrl(mStrUrl);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.OPEN_NETWORK:
                mHandler.sendEmptyMessage(LOAD_URL);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface RequestPermissionType {

        /**
         * 请求打电话的权限码
         */
        int REQUEST_CODE_ASK_CALL_PHONE = 100;
    }

    /**
     * 申请权限
     */
    private void requestPermission(String url) {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(WebActivity.this, android.Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(WebActivity.this, new String[]{android.Manifest.permission.CALL_PHONE},
                        RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                callPhone(url);
            }
        } else {
            callPhone(url);
        }
    }

    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!"".equals(callUrl) && null != callUrl) {

                        callPhone(callUrl);
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(WebActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 拨号方法
     */
    private void callPhone(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse(url));
        startActivity(intent);


    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    loadUrl();
                    break;
            }
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWeb.canGoBack()) {
            // 返回上一页面
            mWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            mWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}


