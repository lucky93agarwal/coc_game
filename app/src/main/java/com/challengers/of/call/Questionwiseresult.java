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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Cocquiz.AnswerRequestJson;
import com.challengers.of.call.Cocquiz.AnswerResponseJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.QuestionWisResult.QuestionWisResultRequestJson;
import com.challengers.of.call.QuestionWisResult.QuestionWisResultResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.adapter.ListAdapter;
import com.challengers.of.call.data.Getquestionwiseresultdata;
import com.challengers.of.call.my_contest.MyContestActivity;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Questionwiseresult extends AppCompatActivity {
    public List<Getquestionwiseresultdata> productList = new ArrayList<>();
    public Getquestionwiseresultdata getquestiondata1;
    public double Amount = 0;
    ListAdapter adapter;
    ListView listView;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Contestid = "", Loginid = "", TotalFifty;
    private String From = "";
//    AsyncCallWS task;
    String Questionno, Answer, Point, Time, Timenew, Totalpoint, Totalfifty;
    public static Context context;
    TextView textViewnew;
//    private InterstitialAd mInterstitialAd;
    private ljsflsj good;
    private static bannedapp bannedapps;
    public Dialog progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionwiseresult);

        progressbar = new Dialog(this);
        bannedapps = new bannedapp(Questionwiseresult.this);
        bannedapps.bannedbig();
        context = getApplicationContext();

        SharedPreferences shear = getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shear.edit();
        edit.putString("fifty", "true");
        edit.apply();

//        MobileAds.initialize(this, "ca-app-pub-7732542218948500~9599487454");
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-7732542218948500/2241658021");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
//
//                }
//            }
//
//
//        });
//        Toast.makeText(this, "after 1 min result will show ", Toast.LENGTH_LONG).show();


        Loginid = getIntent().getStringExtra("Loginid");
        Contestid = getIntent().getStringExtra("Contestid");
//        TotalFifty = getIntent().getStringExtra("TotalFifty");
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        TotalFifty = prefs.getString("Totallife", null);


        From = getIntent().getStringExtra("From");
        listView = findViewById(R.id.listView);
        ImageView ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (task != null) {
//                    if (task.getStatus() == AsyncTask.Status.RUNNING) {
//                        task.cancel(true);
//                    }
//                }

                if (From.equalsIgnoreCase("Contest")) {
                    Intent intent = new Intent(Questionwiseresult.this, Main2Activity.class);
                    intent.putExtra("goto", "Mycontest");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Questionwiseresult.this, Main2Activity.class);
                    intent.putExtra("goto", "Result");
                    intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
                    startActivity(intent);
                }
                finish();
            }
        });
//        task = new AsyncCallWS();
//        task.execute();

        progressbar.setContentView(R.layout.progresbarlayout);
        progressbar.setCancelable(false);
        RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

        RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Questionwiseresult.this,
                20, 60, ContextCompat.getColor(Questionwiseresult.this, R.color.white));
        loader.setAnimDuration(3000);

        rotatingCircularDotsLoader.addView(loader);

        progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
        progressbar.show();


        GetData();
    }


    @Override
    public void onBackPressed() {

        if (SettingsPreferences.getVibration(context)) {
            StaticUtils.vibrate(context, StaticUtils.VIBRATION_DURATION);
        }
        if (SettingsPreferences.getSoundEnableDisable(context)) {
            StaticUtils.backSoundonclick(context);
        }

        if (From.equalsIgnoreCase("Contest")) {
            Intent intent = new Intent(Questionwiseresult.this, MyContestActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(Questionwiseresult.this, Main2Activity.class);
            intent.putExtra("goto", "Result");
            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);
        }
        finish();


    }

    private void GetData() {
        adapter = new ListAdapter(Questionwiseresult.this, R.layout.list_questionwise_result_row);
        adapter.notifyDataSetChanged();
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String Password = prefs.getString("Password", null);
        String Usernames = prefs.getString("Name", null);
        String Loginids = prefs.getString("Loginid", null);
        String TotalFifty = prefs.getString("Totallife", null);
        final QuestionWisResultRequestJson request = new QuestionWisResultRequestJson();

        request.setContestid(decrypt(good.key, Contestid));
        request.setLoginid(decrypt(good.key, Loginids));
        request.setTotalFifty(decrypt(good.key, TotalFifty));



        UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
        service.resultsheet(request).enqueue(new Callback<QuestionWisResultResponseJson>() {
            @Override
            public void onResponse(Call<QuestionWisResultResponseJson> call, Response<QuestionWisResultResponseJson> response) {
                if (response.isSuccessful()) {
                    if (productList.size() > 0){
                        productList.clear();
                    }


                    for (int z=0; z<response.body().data.size(); z++){

                        Questionno = response.body().data.get(z).Questionno;
                        Answer = response.body().data.get(z).Answer;
                        Point = response.body().data.get(z).Points;
                        Time = response.body().data.get(z).Time;
                        Timenew = response.body().data.get(z).key;
                        Totalpoint = response.body().data.get(z).Totalpoint;
                        Totalfifty = response.body().data.get(z).TotalFifty;




                        try {
                            Amount = Amount + Double.parseDouble(Totalpoint);

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        Questionno = encrypt(good.key, good.initVector, Questionno);
                        Answer = encrypt(good.key, good.initVector, Answer);
                        Point = encrypt(good.key, good.initVector, Point);
                        Time = encrypt(good.key, good.initVector, Time);

                        Totalpoint = encrypt(good.key, good.initVector, Totalpoint);
                        Totalfifty = encrypt(good.key, good.initVector, Totalfifty);

                        getquestiondata1 = new Getquestionwiseresultdata();
                        getquestiondata1.setQuestionno(Questionno);
                        getquestiondata1.setAnswer(Answer);
                        getquestiondata1.setPoints(Point);
                        getquestiondata1.setTime(Time);
                        getquestiondata1.setTotalpoints(Totalpoint);
                        adapter.add(getquestiondata1);
                    }


                    if (response.body().data.size() > 0){
                        LayoutInflater inflater = getLayoutInflater();
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totallife", Totalfifty);
                        editor1.apply();
                        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Totallife", Totalfifty);
                        editor.apply();

                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_questionwise_result_header, listView, false);
                        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.list_questionwise_result_footer, listView, false);

                        TextView textView = footer.findViewById(R.id.textView);

                        textView.setText("Total: " + Amount);


                        listView.addHeaderView(header);
                        listView.addFooterView(footer);


                        if (Timenew.equalsIgnoreCase("Creator")) {
//                    textViewnew.setVisibility(View.GONE);

                        } else {
//                    textViewnew.setVisibility(View.VISIBLE);

                            ViewGroup footernew = (ViewGroup) inflater.inflate(R.layout.list_questionowise_result_textview, listView, false);
                            listView.addFooterView(footernew);

                        }
                        listView.setAdapter(adapter);

                    }else {
                        Toast.makeText(Questionwiseresult.this, "Please try again", Toast.LENGTH_LONG).show();
                    }


                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(Questionwiseresult.this, "email and pass not check", Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(Questionwiseresult.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(Questionwiseresult.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(Questionwiseresult.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Questionwiseresult.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;

                    }

                }
                progressbar.dismiss();

            }

            @Override
            public void onFailure(Call<QuestionWisResultResponseJson> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Questionwiseresult.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            }
        });
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Questionno, Answer, Point, Time, Timenew, Totalpoint, Totalfifty;
//
//        @Override
//        protected Void doInBackground(String... params) {

//            displayText = WebService.Selectanswer(Loginid, Contestid, TotalFifty, "ResultSheet");
//            productList = new ArrayList<>();
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Getquestionwiseresultdata getquestiondata1 = new Getquestionwiseresultdata();

//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Questionno = jsonrowdata.getString("Questionno");
//                        Answer = jsonrowdata.getString("Answer");
//                        Point = jsonrowdata.getString("Points");
//                        Time = jsonrowdata.getString("Time");
//                        Timenew = jsonrowdata.getString("key");
//                        Totalpoint = jsonrowdata.getString("Totalpoint");
//                        Totalfifty = jsonrowdata.getString("TotalFifty");
//
//
//                        Amount = Amount + Double.parseDouble(Totalpoint);
//
//                        Questionno = encrypt(good.key, good.initVector, Questionno);
//                        Answer = encrypt(good.key, good.initVector, Answer);
//                        Point = encrypt(good.key, good.initVector, Point);
//                        Time = encrypt(good.key, good.initVector, Time);
//                        Timenew = encrypt(good.key, good.initVector, Timenew);
//                        Totalpoint = encrypt(good.key, good.initVector, Totalpoint);
//                        Totalfifty = encrypt(good.key, good.initVector, Totalfifty);
//
//
//                        getquestiondata1.setQuestionno(Questionno);
//                        getquestiondata1.setAnswer(Answer);
//                        getquestiondata1.setPoints(Point);
//                        getquestiondata1.setTime(Time);
//                        getquestiondata1.setTotalpoints(Totalpoint);
//                        adapter.add(getquestiondata1);
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
//
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Questionwiseresult.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Questionwiseresult.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
////                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
////                SharedPreferences.Editor editor = prefs.edit();
//
//                LayoutInflater inflater = getLayoutInflater();
//                SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor1 = pref2.edit();
//                editor1.putString("Totallife", Totalfifty);
//                editor1.apply();
//                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("Totallife", Totalfifty);
//                editor.apply();
//
//                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_questionwise_result_header, listView, false);
//                ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.list_questionwise_result_footer, listView, false);
//
//                TextView textView = footer.findViewById(R.id.textView);
//
//                textView.setText("Total: " + Amount);
//
//
//                listView.addHeaderView(header);
//                listView.addFooterView(footer);
//
//
//                if (Timenew.equalsIgnoreCase("Creator")) {
////                    textViewnew.setVisibility(View.GONE);
//
//                } else {
////                    textViewnew.setVisibility(View.VISIBLE);
//
//                    ViewGroup footernew = (ViewGroup) inflater.inflate(R.layout.list_questionowise_result_textview, listView, false);
//                    listView.addFooterView(footernew);
//
//                }
//                listView.setAdapter(adapter);
//            } else {
//                Toast.makeText(Questionwiseresult.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            adapter = new ListAdapter(Questionwiseresult.this, R.layout.list_questionwise_result_row);
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

