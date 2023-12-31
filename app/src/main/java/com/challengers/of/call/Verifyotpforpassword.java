package com.challengers.of.call;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.concurrent.TimeUnit;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class Verifyotpforpassword extends AppCompatActivity {
    private String mVerificationId;
    //The edittext to input the code
    private EditText editTextCode;
    //firebase auth object
    private FirebaseAuth mAuth;
    public static Context context;
    private static bannedapp bannedapps;
    private ljsflsj good;
    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Verifyotpforpassword.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Verifyotpforpassword.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        context = getApplicationContext();
        bannedapps = new bannedapp(Verifyotpforpassword.this);
        bannedapps.bannedbig();
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("MobileNo");
        mobile = decrypt(good.key, mobile);
        sendVerificationCode(mobile);

        Button btnsubmit = findViewById(R.id.btnsignup);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
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
            Toast.makeText(Verifyotpforpassword.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
                .addOnCompleteListener(Verifyotpforpassword.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(Verifyotpforpassword.this, Resetpassword.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {

                            Toast.makeText(Verifyotpforpassword.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Verifyotpforpassword.this,Frogetpassword.class);
        startActivity(intent);
    }
}