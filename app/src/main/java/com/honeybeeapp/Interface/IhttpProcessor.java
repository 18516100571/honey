package com.honeybeeapp.Interface;

import java.util.List;
import java.util.Map;

public interface IhttpProcessor {
    //GET请求
    void get(String url, Map<String, Object> params, ICallBack callback);

    //POST请求
    void post(String url, Map<String, Object> params, ICallBack callback);

    //上传文件
    void postFile(String url, List<String> filePathList, ICallBack callBack);
}
