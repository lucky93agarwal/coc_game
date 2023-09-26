package com.challengers.of.call.Practice;

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
import com.challengers.of.call.BannedData.GetBannedResponseJson;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Dashboard;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.GridViewAdapter;
import com.challengers.of.call.Leaderboard;
import com.challengers.of.call.Main2Activity;
import com.challengers.of.call.Profile;
import com.challengers.of.call.R;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.WebService;
import com.challengers.of.call.adapter.PracticeAdapter;
import com.challengers.of.call.data.GetDataBannedApp;
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

public class PracticeActivity extends AppCompatActivity {
    String firstobject, twoobject, threeobject, fourobject, fiveobject;
    String firstrupess, tworupess, threerupess, fourrupess, fiverupess;
    ////// Game Chose Tab
    private static bannedapp bannedapps;

    ////// Game Chose Tab
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    //// Vibration
    public Vibrator vibrator;
    //// Vibration
    public String Totalcontestnew;

    /// it for RecyclerView
    public List<GetDataGameType> productList = new ArrayList<>();
    public GetDataGameType getbcdata;
    public PracticeAdapter adapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public String LIST_STATE_KEY = "list_state";
    /// it for RecyclerView
    public String Name, Img, Id, Url, layout;
    public Dialog unepicDialog, progressbar;
    public LinearLayout linearLayout;
    public static Context context;
    private static addmoneypopup addpopup;

    public Animation myAnim;
    public String Totalwallet, Names;
    public TextView tvwallet;
    public String Totallife;
    ImageView ivprofile;
    private ljsflsj good;

    @Override
    protected void onResume() {
        super.onResume();


        bannedapps = new bannedapp(PracticeActivity.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bannedapps = new bannedapp(PracticeActivity.this);
        bannedapps.bannedbig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        bannedapps = new bannedapp(PracticeActivity.this);
        bannedapps.bannedbig();
        context = getApplicationContext();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Loginid = prefs.getString("drawable", null);
        String Profilepic = prefs.getString("Profilepic", null);
        String Customimage = prefs.getString("Imageurl", null);






        ImageView img = findViewById(R.id.ivprofile);
        ImageView imageView = findViewById(R.id.ivprofileview);
        imageView.setVisibility(View.VISIBLE);
        ivprofile = findViewById(R.id.ivprofile);

        LinearLayout l1 = findViewById(R.id.l1);
        LinearLayout l2 = findViewById(R.id.l2);

        Loginid = decrypt(good.key, Loginid);
        Profilepic = decrypt(good.key, Profilepic);
        Customimage = decrypt(good.key, Customimage);

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




        addpopup = new addmoneypopup(PracticeActivity.this);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.buttonanimation);
        progressbar = new Dialog(this);
        SharedPreferences lucky = getSharedPreferences("coding", Context.MODE_PRIVATE);

        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Totalwallet = pref2.getString("Totalwallet", null);
        Names = pref2.getString("Name", null);
        Totalcontestnew = getIntent().getStringExtra("Totalcontest");
//        Totalwallet = getIntent().getStringExtra("Totalwallet");
//        Names = getIntent().getStringExtra("name");

        Names = decrypt(good.key, Names);

        TextView tvname = (TextView) findViewById(R.id.username);
        tvname.setText(Names);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                Intent intent2 = new Intent(PracticeActivity.this, Profile.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slideout_from_left);
                intent2.putExtra("Totalcontest", Totalcontestnew);
                startActivity(intent2);
                finish();


                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });

        tvwallet = (TextView) findViewById(R.id.txtwallet);
        Totalwallet = decrypt(good.key, Totalwallet);
        tvwallet.setText(Totalwallet);
        tvwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                addpopup.addpopup();
            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
//                vibrator.vibrate(70);
                if (SettingsPreferences.getVibration(context)) {
                    StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
                }
                if (SettingsPreferences.getSoundEnableDisable(context)) {
                    StaticUtils.backSoundonclick(context);
                }

                addpopup.addpopup();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        progressbar = new Dialog(this);
        //// Vibration
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //// Vibration
//        AsyncCallWS task = new AsyncCallWS();
//        task.execute();


        FirstApi();
        //// button click animation
        final Animation myAnim = AnimationUtils.loadAnimation(PracticeActivity.this, R.anim.buttonanimation);
        //// button click animation

        ////// Game Chose Tab




    }

    private void FirstApi() {

        UserService userService = ServiceGenerator.createService(UserService.class, null, null);
        userService.getPractice().enqueue(new Callback<GetPracticeResponseJson>() {
            @Override
            public void onResponse(Call<GetPracticeResponseJson> call, Response<GetPracticeResponseJson> response) {
                if (response.isSuccessful()) {
                    if (productList.size() > 0){
                        productList.clear();
                    }

                    for (int z=0; z<response.body().data.size(); z++){
                        Id = response.body().data.get(z).gameid;
                        Name = response.body().data.get(z).name;
                        Img = response.body().data.get(z).icon;
                        Url = response.body().data.get(z).gameurl;
                        layout = response.body().data.get(z).layout;

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
                            adapter = new PracticeAdapter(PracticeActivity.this, productList);
                            mLayoutManager = new LinearLayoutManager(PracticeActivity.this, LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(PracticeActivity.this, "DATA Not Recived", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(PracticeActivity.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(PracticeActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(PracticeActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(PracticeActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<GetPracticeResponseJson> call, Throwable t) {
                Log.e("System error:", t.getLocalizedMessage());
            }
        });
    }


//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText, Userid, Password;
//
//
//        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//        String Loginid = prefs.getString("Loginid", null);
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Type( "PracticeList");
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
//
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
//                Toast.makeText(PracticeActivity.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(PracticeActivity.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//
//
////                adapter = new PracticeAdapter(GametypesActivity.this, productList);
////                mLayoutManager = new GridLayoutManager(GametypesActivity.this, 2);
////                mRecyclerView.setLayoutManager(mLayoutManager);
////                mRecyclerView.setAdapter(adapter);
//                adapter = new PracticeAdapter(PracticeActivity.this, productList);
//                mLayoutManager = new LinearLayoutManager(PracticeActivity.this, LinearLayoutManager.VERTICAL, false);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(adapter);
//
//            } else {
//                Toast.makeText(PracticeActivity.this, "Log In Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
//            progressbar.setContentView(R.layout.progresbarlayout);
//            progressbar.setCancelable(false);
//            RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//            RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(PracticeActivity.this,
//                    20, 60, ContextCompat.getColor(PracticeActivity.this, R.color.white));
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
                int imageId = getResourseId(PracticeActivity.this, name, "drawable", getPackageName());
                ivprofile.setImageResource(imageId);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }

        Intent intent = new Intent(PracticeActivity.this, Dashboard.class);
        intent.putExtra("goto", "challenger");
        intent.putExtra("Totalcontest", Totalcontestnew);

        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slideout_from_right);

    }
}
