package com.challengers.of.call;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.challengers.of.call.adapter.ListAdapter;
import com.challengers.of.call.data.Getquestionwiseresultdata;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Freequestionwiseresult extends AppCompatActivity {
    public List<Getquestionwiseresultdata> productList;
    com.challengers.of.call.adapter.ListAdapter adapter;
    ListView listView;
    JSONObject jasonObject;
    JSONArray jsonArray = null;
    String Contestid = "", Loginid = "";
    public double Amount = 0;
//    private InterstitialAd mInterstitialAd;

    public String onCheckBack = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionwiseresult);
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
        Loginid = getIntent().getStringExtra("Loginid");
        Contestid = getIntent().getStringExtra("Contestid");
        listView = findViewById(R.id.listView);
        ImageView ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Freequestionwiseresult.this, Freequizdashboard.class);
                startActivity(intent);
                finish();
            }
        });
//        AsyncCallWS task = new AsyncCallWS();
//        task.execute();
    }

//    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//        String displayText;
//        String Questionno, Answer, Point, Time, Totalpoint, Result, Type, UserRemFreeLife;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            displayText = WebService.Selectanswer(Loginid, Contestid, "Freeselectanswer");
//            productList = new ArrayList<>();
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Getquestionwiseresultdata getquestiondata1 = new Getquestionwiseresultdata();
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Questionno = jsonrowdata.getString("Questionno");
//
//                        Answer = jsonrowdata.getString("Answer");
//                        Point = jsonrowdata.getString("Points");
//                        Time = jsonrowdata.getString("Time");
//                        Totalpoint = jsonrowdata.getString("Totalpoint");
//                        Result = jsonrowdata.getString("Result");
//                        Type = jsonrowdata.getString("Type");
//                        Amount = Amount + Double.parseDouble(Totalpoint);
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
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Freequestionwiseresult.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Freequestionwiseresult.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//                onCheckBack = "true";
//                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefs.edit();
//                LayoutInflater inflater = getLayoutInflater();
//                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_questionwise_result_header, listView, false);
//                ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.list_freequestionwise_result_footer, listView, false);
//                TextView textView = footer.findViewById(R.id.textView);
//                TextView txtresult = footer.findViewById(R.id.textView2);
//                ImageView imagegif = footer.findViewById(R.id.imagegif);
//
//                textView.setText("Total: " + Amount);
//                listView.addHeaderView(header);
//                listView.addFooterView(footer);
//                listView.setAdapter(adapter);
//                if (Result.equalsIgnoreCase("0")) {
//                    txtresult.setText("Sorry you lose");
//                    imagegif.setVisibility(View.VISIBLE);
//                    Glide.with(Freequestionwiseresult.this)
//                            .load(R.raw.freeloose)
//                            .asGif()
//                            .crossFade()
//                            .into(imagegif);
////                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Sorry You Loose", Snackbar.LENGTH_LONG);
////                    View view1 = snack.getView();
////                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
////                    params.gravity = Gravity.BOTTOM;
////                    view1.setLayoutParams(params);
////                    view1.setBackgroundColor(Color.parseColor("#00902E"));
////                    snack.show();
//                } else {
//
//                    if (Type.equalsIgnoreCase("L")) {
//                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref2.edit();
//                        editor1.putString("Totallife", Result);
//                        editor1.apply();
//                    } else if (Type.equalsIgnoreCase("R")) {
//                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref2.edit();
//                        editor1.putString("Totalwallet", Result);
//                        editor1.apply();
//                    }
//
//
//                    txtresult.setText("Congrats you won " + Result + " " + Type);
//                    imagegif.setVisibility(View.VISIBLE);
//                    Glide.with(Freequestionwiseresult.this)
//                            .load(R.raw.happy)
//                            .asGif()
//                            .crossFade()
//                            .into(imagegif);
////                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), Result+Type, Snackbar.LENGTH_LONG);
////                    View view1 = snack.getView();
////                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
////                    params.gravity = Gravity.BOTTOM;
////                    view1.setLayoutParams(params);
////                    view1.setBackgroundColor(Color.parseColor("#00902E"));
////                    snack.show();
//                }
//
//            } else {
//                Toast.makeText(Freequestionwiseresult.this, "Please try again", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            onCheckBack = "false";
//            adapter = new ListAdapter(Freequestionwiseresult.this, R.layout.list_questionwise_result_row);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

    @Override
    public void onBackPressed() {
        if (onCheckBack.equals("true")) {
            Intent intent = new Intent(Freequestionwiseresult.this, Freequizdashboard.class);
            startActivity(intent);
            finish();
        }else {

        }
    }
}

