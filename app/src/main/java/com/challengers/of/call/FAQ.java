package com.challengers.of.call;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.challengers.of.call.Banned_APP.bannedapp;

public class FAQ extends AppCompatActivity {

    private WebView webView = null;
    private ImageView ivback;
    private static bannedapp bannedapps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        bannedapps = new bannedapp(FAQ.this);
        bannedapps.bannedbig();

        this.webView = (WebView) findViewById(R.id.webview);
        ivback = (ImageView)findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQ.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        webView.loadUrl("http://callofchallengers.com/coc/faq.php");
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FAQ.this,Dashboard.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);
    }
}
