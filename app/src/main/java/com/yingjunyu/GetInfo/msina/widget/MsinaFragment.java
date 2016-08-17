package com.yingjunyu.GetInfo.msina.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yingjunyu.GetInfo.R;
import com.yingjunyu.GetInfo.msina.webview.MyWebChromeClient;
import com.yingjunyu.GetInfo.commons.Urls;
/**
 * Created by yingjunyu on 2016/8/5.
 */
public class MsinaFragment extends Fragment {
    private WebView movieWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_webview, container, false);
        movieWebView = (WebView)view.findViewById(R.id.webView_movie);
        movieWebView.getSettings().setJavaScriptEnabled(true);

        movieWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            //在webview中打开网页链接，而不是系统自带浏览器
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
//              return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });

        //webView_news1.setWebChromeClient(new myWebChromeClient());

        //覆盖后退键方法，支持后退网页，而不是退出
        movieWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && movieWebView.canGoBack()) {
                        //Log.d(View, "key backup");
                        movieWebView.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        //去除原html页面标题，此处引用的是凤凰网新闻
        movieWebView.setWebChromeClient(new MyWebChromeClient("h_module") );

        movieWebView.loadUrl(Urls.MSINA_URL);
        return view;
    }
}
