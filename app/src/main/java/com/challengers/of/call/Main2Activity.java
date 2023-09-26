package com.challengers.of.call;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GameTypes.GametypesActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.challengers.of.call.adapter.Mycontestadapter;
import com.challengers.of.call.fragment.Fragmentchallenges;
import com.challengers.of.call.fragment.Fragmentcontest;
import com.challengers.of.call.fragment.Fragmentresult;
import com.challengers.of.call.fragment.Fragmenttab;
import com.challengers.of.call.helper.GlideCircleTransformation;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.challengers.of.call.Dashboard.getResourseId;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;

public class Main2Activity extends AppCompatActivity {
    private static bannedapp bannedapps;
    public static String Totalcontest = "0";
    public static Activity main;
    public boolean run = false;
    String go;
    View parent;
    Fragmenttab fragmenttab;

    JSONObject jasonObject;
    JSONArray jsonArray = null;

    CountDownTimer timer;
    String Loginid = "";
//    private AdView mAdView;
    private ImageView homeimage;


    private static addmoneypopup addpopup;
    //// Vibration
    public Vibrator vibrator;
    //// Vibration
    private ljsflsj good;
    public LinearLayout linearLayout;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        try {
            Loginid = decrypt(good.key, Loginid);
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (Loginid != null) {

        } else {
            Intent intent = new Intent(Main2Activity.this, Login.class);

            startActivity(intent);
            Main2Activity.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Main2Activity.this);
        bannedapps.bannedbig();


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Loginid = prefs.getString("Loginid", null);
        try {
            Loginid = decrypt(good.key, Loginid);
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (Loginid != null) {

        } else {
            Intent intent = new Intent(Main2Activity.this, Login.class);

            startActivity(intent);
            Main2Activity.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Main2Activity.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //// Vibration
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        bannedapps = new bannedapp(Main2Activity.this);
        bannedapps.bannedbig();
        //// Vibration
        //// button click animation
        final Animation myAnim = AnimationUtils.loadAnimation(Main2Activity.this,R.anim.buttonanimation);
        //// button click animation
        addpopup = new addmoneypopup(Main2Activity.this);
        ////// Game Chose Tab


        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);

                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }
                addpopup.addpopup();
            }
        });

        ////// Game Chose Tab
        homeimage = (ImageView)findViewById(R.id.ivHome);
        homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Dashboard.class);
                startActivity(intent);
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }
            }
        });






        Totalcontest = getIntent().getStringExtra("Totalcontest");
        ImageView ivback = findViewById(R.id.ivback);

        ImageView ivprofile = findViewById(R.id.ivprofile);
        main = this;
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("drawable", null);
        String Customimage = prefs.getString("Imageurl", null);
        ImageView imageView = findViewById(R.id.ivprofileview);

//        timer = new CountDownTimer(86400000, 10000) {
//            public void onTick(long millisUntilFinished) {

//            }
//            public void onFinish() {
//            }
//        }.start();
        String Profilepic = prefs.getString("Profilepic", null);
        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        Loginid = decrypt(good.key, Loginid);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);
        if (Loginid != null) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            ivprofile.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            int imageId = getResourseId(this, Loginid, "drawable", getPackageName());
            ivprofile.setImageResource(imageId);
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
                ivprofile.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext()).load(Profilepic)
                        .crossFade()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                        .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .into(imageView);
            }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main2Activity.this, Profile.class);
                intent2.putExtra("Totalcontest", Totalcontest);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
            }
        });

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main2Activity.this, Dashboard.class);
                intent.putExtra("goto", "challenger");
                intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                startActivity(intent);
                finish();
            }
        });

        ImageView ivwallet = findViewById(R.id.ivwallet);

        ivwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Main2Activity.this, Wallet.class);
//                intent.putExtra("from", "main");
//                startActivity(intent);
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }
                view.startAnimation(myAnim);
                vibrator.vibrate(70);

                addpopup.addpopup();
//                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });



        TextView txtwallet = findViewById(R.id.txtwallet);

        txtwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(myAnim);
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }

                addpopup.addpopup();
            }
        });
        SharedPreferences lucky = getSharedPreferences("coding",Context.MODE_PRIVATE);
        String Name = lucky.getString("name",null);
        Name = decrypt(good.key, Name);
//        String Name = getIntent().getStringExtra("gamename");



        String Totalwallet = prefs.getString("Totalwallet", null);

        String Twallet = decrypt(good.key, Totalwallet);
        if (Twallet != null && Twallet != "") {
            txtwallet.setText(Twallet);
        } else {
            txtwallet.setText("0");
        }

        TextView textView = findViewById(R.id.username);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(Main2Activity.this, Profile.class);
//                intent2.putExtra("Totalcontest", Totalcontest);
//                startActivity(intent2);
//                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//            }
//        });
        textView.setText(Name);

        String Loginids = prefs.getString("Loginid", null);
        try {
            Loginids = decrypt(good.key, Loginids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Loginids != null) {
            go = getIntent().getStringExtra("goto");
            if (go.equalsIgnoreCase("Mycontest")) {
                FragmentManager manager1 = getSupportFragmentManager();

            }else if (go.equalsIgnoreCase("Result") || go.equalsIgnoreCase("challenger")){

                Bundle bundle = new Bundle();
                bundle.putString("goto", go);
                fragmenttab = new Fragmenttab();
                fragmenttab.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainframe, fragmenttab, "fragmenttab");
                transaction.commit();
            }else {

                Bundle bundle = new Bundle();
                bundle.putString("goto", go);
                fragmeFnttab = new Fragmenttab();
                fragmenttab.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainframe, fragmenttab, "fragmenttab");
                transaction.commit();
            }
        }else {

        }

//        //startTimer();
//        if (Totalcontest.equalsIgnoreCase("0")) {
//        } else {
//            addcounter(2, Integer.valueOf(Totalcontest));
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        if (SettingsPreferences.getVibration(getApplicationContext())) {
            StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
            StaticUtils.backSoundonclick(getApplicationContext());
        }
        Intent intent = new Intent(Main2Activity.this, GametypesActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);
    }

    public void setwallet(String text) {
        TextView txtwallet = findViewById(R.id.txtwallet);
        txtwallet.setText(text);
    }
    public static void lucky(){

    }

    @Override
    protected void onDestroy() {
//        Toast.makeText(Main2Activity.this, "ondestroy called", Toast.LENGTH_LONG).show();
        if(timer!=null)
        {
            timer.cancel();
        }
//        if(task.getStatus()== AsyncTask.Status.RUNNING)
//        {
//            task.cancel(true);
//        }
        Fragmentcontest fragmentcontest = new Fragmentcontest();
        if (fragmentcontest != null) {
//            fragmentcontest.canclealltask();
        }
        Fragmentchallenges fragmentchallenges = new Fragmentchallenges();
        if (fragmentchallenges != null) {
//            fragmentchallenges.canclealltask();
        }
        Fragmentresult fragmentresult = new Fragmentresult();
        if (fragmentresult != null) {
//            fragmentresult.canclealltask();
        }
        super.onDestroy();
    }




    private void starttimer() {

    }

}
