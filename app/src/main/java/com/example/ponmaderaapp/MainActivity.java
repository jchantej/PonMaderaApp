package com.example.ponmaderaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPage();
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    private void loadPage(){
        webView = (WebView)findViewById(R.id.activity_main_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(getString(R.string.url_ponmadera));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView web, String url) {

                byte[] data = Base64.decode(getString(R.string.encode_password), Base64.DEFAULT);
                try {
                    final String password = new String(data, "UTF-8");
                    webView.loadUrl("javascript:(function(){document.getElementById('inputEmail').value = 'jchantej@gmail.com';document.getElementById('inputPassword').value = '"+password+"';})()");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}