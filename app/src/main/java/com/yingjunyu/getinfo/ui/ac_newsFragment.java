package com.yingjunyu.getinfo.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        webView_news1.loadUrl("http://m.baidu.com/news");
        return view;
    }
}
