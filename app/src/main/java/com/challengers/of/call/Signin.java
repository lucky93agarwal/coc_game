package com.challengers.of.call;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
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

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.Utils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Signin extends AppCompatActivity {
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    EditText editText, editText2;
    private String Totalcontest;
    private Vibrator vibrator;
    public String Username, Name,Email;
    public static Context context;
    public Dialog unepicDialog, progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button btn = findViewById(R.id.btnsignup);

        context = getApplicationContext();


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);

        progressbar = new Dialog(this);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText = findViewById(R.id.editText);
                editText2 = findViewById(R.id.editText2);
                if (editText.length() == 0) {
                    Toast.makeText(Signin.this, "Enter Mobile No./ Email Id", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                } else if (editText2.length() == 0) {
                    Toast.makeText(Signin.this, "Enter Password", Toast.LENGTH_LONG).show();
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.setwronAnssound(context);
                    }
                } else {

                    if (Utils.isNetworkAvailable(Signin.this)) {
                        AsyncCallWS task=new AsyncCallWS();
                        task.execute();
                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.backSoundonclick(context);
                        }
                    } else {
                        Toast.makeText(Signin.this, "No internet connection!", Toast.LENGTH_LONG).show();
                        if (SettingsPreferences.getVibration(context)) {
                            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                        }
                        if (SettingsPreferences.getSoundEnableDisable(context)) {
                            StaticUtils.setwronAnssound(context);
                        }
                    }


                    view.startAnimation(myAnim);
                    if (SettingsPreferences.getVibration(context)) {
                        StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                    }
                    if (SettingsPreferences.getSoundEnableDisable(context)) {
                        StaticUtils.backSoundonclick(context);
                    }

                }
            }
        });
        TextView txtsignup = findViewById(R.id.txtsignup);

        TextView txtforget=findViewById(R.id.txtforget);
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                Intent intent=new Intent(Signin.this,Frogetpassword.class);
                startActivity(intent);
                finish();

            }
        });


        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText, Userid, Password;
        String  Sponsorid, Gender, Role, Dateofbirth,Playstatus,Imageurl,Totalwallet,Totallife,UserRemFreeLife, Gameid;
        private KProgressHUD hud;
        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.Login(Userid, Password, "User", Password,"","Login");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Username = jsonrowdata.getString("username");///////// user
                        UserRemFreeLife = jsonrowdata.getString("userRFreeLife");
                        Name = jsonrowdata.getString("name");/////////////////////// name
                        Email = jsonrowdata.getString("email");////////////////// email
                        Role = jsonrowdata.getString("role");
                        Gender = jsonrowdata.getString("gender");
                        Dateofbirth = jsonrowdata.getString("dateofbirth");
                        Sponsorid = jsonrowdata.getString("sponsorid");
                        Playstatus=jsonrowdata.getString("playstatus");
                        Totalcontest=jsonrowdata.getString("Totalcontest");
                        Imageurl=jsonrowdata.getString("Imageurl");
                        Totalwallet=jsonrowdata.getString("Totalwallet");
                        Totallife=jsonrowdata.getString("Totallife");
                        Gameid=jsonrowdata.getString("userid");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(Signin.this, Gameid, Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressbar.dismiss();
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Signin.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Signin.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {

                SharedPreferences lucky = getSharedPreferences("coding",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = lucky.edit();
                edit.putString("userid",Gameid);
                edit.apply();


                if(Username.equalsIgnoreCase("notexist") || Name.equalsIgnoreCase("notexist")|| Email.equalsIgnoreCase("notexist"))
                {
                    Toast.makeText(Signin.this, "Incorrect Credential", Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Loginid", Username);
                    editor.putString("Mobile", Userid);
                    editor.putString("UserRemFreeLife",UserRemFreeLife);
                    editor.putString("Name", Name);
                    editor.putString("Password", Password);
                    editor.putString("Sponsorid", Sponsorid);
                    editor.putString("Playstatus", Playstatus);
                    editor.putString("Wallet", Gender);
                    editor.putString("Totalwallet", Totalwallet);
                    editor.putString("Totallife", Totallife);
                    editor.putString("From","COC");
                    editor.apply();
                    if (Dateofbirth.equalsIgnoreCase("True")) {
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Playstatus", Playstatus);
                        if(Imageurl!="" && Imageurl!=null) {
                            editor1.putString("Imageurl", Imageurl);
                        }
                        editor1.apply();

                        vibrator.vibrate(70);
                        Intent intent = new Intent(Signin.this, Dashboard.class);

                        intent.putExtra("Totalcontest", Totalcontest);
                        startActivity(intent);
                        finish();
                    } else {
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Playstatus", Playstatus);
                        if(Imageurl!="" && Imageurl!=null) {
                            editor1.putString("Imageurl", Imageurl);
                        }
                        editor1.apply();



                        final Intent mainIntent = new Intent(Signin.this, Dashboard.class);
                        mainIntent.putExtra("Totalcontest", Totalcontest);
                        startActivity(mainIntent);
                        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                        Signin.this.finish();
                    }
                    Toast.makeText(Signin.this, "Logged In", Toast.LENGTH_LONG).show();
                    Login.login.finish();
                }
            } else {
                Toast.makeText(Signin.this, "Log In Failed", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onPreExecute() {
            Userid = editText.getText().toString().trim();
            Password = editText2.getText().toString().trim();
            progressbar.setContentView(R.layout.progresbarlayout);
            progressbar.setCancelable(false);
            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Signin.this,
                    20, 60, ContextCompat.getColor(Signin.this, R.color.white));
            loader.setAnimDuration(3000);

            rotatingCircularDotsLoader.addView(loader);

            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
            progressbar.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
