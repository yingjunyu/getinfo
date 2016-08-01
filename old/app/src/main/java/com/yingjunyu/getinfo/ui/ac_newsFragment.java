package com.yingjunyu.getinfo.ui;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

import com.yingjunyu.getinfo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ac_newsFragment extends Fragment {

    private WebView webView_news1;
    public ac_newsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ac_news, container, false);
        webView_news1 = (WebView)view.findViewById(R.id.webView_news1);
        webView_news1.getSettings().setJavaScriptEnabled(true);

        webView_news1.setWebViewClient(new WebViewClient()
        {
            @Override
            //在webview中打开网页链接，而不是系统自带浏览器
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
//              return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
            @Override
            //页面加载开始，可以写方法
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                //super.onPageStarted(view, url, favicon);
                //mProgressDialog.show();
            }
            @Override
            //页面加载结束，可以写方法
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                //view.loadUrl("javascript:function setTop(){document.querySelector('.slTit').style.display=\"none\";}setTop();");
            }
        });

        //webView_news1.setWebChromeClient(new myWebChromeClient());

        //覆盖后退键方法，支持后退网页，而不是退出
        webView_news1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView_news1.canGoBack()) {
                        //Log.d(View, "key backup");
                        webView_news1.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        //去除原html页面标题，此处引用的是凤凰网新闻
        webView_news1.setWebChromeClient(new WebChromeClient() {
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //Activity的进度范围在0到10000之间,所以这里要乘以100
                if(newProgress > 25){
                    //Log.d("iiii","25%");
                    view.loadUrl("javascript:function setTop(){document.querySelector('.slTit').style.display=\"none\";}setTop();");
                }
            }
        });

        webView_news1.loadUrl("http://inews.ifeng.com/?srctag=xzydh1");
        return view;
    }
}
