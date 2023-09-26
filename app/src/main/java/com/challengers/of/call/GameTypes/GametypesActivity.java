package com.challengers.of.call.GameTypes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Add_Money_Popup.addmoneypopup;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.Dashboardapidata.TokenRequestJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfityResponseJson;
import com.challengers.of.call.GridViewAdapter;
import com.challengers.of.call.Login;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.Practice.GetPracticeResponseJson;
import com.challengers.of.call.Practice.PracticeActivity;
import com.challengers.of.call.Profile;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.Splash;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.PracticeAdapter;
import com.challengers.of.call.adapter.gametypeAdapter;
import com.challengers.of.call.data.GetDataGameType;
import com.challengers.of.call.fifty_fity_popup.fiftyfifty;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.Dashboard.getResourseId;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;


public class GametypesActivity extends AppCompatActivity {
    private static bannedapp bannedapps;
    String firstobject, twoobject, threeobject, fourobject, fiveobject;
    String firstrupess, tworupess, threerupess, fourrupess, fiverupess;
    ////// Game Chose Tab
    public ImageView  btngametwo;
    public Button btnquiz;
    ////// Game Chose Tab
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    //// Vibration
    public Vibrator vibrator;
    //// Vibration
    public String Totalcontestnew;
    String Dailylife, Referlife, TotalWallet, Remaininglife, firsttext, secondtext, threetext, fourtext, fivetext, firstrs, twors, threers, fourrs, fivers;
    /// it for RecyclerView
    public List<GetDataGameType> productList = new ArrayList<>();
    public GetDataGameType getbcdata;
    public gametypeAdapter adapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public String LIST_STATE_KEY = "list_state";
    /// it for RecyclerView
    public String Name, Img, Id, Url, layout;
    public String UserName;
    public Dialog unepicDialog, progressbar;
    public LinearLayout linearLayout;

    private static addmoneypopup addpopup;
    private static fiftyfifty fifty;
    public Animation myAnim;
    public String Totalwallet,Names;
    public TextView tvwallet;
    public String Totallife;
    ImageView ivprofile;

    private ljsflsj good;
    public String Loginid;

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
            Intent intent = new Intent(GametypesActivity.this, Login.class);

            startActivity(intent);
            GametypesActivity.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(GametypesActivity.this);
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
            Intent intent = new Intent(GametypesActivity.this, Login.class);

            startActivity(intent);
            GametypesActivity.this.finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(GametypesActivity.this);
        bannedapps.bannedbig();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gametypes);


        bannedapps = new bannedapp(GametypesActivity.this);
        bannedapps.bannedbig();

        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("drawable", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);
        UserName = prefs.getString("Name", null);


        fifty = new fiftyfifty(GametypesActivity.this);





        TextView fifityLinear = (TextView)findViewById(R.id.fifity);
        fifityLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }

                fifty.fiftyfiftys();
            }
        });
        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);
        ivprofile = findViewById(R.id.ivprofile);

        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);
        Loginid = decrypt(good.key, Loginid);
        Customimage = decrypt(good.key, Customimage);
        Profilepic = decrypt(good.key, Profilepic);
        if (Loginid != null) {
            Log.i("xsumitxxx", Loginid);
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            int imageId = getResourseId(this, Loginid, "drawable", getPackageName());
            img.setImageResource(imageId);

        } else if (Customimage != null && Customimage != "") {
            Log.i("xsumitxx", Customimage);
//            Log.i("xsumit", Profilepic);
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

        } else if (Profilepic != null && Profilepic != "") {
            Log.i("xsumit", Profilepic);
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
        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureialog();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(GametypesActivity.this, Profile.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
            }
        });

        LinearLayout quizeonlinegame = (LinearLayout)findViewById(R.id.quizegame);
        quizeonlinegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }
                SharedPreferences lucky = getSharedPreferences("coding",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = lucky.edit();
                edit.putString("gametype","no_url");
                edit.putString("gameid",encrypt(good.key, good.initVector, "1"));
                edit.putString("gamelayout","0");
                edit.putString("name",encrypt(good.key, good.initVector, "Quiz"));
                edit.apply();
                Intent intent = new Intent(GametypesActivity.this, Main2Activity.class);
                intent.putExtra("goto", "challenger");
                intent.putExtra("gamename","Quiz");
                startActivity(intent);
            }
        });
        TextView quizegame = (TextView)findViewById(R.id.quizsss);
        quizegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }
                SharedPreferences lucky = getSharedPreferences("coding",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = lucky.edit();
                edit.putString("gametype","no_url");
                edit.putString("gameid",encrypt(good.key, good.initVector, "1"));
                edit.putString("gamelayout","0");
                edit.putString("name",encrypt(good.key, good.initVector, "Quiz"));
                edit.apply();
                Intent intent = new Intent(GametypesActivity.this, Main2Activity.class);
                intent.putExtra("goto", "challenger");
                intent.putExtra("gamename","Quiz");
                startActivity(intent);
            }
        });

        addpopup = new addmoneypopup(GametypesActivity.this);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        progressbar = new Dialog(this);


        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Totalwallet = pref2.getString("Totalwallet",null);
        Totalcontestnew = getIntent().getStringExtra("Totalcontest");
//        Totalwallet = getIntent().getStringExtra("Totalwallet");
//        Names = getIntent().getStringExtra("name");
        UserName = decrypt(good.key, UserName);
        TextView tvname = (TextView)findViewById(R.id.username);
        tvname.setText(UserName);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }

                Intent intent2 = new Intent(GametypesActivity.this, Profile.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                intent2.putExtra("Totalcontest", Totalcontestnew);
                startActivity(intent2);
                finish();


                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        Totalwallet = decrypt(good.key, Totalwallet);

        tvwallet = (TextView)findViewById(R.id.txtwallet);
        tvwallet.setText(Totalwallet);
        tvwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }

                addpopup.addpopup();
            }
        });



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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(GametypesActivity.this,
                20, 60, ContextCompat.getColor(GametypesActivity.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();
//
//        AsyncCallWS task = new AsyncCallWS();
//        task.execute();


        //// button click animation
        final Animation myAnim = AnimationUtils.loadAnimation(GametypesActivity.this,R.anim.buttonanimation);
        //// button click animation

        ////// Game Chose Tab
        btnquiz = (Button)findViewById(R.id.ivlogout);

        btnquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SettingsPreferences.getVibration(getApplicationContext())) {
                    StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
                    StaticUtils.backSoundonclick(getApplicationContext());
                }

                //// Vibration
                //// button click animation
                v.startAnimation(myAnim);
                //// button click animation
               Intent intent = new Intent(GametypesActivity.this, MyContestActivity.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
               startActivity(intent);
               finish();
            }
        });


//        AsyncCallWSFifty tasks = new AsyncCallWSFifty();
//        tasks.execute();

        String Loginids = prefs.getString("Loginid", null);
        try {
            Loginids = decrypt(good.key, Loginids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Loginids != null) {
            FiFityFiFity();
            GameListAPI();
        }else {

        }


    }

    private void FiFityFiFity() {


        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginid = prefs.getString("Loginid", null);

        final FifityapiRequestJson request = new FifityapiRequestJson();

        request.setUserid(decrypt(good.key, Loginid));

        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.fifity(request).enqueue(new Callback<FiftyfityResponseJson>() {
            @Override
            public void onResponse(Call<FiftyfityResponseJson> call, Response<FiftyfityResponseJson> response) {
                if (response.isSuccessful()) {

                    Remaininglife = response.body().Remaininglife;
                    TotalWallet = response.body().TotalWallet;
                    firstrs = response.body().Firstrs;
                    twors = response.body().Secondrs;
                    threers = response.body().Threers;
                    fourrs = response.body().Fourrs;
                    fivers = response.body().Fivers;
                    firsttext = response.body().FirstLife;
                    secondtext = response.body().SecondLife;
                    threetext = response.body().ThreeLife;

                    fourtext = response.body().FourLife;
                    fivetext = response.body().FiveLife;



                    Remaininglife = encrypt(good.key, good.initVector, Remaininglife);
                    TotalWallet = encrypt(good.key, good.initVector, TotalWallet);
                    firstrs = encrypt(good.key, good.initVector, firstrs);
                    twors = encrypt(good.key, good.initVector, twors);
                    threers = encrypt(good.key, good.initVector, threers);
                    fourrs = encrypt(good.key, good.initVector, fourrs);
                    fivers = encrypt(good.key, good.initVector, fivers);
                    firsttext = encrypt(good.key, good.initVector, firsttext);
                    secondtext = encrypt(good.key, good.initVector, secondtext);
                    threetext = encrypt(good.key, good.initVector, threetext);
                    fourtext = encrypt(good.key, good.initVector, fourtext);
                    fivetext = encrypt(good.key, good.initVector, fivetext);



                    TextView txtlife;
                    String goodfifity = decrypt(good.key, Remaininglife);
                    txtlife = findViewById(R.id.txtlife);
                    txtlife.setText(goodfifity);

                    TextView fifitytextss = (TextView)findViewById(R.id.fifitytext);
                    fifitytextss.setText(goodfifity);


                    firstobject = firsttext;
                    twoobject = secondtext;
                    threeobject = threetext;
                    fourobject = fourtext;
                    fiveobject = fivetext;
                    firstrupess = firstrs;
                    tworupess = twors;
                    threerupess = threers;
                    fourrupess = fourrs;
                    fiverupess = fivers;
                    SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref2.edit();
                    editor1.putString("Totallife", Remaininglife);
                    editor1.putString("Totalwallet", TotalWallet);
                    editor1.apply();
                    SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Totallife", Remaininglife);
                    editor.putString("Totalwallet", TotalWallet);
                    editor.apply();


//
                    SharedPreferences perper = getSharedPreferences("fifty", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = perper.edit();
                    edit.putString("firstobject",firstobject);
                    edit.putString("twoobject",twoobject);
                    edit.putString("threeobject",threeobject);
                    edit.putString("fourobject",fourobject);
                    edit.putString("fiveobject",fiveobject);

                    edit.putString("firstrupess",firstrupess);
                    edit.putString("tworupess",tworupess);
                    edit.putString("threerupess",threerupess);
                    edit.putString("fourrupess",fourrupess);
                    edit.putString("fiverupess",fiverupess);
                    edit.putString("totallife",Remaininglife);
                    edit.apply();





                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(GametypesActivity.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(GametypesActivity.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(GametypesActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(GametypesActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(GametypesActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    Toast.makeText(GametypesActivity.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<FiftyfityResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

    private void GameListAPI() {
        UserService userService = ServiceGenerator.createService(UserService.class, null, null);
        userService.getGameList().enqueue(new Callback<GetPracticeResponseJson>() {
            @Override
            public void onResponse(Call<GetPracticeResponseJson> call, Response<GetPracticeResponseJson> response) {
                if (response.isSuccessful()) {

                    if (productList.size()>0){
                        productList.clear();
                    }
                    for (int z=0; z<response.body().data.size(); z++){
                        Id = response.body().data.get(z).gameid;
                        Name = response.body().data.get(z).name;
                        Img = response.body().data.get(z).icon;
                        Url = response.body().data.get(z).gameurl;
                        layout = response.body().data.get(z).layout;

                        Id = encrypt(good.key, good.initVector, Id);
                        Name = encrypt(good.key, good.initVector, Name);
                        Img = encrypt(good.key, good.initVector, Img);
                        Url = encrypt(good.key, good.initVector, Url);
                        layout = encrypt(good.key, good.initVector, layout);

                        getbcdata = new GetDataGameType();
                        getbcdata.setId(Id);
                        getbcdata.setImg(Img);
                        getbcdata.setName(Name);
                        getbcdata.setUrl(Url);
                        getbcdata.setLayout(layout);
                        getbcdata.setTotalcontest(getIntent().getStringExtra("Totalcontest"));
                        productList.add(getbcdata);




                    }
                    if (productList.size() > 0) {

                        try {
                            adapter = new gametypeAdapter(GametypesActivity.this, productList);
                            mLayoutManager = new LinearLayoutManager(GametypesActivity.this, LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(GametypesActivity.this, "DATA Not Recived", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(GametypesActivity.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(GametypesActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(GametypesActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(GametypesActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(GametypesActivity.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<GetPracticeResponseJson> call, Throwable t) {
                Log.e("System error:", t.getLocalizedMessage());
            }
        });
    }

    //// fifity
//    public class AsyncCallWSFifty extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Dailylife, Referlife, TotalWallet, Remaininglife, firsttext, secondtext, threetext, fourtext, fivetext, firstrs, twors, threers, fourrs, fivers;
//        String Totallife;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            displayText = WebService.Selectlife(Loginid, "buyFifty");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//
//                        Remaininglife = jsonrowdata.getString("Remaininglife");
//                        TotalWallet = jsonrowdata.getString("TotalWallet");
//
//
//
//                        firstrs = jsonrowdata.getString("Firstrs");
//                        twors = jsonrowdata.getString("Secondrs");
//                        threers = jsonrowdata.getString("Threers");
//                        fourrs = jsonrowdata.getString("Fourrs");
//                        fivers = jsonrowdata.getString("Fivers");
//
//                        firsttext = jsonrowdata.getString("FirstLife");
//                        secondtext = jsonrowdata.getString("SecondLife");
//                        threetext = jsonrowdata.getString("ThreeLife");
//                        fourtext = jsonrowdata.getString("FourLife");
//                        fivetext = jsonrowdata.getString("FiveLife");
//

//
//
//                        Remaininglife = encrypt(good.key, good.initVector, Remaininglife);
//                        TotalWallet = encrypt(good.key, good.initVector, TotalWallet);
//
//
//                        firstrs = encrypt(good.key, good.initVector, firstrs);
//                        twors = encrypt(good.key, good.initVector, twors);
//                        threers = encrypt(good.key, good.initVector, threers);
//                        fourrs = encrypt(good.key, good.initVector, fourrs);
//                        fivers = encrypt(good.key, good.initVector, fivers);
//
//
//                        firsttext = encrypt(good.key, good.initVector, firsttext);
//                        secondtext = encrypt(good.key, good.initVector, secondtext);
//                        threetext = encrypt(good.key, good.initVector, threetext);
//                        fourtext = encrypt(good.key, good.initVector, fourtext);
//                        fivetext = encrypt(good.key, good.initVector, fivetext);
//

//
//
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        protected void onPostExecute(Void result) {
//            if (displayText != null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(GametypesActivity.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(GametypesActivity.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//                    if (jsonArray.length() > 0) {
//                        TextView txtlife;
//                        String goodfifity = decrypt(good.key, Remaininglife);
//                        txtlife = findViewById(R.id.txtlife);
//                        txtlife.setText(goodfifity);
//
//                        TextView fifitytextss = (TextView)findViewById(R.id.fifitytext);
//                        fifitytextss.setText(goodfifity);
//
//
//                        firstobject = firsttext;
//                        twoobject = secondtext;
//                        threeobject = threetext;
//                        fourobject = fourtext;
//                        fiveobject = fivetext;
//
//                        firstrupess = firstrs;
//                        tworupess = twors;
//                        threerupess = threers;
//                        fourrupess = fourrs;
//                        fiverupess = fivers;
//                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref2.edit();
//                        editor1.putString("Totallife", Remaininglife);
//                        editor1.putString("Totalwallet", TotalWallet);
//                        editor1.apply();
//                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.putString("Totallife", Remaininglife);
//                        editor.putString("Totalwallet", TotalWallet);
//                        editor.apply();
//
//
////
//                        SharedPreferences perper = getSharedPreferences("fifty", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor edit = perper.edit();
//                        edit.putString("firstobject",firstobject);
//                        edit.putString("twoobject",twoobject);
//                        edit.putString("threeobject",threeobject);
//                        edit.putString("fourobject",fourobject);
//                        edit.putString("fiveobject",fiveobject);
//
//                        edit.putString("firstrupess",firstrupess);
//                        edit.putString("tworupess",tworupess);
//                        edit.putString("threerupess",threerupess);
//                        edit.putString("fourrupess",fourrupess);
//                        edit.putString("fiverupess",fiverupess);
//                        edit.putString("totallife",Remaininglife);
//                        edit.apply();
//
//
//
//                    } else if (jsonArray.length() == 0) {
//                        Toast.makeText(GametypesActivity.this, "No detail found", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(GametypesActivity.this, "Try Again", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(GametypesActivity.this, "Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText, Userid, Password;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Type( "GameList");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Id = jsonrowdata.getString("gameid");/////////////////////// name
//                        Name = jsonrowdata.getString("name");///////// user
//                        Img = jsonrowdata.getString("icon");
//                        Url = jsonrowdata.getString("game_url");
//                        layout = jsonrowdata.getString("layout");
//
//                        Id = encrypt(good.key, good.initVector, Id);
//                        Name = encrypt(good.key, good.initVector, Name);
//                        Img = encrypt(good.key, good.initVector, Img);
//                        Url = encrypt(good.key, good.initVector, Url);
//                        layout = encrypt(good.key, good.initVector, layout);
//
//
//
//
//
//                        getbcdata = new GetDataGameType();
//                        getbcdata.setId(Id);
//                        getbcdata.setImg(Img);
//                        getbcdata.setName(Name);
//                        getbcdata.setUrl(Url);
//                        getbcdata.setLayout(layout);
//                        getbcdata.setTotalcontest(getIntent().getStringExtra("Totalcontest"));
//                        productList.add(getbcdata);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////                Toast.makeText(GametypesActivity.this, "api not working", Toast.LENGTH_SHORT).show();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            progressbar.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(GametypesActivity.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(GametypesActivity.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//
//
////                adapter = new gametypeAdapter(GametypesActivity.this, productList);
////                mLayoutManager = new GridLayoutManager(GametypesActivity.this, 2);
////                mRecyclerView.setLayoutManager(mLayoutManager);
////                mRecyclerView.setAdapter(adapter);
//                adapter = new gametypeAdapter(GametypesActivity.this, productList);
//                mLayoutManager = new LinearLayoutManager(GametypesActivity.this, LinearLayoutManager.VERTICAL, false);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(adapter);
//
//            } else {
//                Toast.makeText(GametypesActivity.this, "Log In Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(GametypesActivity.this,
//                    20, 60, ContextCompat.getColor(GametypesActivity.this, R.color.white));
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


    private void showPictureialog() {
        final Integer[] items = {R.drawable.sticky1,
                R.drawable.sticky2, R.drawable.sticky3, R.drawable.sticky4,
                R.drawable.sticky5, R.drawable.sticky6, R.drawable.sticky7,
                R.drawable.sticky8, R.drawable.sticky9};


        List<Integer> list = new ArrayList<Integer>(Arrays.asList(items));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.selectprofile, null);
        dialog.setView(dialogView);
        dialog.show();
        GridView grid = dialogView.findViewById(R.id.grid);
        GridViewAdapter gridAdapter = new GridViewAdapter(this, R.layout.grid_row, list);
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ImageView img = view.findViewById(R.id.ivprofile);
                String name = getResources().getResourceEntryName(items[i]);
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("drawable", name);
                editor.apply();
                dialog.dismiss();
                int imageId = getResourseId(GametypesActivity.this, name, "drawable", getPackageName());
                ivprofile.setImageResource(imageId);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (SettingsPreferences.getVibration(getApplicationContext())) {
            StaticUtils.vibrate(getApplicationContext(), StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(getApplicationContext())) {
            StaticUtils.backSoundonclick(getApplicationContext());
        }
        Intent intent = new Intent(GametypesActivity.this,Dashboard.class);
        intent.putExtra("goto", "challenger");
        intent.putExtra("Totalcontest", Totalcontestnew);

        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);

    }

    public void totallife(String wallet, String fifity){

        TextView fifitytextss = (TextView)findViewById(R.id.fifitytext);
        fifitytextss.setText(decrypt(good.key, fifity));
        tvwallet = (TextView)findViewById(R.id.txtwallet);
        tvwallet.setText(decrypt(good.key, wallet));
    }
}
