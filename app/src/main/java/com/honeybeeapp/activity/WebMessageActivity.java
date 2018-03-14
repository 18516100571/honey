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
import android.widget.TextView;
import android.widget.Toast;

import com.honeybeeapp.R;
import com.honeybeeapp.base.BaseActivity;
import com.honeybeeapp.base.BaseToolBarActivity;
import com.honeybeeapp.base.Constants;
import com.honeybeeapp.utils.Tools;


/**
 * Created by avazu on 2017/7/7.
 */

public class WebMessageActivity extends BaseToolBarActivity {

    private WebView mWeb;
    private String mStrUrl;

    public static int LOAD_URL = 1;
    private String myTitle;
    private String callUrl;
    private TextView tv_asd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_message);
        mWeb = (WebView) findViewById(R.id.wv_webview);
        tv_asd = findViewById(R.id.tv_asd);
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        Intent webIntent = getIntent();
        mStrUrl = webIntent.getStringExtra("Url");
//        mStrUrl = "http://blog.csdn.net/vipzjyno1/article/details/23206387";
//        myTitle = webIntent.getStringExtra("title");
        myTitle = "彩市头条";

        setTitleText(myTitle);

        hideRightText();
        hideMorePic();
        getLlBasetitleBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mWeb.canGoBack()) {
                    // 返回上一页面
                    mWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    mWeb.goBack();
                }else{
                    finish();
                }
            }
        });

        loadUrl();

    }

    private void loadUrl() {
        if (!Tools.isNetworkAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    WebMessageActivity.this, AlertDialog.THEME_HOLO_LIGHT).setTitle(R.string.net_title)
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
                    tv_asd.setVisibility(View.GONE);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(mStrUrl);

                    return true;

                }
            });

            mWeb.loadUrl(mStrUrl);
//            mWeb.loadUrl("javascript:document.getElementById('header').style.display=\"none\"");
        }
    }

/* 改写物理按键返回的逻辑 */

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


