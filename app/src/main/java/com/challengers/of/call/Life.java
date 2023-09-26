package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class Life extends AppCompatActivity {
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    CountDownTimer timer;


    MyApplication application = new MyApplication();
    String from, Life = "";
    private static String REFERAL_CODE = "";
    String TotalwalletObject;

    public EditText etamount;

    public String Response, custID, Wallet, TXNID, BANKTXNID, ORDERID, TXNAMOUNT, STATUS, TXNTYPE, GATEWAYNAME, RESPMSG, RESPCODE, CHECKSUMHASH, CURRENCY, ettamount;
    public String BANKNAME, MID, PAYMENTMODE, REFUNDAMT, TXNDATE;


    public TextView txtwallet, txtlife;



    public String object, money;
    public Dialog epicDialog, fiftyfiftyss, addmoney, unepicDialog, progressbar; //Popup Dialog Box
    public TextView tvlifepupup;
    public ImageView closepopupbtn;
    public Button popupyesbtn;
    String firstobject, twoobject, threeobject, fourobject, fiveobject;
    String firstrupess, tworupess, threerupess, fourrupess, fiverupess;
    public ImageView imageView;
    public TextView referralcode;/////////////////////////////////////////////////////////////////////////////////

    public String GameName;



    public Animation myAnim;

    private Vibrator vibrator;



    public String Totallife, Totalwallet;


    public static Context context;

    private ljsflsj good;
    private static addmoneypopup addpopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        referralcode = (TextView) findViewById(R.id.btnREFERALCODE);///////////////////////////////////////////////////////////////////////////////////////////////
        context = getApplicationContext();
//        fiftyfiftyss = new Dialog( this, R.style.PauseDialog);
        addmoney = new Dialog(this, R.style.PauseDialog);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        unepicDialog = new Dialog(this, R.style.PauseDialog);
        progressbar = new Dialog(this);
        TextView tearn = (TextView)findViewById(R.id.txtearn);

        SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
        String data = perper.getString("EarnMore",null);
        data = decrypt(good.key, data);
        tearn.setText(data);


        addpopup = new addmoneypopup(Life.this);
        LinearLayout linearLayoutadd = (LinearLayout) findViewById(R.id.linearlayout);
        linearLayoutadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (com.challengers.of.call.testing.SettingsPreferences.getVibration(context)) {
                    com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
                }
                if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
                    com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
                }

                addpopup.addpopup();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype", null);

        epicDialog = new Dialog(this); // for popup Dialog
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        imageView = (ImageView) findViewById(R.id.ivwallet);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Life.this, Wallet.class);
                intent.putExtra("from", "life");
                startActivity(intent);
            }
        });


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);////////////////////////////////////////////////////////////////////
        String Loginid = prefs.getString("drawable", null);
        String Name = prefs.getString("Name", null);
        String Profilepic = prefs.getString("Profilepic", null);
        custID = prefs.getString("Loginid", null);
        String Customimage = prefs.getString("Imageurl", null);
        Totalwallet = prefs.getString("Totalwallet", null);
        String Totallife = prefs.getString("Totallife", null);
        ImageView ivprofile = findViewById(R.id.ivprofile);

        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);
        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        Loginid = decrypt(good.key, Loginid);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);
        if (Loginid != null) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        } else if (Customimage != null && Customimage != "") {
            l1.setVisibility(View.GONE);
            ivprofile.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } else if (Profilepic != null && Profilepic != "") {
            l1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(Profilepic)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(imageView);
        }


        TotalwalletObject = Totalwallet;


        TextView textView = findViewById(R.id.username);
        Name = decrypt(good.key, Name);
        textView.setText(Name);


        txtwallet = findViewById(R.id.txtwallet);

//        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//        String Totalwallet = prefs.getString("Totalwallet", null);
        Totalwallet = decrypt(good.key, Totalwallet);

        if (Totalwallet != null && Totalwallet != "") {
            txtwallet.setText(Totalwallet);
        } else {
            txtwallet.setText("0");
        }

        REFERAL_CODE = prefs.getString("Sponsorid", null);////////////////////////////////////////////////////////////////////////////////////
        String refferal = decrypt(good.key, REFERAL_CODE);
        referralcode.setText(refferal);////////////////////////////////////////////////////////////////////////////////////////////////////////////

        from = getIntent().getStringExtra("from");


        Button button = findViewById(R.id.refer);/////////////////////////////////////////refer///////////////////////////////////////////////////////////////

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//////////////////////////////////////////refer/////////////////////////////////////////////////////////////////
                if (com.challengers.of.call.testing.SettingsPreferences.getVibration(context)) {
                    com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
                }
                if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
                    com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
                }
//                shareApplication();

                share();//////////////////////////////////////////////////////////////////
                view.startAnimation(myAnim);//////////////////////////////////////////////////////////
            }
        });
    }



    private void share() {////////////////////////////////////////////////////////////////
        String refferal = decrypt(good.key, REFERAL_CODE);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBodyText = "Download Call of Challengers App at http://www.callofchallengers.com and win real money, just by playing simple quizzes and games . Apply my referral code " + refferal + " to get ₹5 instanly in your wallet.\n";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
    }



    @Override
    public void onBackPressed() {

            Intent intent = new Intent(Life.this, Dashboard.class);
            startActivity(intent);
            Dashboard.dashboard.finish();
            finish();

    }
}