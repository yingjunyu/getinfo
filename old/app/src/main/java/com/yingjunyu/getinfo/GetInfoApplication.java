package com.yingjunyu.getinfo;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;
/**
 * Created by yingjunyu on 2016/4/18.
 */
public class GetInfoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,"7f95bf87342d58243e5a5ce0bfda6b1b");
    }
}
