package com.challengers.of.call.practice_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Practice.PracticeActivity;
import com.challengers.of.call.R;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class PracticeWebViewTwo extends AppCompatActivity {
    private WebView webView = null;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public String NewsId, WebViewObject, Contestid;
    public ProgressBar progressBar;
    public TextView textView, tvback;
    private static bannedapp bannedapps;
    public static Context context;

    public Dialog Popup, Exits;
    private ljsflsj good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practice_web_view_two);
        View overlay = findViewById(R.id.mylayout);
        bannedapps = new bannedapp(PracticeWebViewTwo.this);
        bannedapps.bannedbig();
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);


        context = getApplicationContext();

        Exits = new Dialog(this);
        Popup = new Dialog(this);



        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        this.webView = (WebView) findViewById(R.id.webview);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
//        webView.setPadding(0, 0, 0, 0);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setInitialScale(80);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);


        String Url = lucky.getString("gametype", null);




        webView.loadUrl(Url);


    }

    @Override
    public void onBackPressed() {

        Popup.setContentView(R.layout.othergamelayout);


        Button okbtn = (Button) Popup.findViewById(R.id.okyes);
        Button nobtn = (Button) Popup.findViewById(R.id.closePopup);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Popup.dismiss();
            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent = new Intent(PracticeWebViewTwo.this, PracticeActivity.class);

                startActivity(intent);
                finish();
                Popup.dismiss();
            }
        });

        Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        Popup.show();



    }
    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        bannedapps = new bannedapp(PracticeWebViewTwo.this);
        bannedapps.bannedbig();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        bannedapps = new bannedapp(PracticeWebViewTwo.this);
        bannedapps.bannedbig();

    }

}
