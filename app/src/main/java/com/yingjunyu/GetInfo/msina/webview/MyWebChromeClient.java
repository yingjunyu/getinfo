package com.yingjunyu.GetInfo.msina.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by yingjunyu on 2016/8/5.
 */
public class MyWebChromeClient extends WebChromeClient{

    private String NoneStyle;

    public MyWebChromeClient(String NoneStyleS){
        this.NoneStyle = NoneStyleS;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        //Activity的进度范围在0到10000之间,所以这里要乘以100
        if(newProgress > 25){
            //Log.d("iiii","25%");
            view.loadUrl("javascript:function setTop(){document.querySelector('."
                    + NoneStyle + "').style.display=\"none\";}setTop();");
        }
    }
}
