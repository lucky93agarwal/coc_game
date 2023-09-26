package com.challengers.of.call;

import android.app.DatePickerDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Profiledata.WithdrawRequestJson;
import com.challengers.of.call.Profiledata.WithdrawResponseJson;
import com.challengers.of.call.ResultApi.ContinueResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.UpdateProfile.UpdatePasswordRequestJson;
import com.challengers.of.call.UpdateProfile.UpdateProfileRequestJson;
import com.challengers.of.call.UpdateProfile.UpdateProfileResponseJson;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Updateprofile extends AppCompatActivity {
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    EditText etmobile, etname, etdate;

    String Totalcontest = "0", Gender = "", State = "";
    private int mYear, mMonth, mDay;
    RadioGroup radioGroup;
    RadioButton radioButton, rbtnmale, rbtnfemale;

    String Loginid, Name, From, Mobile, Password, Message;
    String pleaseselect = "Please Select States";
    String Newpassword, Oldpasswod, Count;
    public static Context context;
    public String genders = "male",DOB,Gender1;
    //// new page
    public Dialog epicDialog, unepicDialog, progressbar, withdraw;

    EditText etnewpassword, etreenternewpassword, etoldpassword;
    LinearLayout newpass, reentpass, oldpass, heading;
    private ljsflsj good;
    ///// new page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        context = getApplicationContext();
        progressbar = new Dialog(this);
        ////// new page
        etnewpassword = findViewById(R.id.newpass);
        etoldpassword = findViewById(R.id.oldpass);
        etreenternewpassword = findViewById(R.id.renewpass);

        newpass = (LinearLayout) findViewById(R.id.newpas);
        oldpass =  (LinearLayout) findViewById(R.id.oldpas);
        reentpass = (LinearLayout)  findViewById(R.id.renewpas);
        heading = (LinearLayout)  findViewById(R.id.heading);
        ///// new page


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        Name = prefs.getString("Name", null);
        From = prefs.getString("From", null);

        Mobile = prefs.getString("Mobile", null);
        Password = prefs.getString("Password", null);
        DOB = prefs.getString("DOB",null);
        genders = prefs.getString("Gender",null);

        genders = decrypt(good.key, genders);
        From = decrypt(good.key, From);

        etmobile = findViewById(R.id.editText);
        etmobile.setText(decrypt(good.key, Mobile));
        etname = findViewById(R.id.editText2);
        etname.setText(decrypt(good.key, Name));
        etdate = findViewById(R.id.editText3);
        etdate.setText(decrypt(good.key, DOB));

        rbtnmale = (RadioButton)findViewById(R.id.radioButton);
        rbtnfemale = (RadioButton)findViewById(R.id.radioButton2);

        radioGroup = findViewById(R.id.radioGroup);



        if (genders.equalsIgnoreCase("male")){
            radioGroup.check(rbtnmale.getId());
//            rbtnmale.isSelected();
        }else {
            radioGroup.check(rbtnfemale.getId());
//            rbtnfemale.isSelected();
        }


//        etmobile.setText(Mobile);
//        etname.setText(Name);
        Button btn = findViewById(R.id.btncontest);

        if (From.equalsIgnoreCase("facebook") || From.equalsIgnoreCase("google")) {
            etmobile.setVisibility(View.VISIBLE);
            newpass.setVisibility(View.GONE);
            oldpass.setVisibility(View.GONE);
            reentpass.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);  ///// change password
            heading.setVisibility(View.GONE);
        } else {
            etmobile.setVisibility(View.VISIBLE);
            newpass.setVisibility(View.VISIBLE);
            oldpass.setVisibility(View.VISIBLE);
            reentpass.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);   ///// change password
            heading.setVisibility(View.VISIBLE);
        }
        ImageView ivdob = findViewById(R.id.ivdob);


        ivdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                setDateTimeField();
            }
        });
        Totalcontest = getIntent().getStringExtra("Totalcontest");
        Button btns = findViewById(R.id.btnsave);///// update profile
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }


                if (etdate.length() == 0) {
                    Toast.makeText(Updateprofile.this, "Enter  Your DOB", Toast.LENGTH_LONG).show();
                } else {
                    int selectedId = radioGroup.getCheckedRadioButtonId();


                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);
                    Gender = radioButton.getText().toString();


//                    AsyncCallWS tasks = new AsyncCallWS();
//                    tasks.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Updateprofile.this,
                            20, 60, ContextCompat.getColor(Updateprofile.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();

                    Update();



                }

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //// update password

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                if (etnewpassword.length() == 0) {
                    Toast.makeText(Updateprofile.this, "Enter New Password", Toast.LENGTH_LONG).show();
                } else if (etoldpassword.length() == 0) {
                    Toast.makeText(Updateprofile.this, "Enter Old Password", Toast.LENGTH_LONG).show();
                } else if (etreenternewpassword.length() == 0) {
                    Toast.makeText(Updateprofile.this, "Confirm New Password", Toast.LENGTH_LONG).show();
                } else if (!etnewpassword.getText().toString().equals(etreenternewpassword.getText().toString())) {
                    Toast.makeText(Updateprofile.this, "Password do not match", Toast.LENGTH_LONG).show();
                } else {

//
//                    AsyncCallWSS task = new AsyncCallWSS();
//                    task.execute();
                    progressbar.setContentView(R.layout.progresbarlayout);
                    progressbar.setCancelable(false);
                    RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                    RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Updateprofile.this,
                            20, 60, ContextCompat.getColor(Updateprofile.this, R.color.white));
                    loader.setAnimDuration(3000);

                    rotatingCircularDotsLoader.addView(loader);

                    progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                    progressbar.show();


                    updatePass();

                }

            }
        });


    }




    private void setDateTimeField() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        etdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void Update() {
        Name = etname.getText().toString();
        DOB = etdate.getText().toString();
        Mobile = etmobile.getText().toString();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final UpdateProfileRequestJson request = new UpdateProfileRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setDOB(DOB);
        request.setGender(Gender);


        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.getupdate(request).enqueue(new Callback<UpdateProfileResponseJson>() {
            @Override
            public void onResponse(Call<UpdateProfileResponseJson> call, Response<UpdateProfileResponseJson> response) {
                if (response.isSuccessful()) {

                    DOB = response.body().DOB;
                    Gender1 = response.body().Gender;





                    DOB = encrypt(good.key, good.initVector, DOB);
                    Gender1 = encrypt(good.key, good.initVector, Gender1);



                    etname.setSelection(Name.length());
                    etdate.setText(DOB);
                    etdate.setSelection(DOB.length());
                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("DOB",DOB);
                    editor.putString("Gender",Gender1);
                    editor.apply();
                    etdate.setText(decrypt(good.key, DOB));

                    Toast.makeText(Updateprofile.this, "Profile Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Updateprofile.this, Profile.class);
                    intent.putExtra("Totalcontest", Totalcontest);
                    startActivity(intent);
                    Dashboard.dashboard.finish();
                    Profile.profile.finish();
                    finish();
                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Updateprofile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Updateprofile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Updateprofile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Updateprofile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Updateprofile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<UpdateProfileResponseJson> call, Throwable t) {
                t.printStackTrace();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }


//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Mobilenumber, Name, DOB, Gender1, State1;
//        private KProgressHUD hud;
//        String[] Statemaster;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            DOB = encrypt(good.key, good.initVector, DOB);
//            Gender = encrypt(good.key, good.initVector, Gender);
//            displayText = WebService.Updateprofile(Loginid, DOB, Gender, "Updateprofile");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        DOB = jsonrowdata.getString("DOB");
//                        Gender1 = jsonrowdata.getString("Gender");
//
//
//                        DOB = encrypt(good.key, good.initVector, DOB);
//                        Gender1 = encrypt(good.key, good.initVector, Gender1);
//                        System.out.println("State1" + State1);
//
//                    }
//
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
//                Toast.makeText(Updateprofile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Updateprofile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//
//                etname.setSelection(Name.length());
//                etdate.setText(DOB);
//                etdate.setSelection(DOB.length());
//                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("DOB",DOB);
//                editor.putString("Gender",Gender1);
//                editor.apply();
//                etdate.setText(decrypt(good.key, DOB));
//
//                Toast.makeText(Updateprofile.this, "Profile Updated", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Updateprofile.this, Profile.class);
//                intent.putExtra("Totalcontest", Totalcontest);
//                startActivity(intent);
//                Dashboard.dashboard.finish();
//                Profile.profile.finish();
//                finish();
//
//
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(Updateprofile.this, "No detail found", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(Updateprofile.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Updateprofile.this,
//                    20, 60, ContextCompat.getColor(Updateprofile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
////            Mobilenumber = etmobile.getText().toString();
//            Name = etname.getText().toString();
//            DOB = etdate.getText().toString();
//            Mobile = etmobile.getText().toString();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

    private void updatePass() {
        Newpassword = etnewpassword.getText().toString();
        Oldpasswod = etoldpassword.getText().toString();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);

        final UpdatePasswordRequestJson request = new UpdatePasswordRequestJson();
        request.setLoginid(decrypt(good.key, Loginid));
        request.setNewpassword(Newpassword);
        request.setOldpassword(Oldpasswod);



        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.updatepass(request).enqueue(new Callback<ContinueResponseJson>() {
            @Override
            public void onResponse(Call<ContinueResponseJson> call, Response<ContinueResponseJson> response) {
                if (response.isSuccessful()) {

                    Message = response.body().message;



                    if (Message.equalsIgnoreCase("Fails")) {
                        Toast.makeText(Updateprofile.this, "Your Old Password is Wrong!", Toast.LENGTH_LONG).show();

                    } else {


                        Toast.makeText(Updateprofile.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Updateprofile.this, Profile.class);
                        intent.putExtra("Totalcontest", Totalcontest);
                        startActivity(intent);
                        Dashboard.dashboard.finish();
                        Profile.profile.finish();
                        finish();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Updateprofile.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Updateprofile.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Updateprofile.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Updateprofile.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Updateprofile.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<ContinueResponseJson> call, Throwable t) {
                t.printStackTrace();

                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }
//    public class AsyncCallWSS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Newpassword, Oldpasswod, Count;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            String Name = prefs.getString("Name", null);
//            Newpassword = encrypt(good.key, good.initVector, Newpassword);
//            Oldpasswod = encrypt(good.key, good.initVector, Oldpasswod);
//            displayText = WebService.Changepassword(Loginid, Newpassword, Oldpasswod, "U", "", "ChangeAccountSecret");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");
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
//                Toast.makeText(Updateprofile.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Updateprofile.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                if (Count.equalsIgnoreCase("Fails")) {
//                    Toast.makeText(Updateprofile.this, "Your Old Password is Wrong!", Toast.LENGTH_LONG).show();
//
//                } else {
//
//
//                    Toast.makeText(Updateprofile.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(Updateprofile.this, Profile.class);
//                    intent.putExtra("Totalcontest", Totalcontest);
//                    startActivity(intent);
//                    Dashboard.dashboard.finish();
//                    Profile.profile.finish();
//                    finish();
//                }
//            } else if (jsonArray.length() == 0) {
//                Toast.makeText(Updateprofile.this, "No detail found", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(Updateprofile.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Updateprofile.this,
//                    20, 60, ContextCompat.getColor(Updateprofile.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
//            Newpassword = etnewpassword.getText().toString();
//            Oldpasswod = etoldpassword.getText().toString();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }
        Intent intent = new Intent(Updateprofile.this, Profile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
