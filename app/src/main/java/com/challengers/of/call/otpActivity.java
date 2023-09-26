package com.challengers.of.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SocialCode.SocialFinalBtnClickResponseJson;
import com.challengers.of.call.SocialCode.SocialOTPRequestJson;
import com.challengers.of.call.SocialCode.SocialOTPResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
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

public class otpActivity extends AppCompatActivity {

    private String mVerificationId;
    //The edittext to input the code
    private EditText editTextCode;
    //firebase auth object
    private FirebaseAuth mAuth;
    public Dialog progressbar;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    private Vibrator vibrator;
    private ljsflsj good;
    String Token, Gender, UserRemFreeLife, Name, Mobile, Userids, wallet, Totallife, Password, Email, Role, Sponsorid, Totalcontest, Imageurl, Totalwallet, userid, Message;


    public String SocialName, SocialPassword, SocialAction, SocialEmail, SocialRole, SocialGender, Userid, Username, UserMobile, UserSponsorid, ImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(otpActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Token = instanceIdResult.getToken();

            }

        });
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("MobileNo");

        mobile = decrypt(good.key, mobile);

        sendVerificationCode(mobile);


        progressbar = new Dialog(this);

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


        Button btnsubmit = findViewById(R.id.btnsignup);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
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
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(otpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
                .addOnCompleteListener(otpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
//                            Intent intent = new Intent(otpActivity.this, Dashboard.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);

//                            AsyncCallWS task1 = new AsyncCallWS();
//                            task1.execute();

                            progressbar.setContentView(R.layout.progresbarlayout);
                            progressbar.setCancelable(false);
                            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(otpActivity.this,
                                    20, 60, ContextCompat.getColor(otpActivity.this, R.color.white));
                            loader.setAnimDuration(3000);

                            rotatingCircularDotsLoader.addView(loader);

                            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                            progressbar.show();

                            OTPActivtiy();

                        } else {

                            //verification unsuccessful.. display an error message
                            String error = task.getException().getMessage();
                            Toast.makeText(otpActivity.this, error, Toast.LENGTH_SHORT).show();

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

    private void OTPActivtiy() {
        final SocialOTPRequestJson request = new SocialOTPRequestJson();
        request.setSocialPassword(SocialPassword);
        request.setSocialAction(SocialAction);
        request.setSocialEmail(SocialEmail);
        request.setSocialGender(SocialGender);
        request.setUsername(Username);
        request.setUserMobile(UserMobile);
        request.setUserSponsorid(UserSponsorid);
        request.setImageURL(ImageURL);
        request.setToken(Token);

        SocialPassword = encrypt(good.key, good.initVector, SocialPassword);
        SocialAction = encrypt(good.key, good.initVector, SocialAction);
        SocialEmail = encrypt(good.key, good.initVector, SocialEmail);
        SocialGender = encrypt(good.key, good.initVector, SocialGender);
        Username = encrypt(good.key, good.initVector, Username);
        UserMobile = encrypt(good.key, good.initVector, UserMobile);
        UserSponsorid = encrypt(good.key, good.initVector, UserSponsorid);
        ImageURL = encrypt(good.key, good.initVector, ImageURL);


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
                        Toast.makeText(otpActivity.this, "Already Registered", Toast.LENGTH_LONG).show();
                    } else if (Message.equalsIgnoreCase("Success")) {
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

                        ImageURL = decrypt(good.key, ImageURL);
                        if (ImageURL.length() > 30) {
                            ImageURL = encrypt(good.key, good.initVector, ImageURL);

                            editor.putString("Profilepic", ImageURL);
                            editor.putString("Imageurl", null);


                        } else {
                            ImageURL = encrypt(good.key, good.initVector, ImageURL);
                            editor.putString("Imageurl", ImageURL);
                            editor.putString("Profilepic", null);

                        }

                        editor1.apply();
                        editor.apply();

                        Intent intent3 = new Intent(otpActivity.this, Dashboard.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent3.putExtra("Mobile", SocialEmail);
                        intent3.putExtra("Totalcontest", "0");


                        startActivity(intent3);
                        finish();
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                        Toast.makeText(otpActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    }




                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(otpActivity.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(otpActivity.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(otpActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(otpActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(otpActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(otpActivity.this, Login.class);
        startActivity(intent);
        finish();
    }
}
