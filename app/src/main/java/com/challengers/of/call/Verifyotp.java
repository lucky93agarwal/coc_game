package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import androidx.annotation.NonNull;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpVerifyRequestJson;
import com.challengers.of.call.SignUpData.SignUpVerifyResponseJson;
import com.challengers.of.call.SocialCode.SocialOTPRequestJson;
import com.challengers.of.call.SocialCode.SocialOTPResponseJson;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Verifyotp extends AppCompatActivity {
    private static bannedapp bannedapps;
    public Dialog unepicDialog, progressbar;
    private String mVerificationId,Message;
    //The edittext to input the code
    private EditText editTextCode;
    //firebase auth object
    private FirebaseAuth mAuth;

    String mobile = "";
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Username, Password, Sponsorid, Email;
    public String Dob, Gender, Address, Name,Mobile, Dateofbirth,Playstatus,Totallife,Verify, Gameid;
    private Vibrator vibrator;
    private ljsflsj good;


    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Verifyotp.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Verifyotp.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);


        bannedapps = new bannedapp(Verifyotp.this);
        bannedapps.bannedbig();

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        progressbar = new Dialog(this);
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);
//
//
        Intent intent = getIntent();
        mobile = intent.getStringExtra("Mobile");
        Username = intent.getStringExtra("Username");
        Password = intent.getStringExtra("Password");
        Sponsorid = intent.getStringExtra("Sponsorid");
        mobile = decrypt(good.key, mobile);
        sendVerificationCode(mobile);


        Button btnsubmit = findViewById(R.id.btnsignup);
        TextView resend = findViewById(R.id.tvResendOtp);
        final TextView expiry = findViewById(R.id.tvTimer);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(myAnim);
                vibrator.vibrate(70);
                String code = editTextCode.getText().toString().trim();

                if (code == null || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }else{

                    verifyVerificationCode(code);


                }







            }
        });


        resend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                sendVerificationCode(mobile);
                Toast.makeText(Verifyotp.this, "Otp sent Successful", Toast.LENGTH_LONG).show();
            }

        });

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                String minute, second;

                int seconds = (int) ((millisUntilFinished / (1000)) % 60);

                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);

                minute = Integer.toString(minutes);

                second = Integer.toString(seconds);

                if (minutes < 10) {
                    String a = "0" + minutes;
                    minute = a;
                }
                if (seconds < 10) {
                    String a = "0" + seconds;
                    second = a;
                }

                expiry.setText("Expired in: " + minute +":"+ second);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                expiry.setText("Expired");
            }

        }.start();
    }










    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verificati on status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
                Toast.makeText(Verifyotp.this, "Verification Successful", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verifyotp.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;

        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verifyotp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

//                            AsyncCallWS task1 =new  AsyncCallWS();
//                            task1.execute();
                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Verifyotp.this,
                                    20, 60, ContextCompat.getColor(Verifyotp.this, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();


                            SignupVerify();


                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

//                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
//                            snackbar.setAction("Dismiss", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                }
//                            });
//                            snackbar.show();
                        }
                    }
                });
    }


    private void SignupVerify(){
        final SocialOTPRequestJson request = new SocialOTPRequestJson();
        request.setUsername(decrypt(good.key, Username));
        request.setUserMobile(mobile);
        request.setSocialPassword(decrypt(good.key, Password));
        request.setUserSponsorid(decrypt(good.key, Sponsorid));



        UserService service = ServiceGenerator.createService(UserService.class, request.getUsername(), request.getSocialPassword());
        service.socialcodeotp(request).enqueue(new Callback<SocialOTPResponseJson>() {
            @Override
            public void onResponse(Call<SocialOTPResponseJson> call, Response<SocialOTPResponseJson> response) {
                if (response.isSuccessful()) {
                    Message = response.body().message;



                    if (Message.equalsIgnoreCase("alreadyregistered")) {
                        Toast.makeText(Verifyotp.this, "Already Registered", Toast.LENGTH_LONG).show();
                    } else if(Message.equalsIgnoreCase("Success")){




                        vibrator.vibrate(70);
                        Intent intent2 = new Intent(Verifyotp.this, Login.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(Verifyotp.this, "Registered Successfully", Toast.LENGTH_LONG).show();



                    }


                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Verifyotp.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Verifyotp.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Verifyotp.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Verifyotp.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Verifyotp.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Verifyotp.this, "Something error please try again", Toast.LENGTH_SHORT).show();

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


//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Dob, Gender, Address, Name,Mobile, Dateofbirth,Playstatus,Totallife,Verify, Gameid;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Userregistration(Username, mobile, Password, Sponsorid, "User", "coc", "Registration");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        Verify = jsonrowdata.getString("verify");
//                        Mobile = jsonrowdata.getString("Mobile");
//                        Gameid=jsonrowdata.getString("userid");
//
//                        Verify = encrypt(good.key, good.initVector, Verify);
//                        Mobile = encrypt(good.key, good.initVector, Mobile);
//                        Gameid = encrypt(good.key, good.initVector, Gameid);
//
//
//                    }
//
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
//                Toast.makeText(Verifyotp.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Verifyotp.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                Mobile = decrypt(good.key, Mobile);
//                Verify = decrypt(good.key, Verify);
//                if (Mobile.equalsIgnoreCase("alreadyregistered")) {
//                    Toast.makeText(Verifyotp.this, "Already Registered", Toast.LENGTH_LONG).show();
//                } else if(Verify.equalsIgnoreCase("Success")){
//
//                    SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor edit = lucky.edit();
//                    edit.putString("userid",Gameid);
//                    edit.apply();
//
//
//                    vibrator.vibrate(70);
//                    Intent intent2 = new Intent(Verifyotp.this, Login.class);
//                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                    overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//                    startActivity(intent2);
//                    finish();
//                    Toast.makeText(Verifyotp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(Verifyotp.this, "Registered Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Verifyotp.this,
//                    20, 60, ContextCompat.getColor(Verifyotp.this, R.color.white));
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
        super.onBackPressed();

        Intent intent = new Intent(Verifyotp.this,Login.class);
        startActivity(intent);
        finish();
    }
}

