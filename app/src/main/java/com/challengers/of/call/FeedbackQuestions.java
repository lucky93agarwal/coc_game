package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Logout.FeedbackRequestJson;
import com.challengers.of.call.Logout.LogoutResponseJson;
import com.challengers.of.call.Retrofit.Log;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class FeedbackQuestions extends AppCompatActivity {

    public EditText mMessageEt,mSubjectET,myouremailET;
    public Button mSendEmailBtn;
    public String Message,Name,YourEmail;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    EditText editText, editText2;
    private String Totalcontest;
    private Vibrator vibrator;
    public Dialog progressbar;

    private ljsflsj good;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_questions);

        progressbar = new Dialog(this);

        mSendEmailBtn = (Button)findViewById(R.id.sendEmailbtn);
        mMessageEt = (EditText)findViewById(R.id.massageet);
        mSubjectET = (EditText)findViewById(R.id.etsubject);
        myouremailET = (EditText)findViewById(R.id.etyouremail);

        mSendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMessageEt.length()==0){
                    Toast.makeText(FeedbackQuestions.this, "Plase Enter your Message", Toast.LENGTH_SHORT).show();
                }else if (mSubjectET.length()==0){
                    Toast.makeText(FeedbackQuestions.this, "Plase Enter your Message Subject", Toast.LENGTH_SHORT).show();
                }else if (myouremailET.length()==0){
                    Toast.makeText(FeedbackQuestions.this, "Plase Enter your Email ID", Toast.LENGTH_SHORT).show();
                }
                else {
//                    AsyncCallWS task=new AsyncCallWS();
//                    task.execute();

                    Feedback();
                }
            }
        });
    }



    private void Feedback() {
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final FeedbackRequestJson request = new FeedbackRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setEmail(myouremailET.getText().toString().trim());
        request.setMessage(mMessageEt.getText().toString().trim());
        request.setName(mSubjectET.getText().toString().trim());


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.getfeedback(request).enqueue(new Callback<LogoutResponseJson>() {
            @Override
            public void onResponse(Call<LogoutResponseJson> call, Response<LogoutResponseJson> response) {
                if (response.isSuccessful()) {

                    String message = response.body().message;


                    if (message.equalsIgnoreCase("success")){
                        Toast.makeText(FeedbackQuestions.this, "Thank you for your valuable Feedback", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(FeedbackQuestions.this,Dashboard.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(FeedbackQuestions.this, "Please try after sometime", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(FeedbackQuestions.this, "Something error please try again", Toast.LENGTH_SHORT).show();
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



//
//
//
//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText, Userid, Password;
//        String Username, Check;
//        private KProgressHUD hud;
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.FeedBack(YourEmail, Name, Message, "UserFeedBack");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Check = jsonrowdata.getString("check");
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
//                Toast.makeText(FeedbackQuestions.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(FeedbackQuestions.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                if (Check.equalsIgnoreCase("Save")){
//                    Toast.makeText(FeedbackQuestions.this, "Thank you for your valuable Feedback", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(FeedbackQuestions.this,Dashboard.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Toast.makeText(FeedbackQuestions.this, "Email Sent Failed! Please Try Again ", Toast.LENGTH_LONG).show();
//                }
//
//
//
//
//
//            } else {
//                Toast.makeText(FeedbackQuestions.this, "Log In Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//            Message = mMessageEt.getText().toString().trim();
//            Name = mSubjectET.getText().toString().trim();
//            YourEmail = myouremailET.getText().toString().trim();
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(FeedbackQuestions.this,
//                    20, 60, ContextCompat.getColor(FeedbackQuestions.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FeedbackQuestions.this,Dashboard.class);
        startActivity(intent);
        finish();
    }
}
