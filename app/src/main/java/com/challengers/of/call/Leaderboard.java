package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.Leaderboarddata.LeaderboardRequestJson;
import com.challengers.of.call.Leaderboarddata.LeaderboardResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.SocialCode.SocialOTPRequestJson;
import com.challengers.of.call.SocialCode.SocialOTPResponseJson;
import com.challengers.of.call.data.Getquestionwiseresultdata;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.adapter.Leaderboardadapter;
import com.challengers.of.call.data.Getleaderboarddata;
import com.challengers.of.call.helper.GlideCircleTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Leaderboard extends AppCompatActivity {
    public ArrayList<Getleaderboarddata> productList = new ArrayList<>();

    JSONObject jasonObject;
    private static bannedapp bannedapps;
    JSONArray jsonArray = null;
    JSONArray jsonArray1 = null;
    String NewCheck = "go", name, selfcontestname;
    Leaderboardadapter adapter;
    Getleaderboarddata getcontestdata;
    LinearLayout l1;
    ListView listView;
    //    TextView txtthisweek, txtpreviousweek;
    TextView txtselfrank, txtselfname, txtselfamount, txtname, textgood;
    String From = "C";

    TextView txttimer;
    CountDownTimer timer;

//    AsyncCallWS task;
    int Remaining = 0, image, simage;
    public KProgressHUD hud;
    public String Message;
    public static Context context;
    String topObject = "Top_Winner";
    public TextView onerank, tworank, threerank;
    public TextView tvrank1, tvrank2, tvrank3, tvrank4, tvrank5, tvrank6, tvrank7, tvrank8, tvrank9, tvrank10;
    public String rank1, rank2, rank3, rank4, rank5, rank6, rank7, rank8, rank9, rank10, totalrank;
    public String r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, total;


    public TextView tvra1, tvra2, tvra3, tvra4, tvra5, tvra6, tvra7, tvra8, tvra9, tvra10;
    public String ra1, ra2, ra3, ra4, ra5, ra6, ra7, ra8, ra9, ra10;
    public String rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10;

    public String raa1, raa2, raa3, raa4, raa5, raa6, raa7, raa8, raa9, raa10;
    public String ras1, ras2, ras3, ras4, ras5, ras6, ras7, ras8, ras9, ras10;

    String Srno, Contestname, Winningamount, selfsrno = "", Useridmain, Prize1, Prize2, Prize3, Prize4, Prize5, Prize6, Prize7, Prize8, Prize9, Prize10, selfwinningamount, Imageurl, Selfimage, Check;

    ImageView btnshowpopup;
    public TextView tvshowpopup;
    public Dialog epicDialog, unepicDialog, progressbar; //Popup Dialog Box
    public ImageView closepopupbtn;

    private ljsflsj good;

    @Override
    protected void onResume() {
        super.onResume();

        bannedapps = new bannedapp(Leaderboard.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(Leaderboard.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
//
        bannedapps = new bannedapp(Leaderboard.this);
        bannedapps.bannedbig();
        context = getApplicationContext();
//        onerank = (TextView)findViewById(R.id.firstrank);
//        tworank = (TextView)findViewById(R.id.secondrank);
//        threerank = (TextView)findViewById(R.id.thridrank);

        progressbar = new Dialog(this);

//        tvshowpopup = (TextView)findViewById(R.id.readonly);
        epicDialog = new Dialog(this, R.style.PauseDialog); // for popup Dialog
//////// show popup
//

        btnshowpopup = (ImageView) findViewById(R.id.btnreadonly);

        btnshowpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (com.challengers.of.call.testing.SettingsPreferences.getVibration(context)) {
                    com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
                }
                if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
                    com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
                }

                popupdata();

            }
        });
//        tvshowpopup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (com.challengers.of.call.testing.SettingsPreferences.getVibration(context)) {
//                    com.challengers.of.call.testing.StaticUtils.vibrate(context, com.challengers.of.call.testing.StaticUtils.VIBRATION_DURATION);
//                }
//                if (com.challengers.of.call.testing.SettingsPreferences.getSoundEnableDisable(context)) {
//                    com.challengers.of.call.testing.StaticUtils.backSoundonclick(context);
//                }
//
//                popupdata();
//
//            }
//        });


        listView = findViewById(R.id.listView);
//        txtthisweek = findViewById(R.id.txtcurrentweek);
//        txtpreviousweek = findViewById(R.id.txtpreviousweek);
        txtselfrank = findViewById(R.id.textView11);///////// your rank
        txtselfname = findViewById(R.id.textView21);//////// you name
        txtname = (TextView) findViewById(R.id.name);
        textgood = (TextView) findViewById(R.id.good);
        txtselfamount = findViewById(R.id.textView31);


        final Button btntoday = findViewById(R.id.btntoday);
        final Button btnyesterday = findViewById(R.id.btnyesterday);

        final Button btntopwinner = findViewById(R.id.btntopwinner);
        final Button btntopcreator = findViewById(R.id.btntopcreator);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView txtuserid = view.findViewById(R.id.textView4);
//                TextView txtname = view.findViewById(R.id.textView2);
//
//                Intent intent = new Intent(Leaderboard.this, Winnerdetail.class);
//                intent.putExtra("Winnerid", txtuserid.getText().toString());
//                intent.putExtra("Name", txtname.getText().toString());

//                startActivity(intent);
//            }
//        });
        if (topObject.equalsIgnoreCase("Top_Winner")) {

            textgood.setText("Contests Won");
        } else if (topObject.equalsIgnoreCase("Top_Creator")) {

            textgood.setText("Contests Created");
        }

        btntopwinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnshowpopup.setVisibility(View.GONE);
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                btnshowpopup.setVisibility(View.GONE);

                btntopwinner.setBackgroundResource(R.drawable.leaderboardbutton);
                btntopwinner.setTextColor(Color.parseColor("#FFFFFF"));


                btntoday.setBackgroundResource(R.drawable.leaderbobtn);
                btntoday.setTextColor(Color.parseColor("#FFFFFF"));


                btntopcreator.setBackgroundResource(R.drawable.leaderbutt);
                btntopcreator.setTextColor(Color.parseColor("#FFFFFF"));

                btnyesterday.setBackgroundResource(R.drawable.leaderbutt);
                btnyesterday.setTextColor(Color.parseColor("#FFFFFF"));

                From = "C";
                topObject = "Top_Winner";
                if (topObject.equalsIgnoreCase("Top_Winner")) {

                    textgood.setText("Contests Won");
                } else if (topObject.equalsIgnoreCase("Top_Creator")) {

                    textgood.setText("Contests Created");
                }
//                if (task != null)
//                    task.cancel(true);
//
//
//                task = new AsyncCallWS();
//                task.execute();
                progressbar.setContentView(R.layout.progresbarlayout);
                progressbar.setCancelable(false);
                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
                        20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
                loader.setAnimDuration(3000);

                rotatingCircularDotsLoader.addView(loader);

                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                progressbar.show();


                FirstApi();


            }
        });

        btntopcreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                btnshowpopup.setVisibility(View.VISIBLE);


                btntopcreator.setBackgroundResource(R.drawable.leaderboardbutton);
                btntopcreator.setTextColor(Color.parseColor("#FFFFFF"));

                btntoday.setBackgroundResource(R.drawable.leaderbobtn);
                btntoday.setTextColor(Color.parseColor("#FFFFFF"));


                btntopwinner.setBackgroundResource(R.drawable.leaderbutt);
                btntopwinner.setTextColor(Color.parseColor("#FFFFFF"));


                btnyesterday.setBackgroundResource(R.drawable.leaderbutt);
                btnyesterday.setTextColor(Color.parseColor("#FFFFFF"));


                topObject = "Top_Creator";
                if (topObject.equalsIgnoreCase("Top_Winner")) {

                    textgood.setText("Contests Won");
                } else if (topObject.equalsIgnoreCase("Top_Creator")) {

                    textgood.setText("Contests Created");
                }
                From = "C";
//                if (task != null)
//                    task.cancel(true);
//                task = new AsyncCallWS();
//                task.execute();
                progressbar.setContentView(R.layout.progresbarlayout);
                progressbar.setCancelable(false);
                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
                        20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
                loader.setAnimDuration(3000);

                rotatingCircularDotsLoader.addView(loader);

                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                progressbar.show();

                FirstApi();
            }
        });


        btntoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                btntoday.setBackgroundResource(R.drawable.leaderbobtn);
                btntoday.setTextColor(Color.parseColor("#FFFFFF"));


                btnyesterday.setBackgroundResource(R.drawable.leaderbutt);
                btnyesterday.setTextColor(Color.parseColor("#FFFFFF"));

                From = "C";
//                if (task != null)
//                    task.cancel(true);
//                task = new AsyncCallWS();
//                task.execute();
                progressbar.setContentView(R.layout.progresbarlayout);
                progressbar.setCancelable(false);
                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
                        20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
                loader.setAnimDuration(3000);

                rotatingCircularDotsLoader.addView(loader);

                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                progressbar.show();


                FirstApi();
            }
        });

        btnyesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                btnyesterday.setBackgroundResource(R.drawable.leaderbobtn);
                btnyesterday.setTextColor(Color.parseColor("#FFFFFF"));


                btntoday.setBackgroundResource(R.drawable.leaderbutt);
                btntoday.setTextColor(Color.parseColor("#FFFFFF"));

                From = "P";

//                if (task != null)
//                    task.cancel(true);
//                task = new AsyncCallWS();
//                task.execute();
                progressbar.setContentView(R.layout.progresbarlayout);
                progressbar.setCancelable(false);
                RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

                RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
                        20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
                loader.setAnimDuration(3000);

                rotatingCircularDotsLoader.addView(loader);

                progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                progressbar.show();


                FirstApi();
            }
        });

        ImageView ivback = findViewById(R.id.ivback);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hud != null) {

                } else {

                    Intent intent = new Intent(Leaderboard.this, Main2Activity.class);
                    intent.putExtra("goto", "challenge");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                    finish();
                }
            }
        });
//        task = new AsyncCallWS();
//        task.execute();
        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
                20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();


        FirstApi();

    }


    private void popupdata() {

        epicDialog.setContentView(R.layout.leaderboardpopup);
        epicDialog.setCancelable(false);

        if (topObject.equalsIgnoreCase("Top_Winner")) {
            TextView textnames = (TextView) epicDialog.findViewById(R.id.textname);
            textnames.setText("Won Contests");
        } else if (topObject.equalsIgnoreCase("Top_Creator")) {
            TextView textnames = (TextView) epicDialog.findViewById(R.id.textname);
            textnames.setText("Created Contests");
        }

        // popup show
        closepopupbtn = (ImageView) epicDialog.findViewById(R.id.closePopup);// popup Close button

        closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
            @Override
            public void onClick(View v) {

                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }
                epicDialog.dismiss();
            }
        });
        tvrank1 = (TextView) epicDialog.findViewById(R.id.tvrank1);
        tvrank1.setText("₹ " + r1);
        tvrank2 = (TextView) epicDialog.findViewById(R.id.tvrank2);
        tvrank2.setText("₹ " + r2);
        tvrank3 = (TextView) epicDialog.findViewById(R.id.tvrank3);
        tvrank3.setText("₹ " + r3);
        tvrank4 = (TextView) epicDialog.findViewById(R.id.tvrank4);
        tvrank4.setText("₹ " + r4);
        tvrank5 = (TextView) epicDialog.findViewById(R.id.tvrank5);
        tvrank5.setText("₹ " + r5);
        tvrank6 = (TextView) epicDialog.findViewById(R.id.tvrank6);
        tvrank6.setText("₹ " + r6);
        tvrank7 = (TextView) epicDialog.findViewById(R.id.tvrank7);
        tvrank7.setText("₹ " + r7);

        tvrank8 = (TextView) epicDialog.findViewById(R.id.tvrank8);
        tvrank8.setText("₹ " + r8);
        tvrank9 = (TextView) epicDialog.findViewById(R.id.tvrank9);
        tvrank9.setText("₹ " + r9);
        tvrank10 = (TextView) epicDialog.findViewById(R.id.tvrank10);
        tvrank10.setText("₹ " + r10);


        tvra1 = (TextView) epicDialog.findViewById(R.id.tvra1);
        tvra1.setText(rs1 + " - " + ras1);
        tvra2 = (TextView) epicDialog.findViewById(R.id.tvra2);
        tvra2.setText(rs2 + " - " + ras2);
        tvra3 = (TextView) epicDialog.findViewById(R.id.tvra3);
        tvra3.setText(rs3 + " - " + ras3);
        tvra4 = (TextView) epicDialog.findViewById(R.id.tvra4);
        tvra4.setText(rs4 + " - " + ras4);
        tvra5 = (TextView) epicDialog.findViewById(R.id.tvra5);
        tvra5.setText(rs5 + " - " + ras5);
        tvra6 = (TextView) epicDialog.findViewById(R.id.tvra6);
        tvra6.setText(rs6 + " - " + ras6);
        tvra7 = (TextView) epicDialog.findViewById(R.id.tvra7);
        tvra7.setText(rs7 + " - " + ras7);

        tvra8 = (TextView) epicDialog.findViewById(R.id.tvra8);
        tvra8.setText(rs8 + " - " + ras8);
        tvra9 = (TextView) epicDialog.findViewById(R.id.tvra9);
        tvra9.setText(rs9 + " - " + ras9);
        tvra10 = (TextView) epicDialog.findViewById(R.id.tvra10);
        tvra10.setText(rs10 + " - " + ras10);


        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        epicDialog.show();
    }

    @Override
    public void onBackPressed() {


        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }


        Intent intent = new Intent(Leaderboard.this, Dashboard.class);
//        intent.putExtra("goto", "challenge");
//
//
//        intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }

    private void Counter(int timertime) {
        timer = new CountDownTimer(timertime, 1000) {
            public void onTick(long millisUntilFinished) {
                //Toast.makeText(Leaderboard.this,"Counter is running",Toast.LENGTH_LONG).show();
                String minute, hour, day;
//                int seconds = (int) ((millisUntilFinished / (1000)) % 60);
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                int days = (int) (millisUntilFinished / ((1000 * 60 * 60 * 24)) % 7);
                day = Integer.toString(days);
                hour = Integer.toString(hours);
                minute = Integer.toString(minutes);
//                second = Integer.toString(seconds);

                if (days < 10) {
                    String a = "0" + days;
                    day = a;
                }
                if (hours < 10) {
                    String a = "0" + hours;
                    hour = a;
                }
                if (minutes < 10) {
                    String a = "0" + minutes;
                    minute = a;
                }
//                if (seconds < 10){
//                    String a = "0" + seconds;
//                    second = a;
//                }
                txttimer.setText(days + " d " + hours + " h " + minutes + " m ");
            }

            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void FirstApi() {
        adapter = new Leaderboardadapter(Leaderboard.this, R.layout.list_leaderboard_row);
        adapter.notifyDataSetChanged();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("Loginid", null);
        String Usernames = prefs.getString("Name", null);
        String Password = prefs.getString("Password", null);

        Loginid = decrypt(good.key, Loginid);

        final LeaderboardRequestJson request = new LeaderboardRequestJson();
        request.setFrom(From);
        request.setTopObject(topObject);
        request.setLoginid(Loginid);


        UserService service = ServiceGenerator.createService(UserService.class,decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.leaderboard(request).enqueue(new Callback<LeaderboardResponseJson>() {
            @Override
            public void onResponse(Call<LeaderboardResponseJson> call, Response<LeaderboardResponseJson> response) {
                if (response.isSuccessful()) {
                    if (productList.size() > 0){
                        productList.clear();
                    }
                    int count = 1;
                    Message = response.body().message;
                    for (int i = 0; i < response.body().data.size(); i++) {

                        Srno = response.body().data.get(i).sno;
                        Contestname = response.body().data.get(i).contestname;
                        Winningamount = response.body().data.get(i).winningamount;
                        Useridmain = response.body().data.get(i).userid;
                        Imageurl = response.body().data.get(i).imageurl;
                        Check = response.body().data.get(i).check;
                        Prize1 = response.body().data.get(i).prize;


                        Contestname = encrypt(good.key, good.initVector, Contestname);
                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
                        Useridmain = encrypt(good.key, good.initVector, Useridmain);
                        Imageurl = encrypt(good.key, good.initVector, Imageurl);
                        Check = encrypt(good.key, good.initVector, Check);
                        Prize1 = encrypt(good.key, good.initVector, Prize1);

                        String myString = null;
                        if (Imageurl == null || Imageurl.isEmpty() || Imageurl.equals(myString)) {
                            image = 0;
                            Imageurl = "null";
                        } else {
                            image = Imageurl.length();
                        }
//                        image = Imageurl.length();

                        if (Srno.equalsIgnoreCase("not")) {
                            break;
                        } else {

                            getcontestdata = new Getleaderboarddata();
                            getcontestdata.setSno(Srno);
                            getcontestdata.setContestname(Contestname);
                            getcontestdata.setPoints(Winningamount);
                            getcontestdata.setUserid(Useridmain);
                            getcontestdata.setImageurl(Imageurl);
                            getcontestdata.setImage(image);
                            getcontestdata.setFirstprize(Prize1);


                            if (topObject.equalsIgnoreCase("Top_Winner")) {

                                getcontestdata.setNaja("Contests Won");

                            } else if (topObject.equalsIgnoreCase("Top_Creator")) {

                                getcontestdata.setNaja("Contests Created");

                            }

//
                            if (Integer.valueOf(Srno) == 1) {
                                getcontestdata.setPrize(1);
                            } else if (Integer.valueOf(Srno) == 2) {
                                getcontestdata.setPrize(2);

                            } else if (Integer.valueOf(Srno) == 3) {

                                getcontestdata.setPrize(3);

                            } else if (Integer.valueOf(Srno) == 4) {

                                getcontestdata.setPrize(4);

                            } else if (Integer.valueOf(Srno) == 5) {

                                getcontestdata.setPrize(5);

                            } else if (Integer.valueOf(Srno) == 6) {

                                getcontestdata.setPrize(6);

                            } else if (Integer.valueOf(Srno) == 7) {

                                getcontestdata.setPrize(7);

                            } else if (Integer.valueOf(Srno) == 8) {

                                getcontestdata.setPrize(8);

                            } else if (Integer.valueOf(Srno) == 9) {

                                getcontestdata.setPrize(9);

                            } else if (Integer.valueOf(Srno) == 10) {

                                getcontestdata.setPrize(10);

                            } else {
                                getcontestdata.setPrize(0);
                            }


                            adapter.add(getcontestdata);
                            count++;
                        }

                    }
                    for (int i = 0; i < response.body().Leaderdata.size(); i++) {

                        selfsrno = response.body().Leaderdata.get(i).srno;
                        selfcontestname = response.body().Leaderdata.get(i).contestname;
                        selfwinningamount = response.body().Leaderdata.get(i).winningamount;
                        name = response.body().Leaderdata.get(i).selfwon;
                        Remaining = Integer.valueOf(response.body().Leaderdata.get(i).remainingtime);
                        Selfimage = response.body().Leaderdata.get(i).imageurl;
//                        Selfimage = jsonrowdata.getString("Imageurl");

                        String myString = null;
                        if (Selfimage == null || Selfimage.isEmpty() || Selfimage.equals(myString)) {
                            simage = 0;
                            Selfimage = "null";
                        } else {
                            simage = Selfimage.length();
                        }



                        selfsrno = encrypt(good.key, good.initVector, selfsrno);
                        selfcontestname = encrypt(good.key, good.initVector, selfcontestname);
                        selfwinningamount = encrypt(good.key, good.initVector, selfwinningamount);
                        name = encrypt(good.key, good.initVector, name);
                        Selfimage = encrypt(good.key, good.initVector, Selfimage);


                        rank1 = response.body().Leaderdata.get(i).rank1;
                        rank2 = response.body().Leaderdata.get(i).rank2;
                        rank3 = response.body().Leaderdata.get(i).rank3;
                        rank4 = response.body().Leaderdata.get(i).rank4;
                        rank5 = response.body().Leaderdata.get(i).rank5;
                        rank6 = response.body().Leaderdata.get(i).rank6;
                        rank7 = response.body().Leaderdata.get(i).rank7;
                        rank8 = response.body().Leaderdata.get(i).rank8;
                        rank9 = response.body().Leaderdata.get(i).rank9;
                        rank10 = response.body().Leaderdata.get(i).rank10;
                        totalrank = response.body().Leaderdata.get(i).totalprice;


                        ra1 = response.body().Leaderdata.get(i).r1;
                        ra2 = response.body().Leaderdata.get(i).r2;
                        ra3 = response.body().Leaderdata.get(i).r3;
                        ra4 = response.body().Leaderdata.get(i).r4;
                        ra5 = response.body().Leaderdata.get(i).r5;
                        ra6 = response.body().Leaderdata.get(i).r6;
                        ra7 = response.body().Leaderdata.get(i).r7;
                        ra8 = response.body().Leaderdata.get(i).r8;
                        ra9 = response.body().Leaderdata.get(i).r9;
                        ra10 = response.body().Leaderdata.get(i).r10;

                        raa1 = response.body().Leaderdata.get(i).ra1;
                        raa2 = response.body().Leaderdata.get(i).ra2;
                        raa3 = response.body().Leaderdata.get(i).ra3;
                        raa4 = response.body().Leaderdata.get(i).ra4;
                        raa5 = response.body().Leaderdata.get(i).ra5;
                        raa6 = response.body().Leaderdata.get(i).ra6;
                        raa7 = response.body().Leaderdata.get(i).ra7;
                        raa8 = response.body().Leaderdata.get(i).ra8;
                        raa9 = response.body().Leaderdata.get(i).ra9;
                        raa10 = response.body().Leaderdata.get(i).ra10;


                    }
                    ImageView ivselfusr = findViewById(R.id.ivselfuser);

                    r1 = rank1;
                    r2 = rank2;
                    r3 = rank3;
                    r4 = rank4;
                    r5 = rank5;
                    r6 = rank6;
                    r7 = rank7;
                    r8 = rank8;
                    r9 = rank9;
                    r10 = rank10;

//
                    rs1 = ra1;
                    rs2 = ra2;
                    rs3 = ra3;
                    rs4 = ra4;
                    rs5 = ra5;
                    rs6 = ra6;
                    rs7 = ra7;
                    rs8 = ra8;
                    rs9 = ra9;
                    rs10 = ra10;

                    ras1 = raa1;
                    ras2 = raa2;
                    ras3 = raa3;
                    ras4 = raa4;
                    ras5 = raa5;
                    ras6 = raa6;
                    ras7 = raa7;
                    ras8 = raa8;
                    ras9 = raa9;
                    ras10 = raa10;

                    total = totalrank;



                    selfsrno = decrypt(good.key, selfsrno);
                    selfcontestname = decrypt(good.key, selfcontestname);
                    selfwinningamount = decrypt(good.key, selfwinningamount);
                    name = decrypt(good.key, name);
                    Selfimage = decrypt(good.key, Selfimage);
                    if (selfsrno != "" && selfsrno != null && !selfsrno.isEmpty()) {
                        LinearLayout cardView = (LinearLayout) findViewById(R.id.c1);
                        cardView.setVisibility(View.VISIBLE);
                        txtselfrank.setText(selfsrno);
                        txtselfname.setText(selfcontestname);
                        txtname.setText(selfwinningamount);
                        txtselfamount.setText("₹ " + name);
                        if (simage < 20) {
                            if (Selfimage != null && Selfimage != "") {
                                Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Selfimage)
                                        .crossFade()
                                        .placeholder(R.drawable.logo)
                                        .error(R.drawable.logo)
                                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .skipMemoryCache(false)
                                        .into(ivselfusr);
                            }
                        } else {
                            Glide.with(getApplicationContext()).load(Selfimage)
                                    .crossFade()
                                    .placeholder(R.drawable.logo)
                                    .error(R.drawable.logo)
                                    .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .skipMemoryCache(false)
                                    .into(ivselfusr);
                        }
                    } else {
                        LinearLayout cardView = findViewById(R.id.c1);
                        cardView.setVisibility(View.GONE);
                    }
                    txttimer = findViewById(R.id.txttimer);
                    Counter(Remaining);
                    LayoutInflater inflater = getLayoutInflater();
                    ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_leaderboard_header, listView, false);
                    if (listView.getHeaderViewsCount() > 0) {
                    } else {
                    }
                    listView.setAdapter(adapter);


//                    if (productList.size()==0){
//                        Toast.makeText(Leaderboard.this, "No detail found", Toast.LENGTH_LONG).show();
//                        if (listView.getHeaderViewsCount() > 0) {
//                            LayoutInflater inflaters = getLayoutInflater();
//                            ViewGroup headers = (ViewGroup) inflater.inflate(R.layout.list_leaderboard_header, listView, false);
//                            listView.removeHeaderView(headers);
//                        }
//                        LinearLayout cardView = findViewById(R.id.c1);
//                        cardView.setVisibility(View.GONE);
//                        listView.setAdapter(null);
//                    }



                } else {
                    switch (response.code()) {
                        case 401:
//                            Toast.makeText(Leaderboard.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
//                            Toast.makeText(Leaderboard.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
//                            Toast.makeText(Leaderboard.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
//                            Toast.makeText(Leaderboard.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
//                            Toast.makeText(Leaderboard.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Toast.makeText(Leaderboard.this, "Something error please try again", Toast.LENGTH_SHORT).show();

                }
                progressbar.dismiss();
            }

            @Override
            public void onFailure(Call<LeaderboardResponseJson> call, Throwable t) {
                t.printStackTrace();


                Log.e("System error:", t.getLocalizedMessage());

            }
        });
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText, Useridmain;
//        String Srno, Contestname, Winningamount, selfsrno = "", Prize1, Prize2, Prize3, Prize4, Prize5, Prize6, Prize7, Prize8, Prize9, Prize10, selfwinningamount, Imageurl, Selfimage, Check;
//        String NewCheck = "go", name, selfcontestname;
//        int Remaining = 0, image, simage;
//        private KProgressHUD hud;
//        public String Rank1, Rank2, Rank3, Rank4, Rank5, Rank6, Rank7, Rank8, Rank9, Rank10;
//
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//            String Loginid = prefs.getString("Loginid", null);
//            if (Loginid != null) {
//                displayText = WebService.Selectleaderboard(Loginid, topObject, From, "LeaderboardData");
//            }
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    int count = 1;
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//
//                        Srno = jsonrowdata.getString("Sno");
//                        Contestname = jsonrowdata.getString("Contestname");
//                        Winningamount = jsonrowdata.getString("Winningamount");
//
//                        Useridmain = jsonrowdata.getString("Userid");
//                        Imageurl = jsonrowdata.getString("Imageurl");
//                        Check = jsonrowdata.getString("Check");
//                        Prize1 = jsonrowdata.getString("prize");
//

//
//                        Contestname = encrypt(good.key, good.initVector, Contestname);
//                        Winningamount = encrypt(good.key, good.initVector, Winningamount);
//                        Useridmain = encrypt(good.key, good.initVector, Useridmain);
//                        Imageurl = encrypt(good.key, good.initVector, Imageurl);
//                        Check = encrypt(good.key, good.initVector, Check);
//                        Prize1 = encrypt(good.key, good.initVector, Prize1);
//
//

////
////                        Prize7 = jsonrowdata.getString("prize7");
////                        Prize8 = jsonrowdata.getString("prize8");
////                        Prize9 = jsonrowdata.getString("prize9");
////
////                        Prize10 = jsonrowdata.getString("prize10");
//
//
//                        image = Imageurl.length();
//
//                        if (Srno.equalsIgnoreCase("not")) {
//                            break;
//                        } else {
//
//                            getcontestdata = new Getleaderboarddata();
//                            getcontestdata.setSno(Srno);
//                            getcontestdata.setContestname(Contestname);
//                            getcontestdata.setPoints(Winningamount);
//                            getcontestdata.setUserid(Useridmain);
//                            getcontestdata.setImageurl(Imageurl);
//                            getcontestdata.setImage(image);
//                            getcontestdata.setFirstprize(Prize1);
////                            getcontestdata.setSecondprize(Prize2);
////                            getcontestdata.setThirdprize(Prize3);
////
////                            getcontestdata.setFourprize(Prize4);
////                            getcontestdata.setFiveprize(Prize5);
////                            getcontestdata.setSixprize(Prize6);
////                            getcontestdata.setSevenprize(Prize7);
//
//
//                            if (topObject.equalsIgnoreCase("Top_Winner")) {
//
//                                getcontestdata.setNaja("Contests Won");
////                                textgood.setText("Contests Won");
//                            } else if (topObject.equalsIgnoreCase("Top_Creator")) {
//
//                                getcontestdata.setNaja("Contests Created");
////                                textgood.setText("Contests Created");
//                            }
////                            getcontestdata.setErthprize(Prize8);
////                            getcontestdata.setNineprize(Prize9);
////                            getcontestdata.setTenprize(Prize10);
//
//
////                            getcontestdata.setRankone(Rank1);
////                            getcontestdata.setRanktwo(Rank2);
////                            getcontestdata.setRankthree(Rank3);
////                            getcontestdata.setRankfour(Rank4);
////                            getcontestdata.setRankfive(Rank5);
////                            getcontestdata.setRanksix(Rank6);
////
////                            getcontestdata.setRankseven(Rank7);
////                            getcontestdata.setRankearth(Rank8);
////                            getcontestdata.setRanknine(Rank9);
////                            getcontestdata.setRankten(Rank10);
//
//
////                            if (Loginid.equals(Useridmain)){
////                                getcontestdata.setColor(1);
////                            }
////
////
//                            if (Integer.valueOf(Srno) == 1) {
//                                getcontestdata.setPrize(1);
//
//                            } else if (Integer.valueOf(Srno) == 2) {
//
//                                getcontestdata.setPrize(2);
//
//                            } else if (Integer.valueOf(Srno) == 3) {
//
//                                getcontestdata.setPrize(3);
//
//                            } else if (Integer.valueOf(Srno) == 4) {
//
//                                getcontestdata.setPrize(4);
//
//                            } else if (Integer.valueOf(Srno) == 5) {
//
//                                getcontestdata.setPrize(5);
//
//                            } else if (Integer.valueOf(Srno) == 6) {
//
//                                getcontestdata.setPrize(6);
//
//                            } else if (Integer.valueOf(Srno) == 7) {
//
//                                getcontestdata.setPrize(7);
//
//                            } else if (Integer.valueOf(Srno) == 8) {
//
//                                getcontestdata.setPrize(8);
//
//                            } else if (Integer.valueOf(Srno) == 9) {
//
//                                getcontestdata.setPrize(9);
//
//                            } else if (Integer.valueOf(Srno) == 10) {
//
//                                getcontestdata.setPrize(10);
//
//                            } else {
//                                getcontestdata.setPrize(0);
//                            }
////
////
////                            if (Integer.valueOf(Srno) <= 3) {
////                                getcontestdata.setColor(1);
////                            } else {
////                                getcontestdata.setColor(0);
////                            }
//
//
//                            adapter.add(getcontestdata);
//                            count++;
//                        }
//
//
//                    }
//
//                    // accesssing self data
//
//                    jsonArray1 = jasonObject.getJSONArray("Leaderdata");
//
//                    for (int i = 0; i < jsonArray1.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray1.getJSONObject(i);
//                        selfsrno = jsonrowdata.getString("Srno");
//                        selfcontestname = jsonrowdata.getString("Contestname");
//                        selfwinningamount = jsonrowdata.getString("Winningamount");
//                        name = jsonrowdata.getString("selfwon");
//                        Remaining = Integer.valueOf(jsonrowdata.getString("Remainingtime"));
//                        Selfimage = jsonrowdata.getString("Imageurl");
//                        simage = Selfimage.length();
//

//
//
//                        selfsrno = encrypt(good.key, good.initVector, selfsrno);
//                        selfcontestname = encrypt(good.key, good.initVector, selfcontestname);
//                        selfwinningamount = encrypt(good.key, good.initVector, selfwinningamount);
//                        name = encrypt(good.key, good.initVector, name);
//                        Selfimage = encrypt(good.key, good.initVector, Selfimage);
//

//
//
//                        //// price
//                        rank1 = jsonrowdata.getString("rank1");
//                        rank2 = jsonrowdata.getString("rank2");
//                        rank3 = jsonrowdata.getString("rank3");
//                        rank4 = jsonrowdata.getString("rank4");
//                        rank5 = jsonrowdata.getString("rank5");
//                        rank6 = jsonrowdata.getString("rank6");
//                        rank7 = jsonrowdata.getString("rank7");
//                        rank8 = jsonrowdata.getString("rank8");
//                        rank9 = jsonrowdata.getString("rank9");
//                        rank10 = jsonrowdata.getString("rank10");
//                        totalrank = jsonrowdata.getString("totalprice");
//
//
//                        ra1 = jsonrowdata.getString("r1");
//                        ra2 = jsonrowdata.getString("r2");
//                        ra3 = jsonrowdata.getString("r3");
//                        ra4 = jsonrowdata.getString("r4");
//                        ra5 = jsonrowdata.getString("r5");
//                        ra6 = jsonrowdata.getString("r6");
//                        ra7 = jsonrowdata.getString("r7");
//                        ra8 = jsonrowdata.getString("r8");
//                        ra9 = jsonrowdata.getString("r9");
//                        ra10 = jsonrowdata.getString("r10");
//
//
//                        raa1 = jsonrowdata.getString("ra1");
//                        raa2 = jsonrowdata.getString("ra2");
//                        raa3 = jsonrowdata.getString("ra3");
//                        raa4 = jsonrowdata.getString("ra4");
//                        raa5 = jsonrowdata.getString("ra5");
//                        raa6 = jsonrowdata.getString("ra6");
//                        raa7 = jsonrowdata.getString("ra7");
//                        raa8 = jsonrowdata.getString("ra8");
//                        raa9 = jsonrowdata.getString("ra9");
//                        raa10 = jsonrowdata.getString("ra10");
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
////            hud.dismiss();
//            progressbar.dismiss();
//            if (displayText != null) {
//                if (displayText.equalsIgnoreCase("connection fault")) {
//                    Toast.makeText(Leaderboard.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                    Toast.makeText(Leaderboard.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//                } else if (jsonArray != null) {
//                    if (jsonArray.length() > 0) {
//                        ImageView ivselfusr = findViewById(R.id.ivselfuser);
//
////                        onerank.setText("RANK 1 - ₹" + firstrank);
////
////
////                        tworank.setText("RANK 2 - ₹" + secondrank);
////
////                        threerank.setText("RANK 3 - ₹" + thirdrank);
//
//                        ///// price
//                        r1 = rank1;
//                        r2 = rank2;
//                        r3 = rank3;
//                        r4 = rank4;
//                        r5 = rank5;
//                        r6 = rank6;
//                        r7 = rank7;
//                        r8 = rank8;
//                        r9 = rank9;
//                        r10 = rank10;
//
////
//                        rs1 = ra1;
//                        rs2 = ra2;
//                        rs3 = ra3;
//                        rs4 = ra4;
//                        rs5 = ra5;
//                        rs6 = ra6;
//                        rs7 = ra7;
//                        rs8 = ra8;
//                        rs9 = ra9;
//                        rs10 = ra10;
//
//                        ras1 = raa1;
//                        ras2 = raa2;
//                        ras3 = raa3;
//                        ras4 = raa4;
//                        ras5 = raa5;
//                        ras6 = raa6;
//                        ras7 = raa7;
//                        ras8 = raa8;
//                        ras9 = raa9;
//                        ras10 = raa10;
//
//                        total = totalrank;
//
////                        tvshowpopup = (TextView)findViewById(R.id.readonly);
////                        tvshowpopup.setText("TOTAL WINNINGS: ₹ "+total);
//
//
////                        if (Check.equalsIgnoreCase(NewCheck)){
////                            AsyncCallWSdistribute task = new AsyncCallWSdistribute();
////                            task.execute();
////                        }
//
//                        selfsrno = decrypt(good.key, selfsrno);
//                        selfcontestname = decrypt(good.key, selfcontestname);
//                        selfwinningamount = decrypt(good.key, selfwinningamount);
//                        name = decrypt(good.key, name);
//                        Selfimage = decrypt(good.key, Selfimage);
//                        if (selfsrno != "" && selfsrno != null && !selfsrno.isEmpty()) {
//                            LinearLayout cardView = (LinearLayout) findViewById(R.id.c1);
//                            cardView.setVisibility(View.VISIBLE);
//                            txtselfrank.setText(selfsrno);
//                            txtselfname.setText(selfcontestname);
//                            txtname.setText(selfwinningamount);
//                            txtselfamount.setText("₹ " + name);
//                            if (simage < 20) {
//                                if (Selfimage != null && Selfimage != "") {
//                                    Glide.with(getApplicationContext()).load("http://callofchallengers.com/coc/images/users/" + Selfimage)
//                                            .crossFade()
//                                            .placeholder(R.drawable.logo)
//                                            .error(R.drawable.logo)
//                                            .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
//                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                            .skipMemoryCache(false)
//                                            .into(ivselfusr);
//                                }
//                            } else {
//                                Glide.with(getApplicationContext()).load(Selfimage)
//                                        .crossFade()
//                                        .placeholder(R.drawable.logo)
//                                        .error(R.drawable.logo)
//                                        .bitmapTransform(new GlideCircleTransformation(getApplicationContext()))
//                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                        .skipMemoryCache(false)
//                                        .into(ivselfusr);
//                            }
//                        } else {
//                            LinearLayout cardView = findViewById(R.id.c1);
//                            cardView.setVisibility(View.GONE);
//                        }
//                        txttimer = findViewById(R.id.txttimer);
//                        Counter(Remaining);
//                        LayoutInflater inflater = getLayoutInflater();
//                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_leaderboard_header, listView, false);
//                        if (listView.getHeaderViewsCount() > 0) {
//                        } else {
//                        }
//                        listView.setAdapter(adapter);
//                    } else if (jsonArray.length() == 0) {
//                        Toast.makeText(Leaderboard.this, "No detail found", Toast.LENGTH_LONG).show();
//                        if (listView.getHeaderViewsCount() > 0) {
//                            LayoutInflater inflater = getLayoutInflater();
//                            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_leaderboard_header, listView, false);
//                            listView.removeHeaderView(header);
//                        }
//                        CardView cardView = findViewById(R.id.c1);
//                        cardView.setVisibility(View.GONE);
//                        listView.setAdapter(null);
//                    }
//                } else {
//                    Toast.makeText(Leaderboard.this, "Please try again", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(Leaderboard.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            adapter = new Leaderboardadapter(Leaderboard.this, R.layout.list_leaderboard_row);
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader) progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Leaderboard.this,
//                    20, 60, ContextCompat.getColor(Leaderboard.this, R.color.white));
//            loader.setAnimDuration(3000);
//
//            rotatingCircularDotsLoader.addView(loader);
//
//            progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//            progressbar.show();
////            hud = KProgressHUD.create(Leaderboard.this)
////                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
////                    .setWindowColor(Color.parseColor("#c20e14"))
////                    .setLabel("")
////                    .setAnimationSpeed(1);
////            hud.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//
//        @Override
//        protected void onCancelled() {
//            task.cancel(true);
//            super.onCancelled();
//        }
//    }


}
