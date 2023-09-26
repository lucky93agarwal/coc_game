package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Vibrator;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.Signindata.FBLoginRequestJson;
import com.challengers.of.call.SplashData.VersionResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Register extends AppCompatActivity {

    EditText etName, etPassword, etMobile, etRefralcode;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    CheckBox chkpolicy2;
    TextView chkpolicy;
    boolean available = false;
    boolean available1 = false;
    private Vibrator vibrator;
    private String Check, Count;
    public static Context context;
    public Dialog unepicDialog, progressbar;
    private ljsflsj good;

    public String Username, Mobile, Password, IMEINumber, CheckMobileNo, Sponsorid, MethodName;

    private static bannedapp bannedapps;

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Register.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Register.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();
        progressbar = new Dialog(this);

        bannedapps = new bannedapp(Register.this);
        bannedapps.bannedbig();

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        etName = (EditText) findViewById(R.id.editText);
        etPassword = (EditText) findViewById(R.id.editText2);
        etMobile = (EditText) findViewById(R.id.editText3);
        etRefralcode = (EditText) findViewById(R.id.editText4);
        chkpolicy2 = (CheckBox) findViewById(R.id.chkpolicy2);
        chkpolicy = (TextView) findViewById(R.id.chkpolicy);

        Button btn = (Button) findViewById(R.id.btnsignup);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        TextView txtlogin = (TextView) findViewById(R.id.txtsignup);


        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        chkpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://callofchallengers.com/coc/term_condition.php"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });


        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                    AsyncCallWS task = new AsyncCallWS();
////                    task.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Register.this,
                            20, 60, ContextCompat.getColor(Register.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();

                    Check_Name();
                }
            }
        });
        etMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                    AsyncCallWSMobile task2 = new AsyncCallWSMobile();
//                    task2.execute();

                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Register.this,
                            20, 60, ContextCompat.getColor(Register.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();

                    Check_Mobile();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
//                view.setAnimation(myAnim);
                if (available) {
                    if (chkpolicy2.isChecked()) {
                        if (etName.length() == 0) {
                            Toast.makeText(Register.this, "Please Enter Name", Toast.LENGTH_LONG).show();
                        } else if (etPassword.length() == 0) {
                            Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                        } else if (etMobile.length() == 0) {
                            Toast.makeText(Register.this, "Please Enter Mobile No.", Toast.LENGTH_LONG).show();
                        } else if (available1 == false) {
                            Toast.makeText(Register.this, "Mobile Number is already registered", Toast.LENGTH_LONG).show();

                        } else {
//                            AsyncCallWSsendotp task = new AsyncCallWSsendotp();
//                            task.execute();
                            ///// final Button Check Api
                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Register.this,
                                    20, 60, ContextCompat.getColor(Register.this, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();
                            FinalButtonClick();
                            view.startAnimation(myAnim);
                        }
                    } else {
                        Toast.makeText(Register.this, "Please Accept term and condition", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Nick name not available.", Toast.LENGTH_LONG).show();
                }


            }

        });
    }

    ////// name check api
    private void Check_Name() {
        final SignUpNameCheckRequestJson request = new SignUpNameCheckRequestJson();
        request.setName(etName.getText().toString().trim());

        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.signupname(request).enqueue(new Callback<SignUpNameCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpNameCheckResponseJson> call, Response<SignUpNameCheckResponseJson> response) {
                if (response.isSuccessful()) {

                    Count = response.body().message;

                    if (Count.equalsIgnoreCase("Exist")) {
                        available = false;
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Nick name already exist", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#9b0000"));
                        snack.show();

                    } else {
                        available = true;
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Nick name available", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#00902E"));
                        snack.show();

                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Register.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Register.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Register.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Register.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Register.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(Register.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<SignUpNameCheckResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }

    //////// check mobile api
    private void Check_Mobile() {

        final SignUpMobileCheckRequestJson request = new SignUpMobileCheckRequestJson();
        request.setMobile(etMobile.getText().toString().trim());

        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.signupmobile(request).enqueue(new Callback<SignUpMobileCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpMobileCheckResponseJson> call, Response<SignUpMobileCheckResponseJson> response) {
                if (response.isSuccessful()) {
                    Count = response.body().message;
                    if (Count.equalsIgnoreCase("Exist")) {

                        available1 = false;
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number already exist", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#9b0000"));
                        snack.show();

                    } else {
                        available1 = true;
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number available", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#00902E"));
                        snack.show();

                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Register.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Register.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Register.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Register.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Register.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Register.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<SignUpMobileCheckResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });

    }


    private void FinalButtonClick() {
        SharedPreferences prefs = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);

        IMEINumber = prefs.getString("IMEI", null);
        final SignUpBtnCheckRequestJson request = new SignUpBtnCheckRequestJson();

        request.setMobile(etMobile.getText().toString().trim());
        request.setName(etName.getText().toString().trim());
        request.setSponsorid(etRefralcode.getText().toString().trim());
        Log.d("walletwalletssss","RafralCode= "+etRefralcode.getText().toString().trim());

        request.setPassword(etPassword.getText().toString().trim());
        request.setImei(decrypt(good.key, IMEINumber));


        Username = etName.getText().toString().trim();
        Mobile = etMobile.getText().toString().trim();
        Password = etPassword.getText().toString().trim();
        Sponsorid = etRefralcode.getText().toString().trim();


        Username = encrypt(good.key, good.initVector, Username);
        Mobile = encrypt(good.key, good.initVector, Mobile);
        Password = encrypt(good.key, good.initVector, Password);
        Sponsorid = encrypt(good.key, good.initVector, Sponsorid);


        UserService service = ServiceGenerator.createService(UserService.class, request.getName(), request.getPassword());
        service.signupbtn(request).enqueue(new Callback<SignUpBtnCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpBtnCheckResponseJson> call, Response<SignUpBtnCheckResponseJson> response) {
                if (response.isSuccessful()) {
                    Count = response.body().message;



                    if (Count.equalsIgnoreCase("allreadyregister")) {
                        Toast.makeText(Register.this, "Mobile No or User Name already Exist", Toast.LENGTH_SHORT).show();
                    } else if (Count.equalsIgnoreCase("invalideref")) {
                        Toast.makeText(Register.this, "Invalid Referral Code", Toast.LENGTH_SHORT).show();
                    } else if (Count.equalsIgnoreCase("imei")) {
                        Toast.makeText(Register.this, "Your device is already Registered", Toast.LENGTH_SHORT).show();
                    } else if (Count.equalsIgnoreCase("Success")) {


                        vibrator.vibrate(70);
                        Intent intent2 = new Intent(Register.this, Login.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(Register.this, "Invalid Referal Code", Toast.LENGTH_LONG).show();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Register.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Register.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Register.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Register.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Register.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Register.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<SignUpBtnCheckResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }



    @Override
    public void onDestroy() {
//        AsyncCallWSsendotp task1 = new AsyncCallWSsendotp();
//        if (task1 != null && task1.getStatus() == AsyncTask.Status.RUNNING) {
//            task1.cancel(true);
//        }
        super.onDestroy();
    }
}