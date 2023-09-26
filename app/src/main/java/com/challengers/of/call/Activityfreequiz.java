package com.challengers.of.call;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.data.Getquestiondata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Activityfreequiz extends AppCompatActivity {
    public static boolean click = false;
    public static List<Getquestiondata> Questiondata;
    CountDownTimer cTimer = null;
    private static bannedapp bannedapps;
    TextView tvtimer;
    JSONObject jasonObject;
    ProgressBar mProgressBar, mProgressBar1;
    JSONArray jsonArray = null;
    TextView txtquestion, txtoptiona, txtoptionb,
            txtoptionc, txtoptiond, txtquestionno, txtscore;
    Getquestiondata getquestiondata;
    String Contestid = "", Answer = "", Rightanswer, Loginid;
    int count = 0, Point = 0, Finalamount = 0;
    LinearLayout layoutoption;
    String data = "";
    long startTime = 0;
    LinearLayout l1;
    private JSONArray resultArray;
    private String From = "", Animate = "";
    /////////////////////////////////////////////////////////////////////////
    public CardView layout_A, layout_B, layout_C, layout_D;
    private Vibrator vibrator;
    private MediaPlayer weoolmediaplayer;
    public String Totalwallet;
    public int btnPosition = 0;
    public ImageView fifty_fifty;
    public boolean userd = true;
    /////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityfreequiz);

        bannedapps = new bannedapp(Activityfreequiz.this);
        bannedapps.bannedbig();
        fifty_fifty = (ImageView) findViewById(R.id.fifty_fifty);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        weoolmediaplayer = MediaPlayer.create(Activityfreequiz.this, R.raw.click);


        layout_A = (CardView) findViewById(R.id.cardview);
        layout_B = (CardView) findViewById(R.id.cardview2);
        layout_C = (CardView) findViewById(R.id.cardview3);
        layout_D = (CardView) findViewById(R.id.cardview4);


        fifty_fifty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (SettingsPreferences.getSoundEnableDisable(Activityfreequiz.this)) {
                    StaticUtils.backSoundonclick(Activityfreequiz.this);
                }
                if (SettingsPreferences.getVibration(Activityfreequiz.this)) {
                    StaticUtils.vibrate(Activityfreequiz.this, StaticUtils.VIBRATION_DURATION);
                }
                Rightanswer = getquestiondata.getAnswer();


                SharedPreferences shear = getSharedPreferences("fifty", Context.MODE_PRIVATE);
                String checkfifty = shear.getString("fifty", null);


                if (checkfifty.equalsIgnoreCase("true")) {

                    btnPosition = 0;

                    if ("A".equalsIgnoreCase(Rightanswer)) {
                        btnPosition = 1;
                    }
                    if ("B".equalsIgnoreCase(Rightanswer)) {
                        btnPosition = 2;
                    }
                    if ("C".equalsIgnoreCase(Rightanswer)) {
                        btnPosition = 3;
                    }
                    if ("D".equalsIgnoreCase(Rightanswer)) {
                        btnPosition = 4;
                    }
                    if (btnPosition == 1) {
                        layout_B.setVisibility(View.GONE);
                        layout_C.setVisibility(View.GONE);

                    } else if (btnPosition == 2) {
                        layout_C.setVisibility(View.GONE);
                        layout_D.setVisibility(View.GONE);

                    } else if (btnPosition == 3) {
                        layout_D.setVisibility(View.GONE);
                        layout_A.setVisibility(View.GONE);

                    } else if (btnPosition == 4) {
                        layout_A.setVisibility(View.GONE);
                        layout_B.setVisibility(View.GONE);

                    }


                    SharedPreferences.Editor edit = shear.edit();
                    edit.putString("fifty", "fales");
                    edit.apply();


                } else {
                    Toast.makeText(Activityfreequiz.this, "Life Line Already Used !", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Contestid = getIntent().getStringExtra("Contestid");
        data = getIntent().getStringExtra("data");
        Finalamount = getIntent().getIntExtra("Finalamount", 0);
        count = getIntent().getIntExtra("count", 0);
        Animate = getIntent().getStringExtra("Animate");
        tvtimer = findViewById(R.id.tvTimer);
        resultArray = new JSONArray();
        l1 = findViewById(R.id.l1);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);
        txtquestion = findViewById(R.id.txtquestion);
        txtoptiona = findViewById(R.id.optiona);
        txtoptionb = findViewById(R.id.optionb);
        txtoptionc = findViewById(R.id.optionc);
        txtoptiond = findViewById(R.id.optiond);
        txtscore = findViewById(R.id.txtscore);
        txtscore.setText(Finalamount + "/100");
        txtquestionno = findViewById(R.id.questionno);
        txtquestionno.setText(Finalamount + "/100");
        layoutoption = findViewById(R.id.layoutoption);
        txtoptiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if (click == false) {
                        Answer = "A";
                        startTime = 0;
                        startTime = System.currentTimeMillis();
                        Rightanswer = getquestiondata.getAnswer();
                        if (Answer.equalsIgnoreCase(Rightanswer)) {
                            Point = 10;
                            Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
                            Finalamount = Finalamount + Point;
                            txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                            txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            txtscore.setText(Finalamount + "/100");
                            playsound(R.raw.rightanswer);
                        } else {
                            // Wrong answer color #730a07
                            Point = 0;
                            //txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                            txtoptiona.setBackgroundColor(Color.parseColor("#730a07"));
                            txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            if (Rightanswer.equalsIgnoreCase("A")) {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("B")) {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("C")) {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("D")) {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
//                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//                        task.execute();
                        click = true;
                    }
                }
            }
        });
        txtoptionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if (click == false) {
                        Answer = "B";
                        startTime = 0;
                        startTime = System.currentTimeMillis();
                        Rightanswer = getquestiondata.getAnswer();
                        if (Answer.equalsIgnoreCase(Rightanswer)) {
                            Point = 10;
                            Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
                            txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                            txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            playsound(R.raw.rightanswer);
                        } else {
                            Point = 0;
                            txtoptionb.setBackgroundColor(Color.parseColor("#730a07"));
                            txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            if (Rightanswer.equalsIgnoreCase("A")) {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("B")) {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("C")) {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("D")) {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
//
//                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//                        task.execute();
                        click = true;
                    }
                }
            }
        });
        txtoptionc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if (click == false) {
                        Answer = "C";
                        startTime = 0;
                        startTime = System.currentTimeMillis();
                        Rightanswer = getquestiondata.getAnswer();
                        if (Answer.equalsIgnoreCase(Rightanswer)) {
                            Point = 10;
                            Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
                            txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                            txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            playsound(R.raw.rightanswer);
                        } else {
                            Point = 0;
                            txtoptionc.setBackgroundColor(Color.parseColor("#730a07"));
                            txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            if (Rightanswer.equalsIgnoreCase("A")) {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("B")) {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("C")) {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("D")) {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }

//                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//                        task.execute();
                        click = true;
                    }
                }
            }
        });
        txtoptiond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if (click == false) {
                        Answer = "D";
                        startTime = 0;
                        startTime = System.currentTimeMillis();
                        Rightanswer = getquestiondata.getAnswer();
                        if (Answer.equalsIgnoreCase(Rightanswer)) {
                            Point = 10;
                            Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
                            txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                            txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            playsound(R.raw.rightanswer);
                        } else {
                            Point = 0;
                            txtoptiond.setBackgroundColor(Color.parseColor("#730a07"));
                            txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            if (Rightanswer.equalsIgnoreCase("A")) {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("B")) {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("C")) {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            } else if (Rightanswer.equalsIgnoreCase("D")) {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
//                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//                        task.execute();
                        click = true;
                    }
                }
            }
        });
//        AsyncCallWS task = new AsyncCallWS();
//        task.execute();
        if (Animate != null) {

            String data = getIntent().getStringExtra("data");
            try {
                resultArray = new JSONArray(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (count < Questiondata.size()) {
                Nextquestion();
            }
        } else {
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
        }


    }

    private void userds() {
        userd = false;
    }

    void startTimer() {
        cTimer = new CountDownTimer(11000, 1) {
            public void onTick(long millisUntilFinished) {
                mProgressBar1.setProgress((int) (millisUntilFinished));
                tvtimer.setText(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + "");
            }

            public void onFinish() {
                mProgressBar1.setVisibility(View.INVISIBLE);
                mProgressBar1.setVisibility(View.VISIBLE);
                click = true;
                Answer = "N/A";
                Rightanswer = getquestiondata.getAnswer();
                Point = 0;
//                AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//                task.execute();
                //Toast.makeText(Activityquiz.this,"Your Time has been finished",Toast.LENGTH_LONG).show();
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    private void RunAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.textviewanimate);
        a.reset();
//        TextView tv =
//        tv.clearAnimation();(TextView) findViewById(R.id.firstTextView);
//        tv.startAnimation(a);
    }

    private void Nextquestion() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mProgressBar1.setVisibility(View.VISIBLE);
        mProgressBar1.setMax(10 * 1000);
        if (count < Questiondata.size()) {
            txtoptiona.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptionb.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptionc.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptiond.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptiona.setTextColor(Color.parseColor("#6D0303"));
            txtoptionb.setTextColor(Color.parseColor("#6D0303"));
            txtoptionc.setTextColor(Color.parseColor("#6D0303"));
            txtoptiond.setTextColor(Color.parseColor("#6D0303"));
            getquestiondata = Questiondata.get(count);
            txtquestion.setText(getquestiondata.getQuestion());
            txtoptiona.setText(getquestiondata.getOptiona());
            txtoptionb.setText(getquestiondata.getOptionb());
            txtoptionc.setText(getquestiondata.getOptionc());
            txtoptiond.setText(getquestiondata.getOptiond());
            txtquestionno.setText((count + 1) + "/" + Questiondata.size());
            startTimer();
            count++;
            //startTimer();
        } else {
            //cancelTimer();
            count++;
            Toast.makeText(Activityfreequiz.this, "Your quiz has been finished", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(Activityfreequiz.this, Freequestionwiseresult.class);
//            intent.putExtra("Loginid", Loginid);
//            intent.putExtra("Contestid", Contestid);
//            startActivity(intent);
//            finish();
        }
        click = false;
    }

    @Override
    public void onBackPressed() {
    }

    private void animate() {
        if (count < Questiondata.size()) {
//            Handler handler1 = new Handler();
//            handler1.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(Activityfreequiz.this, Displayanimation.class);
//                    intent.putExtra("Answer", Answer);
//                    intent.putExtra("Rightanswer", Rightanswer);
//                    intent.putExtra("Contestid", Contestid);
//                    intent.putExtra("data", resultArray.toString());
//                    intent.putExtra("Finalamount", Finalamount);
//                    intent.putExtra("From", "Freequiz");
//                    intent.putExtra("count", count);
//                    intent.putExtra("paid", "free");
//                    startActivity(intent);
//                    finish();
//                    overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
//                }
//            }, 2000);


            long difference = System.currentTimeMillis() - startTime;
            if (difference < 20000) {

                AsyncCallWSwait taskwait = new AsyncCallWSwait();
                taskwait.execute();
            } else {
                AsyncCallWSwait taskwait = new AsyncCallWSwait();
                taskwait.execute();
            }
        }
    }


    private void playsound(int sound) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(Activityfreequiz.this, sound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer md) {
                mediaPlayer.stop();
                mediaPlayer.release();
                //Toast.makeText(Activityfreequiz.this, "Media player has stop", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText;
        String Questionid, Question, Optiona, Optionb, Optionc, Optiond, Answer1;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
            displayText = WebService.Selectquestion("Bindquestion");
            Questiondata = new ArrayList<>();
            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                try {
                    jasonObject = new JSONObject(displayText);
                    jsonArray = jasonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Getquestiondata getquestiondata1 = new Getquestiondata();
                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                        Questionid = jsonrowdata.getString("Questionid");
                        Question = jsonrowdata.getString("Question");
                        Optiona = jsonrowdata.getString("Optiona");
                        Optionb = jsonrowdata.getString("Optionb");
                        Optionc = jsonrowdata.getString("Optionc");
                        Optiond = jsonrowdata.getString("Optiond");
                        Answer1 = jsonrowdata.getString("Answer");
                        getquestiondata1.setQuestionid(Questionid);
                        getquestiondata1.setQuestion(Question);
                        getquestiondata1.setOptiona(Optiona);
                        getquestiondata1.setOptionb(Optionb);
                        getquestiondata1.setOptionc(Optionc);
                        getquestiondata1.setOptiond(Optiond);
                        getquestiondata1.setAnswer(Answer1);
                        Questiondata.add(getquestiondata1);
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
            if (displayText.equalsIgnoreCase("connection fault")) {
                l1.setVisibility(View.GONE);
                Toast.makeText(Activityfreequiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                l1.setVisibility(View.GONE);
                Toast.makeText(Activityfreequiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                l1.setVisibility(View.VISIBLE);
                Nextquestion();
            } else {
                Toast.makeText(Activityfreequiz.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            hud = KProgressHUD.create(Activityfreequiz.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            //hud.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

//    public class AsyncCallWSinsertanswer extends AsyncTask<String, Void, Void> {
//        String displayText = "";
//        String Questionid, Count;
//        private KProgressHUD hud;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
//            Loginid = prefs.getString("Loginid", null);
//
//            int counter = count;
//            int size = Questiondata.size();
//            displayText = WebService.Insertanswer(Loginid, Contestid, Questionid, Rightanswer, Answer, Point, counter, size, "Insertfreeanswer");
//            //displayText = WebService.Insertfreeanswer1(Loginid, Contestid, resultArray.toString(), size, "", "Insertfreeanswerquiz");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Count = jsonrowdata.getString("Count");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
////            hud.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Activityfreequiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//                if (count == Questiondata.size()) {
//
//                    boolean check = isApplicationSentToBackground(Activityfreequiz.this);
//                    if (check == false) {
//                        Intent intent = new Intent(Activityfreequiz.this, Freequestionwiseresult.class);
//                        intent.putExtra("Loginid", Loginid);
//                        intent.putExtra("Contestid", Contestid);
//                        intent.putExtra("From", "free");
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        finish();
//                    }
//                } else if (count < Questiondata.size()) {
//                    if (Answer.equalsIgnoreCase("N/A")) {
//                        Nextquestion();
//                    } else {
//                        animate();
//                    }
//                }
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Activityfreequiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//                if (count == Questiondata.size()) {
//
//                    boolean check = isApplicationSentToBackground(Activityfreequiz.this);
//                    if (check == false) {
//                        Intent intent = new Intent(Activityfreequiz.this, Freequestionwiseresult.class);
//                        intent.putExtra("Loginid", Loginid);
//                        intent.putExtra("Contestid", Contestid);
//                        intent.putExtra("From", "free");
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        finish();
//                    }
//                } else if (count < Questiondata.size()) {
//                    if (Answer.equalsIgnoreCase("N/A")) {
//                        Nextquestion();
//                    } else {
//                        animate();
//                    }
//                }
//            } else {
////                if (jsonArray.length() > 0) {
////                if (count <= Questiondata.size())
//                //{
//                if (count == Questiondata.size()) {
//
//                    boolean check = isApplicationSentToBackground(Activityfreequiz.this);
//                    if (check == false) {
//                        Intent intent = new Intent(Activityfreequiz.this, Freequestionwiseresult.class);
//                        intent.putExtra("Loginid", Loginid);
//                        intent.putExtra("Contestid", Contestid);
//                        intent.putExtra("From", "free");
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        finish();
//                    }
//                } else if (count < Questiondata.size()) {
//                    if (Answer.equalsIgnoreCase("N/A")) {
//                        Nextquestion();
//                    } else {
//                        animate();
//                    }
//                }
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            getquestiondata = Questiondata.get(count - 1);
//            Questionid = getquestiondata.getQuestionid();
////            hud = KProgressHUD.create(Activityquiz.this)
////                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
////                    .setWindowColor(Color.parseColor("#000000"))
////                    .setLabel("Please Wait...")
////                    .setAnimationSpeed(1);
////            hud.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

    public class AsyncCallWSwait extends AsyncTask<String, Void, Void> {
        String displayText;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {

            long difference = System.currentTimeMillis() - startTime;
            if (difference < 20000) {
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Intent intent = new Intent(Activityfreequiz.this, Displayanimation.class);
            intent.putExtra("Answer", Answer);
            intent.putExtra("Rightanswer", Rightanswer);
            intent.putExtra("Contestid", Contestid);
            intent.putExtra("data", resultArray.toString());
            intent.putExtra("Finalamount", Finalamount);
            intent.putExtra("From", "Freequiz");
            intent.putExtra("count", count);
            intent.putExtra("paid", "free");
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
