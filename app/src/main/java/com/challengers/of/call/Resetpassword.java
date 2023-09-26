package com.challengers.of.call;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Frogetpass.FrogetPassRequestJson;
import com.challengers.of.call.ResetPass.ResetPassRequestJson;
import com.challengers.of.call.ResetPass.ResetpassResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpMobileCheckResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Resetpassword extends AppCompatActivity {
    String Mobile = "",Password;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    EditText etmobileno,etpassword,etconfirmpassword;

    private String userid,Msg;
    private static bannedapp bannedapps;

    public Dialog epicDialog, unepicDialog, progressbar;
    public static Context context;
    private ljsflsj good;


    private SharedPreferences sharedPreferences;


    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Resetpassword.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Resetpassword.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);


        bannedapps = new bannedapp(Resetpassword.this);
        bannedapps.bannedbig();
        progressbar = new Dialog(this);

        etpassword=findViewById(R.id.editText2);
        etconfirmpassword=findViewById(R.id.editText3);

        sharedPreferences = getSharedPreferences("Userid", Context.MODE_PRIVATE);

        userid = sharedPreferences.getString("userid",null);



        context = getApplicationContext();
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        Button btnchangepassword=findViewById(R.id.btnchangepassword);
        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                view.startAnimation(myAnim);
                if(etpassword.length()==0)
                {
                    Toast.makeText(Resetpassword.this,"Enter Password",Toast.LENGTH_LONG).show();
                }
                else if(etconfirmpassword.length()==0)
                {
                    Toast.makeText(Resetpassword.this,"Confirm Password",Toast.LENGTH_LONG).show();
                }
                else if(etpassword.getText().toString().trim().equals(etconfirmpassword.getText().toString().trim()))
                {
//                    AsyncCallWS task=new AsyncCallWS();
//                    task.execute();
                    Password = etpassword.getText().toString().trim();

                    ResetPass();
                }
                else
                {
                    Toast.makeText(Resetpassword.this,"Password did not match",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void ResetPass() {
        final ResetPassRequestJson request = new ResetPassRequestJson();
        request.setUserid(decrypt(good.key, userid));
        request.setPassword(Password);

        UserService service = ServiceGenerator.createService(UserService.class, request.getUserid(), request.getPassword());
        service.resetpass(request).enqueue(new Callback<ResetpassResponseJson>() {
            @Override
            public void onResponse(Call<ResetpassResponseJson> call, Response<ResetpassResponseJson> response) {
                if (response.isSuccessful()) {
                    Msg = response.body().message;
                    if (Msg.equalsIgnoreCase("Not Exist")) {
                        Toast.makeText(Resetpassword.this, "Your Password Not SAVE!", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent2 = new Intent(Resetpassword.this, Login.class);
                        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(Resetpassword.this, "Password changed successfully", Toast.LENGTH_LONG).show();
                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Resetpassword.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Resetpassword.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Resetpassword.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Resetpassword.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Resetpassword.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Resetpassword.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<ResetpassResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }


//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Msg;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Forgetpassword(userid, Password,  "", "ResetPassword");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Msg = jsonrowdata.getString("Msg");
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
//                Toast.makeText(Resetpassword.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Resetpassword.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                if (Msg.equalsIgnoreCase("Not Exist")) {
//                    Toast.makeText(Resetpassword.this, "Your Password Not SAVE!", Toast.LENGTH_LONG).show();
//                } else {
//                    Intent intent2 = new Intent(Resetpassword.this, Login.class);
//                    overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//                    startActivity(intent2);
//                    finish();
//                    Toast.makeText(Resetpassword.this, "Password changed successfully", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(Resetpassword.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            Password=etpassword.getText().toString();
//
//            Password = encrypt(good.key, good.initVector, Password);
//
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Resetpassword.this,
//                    20, 60, ContextCompat.getColor(Resetpassword.this, R.color.white));
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
        Intent intent = new Intent(Resetpassword.this,Login.class);
        startActivity(intent);
    }
}
