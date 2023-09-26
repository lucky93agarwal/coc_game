package com.challengers.of.call;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Changepassword extends AppCompatActivity {
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    private static bannedapp bannedapps;
    String Totalcontest="0";
    EditText etnewpassword,etreenternewpassword, etoldpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        bannedapps = new bannedapp(Changepassword.this);
        bannedapps.bannedbig();
        ImageView ivback = findViewById(R.id.ivback);
        etnewpassword=findViewById(R.id.newpass);
        Totalcontest = getIntent().getStringExtra("Totalcontest");
        etoldpassword=findViewById(R.id.oldpass);
        etreenternewpassword=findViewById(R.id.renewpass);
        Button btn=findViewById(R.id.btnchangepassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnewpassword.length()==0)
                {
                    Toast.makeText(Changepassword.this,"Enter New Password",Toast.LENGTH_LONG).show();
                }
                else if(etoldpassword.length()==0)
                {
                    Toast.makeText(Changepassword.this,"Enter Old Password",Toast.LENGTH_LONG).show();
                }
                else if(etreenternewpassword.length()==0)
                {
                    Toast.makeText(Changepassword.this,"Confirm New Password",Toast.LENGTH_LONG).show();
                }
                else if(!etnewpassword.getText().toString().equals(etreenternewpassword.getText().toString()))
                {
                    Toast.makeText(Changepassword.this,"Password do not match",Toast.LENGTH_LONG).show();
                }
                else
                {
                    AsyncCallWS task=new AsyncCallWS();
                    task.execute();
                }
            }
        });

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent =new Intent(Main2Activity.this,Dashboard.class);
//                intent.putExtra("goto","challenger");
//                intent.putExtra("Totalcontest",Main2Activity.Totalcontest);
//                startActivity(intent);
                finish();
            }
        });
    }


    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText;
        String Newpassword,Oldpasswod,Count;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            String Name = prefs.getString("Name", null);

            displayText = WebService.Changepassword(Loginid,Newpassword,Oldpasswod,"U","","Changepassword");
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Count = jsonrowdata.getString("Count");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            hud.dismiss();
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Changepassword.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Changepassword.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                if (Count.equalsIgnoreCase("Fails")){
                    Toast.makeText(Changepassword.this, "Your Old Password is Wrong!", Toast.LENGTH_LONG).show();

                }else {


                    Toast.makeText(Changepassword.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Changepassword.this, Dashboard.class);
                    intent.putExtra("Totalcontest", Totalcontest);
                    startActivity(intent);
                    Dashboard.dashboard.finish();
                    Profile.profile.finish();
                    finish();
                }
            } else if (jsonArray.length() == 0) {
                Toast.makeText(Changepassword.this, "No detail found", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Changepassword.this, "Try Again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            hud = KProgressHUD.create(Changepassword.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
            Newpassword=etnewpassword.getText().toString();
            Oldpasswod=etoldpassword.getText().toString();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
