package com.honeybeeapp.utils;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.honeybeeapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;


public class Tools {


    /**
     * app统计
     *
     * @return
     */




    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }




    public static String optString(JSONObject obj, String key) {
        if (obj.isNull(key)) {
            return "";
        } else return obj.optString(key);
    }

    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static List<String> findDeniedPermissions(Activity activity, String... permission) {
        if (activity == null) {
            return null;
        }

        List<String> denyPermissions = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= 23) {
            for (String value : permission) {
                if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                    denyPermissions.add(value);
                }
            }
        }

        return denyPermissions;
    }

    public static boolean shouldShowRequestPermissionRationale(Object object, String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) object, perm);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else {
            return object instanceof android.app.Fragment
                    && Build.VERSION.SDK_INT >= 23
                    && ((android.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
        }
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return ((Activity) object);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).getActivity();
        } else {
            return null;
        }
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static int getIsFristOpenApp(Context ctx, int i) {
        SharedPreferences share = ctx.getSharedPreferences("opay", Context.MODE_PRIVATE);
        return share.getInt("isFrisOpenApp", i);
    }

    public static void setIsFristOpenApp(Context ctx, int strToken) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("opay", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("isFrisOpenApp", strToken);
        editor.commit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
   /* public static void setDataList(Context mContext, ArrayList<PushSaveListItem> datalist) {
        SharedPreferences  preferences = mContext.getSharedPreferences("opay", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = preferences.edit();

        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString("PushItem", strJson);
        editor.commit();

    }

    */

    /**
     * 获取List
     *
     * @return
     *//*
    public  static  ArrayList<PushSaveListItem> getDataList(Context mContext) {
        SharedPreferences  preferences = mContext.getSharedPreferences("opay", Context.MODE_PRIVATE);
//        SharedPreferences.Editor  editor = preferences.edit();
        ArrayList<PushSaveListItem> datalist=new ArrayList<PushSaveListItem>();
        String strJson = preferences.getString("PushItem", null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<PushSaveListItem>>() {
        }.getType());
        return datalist;

    }*/
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.substring(0, 32).toString();
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }


    //根据value值获取到对应的一个key值
    public static String getKey(HashMap<String, String> map, String value) {
        String key = null;
        //Map,HashMap并没有实现Iteratable接口.不能用于增强for循环.
        for (String getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                key = getKey;
            }
        }

        return key;
        //这个key肯定是最后一个满足该条件的key.
    }





    public static String getLocalJSONString(Context ctx) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = ctx.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("countrylist.json")));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }



    public static String getMsisdn(Context context) {
        return "";
    }

    public static String getOsVersion() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

    public static String getManufacturer() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

    public static String getKeyFromValue(Context ctx, String strValue, int[] iArr) {
        int nLength = iArr.length;
        if (nLength <= 0) return "";
        for (int i = 0; i < nLength; i++) {
            if (ctx.getString(iArr[i]).equals(strValue)) {
                return "0" + String.valueOf(i);
            }
        }
        return "";
    }

    public static String getValueFromKey(Context ctx, String strKey, int[] iArr) {
        try {
            int nKey = Integer.valueOf(strKey);
            String strRet = "";
            strRet = ctx.getString(iArr[nKey]);
            return strRet;
        } catch (NumberFormatException e) {

        } catch (IndexOutOfBoundsException e) {

        }
        return "";
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getAppVersion(Context context) {

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (Exception e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
            return "";
        }
    }

    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {

            if (Character.isDigit(str.charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {   //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;

    }

    public static void toastStr(Context context, String message) {
        Toast t = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }




	/*public static HashMap<String, String> appendParams(HashMap<String, String> map,Context context){
        if (map == null){
			map = new HashMap<String, String>();
		}
		map.put("mobile_device_id", encode(getDeviceID(context)));
		map.put("app_id", encode("1"));
        map.put("user_id", encode(getUid(context)));
        map.put("token", encode(getAccessToken(context)));
		return map;
	}*/



    //{"age":"26","name":"zitong"}
    public static String mapToJSONString(HashMap<String, String> map) {
        String strPrefix = "{";
        String strPostfix = "}";
        String strContent = "";
        for (String key : map.keySet()) {
            strContent = strContent + '"' + key + '"' + ":" + '"' + map.get(key) + '"' + ",";
        }
        if (strContent.length() > 0) {
            strContent = strContent.substring(0, strContent.length() - 1);
        }
        return strPrefix + strContent + strPostfix;
    }


    public static String encode(String parameter) {
        if (!TextUtils.isEmpty(parameter)) {
            try {
                parameter = URLEncoder.encode(parameter, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return parameter;
    }

    public static String decode(String parameter) {
        if (!TextUtils.isEmpty(parameter)) {
            try {
                parameter = URLDecoder.decode(parameter, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return parameter;
    }

    public static void intentActivity(Context context, Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static String resetPrice(String price) {
        String s = "";
        int index = 0;
        index = price.lastIndexOf(".");
        if (index == -1) {
            price += ".00";
            index = price.lastIndexOf(".");
        }
        for (int i = index; --i >= 0; ) {
            if (!Character.isDigit(price.charAt(i))) {
                s = price.substring(i + 1, price.length());
                return s;
            }
        }
        return s;
    }

    public static String getLanguage() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        //	String language = locale.getLanguage() + "-" + country.toUpperCase();
        String language = country.toLowerCase();
        return language;
    }

    public static Drawable readSDIMG(Context context, String imgpath) {
        File file = new File(imgpath);
        InputStream is = null;
        Drawable able = null;
        try {
            is = new FileInputStream(file);
            able = Drawable.createFromResourceStream(context.getResources(),
                    null, is, "icon", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return able;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }




    public static String rsaDecode(String strSrc) {

        RSAPublicKey i;
        Cipher ci = null;
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }


    /**
     * SHA加密
     *
     * @param strSrc 明文
     * @return 加密之后的密文
     */
    public static String shaEncrypt(String strSrc) {

        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(60 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    public static boolean copyFolder(String oldPath, String newPath) {
        boolean isok = true;
        try {
            File f = new File(newPath);
            // (new File(newPath)).mkdirs();
            if (!f.exists()) {
                File localFile = new File(newPath);
                localFile.mkdirs();
                String str = "chmod" + newPath + " " + "777"
                        + " &busybox chmod " + newPath + " " + "777";
                try {
                    Runtime.getRuntime().exec(str);
                } catch (Exception e) {

                }
            }
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + File.separator + (temp.getName()).toString());
                    // output.write(readFileData(newPath, temp.getName(),
                    // (int) temp.length()));
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();

                }
            }
        } catch (Exception e) {
            isok = false;
        }
        return isok;
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String folderPath) {
        boolean isSuccess = false;
        File file = new File(folderPath);
        if (!file.exists()) {
            return isSuccess;
        }
        if (!file.isDirectory()) {
            return isSuccess;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (folderPath.endsWith(File.separator)) {
                temp = new File(folderPath + tempList[i]);
            } else {
                temp = new File(folderPath + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(folderPath + File.separator + tempList[i]);
                delFolder(folderPath + File.separator + tempList[i]);
                isSuccess = true;
            }
        }
        return isSuccess;
    }

    public byte[] readFileData(String path, String fileName, int size) {
        String dirPath = path + File.separator + fileName;
        RandomAccessFile f;
        byte[] data = new byte[size];
        try {
            f = new RandomAccessFile(new File(dirPath), "rwd");
            f.read(data);
            f.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }





    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }








    public static HashMap<String, String> getLoanUserInfoIndustryParameters() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("queryType", "02");
        map.put("industryId", "");
        return map;
    }

    public static HashMap<String, String> getMerchantList(String strContent) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("merchantName", strContent);
        return map;
    }


    public static HashMap<String, String> getAppAuthLoginWithSmsParameters(String strContent) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("merchantName", strContent);
        return map;
    }

    //only for bmp from camera
    public static Bitmap compressBitMap(String strPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(strPath, options);


        return getimage(bitmap, options.outWidth, options.outHeight, strPath);


    }


    public static Bitmap getimage(Bitmap bitmap, int nW, int nH, String strPath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        if (baos.toByteArray().length / 1024 < 1024) {
            return bitmap;
        }


        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (nW > nH && nW > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (nW / ww);
        } else if (nW < nH && nH > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (nH / hh);
        }
        if (be <= 0)
            be = 1;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = be;//设置缩放比例
        return compressImage(BitmapFactory.decodeFile(strPath, options));

    }

    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 700f;//这里设置高度为800f
        float ww = 700f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1024) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static String getAppVersion(Activity activity) {
        PackageManager pm = activity.getPackageManager();//context为当前Activity上下文
        PackageInfo pi;
        String versionCode = "";
        try {
            pi = pm.getPackageInfo(activity.getPackageName(), 0);
            versionCode = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return versionCode;
    }








    public static int DpToPx(Context context, float dp) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dp * scale + 0.5f);
    }

    public static String hidePhoneNumber(String strHideNumber) {
        int nLength = strHideNumber.length();
        if (nLength >= 8) {
            strHideNumber = strHideNumber.replace(strHideNumber.substring(nLength - 8, nLength - 4), "****");
        } else if (nLength >= 6 && nLength < 8) {
            strHideNumber = strHideNumber.replace(strHideNumber.substring(nLength - 6, nLength - 3), "***");
        }
        return strHideNumber;
    }

    public static String getVersionCode(Context context) {
        return getPackageInfo(context).versionName;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }




    public static int isNeedUpdate(String netVersion, String localVersion) {
        if (netVersion.equals(localVersion)) {
            return 0;
        }
        String[] version1Array = netVersion.split("\\.");
        String[] version2Array = localVersion.split("\\.");
        Log.d("HomePageActivity", "version1Array==" + version1Array.length);
        Log.d("HomePageActivity", "version2Array==" + version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }












    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 获取底部虚拟键盘的高度
     */
    public static int getBottomKeyboardHeight(Context ctx) {
        Activity ac = (Activity) ctx;
        int screenHeight = getAccurateScreenDpi(ac)[1];
        DisplayMetrics dm = new DisplayMetrics();
        ac.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }

    /**
     * 获取精确的屏幕大小
     */
    public static int[] getAccurateScreenDpi(Activity ac) {
        int[] screenWH = new int[2];
        Display display = ac.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenWH;
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int nHeight = 0;
    public static int nWidth = 0;

    public static boolean is16B9() {


        Float f = (float) nHeight / nWidth;

        if (f > 1.78) {
            return false;
        } else return true;
    }

    public static BigDecimal moneySub(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2);
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo[] infos = cm.getAllNetworkInfo();
        if (infos == null) {
            return false;
        }
        for (NetworkInfo info : infos) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
//		return true;
    }
    public static void showCallDialog(final Activity activity, final String url) {
        final View reg = View.inflate(activity,
                R.layout.dialog_call, null);// R.layout.dialog_register
        final Dialog dialog = new Dialog(activity,
                R.style.Dialog_clear);//
        TextView on = (TextView) reg.findViewById(R.id.hk_on);
        TextView cancel = (TextView) reg.findViewById(R.id.hk_back);
        TextView tv_pwd_message = (TextView) reg.findViewById(R.id.tv_pwd_message);
        tv_pwd_message.setText(url.replace("tel:", ""));
        cancel.setText("取消");
        on.setText("确定");

        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        dialog.addContentView(reg, lp);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        WindowManager windowManager = activity
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp1 = dialog.getWindow().getAttributes();
        lp1.width = (int) (display.getWidth());
        // dialog.getWindow().setAttributes(lp);
        int px = DpToPx(activity, 65);
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
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
//                activity.startActivity(intent);

            }
        });

    }
}


