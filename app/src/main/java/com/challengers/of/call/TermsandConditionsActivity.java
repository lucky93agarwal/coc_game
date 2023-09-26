package com.challengers.of.call;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

public class TermsandConditionsActivity extends AppCompatActivity {

    private WebView webView = null;
    private ImageView ivback;
    public String From;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsand_conditions);

          From = getIntent().getStringExtra("From");

        this.webView = (WebView) findViewById(R.id.webview);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        webView.loadUrl("http://callofchallengers.com/coc/term_condition.php");
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


            Intent intent = new Intent(TermsandConditionsActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);

    }
}
