package com.challengers.of.call;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challengers.of.call.data.Getleaderboarddatafreequiz;
import com.challengers.of.call.helper.GlideCircleTransformation;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Leaderboardfreequiz extends AppCompatActivity {

    JSONObject jasonObject;
    JSONArray jsonArray = null;
    JSONArray jsonArray1 = null;
    Leaderboardadapterfreequiz adapter;
    Getleaderboarddatafreequiz getcontestdata;
    ListView listView;
    TextView txtthisweek, txtpreviousweek;
    TextView txtselfrank, txtselfname, txtselfamount;
    private TextView createcontextobject;

    String From = "C";

    TextView txttimer;
    CountDownTimer timer;
    String topObject="Top_Winner";
    AsyncCallWSfree task;
    private TextView textviewTimer;
    public TextView onerank,tworank,threerank;
    public KProgressHUD hud;

    public TextView tvshowpopup;
    public Dialog epicDialog; //Popup Dialog Box
    public ImageView closepopupbtn;
    public TextView tvrank1,tvrank2,tvrank3,tvrank4,tvrank5,tvrank6,tvrank7,tvrank8,tvrank9,tvrank10;
    public String rank1,rank2,rank3,rank4,rank5,rank6,rank7,rank8,rank9,rank10,totalrank;
    public String r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboardfreequiz);


        onerank = (TextView)findViewById(R.id.firstrank);
        tworank = (TextView)findViewById(R.id.secondrank);
        threerank = (TextView)findViewById(R.id.thridrank);

        tvshowpopup = (TextView)findViewById(R.id.readonly);
        epicDialog = new Dialog(this); // for popup Dialog

        TextView btnshowpopup = (TextView)findViewById(R.id.btnreadonly);
//
        btnshowpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.setContentView(R.layout.spinnerlayout);
                epicDialog.setCancelable(false);
                // popup show
                closepopupbtn = (ImageView)epicDialog.findViewById(R.id.closePopup);// popup Close button

                closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
                    @Override
                    public void onClick(View v) {
                        epicDialog.dismiss();
                    }
                });
                tvrank1 = (TextView)epicDialog.findViewById(R.id.tvrank1);
                tvrank1.setText("₹ "+r1);
                tvrank2 = (TextView)epicDialog.findViewById(R.id.tvrank2);
                tvrank2.setText("₹ "+r2);
                tvrank3 = (TextView)epicDialog.findViewById(R.id.tvrank3);
                tvrank3.setText("₹ "+r3);
                tvrank4 = (TextView)epicDialog.findViewById(R.id.tvrank4);
                tvrank4.setText("₹ "+r4);
                tvrank5 = (TextView)epicDialog.findViewById(R.id.tvrank5);
                tvrank5.setText("₹ "+r5);
                tvrank6 = (TextView)epicDialog.findViewById(R.id.tvrank6);
                tvrank6.setText("₹ "+r6);
                tvrank7 = (TextView)epicDialog.findViewById(R.id.tvrank7);
                tvrank7.setText("₹ "+r7);

                tvrank8 = (TextView)epicDialog.findViewById(R.id.tvrank8);
                tvrank8.setText("₹ "+r8);
                tvrank9 = (TextView)epicDialog.findViewById(R.id.tvrank9);
                tvrank9.setText("₹ "+r9);
                tvrank10 = (TextView)epicDialog.findViewById(R.id.tvrank10);
                tvrank10.setText("₹ "+r10);
                epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                epicDialog.show();
            }
        });
        tvshowpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.setContentView(R.layout.spinnerlayout);
                epicDialog.setCancelable(false);

                closepopupbtn = (ImageView)epicDialog.findViewById(R.id.closePopup);// popup Close button

                closepopupbtn.setOnClickListener(new View.OnClickListener() {// popup Close button working
                    @Override
                    public void onClick(View v) {
                        epicDialog.dismiss();
                    }
                });
                tvrank1 = (TextView)epicDialog.findViewById(R.id.tvrank1);
                tvrank1.setText("₹ "+r1);
                tvrank2 = (TextView)epicDialog.findViewById(R.id.tvrank2);
                tvrank2.setText("₹ "+r2);
                tvrank3 = (TextView)epicDialog.findViewById(R.id.tvrank3);
                tvrank3.setText("₹ "+r3);
                tvrank4 = (TextView)epicDialog.findViewById(R.id.tvrank4);
                tvrank4.setText("₹ "+r4);
                tvrank5 = (TextView)epicDialog.findViewById(R.id.tvrank5);
                tvrank5.setText("₹ "+r5);
                tvrank6 = (TextView)epicDialog.findViewById(R.id.tvrank6);
                tvrank6.setText("₹ "+r6);
                tvrank7 = (TextView)epicDialog.findViewById(R.id.tvrank7);
                tvrank7.setText("₹ "+r7);
                tvrank8 = (TextView)epicDialog.findViewById(R.id.tvrank8);
                tvrank8.setText("₹ "+r8);
                tvrank9 = (TextView)epicDialog.findViewById(R.id.tvrank9);
                tvrank9.setText("₹ "+r9);
                tvrank10 = (TextView)epicDialog.findViewById(R.id.tvrank10);
                tvrank10.setText("₹ "+r10);

                epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
                epicDialog.show(); // popup show
            }
        });


        listView = findViewById(R.id.listView);
//        txtthisweek = findViewById(R.id.txtcurrentweek);
//        txtpreviousweek = findViewById(R.id.txtpreviousweek);
        txtselfrank = findViewById(R.id.textView11);
        txtselfname = findViewById(R.id.textView21);
        txtselfamount = findViewById(R.id.textView31);
        createcontextobject = (TextView)findViewById(R.id.textView3);

        final Button btnthisweek = findViewById(R.id.btnthisweek);
        final Button btnpreviousweek = findViewById(R.id.btnpreviousweek);

        btnthisweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnthisweek.setBackgroundColor(Color.parseColor("#c20e14"));
                btnthisweek.setTextColor(Color.parseColor("#FFFFFF"));



                btnpreviousweek.setBackgroundColor(Color.parseColor("#eb1c30"));

                btnpreviousweek.setTextColor(Color.parseColor("#000000"));


                //btnthisweek.setBackgroundColor(Color.parseColor("#eb1c30"));
                //                btnthisweek.setTextColor(Color.parseColor("#FFFFFF"));
                //
                //
                //
                //                btnpreviousweek.setBackgroundColor(Color.parseColor("#eb1c30"));
                //                btnpreviousweek.setTextColor(Color.parseColor("#000000"));

                From = "C";
                if (task != null)
                    task.cancel(true);
                task = new AsyncCallWSfree();
                task.execute();
            }
        });

        btnpreviousweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                btnpreviousweek.setBackgroundColor(Color.parseColor("#c20e14"));
                btnpreviousweek.setTextColor(Color.parseColor("#FFFFFF"));


                btnthisweek.setBackgroundColor(Color.parseColor("#eb1c30"));
                btnthisweek.setTextColor(Color.parseColor("#000000"));

                //btnpreviousweek.setBackgroundColor(Color.parseColor("#eb1c30"));
                //                btnpreviousweek.setTextColor(Color.parseColor("#FFFFFF"));
                //
                //
                //
                //                btnthisweek.setBackgroundColor(Color.parseColor("#eb1c30"));
                //                btnthisweek.setTextColor(Color.parseColor("#000000"));


                From = "P";
                if (task != null)
                    task.cancel(true);
                task = new AsyncCallWSfree();
                task.execute();
            }
        });

        ImageView ivback = findViewById(R.id.ivback);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Leaderboardfreequiz.this, Freequizdashboard.class);
                intent.putExtra("goto", "challenge");
                intent.putExtra("Totalcontest", Freequizdashboard.class);
                startActivity(intent);
                finish();
            }
        });
        task = new AsyncCallWSfree();
        task.execute();
    }
    @Override
    public void onBackPressed() {


            Intent intent = new Intent(Leaderboardfreequiz.this, Freequizdashboard.class);
            intent.putExtra("goto", "challenge");
            intent.putExtra("Totalcontest", Main2Activity.Totalcontest);
            startActivity(intent);
            finish();
            //super.onBackPressed();

    }
    private void Counter(int timertime) {
        timer = new CountDownTimer(timertime, 1000) {
            public void onTick(long millisUntilFinished) {
                //Toast.makeText(Leaderboard.this,"Counter is running",Toast.LENGTH_LONG).show();
                String minute, hour, day;
//                int seconds = (int) ((millisUntilFinished / (1000 )) %60);
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                int days = (int) (millisUntilFinished / ((1000 * 60 * 60 * 24)) % 7);
//                second = Integer.toString(seconds);
                hour = Integer.toString(hours);
                minute = Integer.toString(minutes);
                day = Integer.toString(days);
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
                txttimer.setText( hours + " h " + minutes + " m ");
            }
            public void onFinish() {
//                AsyncCallWSdistribute task = new AsyncCallWSdistribute();
//                task.execute();
            }
        }.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    public class AsyncCallWSfree extends AsyncTask<String, Void, Void> {
        String displayText,Useridmain;
        String Srno, Contestname, Winningamount, selfsrno = "",Check,Prize1, Prize2,Prize3,Prize4,Prize5,Prize6,Prize7,Prize8,Prize9,Prize10, selfcontestname, selfwinningamount,Imageurl,Selfimage,firstrank,secondrank,thirdrank;
        int Remaining = 0,image,simage;
        String NewCheck = "go";



        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            if (Loginid != null) {
                displayText = WebService.Selectleaderboardfree(Loginid, From, "Leaderboardfree");
            }
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    int count = 1;
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Srno = jsonrowdata.getString("Sno");
                        Contestname = jsonrowdata.getString("Contestname");
                        Winningamount = jsonrowdata.getString("Winningamount");


                        Useridmain = jsonrowdata.getString("Userid");
                        Imageurl = jsonrowdata.getString("Imageurl");
                        Prize1 = jsonrowdata.getString("prize1");
                        Prize2 = jsonrowdata.getString("prize2");
                        Prize3 = jsonrowdata.getString("prize3");

                        Prize4 = jsonrowdata.getString("prize4");
                        Prize5 = jsonrowdata.getString("prize5");
                        Prize6 = jsonrowdata.getString("prize6");
                        Prize7 = jsonrowdata.getString("prize7");
                        Prize8 = jsonrowdata.getString("prize8");
                        Prize9 = jsonrowdata.getString("prize9");
                        Prize10 = jsonrowdata.getString("prize10");

                        image = Imageurl.length();

                        if (Srno.equalsIgnoreCase("not")){
//                            Toast.makeText(Leaderboardfreequiz.this, "Data is not available", Toast.LENGTH_SHORT).show();
                            break;

                        }else {

                            getcontestdata = new Getleaderboarddatafreequiz();
                            getcontestdata.setSno(Srno);
                            getcontestdata.setContestname(Contestname);
                            getcontestdata.setPoints(Winningamount);
                            getcontestdata.setImage(image);
                            getcontestdata.setFirstprize(Prize1);
                            getcontestdata.setSecondprize(Prize2);
                            getcontestdata.setThirdprize(Prize3);

                            getcontestdata.setFourprize(Prize4);
                            getcontestdata.setFiveprize(Prize5);
                            getcontestdata.setSixprize(Prize6);
                            getcontestdata.setSevenprize(Prize7);
                            getcontestdata.setErthprize(Prize8);
                            getcontestdata.setNineprize(Prize9);
                            getcontestdata.setTenprize(Prize10);







                            getcontestdata.setUserid(Useridmain);
                            getcontestdata.setImageurl(Imageurl);
                            if (Loginid.equals(Useridmain)){
                                getcontestdata.setColor(1);
                            }


                            if (Integer.valueOf(Srno) == 1) {
                                getcontestdata.setPrize(1);

                            } else if(Integer.valueOf(Srno) == 2)  {

                                getcontestdata.setPrize(2);

                            }else if(Integer.valueOf(Srno) == 3){

                                getcontestdata.setPrize(3);

                            }else if(Integer.valueOf(Srno) == 4){

                                getcontestdata.setPrize(4);

                            }else if(Integer.valueOf(Srno) == 5){

                                getcontestdata.setPrize(5);

                            }else if(Integer.valueOf(Srno) == 6){

                                getcontestdata.setPrize(6);

                            }else if(Integer.valueOf(Srno) == 7){

                                getcontestdata.setPrize(7);

                            }else if(Integer.valueOf(Srno) == 8){

                                getcontestdata.setPrize(8);

                            }else if(Integer.valueOf(Srno) == 9){

                                getcontestdata.setPrize(9);

                            }else if(Integer.valueOf(Srno) == 10){

                                getcontestdata.setPrize(10);

                            }else{
                                getcontestdata.setPrize(0);
                            }



                            if (Integer.valueOf(Srno) <= 3) {
                                getcontestdata.setColor(1);
                            } else {
                                getcontestdata.setColor(0);
                            }
                            adapter.add(getcontestdata);
                            count++;


                        }




//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                TextView txtuserid = view.findViewById(R.id.textView4);
//                                TextView txtname = view.findViewById(R.id.textView2);
//
//                                Intent intent = new Intent(Leaderboardfreequiz.this, Winnerdetail.class);
//                                intent.putExtra("Winnerid", Useridmain);
//                                intent.putExtra("Name", txtname.getText().toString());
//                                startActivity(intent);
//                            }
//                        });




//
                    }

                    // accesssing self data
                    jsonArray1 = jasonObject.getJSONArray("Leaderdata");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonrowdata = jsonArray1.getJSONObject(i);
                        selfsrno = jsonrowdata.getString("Srno");
                        selfcontestname = jsonrowdata.getString("Contestname");
                        selfwinningamount = jsonrowdata.getString("Winningamount");
                        Remaining = Integer.valueOf(jsonrowdata.getString("Remainingtime"));
                        Selfimage = jsonrowdata.getString("Imageurl");
                        simage = Selfimage.length();

                        firstrank = jsonrowdata.getString("R1");

                        secondrank = jsonrowdata.getString("R2");
                        thirdrank = jsonrowdata.getString("R3");

                        rank1 = jsonrowdata.getString("rank1");
                        rank2 = jsonrowdata.getString("rank2");
                        rank3 = jsonrowdata.getString("rank3");
                        rank4 = jsonrowdata.getString("rank4");
                        rank5 = jsonrowdata.getString("rank5");
                        rank6 = jsonrowdata.getString("rank6");
                        rank7 = jsonrowdata.getString("rank7");
                        rank8 = jsonrowdata.getString("rank8");
                        rank9 = jsonrowdata.getString("rank9");
                        rank10 = jsonrowdata.getString("rank10");
                        totalrank = jsonrowdata.getString("totalprice");

                        Check = jsonrowdata.getString("Check");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Void result) {
            hud.dismiss();
            if(displayText!=null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(Leaderboardfreequiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(Leaderboardfreequiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if(jsonArray!=null || jsonArray1!=null) {
                    if (jsonArray.length() > 0) {

                        ImageView ivselfusr = findViewById(R.id.ivselfuser);
                        onerank.setText("RANK 1 - ₹" + firstrank);
                        tworank.setText("RANK 2 - ₹" + secondrank);
                        threerank.setText("RANK 3 - ₹" + thirdrank);

                        r1 = rank1;
                        r2= rank2;
                        r3 = rank3;
                        r4 = rank4;
                        r5 = rank5;
                        r6 = rank6;
                        r7 = rank7;
                        r8 = rank8;
                        r9 = rank9;
                        r10 = rank10;

                        total = totalrank;

                        tvshowpopup = (TextView)findViewById(R.id.readonly);
                        tvshowpopup.setText("TOTAL WINNINGS: ₹ "+total);
//                        if (Check.equalsIgnoreCase(NewCheck)){
//                            AsyncCallWSdistribute task = new AsyncCallWSdistribute();
//                            task.execute();
//                        }


                        if (selfsrno != "" && selfsrno != null && !selfsrno.isEmpty()) {
                            CardView cardView = findViewById(R.id.c1);
                            cardView.setVisibility(View.VISIBLE);
                            txtselfrank.setText(selfsrno);
                            txtselfname.setText(selfcontestname);
                            txtselfamount.setText(selfwinningamount);

                            if (simage < 30) {
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
                            }else {
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
                            CardView cardView = findViewById(R.id.c1);
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
                    } else if (jsonArray.length() == 0) {
                        Toast.makeText(Leaderboardfreequiz.this, "No detail found", Toast.LENGTH_LONG).show();
                        if (listView.getHeaderViewsCount() > 0) {
                            LayoutInflater inflater = getLayoutInflater();
                            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_leaderboard_header, listView, false);
                            listView.removeHeaderView(header);
                        }
                        CardView cardView = findViewById(R.id.c1);
                        cardView.setVisibility(View.GONE);
                        listView.setAdapter(null);
                    }
                }
                else
                {
                    Toast.makeText(Leaderboardfreequiz.this, "Please try again", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(Leaderboardfreequiz.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            adapter = new Leaderboardadapterfreequiz(Leaderboardfreequiz.this, R.layout.list_leaderboard_row);
            hud = KProgressHUD.create(Leaderboardfreequiz.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onCancelled() {
            task.cancel(true);
            super.onCancelled();
        }
    }




    public class AsyncCallWSdistribute extends AsyncTask<String, Void, Void> {
        String displayText;
        String Count;
//        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String Loginid = prefs.getString("Loginid", null);
            if (Loginid != null) {
                displayText = WebService.Countcontest(Loginid, "", "Distributefreeleaderpriceapp");
            }
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    int count = 1;
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

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Void result) {
//            hud.dismiss();
            if(displayText!=null) {
                if (displayText.equalsIgnoreCase("connection fault")) {
                    Toast.makeText(Leaderboardfreequiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
                } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    Toast.makeText(Leaderboardfreequiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
                } else if (jsonArray.length() > 0) {
                    if (Count != null && Count != "") {
                        SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref2.edit();
                        editor1.putString("Totalwallet", Count);
                        editor1.apply();
//                        AsyncCallWS task = new AsyncCallWS();
//                        task.execute();
                    }
                } else if (jsonArray.length() == 0) {
                    Toast.makeText(Leaderboardfreequiz.this, "No detail found", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(Leaderboardfreequiz.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
//            hud = KProgressHUD.create(Leaderboard.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setWindowColor(Color.parseColor("#000000"))
//                    .setLabel("Please Wait...")
//                    .setAnimationSpeed(1);
//            hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }






}
