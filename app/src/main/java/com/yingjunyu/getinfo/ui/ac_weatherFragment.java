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
public class ac_weatherFragment extends Fragment {

    private WebView webView_weather;

    public ac_weatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ac_weather, container, false);
        webView_weather = (WebView) view.findViewById(R.id.webView_weather);
        webView_weather.getSettings().setJavaScriptEnabled(true);
        webView_weather.loadUrl("http://m.baidu.com/from=0/s?word=%E6%AD%A6%E6%B1%89%E5%A4%A9%E6%B0%94&sa=ipw");
        return view;
    }
}
