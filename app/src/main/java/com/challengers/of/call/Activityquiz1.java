package com.challengers.of.call;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.data.Getquestiondata;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Activityquiz1 extends AppCompatActivity {
    CountDownTimer cTimer = null;
    private static bannedapp bannedapps;
    TextView tvtimer;
    JSONObject jasonObject;
    ProgressBar mProgressBar, mProgressBar1;
    JSONArray jsonArray = null;
    JSONArray resultArray = null;
    TextView txtquestion, txtoptiona, txtoptionb, txtoptionc, txtoptiond, txtquestionno,txtscore;
    List<Getquestiondata> Questiondata;
    Getquestiondata getquestiondata;
    String Contestid = "", Answer = "", Rightanswer,Loginid;
    int count = 0, Point = 0,Finalamount=0;
    public static boolean click=false;
    private String From="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityquiz);

        bannedapps = new bannedapp(Activityquiz1.this);
        bannedapps.bannedbig();
        Contestid = getIntent().getStringExtra("Contestid");
        tvtimer = findViewById(R.id.tvTimer);
        From=getIntent().getStringExtra("From");
        resultArray=new JSONArray();
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);
        txtquestion = findViewById(R.id.txtquestion);
        txtoptiona = findViewById(R.id.optiona);
        txtoptionb = findViewById(R.id.optionb);
        txtoptionc = findViewById(R.id.optionc);
        txtoptiond = findViewById(R.id.optiond);
        txtscore=findViewById(R.id.txtscore);
        txtquestionno = findViewById(R.id.questionno);
        txtoptiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if(click==false) {
                        Answer = "A";
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
                            Point = 0;
                            txtoptiona.setBackgroundColor(Color.parseColor("#730a07"));
                            txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            Finalamount = Finalamount + Point;
                            txtscore.setText(Finalamount + "/100");
                            if(Rightanswer.equalsIgnoreCase("A"))
                            {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("B"))
                            {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("C"))
                            {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("D"))
                            {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
                        addjason();
                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
                        task.execute();
                        click=true;
                    }
                }
            }
        });
        txtoptionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if(click==false) {
                        Answer = "B";
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
                            if(Rightanswer.equalsIgnoreCase("A"))
                            {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("B"))
                            {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("C"))
                            {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("D"))
                            {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
                        addjason();
                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
                        task.execute();
                        click=true;
                    }
                }
            }
        });
        txtoptionc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if(click==false) {
                        Answer = "C";
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
                            if(Rightanswer.equalsIgnoreCase("A"))
                            {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("B"))
                            {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("C"))
                            {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("D"))
                            {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
                        addjason();
                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
                        task.execute();
                        click=true;
                    }
                }
            }
        });
        txtoptiond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTimer();
                if (count <= Questiondata.size()) {
                    if(click==false) {
                        Answer = "D";
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
                            //highlighting right answer
                            if(Rightanswer.equalsIgnoreCase("A"))
                            {
                                txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("B"))
                            {
                                txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("C"))
                            {
                                txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            else if(Rightanswer.equalsIgnoreCase("D"))
                            {
                                txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
                                txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
                            }
                            playsound(R.raw.wronganswer);
                        }
                        addjason();
                        AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
                        task.execute();
                        click=true;
                    }
                }
            }
        });
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
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
                Answer="N/A";
                Rightanswer = getquestiondata.getAnswer();
                Point=0;
                addjason();
                AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
                task.execute();
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
//        TextView tv = (TextView) findViewById(R.id.firstTextView);
//        tv.clearAnimation();
//        tv.startAnimation(a);
    }

    private void Nextquestion() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mProgressBar1.setVisibility(View.VISIBLE);
        mProgressBar1.setMax( 10 * 1000);
        if (count < Questiondata.size()) {
            txtoptiona.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptionb.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptionc.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptiond.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtoptiona.setTextColor(Color.parseColor("#6D0303"));
            txtoptionb.setTextColor(Color.parseColor("#6D0303"));
            txtoptionc.setTextColor(Color.parseColor("#6D0303"));
            txtoptiond.setTextColor(Color.parseColor("#6D0303"));
            cancelTimer();
            getquestiondata = Questiondata.get(count);
            txtquestion.setText(getquestiondata.getQuestion());
            txtoptiona.setText(getquestiondata.getOptiona());
            txtoptionb.setText(getquestiondata.getOptionb());
            txtoptionc.setText(getquestiondata.getOptionc());
            txtoptiond.setText(getquestiondata.getOptiond());
            txtquestionno.setText((count + 1) + "/" + Questiondata.size());
            startTimer();
            count++;
        } else {
            cancelTimer();
            count++;
            Toast.makeText(Activityquiz1.this, "Your quiz has been finished", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Activityquiz1.this,Questionwiseresult.class);
            intent.putExtra("Loginid",Loginid);
            intent.putExtra("Contestid",Contestid);
            intent.putExtra("From", From);
            startActivity(intent);
            finish();
        }
        click=false;
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String displayText="";
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
            hud.dismiss();
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Activityquiz1.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Activityquiz1.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else if (jsonArray.length() > 0) {
                SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Nextquestion();
            } else {
                Toast.makeText(Activityquiz1.this, "Please try again", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onPreExecute() {
            hud = KProgressHUD.create(Activityquiz1.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(Color.parseColor("#c20e14"))
                    .setLabel("")
                    .setAnimationSpeed(1);
            hud.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public class AsyncCallWSinsertanswer extends AsyncTask<String, Void, Void> {
        String displayText="";
        String Questionid, Count;
        private KProgressHUD hud;
        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            Loginid = prefs.getString("Loginid", null);
            String Name = prefs.getString("Name", null);
            if (count == Questiondata.size()) {
                int size = Questiondata.size();
                //displayText = WebService.Insertanswer(Loginid, Contestid, Questionid, Rightanswer, Answer, Point, counter, size, "Insertanswer");
                displayText = WebService.Insertanswer1(Loginid, Contestid,resultArray.toString(), size, "","Insertanswer1");
                if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                    try {
                        jasonObject = new JSONObject(displayText);
                        jsonArray = jasonObject.getJSONArray("Response");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonrowdata = jsonArray.getJSONObject(i);
                            Count = jsonrowdata.getString("Count");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            hud.dismiss();
            if (displayText.equalsIgnoreCase("connection fault")) {
                Toast.makeText(Activityquiz1.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
                Toast.makeText(Activityquiz1.this, "Please try after some times...", Toast.LENGTH_LONG).show();
            } else  {
                if (count < Questiondata.size()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Nextquestion();
                }
                else
                {
                    Toast.makeText(Activityquiz1.this, "Your quiz has been finished", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Activityquiz1.this,Questionwiseresult.class);
                    intent.putExtra("Loginid",Loginid);
                    intent.putExtra("Contestid",Contestid);
                    intent.putExtra("From", From);
                    startActivity(intent);
                    finish();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            if (count < Questiondata.size()) {
                getquestiondata = Questiondata.get(count-1);
                Questionid = getquestiondata.getQuestionid();
            }
//            hud = KProgressHUD.create(Activityquiz.this)
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
    @Override
    public void onBackPressed() {
//        finish();
//        super.onBackPressed();
    }

    private void playsound(int sound)
    {
        MediaPlayer mediaPlayer=MediaPlayer.create( Activityquiz1.this,sound);
        mediaPlayer.start();
    }
    private  void addjason()
    {
        JSONObject object=new JSONObject();
        try {
            object.put("Questionid",getquestiondata.getQuestionid());
            object.put("Rightanswer",getquestiondata.getAnswer());
            object.put("Answer",Answer);
            object.put("Point",Point);
            object.put("Counter",count);
            resultArray.put(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
