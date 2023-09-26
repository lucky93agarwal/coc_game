package com.challengers.of.call;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SplashData.VersionRequestJson;
import com.challengers.of.call.SplashData.VersionResponseJson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Splash extends AppCompatActivity {
    private static bannedapp bannedapps;
    LinearLayout Layout_bars;
    TextView[] bottomBars;
    int[] screens;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Mobile, Password;
    String Loginid, sumit;
    String Name, Email, From;
    public static String facebookuri = "";
    ViewPager vp;
    public TextView VersionName; // Version NUmber
    public Dialog epicDialog, unepicDialog, progressbar, closeapp;

    public String version, application, new_version, message, methodname;
    public String onemethod;

    public String oneverson;

    public static Context context;
    SharedPreferences settings;
    public String IMEINumber;

    private ljsflsj good;
    private VersionRequestJson request;
    private static String Names;
    static final int PERMISSION_READ_STATE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bannedapps = new bannedapp(Splash.this);
        bannedapps.bannedbig();


        PackageInfo pInfo;
        VersionName = (TextView) findViewById(R.id.versionname);// Version Number
        try {
            int versiterbaru = BuildConfig.VERSION_CODE;
            version = String.valueOf(versiterbaru);



//                        encrypted = AESUtils.encrypt(Totalwallet);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        version = BuildConfig.VERSION_NAME;// String variable
//        VersionName.setText("Version " + version);
        progressbar = new Dialog(this);
        closeapp = new Dialog(this, R.style.PauseDialog);


//        version = String.valueOf(versiterbaru);
        application = "0";
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionCode + "";
            Name = getPackageName();





        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Name = prefs.getString("Name", null);
        From = prefs.getString("From", null);
        Email = prefs.getString("Mobile", null);
        Mobile = prefs.getString("Mobile", null);
        Password = prefs.getString("Password", null);

        try {
            Loginid = encrypt(good.key, good.initVector, Loginid);
            Name = encrypt(good.key, good.initVector, Name);
            From = encrypt(good.key, good.initVector, From);
            Email = encrypt(good.key, good.initVector, Email);
            Mobile = encrypt(good.key, good.initVector, Mobile);
            Password = encrypt(good.key, good.initVector, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Loginid = encrypt(good.key, good.initVector, Loginid);
//        Name = encrypt(good.key, good.initVector, Name);
//        From = encrypt(good.key, good.initVector, From);
//        Email = encrypt(good.key, good.initVector, Email);
//        Mobile = encrypt(good.key, good.initVector, Mobile);
//        Password = encrypt(good.key, good.initVector, Password);

        if (bannedapps.bannedbig()) {

        } else {
            good();
        }


    }

    private void good() {
        int permissionCheck = ContextCompat.checkSelfPermission(Splash.this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyTelephoneManager();
        } else {
            ActivityCompat.requestPermissions(Splash.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }
    }



    private MaterialDialog pupop(String message) {

        try {

            message = decrypt(good.key, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final MaterialDialog mds = new MaterialDialog.Builder(this)
                .title("Maintenance")
                .content(message)
                .icon(new IconicsDrawable(this)
                        .icon(FontAwesome.Icon.faw_google)
                        .color(Color.RED)
                        .sizeDp(24))
                .cancelable(false)
                .positiveColor(Color.BLUE)
                .negativeColor(Color.RED)
                .show();

        return mds;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyTelephoneManager();
                } else {
                    Toast.makeText(Splash.this, "it's Mandatory to Allow", Toast.LENGTH_LONG).show();
                    good();
                }
            }
        }
    }

    private void MyTelephoneManager() {

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            IMEINumber = manager.getImei();
//        }else {
//            IMEINumber = manager.getDeviceId();
//        }
        IMEINumber = manager.getDeviceId();
//        Toast.makeText(Splash.this, IMEINumber, Toast.LENGTH_LONG).show();

        if (IMEINumber != null && !IMEINumber.isEmpty()){


            String IMEINumbers = encrypt(good.key, good.initVector, IMEINumber);
            request = new VersionRequestJson();

            request.version = version;
            request.application = "0";
            request.imei = IMEINumber;
            request.packagename = getPackageName();

            retrofit();

            SharedPreferences prefs = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("IMEI", IMEINumbers);
            edit.apply();

        }else {
//            Toast.makeText(Splash.this, "Device Information not get", Toast.LENGTH_LONG).show();

            IMEINumber = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

            String IMEINumbers = encrypt(good.key, good.initVector, IMEINumber);
            request = new VersionRequestJson();

            request.version = version;
            request.application = "0";
            request.imei = IMEINumber;
            request.packagename = getPackageName();

            retrofit();

            SharedPreferences prefs = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("IMEI", IMEINumbers);
            edit.apply();
        }

    }

    private void retrofit() {


        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.checkVersion(request).enqueue(new Callback<VersionResponseJson>() {
            @Override
            public void onResponse(Call<VersionResponseJson> call, Response<VersionResponseJson> response) {
                if (response.isSuccessful()) {


                    if (response.body().new_version.equalsIgnoreCase("0")) {
                        showPopupUpdate(encrypt(good.key, good.initVector, response.body().message));
                    } else if (response.body().new_version.equalsIgnoreCase("1")) {
                        start();
                    } else if (response.body().new_version.equalsIgnoreCase("2")) {
                        pupop(encrypt(good.key, good.initVector, response.body().message));
                    } else if (response.body().new_version.equalsIgnoreCase("3")) {
                        Toast.makeText(Splash.this, "Due to unusual activities your account has been blocked.", Toast.LENGTH_LONG).show();
                    }else if (response.body().new_version.equalsIgnoreCase("4")) {
                        Toast.makeText(Splash.this, "This is Clone Application. Please use real COC app", Toast.LENGTH_LONG).show();
                    }
                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Splash.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Splash.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Splash.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Splash.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Splash.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Splash.this, "Something error please try again", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<VersionResponseJson> call, Throwable t) {
                t.printStackTrace();
                showPopupHold("Problems when loading apps");

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }


    private MaterialDialog showPopupUpdate(String message) {
        try {

            message = decrypt(good.key, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final MaterialDialog md = new MaterialDialog.Builder(this)
                .title("New Apps Available")
                .content(message)
                .icon(new IconicsDrawable(this)
                        .icon(FontAwesome.Icon.faw_google)
                        .color(Color.RED)
                        .sizeDp(24))
                .positiveText("OK")
                .negativeText("NO")
                .cancelable(false)
                .positiveColor(Color.BLUE)
                .negativeColor(Color.RED)
                .show();

        View positive = md.getActionButton(DialogAction.POSITIVE);

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                md.dismiss();
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://callofchallengers.com")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://callofchallengers.com")));
                }
                finish();
            }
        });
        View negative = md.getActionButton(DialogAction.NEGATIVE);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                md.dismiss();
                finish();
            }

        });
        return md;
    }

    private MaterialDialog showPopupHold(String message) {
        try {

            message = decrypt(good.key, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final MaterialDialog md = new MaterialDialog.Builder(this)
                .title("Notification")
                .content(message)
                .icon(new IconicsDrawable(this)
                        .icon(FontAwesome.Icon.faw_android)
                        .color(Color.BLUE)
                        .sizeDp(24))
                .positiveText("OK")
                .negativeText("NO")
                .cancelable(false)
                .positiveColor(Color.BLUE)
                .negativeColor(Color.RED)
                .show();

        View positive = md.getActionButton(DialogAction.POSITIVE);

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                md.dismiss();
                start();
                finish();
            }
        });
        View negative = md.getActionButton(DialogAction.NEGATIVE);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                md.dismiss();
                finish();
            }

        });

        return md;
    }

    private void start() {


//        Thread td = new Thread(){
//            public void run(){
//                try {
//                    sleep(5000);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }finally
//                {

        try {
            Loginid = decrypt(good.key, Loginid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent;

        if (Loginid != null) {
            intent = new Intent(Splash.this, Dashboard.class);

            Splash.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        } else {
            intent = new Intent(Splash.this, Login.class);

            Splash.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        }
        startActivity(intent);
//                }
//            }
//        }; td.start();


    }


}