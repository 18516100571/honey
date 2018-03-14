package com.honeybeeapp.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.honeybeeapp.Interface.DialogClearInterface;
import com.honeybeeapp.R;
import com.honeybeeapp.base.MyApplication;

/**
 * Created by Avazu Holding on 2018/3/1.
 */

public class DialogUtils {

    public static void showUpdateDialog(final Activity context, final String url, final DialogClearInterface dialogInterface) {
        final View reg = View.inflate(context,
                R.layout.dialog_clear, null);// R.layout.dialog_register
        final Dialog dialog = new Dialog(context,
                R.style.Dialog_clear);// ���е�dialog��һ��
        TextView on = (TextView) reg.findViewById(R.id.hk_on);
        TextView cancel = (TextView) reg.findViewById(R.id.hk_back);

        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        dialog.addContentView(reg, lp);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        WindowManager windowManager = context
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp1 = dialog.getWindow().getAttributes();
        lp1.width = (int) (display.getWidth());
        // dialog.getWindow().setAttributes(lp);
        int px = Tools.DpToPx(context, 65);
        lp1.width = lp1.width - px;
        dialog.getWindow().setAttributes(lp1);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                dialog.dismiss();

            }
        });
        on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogInterface.okClear();
            }
        });

    }
}
