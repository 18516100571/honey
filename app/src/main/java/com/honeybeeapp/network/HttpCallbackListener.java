package com.honeybeeapp.network;

/**
 * Created by Avazu Holding on 2018/3/14.
 */

public interface HttpCallbackListener {
    /**
     * 网络数据访问成功回调
     * @param from  由谁发起的调用,用于区别调用者
     * @param response 访问成功返回的数据
     */
    void onFinish(int from, String response);

    /**
     * 在这里对异常情况进行处理
     * @param e
     */
    void onError(Exception e);

}
