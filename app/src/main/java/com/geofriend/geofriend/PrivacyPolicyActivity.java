package com.geofriend.geofriend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class PrivacyPolicyActivity extends Activity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        web =(WebView)findViewById(R.id.webView);
        web.loadUrl("https://sites.google.com/view/geofriend/home/privacy-policy?fbclid=IwAR2N2bCWgJfSsfDEkdHFzdwDC9IXwSmzuikKl1ab0piL8T3cY_s0EOKfafo");

    }

}