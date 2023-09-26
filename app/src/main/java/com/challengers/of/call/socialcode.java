package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.SocialCode.SocialFinalBtnClickRequestJson;
import com.challengers.of.call.SocialCode.SocialFinalBtnClickResponseJson;
import com.challengers.of.call.SocialCode.SocialMobileCheckRequestJson;
import com.challengers.of.call.SocialCode.SocialMobileCheckResponseJson;
import com.challengers.of.call.SocialCode.SocialNameCheckRequestJson;
import com.challengers.of.call.SocialCode.SocialNameCheckResponseJson;
import com.challengers.of.call.SocialCode.SocialOTPRequestJson;
import com.challengers.of.call.SocialCode.SocialOTPResponseJson;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;


public class socialcode extends AppCompatActivity {

    private EditText refe, user, phone;
    private Button sumb;
    private TextView skip;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public String Count;


    public String SocialName, SocialPassword, SocialAction, SocialEmail, SocialRole, SocialGender, Userid, Username, UserMobile, UserSponsorid, ImageURL;
    public String displayText, refcode, Mobile, Token, IMEINumber, Referalcode, Usernamecheck;
    String  Gender, UserRemFreeLife, Name, Userids, wallet, Totallife, Password, Email, Role, Sponsorid, Totalcontest, Imageurl, Totalwallet, userid, Message;
    private SharedPreferences sharedPreferences;
    public Dialog epicDialog, unepicDialog, progressbar;

    private ljsflsj good;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialcode);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(socialcode.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Token = instanceIdResult.getToken();

            }

        });

        progressbar = new Dialog(this);

        refe = (EditText) findViewById(R.id.editText);
        user = (EditText) findViewById(R.id.etUsername);
        phone = (EditText) findViewById(R.id.etMobile);
        sumb = (Button) findViewById(R.id.btnreferralcode);


        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);




        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                    AsyncCallWSRam task = new AsyncCallWSRam();
//                    task.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
                            20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();
                    Check_Name();
                }
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                    AsyncCallWSMobile task2 = new AsyncCallWSMobile();
//                    task2.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
                            20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();

                    Check_Mobile();
                }
            }
        });


//            String mobile =String.valueOf(sharedPreferences.getInt("EXP",Context.MODE_PRIVATE));
//            String.valueOf(sharedPreferences.getInt("EXP",Context.MODE_PRIVATE));

//        refcode = sharedPreferences().getString("Loginid",Context.MODE_PRIVATE);


        sumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.length() == 0) {

                    Toast.makeText(socialcode.this, "Enter User Name", Toast.LENGTH_LONG).show();


                } else if (phone.length() == 0) {

                    Toast.makeText(socialcode.this, "Enter Mobile Number", Toast.LENGTH_LONG).show();


                } else if (phone.length() != 10) {
                    Toast.makeText(socialcode.this, "Please Enter valid Mobile Number", Toast.LENGTH_SHORT).show();

                } else {
//                    AsyncCallWS task = new AsyncCallWS();
//                    task.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
                            20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();


                    FinalButtonClick();

                }

            }
        });


    }

    private void FinalButtonClick() {



        SharedPreferences prefss = getSharedPreferences("UserDatasss", Context.MODE_PRIVATE);

        IMEINumber = prefss.getString("IMEI", null);

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        SocialPassword = prefs.getString("SocialPassword", null);
        SocialAction = prefs.getString("From", null);
        SocialEmail = prefs.getString("SocialEmail", null);

        SocialGender = prefs.getString("SocialGender", null);
        Username = prefs.getString("Name",null);
        UserMobile = prefs.getString("Mobile", null);
        UserSponsorid = prefs.getString("Sponsorid", null);
        ImageURL = prefs.getString("SocialImageURL", null);


        SocialPassword = decrypt(good.key, SocialPassword);
        SocialAction = decrypt(good.key, SocialAction);
        SocialEmail = decrypt(good.key, SocialEmail);
        SocialGender = decrypt(good.key, SocialGender);
        Username = decrypt(good.key, Username);
        UserMobile = decrypt(good.key, UserMobile);
        UserSponsorid = decrypt(good.key, UserSponsorid);
        ImageURL = decrypt(good.key, ImageURL);
        IMEINumber = decrypt(good.key, IMEINumber);


        final SocialOTPRequestJson request = new SocialOTPRequestJson();
        request.setSocialPassword(SocialPassword);
        request.setSocialAction(SocialAction);
        request.setSocialEmail(SocialEmail);
        request.setSocialGender(SocialGender);
        request.setUsername(user.getText().toString().trim());
        request.setUserMobile(phone.getText().toString().trim());
        request.setUserSponsorid(UserSponsorid);
        request.setImageURL(ImageURL);
        request.setToken(Token);
        request.setImei(IMEINumber);

        refcode = refe.getText().toString().trim();
        Mobile = phone.getText().toString().trim();
        Username = user.getText().toString().trim();



        refcode = encrypt(good.key, good.initVector, refcode);
        Mobile = encrypt(good.key, good.initVector, Mobile);
        Username = encrypt(good.key, good.initVector, Username);

        UserService service = ServiceGenerator.createService(UserService.class, request.getUsername(), request.getSocialPassword());
        service.socialcodeotp(request).enqueue(new Callback<SocialOTPResponseJson>() {
            @Override
            public void onResponse(Call<SocialOTPResponseJson> call, Response<SocialOTPResponseJson> response) {
                if (response.isSuccessful()) {

                    Message = response.body().message;
                    for (int z = 0; z < response.body().data.size(); z++) {
                        Userids = response.body().data.get(z).username;
                        UserRemFreeLife = response.body().data.get(z).userRFreeLife;
                        wallet = response.body().data.get(z).totalwallet;
                        userid = response.body().data.get(z).userid;
                        Sponsorid = response.body().data.get(z).sponsorid;
                        Totallife = response.body().data.get(z).Totallife;
                        Password = response.body().data.get(z).Password;


                        Userids = encrypt(good.key, good.initVector, Userids);
                        UserRemFreeLife = encrypt(good.key, good.initVector, UserRemFreeLife);
                        wallet = encrypt(good.key, good.initVector, wallet);
                        userid = encrypt(good.key, good.initVector, userid);
                        Sponsorid = encrypt(good.key, good.initVector, Sponsorid);
                        Totallife = encrypt(good.key, good.initVector, Totallife);
                        Password = encrypt(good.key, good.initVector, Password);

                    }
                    if (Message.equalsIgnoreCase("alreadyregistered")) {
                        Toast.makeText(socialcode.this, "Already Registered", Toast.LENGTH_LONG).show();
                    } else if (Count.equalsIgnoreCase("imei")) {
                        Toast.makeText(socialcode.this, "Your device is already Registered", Toast.LENGTH_SHORT).show();
                    } else  if (Message.equalsIgnoreCase("Success")) {
                        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = lucky.edit();
                        edit.putString("userid", userid);
                        edit.apply();
                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("Name", Username);
                        editor.putString("UserRemFreeLife", UserRemFreeLife);////
                        editor.putString("Mobile", UserMobile);
                        editor.putString("From", SocialAction);
                        editor.putString("Password", Username);
                        editor.putString("Totallife", Totallife);//////
                        editor.putString("Totalwallet", wallet);/////
                        editor.putString("Playstatus", "1");
                        editor.putString("Sponsorid", Sponsorid);////
                        editor.putString("Loginid", Userids);/////
                        editor.putString("Wallet", SocialGender);


                        SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();

                        editor1.putString("Mobile", SocialEmail);
                        editor1.putString("Loginid", Userids);

//                        ImageURL = decrypt(good.key, ImageURL);
//                        if (ImageURL.length() > 30) {
                            ImageURL = encrypt(good.key, good.initVector, ImageURL);

                            editor.putString("Profilepic", ImageURL);
                            editor.putString("Imageurl", null);

//
//                        } else {
//                            ImageURL = encrypt(good.key, good.initVector, ImageURL);
//                            editor.putString("Imageurl", ImageURL);
//                            editor.putString("Profilepic", null);
//
//                        }

                        editor1.apply();
                        editor.apply();

                        Intent intent3 = new Intent(socialcode.this, Dashboard.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent3.putExtra("Mobile", SocialEmail);
                        intent3.putExtra("Totalcontest", "0");


                        startActivity(intent3);
                        finish();
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                        Toast.makeText(socialcode.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    }




                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(socialcode.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(socialcode.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(socialcode.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(socialcode.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(socialcode.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<SocialOTPResponseJson> call, Throwable t) {
                t.printStackTrace();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

    private void Check_Mobile() {
        final SignUpMobileCheckRequestJson request = new SignUpMobileCheckRequestJson();
        request.setMobile(phone.getText().toString().trim());

        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.signupmobile(request).enqueue(new Callback<SignUpMobileCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpMobileCheckResponseJson> call, Response<SignUpMobileCheckResponseJson> response) {
                if (response.isSuccessful()) {
                    Count = response.body().message;
                    if (Count.equalsIgnoreCase("Exist")) {

                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number already exist", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#9b0000"));
                        snack.show();

                    } else {

                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number available", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#00902E"));
                        snack.show();

                    }
                    progressbar.dismiss();

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(socialcode.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(socialcode.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(socialcode.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(socialcode.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(socialcode.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(socialcode.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<SignUpMobileCheckResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

    private void Check_Name() {
        final SignUpNameCheckRequestJson request = new SignUpNameCheckRequestJson();
        request.setName(user.getText().toString().trim());

        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.signupname(request).enqueue(new Callback<SignUpNameCheckResponseJson>() {
            @Override
            public void onResponse(Call<SignUpNameCheckResponseJson> call, Response<SignUpNameCheckResponseJson> response) {
                if (response.isSuccessful()) {

                    Count = response.body().message;

                    if (Count.equalsIgnoreCase("Exist")) {

                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Nick name already exist", Snackbar.LENGTH_LONG);
                        View view1 = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
                        params.gravity = Gravity.BOTTOM;
                        view1.setLayoutParams(params);
                        view1.setBackgroundColor(Color.parseColor("#9b0000"));
                        snack.show();

                    } else {

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
//                            Toast.makeText(socialcode.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(socialcode.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(socialcode.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(socialcode.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(socialcode.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(socialcode.this, "Something error please try again", Toast.LENGTH_SHORT).show();

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


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(socialcode.this, Login.class);
        startActivity(intent);
        finish();
    }
/////// phone check api
//    public class AsyncCallWSMobile extends AsyncTask<String, Void, Void> {
//
//        String displayText = "";
//        String Mobile, Count;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Checkmobile(Mobile, "", "Validatemobile");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)"))
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("verify");
//
//                        Count = encrypt(good.key, good.initVector, Count);
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(socialcode.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(socialcode.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
//                Toast.makeText(socialcode.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                Count = decrypt(good.key, Count);
//                if (Count.equalsIgnoreCase("Exist")) {
//
//                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number already exist", Snackbar.LENGTH_LONG);
//                    View view1 = snack.getView();
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                    params.gravity = Gravity.BOTTOM;
//                    view1.setLayoutParams(params);
//                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
//                    snack.show();
////                    layoutunavailable.setVisibility(View.VISIBLE);
////                    layoutunavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
////                    layoutavailable.setVisibility(View.GONE);
//                } else {
//
//                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Mobile Number available", Snackbar.LENGTH_LONG);
//                    View view1 = snack.getView();
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                    params.gravity = Gravity.BOTTOM;
//                    view1.setLayoutParams(params);
//                    view1.setBackgroundColor(Color.parseColor("#00902E"));
//                    snack.show();
////                    layoutunavailable.setVisibility(View.GONE);
////                    layoutavailable.setVisibility(View.VISIBLE);
////                    layoutavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
//                }
//            } else {
//                Toast.makeText(socialcode.this, "Please try again.", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            Mobile = phone.getText().toString().trim();
//
//            Mobile = encrypt(good.key, good.initVector, Mobile);
//
//
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
//                    20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
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

////// username time api
//    public class AsyncCallWSRam extends AsyncTask<String, Void, Void> {
//        String displayText = "";
//        String Username, Count;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Checkexistence(Username, "", "CheckUsername");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");
//
//                        Count = encrypt(good.key, good.initVector, Count);
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
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(socialcode.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(socialcode.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (displayText.equalsIgnoreCase("Unable to connect to server")) {
//                Toast.makeText(socialcode.this, "Unable to connect to server...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                Count = decrypt(good.key, Count);
//                if (Count.equalsIgnoreCase("Exist")) {
//
//                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Nick name already exist", Snackbar.LENGTH_LONG);
//                    View view1 = snack.getView();
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                    params.gravity = Gravity.BOTTOM;
//                    view1.setLayoutParams(params);
//                    view1.setBackgroundColor(Color.parseColor("#9b0000"));
//                    snack.show();
////                    layoutunavailable.setVisibility(View.VISIBLE);
////                    layoutunavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
////                    layoutavailable.setVisibility(View.GONE);
//                } else {
//
//                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Nick name available", Snackbar.LENGTH_LONG);
//                    View view1 = snack.getView();
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
//                    params.gravity = Gravity.BOTTOM;
//                    view1.setLayoutParams(params);
//                    view1.setBackgroundColor(Color.parseColor("#00902E"));
//                    snack.show();
////                    layoutunavailable.setVisibility(View.GONE);
////                    layoutavailable.setVisibility(View.VISIBLE);
////                    layoutavailable.startAnimation(AnimationUtils.loadAnimation(Register.this, R.anim.imageanim));
//                }
//            } else {
//                Toast.makeText(socialcode.this, "Please try again.", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            Username = user.getText().toString().trim();
//
//            Username = encrypt(good.key, good.initVector, Username);
//
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
//                    20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
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


    //////// main button check
//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText, refcode, Mobile, Username;
//        String Referal, Mobilecheck, Referalcode, Usernamecheck;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//
//
//            displayText = WebService.Sendreferal(refcode, Mobile, Username, "CompleteSocial");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//
//                        Referal = jsonrowdata.getString("Referal");
//                        Mobilecheck = jsonrowdata.getString("Mobilecheck");
//                        Usernamecheck = jsonrowdata.getString("Usernamecheck");
//                        Referalcode = jsonrowdata.getString("Referalcode");
//
//
//
//                        Referal = encrypt(good.key, good.initVector, Referal);
//                        Mobilecheck = encrypt(good.key, good.initVector, Mobilecheck);
//                        Usernamecheck = encrypt(good.key, good.initVector, Usernamecheck);
//                        Referalcode = encrypt(good.key, good.initVector, Referalcode);
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
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(socialcode.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(socialcode.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                Referal = decrypt(good.key, Referal);
//
//                if (Referal.equalsIgnoreCase("notexist")) {
//                    Toast.makeText(socialcode.this, "Incorrect Referal Code", Toast.LENGTH_LONG).show();
//                } else if (Usernamecheck.equalsIgnoreCase("exist")) {
//                    Toast.makeText(socialcode.this, "User Name is Exist..", Toast.LENGTH_SHORT).show();
//                } else if (Mobilecheck.equalsIgnoreCase("exist")) {
//                    Toast.makeText(socialcode.this, "Mobile Number is Exist..", Toast.LENGTH_SHORT).show();
//                } else {
//
//
//                    Login.login.finish();
//
//                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//
//
//                    editor.putString("Name", Username);
//                    editor.putString("Mobile", Mobile);
//                    editor.putString("Sponsorid", Referalcode);
//                    editor.apply();
//
//                    SharedPreferences pref2 = getSharedPreferences("UserChallengeData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = pref2.edit();
//                    editor1.putString("Mobile", Mobile);
//                    editor1.putString("Name", Username);
//
//
//                    editor1.apply();
//                    Intent intent = new Intent(socialcode.this, otpActivity.class);
//                    intent.putExtra("MobileNo",Mobile);
//
//                    startActivity(intent);
//                    finish();
//
//                    if (Referal.equalsIgnoreCase("apply")) {
//                        Toast.makeText(socialcode.this, "Your Referal Code Successfully applied", Toast.LENGTH_LONG).show();
//                    }
//
//
//                }
//            } else {
//                Toast.makeText(socialcode.this, "Log In Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            refcode = refe.getText().toString().trim();
//            Mobile = phone.getText().toString().trim();
//            Username = user.getText().toString().trim();
//
//
//
//
//            refcode = encrypt(good.key, good.initVector, refcode);
//            Mobile = encrypt(good.key, good.initVector, Mobile);
//            Username = encrypt(good.key, good.initVector, Username);
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(socialcode.this,
//                    20, 60, ContextCompat.getColor(socialcode.this, R.color.white));
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
}