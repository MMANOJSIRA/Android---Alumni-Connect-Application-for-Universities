package com.example.sira.collegephonebook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sira on 8/4/16.
 */
public class ActivityWebView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        TextView urltext = (TextView)findViewById(R.id.textView2);
        TextView person = (TextView)findViewById(R.id.textView3);

        Intent ii = getIntent();
        Bundle bundle = ii.getExtras();
    String url = bundle.getString("bundleurl");
        String name = bundle.getString("bundlename");

        urltext.setText("Loading URL: " + url);
        person.setText("Profile of: "+name);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://"+url);




    }
}
