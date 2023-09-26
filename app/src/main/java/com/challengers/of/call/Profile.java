package com.challengers.of.call;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Dashboardapidata.TokenRequestJson;
import com.challengers.of.call.DeviceUtils.DeviceUtils;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Logout.LogoutRequestJson;
import com.challengers.of.call.Logout.LogoutResponseJson;
import com.challengers.of.call.Profiledata.ProfileImgRequestJson;
import com.challengers.of.call.Profiledata.ProfileImgResponseJson;
import com.challengers.of.call.Profiledata.ProfileRequestJson;
import com.challengers.of.call.Profiledata.ProfileResponseJson;
import com.challengers.of.call.Profiledata.ProfiledataResponseJson;
import com.challengers.of.call.Profiledata.WithdrawFiRequestJson;
import com.challengers.of.call.Profiledata.WithdrawFiResponseJson;
import com.challengers.of.call.Profiledata.WithdrawFinalRequestJson;
import com.challengers.of.call.Profiledata.WithdrawFinalResponseJson;
import com.challengers.of.call.Profiledata.WithdrawRequestJson;
import com.challengers.of.call.Profiledata.WithdrawResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
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
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Profile extends AppCompatActivity {
    private static bannedapp bannedapps;
    String Username, Mobile, Password, OTP, Responsemobile, Sponsorid, TotalWallet;
    public String Type, RequestGuid, OrderId, Statuss, StatusCode, StatusMessage, Message, Metadata;
    public String Cashwallet, Winningamount, Totalpoint, winning, Rewards, Winningss;
    private static addmoneypopup addpopup;
    private TextView totalbalence, txtcashwallet, txtwinningwallet, txtReward;
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;
    public static Activity profile;
    JSONArray resultArray;
    String encoded;
    Uri picUri;
    byte[] CDRIVES;
    String sumiturl;
    LinearLayout paidlayout;

    ImageView imageView, image, ivupload;
    JSONObject jasonObject;
    private GoogleApiClient mGoogleApiClient;
    JSONArray jsonArray = null;
    String Totalcontest = "0";
    public ImageView closepopupbtn;
    String displayText = "";
    public Button btn;
    String createdchallenge, joinedchallenge, Totalpayedchallenge, losechallenge, wonchallenge;
    int m;
    public String Loginid, Wallets, p, Emailid, orderID, confMobile;
    public String GameName;
    public String Amount;
    public LinearLayout linearadd;
    String DataParseUrl = "http://callofchallengers.com/coc/cocwithdraw.php";


    int aa = 100000;
    public Button btnobjwithdraw;
    //    AsyncCallWS task;
    public String Rupees, ReciveMoney;
    public LinearLayout linearwithdraw;
    String resTxt = null;
//    AsyncCallWSupload taskupload;


    public String Response, custID, Wallet;

    public Dialog epicDialog, unepicDialog, progressbar, withdraw, information, banned;
    public EditText etobjamount, etobjmobile, etobjmobile2;
    public TextView tvwinn;
    String CheckToast = "yes";
    boolean available = false;
    String Count;

    public static Context context;
    public String Status, StatusTwo;

    private ljsflsj good;

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    private static String REFERAL_CODE = "";

    public Animation myAnim;

    public TextView tvviewdata;
    private Vibrator vibrator;


    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Profile.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walletlayouttwo);
        context = getApplicationContext();
        totalbalence = (TextView) findViewById(R.id.tatalbalence);
        txtcashwallet = (TextView) findViewById(R.id.txtcashwallet);
        txtwinningwallet = (TextView) findViewById(R.id.txtwinningwallet);
        txtReward = (TextView) findViewById(R.id.rewardstv);
        bannedapps = new bannedapp(Profile.this);
        bannedapps.bannedbig();
        LinearLayout referss = (LinearLayout) findViewById(R.id.refers);
        referss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);////////////////////////////////////////////////////////////////////
                REFERAL_CODE = prefs.getString("Sponsorid", null);
                share();
            }
        });

        addpopup = new addmoneypopup(Profile.this);

        SharedPreferences sharedPreferences = getSharedPreferences("coding", Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype", null);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        progressbar = new Dialog(this);
        epicDialog = new Dialog(this, R.style.PauseDialog); // for popup Dialog
        unepicDialog = new Dialog(this, R.style.PauseDialog);
        withdraw = new Dialog(this, R.style.PauseDialog);
        information = new Dialog(this, R.style.PauseDialog);

        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        tvviewdata = (TextView) findViewById(R.id.history);
        tvviewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent = new Intent(Profile.this, wallethistory.class);
//                intent.putExtra("from",From);
                startActivity(intent);
                finish();
            }
        });


        paidlayout = findViewById(R.id.paid);
        // imageView
        imageView = findViewById(R.id.image);
        // imageView

        // imageload
        ivupload = findViewById(R.id.imageupload);
        // imageload

        // imageView setVisibility
        imageView.setVisibility(View.VISIBLE);
        // imageView setVisibility
        banned = new Dialog(this, R.style.PauseDialog);
        profile = this;

        Totalcontest = getIntent().getStringExtra("Totalcontest");
        Main2Activity.Totalcontest = Totalcontest;

        ImageView ivlogout = findViewById(R.id.ivlogout);


        ImageView ivchangepassword = findViewById(R.id.ivchangepassword);
        ivchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Changepassword.class);
                intent.putExtra("Totalcontest", Totalcontest);
                startActivity(intent);
            }
        });
        ImageView ivupdateprofile = findViewById(R.id.ivprofile);
        ivupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent = new Intent(Profile.this, Updateprofile.class);
                intent.putExtra("Totalcontest", Totalcontest);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                startActivity(intent);
            }
        });


        ivlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
//                try {
////                    File dir = context.getCacheDir();
////                    deleteDir(dir);
////                } catch (Exception e) { e.printStackTrace();}
                progressbar.setContentView(R.layout.progresbarlayout);
                progressbar.setCancelable(false);
                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
                        20, 60, ContextCompat.getColor(Profile.this, R.color.white));
                loader.setAnimDuration(3000);

                rotatingCircularDotsLoader.addView(loader);

                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                progressbar.show();

                Logout();




            }
        });

//        task = new AsyncCallWS();
//        task.execute();


        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
                20, 60, ContextCompat.getColor(Profile.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();


        FirstDataAPI();

        ImageView ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Profile.this, Dashboard.class);
                intent.putExtra("goto", "challenger");
                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                startActivity(intent);
                finish();
                Dashboard.dashboard.finish();
            }
        });
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Emailid = prefs.getString("Mobile", null);
        String Name = prefs.getString("Name", null);
        custID = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);
        TextView textView = findViewById(R.id.username);
        String na = decrypt(good.key, Name);
        textView.setText(na);
        image = findViewById(R.id.image2);
        String Imagename = prefs.getString("drawable", null);
        Imagename = decrypt(good.key, Imagename);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);

        linearadd = (LinearLayout) findViewById(R.id.addmoney);
        linearadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

//                popupdata();
                addpopup.addpopup();
            }
        });

        linearwithdraw = (LinearLayout) findViewById(R.id.withdrawin);
        linearwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
                StatusTwo = perper.getString("Status", null);
                StatusTwo = decrypt(good.key, StatusTwo);
                Log.d("walletwalletssss","Profile= "+ StatusTwo);
                if (StatusTwo.equalsIgnoreCase("0")) {
                    banned.setContentView(R.layout.bannduserlayout);

                    banned.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    banned.show();
                } else {
                    withdrow();
                }

            }
        });
        if (Imagename != null) {
            image.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            int imageId = getResourseId(this, Imagename, "drawable", getPackageName());
            imageView.setImageResource(imageId);
        } else if (Customimage != null && Customimage != "") {

            image.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(image);
            imageView.setVisibility(View.GONE);
        } else if (Profilepic != null && Profilepic != "") {
            Log.i("xsumit", Profilepic);
            image.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(Profilepic)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(image);

            imageView.setVisibility(View.GONE);
        }
        //imageView.setVisibility(View.GONE);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
            }
        });

        // imageLoad icon click then code is work
        ivupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
            }
        });
        // imageLoad icon click then code is work

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
            }
        });


        FirstAPI();

//        AsyncCallWSsendotp task = new AsyncCallWSsendotp();
//        task.execute();

    }


    private void share() {////////////////////////////////////////////////////////////////
        String refer = decrypt(good.key, REFERAL_CODE);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBodyText = "Download COC at http://www.callofchallengers.com sponsor and win money for playing the ultimate quiz game . Apply my referral code " + refer + " to get extra lives.\n";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
    }

    private void Logout() {


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final LogoutRequestJson request = new LogoutRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.getlogout(request).enqueue(new Callback<LogoutResponseJson>() {
            @Override
            public void onResponse(Call<LogoutResponseJson> call, Response<LogoutResponseJson> response) {
                if (response.isSuccessful()) {

                    String message = response.body().message;


                    if (message.equalsIgnoreCase("success")){
                        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                        lucky.edit().clear().commit();
                        SharedPreferences settings = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        settings.edit().clear().commit();
                        SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
                        pref2.edit().clear().commit();
                        Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                        deleteCache(Profile.this);
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        finish();


                    }else {
                        Toast.makeText(Profile.this, "Please try after sometime", Toast.LENGTH_LONG).show();
                    }



                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Profile.this, "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<LogoutResponseJson> call, Throwable t) {
                t.printStackTrace();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });




    }
    private void withdrow() {
        withdraw.setContentView(R.layout.withdrawlayout);
        withdraw.setCancelable(false);


        tvwinn = (TextView) withdraw.findViewById(R.id.winningamount);
//        String winn = decrypt(good.key, Winningss);
        tvwinn.setText(Winningss);
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);


        etobjamount = (EditText) withdraw.findViewById(R.id.etamountnew);


        etobjamount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }
                    Amount = etobjamount.getText().toString().trim();
                    Amount = encrypt(good.key, good.initVector, Amount);

                    ////////// amount enter time
//                    AsyncCallWSwith task = new AsyncCallWSwith();
//                    task.execute();

                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
                            20, 60, ContextCompat.getColor(Profile.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();

                    AmountEnterTime();
                }
            }
        });
        etobjmobile = (EditText) withdraw.findViewById(R.id.etmobile);
        Mobile = etobjmobile.getText().toString().trim();


        etobjmobile2 = (EditText) withdraw.findViewById(R.id.reentermobile);

        confMobile = etobjmobile2.getText().toString().trim();


        btnobjwithdraw = (Button) withdraw.findViewById(R.id.btnwithdraw);
        btnobjwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Rupeess = decrypt(good.key, Rupees);
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                if (available) {
                    if (etobjamount.length() == 0) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Please Enter Amount");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    } else if (Integer.valueOf(etobjamount.getText().toString()) < Integer.valueOf(Rupeess)) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Required Minimum balance is ₹" + Rupeess + " for withdraw");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    } else if (etobjmobile.length() == 0) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Please Enter your Mobile");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Please Enter your Mobile", Toast.LENGTH_SHORT).show();
                    } else if (etobjmobile.length() != 10) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Please Enter valid Mobile Number");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Please Enter valid Mobile Number", Toast.LENGTH_SHORT).show();
                    } else if (!etobjmobile.getText().toString().equals(etobjmobile2.getText().toString())) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Mobile Number Mismatched");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Mobile Number Mismatched", Toast.LENGTH_SHORT).show();
                    } else {

                        withdraw.dismiss();
                        Mobile = etobjmobile.getText().toString().trim();
                        confMobile = etobjmobile2.getText().toString().trim();
                        Amount = etobjamount.getText().toString().trim();

                        confMobile = encrypt(good.key, good.initVector, confMobile);
                        Mobile = encrypt(good.key, good.initVector, Mobile);
                        Amount = encrypt(good.key, good.initVector, Amount);
//                        AsyncCallWSwithBTN task = new AsyncCallWSwithBTN();
//                        task.execute();
//

                        progressbar.setContentView(R.layout.progresbarlayout);
                        progressbar.setCancelable(false);
                        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
                                20, 60, ContextCompat.getColor(Profile.this, R.color.white));
                        loader.setAnimDuration(3000);

                        rotatingCircularDotsLoader.addView(loader);

                        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                        progressbar.show();

                        WithdrawButton();
//                        SendDataToServer(Emailid, Loginid, Amount, Mobile);
//                        btnobjwithdraw.setEnabled(false);


                        v.startAnimation(myAnim);
                        Showinformationpopup();

                    }
                } else {
                    if (CheckToast.equalsIgnoreCase("NotExist")) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Required Minimum balance is ₹" + Rupeess + " for withdraw");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Required Minimum balance is ₹" + Rupeess + " for withdraw", Toast.LENGTH_SHORT).show();


                    } else if (CheckToast.equalsIgnoreCase("NotAmount")) {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("You have not enough balance to withdraw");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "You have not enough balance to withdraw", Toast.LENGTH_SHORT).show();

                    } else {
                        TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                        text.setText("Please fill correct details");
                        text.setVisibility(View.VISIBLE);
//                        Toast.makeText(Profile.this, "Please fill correct details", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });


        closepopupbtn = (ImageView) withdraw.findViewById(R.id.closePopup);// popup Close button

        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                withdraw.dismiss();
            }
        });

        withdraw.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        withdraw.show();

    }


    private void Showinformationpopup() {
        information.setContentView(R.layout.notelayout);
        information.setCancelable(false);


        Button OKpopupbtn = (Button) information.findViewById(R.id.closePopup);
        OKpopupbtn.setText("CONFIRM");
        OKpopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }


//                SendDataToServer(Emailid, Loginid, Amount, Mobile);
                information.dismiss();


                WithDrawFinal();
            }
        });
        ImageView closeiv = (ImageView) information.findViewById(R.id.close);
        closeiv.setVisibility(View.VISIBLE);
        closeiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information.dismiss();
            }
        });


        information.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        information.show();
    }

//    private void WithDrawFirstApi() {
//        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//        String Loginid = prefs.getString("Loginid", null);
//        String Password = prefs.getString("Password", null);
//        String Usernames = prefs.getString("Name", null);
//
//        final WithdrawFiRequestJson request = new WithdrawFiRequestJson();
//        request.setLogin(decrypt(good.key, Loginid));
//        request.setAmount(decrypt(good.key, Amount));
//        request.setMobile(Mobile);
//        request.setEmail(Emailid);
//
//        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
//        service.withdrawfi(request).enqueue(new Callback<WithdrawFiResponseJson>() {
//            @Override
//            public void onResponse(Call<WithdrawFiResponseJson> call, Response<WithdrawFiResponseJson> response) {
//                if (response.isSuccessful()) {
//
//
//                    Type = response.body().type;
//                    RequestGuid = response.body().requestGuid;
//                    OrderId = response.body().orderId;
//                    Statuss = response.body().status;
//                    StatusCode = response.body().statusCode;
//                    StatusMessage = response.body().statusMessage;
//
//
//
//                    if (StatusMessage.equalsIgnoreCase("ACCEPTED")) {
//
//                        progressbar.setContentView(R.layout.progresbarlayout);
//                        progressbar.setCancelable(false);
//                        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//                        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                                20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//                        loader.setAnimDuration(3000);
//
//                        rotatingCircularDotsLoader.addView(loader);
//
//                        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//                        progressbar.show();
//
//
//                        WithDrawFinal();
//
//
//                    } else {
//
//                        Toast.makeText(Profile.this, StatusMessage, Toast.LENGTH_LONG).show();
//                    }
//
//
//
//
//                } else {
//                    switch (response.code()) {
//                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
//                            break;
//                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
//                            break;
//                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
//                            break;
//                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
//                            break;
//                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
//                            break;
//
//                    }
//
//                }
//                progressbar.dismiss();
//
//            }
//
//            @Override
//            public void onFailure(Call<WithdrawFiResponseJson> call, Throwable t) {
//                t.printStackTrace();
//
//
//                Log.e("System error:", t.getLocalizedMessage());
//
//            }
//        });
//    }

    /////// final time run api
//    private void SendDataToServer(final String emailid, final String loginid, final String amount, final String mobile) {
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//            @Override
//            protected String doInBackground(String... params) {
//                String QuicEmail = emailid;
//                String QuicLoginid = loginid;
//                String QuicAmount = amount;
//                String QuicMobile = mobile;
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//
//                nameValuePairs.add(new BasicNameValuePair("Email", QuicEmail));
//                nameValuePairs.add(new BasicNameValuePair("Login", QuicLoginid));
//                nameValuePairs.add(new BasicNameValuePair("Amount", QuicAmount));
//                nameValuePairs.add(new BasicNameValuePair("Mobile", QuicMobile));
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//
//                    HttpPost httpPost = new HttpPost(DataParseUrl);
//
//                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                    HttpResponse response = httpClient.execute(httpPost);
//
//                    HttpEntity entity = response.getEntity();
//
//                    resTxt = EntityUtils.toString(entity);
//
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//
//                }
//                return resTxt;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                if (result != null) {
//                    try {
//
//                        JSONObject resulttwo = new JSONObject(result);
//
//                        JSONArray array = resulttwo.getJSONArray("Result");
//
//                        for (int i = 0; i < array.length(); i++) {
//
//                            JSONObject jsonrowdata = array.getJSONObject(i);
//
//
//                            Type = jsonrowdata.getString("type");
//                            RequestGuid = jsonrowdata.getString("requestGuid");
//                            OrderId = jsonrowdata.getString("orderId");
//                            Statuss = jsonrowdata.getString("status");
//                            StatusCode = jsonrowdata.getString("statusCode");
//                            StatusMessage = jsonrowdata.getString("statusMessage");
//
//
//                        }
//                        if (StatusMessage.equalsIgnoreCase("ACCEPTED")) {
////
////                            btnobjwithdraw.setEnabled(false);
////                            AsyncCallWSsendotpbtn task = new AsyncCallWSsendotpbtn();
////                            task.execute();
//                            progressbar.setContentView(R.layout.progresbarlayout);
//                            progressbar.setCancelable(false);
//                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//                            loader.setAnimDuration(3000);
//
//                            rotatingCircularDotsLoader.addView(loader);
//
//                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//                            progressbar.show();
//
//
//                            WithDrawFinal();
//
//
//                        } else {
//
//                            Toast.makeText(Profile.this, StatusMessage, Toast.LENGTH_LONG).show();
//                        }
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//
//            @Override
//            protected void onPreExecute() {
//
//                Mobile = etobjmobile.getText().toString().trim();
//                confMobile = etobjmobile2.getText().toString().trim();
//                Amount = etobjamount.getText().toString().trim();
//            }
//        }
//
//        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//        sendPostReqAsyncTask.execute(emailid, loginid, amount, mobile);
//    }

    private void WithDrawFinal() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final WithdrawFinalRequestJson request = new WithdrawFinalRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setAmount(decrypt(good.key, Amount));
        request.setMobile(decrypt(good.key, Mobile));



        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profilefinal(request).enqueue(new Callback<WithdrawFinalResponseJson>() {
            @Override
            public void onResponse(Call<WithdrawFinalResponseJson> call, Response<WithdrawFinalResponseJson> response) {
                if (response.isSuccessful()) {

                    String Message = response.body().Message;
                    String TotalWallet = response.body().TotalWallet;
                    String WinWallet = response.body().WinWallet;
                    if (Message.equalsIgnoreCase("fail")){

                        Toast.makeText(Profile.this, "Withdraw Fail", Toast.LENGTH_LONG).show();

                    }else {
                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Totalwallet", encrypt(good.key, good.initVector, TotalWallet));



                        editor.apply();
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();

                        editor1.putString("Totalwallet", encrypt(good.key, good.initVector, TotalWallet));
                        editor1.apply();
                        txtwinningwallet.setText(WinWallet);
                        totalbalence.setText(TotalWallet);
                        Toast.makeText(Profile.this, "Congratulations, your withdrawl amount will be credited into your paytm wallet within 1 hour.", Toast.LENGTH_LONG).show();

                    }

//                    TotalWallet = response.body().Wallet;




//                    TextView text = (TextView) withdraw.findViewById(R.id.texttext);
//                    text.setText("Money added to your paytm account successfully");
//                    text.setVisibility(View.VISIBLE);


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Profile.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<WithdrawFinalResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

    //    public class AsyncCallWSsendotpbtn extends AsyncTask<String, Void, Void> {
//        String From = "", displayText = "", Verify;
//        String Username, Mobile, Password, OTP, Responsemobile, Sponsorid, TotalWallet;
//        TextView txtwinningwallet, totalbalence;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Withdraw(Mobile, Amount, Loginid, OrderId, "Withdraw");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        Wallet = jsonrowdata.getString("TotalWallet");
//                        TotalWallet = jsonrowdata.getString("Wallet");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            TextView text = (TextView) withdraw.findViewById(R.id.texttext);
//            text.setVisibility(View.GONE);
//            progressbar.dismiss();
//            if (displayText != null && !displayText.isEmpty()) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
//                    Toast.makeText(Profile.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray.length() > 0) {
//
//                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putString("Totalwallet", Wallet);
//
//
//                    editor.apply();
//                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//
//                    editor1.putString("Totalwallet", Wallet);
//                    editor1.apply();
//                    txtwinningwallet.setText(TotalWallet);
//                    totalbalence.setText(Wallet);
////                    TextView text = (TextView) withdraw.findViewById(R.id.texttext);
//                    text.setText("Money added to your paytm account successfully");
//                    text.setVisibility(View.VISIBLE);
////                    Toast.makeText(Profile.this, "Money added to your paytm account successfully", Toast.LENGTH_LONG).show();
//
////
////                    Intent intent = new Intent(Profile.this, Wallet.class);
////                    intent.putExtra("from", "dashboard");
////                    startActivity(intent);
////                    finish();
//
//
//                }
//            } else {
//                Toast.makeText(Profile.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            Mobile = etobjmobile.getText().toString().trim();
//            confMobile = etobjmobile2.getText().toString().trim();
////            Amount = etobjamount.getText().toString().trim();
//            txtwinningwallet = findViewById(R.id.txtwinningwallet);
//            totalbalence = findViewById(R.id.tatalbalence);
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//
//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//        }
//    }
    private void AmountEnterTime() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final WithdrawRequestJson request = new WithdrawRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setAmount(decrypt(good.key, Amount));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profilewithamount(request).enqueue(new Callback<WithdrawResponseJson>() {
            @Override
            public void onResponse(Call<WithdrawResponseJson> call, Response<WithdrawResponseJson> response) {
                if (response.isSuccessful()) {

                    Count = response.body().count;
                    Rupees = response.body().rupees;

                    Count = encrypt(good.key, good.initVector, Count);
                    Rupees = encrypt(good.key, good.initVector, Rupees);

                    TextView text = (TextView) withdraw.findViewById(R.id.texttext);
                    text.setVisibility(View.GONE);
                    Count = decrypt(good.key, Count);
                    String Rup = decrypt(good.key, Rupees);
                    if (Count.equalsIgnoreCase("NotExist")) {
                        CheckToast = "NotExist";
                        available = false;

                        text.setText("Required Minimum balance is ₹" + Rup + " for withdraw");
                        text.setVisibility(View.VISIBLE);

                    } else if (Count.equalsIgnoreCase("NotAmount")) {
                        CheckToast = "NotAmount";
                        available = false;

                        text.setText("You have not enough balance to withdraw");
                        text.setVisibility(View.VISIBLE);

                    } else {
                        available = true;
                    }
                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Profile.this, "Something error please try again", Toast.LENGTH_SHORT).show();
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<WithdrawResponseJson> call, Throwable t) {
                t.printStackTrace();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

    ////////// amount enter time
//    public class AsyncCallWSwith extends AsyncTask<String, Void, Void> {
////        String displayText = "";
////        String Username, Count;
////
////
////        private KProgressHUD hud;
////
////
////        @Override
////        protected Void doInBackground(String... params) {
////            displayText = WebService.CheckAmount(Amount, Loginid, "CheckAmount");
////            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
////                try {
////                    jasonObject = new JSONObject(displayText);
////                    jsonArray = jasonObject.getJSONArray("Response");
////                    for (int i = 0; i < jsonArray.length(); i++) {
////                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
////                        Count = jsonrowdata.getString("Count");
////                        Rupees = jsonrowdata.getString("Rupees");
////

////
////                        Count = encrypt(good.key, good.initVector, Count);
////                        Rupees = encrypt(good.key, good.initVector, Rupees);
////

////
////
////
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////            return null;
////        }
////
////        @Override
////        protected void onPostExecute(Void result) {
////            progressbar.dismiss();
////            TextView text = (TextView) withdraw.findViewById(R.id.texttext);
////            text.setVisibility(View.GONE);
////            if (displayText.equalsIgnoreCase("connection fault")) {
////                Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
////            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
////                Toast.makeText(Profile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
////            } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
////                Toast.makeText(Profile.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
////            } else if (jsonArray.length() > 0) {
////
////                Count = decrypt(good.key, Count);
////                String Rup = decrypt(good.key, Rupees);
////                if (Count.equalsIgnoreCase("NotExist")) {
////                    CheckToast = "NotExist";
////                    available = false;
////
//////                    TextView text = (TextView) withdraw.findViewById(R.id.texttext);
////                    text.setText("Required Minimum balance is ₹" + Rup + " for withdraw");
////                    text.setVisibility(View.VISIBLE);
//////                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Required Minimum balance is ₹ " + Rupees + " for withdraw", Snackbar.LENGTH_LONG);
//////                    View view1 = snack.getView();
//////                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//////                    params.gravity = Gravity.BOTTOM;
//////                    view1.setLayoutParams(params);
//////                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
//////                    snack.show();
//////                    layoutunavailable.setVisibility(View.VISIBLE);
//////                    layoutunavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
//////                    layoutavailable.setVisibility(View.GONE);
////                } else if (Count.equalsIgnoreCase("NotAmount")) {
////                    CheckToast = "NotAmount";
////                    available = false;
////
////                    text.setText("You have not enough balance to withdraw");
////                    text.setVisibility(View.VISIBLE);
//////                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "You have not enough balance to withdraw", Snackbar.LENGTH_LONG);
//////                    View view1 = snack.getView();
//////                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//////                    params.gravity = Gravity.BOTTOM;
//////                    view1.setLayoutParams(params);
//////                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
//////                    snack.show();
////                } else {
////                    available = true;
//////                    TextView text = (TextView) withdraw.findViewById(R.id.texttext);
//////                    text.setText("This amount is valid");
//////                    text.setVisibility(View.VISIBLE);
//////                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "This amount is valid", Snackbar.LENGTH_LONG);
//////                    View view1 = snack.getView();
//////                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//////                    params.gravity = Gravity.BOTTOM;
//////                    view1.setLayoutParams(params);
//////                    view1.setBackgroundColor(Color.parseColor("#00902E"));
//////                    snack.show();
////
////                }
////            } else {
////                Toast.makeText(Profile.this, "Please try again.", Toast.LENGTH_LONG).show();
////            }
////        }
////
////        @Override
////        protected void onPreExecute() {
////
////            Mobile = etobjmobile.getText().toString().trim();
////            confMobile = etobjmobile2.getText().toString().trim();
//////            Amount = etobjamount.getText().toString().trim();
////
////
////            progressbar.setContentView(R.layout.progresbarlayout);
////            progressbar.setCancelable(false);
////            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
////
////            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
////                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
////            loader.setAnimDuration(3000);
////
////            rotatingCircularDotsLoader.addView(loader);
////
////            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
////            progressbar.show();
////        }
////
////        @Override
////        protected void onProgressUpdate(Void... values) {
////
////        }
////    }


    private void WithdrawButton() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final WithdrawRequestJson request = new WithdrawRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setAmount(decrypt(good.key, Amount));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profilewithbtn(request).enqueue(new Callback<WithdrawResponseJson>() {
            @Override
            public void onResponse(Call<WithdrawResponseJson> call, Response<WithdrawResponseJson> response) {
                if (response.isSuccessful()) {

                    Count = response.body().count;
                    Rupees = response.body().rupees;
                    ReciveMoney = response.body().reciveMoney;

                    Count = encrypt(good.key, good.initVector, Count);
                    Rupees = encrypt(good.key, good.initVector, Rupees);
                    ReciveMoney = encrypt(good.key, good.initVector, ReciveMoney);

                    TextView infoText = (TextView) information.findViewById(R.id.textt);

                    String recive = decrypt(good.key, ReciveMoney);
                    infoText.setText("Withdrawable Amount ₹" + recive );
//

                    Count = decrypt(good.key, Count);
                    if (Count.equalsIgnoreCase("NotExist")) {
                        CheckToast = "NotExist";
                        available = false;


                    } else if (Count.equalsIgnoreCase("NotAmount")) {
                        CheckToast = "NotAmount";
                        available = false;

                    } else {
                        available = true;


                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Profile.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<WithdrawResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }
///////////// final withdraw button click
//    public class AsyncCallWSwithBTN extends AsyncTask<String, Void, Void> {
//        String displayText = "";
//        String Username, Count;
//
//
//        private KProgressHUD hud;
//
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.CheckAmount(Amount, Loginid, "CheckAmount");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");
//                        Rupees = jsonrowdata.getString("Rupees");
//                        ReciveMoney = jsonrowdata.getString("ReciveMoney");
//

//
//                        Count = encrypt(good.key, good.initVector, Count);
//                        Rupees = encrypt(good.key, good.initVector, Rupees);
//                        ReciveMoney = encrypt(good.key, good.initVector, ReciveMoney);
//

//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            TextView text = (TextView) withdraw.findViewById(R.id.texttext);
//            text.setVisibility(View.GONE);
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Profile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
//                Toast.makeText(Profile.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                TextView infoText = (TextView)information.findViewById(R.id.textt);
//
//                String recive = decrypt(good.key, ReciveMoney);
//                infoText.setText("Withdrawable Amount ₹"+recive+" Remain in Winning Wallet");
////
//
//                Count = decrypt(good.key, Count);
//                if (Count.equalsIgnoreCase("NotExist")) {
//                    CheckToast = "NotExist";
//                    available = false;
//
//
//                } else if (Count.equalsIgnoreCase("NotAmount")) {
//                    CheckToast = "NotAmount";
//                    available = false;
//
//                } else {
//                    available = true;
//
////                    SendDataToServer(Emailid, Loginid, Amount, Mobile);
////                    btnobjwithdraw.setEnabled(false);
//                }
//            } else {
//                Toast.makeText(Profile.this, "Please try again.", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
////            Amount = etobjamount.getText().toString().trim();
//
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//        }
//    }


    @Override
    public void onBackPressed() {
        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }


        Intent intent = new Intent(Profile.this, Dashboard.class);
        intent.putExtra("goto", "challenger");
        intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);
        startActivity(intent);
        Dashboard.dashboard.finish();
        finish();
    }

    public Intent getPickImageChooserIntent() {

        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);

        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);

        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }
//    getPickImageChooserIntent()

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalFilesDir("");
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (cameraPermission && readExternalFile) {
                        // write your logic here
                    } else {
                        Snackbar.make(Profile.this.findViewById(android.R.id.content),
                                "Please Grant Permissions to upload profile photo",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                    @Override
                                    public void onClick(View v) {
                                        requestPermissions(
                                                new String[]{Manifest.permission
                                                        .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                                1);
                                    }
                                }).show();
                    }
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ImageView image = findViewById(R.id.image2);
            imageView.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);

            if (requestCode == IMAGE_RESULT) {
                String filePath = getImageFilePath(data);
                if (filePath != null) {
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                    image.setImageBitmap(selectedImage);
                    Glide.with(getApplicationContext()).load(stream.toByteArray())
                            .crossFade()
                            .placeholder(R.drawable.logo)
                            .error(R.drawable.logo)
                            .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(image);
                    Glide.get(getApplicationContext()).clearMemory();
                    clearGlideDiskCache();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
                    CDRIVES = byteArrayOutputStream.toByteArray();
                    encoded = Base64.encodeToString(CDRIVES, Base64.DEFAULT);
//                    resultArray = new JSONArray();
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("SID", encoded);
//                        resultArray.put(object);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    taskupload = new AsyncCallWSupload();
//                    taskupload.execute();

                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
                            20, 60, ContextCompat.getColor(Profile.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();


                    ProfileAPI();
                }
            }
        }
    }


    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;

        if (isCamera) return getCaptureImageOutputUri().getPath();
        else return getPathFromURI(data.getData());

    }

    public String getImageFilePath(Intent data) {
        return getImageFromFilePath(data);
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("pic_uri", picUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        picUri = savedInstanceState.getParcelable("pic_uri");
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private void FirstDataAPI() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final ProfileRequestJson request = new ProfileRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profiledashboard(request).enqueue(new Callback<ProfiledataResponseJson>() {
            @Override
            public void onResponse(Call<ProfiledataResponseJson> call, Response<ProfiledataResponseJson> response) {
                if (response.isSuccessful()) {

                    for (int z = 0; z < response.body().data.size(); z++) {
                        createdchallenge = response.body().data.get(z).createdchallenge;
                        joinedchallenge = response.body().data.get(z).joinedchallenge;
                        Totalpayedchallenge = response.body().data.get(z).Totalpayedchallenge;
                        wonchallenge = response.body().data.get(z).wonchallenge;
                        losechallenge = response.body().data.get(z).losechallenge;
                        winning = response.body().data.get(z).winning;
                        Status = response.body().data.get(z).status;





                        createdchallenge = encrypt(good.key, good.initVector, createdchallenge);
                        joinedchallenge = encrypt(good.key, good.initVector, joinedchallenge);
                        Totalpayedchallenge = encrypt(good.key, good.initVector, Totalpayedchallenge);
                        wonchallenge = encrypt(good.key, good.initVector, wonchallenge);
                        losechallenge = encrypt(good.key, good.initVector, losechallenge);
                        winning = encrypt(good.key, good.initVector, winning);

                        Status = encrypt(good.key, good.initVector, Status);

                    }


                    TextView txtcontestcreated, txtchallengejoin,  txtchallengewonnew, txtplayerdcontestnew, txtchallengewon, txtplayerdcontest;
                    StatusTwo = Status;
                    SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = perper.edit();
                    edit.putString("Status", Status);
                    edit.apply();
                    txtcontestcreated = findViewById(R.id.txtcontestcreated);
                    txtchallengejoin = findViewById(R.id.txtchallengejoin);
                    txtchallengewon = findViewById(R.id.txtchallengewon);
                    txtchallengewonnew = findViewById(R.id.txtchallengewonnew);
                    txtplayerdcontest = findViewById(R.id.txtplayerdcontest);
                    txtplayerdcontestnew = findViewById(R.id.txtplayerdcontestnew);


                    createdchallenge = decrypt(good.key, createdchallenge);
                    joinedchallenge = decrypt(good.key, joinedchallenge);
                    Totalpayedchallenge = decrypt(good.key, Totalpayedchallenge);
                    wonchallenge = decrypt(good.key, wonchallenge);
                    losechallenge = decrypt(good.key, losechallenge);
                    String winn = decrypt(good.key, winning);

                    txtcontestcreated.setText(createdchallenge);
                    txtchallengejoin.setText(joinedchallenge);
                    txtchallengewon.setText(Totalpayedchallenge);
                    txtchallengewonnew.setText(wonchallenge);
                    txtplayerdcontest.setText(losechallenge);
                    txtplayerdcontestnew.setText(winn + "%");


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Profile.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ProfiledataResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }
    ///////////// Starting time run first api
//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//
//        String createdchallenge, TodayContestcreated, joinedchallenge, TodayContestwin, winning, Totalpayedchallenge, losechallenge, TodayContestlose, Contestamount, TodayContestamount, wonchallenge, TodayChallengejoin, Challengewin, TodayChallengewin, Challngeloose, TodayChallngeloose;
//        String Contestwon, Todaycontestwon, Contestplayed, Todaycontestplay;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//
//            displayText = WebService.Selectchallenge(Loginid, GameName, "ProfileDetail");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//
//                        createdchallenge = jsonrowdata.getString("createdchallenge");
//                        joinedchallenge = jsonrowdata.getString("joinedchallenge");
//                        Totalpayedchallenge = jsonrowdata.getString("Totalpayedchallenge");
//                        wonchallenge = jsonrowdata.getString("wonchallenge");
//                        losechallenge = jsonrowdata.getString("losechallenge");
//                        winning = jsonrowdata.getString("winning");
//                        Status = jsonrowdata.getString("status");
//
//

//
//                        createdchallenge = encrypt(good.key, good.initVector, createdchallenge);
//                        joinedchallenge = encrypt(good.key, good.initVector, joinedchallenge);
//                        Totalpayedchallenge = encrypt(good.key, good.initVector, Totalpayedchallenge);
//                        wonchallenge = encrypt(good.key, good.initVector, wonchallenge);
//                        losechallenge = encrypt(good.key, good.initVector, losechallenge);
//                        winning = encrypt(good.key, good.initVector, winning);
//                        Status = encrypt(good.key, good.initVector, Status);
//
//
//

//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Profile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                TextView txtcontestcreated, txtcontestwin, txtchallengejoin, textplayerdcontestnew, txtchallengewin, txtchallengewonnew, txtplayerdcontestnew, txtchallengewon, txttodaychallengewon, txtplayerdcontest, txttodayplayedcontest;
//                StatusTwo = Status;
//                SharedPreferences perper = getSharedPreferences("walletdata", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = perper.edit();
//                edit.putString("Status",Status);
//                edit.apply();
//                txtcontestcreated = findViewById(R.id.txtcontestcreated);
//                txtchallengejoin = findViewById(R.id.txtchallengejoin);
//                txtchallengewon = findViewById(R.id.txtchallengewon);
//                txtchallengewonnew = findViewById(R.id.txtchallengewonnew);
//                txtplayerdcontest = findViewById(R.id.txtplayerdcontest);
//                txtplayerdcontestnew = findViewById(R.id.txtplayerdcontestnew);
//
//
//                createdchallenge = decrypt(good.key, createdchallenge);
//                joinedchallenge = decrypt(good.key, joinedchallenge);
//                Totalpayedchallenge = decrypt(good.key, Totalpayedchallenge);
//                wonchallenge = decrypt(good.key, wonchallenge);
//                losechallenge = decrypt(good.key, losechallenge);
//                String winn = decrypt(good.key, winning);
//
//                txtcontestcreated.setText(createdchallenge);
//                txtchallengejoin.setText(joinedchallenge);
//                txtchallengewon.setText(Totalpayedchallenge);
//                txtchallengewonnew.setText(wonchallenge);
//                txtplayerdcontest.setText(losechallenge);
//                txtplayerdcontestnew.setText(winn + "%");
//
//
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(Profile.this, "No detail found", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(Profile.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

    private void ProfileAPI() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        final int random = new Random().nextInt(1000000) + 100000;
        String Loginids = decrypt(good.key, Loginid);
        sumiturl = Loginids + random + ".jpeg";
        final ProfileImgRequestJson request = new ProfileImgRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setImageurl(sumiturl);
        request.setImagebytes(encoded);

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profileimg(request).enqueue(new Callback<ProfileImgResponseJson>() {
            @Override
            public void onResponse(Call<ProfileImgResponseJson> call, Response<ProfileImgResponseJson> response) {
                if (response.isSuccessful()) {

                    Message = response.body().message;


                    if (Message.equalsIgnoreCase("updated")) {
//                        deleteCache(getApplicationContext());
                        Glide.get(getApplicationContext()).clearMemory();
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Profile pic updated successfully", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#00902E"));
                        snack.show();

                        sumiturl = encrypt(good.key, good.initVector, sumiturl);
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Imageurl", sumiturl);
                        editor1.putString("Profilepic", null);
                        editor1.apply();

                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Profilepic", null);

                        editor.apply();


                    }

                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ProfileImgResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }

//    private class AsyncCallWSupload extends AsyncTask<String, Void, Void> {
//        String Msg;
//        private KProgressHUD hud;
//        String Loginid;
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            Loginid = prefs.getString("Loginid", null);
//
//            final int random = new Random().nextInt(1000000) + 100000;
//            String Loginids = decrypt(good.key, Loginid);
//            sumiturl = Loginids + random + ".jpeg";
//


//
////            if (resultArray.length() > 0) {
//            displayText = WebService.Updateprofilepic(Loginid, sumiturl, encoded, "", "Uploadprofile");
////            }
//            if (displayText != "" && displayText != null) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Msg = jsonrowdata.getString("Count");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.print("SumitVerma" + sumiturl);
//            return null;
//
//
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Profile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                if (Msg.equalsIgnoreCase("updated")) {
//                    deleteCache(getApplicationContext());
//                    Glide.get(getApplicationContext()).clearMemory();
//                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Profile pic updated successfully", Snackbar.LENGTH_LONG);
//                    View view1 = snack.getView();
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                    params.gravity = Gravity.BOTTOM;
//                    view1.setLayoutParams(params);
//                    view1.setBackgroundColor(Color.parseColor("#00902E"));
//                    snack.show();
//
//                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//                    editor1.putString("Imageurl", sumiturl);
//                    editor1.putString("Profilepic", null);
//                    editor1.apply();
//
//                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putString("Profilepic", null);
//
//                    editor.apply();
//
//
//                }
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//        }
//
//        @Override
//        protected void onCancelled() {
//            taskupload.cancel(true);
//            super.onCancelled();
//        }
//    }

    void clearGlideDiskCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();
    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(Profile.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(Profile.this,
                        Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (Profile.this, Manifest.permission.CAMERA)) {

                Snackbar.make(Profile.this.findViewById(android.R.id.content),
                        "Please Grant Permissions to upload profile photo",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View v) {
                                requestPermissions(
                                        new String[]{Manifest.permission
                                                .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                        1);
                            }
                        }).show();
            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        1);
            }
        } else {

        }
    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onStart();
    }

    private void FirstAPI() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final ProfileRequestJson request = new ProfileRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.profiledata(request).enqueue(new Callback<ProfileResponseJson>() {
            @Override
            public void onResponse(Call<ProfileResponseJson> call, Response<ProfileResponseJson> response) {
                if (response.isSuccessful()) {

                    Cashwallet = response.body().cashwallet;
                    Winningamount = response.body().winningamount;
                    Totalpoint = response.body().totalpoint;
                    Rewards = response.body().reward;



                    Cashwallet = encrypt(good.key, good.initVector, Cashwallet);
                    Winningamount = encrypt(good.key, good.initVector, Winningamount);
                    Totalpoint = encrypt(good.key, good.initVector, Totalpoint);
                    Rewards = encrypt(good.key, good.initVector, Rewards);


                    String point = decrypt(good.key, Totalpoint);
                    String Cwallet = decrypt(good.key, Cashwallet);
                    String Winn = decrypt(good.key, Winningamount);
                    String Rewa = decrypt(good.key, Rewards);

                    totalbalence.setText(point);
                    txtcashwallet.setText(Cwallet);
                    txtwinningwallet.setText(Winn);
                    Winningss = Winn;
                    winning = Winningamount;
                    txtReward.setText(Rewa);

                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totalwallet", Totalpoint);


                    editor.apply();
                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref2.edit();

                    editor1.putString("Totalwallet", Totalpoint);
                    editor1.apply();


                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Profile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Profile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Profile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Profile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Profile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<ProfileResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

///////////// Starting time run second api
//    public class AsyncCallWSsendotp extends AsyncTask<String, Void, Void> {
//        String From = "", displayText = "";
//
//
//        private KProgressHUD hud;
//
//        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//        String Loginid = prefs.getString("Loginid", null);
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Selectchallenge(Loginid, GameName, "wallet");
//
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Cashwallet = jsonrowdata.getString("Cashwallet");
//                        Winningamount = jsonrowdata.getString("Winningamount");
//                        Totalpoint = jsonrowdata.getString("Totalpoint");
//                        Rewards = jsonrowdata.getString("reward");
//
//
//
//                        Cashwallet = encrypt(good.key, good.initVector, Cashwallet);
//                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
//                        Totalpoint = encrypt(good.key, good.initVector, Totalpoint);
//                        Rewards = encrypt(good.key, good.initVector, Rewards);
//
//

//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
////            hud.dismiss();
//            progressbar.dismiss();
//            if (displayText != null && !displayText.isEmpty()) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(Profile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (jsonArray.length() > 0) {
//                    String point = decrypt(good.key, Totalpoint);
//                    String Cwallet = decrypt(good.key, Cashwallet);
//                    String Winn = decrypt(good.key, Winningamount);
//                    String Rewa = decrypt(good.key, Rewards);
//                    totalbalence.setText(point);
//                    txtcashwallet.setText(Cwallet);
//                    txtwinningwallet.setText(Winn);
//                    winning = Winningamount;
//                    txtReward.setText(Rewa);
//
//                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putString("Totalwallet", Totalpoint);
//
//
//                    editor.apply();
//                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//
//                    editor1.putString("Totalwallet", Totalpoint);
//                    editor1.apply();
//                }
//            } else {
//                Toast.makeText(Profile.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            totalbalence = findViewById(R.id.tatalbalence);
//            txtcashwallet = findViewById(R.id.txtcashwallet);
//            txtwinningwallet = findViewById(R.id.txtwinningwallet);
//            txtReward = findViewById(R.id.rewardstv);
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Profile.this,
//                    20, 60, ContextCompat.getColor(Profile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        bannedapps = new bannedapp(Profile.this);
        bannedapps.bannedbig();


    }


    public void showAlertDialogAndExitApp(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(Profile.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialog.show();
    }
}
