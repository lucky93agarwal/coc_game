package com.challengers.of.call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.TextingtwoActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class TextinActivity extends AppCompatActivity {
    private static bannedapp bannedapps;
    private WebView webView = null ;
    JSONObject jasonObject ;
    JSONArray jsonArray = null ;
    public String NewsId , WebViewObject, Contestid ;
    public ProgressBar progressBar ;
    public static Context context;
    public TextView textView, tvback;
    public Dialog Popup, Exits;

    public String Count;

    private String From;
    private ljsflsj good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window. FEATURE_PROGRESS );
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        bannedapps = new bannedapp(TextinActivity.this);
        bannedapps.bannedbig();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_textin);
        context = getApplicationContext();
//        From = getIntent().getStringExtra("From");
        Popup = new Dialog(this);
        Exits = new Dialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences("Count", Context.MODE_PRIVATE);
        Count = sharedPreferences.getString("Count", null);
        From = getIntent().getStringExtra("From");

//        Toast.makeText(context, From, Toast.LENGTH_SHORT).show();






//        mWebView.setInitialScale(ZOOM_LEVEL);

//        textView = (TextView)findViewById(R.id.pdf);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

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
        webView .setWebChromeClient( new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar .setProgress(progress);
                if (progress == 100 ) {
                    progressBar .setVisibility(View. GONE );
                } else {
                    progressBar .setVisibility(View. VISIBLE );
                }
            }
        });
        WebSettings webSettings = webView .getSettings();
        webSettings.setJavaScriptEnabled( true );
        webView .setWebViewClient( new WebViewClient());
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);


        SharedPreferences lucky = getSharedPreferences("coding",Context.MODE_PRIVATE);
        String Loginid = lucky.getString("userid", null);
        String gameid = lucky.getString("gameid",null);


        String Url = lucky.getString("gametype",null);
        Contestid = getIntent().getStringExtra("Contestid");



        if (Count.equalsIgnoreCase("0")){

            Url = decrypt(good.key, Url);

//            webView .loadUrl(Url+"?userid="+Loginid+"&contestid="+Contestid+"&gameid="+gameid);

            webView .loadUrl(Url+"?userid="+decrypt(good.key, Loginid)+"&contestid="+decrypt(good.key, Contestid)+"&gameid="+decrypt(good.key, gameid));



            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("Count","10");
            edit.apply();
        }else {
            Exits.setContentView(R.layout.quizexit);
            Exits.setCancelable(false);
            Button nobtn = (Button)Exits.findViewById(R.id.exit);
            nobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }


                    if (From.equalsIgnoreCase("Contest")) {
                        Intent intent = new Intent(TextinActivity.this, MyContestActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(TextinActivity.this, Main2Activity.class);
                        intent.putExtra("goto", "Result");
                        intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                        startActivity(intent);
                    }
                    Exits.dismiss();
                }
            });
            Exits.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
            Exits.show();
        }





    }



    @Override
    public void onBackPressed() {
        Popup.setContentView(R.layout.othergamelayout);
        Popup.setCancelable(false);

        Button okbtn = (Button)Popup.findViewById(R.id.okyes);
        Button nobtn = (Button)Popup.findViewById(R.id.closePopup);
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

                if (From.equalsIgnoreCase("Contest")) {
                    Intent intent = new Intent(TextinActivity.this, MyContestActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(TextinActivity.this, Main2Activity.class);
                    intent.putExtra("goto", "Result");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                }
                Popup.dismiss();
            }
        });

        Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        Popup.show();



    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();

        bannedapps = new bannedapp(TextinActivity.this);
        bannedapps.bannedbig();

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();

        bannedapps = new bannedapp(TextinActivity.this);
        bannedapps.bannedbig();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
