package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Frogetpass.FrogetPassRequestJson;
import com.challengers.of.call.Frogetpass.FrogetPassResponseJson;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.Utils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Frogetpassword extends AppCompatActivity {
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String useridObject;
    private SharedPreferences sharedPreferences;
    EditText etMobile;
    public static Context context;
    public Dialog unepicDialog, progressbar;
    private ljsflsj good;
    private static bannedapp bannedapps;
    private String Check, Count;
    public String Username, Mobile, Password, Userid, CheckMobileNo, Sponsorid, MethodName;

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Frogetpassword.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Frogetpassword.this);
        bannedapps.bannedbig();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frogetpassword);

        bannedapps = new bannedapp(Frogetpassword.this);
        bannedapps.bannedbig();


        progressbar = new Dialog(this);
        etMobile = findViewById(R.id.editText);
        context = getApplicationContext();
        sharedPreferences = getSharedPreferences("Userid", Context.MODE_PRIVATE);
        Button btn = findViewById(R.id.btnforgetpassword);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etMobile.length() == 0) {
                    Toast.makeText(Frogetpassword.this, "Please Enter Mobile No.", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                } else {

                    if (Utils.isNetworkAvailable(Frogetpassword.this)) {

                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.backSoundonclick(context);
                        }
//                        AsyncCallWSsendotp task = new AsyncCallWSsendotp();
//                        task.execute();
                        progressbar.setContentView(R.layout.progresbarlayout);
                        progressbar.setCancelable(false);
                        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Frogetpassword.this,
                                20, 60, ContextCompat.getColor(Frogetpassword.this, R.color.white));
                        loader.setAnimDuration(3000);

                        rotatingCircularDotsLoader.addView(loader);

                        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                        progressbar.show();


                        FrogetPass();
                    } else {
                        Toast.makeText(Frogetpassword.this, "No internet connection!", Toast.LENGTH_LONG).show();
                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.setwronAnssound(context);
                        }
                    }


                    view.startAnimation(myAnim);
                }
            }
        });


    }

    private void FrogetPass() {
        final FrogetPassRequestJson request = new FrogetPassRequestJson();
        request.setMobile(etMobile.getText().toString().trim());

        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.forgetpass(request).enqueue(new Callback<FrogetPassResponseJson>() {
            @Override
            public void onResponse(Call<FrogetPassResponseJson> call, Response<FrogetPassResponseJson> response) {
                if (response.isSuccessful()) {
                    Count = response.body().message;

                    CheckMobileNo = response.body().checkmobileno;
                    Userid = response.body().userid;

                    CheckMobileNo = encrypt(good.key, good.initVector, CheckMobileNo);

                    Userid = encrypt(good.key, good.initVector, Userid);


                    if (Count.equalsIgnoreCase("Not Exist")) {
                        Toast.makeText(Frogetpassword.this, "You are not registered", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences("Userid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("userid", Userid);
                        edit.apply();
                        Intent intent = new Intent(Frogetpassword.this, Verifyotpforpassword.class);

                        intent.putExtra("MobileNo", CheckMobileNo);


                        startActivity(intent);
                        finish();


                        progressbar.dismiss();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Frogetpassword.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Frogetpassword.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Frogetpassword.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Frogetpassword.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Frogetpassword.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Frogetpassword.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<FrogetPassResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });


    }

    /////// after mobile no enter
//    public class AsyncCallWSsendotp extends AsyncTask<String, Void, Void> {
//        String From = "", displayText = "",Verify;
//        String Mobile, OTP, Responsemobile;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Getotp1(Mobile, "forgetpassword","ForgetOtp");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");

//                    for (int i = 0; i < jsonArray.length(); i++)
//                    {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
////                        OTP = jsonrowdata.getString("OTP");
//                        Responsemobile = jsonrowdata.getString("MobileNo");
//                        Verify = jsonrowdata.getString("Verify");
//                        useridObject = jsonrowdata.getString("Userid");

//
//
//                        Responsemobile = encrypt(good.key, good.initVector, Responsemobile);
//                        Verify = encrypt(good.key, good.initVector, Verify);
//                        useridObject = encrypt(good.key, good.initVector, useridObject);

//
//                        SharedPreferences.Editor edit = sharedPreferences.edit();
//                        edit.putString("userid",useridObject);
//                        edit.apply();
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
//            progressbar.dismiss();
//            if (displayText != null && !displayText.isEmpty()) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(Frogetpassword.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(Frogetpassword.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (jsonArray.length() > 0)
//                {
//                    Verify = decrypt(good.key, Verify);
//                    if(Verify.equalsIgnoreCase("Not Exist"))
//                    {
//                        Toast.makeText(Frogetpassword.this, "You are not registered", Toast.LENGTH_LONG).show();
//                    }
//                    else {
//                        Intent intent = new Intent(Frogetpassword.this, Verifyotpforpassword.class);
////                        intent.putExtra("OTPMessage", OTP);
////                        intent.putExtra("Password", Verify);
//                        intent.putExtra("MobileNo", Responsemobile);
////                        intent.putExtra("Mobile", Mobile);
//
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            } else {
//                Toast.makeText(Frogetpassword.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            Mobile = etMobile.getText().toString().trim();

//            Mobile = encrypt(good.key, good.initVector, Mobile);
//
//

//
//
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Frogetpassword.this,
//                    20, 60, ContextCompat.getColor(Frogetpassword.this, R.color.white));
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
        Intent intent = new Intent(Frogetpassword.this, Login.class);
        startActivity(intent);
    }
}
