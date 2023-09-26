package com.challengers.of.call;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Random;

public class Displayanimation extends AppCompatActivity {
    String Answer="",Rightanswer="",Contestid="",data="",From="",paid="";
    int count=0,Finalamount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanimation);
        Answer=getIntent().getStringExtra("Answer");
        Rightanswer=getIntent().getStringExtra("Rightanswer");
        Contestid=getIntent().getStringExtra("Contestid");
        data=getIntent().getStringExtra("data");
        paid=getIntent().getStringExtra("paid");
        Finalamount=getIntent().getIntExtra("Finalamount",0);
        count=getIntent().getIntExtra("count",0);
        From=getIntent().getStringExtra("From");
        displayimage();
        AsyncCallWSwait task=new AsyncCallWSwait();
        task.execute();
    }

    private void displayimage() {
        ImageView imageView = findViewById(R.id.imageView);
        if (Answer.equalsIgnoreCase(Rightanswer)) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(6) + 1;

            switch (randomInt) {
                case 1:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.amazing)
                            .into(imageView);
                    break;
                case 2:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.excellent)
                            .into(imageView);
                    break;
                case 3:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.fabulous)
                            .into(imageView);
                    break;
                case 4:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.goahead)
                            .into(imageView);
                    break;
                case 5:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.greatgoing)
                            .into(imageView);
                    break;
                case 6:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.welldone)
                            .into(imageView);

                    break;
            }
        } else {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(3) + 1;

            switch (randomInt) {
                case 1:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.tryagain)
                            .into(imageView);
                    break;
                case 2:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.dontgiveup)
                            .into(imageView);
                    break;
                case 3:
                    Glide.with(Displayanimation.this)
                            .load(R.raw.youcan)
                            .into(imageView);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    public class AsyncCallWSwait extends AsyncTask<String, Void, Void> {
        String displayText;
        String Questionid, Question, Optiona, Optionb, Optionc, Optiond, Answer1;
        private KProgressHUD hud;

        @Override
        protected Void doInBackground(String... params) {
                try {
                    Thread.currentThread();
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            if(paid.equalsIgnoreCase("paid")) {
                Intent intent = new Intent(Displayanimation.this, Activityquiz.class);
                intent.putExtra("Contestid", Contestid);
                intent.putExtra("data", data);
                intent.putExtra("count", count);
                intent.putExtra("Finalamount", Finalamount);
                intent.putExtra("From", From);
                intent.putExtra("From1", "Animate");
                intent.putExtra("Animate", "Animate");
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Displayanimation.this, Activityfreequiz.class);
                intent.putExtra("Contestid", Contestid);
                intent.putExtra("data", data);
                intent.putExtra("count", count);
                intent.putExtra("Finalamount", Finalamount);
                intent.putExtra("From", From);
                intent.putExtra("From1", "Animate");
                intent.putExtra("Animate", "Animate");
                startActivity(intent);
            }
            finish();
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right) ;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
