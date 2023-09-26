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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.helper.GlideCircleTransformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Wallet extends AppCompatActivity {
//    TextView txtaddmoney;
    String From = "", Login;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    public ImageView imageView,ivwithdraw;
    public ImageView ivaddmoney;
    public KProgressHUD hud;

    public Dialog epicDialog, unepicDialog, progressbar;


    public String GameName;

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet2);
        imageView = (ImageView)findViewById(R.id.wallethistory);
        ivaddmoney = (ImageView)findViewById(R.id.add_money);
        SharedPreferences sharedPreferences = getSharedPreferences("coding",Context.MODE_PRIVATE);
        GameName = sharedPreferences.getString("gametype",null);
        ivwithdraw = (ImageView)findViewById(R.id.withdraw);

        progressbar = new Dialog(this);

        ivwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wallet.this, Withdraw.class);
                startActivity(intent);
                finish();
            }
        });


        ivaddmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wallet.this,paytm.class);
                intent.putExtra("from","wallet");
                startActivity(intent);
                finish();
            }
        });


//        txtaddmoney = findViewById(R.id.txtaddmoney);
        From = getIntent().getStringExtra("from");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wallet.this,wallethistory.class);
//                intent.putExtra("from",From);
                startActivity(intent);
                finish();
            }
        });

        ImageView ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (From.equalsIgnoreCase("dashboard")) {
                    Intent intent = new Intent(Wallet.this, Dashboard.class);
                    intent.putExtra("goto", "challenger");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    Dashboard.dashboard.finish();
                    finish();
                } else if (From.equalsIgnoreCase("freequiz")) {
                    Intent intent = new Intent(Wallet.this, Freequizdashboard.class);
                    startActivity(intent);
                    finish();
                } else if (From.equalsIgnoreCase("main")) {
                    Intent intent = new Intent(Wallet.this, Main2Activity.class);
                    intent.putExtra("goto", "main");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    Main2Activity.main.finish();
                    finish();
                }else if (From.equalsIgnoreCase("life")){
                    Intent intent = new Intent(Wallet.this, Life.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Login = prefs.getString("Loginid", null);
        String Loginid = prefs.getString("drawable", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);
        String Name = prefs.getString("Name", null);
        ImageView ivprofile = findViewById(R.id.ivprofile);
        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);
        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        if (Loginid != null) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            int imageId = getResourseId(this, Loginid, "drawable", getPackageName());
            img.setImageResource(imageId);
        } else if (Customimage != null && Customimage != "") {
            l1.setVisibility(View.GONE);
            ivprofile.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Customimage)
                    .crossFade()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }else
            if (Profilepic != null && Profilepic != "") {
                l1.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext()).load(Profilepic)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(imageView);
            }

        TextView textView = findViewById(R.id.username);
        textView.setText(Name);
        AsyncCallWSsendotp task = new AsyncCallWSsendotp();
        task.execute();
    }

    @Override
    public void onBackPressed() {
       if (hud != null){

       }else {
        if (From.equalsIgnoreCase("dashboard")) {
            Intent intent = new Intent(Wallet.this, Dashboard.class);
            intent.putExtra("goto", "challenger");
            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);
            Dashboard.dashboard.finish();
            finish();
        } else if (From.equalsIgnoreCase("freequiz")) {
            Intent intent = new Intent(Wallet.this, Freequizdashboard.class);
            startActivity(intent);
            finish();
        } else if (From.equalsIgnoreCase("main")) {
            Intent intent = new Intent(Wallet.this, Main2Activity.class);
            intent.putExtra("goto", "main");
            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);
            Main2Activity.main.finish();
            finish();
        }else if (From.equalsIgnoreCase("life")){
            Intent intent = new Intent(Wallet.this, Life.class);
            intent.putExtra("from","freequiz");
            startActivity(intent);
            finish();
        }
       }

    }

    public class AsyncCallWSsendotp extends AsyncTask<String, Void, Void> {
        String From = "", displayText = "";
        String Cashwallet, Winningamount, Totalpoint;
        TextView totalbalence, txtcashwallet, txtwinningwallet;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.Selectchallenge(Login,GameName, "Selectwallet");

            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Cashwallet = jsonrowdata.getString("Cashwallet");
                        Winningamount = jsonrowdata.getString("Winningamount");
                        Totalpoint = jsonrowdata.getString("Totalpoint");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            hud.dismiss();
            progressbar.dismiss();
            if (displayText != null && !displayText.isEmpty()) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(Wallet.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(Wallet.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {
                    totalbalence.setText(Totalpoint);
                    txtcashwallet.setText(Cashwallet);
                    txtwinningwallet.setText(Winningamount);
                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totalwallet", Totalpoint);


                    editor.apply();
                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref2.edit();

                    editor1.putString("Totalwallet",Totalpoint);
                    editor1.apply();
                }
            } else {
                Toast.makeText(Wallet.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            totalbalence = findViewById(R.id.tatalbalence);
            txtcashwallet = findViewById(R.id.txtcashwallet);
            txtwinningwallet = findViewById(R.id.txtwinningwallet);
//            hud = KProgressHUD.create(Wallet.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setWindowColor(Color.parseColor("#c20e14"))
//                    .setLabel("")
//                    .setAnimationSpeed(1);
//            hud.show();
            progressbar.setContentView(R.layout.progresbarlayout);
            progressbar.setCancelable(false);
            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Wallet.this,
                    20, 60, ContextCompat.getColor(Wallet.this, R.color.white));
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
