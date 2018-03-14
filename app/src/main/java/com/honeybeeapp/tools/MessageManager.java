package com.honeybeeapp.tools;

import android.content.Context;

/**
 * @author yyszsq 2017-6-12 11:20:30
 * 吐司
 *
 */
public class MessageManager {

	static CustomToast loginToast =null; 
	public static void showMsg(Context context , String msg) {
		try{
		 loginToast = new CustomToast(); 
		 loginToast.showToast(context, msg, 1000); 
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Err:(MessageManager)=showMsg():"+e.toString());
		}
	}
}
